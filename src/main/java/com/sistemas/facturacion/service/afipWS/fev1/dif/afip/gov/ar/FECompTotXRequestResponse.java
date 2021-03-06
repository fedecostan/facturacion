
package com.sistemas.facturacion.service.afipWS.fev1.dif.afip.gov.ar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FECompTotXRequestResult" type="{http://ar.gov.afip.dif.FEV1/}FERegXReqResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "feCompTotXRequestResult"
})
@XmlRootElement(name = "FECompTotXRequestResponse")
public class FECompTotXRequestResponse {

    @XmlElement(name = "FECompTotXRequestResult")
    protected FERegXReqResponse feCompTotXRequestResult;

    /**
     * Gets the value of the feCompTotXRequestResult property.
     * 
     * @return
     *     possible object is
     *     {@link FERegXReqResponse }
     *     
     */
    public FERegXReqResponse getFECompTotXRequestResult() {
        return feCompTotXRequestResult;
    }

    /**
     * Sets the value of the feCompTotXRequestResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link FERegXReqResponse }
     *     
     */
    public void setFECompTotXRequestResult(FERegXReqResponse value) {
        this.feCompTotXRequestResult = value;
    }

}
