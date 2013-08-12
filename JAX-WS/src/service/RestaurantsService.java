package service;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService(name = "RestaurantsService", targetNamespace = "http://localhost/RestaurantsService")
public interface RestaurantsService {
    @WebMethod(operationName = "getRestaurants")
    public @WebResult(name = "List<Restaurant>")
    List<Restaurant> getRestaurants();
}
