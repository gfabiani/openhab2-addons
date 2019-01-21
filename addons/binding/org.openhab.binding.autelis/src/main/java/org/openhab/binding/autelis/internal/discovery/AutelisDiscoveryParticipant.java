/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.autelis.internal.discovery;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.smarthome.config.discovery.DiscoveryResult;
import org.eclipse.smarthome.config.discovery.DiscoveryResultBuilder;
import org.eclipse.smarthome.config.discovery.UpnpDiscoveryParticipant;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.ThingUID;
import org.jupnp.model.meta.RemoteDevice;
import org.openhab.binding.autelis.AutelisBindingConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Discovery Service for Autelis Pool Controllers.
 *
 * @author Dan Cunningham
 *
 */
public class AutelisDiscoveryParticipant implements UpnpDiscoveryParticipant {

    private final Logger logger = LoggerFactory.getLogger(AutelisDiscoveryParticipant.class);

    private static String MANUFACTURER = "autelis";
    private static String MODEL_PENTAIR = "pc100p";
    private static String MODEL_JANDY = "pc100j";

    @Override
    public Set<ThingTypeUID> getSupportedThingTypeUIDs() {
        return AutelisBindingConstants.SUPPORTED_THING_TYPES_UIDS;
    }

    @Override
    public DiscoveryResult createResult(RemoteDevice device) {
        ThingUID uid = getThingUID(device);
        if (uid != null) {
            Map<String, Object> properties = new HashMap<>(3);

            URL url = device.getDetails().getBaseURL();
            String label = device.getDetails().getFriendlyName();
            int port = url.getPort() > 0 ? url.getPort() : 80;

            properties.put("host", url.getHost());
            properties.put("user", "admin");
            properties.put("password", "admin");
            properties.put("port", new Integer(port));

            DiscoveryResult result = DiscoveryResultBuilder.create(uid).withProperties(properties).withLabel(label)
                    .build();

            logger.debug("Created a DiscoveryResult for device '{}' with UDN '{}'",
                    device.getDetails().getFriendlyName(), device.getIdentity().getUdn().getIdentifierString());
            return result;
        } else {
            return null;
        }
    }

    @Override
    public ThingUID getThingUID(RemoteDevice device) {
        if (device.getDetails().getManufacturerDetails().getManufacturer() != null
                && device.getDetails().getModelDetails().getModelNumber() != null) {
            logger.trace("UPNP {} : {}", device.getDetails().getManufacturerDetails().getManufacturer(),
                    device.getDetails().getModelDetails().getModelNumber());
            if (device.getDetails().getManufacturerDetails().getManufacturer().toLowerCase().startsWith(MANUFACTURER)) {
                logger.debug("Autelis Pool Control Found at {}", device.getDetails().getBaseURL());
                String id = device.getIdentity().getUdn().getIdentifierString().replaceAll(":", "").toUpperCase();
                if (device.getDetails().getModelDetails().getModelNumber().toLowerCase().startsWith(MODEL_PENTAIR)) {
                    return new ThingUID(AutelisBindingConstants.PENTAIR_THING_TYPE_UID, id);
                }
                if (device.getDetails().getModelDetails().getModelNumber().toLowerCase().startsWith(MODEL_JANDY)) {
                    return new ThingUID(AutelisBindingConstants.JANDY_THING_TYPE_UID, id);
                }
            }
        }
        return null;
    }
}
