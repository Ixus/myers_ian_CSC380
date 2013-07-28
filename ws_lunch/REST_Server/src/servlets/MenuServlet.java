package servlets;

import model.ItemType;
import model.MenuType;
import model.RestaurantType;
import model.RestaurantsType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: imyers
 * Date: 7/27/13
 * Time: 2:19 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/MenuServlet")
public class MenuServlet extends javax.servlet.http.HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(RestaurantsType.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            RestaurantsType restaurantsType = this.getRestaurants();
            marshaller.marshal(restaurantsType, response.getWriter());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private RestaurantsType getRestaurants() {
        RestaurantsType restaurants = new RestaurantsType();

        // Create restaurant
        RestaurantType restaurant = new RestaurantType();
        restaurant.setMenu(new MenuType());
        restaurant.setTitle("Fluffies Chicken");

        // Create menu items
        ItemType item = new ItemType();
        item.setName("Couch Cushion Chicken Bucket");
        item.setPrice((float)2.50);

        ItemType item2 = new ItemType();
        item2.setName("Extra Crispy Chicken 10 Day Rule Deluxe");
        item2.setPrice((float)1.50);

        ItemType item3 = new ItemType();
        item3.setName("Large Coke");
        item3.setPrice((float)2.50);

        // Add
        restaurant.getMenu().getItem().add(item);
        restaurant.getMenu().getItem().add(item2);
        restaurant.getMenu().getItem().add(item3);
        restaurants.getRestaurant().add(restaurant);

        // Create Restaurant
        RestaurantType restaurant2 = new RestaurantType();
        restaurant2.setMenu(new MenuType());
        restaurant2.setTitle("Yangs Kitten");

        // Create menu items
        ItemType item2_1 = new ItemType();
        item2_1.setName("Kitten Raspberry Pie");
        item2_1.setPrice((float)4.50);

        ItemType item2_2 = new ItemType();
        item2_2.setName("Chocolate Kitten Cupcake");
        item2_2.setPrice((float).99);

        ItemType item2_3 = new ItemType();
        item2_3.setName("Large Coke");
        item2_3.setPrice((float)2.50);

        // Add
        restaurant2.getMenu().getItem().add(item2_1);
        restaurant2.getMenu().getItem().add(item2_2);
        restaurant2.getMenu().getItem().add(item2_3);
        restaurants.getRestaurant().add(restaurant2);

        return restaurants;
    }
}
