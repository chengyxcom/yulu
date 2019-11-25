package cn.eleven.app.service;

import cn.eleven.app.entity.BigLetter;

import java.util.List;

public interface ILetterService {

    Boolean saveLetter(Integer userLeft, Integer userRight, String letter);

    List<BigLetter> showUserList(Integer userLeft);

    Boolean deleteList(Integer userLeft, Integer userRight);

    Boolean setTop(Integer userLeft, Integer userRight);

    Boolean cancelTop(Integer userLeft, Integer userRight);

    List<BigLetter> showLetter(Integer userLeft, Integer userRight);

    Boolean deleteLetter(Integer letterId);

    void changeIsReadState(Integer userLeft, Integer userRight);
}
