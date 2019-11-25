package cn.eleven.app.util;

/**
 * 验证码
 *
 * @author soft01
 */
public class VerificationCode {
    // 设置超时时间
    final static String defaultConnectTimeout = "sun.net.client.defaultConnectTimeout";
    final static String defaultReadTimeout = "sun.net.client.defaultReadTimeout";
    final static String Timeout = "10000";
    // 初始化ascClient需要的几个参数
    final static String product = "Dysmsapi";
    final static String domain = "dysmsapi.aliyuncs.com";
    final static String accessKeyId = "LTAI3Vh7ASr6KdZO";
    final static String accessKeySecret = "tPqjBGEAlxFX9LBanwhXJLWGZtGpAe";
    final static String SignName = "epartner";
    final static String TemplateCode = "SMS_173246361";

    public static String getDefaultconnecttimeout() {
        return defaultConnectTimeout;
    }

    public static String getDefaultreadtimeout() {
        return defaultReadTimeout;
    }

    public static String getTimeout() {
        return Timeout;
    }

    public static String getProduct() {
        return product;
    }

    public static String getDomain() {
        return domain;
    }

    public static String getAccesskeyid() {
        return accessKeyId;
    }

    public static String getAccesskeysecret() {
        return accessKeySecret;
    }

    public static String getSignname() {
        return SignName;
    }

    public static String getTemplatecode() {
        return TemplateCode;
    }

}
