package org.example.entity;

public class Order {
    //订单金额
    private int amount;
    //积分
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "amount=" + amount +
                ", score=" + score +
                '}';
    }
}
