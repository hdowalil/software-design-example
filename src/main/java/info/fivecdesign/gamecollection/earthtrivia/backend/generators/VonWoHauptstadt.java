package info.fivecdesign.gamecollection.earthtrivia.backend.generators;

import java.util.Random;

import info.fivecdesign.gamecollection.earthtrivia.backend.info.Countries;
import info.fivecdesign.gamecollection.earthtrivia.backend.info.Country;

/**
 * Created by Herbert on 27.07.2015.
 */
public class VonWoHauptstadt implements Generator {

    CountrySelector selector = null;

    public VonWoHauptstadt(Difficulty difficulty, Countries countries, Random rnd) {
        selector = new CountrySelector(difficulty,rnd,countries);
    }

    @Override
    public Question generate() {

        Country country = selector.next();

        Question result = new Question();

        result.setQuestion("Von welchem Land ist " + country.getCapital() + " die Hauptstadt?");
        result.addAnswer(country.getName());

        do {
            Country otherCountry = selector.next();
            if (otherCountry.getRegion().equals(country.getRegion())) {
                result.addAnswer(otherCountry.getName());
            }
        } while (result.getNumberOfAnswers() < 4);

        return result;
    }
}
