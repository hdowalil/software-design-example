package info.fivecdesign.gamecollection.earthtrivia.backend.generators;

import java.util.Random;

import info.fivecdesign.gamecollection.earthtrivia.backend.info.Countries;
import info.fivecdesign.gamecollection.earthtrivia.backend.info.Country;
import info.fivecdesign.gamecollection.earthtrivia.backend.info.Difficulty;

public class GeneratorCapital implements Generator {

    CountrySelector selector = null;

    public GeneratorCapital(Difficulty difficulty, Countries countries, Random rnd) {
        selector = new CountrySelector(difficulty,rnd,countries);
    }

    @Override
    public Question generate() {

        Country country = selector.next();

        Question result = new Question();

        result.setQuestion("Of which country is " + country.getCapital() + " the capital?");
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
