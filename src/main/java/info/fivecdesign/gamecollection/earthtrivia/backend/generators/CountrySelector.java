package info.fivecdesign.gamecollection.earthtrivia.backend.generators;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import info.fivecdesign.gamecollection.earthtrivia.backend.info.Countries;
import info.fivecdesign.gamecollection.earthtrivia.backend.info.Country;

/**
 * 
 * will deliver countires based on the difficulty, call {@link #next()} method to iterate
 *
 */
public class CountrySelector  {

    private Difficulty difficulty;
    private Countries countries;
    private Random rnd;
    private boolean selectSameRegionAfterFirstSelection;

    Set<Integer> alreadySelectedIntegers;
    String selectedRegion = null;

    public CountrySelector(Difficulty difficulty, Random rnd, Countries countries) {
        this(difficulty, rnd, countries, true);
    }

    public CountrySelector(Difficulty difficulty, Random rnd, Countries countries, boolean selectSameRegionAfterFirstSelection) {
        this.difficulty = difficulty;
        this.rnd = rnd;
        this.countries = countries;
        this.selectSameRegionAfterFirstSelection = selectSameRegionAfterFirstSelection;
    }

    public boolean isCountryMatchingDifficulty(Country country, Difficulty diff) {
        if (Difficulty.EASY.equals(diff)) {
            return (5000000.0 < country.getPopulation());
        } else if (Difficulty.MEDIUM.equals(diff)) {
            return (1000000.0 < country.getPopulation());
        } else {
            return true;
        }
    }

    public boolean isNeighbourOf (Country ctr, String a3c) {
        if (ctr.getBorders() == null || ctr.getBorders().length == 0) {
            return false;
        }
        for (String border : ctr.getBorders()) {
            if (border.equalsIgnoreCase(a3c)) {
                return true;
            }
        }
        return false;
    }

    public Country getByAlpha3Code(String a3c) {
        for (Country ctr : countries.getCountries()) {
            if (a3c.equalsIgnoreCase(ctr.getAlpha3Code())) {
                return ctr;
            }
        }
        return null;
    }

    public int nextInt(int n) {
        return rnd.nextInt(n);
    }

    private boolean regionMatch(String region) {
        if (selectSameRegionAfterFirstSelection == false) {
            return true;
        }
        if (selectedRegion == null) {
            return true;
        }
        if ("Oceania".equals(region)) {
            return selectedRegion.equals("Asia");
        } else {
            return selectedRegion.equals(region);
        }
    }

    public Country next() {
    	
        if (alreadySelectedIntegers == null) {
            alreadySelectedIntegers = new HashSet<Integer>(4);
        }

        int nextIndex = -1;
        Country country = null;
        int counter = 1;
        do {
            nextIndex = rnd.nextInt(countries.getCountries().length);
            country = countries.getCountries()[nextIndex];
            counter++;
            if (counter > 100) {
                throw new DidntMakeItException();
            }
        } while (!regionMatch(country.getRegion()) ||
                alreadySelectedIntegers.contains(nextIndex) || !isCountryMatchingDifficulty(country, difficulty));

        if (alreadySelectedIntegers.size() == 0) {
            if (country.getRegion().equalsIgnoreCase("Oceania")) {
                selectedRegion = "Asia";
            } else {
                selectedRegion = country.getRegion();
            }
        }

        alreadySelectedIntegers.add(nextIndex);
        return country;

    }

 }
