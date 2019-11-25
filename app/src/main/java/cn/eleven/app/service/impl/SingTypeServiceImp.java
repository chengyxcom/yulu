package cn.eleven.app.service.impl;

import cn.eleven.app.entity.SignType;
import cn.eleven.app.mapper.SignTypeMapper;
import cn.eleven.app.service.ISignTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingTypeServiceImp implements ISignTypeService {

    @Autowired
    private SignTypeMapper signTypeMapper;

    @Override
    public SignType getBySingTypeId(Integer id) {
        SignType list = signTypeMapper.findBySignTypeId(id);
        return list;
    }

    @Override
    public SignType getBySignType(String signType) {
        System.out.println(signType);
        List<SignType> list = signTypeMapper.findBySign(signType);
        if (list.size() == 1) {
            return list.get(0);
        }
        int i = (int) (Math.random() * list.size());
        return list.get(i);
    }

    @Override
    public List<SignType> getAll() {
        return signTypeMapper.findAll();
    }
}
