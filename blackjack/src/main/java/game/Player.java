package game;

public class Player {
    private String name;
    private Bet playerBet;
    private Deck playerDeck;

    //初始
    public Player(String name) {
        this.name = name;
        playerBet = new Bet();
        playerDeck = new Deck();
    }

    //返回玩家手牌
    public Deck getPlayerDeck() {
        return playerDeck;
    }

    //判断玩家是否破产（该类多数操作为封装）
    public boolean isLose() {
        return playerBet.isBrokeOut();
    }

    //发牌之后玩家拿牌
    public void getCard(Card card) {
        playerDeck.addCard(card);
    }

    //返回金额
    public int getPlayerMoney() {
        return playerBet.getMoney();
    }

    //赌钱成功true失败false
    public boolean betMoneyOn(int money) {
        return playerBet.betMoney(money);
    }

    //赌资重置
    public void resetbetMoneyOn(){
        playerBet.resetbetMoney();
    }

    //返回下注金额
    public int getbetMoneyOn(){
        return playerBet.getMoneyInBet();
    }

    //赢钱
    public void winMoney(int money) {
        playerBet.winBet(money);
    }

    //输钱
    public void loseMoney(int money) {
        playerBet.loseBet(money);
    }

    //重置
    public void resetDeck() {
        playerDeck.restart();
    }

    //重新开始
    public void resetBet(){
        playerBet.resetMoney();
    }
}
