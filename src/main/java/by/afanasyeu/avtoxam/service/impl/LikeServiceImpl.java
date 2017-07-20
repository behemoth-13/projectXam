package by.afanasyeu.avtoxam.service.impl;

import by.afanasyeu.avtoxam.dao.entities.Like;
import by.afanasyeu.avtoxam.dao.mappers.DisLikeMapper;
import by.afanasyeu.avtoxam.dao.mappers.LikeMapper;
import by.afanasyeu.avtoxam.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Afanasyeu Alexei on 12.07.2017.
 */
@Service
public class LikeServiceImpl implements LikeService{

    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private DisLikeMapper disLikeMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insertLike(Like like) {
        if (like.getMessageId() != null) {
            insertLikeMessage(like);
        } else {
            insertLikeComment(like);
        }
    }

    private void insertLikeMessage(Like like) {
        if (likeMapper.isExistFromMessage(like)) {
            likeMapper.deleteFromMessage(like);
        } else {
            disLikeMapper.deleteFromMessage(like);
            likeMapper.insertLike(like);
        }
    }

    private void insertLikeComment(Like like) {
        if (likeMapper.isExistFromComment(like)) {
            likeMapper.deleteFromComment(like);
        } else {
            disLikeMapper.deleteFromComment(like);
            likeMapper.insertLike(like);
        }
    }
}
