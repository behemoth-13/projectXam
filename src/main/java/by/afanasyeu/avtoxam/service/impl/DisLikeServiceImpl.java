package by.afanasyeu.avtoxam.service.impl;

import by.afanasyeu.avtoxam.dao.entities.DisLike;
import by.afanasyeu.avtoxam.dao.mappers.DisLikeMapper;
import by.afanasyeu.avtoxam.service.DisLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Afanasyeu Alexei on 12.07.2017.
 */

@Service
public class DisLikeServiceImpl implements DisLikeService{

    @Autowired
    private DisLikeMapper disLikeMapper;

    @Override
    public void insertDisLike(DisLike disLike) {
        disLikeMapper.insertDisLike(disLike);
    }

    @Override
    public void deleteFromMessage(DisLike disLike) {
        disLikeMapper.deleteFromMessage(disLike);
    }

    @Override
    public void deleteFromComment(DisLike disLike) {
        disLikeMapper.deleteFromComment(disLike);
    }
}
