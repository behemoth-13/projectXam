package by.afanasyeu.avtoxam.service.impl;

import by.afanasyeu.avtoxam.dao.entities.Country;
import by.afanasyeu.avtoxam.dao.mappers.CountryMapper;
import by.afanasyeu.avtoxam.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Afanasyeu Alexei
 */

@Service
public class CountryServiceImpl implements CountryService{

    private final CountryMapper countryMapper;

    @Autowired
    public CountryServiceImpl(CountryMapper countryMapper) {
        this.countryMapper = countryMapper;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Country> getCountries() {
        return countryMapper.getCountries();
    }
}
