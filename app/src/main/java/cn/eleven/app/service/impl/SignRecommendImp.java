package cn.eleven.app.service.impl;

import cn.eleven.app.entity.SignRecommend;
import cn.eleven.app.mapper.SignRecommendMapper;
import cn.eleven.app.service.ISignRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignRecommendImp implements ISignRecommendService {

    @Autowired
    private SignRecommendMapper signRecommendMapper;

    @Override
    public SignRecommend getSignType(String signType) {
        return signRecommendMapper.findSignType(signType);
    }
}
