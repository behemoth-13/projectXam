package by.afanasyeu.avtoxam.dao.entities.DTO;

/**
 * Created by Afanasyeu Alexei on 04.07.2017.
 */
public class RegionDTO {
    private Integer id;
    private String region;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }

    public RegionDTO(){}

    public RegionDTO(Integer id, String region) {
        this.id = id;
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegionDTO regionDTO = (RegionDTO) o;

        if (id != null ? !id.equals(regionDTO.id) : regionDTO.id != null) return false;
        return region != null ? region.equals(regionDTO.region) : regionDTO.region == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (region != null ? region.hashCode() : 0);
        return result;
    }
}
