package info.fivecdesign.gamecollection.earthtrivia.backend.generators;

import javax.annotation.concurrent.Immutable;

import info.fivecdesign.gamecollection.earthtrivia.backend.info.City;

@Immutable
public class CityDistancePair {

	private final static double AVERAGE_RADIUS_OF_EARTH = 6371;

	private City city1;
	private City city2;
	private int distanceInKilometers = 0;

	public CityDistancePair(City city1, City city2) {
		super();
		this.city1 = city1;
		this.city2 = city2;
		this.distanceInKilometers = calculateDistance(city1.getLatCoordinate(),city1.getLongCoordinate(), city2.getLatCoordinate(), city2.getLongCoordinate());
	}

	public static int calculateDistance(double userLat, double userLng, double venueLat, double venueLng) {

		double latDistance = Math.toRadians(userLat - venueLat);
		double lngDistance = Math.toRadians(userLng - venueLng);

		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(userLat))
				* Math.cos(Math.toRadians(venueLat)) * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH * c));
	}

	public City getCity1() {
		return city1;
	}

	public City getCity2() {
		return city2;
	}

	public int getDistanceInKilometers() {
		return distanceInKilometers;
	}
	
}
