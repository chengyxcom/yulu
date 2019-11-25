package cn.eleven.app.service.ex;

public class VCodeNotMatchException extends ServiceException {

    private static final long serialVersionUID = 1148157191640366846L;

    public VCodeNotMatchException() {
        super();
    }

    public VCodeNotMatchException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public VCodeNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public VCodeNotMatchException(String message) {
        super(message);
    }

    public VCodeNotMatchException(Throwable cause) {
        super(cause);
    }


}
