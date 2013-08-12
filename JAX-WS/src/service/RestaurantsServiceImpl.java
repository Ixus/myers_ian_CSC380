package service;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "service.RestaurantsService", serviceName = "RestaurantsService")
public class RestaurantsServiceImpl implements RestaurantsService {
    private static final List<Restaurant> RESTAURANT_LIST;

    static {
        RESTAURANT_LIST = new ArrayList<Restaurant>();
        Restaurant r1 = new Restaurant("Carl's Junior");
        r1.addToMenu("Big Burger");
        r1.addToMenu("Famous Star");
        Restaurant r2 = new Restaurant("McDonalds");
        r2.addToMenu("Quarter Pounder");
        r2.addToMenu("Big Mac");
        RESTAURANT_LIST.add(r1);
        RESTAURANT_LIST.add(r2);
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return RESTAURANT_LIST;
    }
}
