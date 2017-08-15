package by.afanasyeu.avtoxam.controller.rest;

import by.afanasyeu.avtoxam.dao.entities.Country;
import by.afanasyeu.avtoxam.service.CountryService;
import by.afanasyeu.avtoxam.service.impl.CountryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller для операций с {@link Country}
 * @author Afanasyeu Alexei
 */

@RestController
@RequestMapping("/rest/country")
public class CountryRestController {

    /**
     *
     */
    private final CountryService countryService;

    @Autowired
    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PreAuthorize("permitAll")
    @GetMapping(value = "")
    public ResponseEntity<List<Country>> getCountries() {
        List<Country> countries;
        try {
            countries = countryService.getCountries();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }
}