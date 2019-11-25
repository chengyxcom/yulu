package cn.eleven.app.service.impl;

import cn.eleven.app.entity.BigLetter;
import cn.eleven.app.entity.Letter;
import cn.eleven.app.entity.User;
import cn.eleven.app.mapper.LetterMapper;
import cn.eleven.app.mapper.UserMapper;
import cn.eleven.app.service.ILetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class LetterServiceImpl implements ILetterService {
    @Autowired
    private LetterMapper letterMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public Boolean saveLetter(Integer userLeft, Integer userRight, String letter) {
        Date date = new Date();
        System.out.println("letter=" + letter);

        Integer isSend = 1;
        Integer result = letterMapper.saveLetter(userLeft, userRight, letter, date, isSend);
        letterMapper.setUserRightDelete(userLeft, userRight);
        isSend = 0;
        Integer result1 = letterMapper.saveLetter(userRight, userLeft, letter, date, isSend);
        letterMapper.setUserRightDelete(userRight, userLeft);

        if (result == 1 && result1 == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<BigLetter> showUserList(Integer userLeft) {
        List<BigLetter> lists = letterMapper.findUserUid(userLeft);
        for (BigLetter list : lists) {
            Integer uid = list.getUid();
            Letter letter = letterMapper.findFirstLetterByUid(uid);
            list.setLetter(letter.getLetter());
            list.setLetterTime(letter.getLetterTime());
            list.setUserRightTop(letter.getUserRightTop());
            list.setLetterId(letter.getLetterId());
        }

        Collections.sort(lists, new Comparator<BigLetter>() {
            @Override
            public int compare(BigLetter o1, BigLetter o2) {
                //根据userRightTop排序
                if (o1.getUserRightTop() > o2.getUserRightTop()) {
                    return -1;
                }
                if (o1.getUserRightTop() == o2.getUserRightTop()) {
                    return 0;
                }
                return 1;
            }
        });

        return lists;
    }

    @Override
    public Boolean deleteList(Integer userLeft, Integer userRight) {
        Integer result = letterMapper.updateUserRightDelete(userLeft, userRight);
        if (result != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean setTop(Integer userLeft, Integer userRight) {
        Integer result = letterMapper.setTop(userLeft, userRight);
        if (result != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean cancelTop(Integer userLeft, Integer userRight) {
        Integer result = letterMapper.cancelTop(userLeft, userRight);
        if (result != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<BigLetter> showLetter(Integer userLeft, Integer userRight) {
        List<BigLetter> list = letterMapper.showLetter(userLeft, userRight);
        for (BigLetter letter : list) {
            Integer isSend = letter.getIsSend();
            Integer letterId = letter.getLetterId();
            if (isSend == 1) {
                Integer userLeft1 = letterMapper.getUserLeft(letterId);
                User user = userMapper.findByUid(userLeft1);
                letter.setUid(user.getUid());
                letter.setUsername(user.getUsername());
                letter.setAvatar(user.getAvatar());
            } else {
                Integer userRight1 = letterMapper.getUserRight(letterId);
                User user = userMapper.findByUid(userRight1);
                letter.setUid(user.getUid());
                letter.setUsername(user.getUsername());
                letter.setAvatar(user.getAvatar());
            }
        }
        return list;
    }

    @Override
    public Boolean deleteLetter(Integer letterId) {
        Integer result = letterMapper.deleteLetter(letterId);
        if (result != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void changeIsReadState(Integer userLeft, Integer userRight) {
        letterMapper.changeIsReadState(userLeft, userRight);
    }
}
