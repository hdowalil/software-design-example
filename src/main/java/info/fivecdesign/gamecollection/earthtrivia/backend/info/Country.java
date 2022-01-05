package info.fivecdesign.gamecollection.earthtrivia.backend.info;

import java.util.Arrays;

public class Country {

    String name;
    String capital;
    String[] altSpellings;
    String relevance;
    String region;
    String subregion;
    CountryTranslation translations;
    Double population;
    double[] latlng;
    String demonym;
    Double area;
    Double gini;
    String[] timezones;
    String[] borders;
    String nativeName;
    String[] callingCodes;
    String[] topLevelDomain;
    String alpha2Code;
    String alpha3Code;
    String[] currencies;
    String[] languages;

    public String getRelevance() {
        return relevance;
    }

    public void setRelevance(String relevance) {
        this.relevance = relevance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String[] getAltSpellings() {
        return altSpellings;
    }

    public void setAltSpellings(String[] altSpellings) {
        this.altSpellings = altSpellings;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public CountryTranslation getTranslations() {
        return translations;
    }

    public void setTranslations(CountryTranslation translations) {
        this.translations = translations;
    }

    public Double getPopulation() {
        return population;
    }

    public void setPopulation(Double population) {
        this.population = population;
    }

    public double[] getLatlng() {
        return latlng;
    }

    public void setLatlng(double[] latlng) {
        this.latlng = latlng;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getGini() {
        return gini;
    }

    public void setGini(Double gini) {
        this.gini = gini;
    }

    public String[] getTimezones() {
        return timezones;
    }

    public void setTimezones(String[] timezones) {
        this.timezones = timezones;
    }

    public String[] getBorders() {
        return borders;
    }

    public void setBorders(String[] borders) {
        this.borders = borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String[] getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(String[] callingCodes) {
        this.callingCodes = callingCodes;
    }

    public String[] getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(String[] topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String[] getCurrencies() {
        return currencies;
    }

    public void setCurrencies(String[] currencies) {
        this.currencies = currencies;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", altSpellings=" + Arrays.toString(altSpellings) +
                ", relevance='" + relevance + '\'' +
                ", region='" + region + '\'' +
                ", subregion='" + subregion + '\'' +
                ", translations=" + translations +
                ", population=" + population +
                ", latlng=" + Arrays.toString(latlng) +
                ", demonym='" + demonym + '\'' +
                ", area=" + area +
                ", gini=" + gini +
                ", timezones=" + Arrays.toString(timezones) +
                ", borders=" + Arrays.toString(borders) +
                ", nativeName='" + nativeName + '\'' +
                ", callingCodes=" + Arrays.toString(callingCodes) +
                ", topLevelDomain=" + Arrays.toString(topLevelDomain) +
                ", alpha2Code='" + alpha2Code + '\'' +
                ", alpha3Code='" + alpha3Code + '\'' +
                ", currencies=" + Arrays.toString(currencies) +
                ", languages=" + Arrays.toString(languages) +
                '}';
    }
}
