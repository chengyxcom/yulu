package cn.eleven.app.service;

import cn.eleven.app.entity.SignType;

import java.util.List;

public interface ISignTypeService {
    SignType getBySingTypeId(Integer id);

    SignType getBySignType(String signType);

    List<SignType> getAll();
}
