package by.afanasyeu.avtoxam.service.impl;

import by.afanasyeu.avtoxam.dao.entities.Like;
import by.afanasyeu.avtoxam.dao.mappers.DisLikeMapper;
import by.afanasyeu.avtoxam.dao.mappers.LikeMapper;
import by.afanasyeu.avtoxam.service.DisLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Afanasyeu Alexei on 12.07.2017.
 */

@Service
public class DisLikeServiceImpl implements DisLikeService{

    @Autowired
    private DisLikeMapper disLikeMapper;
    @Autowired
    private LikeMapper likeMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insertDisLike(Like disLike) {
        if (disLike.getMessageId() != null) {
            insertDisLikeMessage(disLike);
        } else {
            insertDisLikeComment(disLike);
        }
    }

    private void insertDisLikeMessage(Like disLike) {
        if (disLikeMapper.isExistFromMessage(disLike)) {
            disLikeMapper.deleteFromMessage(disLike);
        } else {
            likeMapper.deleteFromMessage(disLike);
            disLikeMapper.insertDisLike(disLike);
        }
    }

    private void insertDisLikeComment(Like disLike) {
        if (disLikeMapper.isExistFromComment(disLike)) {
            disLikeMapper.deleteFromComment(disLike);
        } else {
            likeMapper.deleteFromComment(disLike);
            disLikeMapper.insertDisLike(disLike);
        }
    }
}
