package info.fivecdesign.gamecollection.earthtrivia.backend.info;

public class CountryTranslation {

    String de;
    String es;
    String fr;
    String ja;
    String it;

    public CountryTranslation() {
        super();
    }

    public CountryTranslation(String de, String es, String fr, String it, String ja) {
        super();
        this.de = de;
        this.es = es;
        this.fr = fr;
        this.it = it;
        this.ja = ja;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getEs() {
        return es;
    }

    public void setEs(String es) {
        this.es = es;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getJa() {
        return ja;
    }

    public void setJa(String ja) {
        this.ja = ja;
    }

    public String getIt() {
        return it;
    }

    public void setIt(String it) {
        this.it = it;
    }
}
