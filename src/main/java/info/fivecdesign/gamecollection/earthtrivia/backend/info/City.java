package info.fivecdesign.gamecollection.earthtrivia.backend.info;

public class City {

    String city;
    String cityDe;
    Difficulty diff;
    String ctr;
    String ll;
    double latCoordinate;
    double longCoordinate;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityDe() {
        return cityDe;
    }

    public void setCityDe(String cityDe) {
        this.cityDe = cityDe;
    }

    public Difficulty getDiff() {
        return diff;
    }

    public void setDiff(Difficulty diff) {
        this.diff = diff;
    }

    public String getCtr() {
        return ctr;
    }

    public void setCtr(String ctr) {
        this.ctr = ctr;
    }

    public String getLl() {
        return ll;
    }

    public void setLl(String ll) {
        this.ll = ll;
    }

    public double getLatCoordinate() {
        return latCoordinate;
    }

    public void setLatCoordinate(double latCoordinate) {
        this.latCoordinate = latCoordinate;
    }

    public double getLongCoordinate() {
        return longCoordinate;
    }

    public void setLongCoordinate(double longCoordinate) {
        this.longCoordinate = longCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city1 = (City) o;

        if (Double.compare(city1.latCoordinate, latCoordinate) != 0) return false;
        if (Double.compare(city1.longCoordinate, longCoordinate) != 0) return false;
        if (city != null ? !city.equals(city1.city) : city1.city != null) return false;
        if (cityDe != null ? !cityDe.equals(city1.cityDe) : city1.cityDe != null) return false;
        if (diff != city1.diff) return false;
        return !(ctr != null ? !ctr.equals(city1.ctr) : city1.ctr != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = city != null ? city.hashCode() : 0;
        result = 31 * result + (cityDe != null ? cityDe.hashCode() : 0);
        result = 31 * result + (diff != null ? diff.hashCode() : 0);
        result = 31 * result + (ctr != null ? ctr.hashCode() : 0);
        temp = Double.doubleToLongBits(latCoordinate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longCoordinate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

	@Override
	public String toString() {
		return "City [city=" + city + ", cityDe=" + cityDe + ", diff=" + diff + ", ctr=" + ctr + ", ll=" + ll
				+ ", latCoordinate=" + latCoordinate + ", longCoordinate=" + longCoordinate + "]";
	}
    
}
