package info.fivecdesign.gamecollection.earthtrivia.backend.info;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;

import com.google.gson.Gson;

/**
 * Created by Herbert on 24.08.2015.
 */
public class TriviaResources {
    Countries countries;
    CitiesContinents citiesContinents = new CitiesContinents();

    public void init() {

    	ClassLoader loader = getClass().getClassLoader();
    	
        init(readResource(loader, "restcountries.json"),
                readResource(loader, "cities_africa.json"),
                readResource(loader, "cities_asia.json"),
                readResource(loader, "cities_europe.json"),
                readResource(loader, "cities_latam.json"),
                readResource(loader, "cities_northam.json"),
                readResource(loader, "cities_oceaniea.json"));


    }

    public void init(String restCountries, String citiesAfrica, String citiesAsia, String citiesEurope, String citiesLatam, String citiesNortham, String citiesOceania) {

        Gson gson = new Gson();
        countries = gson.fromJson(restCountries, Countries.class);

        citiesContinents.setCitiesAfrica(parseCities(citiesAfrica));
        citiesContinents.setCitiesAsia(parseCities(citiesAsia));
        citiesContinents.setCitiesEurope(parseCities(citiesEurope));
        citiesContinents.setCitiesLatam(parseCities(citiesLatam));
        citiesContinents.setCitiesNortham(parseCities(citiesNortham));
        citiesContinents.setCitiesOceania(parseCities(citiesOceania));
    }

    public Cities parseCities (String citiesString) {

        Gson gson = new Gson();
        Cities cities = gson.fromJson(citiesString, Cities.class);

        for (City city : cities.getCities()) {

            // wenn ein beistrich da ist dann ist der deutsche name anders, und der teil nach dem , der deutsche name!
            if (city.getCity().indexOf(",") != -1) {
                String[] names = city.getCity().split("\\,");
                city.setCity(names[0]);
                city.setCityDe(names[1]);
            } else {
                city.setCityDe(city.getCity());
            }

            // ll von string auf 2 doubles
            String[] lls = city.getLl().split("\\,");
            city.setLatCoordinate(Double.parseDouble(lls[0]));
            city.setLongCoordinate(Double.parseDouble(lls[1]));
            city.setLl(null);
        }

        return cities;
    }

    public static String readResource(ClassLoader loader, String id) {
    	
        InputStream resourceReader = loader.getResourceAsStream(id);
        Writer writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                resourceReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return writer.toString();

    }

    public Countries getCountries() {
        return countries;
    }

    public CitiesContinents getCitiesContinents() {
        return citiesContinents;
    }


}
