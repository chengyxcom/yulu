package cn.eleven.app.service.ex;

/**
 * 手机号冲突异常，手机号已经被注册
 *
 * @author soft01
 */
public class PhoneConflictException extends ServiceException {

    private static final long serialVersionUID = 16007299209771056L;

    public PhoneConflictException() {
        super();
    }

    public PhoneConflictException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public PhoneConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneConflictException(String message) {
        super(message);
    }

    public PhoneConflictException(Throwable cause) {
        super(cause);
    }


}
