package cn.eleven.app.mapper;

import cn.eleven.app.entity.SignType;

import java.util.List;

public interface SignTypeMapper {
    SignType findBySignTypeId(Integer id);

    List<SignType> findBySign(String signType);

    List<SignType> findAll();
}
