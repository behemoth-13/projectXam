package by.afanasyeu.avtoxam.service;

import by.afanasyeu.avtoxam.dao.entities.DTO.RegionDTO;
import by.afanasyeu.avtoxam.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Afanasyeu Alexei on 10.07.2017.
 */
public interface RegionService {
    List<RegionDTO> getRegionsByCountryId(Integer countryId) throws ServiceException;
}
