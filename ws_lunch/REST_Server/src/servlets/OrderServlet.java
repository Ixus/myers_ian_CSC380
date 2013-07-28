package servlets;

import model.RestaurantsType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: imyers
 * Date: 7/27/13
 * Time: 2:35 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RestaurantsType.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            RestaurantsType restaurants = (RestaurantsType)unmarshaller.unmarshal(request.getInputStream());
            System.out.println(restaurants.getRestaurant().get(0).getTitle());
            System.out.println(restaurants.getRestaurant().get(0).getMenu().getItem().get(0).getName());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
