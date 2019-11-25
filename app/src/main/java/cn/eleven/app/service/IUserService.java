package cn.eleven.app.service;


import cn.eleven.app.entity.User;

import java.util.Date;

public interface IUserService {

    /**
     * 用户注册业务层接口
     *
     * @param user 用户信息
     */
    void reg(User user, String code, String standardCode);

    /**
     * 给用户发送验证码
     *
     * @param phone 验证码所发送的手机号
     * @return 返回标准验证码用于校验
     */
    String acquireVcode(String phone);

    /**
     * 用户根据手机号码，密码登录
     *
     * @param phone    用户登录手机号码
     * @param password 用户登录密码
     * @return 返回用户信息
     */
    User loginByPassword(String phone, String password);

    /**
     * 根据验证码进行登录
     *
     * @param phone 登录手机号码
     * @param code  登录验证码
     * @return
     */
    User loginByCode(String phone, String code, String standardCode);

    /**
     * 用户根据旧密码修改用户密码
     *
     * @param uid         用户id
     * @param username    用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void changePasswordByOld(Integer uid, String phone, String oldPassword, String newPassword);

    /**
     * 用户根据旧密码修改用户密码
     *
     * @param uid         用户id
     * @param Code        验证码
     * @param newPassword 新密码
     */
    void changePasswordByCode(Integer uid, String Code, String newPassword, String standardCode);

    void changeBirthday(Integer uid, Date date);

    /**
     * 用户根据用户id获取用户信息
     *
     * @param uid
     * @return
     */
    User getByUid(Integer uid);

    void changeUsername(Integer uid, String username);

    void changeMessage(Integer uid, String message);

    User showUserByUid(Integer uid);


    void changeDistrict(String provinceCode, String cityCode, Integer uid);

    void changeAvatar(User user);

    void changeBackgroundImg(User user);

    void changeGender(Integer gender, Integer uid);

    String getUsernameByUid(Integer commentUid);

    void changePasswordByPhone(Integer uid, String password);

    String getAvatarByUid(String uid);
}
