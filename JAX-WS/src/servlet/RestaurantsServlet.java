package servlet;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;
import service.RestaurantsServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.xml.ws.Endpoint;

@WebServlet("/services/restaurants")
public class RestaurantsServlet extends CXFNonSpringServlet {

    protected void loadBus(ServletConfig sc)  {
        super.loadBus(sc);

        // You could add the endpoint publish code here
        Bus bus = getBus();
        BusFactory.setDefaultBus(bus);
        Endpoint.publish("/RestaurantsService", new RestaurantsServiceImpl());
    }

}
