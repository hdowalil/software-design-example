package info.fivecdesign.gamecollection.earthtrivia.backend.generators;

import java.util.Comparator;

import info.fivecdesign.gamecollection.earthtrivia.backend.info.City;

/**
 * Created by Herbert on 24.08.2015.
 */
public class CityLatComparator implements Comparator<City> {

    @Override
    public int compare(City lhs, City rhs) {
        return Double.valueOf(lhs.getLatCoordinate()).compareTo(Double.valueOf(rhs.getLatCoordinate()));
    }
}
