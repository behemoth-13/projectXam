package by.afanasyeu.avtoxam.service;

import by.afanasyeu.avtoxam.dao.entities.Like;

/**
 * Created by Afanasyeu Alexei on 12.07.2017.
 */
public interface LikeService {
    void insertLike(Like like);
    void deleteFromMessage(Like like);
    void deleteFromComment(Like like);
}
