package by.afanasyeu.avtoxam.dao.mappers;

import by.afanasyeu.avtoxam.dao.entities.DTO.RegionDTO;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Afanasyeu Alexei on 04.07.2017.
 */
@Repository
public interface RegionMapper {
    @Select("SELECT id AS id, region AS region FROM region WHERE country_id = #{countryId}")
    @Results(value={
            @Result(property="countryId", column ="country_id" )
    })
    List<RegionDTO> getRegionsByCountryId(Integer countryId);

    @Select("SELECT IF(COUNT(*) > 0,'true','false') FROM region WHERE id = #{regionId}")
    Boolean isExistRegion(Integer regionId);
}