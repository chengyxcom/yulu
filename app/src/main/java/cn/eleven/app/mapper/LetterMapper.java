package cn.eleven.app.mapper;

import cn.eleven.app.entity.BigLetter;
import cn.eleven.app.entity.Letter;

import java.util.Date;
import java.util.List;

public interface LetterMapper {

    Integer saveLetter(Integer userLeft, Integer userRight, String letter, Date date, Integer isSend);

    List<BigLetter> findUserUid(Integer userLeft);

    Letter findFirstLetterByUid(Integer uid);

    Integer updateUserRightDelete(Integer userLeft, Integer userRight);

    void setUserRightDelete(Integer userLeft, Integer userRight);

    Integer setTop(Integer userLeft, Integer userRight);

    Integer cancelTop(Integer userLeft, Integer userRight);

    List<BigLetter> showLetter(Integer userLeft, Integer userRight);

    Integer getUserLeft(Integer letterId);

    Integer getUserRight(Integer letterId);

    Integer deleteLetter(Integer letterId);

    void changeIsReadState(Integer userLeft, Integer userRight);
}
