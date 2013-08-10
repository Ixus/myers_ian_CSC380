package servlets;

import generatedOrder.GetOrderRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;

@WebServlet(name = "OrderServlet", urlPatterns="/order")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void receiveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(GetOrderRequest.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            GetOrderRequest order = (GetOrderRequest)unmarshaller.unmarshal(request.getInputStream());
            System.out.println(order.getRestaurant().getMenu().getItem()); // PROBLEM??
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
