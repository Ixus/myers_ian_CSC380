
package generatedOrder;

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
 *         &lt;element ref="{http://neumont.edu/imyers/lunch/restaurants}Restaurant"/>
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
    "restaurant"
})
@XmlRootElement(name = "GetOrderRequest", namespace = "http://neumont.edu/imyers/lunch/restaurants")
public class GetOrderRequest {

    @XmlElement(name = "Restaurant", namespace = "http://neumont.edu/imyers/lunch/restaurants", required = true)
    protected Restaurant restaurant;

    /**
     * Gets the value of the restaurant property.
     * 
     * @return
     *     possible object is
     *     {@link Restaurant }
     *
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * Sets the value of the restaurant property.
     *
     * @param value
     *     allowed object is
     *     {@link Restaurant }
     *     
     */
    public void setRestaurant(Restaurant value) {
        this.restaurant = value;
    }

}
