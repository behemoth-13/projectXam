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
 * @author Afanasyeu Alexei
 */

@Service
public class DisLikeServiceImpl implements DisLikeService{

    private final DisLikeMapper disLikeMapper;

    private final LikeMapper likeMapper;

    @Autowired
    public DisLikeServiceImpl(DisLikeMapper disLikeMapper, LikeMapper likeMapper) {
        this.disLikeMapper = disLikeMapper;
        this.likeMapper = likeMapper;
    }

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
