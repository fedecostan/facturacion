
package com.sistemas.facturacion.service.afip.gov.afip.desein.dvadac.sua.view.wsaa;

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
 *         &lt;element name="loginCmsReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "loginCmsReturn"
})
@XmlRootElement(name = "loginCmsResponse")
public class LoginCmsResponse {

    @XmlElement(required = true)
    protected String loginCmsReturn;

    /**
     * Gets the value of the loginCmsReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginCmsReturn() {
        return loginCmsReturn;
    }

    /**
     * Sets the value of the loginCmsReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginCmsReturn(String value) {
        this.loginCmsReturn = value;
    }

}
