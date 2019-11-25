package cn.eleven.app.mapper;

import cn.eleven.app.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserMapper {
    /**
     * 插入用户数据接口
     *
     * @param user 用户注册信息
     * @return 返回用户信息
     */
    Integer save(User user);

    /**
     * 根据id修改用户密码
     *
     * @param uid      用户id
     * @param password 用户密码
     * @return 修改成功的行数，若为0则修改失败
     */
    Integer updatePassword(
            @Param("uid") Integer uid,
            @Param("password") String password);

    /**
     * 更新个人资料
     *
     * @param user 封装了用户id和新的资料
     * @return
     */
    Integer updateBirthday(Integer uid, Date birthday);

    /**
     * 根据用户id查找用户
     *
     * @param uid
     * @return
     */
    User findByUid(Integer uid);

    /**
     * 根据手机号查询用户
     *
     * @param phone 用户注册手机号
     * @return 用户数据，若没有匹配，返回null
     */
    User findByPhone(String phone);

    void changeUsername(Integer uid, String username);

    void changeMessage(Integer uid, String message);

    /**
     * 根据uid查询用户信息
     *
     * @param uid 用户id
     * @return 用户信息
     */
    User showUserByUid(Integer uid);

    void changeDistrict(String provinceName, String provinceCode, String cityName, String cityCode, Integer uid);

    void changeAvatar(User user);

    void changeBackgroundImg(User user);

    void changeGender(Integer gender, Integer uid);

    String getUsernameByUid(Integer uid);

    String getAvatarByUid(String uid);
}
