package info.fivecdesign.gamecollection.earthtrivia.backend.info;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import info.fivecdesign.gamecollection.earthtrivia.backend.generators.DidntMakeItException;
import info.fivecdesign.gamecollection.earthtrivia.backend.generators.Difficulty;
import info.fivecdesign.gamecollection.earthtrivia.backend.generators.Generator;
import info.fivecdesign.gamecollection.earthtrivia.backend.generators.Hauptstadt;
import info.fivecdesign.gamecollection.earthtrivia.backend.generators.Nachbarland;
import info.fivecdesign.gamecollection.earthtrivia.backend.generators.Question;
import info.fivecdesign.gamecollection.earthtrivia.backend.generators.Questions;
import info.fivecdesign.gamecollection.earthtrivia.backend.generators.StadtNoerdlicher;
import info.fivecdesign.gamecollection.earthtrivia.backend.generators.VonWoHauptstadt;

/**
 * Created by Herbert on 27.07.2015.
 */
public class TriviaGenerator {

    TriviaResources resources = null;

    public TriviaGenerator(TriviaResources resources) {
        this.resources = resources;
    }

    public Questions generateQuestionsFor(Date datum) {
        Questions result = new Questions();
        result.setDateFor(datum);
        result.setEasy(generateQuestionsFor(Difficulty.EASY, datum));
        result.setMedium(generateQuestionsFor(Difficulty.MEDIUM,datum));
        result.setHard(generateQuestionsFor(Difficulty.HARD,datum));
        return result;
    }

    public List<Question> generateQuestionsFor(Difficulty diff, Date datum) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String rndInput = sdf.format(datum);
        if (Difficulty.HARD.equals(diff)) {
            rndInput = rndInput+"3";
        } else if (Difficulty.MEDIUM.equals(diff)) {
            rndInput = rndInput+"2";
        } else {
            rndInput = rndInput+"1";
        }
        Random rnd = new Random(Integer.valueOf(rndInput));
        List<Question> result = new ArrayList<Question>(24);
        while (result.size() < 24) {
            Question q = null;
            while (q == null) {
                try {
                    q = generateQuestion(diff, rnd);
                } catch (DidntMakeItException dmie) {
                    // do nothing, continue
                }
            }
            result.add(q);
        }
        return result;
    }

    public Question generateQuestion(Difficulty diff, Random rnd) {
        int questionType = rnd.nextInt(4);
        Generator questionGenerator = null;
        switch (questionType) {
            case 0: questionGenerator = new Hauptstadt(diff, resources.getCountries(),rnd); break;
            case 1: questionGenerator = new VonWoHauptstadt(diff, resources.getCountries(),rnd); break;
            case 2: questionGenerator = new Nachbarland(diff, resources.getCountries(),rnd); break;
            case 3: questionGenerator = new StadtNoerdlicher(diff, resources.getCitiesContinents(), resources.getCountries(),rnd); break;
        }
        return questionGenerator.generate();
    }

 }
