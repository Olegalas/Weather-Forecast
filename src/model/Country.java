package model;

import exceptions.SearchException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dexter on 02.02.16.
 */
public final class Country {

    private final String name;
    private final List<City> cities = new ArrayList<>();

    public Country(String name, String city, String id){
        this.name = name;
        this.cities.add(new City(city, id));
    }

    public void addCity(String city, String id){
        this.cities.add(new City(city, id));
    }

    public String getName() {
        return name;
    }

    public List<City> getCity() {
        return cities;
    }

    public City getCityByName(String city) throws SearchException {

        for(City tmp : this.cities){
            if(tmp.getName().equals(city)){
                return tmp;
            }
        }
        throw new SearchException("The City wasn't found");
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", city=" + cities + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (name != null ? !name.equals(country.name) : country.name != null) return false;
        return cities != null ? cities.equals(country.cities) : country.cities == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (cities != null ? cities.hashCode() : 0);
        return result;
    }
}
