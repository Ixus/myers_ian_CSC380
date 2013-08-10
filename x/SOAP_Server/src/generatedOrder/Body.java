
package generatedOrder;

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
 *         &lt;element ref="{http://neumont.edu/imyers/lunch/restaurants}GetOrderRequest"/>
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
    "getOrderRequest"
})
@XmlRootElement(name = "Body", namespace = "http://www.w3.org/2001/12/soap-envelope")
public class Body {

    @XmlElement(name = "GetOrderRequest", namespace = "http://neumont.edu/imyers/lunch/restaurants", required = true)
    protected GetOrderRequest getOrderRequest;

    /**
     * Gets the value of the getOrderRequest property.
     * 
     * @return
     *     possible object is
     *     {@link GetOrderRequest }
     *     
     */
    public GetOrderRequest getGetOrderRequest() {
        return getOrderRequest;
    }

    /**
     * Sets the value of the getOrderRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetOrderRequest }
     *     
     */
    public void setGetOrderRequest(GetOrderRequest value) {
        this.getOrderRequest = value;
    }

}
