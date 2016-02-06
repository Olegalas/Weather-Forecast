package model;

/**
 * Created by dexter on 04.02.16.
 */
public class City {

    private final String name;
    private final String id;

    public City(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return name ;
    }
}
