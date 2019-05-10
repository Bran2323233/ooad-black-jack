package play;

import game.Bet;
import game.Card;
import game.Deck;
import game.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;

public class GameInterface extends JFrame {

    private Player player;
    private Player dealer;
    private Deck deck;
    private int NumOfPCard;
    private int NumOfDCard;
    private JButton Deal;
    private JButton Hit;
    private JButton Start;
    private JButton Twe;
    private JButton Fif;
    private JButton Hun;
    private JButton Reset;
    private JTextField BetMoney;
    private  JTextField Money;

    public GameInterface() {

        player = new Player("玩家");
        dealer = new Player("庄家");
        deck = new Deck();
        NumOfPCard = 0;
        NumOfDCard = 0;
        //背景
        final Background bd = new Background();
        this.add(bd, 0);

        //面板尺寸
        this.setSize(800, 500);


        //位置居中
        this.setLocationRelativeTo(null);

        //标题
        this.setTitle("21点小游戏");

        //菜单
        JMenuBar jmb1 = new JMenuBar();
        JMenu jm1 = new JMenu("选项");
        JMenu jm2 = new JMenu("帮助");
        JMenuItem jmt1_1 = new JMenuItem("重新开始");
        JMenuItem jmt1_2 = new JMenuItem("退出游戏");
        final JMenuItem jmt2_1 = new JMenuItem("关于我们");
        jmb1.add(jm1);
        jmb1.add(jm2);
        jm1.add(jmt1_1);
        jm1.add(jmt1_2);
        jm2.add(jmt2_1);
        this.setJMenuBar(jmb1);

        //游戏按钮
        Deal = new JButton("Deal");
        bd.add(Deal);
        Hit = new JButton("Hit");
        bd.add(Hit);
        Start = new JButton("开始游戏");
        bd.add(Start);

        //赌注按钮
        Twe = new JButton("20");
        bd.add(Twe);
        Fif = new JButton("50");
        bd.add(Fif);
        Hun = new JButton("100");
        bd.add(Hun);
        Reset = new JButton("重置");
        bd.add(Reset);

        //设置按钮初始状态
        Twe.setEnabled(true);
        Fif.setEnabled(true);
        Hun.setEnabled(true);
        Start.setEnabled(true);
        Hit.setEnabled(false);
        Deal.setEnabled(false);
        Reset.setEnabled(true);

        //赌金
        BetMoney = new JTextField("下注：" + Integer.toString(player.getbetMoneyOn())+ "￥");
        //BetMoney.setBounds(750,10,40,20);
        BetMoney.setEnabled(false);
        bd.add(BetMoney);

        //总资金
        Money = new JTextField("总资金：" + Integer.toString(player.getPlayerMoney())+ "￥");
        //Money.setBounds(750,450,40,20);
        Money.setEnabled(false);
        bd.add(Money);

        //可见状态
        this.setVisible(true);

        //20
        Twe.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if(!player.betMoneyOn(20)){
                    JOptionPane.showInternalMessageDialog(Twe, "赌资不够！");
                }
                BetMoney.setEnabled(true);
                BetMoney.setText("下注：" + Integer.toString(player.getbetMoneyOn())+ "￥");
                BetMoney.setEnabled(false);
            }
        });

        //50
        Fif.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if(!player.betMoneyOn(50)){
                    JOptionPane.showInternalMessageDialog(Fif, "赌资不够！");
                }
                BetMoney.setEnabled(true);
                BetMoney.setText("下注：" + Integer.toString(player.getbetMoneyOn())+ "￥");
                BetMoney.setEnabled(false);
            }
        });

        //100
        Hun.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if(!player.betMoneyOn(100)){
                    JOptionPane.showInternalMessageDialog(Hun, "赌资不够！");
                }
                BetMoney.setEnabled(true);
                BetMoney.setText("下注：" + Integer.toString(player.getbetMoneyOn())+ "￥");
                BetMoney.setEnabled(false);
            }
        });

        //重置
        Reset.addActionListener(
                new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
               player.resetbetMoneyOn();
               BetMoney.setEnabled(true);
               BetMoney.setText("下注：" + Integer.toString(player.getbetMoneyOn())+ "￥");
               BetMoney.setEnabled(false);
            }
        });

        //重新开始
        jmt1_1.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.resetDeck();
                dealer.resetDeck();
                player.resetBet();
                player.resetbetMoneyOn();
                BetMoney.setEnabled(true);
                BetMoney.setText("下注：" + Integer.toString(player.getbetMoneyOn())+ "￥");
                BetMoney.setEnabled(false);
                Money.setEnabled(true);
                Money.setText("总资金：" + Integer.toString(player.getPlayerMoney())+ "￥");
                Money.setEnabled(false);
                Twe.setEnabled(true);
                Fif.setEnabled(true);
                Hun.setEnabled(true);
                Start.setEnabled(true);
                Reset.setEnabled(true);
            }
        });

        //退出游戏
        jmt1_2.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //关于我们
        jmt2_1.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                String Msg = "欢迎来到黑杰克!\n"
                        + "由符嘉铭、毕冉制作";
                JOptionPane.showInternalMessageDialog(jmt2_1, Msg);
            }
        });

        //开始游戏按钮
        Start.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //repaint();
                Hit.setEnabled(true);
                Deal.setEnabled(true);
                Twe.setEnabled(false);
                Fif.setEnabled(false);
                Hun.setEnabled(false);
                Start.setEnabled(false);
                Reset.setEnabled(false);
                if(player.isLose()){
                    JOptionPane.showInternalMessageDialog(Start, "你没钱了，兄弟，重新开始吧！");
                    Hit.setEnabled(false);
                    Deal.setEnabled(false);
                }
                else{
                    if(player.getbetMoneyOn() != 0){
                        startPlayer();
                        String Img = "";

                        //显示玩家卡牌
                        Deck deck1 = player.getPlayerDeck();
                        ArrayList<Card> playerList = deck1.getUseDeck();
                        for (int i = 0; i < playerList.size(); i++) {
                            Card card1 = playerList.get(i);
                            Img = card1.getSuit() + "_" + Integer.toString(card1.getPoint()) + ".png";
                            bd.paintCard(Img , i ,2);
                        }

                        //显示庄家卡牌,隐藏第一张
                        Deck deck2 = dealer.getPlayerDeck();
                        ArrayList<Card> dealerList = deck2.getUseDeck();
                        Card card2 = dealerList.get(1);
                        Img = card2.getSuit() + "_" + Integer.toString(card2.getPoint()) + ".png";
                        bd.paintCard(Img,1,1);
                        bd.paintCard("back.png",0,1);
                    }
                    else{
                        Hit.setEnabled(false);
                        Deal.setEnabled(false);
                        Twe.setEnabled(true);
                        Fif.setEnabled(true);
                        Hun.setEnabled(true);
                        Start.setEnabled(true);
                        Reset.setEnabled(true);
                        JOptionPane.showInternalMessageDialog(Start, "赌金不能为0！");
                    }
                }
            }
        });

        //要牌
        Deal.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //牌数不能大于5
                if (NumOfPCard == 5) {
                    JOptionPane.showInternalMessageDialog(Deal, "牌数不能大于5张！");
                } else {
                    String Img = "";
                    Card card = deck.getDeckCard();
                    player.getCard(deck.getCard());

                    //更新玩家卡牌
                    Img = card.getSuit() + "_" + Integer.toString(card.getPoint()) + ".png";
                    bd.paintCard(Img,NumOfPCard,2);
                    NumOfPCard++;
                }

            }
        });

        //开牌
        Hit.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int bet = player.getbetMoneyOn();
                //System.out.println(bet);
                Hit.setEnabled(false);
                Deal.setEnabled(false);
                Reset.setEnabled(false);
                int num1 = judgePlayer();
                if (judgeBlackJack() == 1 && num1 == 21) {
                    player.winMoney((int) (1.5 * bet));
                    dealer.loseMoney((int) (1.5 * bet));
                    JOptionPane.showInternalMessageDialog(Hit, "恭喜直接获胜！");
                }
                //此时电脑开始要牌
                String Img = "";
                while (NumOfDCard <= 4 && judgeDealer() < 17) {
                    Card card = deck.getDeckCard();
                    dealer.getCard(deck.getCard());

                    //更新庄家卡牌
                    Img = card.getSuit() + "_" + Integer.toString(card.getPoint()) + ".png";
                    bd.paintCard(Img ,NumOfDCard ,1);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    if (judgeDealer() >= 21) {
                            break;
                    }
                    NumOfDCard++;
                }

                //显示庄家隐藏卡牌
                Deck deck2 = dealer.getPlayerDeck();
                ArrayList<Card> dealerList = deck2.getUseDeck();
                Card card2 = dealerList.get(0);
                Img = card2.getSuit() + "_" + Integer.toString(card2.getPoint()) + ".png";
                bd.paintCard(Img ,0 ,1);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                    //电脑爆了
                if (judgeDealer() > 21 && num1 <= 21) {
                        player.winMoney(bet);
                        dealer.loseMoney(bet);
                        JOptionPane.showInternalMessageDialog(Hit, "你赢了！");
                } else if(judgeDealer() <= 21 && num1 <= 21){
                    //玩家和电脑开牌比大小
                    if (num1 > judgeDealer()) {
                            player.winMoney(bet);
                            dealer.loseMoney(bet);
                            JOptionPane.showInternalMessageDialog(Hit, "你赢了！");
                    } else if (judgeDealer() == num1) {
                            JOptionPane.showInternalMessageDialog(Hit, "平局！");
                    } else {
                            player.loseMoney(bet);
                            dealer.winMoney(bet);
                            JOptionPane.showInternalMessageDialog(Hit, "你输了！");
                    }
                }else if(judgeDealer() > 21 && num1 > 21){
                    //都爆了
                    JOptionPane.showInternalMessageDialog(Hit, "平局！");
                }else if(judgeDealer() <= 21 && num1 > 21){
                    player.loseMoney(bet);
                    dealer.winMoney(bet);
                    JOptionPane.showInternalMessageDialog(Hit, "你输了！");
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                Twe.setEnabled(true);
                Fif.setEnabled(true);
                Hun.setEnabled(true);
                Start.setEnabled(true);
                Reset.setEnabled(true);
                NumOfDCard = 0;
                NumOfPCard = 0;
                player.resetDeck();
                dealer.resetDeck();
                repaint();
                player.resetbetMoneyOn();
                BetMoney.setEnabled(true);
                BetMoney.setText("下注：" + Integer.toString(player.getbetMoneyOn())+ "￥");
                BetMoney.setEnabled(false);
                //System.out.println(player.getPlayerMoney());
                Money.setEnabled(true);
                Money.setText("总资金：" + Integer.toString(player.getPlayerMoney())+ "￥");
                Money.setEnabled(false);
            }
        });
    }

    //游戏开始
    public void startPlayer() {
        //初始化使用的牌
        deck.restart();
        //切牌
        deck.cutCard();
        //玩家两张牌
        player.getCard(deck.getCard());
        NumOfPCard++;
        player.getCard(deck.getCard());
        NumOfPCard++;
        //庄家两张牌
        dealer.getCard(deck.getCard());
        NumOfDCard++;
        dealer.getCard(deck.getCard());
        NumOfDCard++;
    }

    //判断玩家点数
    public int judgePlayer() {
        Deck deck = player.getPlayerDeck();
        ArrayList<Card> playerList = deck.getUseDeck();
        int countAce = 0;
        int amount = 0;
        for (int i = 0; i < playerList.size(); i++) {
            Card card = playerList.get(i);
            int value = card.getValue();
            if (value == 11) {
                countAce++;
            } else {
                amount += value;
            }
        }
        for (int i = countAce; i >= 1; i--) {
            if (amount + i * 11 <= 21) {
                amount += 11;
            } else {
                amount += 1;
            }
        }
        return amount;
    }

    // 判断庄家点数
    public int judgeDealer() {
        Deck deck = dealer.getPlayerDeck();
        ArrayList<Card> dealerList = deck.getUseDeck();
        int countAce = 0;
        int amount = 0;
        for (int i = 0; i < dealerList.size(); i++) {
            Card card = dealerList.get(i);
            int value = card.getValue();
            if (value == 11) {
                countAce++;
            } else {
                amount += value;
            }
        }
        for (int i = countAce; i >= 1; i--) {
            if (amount + i * 11 <= 21) {
                amount += 11;
            } else {
                amount += 1;
            }
        }
        return amount;
    }

    //判断是否有黑杰克
    public int judgeBlackJack() {
        Deck deck = player.getPlayerDeck();
        ArrayList<Card> playerList = deck.getUseDeck();
        int countAce = 0;
        for (int i = 0; i < playerList.size(); i++) {
            Card card = playerList.get(i);
            int value = card.getValue();
            if (value == 11) {
                countAce++;
            }
        }
        return countAce;
    }


    public static void main(String[] args){

        GameInterface GI = new GameInterface();

    }
}
