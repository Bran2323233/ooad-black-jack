package game;

import java.util.ArrayList;

public class Card {

    //牌的花色
    private String suit;
    //牌的点数
    private int point;
    //牌在游戏中实际代表的值
    private int value;

    //构造函数  同时判断卡牌的实际代表值  ACE代表11留着后续判断
    public Card(String suit, int point) {
        this.suit = suit;
        this.point = point;
        if (point >= 2 && point <= 10) {
            value = point;
        } else if (point >= 11 && point <= 13) {
            value = 10;
        } else if (point == 1) {
            value = 11;
        }
    }

    //返回花色
    public String getSuit() {
        return suit;
    }

    //返回牌的点数
    public int getPoint() {
        return point;
    }

    //返回牌的实际值
    public int getValue() {
        return value;
    }
}
