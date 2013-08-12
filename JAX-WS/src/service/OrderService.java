package service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "OrderService", targetNamespace = "http://localhost/OrderService")
public interface OrderService {
    @WebMethod(operationName = "submitOrder")
    void submitOrder(@WebParam(name = "order") Order order);
}
