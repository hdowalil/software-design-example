package info.fivecdesign.gamecollection.earthtrivia.backend.generators;

import java.util.Random;

import info.fivecdesign.gamecollection.earthtrivia.backend.info.Countries;
import info.fivecdesign.gamecollection.earthtrivia.backend.info.Country;

/**
 * Created by Herbert on 27.07.2015.
 */
public class Nachbarland implements Generator {

   CountrySelector selector;

    public Nachbarland(Difficulty difficulty, Countries countries, Random rnd) {
        selector = new CountrySelector(difficulty,rnd,countries,false);
    }

    private Question tryToGenerate() {

        Country firstCountry = null;
        do {
            firstCountry = selector.next();
        } while (firstCountry.getBorders() == null || firstCountry.getBorders().length == 0);

        Question result = new Question();
        result.setQuestion("Welches dieser Länder ist ein Nachbarland von " + firstCountry.getName());

        int selectedCountry = selector.nextInt(firstCountry.getBorders().length);
        Country neighbour = selector.getByAlpha3Code(firstCountry.getBorders()[selectedCountry]);
        result.addAnswer(neighbour.getName());

        int maxCtr = 100;
        do {
            maxCtr--;
            selectedCountry = selector.nextInt(firstCountry.getBorders().length);
            Country anotherNeighbour = selector.getByAlpha3Code(firstCountry.getBorders()[selectedCountry]);
            if (anotherNeighbour.getBorders() != null && anotherNeighbour.getBorders().length > 0) {
                selectedCountry = selector.nextInt(anotherNeighbour.getBorders().length);
                String selectedCountryCode = anotherNeighbour.getBorders()[selectedCountry];
                if (!selectedCountryCode.equalsIgnoreCase(firstCountry.getAlpha3Code()) && !selectedCountryCode.equalsIgnoreCase(neighbour.getAlpha3Code()) && !selector.isNeighbourOf(firstCountry,selectedCountryCode)) {
                    Country anotherNeighbourOfNeighbour = selector.getByAlpha3Code(selectedCountryCode);
                    result.addAnswer(anotherNeighbourOfNeighbour.getName());
                }
            }
        } while (maxCtr > 0 && result.getNumberOfAnswers() < 4);

        if (maxCtr > 0) {
            return result;
        } else {
            return null;
        }
    }

    @Override
    public Question generate() {

        Question result = null;
        do {
            result = tryToGenerate();
        } while (result == null);

        return result;
    }

}
