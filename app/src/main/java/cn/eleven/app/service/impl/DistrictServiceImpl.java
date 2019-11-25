package cn.eleven.app.service.impl;

import cn.eleven.app.entity.District;
import cn.eleven.app.mapper.DistrictMapper;
import cn.eleven.app.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;


    public List<District> getByParent(String parent) {
        return districtMapper.findByParent(parent);
    }

    @Override
    public District getByCode(String code) {
        return districtMapper.findByCode(code);
    }
}
