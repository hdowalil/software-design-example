package info.fivecdesign.gamecollection.earthtrivia.backend;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import javax.annotation.Nonnull;

import info.fivecdesign.gamecollection.earthtrivia.backend.generators.DidntMakeItException;
import info.fivecdesign.gamecollection.earthtrivia.backend.generators.Generator;
import info.fivecdesign.gamecollection.earthtrivia.backend.generators.GeneratorCapital;
import info.fivecdesign.gamecollection.earthtrivia.backend.generators.GeneratorCityDistance;
import info.fivecdesign.gamecollection.earthtrivia.backend.generators.GeneratorCityNorth;
import info.fivecdesign.gamecollection.earthtrivia.backend.generators.GeneratorNeighboringCountry;
import info.fivecdesign.gamecollection.earthtrivia.backend.generators.Question;
import info.fivecdesign.gamecollection.earthtrivia.backend.generators.Questions;
import info.fivecdesign.gamecollection.earthtrivia.backend.info.Difficulty;
import info.fivecdesign.gamecollection.earthtrivia.backend.info.TriviaResources;

public class TriviaGenerator {

    TriviaResources resources = null;

    public TriviaGenerator(TriviaResources resources) {
        this.resources = resources;
    }

    /**
     * questions are generated with the same random-seed for the same day.
     * <br>
     * so when running the game the same day you should get the same set of questions
     * <br>
     * running the game another day, you will get a new set of questions
     * 
     * @param datum
     * @return
     */
    public Questions generateQuestionsFor(Date datum) {
        Questions result = new Questions();
        result.setDateFor(datum);
        result.setEasy(generateQuestionsFor(Difficulty.EASY, datum));
        result.setMedium(generateQuestionsFor(Difficulty.MEDIUM,datum));
        result.setHard(generateQuestionsFor(Difficulty.HARD,datum));
        return result;
    }

    public List<Question> generateQuestionsFor(@Nonnull Difficulty diff, @Nonnull Date date) {
    	
    	Objects.requireNonNull(diff);
    	Objects.requireNonNull(date);
    	
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String rndInput = sdf.format(date);
        if (Difficulty.HARD.equals(diff)) {
            rndInput = rndInput+"3";
        } else if (Difficulty.MEDIUM.equals(diff)) {
            rndInput = rndInput+"2";
        } else {
            rndInput = rndInput+"1";
        }
        
        // use a random seed specific for the current date
        Random rnd = new Random(Integer.valueOf(rndInput));
        
        List<Question> result = new ArrayList<Question>(24);
        while (result.size() < 24) {
            Question q = null;
            while (q == null) {
                try {
                    q = generateQuestion(diff, rnd);
                } catch (DidntMakeItException dmie) {
                    // no probe, do nothing, continue
                }
            }
            result.add(q);
        }
        
        assert result != null && result.size() > 0;
        
        return result;
    }

    public Question generateQuestion(Difficulty diff, Random rnd) {
        int questionType = rnd.nextInt(4);
        Generator questionGenerator = null;
        switch (questionType) {
            case 0: questionGenerator = new GeneratorCapital(diff, resources.getCountries(),rnd); break;
            case 1: questionGenerator = new GeneratorNeighboringCountry(diff, resources.getCountries(),rnd); break;
            case 2: questionGenerator = new GeneratorCityNorth(diff, resources.getCitiesContinents(), resources.getCountries(),rnd); break;
            case 3: questionGenerator = new GeneratorCityDistance(diff, resources.getCitiesContinents(), rnd); break;
        }
        return questionGenerator.generate();
    }

 }
