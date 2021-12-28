package info.fivecdesign.gamecollection.earthtrivia.backend.generators;

import java.util.Random;

import info.fivecdesign.gamecollection.earthtrivia.backend.info.Countries;
import info.fivecdesign.gamecollection.earthtrivia.backend.info.Country;

/**
 * Created by Herbert on 27.07.2015.
 */
public class Hauptstadt implements Generator {

    CountrySelector selector = null;

    public Hauptstadt(Difficulty difficulty, Countries countries, Random rnd) {
        selector = new CountrySelector(difficulty,rnd,countries);
    }

    @Override
    public Question generate() {

        Country country = selector.next();

        Question result = new Question();

        result.setQuestion("Wie lautet die Hauptstadt von " + country.getName());
        result.addAnswer(country.getCapital());

        int i=0;
        do {
            Country otherCountry = selector.next();
            if (otherCountry.getRegion().equals(country.getRegion())) {
                result.addAnswer(otherCountry.getCapital());
            }
            i++;
        } while (result.getNumberOfAnswers() < 4 && i<100);

        if (result.getNumberOfAnswers() == 4) {
            return result;
        } else {
            return null;
        }
    }
}
