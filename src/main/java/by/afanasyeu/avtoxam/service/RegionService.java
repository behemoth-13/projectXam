package by.afanasyeu.avtoxam.service;

import by.afanasyeu.avtoxam.dao.entities.DTO.RegionDTO;
import by.afanasyeu.avtoxam.service.exception.ServiceException;

import java.util.List;

/**
 * @author Afanasyeu Alexei
 */
public interface RegionService {
    List<RegionDTO> getRegionsByCountryId(Integer countryId) throws ServiceException;
    Boolean isExistRegion(Integer regionId);
}
