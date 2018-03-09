
package entity;

public class Order {
    private int bot;
    private int top;
    private int amount;

    public Order(int bot, int top, int amount) {
        this.bot = bot;
        this.top = top;
        this.amount = amount;
    }

    public int getBot() {
        return bot;
    }

    public int getTop() {
        return top;
    }

    public int getAmount() {
        return amount;
    }
}
