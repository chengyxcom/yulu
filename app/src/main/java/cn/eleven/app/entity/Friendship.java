package cn.eleven.app.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Friendship implements Serializable {
    private int userLeft;
    private int userRight;
    private int relation;
}
