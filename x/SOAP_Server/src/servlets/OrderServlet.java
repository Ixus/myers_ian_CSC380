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
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

@WebServlet(name = "OrderServlet", urlPatterns="/order")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        receiveOrder(request, response);
    }

    protected void receiveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(generatedOrder.Envelope.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            generatedOrder.Envelope order = (generatedOrder.Envelope)unmarshaller.unmarshal(request.getInputStream());
            System.out.println("Order submitted!");
            System.out.println(order.getBody().getGetOrderRequest().getRestaurant().getMenu().getItem().getName());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
