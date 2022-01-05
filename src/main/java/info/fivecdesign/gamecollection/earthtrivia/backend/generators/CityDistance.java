package info.fivecdesign.gamecollection.earthtrivia.backend.generators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import info.fivecdesign.gamecollection.earthtrivia.backend.info.CitiesContinents;
import info.fivecdesign.gamecollection.earthtrivia.backend.info.City;

/**
 *
 * https://en.wikipedia.org/wiki/Latitude_and_longitude_of_cities,_A-H
 * http://www.infoplease.com/ipa/A0001769.html
 * https://github.com/deremer/Cities/tree/master/countries
 *
 */
public class CityDistance implements Generator {
    
    CitySelector cities = null;

    public CityDistance(Difficulty difficulty, CitiesContinents cities, Random rnd) {
        this.cities = new CitySelector(difficulty,rnd,cities);
    }

    @Override
    public Question generate() {

        Question result = null;
        while (result == null) {
            result = tryToGenerate();
        }

        return result;
    }

    private City nextUniqueCity(Set<City> cityUniqe) {
    	City nextCity = cities.next();
    	while (cityUniqe.contains(nextCity)) {
    		nextCity = cities.next();
    	}
    	cityUniqe.add(nextCity);
    	return nextCity;
    }
    
    private Question tryToGenerate(){
    	
        Set<CityDistancePair> cityPairSet = new HashSet<CityDistancePair>();
        while (cityPairSet.size() < 4) {
        	
        	Set<City> cityUnique = new HashSet<City>();
        	cityPairSet.add(new CityDistancePair(nextUniqueCity(cityUnique),nextUniqueCity(cityUnique)));
        }

        List<CityDistancePair> cityPairsByDistance = criteriaMet(cityPairSet);
        if (cityPairsByDistance == null) {
            return null;
        } else {

            Question result = new Question();

            result.setQuestion("Which of these cities are closest?");
            result.addAnswer(cityPairsByDistance.get(0).getCity1().getCity() + " to " + cityPairsByDistance.get(0).getCity2().getCity());
            result.addAnswer(cityPairsByDistance.get(1).getCity1().getCity() + " to " + cityPairsByDistance.get(1).getCity2().getCity());
            result.addAnswer(cityPairsByDistance.get(2).getCity1().getCity() + " to " + cityPairsByDistance.get(2).getCity2().getCity());
            // we just skip possible answer nr 4 (index position 3) as it is the biggest distance and so an unrealistic answer possibly

            return result;
        }
    }

    /*
     * will return null in case the question is too easy or too hard
     */
    private @Nullable List<CityDistancePair> criteriaMet (Set<CityDistancePair> cityPairSet) {

        List<CityDistancePair> cityPairsByDistance= new ArrayList<CityDistancePair>(4);
        cityPairsByDistance.addAll(cityPairSet);

        Collections.sort(cityPairsByDistance, new CityDistanceComparator());

        int diff = cityPairsByDistance.get(1).getDistanceInKilometers() - cityPairsByDistance.get(0).getDistanceInKilometers();
        
        // Difference of distances not too small, and not too big
        if (diff  > 250 && diff < 1500) {
            return cityPairsByDistance;
        } else {
            return null;
        }
    }

}
