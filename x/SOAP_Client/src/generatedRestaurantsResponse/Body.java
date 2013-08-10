
package generatedRestaurantsResponse;

import javax.xml.bind.annotation.*;


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
 *         &lt;element ref="{http://neumont.edu/imyers/lunch/restaurants}GetRestaurantsResponse"/>
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
    "getRestaurantsResponse"
})
@XmlRootElement(name = "Body", namespace = "http://www.w3.org/2001/12/soap-envelope")
public class Body {

    @XmlElement(name = "GetRestaurantsResponse", namespace = "http://neumont.edu/imyers/lunch/restaurants", required = true)
    protected GetRestaurantsResponse getRestaurantsResponse;

    /**
     * Gets the value of the getRestaurantsResponse property.
     * 
     * @return
     *     possible object is
     *     {@link GetRestaurantsResponse }
     *     
     */
    public GetRestaurantsResponse getGetRestaurantsResponse() {
        return getRestaurantsResponse;
    }

    /**
     * Sets the value of the getRestaurantsResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetRestaurantsResponse }
     *     
     */
    public void setGetRestaurantsResponse(GetRestaurantsResponse value) {
        this.getRestaurantsResponse = value;
    }

}
