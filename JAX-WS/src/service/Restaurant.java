package service;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private List<String> menu = new ArrayList<String>();

    public Restaurant() {
    }

    public Restaurant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMenu() {
        return menu;
    }

    public void setMenu(List<String> menu) {
        this.menu = menu;
    }

    public void addToMenu(String menuItem) {
        menu.add(menuItem);
    }
}
