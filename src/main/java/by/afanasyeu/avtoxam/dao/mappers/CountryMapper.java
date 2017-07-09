package by.afanasyeu.avtoxam.dao.mappers;

import by.afanasyeu.avtoxam.dao.entities.Country;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Afanasyeu Alexei on 09.07.2017.
 */
@Repository
public interface CountryMapper {
    @Select("SELECT id AS id, country AS country FROM country")
    @Results()
    List<Country> getCountries();
}
