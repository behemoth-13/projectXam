package by.afanasyeu.avtoxam.service;

import by.afanasyeu.avtoxam.dao.entities.Favorite;

/**
 * Created by Afanasyeu Alexei on 12.07.2017.
 */
public interface FavoriteService {
    void insertFavorite(Favorite favorite);
    void delete(Favorite favorite);
}
