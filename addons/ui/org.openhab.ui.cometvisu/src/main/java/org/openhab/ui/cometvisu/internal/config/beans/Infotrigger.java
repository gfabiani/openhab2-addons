//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.17 at 06:25:15 PM CET 
//


package org.openhab.ui.cometvisu.internal.config.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for infotrigger complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="infotrigger"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="layout" type="{}layout" minOccurs="0"/&gt;
 *         &lt;element name="label" type="{}label" minOccurs="0"/&gt;
 *         &lt;element name="address" type="{}address" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="upvalue" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="shortupvalue" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="downvalue" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="shortdownvalue" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="uplabel" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="downlabel" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="shorttime" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="change"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;enumeration value="absolute"/&gt;
 *             &lt;enumeration value="relative"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute ref="{}min"/&gt;
 *       &lt;attribute ref="{}max"/&gt;
 *       &lt;attribute ref="{}mapping"/&gt;
 *       &lt;attribute ref="{}styling"/&gt;
 *       &lt;attribute ref="{}align"/&gt;
 *       &lt;attribute ref="{}format"/&gt;
 *       &lt;attribute name="infoposition"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;enumeration value="left"/&gt;
 *             &lt;enumeration value="middle"/&gt;
 *             &lt;enumeration value="right"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute ref="{}flavour"/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "infotrigger", propOrder = {
    "layout",
    "label",
    "address"
})
public class Infotrigger {

    protected Layout layout;
    protected Label label;
    @XmlElement(required = true)
    protected List<Address> address;
    @XmlAttribute(name = "upvalue")
    protected String upvalue;
    @XmlAttribute(name = "shortupvalue")
    protected String shortupvalue;
    @XmlAttribute(name = "downvalue")
    protected String downvalue;
    @XmlAttribute(name = "shortdownvalue")
    protected String shortdownvalue;
    @XmlAttribute(name = "uplabel")
    protected String uplabel;
    @XmlAttribute(name = "downlabel")
    protected String downlabel;
    @XmlAttribute(name = "shorttime")
    protected BigDecimal shorttime;
    @XmlAttribute(name = "change")
    protected String change;
    @XmlAttribute(name = "min")
    protected BigDecimal min;
    @XmlAttribute(name = "max")
    protected BigDecimal max;
    @XmlAttribute(name = "mapping")
    protected String mapping;
    @XmlAttribute(name = "styling")
    protected String styling;
    @XmlAttribute(name = "align")
    protected String align;
    @XmlAttribute(name = "format")
    protected String format;
    @XmlAttribute(name = "infoposition")
    protected String infoposition;
    @XmlAttribute(name = "flavour")
    protected String flavour;

    /**
     * Gets the value of the layout property.
     * 
     * @return
     *     possible object is
     *     {@link Layout }
     *     
     */
    public Layout getLayout() {
        return layout;
    }

    /**
     * Sets the value of the layout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Layout }
     *     
     */
    public void setLayout(Layout value) {
        this.layout = value;
    }

    /**
     * Gets the value of the label property.
     * 
     * @return
     *     possible object is
     *     {@link Label }
     *     
     */
    public Label getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value
     *     allowed object is
     *     {@link Label }
     *     
     */
    public void setLabel(Label value) {
        this.label = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the address property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Address }
     * 
     * 
     */
    public List<Address> getAddress() {
        if (address == null) {
            address = new ArrayList<Address>();
        }
        return this.address;
    }

    /**
     * Gets the value of the upvalue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpvalue() {
        return upvalue;
    }

    /**
     * Sets the value of the upvalue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpvalue(String value) {
        this.upvalue = value;
    }

    /**
     * Gets the value of the shortupvalue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortupvalue() {
        return shortupvalue;
    }

    /**
     * Sets the value of the shortupvalue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortupvalue(String value) {
        this.shortupvalue = value;
    }

    /**
     * Gets the value of the downvalue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDownvalue() {
        return downvalue;
    }

    /**
     * Sets the value of the downvalue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDownvalue(String value) {
        this.downvalue = value;
    }

    /**
     * Gets the value of the shortdownvalue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortdownvalue() {
        return shortdownvalue;
    }

    /**
     * Sets the value of the shortdownvalue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortdownvalue(String value) {
        this.shortdownvalue = value;
    }

    /**
     * Gets the value of the uplabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUplabel() {
        return uplabel;
    }

    /**
     * Sets the value of the uplabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUplabel(String value) {
        this.uplabel = value;
    }

    /**
     * Gets the value of the downlabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDownlabel() {
        return downlabel;
    }

    /**
     * Sets the value of the downlabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDownlabel(String value) {
        this.downlabel = value;
    }

    /**
     * Gets the value of the shorttime property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getShorttime() {
        return shorttime;
    }

    /**
     * Sets the value of the shorttime property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setShorttime(BigDecimal value) {
        this.shorttime = value;
    }

    /**
     * Gets the value of the change property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChange() {
        return change;
    }

    /**
     * Sets the value of the change property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChange(String value) {
        this.change = value;
    }

    /**
     * Gets the value of the min property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMin() {
        return min;
    }

    /**
     * Sets the value of the min property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMin(BigDecimal value) {
        this.min = value;
    }

    /**
     * Gets the value of the max property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMax() {
        return max;
    }

    /**
     * Sets the value of the max property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMax(BigDecimal value) {
        this.max = value;
    }

    /**
     * Gets the value of the mapping property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMapping() {
        return mapping;
    }

    /**
     * Sets the value of the mapping property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMapping(String value) {
        this.mapping = value;
    }

    /**
     * Gets the value of the styling property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStyling() {
        return styling;
    }

    /**
     * Sets the value of the styling property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStyling(String value) {
        this.styling = value;
    }

    /**
     * Gets the value of the align property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlign() {
        return align;
    }

    /**
     * Sets the value of the align property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlign(String value) {
        this.align = value;
    }

    /**
     * Gets the value of the format property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the value of the format property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormat(String value) {
        this.format = value;
    }

    /**
     * Gets the value of the infoposition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInfoposition() {
        return infoposition;
    }

    /**
     * Sets the value of the infoposition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInfoposition(String value) {
        this.infoposition = value;
    }

    /**
     * Gets the value of the flavour property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlavour() {
        return flavour;
    }

    /**
     * Sets the value of the flavour property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlavour(String value) {
        this.flavour = value;
    }

}