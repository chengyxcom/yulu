package cn.eleven.app.util;

import java.io.Serializable;

public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = -5769694379148055952L;

    private Integer state;
    private String message;
    private T data;

    public JsonResult() {
        super();
    }

    /*
     * 构造方法的参数变为异常，异常中获取message
     */
    public JsonResult(Throwable e) {
        super();
        this.message = e.getMessage();
    }

    public JsonResult(Integer state) {
        super();
        this.state = state;
    }


    public JsonResult(Integer state, T data) {
        super();
        this.state = state;
        this.data = data;
    }


    public Integer getState() {
        return state;
    }

    public void setState(Integer stat) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
