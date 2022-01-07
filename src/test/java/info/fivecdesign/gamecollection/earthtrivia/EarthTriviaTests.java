package info.fivecdesign.gamecollection.earthtrivia;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import info.fivecdesign.gamecollection.earthtrivia.backend.TriviaGenerator;
import info.fivecdesign.gamecollection.earthtrivia.backend.generators.CityDistancePair;
import info.fivecdesign.gamecollection.earthtrivia.backend.generators.Questions;
import info.fivecdesign.gamecollection.earthtrivia.backend.info.TriviaResources;

class EarthTriviaTests {

	@Test
	void testDistance() {
		
        int adelaideToAliceSprings = CityDistancePair.calculateDistance(34.55,138.53,23.42,133.53);
        Assertions.assertEquals(1329, adelaideToAliceSprings, "Adelaide to Alice should be 1329!");
        
        int viennaToLisbon = CityDistancePair.calculateDistance(48.12,16.22,38.44,-9.08);
        Assertions.assertEquals(2299, viennaToLisbon, "Vienna to Lisbon should be 2299!");
	}
	
	@Test
	void testQuestions() throws IOException, ParseException {

		TriviaResources resources = new TriviaResources();

		ClassLoader loader = getClass().getClassLoader();
		
		resources.init(TriviaResources.readResource(loader, "restcountries.json"), TriviaResources.readResource(loader, "cities_africa.json"), TriviaResources.readResource(loader, "cities_asia.json"),
				TriviaResources.readResource(loader, "cities_europe.json"), TriviaResources.readResource(loader, "cities_latam.json"), TriviaResources.readResource(loader, "cities_northam.json"),
				TriviaResources.readResource(loader, "cities_oceania.json"));

		TriviaGenerator generator = new TriviaGenerator(resources);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date datum = sdf.parse("20121209");
		Gson gson = new Gson();

		for (int i = 0; i < 31; i++) {
			Questions q = generator.generateQuestionsFor(datum);
			Assertions.assertTrue(q.getEasy() != null && q.getEasy().size() > 0, "There should be Easy questions");
			Assertions.assertTrue(q.getMedium()!= null && q.getMedium().size() > 0, "There should be Medium questions");
			Assertions.assertTrue(q.getHard() != null && q.getHard().size() > 0, "There should be Hard questions");
			
			System.out.println(gson.toJson(q));
			
			// forward to next day
			datum = new Date(datum.getTime() + 86400000L);
		}
	}

}
