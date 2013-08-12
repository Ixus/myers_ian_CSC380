package service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "service.OrderService", serviceName = "OrderService")
public class OrderServiceImpl implements OrderService {
    @Override
    public void submitOrder(@WebParam(name = "order") Order order) {
        System.out.println(order.getRestaurantName() + " - " + order.getItemName());
    }
}
