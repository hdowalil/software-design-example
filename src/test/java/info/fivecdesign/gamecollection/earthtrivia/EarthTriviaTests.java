package info.fivecdesign.gamecollection.earthtrivia;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import info.fivecdesign.gamecollection.earthtrivia.backend.generators.Questions;
import info.fivecdesign.gamecollection.earthtrivia.backend.info.TriviaGenerator;
import info.fivecdesign.gamecollection.earthtrivia.backend.info.TriviaResources;

class EarthTriviaTests {

	@Test
	void test() throws IOException, ParseException {

		TriviaResources resources = new TriviaResources();

		ClassLoader loader = getClass().getClassLoader();
		
		resources.init(TriviaResources.readResource(loader, "restcountries.json"), TriviaResources.readResource(loader, "cities_africa.json"), TriviaResources.readResource(loader, "cities_asia.json"),
				TriviaResources.readResource(loader, "cities_europe.json"), TriviaResources.readResource(loader, "cities_latam.json"), TriviaResources.readResource(loader, "cities_northam.json"),
				TriviaResources.readResource(loader, "cities_oceania.json"));

		TriviaGenerator generator = new TriviaGenerator(resources);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date datum = sdf.parse("20120801");
		Gson gson = new Gson();

		for (int i = 0; i < 31; i++) {
			Questions q = generator.generateQuestionsFor(datum);
			System.out.println(gson.toJson(q));
			datum = new Date(datum.getTime() + 86400000L);
		}
	}

	public static void main(String[] args) throws IOException, ParseException {
		
		EarthTriviaTests tests = new EarthTriviaTests();
		tests.test();
		
	}
	
//	static String readFile(String fileName) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(
//				new FileInputStream("C:\\wsAndroid\\WorldTrivia\\app\\src\\main\\res\\raw\\" + fileName), "UTF8"));
//		try {
//			StringBuilder sb = new StringBuilder();
//			String line = br.readLine();
//
//			while (line != null) {
//				sb.append(line);
//				sb.append("\n");
//				line = br.readLine();
//			}
//			return sb.toString();
//		} finally {
//			br.close();
//		}
//	}

}
