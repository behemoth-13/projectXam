package by.afanasyeu.avtoxam.controller.rest;

import by.afanasyeu.avtoxam.dao.entities.DTO.RegionDTO;
import by.afanasyeu.avtoxam.service.RegionService;
import by.afanasyeu.avtoxam.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Afanasyeu Alexei on 28.07.2017.
 */

@RestController
@RequestMapping("/rest/region")
public class RegionRestController {

    @Autowired
    private RegionService regionService;

    @Secured("ROLE_USER")
    @GetMapping(value = "/{countryId}")
    public ResponseEntity<List<RegionDTO>> getRegionsByCountryId(@PathVariable Integer countryId) {
        List<RegionDTO> regions;
        try {
            regions = regionService.getRegionsByCountryId(countryId);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(regions, HttpStatus.OK);
    }
}
