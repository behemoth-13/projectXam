package by.afanasyeu.avtoxam.service.impl;

import by.afanasyeu.avtoxam.dao.entities.DTO.RegionDTO;
import by.afanasyeu.avtoxam.dao.mappers.RegionMapper;
import by.afanasyeu.avtoxam.service.RegionService;
import by.afanasyeu.avtoxam.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Afanasyeu Alexei on 10.07.2017.
 */
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionMapper regionMapper;

    @Override
    public List<RegionDTO> getRegionsByCountryId(Integer countryId) throws ServiceException {
        List<RegionDTO> list = regionMapper.getRegionsByCountryId(countryId);
        if (list.size() != 0) {
            return list;
        } else {
            throw new ServiceException("Regions not founded by countryId. CountryId: " + countryId + ".");
        }
    }
}