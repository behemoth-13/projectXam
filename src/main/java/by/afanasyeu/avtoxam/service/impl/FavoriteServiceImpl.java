package by.afanasyeu.avtoxam.service.impl;

import by.afanasyeu.avtoxam.dao.entities.Favorite;
import by.afanasyeu.avtoxam.dao.mappers.FavoriteMapper;
import by.afanasyeu.avtoxam.service.FavoriteService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Afanasyeu Alexei
 */

@Transactional(propagation = Propagation.REQUIRES_NEW)
@Service
public class FavoriteServiceImpl implements FavoriteService{

    private final FavoriteMapper favoriteMapper;

    @Autowired
    public FavoriteServiceImpl(FavoriteMapper favoriteMapper) {
        this.favoriteMapper = favoriteMapper;
    }

    @Override
    public void insertFavorite(Favorite favorite) {
        if (favoriteMapper.isExist(favorite)) {
            favoriteMapper.delete(favorite);
        } else {
            favoriteMapper.insertFavorite(favorite);
        }
    }
}
