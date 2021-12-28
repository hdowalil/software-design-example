package info.fivecdesign.gamecollection.earthtrivia.backend.generators;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;

import info.fivecdesign.gamecollection.earthtrivia.backend.info.TriviaGenerator;
import info.fivecdesign.gamecollection.earthtrivia.backend.info.TriviaResources;

/**
 * Created by Herbert on 24.08.2015.
 */
public class QuestionsTest {

    public static void main(String[] args) throws IOException,ParseException {

        TriviaResources resources = new TriviaResources();

        resources.init(readFile("restcountries.json"),
                readFile("cities_africa.json"),
                readFile("cities_asia.json"),
                readFile("cities_europe.json"),
                readFile("cities_latam.json"),
                readFile("cities_northam.json"),
                readFile("cities_oceania.json")
                );

        TriviaGenerator generator = new TriviaGenerator(resources);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date datum = sdf.parse("20120801");
        Gson gson = new Gson();

        for (int i=0; i<31; i++) {
            Questions q = generator.generateQuestionsFor(datum);
            System.out.println(gson.toJson(q));
            datum = new Date(datum.getTime() + 86400000L);
        }
    }

    static String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("C:\\wsAndroid\\WorldTrivia\\app\\src\\main\\res\\raw\\"+fileName), "UTF8"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }}
