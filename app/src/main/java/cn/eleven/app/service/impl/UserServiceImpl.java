package cn.eleven.app.service.impl;


import cn.eleven.app.entity.District;
import cn.eleven.app.entity.User;
import cn.eleven.app.mapper.DistrictMapper;
import cn.eleven.app.mapper.UserMapper;
import cn.eleven.app.service.IUserService;
import cn.eleven.app.service.ex.*;
import cn.eleven.app.util.VerificationCode;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DistrictMapper districtMapper;


    /**
     * 定义一个发送验证码的接口，属性有手机号
     * 判断手机号是否合法
     * 合法随机生成一个验证码发送给用户，形成一个标准待被校验的验证码
     */
    @Override
    public String acquireVcode(String phone) {
        //判断号码是否合法
        if (phone.length() != 11) {
            //是：手机号码长度不合法
            throw new VCodeSendException("手机号码长度不合法！");
        }
        //根据验证码进行注册
        // 设置超时时间
        System.setProperty(VerificationCode.getDefaultconnecttimeout(), VerificationCode.getTimeout());
        System.setProperty(VerificationCode.getDefaultreadtimeout(), VerificationCode.getTimeout());
        // 初始化ascClient需要的几个参数
        final String product = VerificationCode.getProduct();
        final String domain = VerificationCode.getDomain();
        // 替换成你的AK
        final String accessKeyId = VerificationCode.getAccesskeyid();// 你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = VerificationCode.getAccesskeysecret();
        // 初始化ascClient
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e1) {
            e1.printStackTrace();
            System.err.println("验证码出现未知错误");
        }
        //获取验证码
        String standardCode = vcode();
        IAcsClient acsClient = new DefaultAcsClient(profile);
        // 组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        // 使用post提交
        request.setMethod(MethodType.POST);
        // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phone);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(VerificationCode.getSignname());
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(VerificationCode.getTemplatecode());
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{ \"code\":\"" + standardCode + "\"}");
        SendSmsResponse sendSmsResponse;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null
                    && sendSmsResponse.getCode().equals("OK")) {
                // 请求成功
                System.out.println("获取验证码成功！！！");
            } else {
                //如果验证码出错，会输出错误码告诉你具体原因
                System.out.println(sendSmsResponse.getCode());
                System.out.println("获取验证码失败...");
            }
        } catch (ServerException e) {
            e.printStackTrace();
            throw new VCodeSendException("由于系统维护，暂时无法注册！！！");
        } catch (ClientException e) {
            e.printStackTrace();
            throw new VCodeSendException("由于系统维护，暂时无法注册！！！");
        }
        return standardCode;
    }


    /**
     * 用户注册功能
     */
    @Override
    public void reg(User user, String code, String standardCode) {
        //获取用户手机号码
        String phone = user.getPhone();
        // 调用userMapper根据手机号码查询用户数据
        User result = userMapper.findByPhone(phone);
        // 判断查询结果是否不为null
        if (result != null) {
            // 是：手机号已存在，不允许注册，抛出PhoneConflictException异常
            throw new PhoneConflictException("手机号码已经被注册，不能再次注册！");
        }
        // 参数user是客户端提交的注册数据，并不包含隐藏数据
        // 执行加密
        String salt = UUID.randomUUID().toString();
        String md5Password = getMd5Password(user.getPassword(), salt);
        System.out.println("user.getPassword==" + user.getPassword());
        System.out.println("reg_md5Password==" + md5Password);
        // 补全数据：salt
        user.setSalt(salt);
        // 补全数据：加密后的密码
        user.setPassword(md5Password);
        // 补全数据：注册时间为当前时间
        user.setRegTime(new Date());
        //补全数据：用户名默认手机号码
        user.setUsername(phone);

        //补全其他数据(默认)
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse("1999-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setBirthday(date);
        user.setAvatar("/avatar/morentouxiang.png");
        user.setGender(1);
        user.setProvinceName("云南");
        user.setProvinceCode("530000");
        user.setAge(0);
        user.setCityName("昆明");
        user.setCityCode("530100");
        user.setMessage("写点什么呢，想想...");
        user.setBackgroundImg("/backgroundImg/morenbeijing.png");
        user.setIsDelete(0);

        //校验验证码
        if (!standardCode.equals(code)) {
            //验证码不正确
            throw new VCodeNotMatchException("验证码错误！");
        } else {//验证码正确
            // 调用userMapper的功能插入用户数据，实现注册，并获取插入数据时的返回值
            System.out.println("phone=" + phone);
            Integer row = userMapper.save(user);
            // 判断以上返回值是否不为1
            if (row != 1) {
                // 是：插入用户数据失败，抛出InsertException
                throw new InsertException("插入用户数据时出现未知错误！");
            }
        }
    }

    /**
     * 根据手机号码，密码登录
     */
    public User loginByPassword(String phone, String password) {
        System.out.println("password = " + password);
        // 根据参数username查询用户数据
        User result = userMapper.findByPhone(phone);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：用户名不存在，抛出UserNotFoundException
            throw new UserNotFoundException("手机号码不存在");
        }

        // 从查询结果中取出盐值
        String salt = result.getSalt();
        // 调用getMd5Password()，基于参数password和盐值进行加密
        String md5Password = getMd5Password(password, salt);
        System.out.println("md5Password==" + md5Password);
        System.out.println("result.getPassword()==" + result.getPassword());
        // 判断加密后的密码与查询结果中的密码是否不匹配
        if (!result.getPassword().equals(md5Password)) {
            System.out.println("result.getPassword()==" + result.getPassword());
            // 是：密码错误，抛出PasswordNotMatchException
            throw new PasswordNotMatchException("密码错误");
        }

        // 将查询结果中不应该返回的字段设置为null
        // 例如：regTime、password、salt
        result.setRegTime(null);
        result.setPassword(null);
        result.setSalt(null);
        // 返回查询结果
        return result;

    }

    /**
     * 根据手机号码，验证码登录
     */
    public User loginByCode(String phone, String code, String standardCode) {
        // 根据参数username查询用户数据
        User result = userMapper.findByPhone(phone);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：用户名不存在，抛出UserNotFoundException
            throw new UserNotFoundException("手机号码不存在");
        }

        if (!standardCode.equals(code)) {
            // 是：验证码错误，抛出PasswordNotMatchException
            throw new VCodeNotMatchException("验证码错误");
        }

        // 将查询结果中不应该返回的字段设置为null
        // 例如：regTime、password、salt
        result.setRegTime(null);
        result.setPassword(null);
        result.setSalt(null);
        // 返回查询结果
        return result;
    }

    /**
     * 用户修改密码
     */
    @Override
    public void changePasswordByOld(Integer uid, String phone, String oldPassword, String newPassword) {
        // 根据参数uid查询用户数据
        User result = userMapper.findByUid(uid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：用户数据不存在，抛出UserNotFoundException
            throw new UserNotFoundException("用户数据不存在或已删除");
        }

        // 从查询结果中取出盐值
        String salt = result.getSalt();
        // 将参数oldPassword进行加密，得到oldMd5Password
        String oldMd5Password = getMd5Password(oldPassword, salt);
        // 判断查询结果中的password与oldMd5Password是否不匹配
        if (!result.getPassword().equals(oldMd5Password)) {
            // 是：密码验证失败，抛出PasswordNotMatchException
            throw new PasswordNotMatchException(
                    "原密码错误");
        }

        // 将参数newPassword进行加密，得到newMd5Password
        String newMd5Password = getMd5Password(newPassword, salt);
        // 执行更新密码，获取返回值
        Integer rows = userMapper.updatePassword(
                uid, newMd5Password);
        // 判断返回的受影响的行数是否不为1
        if (rows != 1) {
            // 是：更新失败，抛出UpdateException
            throw new UpdateException("更新数据时出现未知错误");
        }
    }

    @Override
    public void changePasswordByCode(Integer uid, String code, String newPassword, String standardCode) {
        // 根据参数uid查询用户数据
        User result = userMapper.findByUid(uid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：用户数据不存在，抛出UserNotFoundException
            throw new UserNotFoundException("用户数据不存在或已删除");
        }
        //从查询结果中取出手机号码
        String phone = result.getPhone();
        //验证码校验
        if (!standardCode.equals(code)) {
            // 是：验证码错误，抛出PasswordNotMatchException
            throw new VCodeNotMatchException("验证码错误");
        }
        // 从查询结果中取出盐值
        String salt = result.getSalt();
        // 将参数newPassword进行加密，得到newMd5Password
        String newMd5Password = getMd5Password(newPassword, salt);
        // 执行更新密码，获取返回值
        Integer rows = userMapper.updatePassword(
                uid, newMd5Password);
        // 判断返回的受影响的行数是否不为1
        if (rows != 1) {
            // 是：更新失败，抛出UpdateException
            throw new UpdateException("更新数据时出现未知错误");
        }
    }

    @Override
    public void changePasswordByPhone(Integer uid, String password) {
        User result = userMapper.findByUid(uid);
        if (result == null) {
            throw new UserNotFoundException("用户数据不存在或已删除");
        }
        String salt = result.getSalt();
        String newMd5Password = getMd5Password(password, salt);
        Integer rows = userMapper.updatePassword(
                uid, newMd5Password);
        if (rows != 1) {
            // 是：更新失败，抛出UpdateException
            throw new UpdateException("更新数据时出现未知错误");
        }
    }

    @Override
    public String getAvatarByUid(String uid) {
        return userMapper.getAvatarByUid(uid);
    }

    @Override
    public void changeBirthday(Integer uid, Date date) {
        // 根据参数uid查询用户数据
        User result = userMapper.findByUid(uid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：用户数据不存在，抛出UserNotFoundException
            throw new UserNotFoundException("用户数据不存在或已删除");
        }
        System.out.println(date);
        Integer rows = userMapper.updateBirthday(uid, date);
        if (rows != 1) {
            // 是：更新失败，抛出UpdateException
            throw new UpdateException("更新数据时出现未知错误");
        }
    }

    /**
     * 用户根据用户id获取用户信息
     *
     * @param uid
     * @return
     */
    @Override
    public User getByUid(Integer uid) {
        return userMapper.findByUid(uid);
    }

    @Override
    public void changeUsername(Integer uid, String username) {
        userMapper.changeUsername(uid, username);
    }

    @Override
    public void changeMessage(Integer uid, String message) {
        userMapper.changeMessage(uid, message);
    }

    @Override
    public User showUserByUid(Integer uid) {
        return userMapper.showUserByUid(uid);
    }

    @Override
    public void changeDistrict(String provinceCode, String cityCode, Integer uid) {
        District province = districtMapper.findByCode(provinceCode);
        District city = districtMapper.findByCode(cityCode);
        userMapper.changeDistrict(province.getName(), provinceCode, city.getName(), cityCode, uid);
    }

    @Override
    public void changeAvatar(User user) {
        userMapper.changeAvatar(user);
    }

    @Override
    public void changeBackgroundImg(User user) {
        userMapper.changeBackgroundImg(user);
    }

    @Override
    public void changeGender(Integer gender, Integer uid) {
        userMapper.changeGender(gender, uid);
    }

    @Override
    public String getUsernameByUid(Integer uid) {
        return userMapper.getUsernameByUid(uid);
    }


    /**
     * 获取加密后的密码
     *
     * @param passsword 原密码
     * @param salt      盐值
     * @return 加密后密码
     */
    private String getMd5Password(String password, String salt) {
        //加密规则：在原密码的左侧和右侧均拼接盐值，循环加密5次
        String str = salt + password + salt;
        for (int i = 0; i < 5; i++) {
            str = DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
        }
        return str;
    }

    /**
     * 生成6位随机数验证码
     *
     * @return
     */
    private static String vcode() {
        String vcode = "";
        for (int i = 0; i < 4; i++) {
            vcode = vcode + (int) (Math.random() * 9);
        }
        return vcode;
    }


}
