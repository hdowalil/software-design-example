package info.fivecdesign.gamecollection.earthtrivia.backend.generators;

import java.util.Random;

import info.fivecdesign.gamecollection.earthtrivia.backend.info.Cities;
import info.fivecdesign.gamecollection.earthtrivia.backend.info.CitiesContinents;
import info.fivecdesign.gamecollection.earthtrivia.backend.info.City;
import info.fivecdesign.gamecollection.earthtrivia.backend.info.Difficulty;

/**
 * 
 * will deliver cities based on the difficulty, call {@link #next()} method to iterate
 *
 */
class CitySelector {

    // easy ... only easy cities from one continent, medium ... medium and easy cities from one continent, hard ... medium and easy from all continents
    private Difficulty difficulty;

    private CitiesContinents cities;
    private Random rnd;

    int firstSelectedContinent = -1;

    public CitySelector(Difficulty difficulty, Random rnd, CitiesContinents cities) {
        this.difficulty = difficulty;
        this.cities = cities;
        this.rnd = rnd;
    }

    private Cities getContinentCities (int index) {
        switch(index) {
            case 0: return cities.getCitiesAfrica();
            case 1: return cities.getCitiesAsia();
            case 2: return cities.getCitiesEurope();
            case 3: return cities.getCitiesLatam();
            case 4: return cities.getCitiesNortham();
            default: return cities.getCitiesOceania();
        }
    }

    public City next() {

        int nextContinent = firstSelectedContinent;
        if (Difficulty.HARD.equals(difficulty) || nextContinent == -1) {
            nextContinent = rnd.nextInt(6);
        }
        if (firstSelectedContinent == -1) {
            firstSelectedContinent = nextContinent;
        }

        Cities cities = getContinentCities(nextContinent);
        City result = null;
        while (result == null) {
            City city = cities.getCities()[rnd.nextInt(cities.getCities().length)];
            if (!Difficulty.EASY.equals(difficulty) || Difficulty.EASY.equals(city.getDiff())) {
                result = city;
            }
        }

        return result;
    }
}
