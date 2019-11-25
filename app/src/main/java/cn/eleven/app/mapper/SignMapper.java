package cn.eleven.app.mapper;

import cn.eleven.app.entity.Sign;

public interface SignMapper {
    Sign findByUid(Integer uid);

    Sign findBySignId(Integer id);

    Integer countSign(Integer uid);

    Integer saveSign(Sign sign);
}
