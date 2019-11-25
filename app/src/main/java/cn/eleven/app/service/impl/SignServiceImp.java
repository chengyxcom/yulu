package cn.eleven.app.service.impl;

import cn.eleven.app.entity.Sign;
import cn.eleven.app.entity.SignType;
import cn.eleven.app.mapper.SignMapper;
import cn.eleven.app.mapper.SignTypeMapper;
import cn.eleven.app.service.ISignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SignServiceImp implements ISignService {

    @Autowired
    private SignMapper signMapper;

    @Autowired
    private SignTypeMapper signTypeMapper;

    @Override
    public Integer Sign(Integer id, String signType) {
        Sign sign = new Sign();
        Sign sign1 = signMapper.findByUid(id);
        Date now = new Date();

        List<SignType> list = signTypeMapper.findBySign(signType);
        int i = (int) (Math.random() * list.size());

        if (sign1 == null) {
            sign.setUid(id);
            sign.setSignTypeId(list.get(i).getSignTypeId());
            sign.setSignTime(now);
        } else {
            Date signTime = sign1.getSignTime();
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            if (fmt.format(signTime).equals(fmt.format(now))) {
                return 0;
            } else {
                sign.setUid(id);
                sign.setSignTypeId(list.get(i).getSignTypeId());
                sign.setSignTime(now);
            }
        }
        signMapper.saveSign(sign);
        return list.get(i).getSignTypeId();
    }

    @Override
    public Integer getCountSign(Integer uid) {
        return signMapper.countSign(uid);
    }
}
