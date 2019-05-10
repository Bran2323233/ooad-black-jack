package game;

public class Bet {

    //本金
    private int money;
    //一轮中的赌金（其实没什么用，因为根本用不到这个值，后续会直接从键盘输入中读取）
    private int moneyInBet;

    //初始独资
    public Bet() {
        money = 1000;
        moneyInBet = 0;
    }

    //返回赌资
    public int getMoney() {
        return money;
    }

    //返回这一轮的赌金
    public int getMoneyInBet() {
        return moneyInBet;
    }

    //下注  如果返回值为false，则下注失败，否则为true
    public boolean betMoney(int this_turn_money) {
        if (money < moneyInBet + this_turn_money) {
            return false;
        } else {
            moneyInBet += this_turn_money;
            return true;
        }
    }

    //初始化下注金额
    public void resetbetMoney(){
        moneyInBet = 0;
    }

    //判断是否破产，true为破产，否则为false，同时初始化这一轮的赌金
    public boolean isBrokeOut() {
        if (money <= 0){
            return true;
        }
        else {
            return false;
        }
    }
    //初始化赌金
    public void resetMoney(){
        this.money = 1000;
    }

    //赢钱
    public void winBet(int winMoney) {
        money += winMoney;
    }

    //输钱
    public void loseBet(int loseMoney) {
        money -= loseMoney;
    }
}
