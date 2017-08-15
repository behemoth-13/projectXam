package by.afanasyeu.avtoxam.dao.entities;

/**
 * Используется для передачи на клиент.
 * @author Afanasyeu Alexei
 */
public class Country {
    private Integer id;
    private String country;

    public Country() {
    }

    public Country(Integer id, String country) {
        this.id = id;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country1 = (Country) o;

        if (id != null ? !id.equals(country1.id) : country1.id != null) return false;
        return country != null ? country.equals(country1.country) : country1.country == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}
