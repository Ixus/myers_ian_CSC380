package servlets;

import com.sun.xml.internal.messaging.saaj.soap.Envelope;
import generatedRestaurantsResponse.GetRestaurantsResponse;
import generatedRestaurantsResponse.Item;
import generatedRestaurantsResponse.Menu;
import generatedRestaurantsResponse.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "RestaurantsServlet", urlPatterns="/restaurants")
public class RestaurantsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getRestaurantsRequest(request, response);
    }

    protected void getRestaurantsRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(generatedRestaurantsResponse.Envelope.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            generatedRestaurantsResponse.Envelope getRestaurants = getRestaurants();
            marshaller.marshal(getRestaurants, response.getWriter());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private generatedRestaurantsResponse.Envelope getRestaurants() {
        GetRestaurantsResponse restaurants = new GetRestaurantsResponse();

        // Create SOAP envelope & body
        generatedRestaurantsResponse.Envelope envelope = new generatedRestaurantsResponse.Envelope();
        generatedRestaurantsResponse.Body body = new generatedRestaurantsResponse.Body();

        // Create restaurant
        Restaurant restaurant = new Restaurant();
        restaurant.setMenu(new Menu());
        restaurant.setTitle("Fluffies Chicken");

        // Create menu items
        Item item = new Item();
        item.setName("Couch Cushion Chicken Bucket");
        item.setPrice(new BigDecimal(2.50));

        Item item2 = new Item();
        item2.setName("Extra Crispy Chicken 10 Day Rule Deluxe");
        item2.setPrice(new BigDecimal(1.50));

        Item item3 = new Item();
        item3.setName("Large Coke");
        item3.setPrice(new BigDecimal(2.50));

        // Add
        restaurant.getMenu().getItem().add(item);
        restaurant.getMenu().getItem().add(item2);
        restaurant.getMenu().getItem().add(item3);
        restaurants.getRestaurant().add(restaurant);

        // Create Restaurant
        Restaurant restaurant2 = new Restaurant();
        restaurant2.setMenu(new Menu());
        restaurant2.setTitle("Yangs Kitten");

        // Create menu items
        Item item2_1 = new Item();
        item2_1.setName("Kitten Raspberry Pie");
        item2_1.setPrice(new BigDecimal(4.50));

        Item item2_2 = new Item();
        item2_2.setName("Chocolate Kitten Cupcake");
        item2_2.setPrice(new BigDecimal(.99));

        Item item2_3 = new Item();
        item2_3.setName("Large Coke");
        item2_3.setPrice(new BigDecimal(2.50));

        // Add
        restaurant2.getMenu().getItem().add(item2_1);
        restaurant2.getMenu().getItem().add(item2_2);
        restaurant2.getMenu().getItem().add(item2_3);
        restaurants.getRestaurant().add(restaurant2);

        // ADD FINISH
        body.setGetRestaurantsResponse(restaurants);
        envelope.setBody(body);

        return envelope;
    }
}
