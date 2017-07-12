package by.afanasyeu.avtoxam.service.impl;

import by.afanasyeu.avtoxam.dao.entities.Like;
import by.afanasyeu.avtoxam.dao.mappers.LikeMapper;
import by.afanasyeu.avtoxam.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Afanasyeu Alexei on 12.07.2017.
 */
@Service
public class LikeServiceImpl implements LikeService{

    @Autowired
    private LikeMapper likeMapper;

    @Transactional
    @Override
    public void insertLike(Like like) {
        likeMapper.insertLike(like);
    }

    @Transactional
    @Override
    public void deleteFromMessage(Like like) {
        likeMapper.deleteFromMessage(like);
    }

    @Transactional
    @Override
    public void deleteFromComment(Like like) {
        likeMapper.deleteFromComment(like);
    }
}
