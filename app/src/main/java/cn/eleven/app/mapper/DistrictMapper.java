package cn.eleven.app.mapper;

import cn.eleven.app.entity.District;

import java.util.List;

/**
 * 处理省/市/区持久层接口
 */
public interface DistrictMapper {
    /**
     * 根据父级的行政代号
     *
     * @param parent
     * @return
     */
    List<District> findByParent(String parent);

    District findByCode(String code);


}
