package info.fivecdesign.gamecollection.earthtrivia.backend.generators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import info.fivecdesign.gamecollection.earthtrivia.backend.info.CitiesContinents;
import info.fivecdesign.gamecollection.earthtrivia.backend.info.City;
import info.fivecdesign.gamecollection.earthtrivia.backend.info.Countries;

/**
 * Created by Herbert on 27.07.2015.
 */
public class StadtNoerdlicher implements Generator {

    CitySelector cities = null;
    CountrySelector countries = null;

    public StadtNoerdlicher(Difficulty difficulty, CitiesContinents cities, Countries countries, Random rnd) {
        this.countries = new CountrySelector(difficulty,rnd,countries);
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

    private Question tryToGenerate(){

        Set<City> citySet = new HashSet<City>();
        while (citySet.size() < 4) {
            citySet.add(cities.next());
        }

        List<City> cityByLats = kriterienErfuellt(citySet);
        if (cityByLats == null) {
            return null;
        } else {

            Question result = new Question();

            result.setQuestion("Welche dieser Städte liegt am nördlichsten?");
            result.addAnswer(cityByLats.get(3).getCityDe());
            result.addAnswer(cityByLats.get(2).getCityDe());
            result.addAnswer(cityByLats.get(1).getCityDe());
            result.addAnswer(cityByLats.get(0).getCityDe());

            return result;
        }
    }

    private List<City> kriterienErfuellt (Set<City> citySet) {

        List<City> cityBylats = new ArrayList<City>(4);
        cityBylats.addAll(citySet);

        Collections.sort(cityBylats, new CityLatComparator());

        double diff = cityBylats.get(3).getLatCoordinate() - cityBylats.get(2).getLatCoordinate();
        if (diff  > 2d && diff < 12d) {
            return cityBylats;
        } else {
            return null;
        }
    }
}