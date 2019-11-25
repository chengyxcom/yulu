package cn.eleven.app.service.ex;

/**
 * 验证码异常，验证码未发送
 *
 * @author soft01
 */
public class VCodeSendException extends ServiceException {

    private static final long serialVersionUID = 3574164710473643097L;

    public VCodeSendException() {
        super();
    }

    public VCodeSendException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public VCodeSendException(String message, Throwable cause) {
        super(message, cause);
    }

    public VCodeSendException(String message) {
        super(message);
    }

    public VCodeSendException(Throwable cause) {
        super(cause);
    }


}
