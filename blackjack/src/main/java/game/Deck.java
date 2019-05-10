package game;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    //一局游戏中使用的牌
    private ArrayList<Card> useDeck;

    //牌的所有花色
    private String[] color = {"1", "2", "3", "4"};

    public Deck() {
        useDeck = new ArrayList<Card>();
    }

    //返回牌堆
    public ArrayList<Card> getUseDeck() {
        return useDeck;
    }

    //往自己的牌中加一张牌
    public void addCard(Card card) {
        useDeck.add(card);
    }

    //切牌
    public void cutCard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 13; j++) {
                String cardColor = color[i];
                Card myCard = new Card(cardColor, j);
                this.addCard(myCard);
            }
        }
        //打乱顺序
        Collections.shuffle(useDeck);
    }

    //返回剩余牌的数量
    public int countList() {
        return useDeck.size();
    }

    //从使用的牌中发一张牌，发第一张牌
    public Card getCard() {
        Card card = useDeck.get(0);
        useDeck.remove(0);
        return card;
    }

    //返回第一张牌
    public Card getDeckCard() {
        Card card = useDeck.get(0);
        return card;
    }

    //清空牌库
    public void restart() {
        while (!useDeck.isEmpty()) {
            useDeck.remove(0);
        }
    }

}
