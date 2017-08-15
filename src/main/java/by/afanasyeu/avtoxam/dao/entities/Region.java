package by.afanasyeu.avtoxam.dao.entities;

/**
 * @see by.afanasyeu.avtoxam.dao.entities.DTO.RegionDTO
 * @author Afanasyeu Alexei
 */
public class Region {
    private Integer id;
    private Integer countryId;
    private String region;

    public Region() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCountryId() {
        return countryId;
    }
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", countryId=" + countryId +
                ", region='" + region + '\'' +
                '}';
    }
}
