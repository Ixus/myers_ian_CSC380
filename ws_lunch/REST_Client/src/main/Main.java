package main;

import model.ItemType;
import model.MenuType;
import model.RestaurantType;
import model.RestaurantsType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: imyers
 * Date: 7/27/13
 * Time: 2:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    private static final String menuServlet = "http://localhost:8080/MenuServlet";
    private static final String orderServlet = "http://localhost:8080/OrderServlet";

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        try {
            RestaurantsType restaurants = getMenu();
            List<Integer> selection = showMenu(restaurants);
            submitOrder(selection, restaurants);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public HttpURLConnection createConnection(String url, String methodType) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) (new URL(url)).openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod(methodType);
        return connection;
    }

    public RestaurantsType getMenu() throws IOException, JAXBException {
        HttpURLConnection connection = createConnection(menuServlet, "GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        File file = new File("blah.xml");
        BufferedWriter out = new BufferedWriter(new FileWriter(file));

        String input;
        while ((input = in.readLine()) != null) out.write(input);
        out.close();
        connection.disconnect();

        JAXBContext jaxbContext = JAXBContext.newInstance(RestaurantsType.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        return (RestaurantsType) unmarshaller.unmarshal(file);
    }

    public List<Integer> showMenu(RestaurantsType restaurants) {

        // Get Restaurant
        for (int i = 0; i < restaurants.getRestaurant().size(); i++) {
            System.out.println(i + ". " + restaurants.getRestaurant().get(i).getTitle());
        }
        Scanner scan = new Scanner(System.in);
        Integer restaurantIndex = scan.nextInt();

        // Get Menu Item
        for(int i = 0; i < restaurants.getRestaurant().get(restaurantIndex).getMenu().getItem().size(); i++) {
            ItemType item = restaurants.getRestaurant().get(restaurantIndex).getMenu().getItem().get(i);
            System.out.println(i + ". " + item.getName() + " ($" + item.getPrice() + ")");
        }
        Integer itemIndex = scan.nextInt();

        List<Integer> list = new ArrayList<Integer>();
        list.add(restaurantIndex);
        list.add(itemIndex);
        return list;
    }

    public void submitOrder(List<Integer> selection, RestaurantsType restaurants) throws IOException, JAXBException {
        int restaurantIndex = selection.get(0);
        int itemIndex = selection.get(1);

        RestaurantsType myOrder = new RestaurantsType();

        // Create restaurant
        RestaurantType restaurant = new RestaurantType();
        restaurant.setMenu(new MenuType());
        restaurant.setTitle(restaurants.getRestaurant().get(restaurantIndex).getTitle());

        // Create menu item
        ItemType item = restaurants.getRestaurant().get(restaurantIndex).getMenu().getItem().get(itemIndex);

        // Add
        restaurant.getMenu().getItem().add(item);
        myOrder.getRestaurant().add(restaurant);

        // Connect to server
        HttpURLConnection connection = createConnection(orderServlet, "POST");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("Protocol-Version", "1.1");
        connection.setRequestProperty("Content-Type", "text/xml");

        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());

        JAXBContext jaxbContext = JAXBContext.newInstance(RestaurantsType.class);
        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.marshal(myOrder, out);

        out.flush();
        out.close();
        System.out.println("Response code: " + connection.getResponseCode());
        connection.disconnect();

    }
}
