package by.afanasyeu.avtoxam.dao.mappers;

import by.afanasyeu.avtoxam.dao.entities.Country;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Используется для обращения к БД
 * @author Afanasyeu Alexei
 */
@Repository
public interface CountryMapper {
    @Select("SELECT id AS id, country AS country FROM country")
    @Results()
    List<Country> getCountries();
}
