
package generatedRestaurantsRequest;

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
 *         &lt;element ref="{http://neumont.edu/imyers/lunch/restaurants}GetRestaurants"/>
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
    "getRestaurants"
})
@XmlRootElement(name = "Body", namespace = "http://www.w3.org/2001/12/soap-envelope")
public class Body {

    @XmlElement(name = "GetRestaurants", namespace = "http://neumont.edu/imyers/lunch/restaurants", required = true)
    protected GetRestaurants getRestaurants;

    /**
     * Gets the value of the getRestaurants property.
     * 
     * @return
     *     possible object is
     *     {@link GetRestaurants }
     *     
     */
    public GetRestaurants getGetRestaurants() {
        return getRestaurants;
    }

    /**
     * Sets the value of the getRestaurants property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetRestaurants }
     *     
     */
    public void setGetRestaurants(GetRestaurants value) {
        this.getRestaurants = value;
    }

}
