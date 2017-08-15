package by.afanasyeu.avtoxam.service.impl;

import by.afanasyeu.avtoxam.dao.entities.DTO.RegionDTO;
import by.afanasyeu.avtoxam.dao.mappers.RegionMapper;
import by.afanasyeu.avtoxam.service.RegionService;
import by.afanasyeu.avtoxam.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Afanasyeu Alexei
 */

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionMapper regionMapper;

    @Autowired
    public RegionServiceImpl(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<RegionDTO> getRegionsByCountryId(Integer countryId) throws ServiceException {
        List<RegionDTO> list = regionMapper.getRegionsByCountryId(countryId);
        if (list.size() != 0) {
            return list;
        } else {
            throw new ServiceException("Regions not founded by countryId. CountryId: " + countryId + ".");
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Boolean isExistRegion(Integer regionId) {
        return regionMapper.isExistRegion(regionId);
    }
}