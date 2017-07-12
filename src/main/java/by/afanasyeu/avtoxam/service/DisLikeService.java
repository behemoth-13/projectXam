package by.afanasyeu.avtoxam.service;

import by.afanasyeu.avtoxam.dao.entities.DisLike;

/**
 * Created by Afanasyeu Alexei on 12.07.2017.
 */
public interface DisLikeService {
    void insertDisLike(DisLike disLike);
    void deleteFromMessage(DisLike disLike);
    void deleteFromComment(DisLike disLike);
}
