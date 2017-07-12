package by.afanasyeu.avtoxam.service.impl;

import by.afanasyeu.avtoxam.dao.entities.Country;
import by.afanasyeu.avtoxam.dao.mappers.CountryMapper;
import by.afanasyeu.avtoxam.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Afanasyeu Alexei on 12.07.2017.
 */

@Service
public class CountryServiceImpl implements CountryService{

    @Autowired
    private CountryMapper countryMapper;

    @Override
    public List<Country> getCountries() {
        return countryMapper.getCountries();
    }
}
