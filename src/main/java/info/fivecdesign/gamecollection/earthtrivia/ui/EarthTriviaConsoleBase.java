package info.fivecdesign.gamecollection.earthtrivia.ui;

import java.io.PrintStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

import info.fivecdesign.gamecollection.earthtrivia.backend.generators.Difficulty;
import info.fivecdesign.gamecollection.earthtrivia.backend.generators.Question;
import info.fivecdesign.gamecollection.earthtrivia.backend.generators.Questions;
import info.fivecdesign.gamecollection.earthtrivia.backend.info.TriviaGenerator;
import info.fivecdesign.gamecollection.earthtrivia.backend.info.TriviaResources;
import info.fivecdesign.gamecollection.portal.GameConsole;
import info.fivecdesign.gamecollection.portal.Portal;

public abstract class EarthTriviaConsoleBase implements GameConsole {

	private Difficulty difficulty = null;
	Random rnd = new Random(); 

	protected EarthTriviaConsoleBase(Difficulty difficulty) {
		super();
		this.difficulty = difficulty;
	}

	@Override
	public String getName() {
		return "Earth Trivia";
	}

	@Override
	public int run(PrintStream out, Scanner in, Portal portal) {

		TriviaResources resources = new TriviaResources();

		ClassLoader loader = getClass().getClassLoader();

		resources.init(TriviaResources.readResource(loader, "restcountries.json"),
				TriviaResources.readResource(loader, "cities_africa.json"),
				TriviaResources.readResource(loader, "cities_asia.json"),
				TriviaResources.readResource(loader, "cities_europe.json"),
				TriviaResources.readResource(loader, "cities_latam.json"),
				TriviaResources.readResource(loader, "cities_northam.json"),
				TriviaResources.readResource(loader, "cities_oceania.json"));

		TriviaGenerator generator = new TriviaGenerator(resources);
		Questions q = generator.generateQuestionsFor(new Date());

		int score = 0;
		switch (difficulty) {
		case EASY:
			score = loopThrougQuestions(out, in, q.getEasy());
			break;
		case MEDIUM:
			score = loopThrougQuestions(out, in, q.getMedium());
			break;
		case HARD:
			score = loopThrougQuestions(out, in, q.getHard());
			break;
		}

		return score;
	}

	private int loopThrougQuestions(PrintStream out, Scanner in, List<Question> questions) {

		int alreadyAnswered = 0;
		boolean answered = true;
		while (answered == true && alreadyAnswered < questions.size()) {

			Question q = questions.get(alreadyAnswered);
			out.println(q.getQuestion());
			
			// index 0 is always the correct answer, we need to shuffle
			Map<Character, Integer> shuffleResult = shuffleAnswers(q.getNumberOfAnswers());
			for (Entry<Character, Integer> answer : shuffleResult.entrySet()) {
				System.out.println (answer.getKey() + ": " + q.getAnswer(answer.getValue()));
			}
			
			// let´s query user for a valid possible answer
			String entry = "";
			do {
				entry = in.next();
			} while (entry == null || entry.length() != 1 || !shuffleResult.keySet().contains(entry.charAt(0)));

			// user answered with a valid letter (A, B, C etc), only if this is index = 0 it was a correct answer
			if (shuffleResult.get(entry.charAt(0)) == 0) {
				out.println("Correct!");
				alreadyAnswered++;
			} else {
				out.println("Sorry, but that was wrong!");
				answered = false;
			}
			
		}

		return alreadyAnswered;
	}

	private Map<Character, Integer> shuffleAnswers (int numberOfAnswers) {
		
		String baseChars = "ABCDEFGHIJKLMNOPQRSTUVQXYZ";
		
		Map<Character, Integer> result = new HashMap<Character, Integer>();
		
		for (int i=0; i<numberOfAnswers; i++) {
			
			char nextPossibleChar = ' ';
			do {
				nextPossibleChar = baseChars.charAt(rnd.nextInt(numberOfAnswers));
			} while (result.containsKey(nextPossibleChar));
				
			result.put(nextPossibleChar, i);
			
		}
		
		return result;
	}
	
}
