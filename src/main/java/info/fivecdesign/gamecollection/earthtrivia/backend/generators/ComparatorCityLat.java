package info.fivecdesign.gamecollection.earthtrivia.backend.generators;

import java.util.Comparator;

import info.fivecdesign.gamecollection.earthtrivia.backend.info.City;

public class ComparatorCityLat implements Comparator<City> {

    @Override
    public int compare(City lhs, City rhs) {
        return Double.valueOf(lhs.getLatCoordinate()).compareTo(Double.valueOf(rhs.getLatCoordinate()));
    }
}
