/**
 * Copyright (c) 2010-2018 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.openwebnet.handler;

import static org.openhab.binding.openwebnet.OpenWebNetBindingConstants.*;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import javax.measure.Quantity;
import javax.measure.Unit;

import org.eclipse.smarthome.core.library.types.QuantityType;
import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusDetail;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.core.types.RefreshType;
import org.openwebnet.message.BaseOpenMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link OpenWebNetThingHandler} is responsible for handling commands for a OpenWebNet device.
 * It's the abstract class for all OpenWebNet things. It should be extended by each specific OpenWebNet category of
 * device (WHO)
 *
 * @author Massimo Valla - Initial contribution
 */
// TODO rename to 'DeviceHandler'
public abstract class OpenWebNetThingHandler extends BaseThingHandler {

    private final Logger logger = LoggerFactory.getLogger(OpenWebNetThingHandler.class);

    protected OpenWebNetBridgeHandler bridgeHandler;
    protected String ownId; // OpenWebNet identifier for this device: WHO.WHERE
    protected String deviceWhere; // this device WHERE address

    public OpenWebNetThingHandler(Thing thing) {
        super(thing);
    }

    @Override
    public void initialize() {
        logger.debug("==OWN:ThingHandler== initialize() thing={}", thing.getUID());
        Bridge bridge = getBridge();
        if (bridge != null && bridge.getHandler() != null) {
            bridgeHandler = (OpenWebNetBridgeHandler) bridge.getHandler();
            if (getConfig().get(CONFIG_PROPERTY_WHERE) == null) {
                logger.warn("==OWN:ThingHandler== WHERE parameter in configuration is null or invalid. thing={}",
                        thing.getUID());
                updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR,
                        "WHERE parameter in configuration is null or invalid");
                return;
            }
            deviceWhere = (String) getConfig().get(CONFIG_PROPERTY_WHERE);
            // TODO check range for WHERE
            ownId = bridgeHandler.ownIdFromWhere(deviceWhere, this);
            bridgeHandler.registerDevice(ownId, this);
            logger.debug("==OWN:ThingHandler== associated thing to bridge with ownId={}", ownId);
            updateStatus(ThingStatus.UNKNOWN, ThingStatusDetail.NONE, "waiting state update...");
            // TODO handleCommand(REFRESH) : is it called automatically ? otherwise do here a:
            // bridgeHandler.requestDeviceState(getThing().getUID());
        } else {
            logger.warn(
                    "==OWN:ThingHandler== No bridge associated, please assign a bridge in thing configuration. thing={}",
                    thing.getUID());
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR,
                    "No bridge associated, please assign a bridge in thing configuration.");
        }
    }

    /**
     * Handles a command from openHAB to this thing
     *
     */
    @Override
    public void handleCommand(ChannelUID channel, Command command) {
        logger.debug("==OWN:ThingHandler== handleCommand() (command={} - channel={})", command, channel);
        if (bridgeHandler == null || bridgeHandler.gateway == null) {
            logger.info("==OWN:ThingHandler== Thing {} is not associated to any gateway, skipping command",
                    getThing().getUID());
            return;
        }
        if (!bridgeHandler.gateway.isConnected()) {
            logger.warn("==OWN:ThingHandler== Gateway is NOT connected, setting thing={} to OFFLINE", thing.getUID());
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.BRIDGE_OFFLINE);
            return;
        }
        if (command instanceof RefreshType) {
            logger.debug("==OWN:ThingHandler== Refreshing channel {}", channel);
            // TODO move to a refreshChannel() method that subclasses can implement to disable setting the thing offline
            requestChannelState(channel);
            // set a schedule to put device OFFLINE if no answer is received after THING_STATE_REQ_TIMEOUT
            scheduler.schedule(() -> {
                // if state is still unknown after timer ends, set the thing OFFLINE
                if (thing.getStatus().equals(ThingStatus.UNKNOWN)) {
                    logger.info(
                            "==OWN:ThingHandler== Thing state request timer expired, still unknown. Setting thing={} to OFFLINE",
                            thing.getUID());
                    updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR,
                            "Could not get channel state");
                    logger.debug("==OWN:ThingHandler== Thing OFFLINE");
                }
            }, THING_STATE_REQ_TIMEOUT, TimeUnit.SECONDS);
            return;
        } else {
            handleChannelCommand(channel, command);
        }
    }

    /**
     * Handles a command for the specific channel for this thing.
     * It must be implemented by each specific OpenWebNet category of device (WHO), based on channel
     *
     * @param channel specific ChannleUID
     * @param command the Command to be executed
     */
    protected abstract void handleChannelCommand(ChannelUID channel, Command command);

    /**
     * Handle incoming message from OWN network directed to a device. It should be further implemented by each specific
     * OpenWebNet category of device (WHO)
     *
     * @param msg BaseOpenMessage to handle
     */
    protected void handleMessage(BaseOpenMessage msg) {
        // logger.debug("==OWN:ThingHandler== handleMessage() for thing: {}", getThing().getUID());
        // update status to ONLINE if not already online
        if (ThingStatus.ONLINE != getThing().getStatus()) {
            updateStatus(ThingStatus.ONLINE);
        }
    }

    /**
     * Request to gateway state for thing channel. It must be implemented by each specific OpenWebNet category of device
     * (WHO)
     *
     * @param channel ChannleUID to be requested
     */
    protected abstract void requestChannelState(ChannelUID channel);

    @Override
    public void handleRemoval() {
        logger.debug("==OWN:ThingHandler== handleRemoval() thing={}", getThing().getUID());
        if (bridgeHandler != null) {
            bridgeHandler.unregisterDevice(ownId);
        }
        super.handleRemoval();
    }

    @Override
    public void dispose() {
        logger.debug("==OWN:ThingHandler== dispose() thing={}", getThing().getUID());
        super.dispose();
    }

    /**
     * Returns a prefix String for ownId specific for each handler. To be implemented by sub-classes.
     *
     **/
    protected abstract String ownIdPrefix();

    protected <U extends Quantity<U>> QuantityType<U> commandToQuantityType(Command command, Unit<U> defaultUnit) {
        if (command instanceof QuantityType) {
            return (QuantityType<U>) command;
        }
        return new QuantityType<U>(new BigDecimal(command.toString()), defaultUnit);
    }
}