package io;

public class MoneyData {
    private int amount;

    public MoneyData(int amount) {
        this.amount = amount;
    }

    public MoneyData() {

    }

    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }
}
