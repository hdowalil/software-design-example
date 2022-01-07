package info.fivecdesign.gamecollection.earthtrivia.backend.generators;

import java.util.Comparator;

public class ComparatorCityDistance implements Comparator<CityDistancePair> {

	@Override
	public int compare(CityDistancePair o1, CityDistancePair o2) {
		return Integer.valueOf(o1.getDistanceInKilometers()).compareTo(o2.getDistanceInKilometers());
	}
	
}
