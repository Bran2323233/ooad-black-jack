package game;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Player player;
    private Player dealer;
    private Deck deck;

    public Game() {
        player = new Player("玩家");
        dealer = new Player("庄家");
        deck = new Deck();
    }

    //介绍（这些都是输出的东西）
    public void introduction() {
        System.out.println("欢迎来到黑杰克\n    由符嘉铭  毕冉制作");
    }

    //目录
    public void menu() {
        System.out.println("1.开始游戏;2.结束游戏");
    }

    //下注提示
    public void introBet() {
        //游戏中使用的牌
        System.out.println("请输入你要投注的金额（最低为10且为10的倍数）");
    }

    //选择操作
    public void introOperation() {
        System.out.println("您的选择：1.开牌；2.要牌；");
    }


    public void introSendCard() {
        System.out.println("开始发牌...");
    }

    public void playerLose() {
        System.out.println("你输了");
    }

    public void computerLose() {
        System.out.println("你赢了");
    }

    public void draw() {
        System.out.println("平局");
    }

    public void blackJack() {
        System.out.println("您得到了黑杰克");
    }

    //输出玩家金额
    public void printPlayerMoney() {
        int money = player.getPlayerMoney();
        System.out.println("You have $" + money + " .");
    }

    //玩家赌钱
    public boolean betOn(int money) {
        if (player.betMoneyOn(money)) {
            System.out.println("下注成功");
            return true;
        } else {
            System.out.println("下注失败");
            return false;
        }
    }

    //玩家要牌
    public void getPlayerCard() {
        player.getCard(deck.getCard());
    }

    //庄家要牌
    public void getDealerCard() {
        dealer.getCard(deck.getCard());
    }

    //玩家的牌一直可视
    public void printPlayerCard() {
        Deck deck = player.getPlayerDeck();
        ArrayList<Card> playerList = deck.getUseDeck();
        System.out.println("玩家:");
        for (int i = 0; i < playerList.size(); i++) {
            Card card = playerList.get(i);
            System.out.println(card.getSuit() + " " + card.getPoint());
        }
    }

    //首次的时候庄家一张牌隐藏
    public void printDealerCardFirst() {
        Deck deck = dealer.getPlayerDeck();
        ArrayList<Card> dealerList = deck.getUseDeck();
        System.out.println("庄家:");
        System.out.println("隐藏");
        for (int i = 1; i < dealerList.size(); i++) {
            Card card = dealerList.get(i);
            System.out.println(card.getSuit() + " " + card.getPoint());
        }
    }

    //当玩家操作完成之后庄家的牌可视
    public void printDealerCard() {
        Deck deck = dealer.getPlayerDeck();
        ArrayList<Card> dealerList = deck.getUseDeck();
        System.out.println("庄家:");
        for (int i = 0; i < dealerList.size(); i++) {
            Card card = dealerList.get(i);
            System.out.println(card.getSuit() + " " + card.getPoint());
        }
    }

    //判断玩家手中的牌值
    public int judgePlayer() {
        Deck deck = player.getPlayerDeck();
        ArrayList<Card> playerList = deck.getUseDeck();
        int countAce = 0;
        int amount = 0;
        for (int i = 0; i < playerList.size(); i++) {
            Card card = playerList.get(i);
            int value = card.getValue();
            if (value == 11) countAce++;
            else {
                amount += value;
            }
        }
        for (int i = countAce; i >= 1; i--) {
            if (amount + i * 11 <= 21) amount += 11;
            else amount += 1;
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

    //判断庄家手中的牌值
    public int judgeDealer() {
        Deck deck = dealer.getPlayerDeck();
        ArrayList<Card> dealerList = deck.getUseDeck();
        int countAce = 0;
        int amount = 0;
        for (int i = 0; i < dealerList.size(); i++) {
            Card card = dealerList.get(i);
            int value = card.getValue();
            if (value == 11) countAce++;
            else {
                amount += value;
            }
        }
        for (int i = countAce; i >= 1; i--) {
            if (amount + i * 11 <= 21) amount += 11;
            else amount += 1;
        }
        return amount;
    }

    public void runGame() {
        Scanner scanner = new Scanner(System.in);
        //开始界面
        this.introduction();
        //目录界面
        this.printPlayerMoney();
        this.menu();
        //接受输入 1为开始 2为退出
        int start = scanner.nextInt();
        while (start == 1) {
            //下注金额界面（并在该操作中初始化使用的卡牌）
            this.introBet();
            //读入赌资
            int bet = scanner.nextInt();
            //如果下注成功
            while (!this.betOn(bet)) {
                bet = scanner.nextInt();
            }
            //初始化使用的牌
            deck.restart();
            //切牌
            deck.cutCard();

            //发牌中...
            this.introSendCard();
            //玩家两张牌
            this.getPlayerCard();
            this.getPlayerCard();
            //庄家两张牌
            this.getDealerCard();
            this.getDealerCard();
            //输出玩家以及庄家的牌
            this.printPlayerCard();
            this.printDealerCardFirst();
            //操作界面
            this.introOperation();
            int op = scanner.nextInt();
            int numOfCard = 2;
            int numOfDCard = 2;
            //如果是黑杰克  赢1.5倍
            if (this.judgeBlackJack() == 1 && this.judgePlayer() == 21) {
                this.printDealerCard();
                this.printPlayerCard();
                this.blackJack();
                this.computerLose();
                player.winMoney((int) (1.5 * bet));
                dealer.loseMoney((int) (1.5 * bet));
            } else {
                //玩家不选择开牌并且牌数小于5张
                while (op != 1 && numOfCard <= 4) {
                    Card card = deck.getDeckCard();
                    System.out.println("您得到了" + card.getSuit() + " " + card.getPoint());
                    this.getPlayerCard();
                    numOfCard++;
                    if (this.judgePlayer() > 21){
                        break;
                    }
                    this.introOperation();
                    op = scanner.nextInt();
                }
                //如果爆了，游戏结束
                if (this.judgePlayer() > 21) {
                    this.printDealerCard();
                    this.printPlayerCard();
                    this.playerLose();
                    //输钱
                    player.loseMoney(bet);
                    dealer.winMoney(bet);
                    //如果破产 游戏结束
                    if (player.isLose()) {
                        break;
                    }
                } else {
                    //此时电脑开始要牌
                    while (numOfDCard <= 4 && this.judgeDealer() < 17) {
                        Card card = deck.getDeckCard();
                        System.out.println("电脑得到了" + card.getSuit() + " " + card.getPoint());
                        this.getDealerCard();
                        if (this.judgeDealer() > 21) {
                            break;
                        }
                        numOfDCard++;
                    }
                    //电脑爆了
                    if (this.judgeDealer() > 21) {
                        this.printDealerCard();
                        this.printPlayerCard();
                        this.computerLose();
                        player.winMoney(bet);
                        dealer.loseMoney(bet);
                        if (dealer.isLose()) {
                            break;
                        }
                    } else {
                        //玩家和电脑开牌比大小
                        if (this.judgePlayer() > this.judgeDealer()) {
                            this.printDealerCard();
                            this.printPlayerCard();
                            this.computerLose();
                            player.winMoney(bet);
                            dealer.loseMoney(bet);
                        } else if (this.judgeDealer() == this.judgePlayer()) {
                            this.printDealerCard();
                            this.printPlayerCard();
                            this.draw();
                        } else {
                            this.printDealerCard();
                            this.printPlayerCard();
                            player.loseMoney(bet);
                            dealer.winMoney(bet);
                            this.playerLose();
                        }
                    }
                }
            }
            //重置牌堆
            player.resetDeck();
            dealer.resetDeck();
            this.printPlayerMoney();
            this.menu();
            //接受输入 1为开始 2为退出
            start = scanner.nextInt();
        }
        System.out.println("游戏结束!");
    }

    //测试
    public static void main(String[] args) {
        Game game = new Game();
        game.runGame();
    }
}
