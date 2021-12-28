package info.fivecdesign.gamecollection.earthtrivia.backend.generators;

import info.fivecdesign.gamecollection.earthtrivia.backend.gpsutils.LatLonPoint;
import info.fivecdesign.gamecollection.earthtrivia.backend.gpsutils.LatLonUtils;

/**
 *
 * https://en.wikipedia.org/wiki/Latitude_and_longitude_of_cities,_A-H
 * http://www.infoplease.com/ipa/A0001769.html
 * https://github.com/deremer/Cities/tree/master/countries
 *
 * Created by Herbert on 01.08.2015.
 */
public class CityDistance implements Generator {

    private final static double AVERAGE_RADIUS_OF_EARTH = 6371;

    private int calculateDistance(double userLat, double userLng,
                                 double venueLat, double venueLng) {

        double latDistance = Math.toRadians(userLat - venueLat);
        double lngDistance = Math.toRadians(userLng - venueLng);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(venueLat))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH * c));
    }

    @Override
    public Question generate() {
        return null;
    }

    public static void main(String[] args) {
        CityDistance dist = new CityDistance();
        System.out.println("Adelaide to Alice " + Integer.toString(dist.calculateDistance(34.55,138.53,23.42,133.53)));
        LatLonPoint vienna = new LatLonPoint(48.12, 16.22);
        LatLonPoint lisbon = new LatLonPoint(38.44, -9.08);
        System.out.println("Vienna to Lisbon " + LatLonUtils.getQuickEstimate(vienna,lisbon));
    }

}
