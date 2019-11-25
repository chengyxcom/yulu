package cn.eleven.app.service;

public interface ISignService {
    Integer Sign(Integer id, String signType);

    Integer getCountSign(Integer uid);

}
