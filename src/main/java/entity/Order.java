package entity;

/**
 * This is an object which is used to store information of a specific order item
 * as an object. This object will commonly be in an arrayList of all the orders
 * by a specific user, so that we can list them for the user to see.
 *
 * @author Temporalis
 */
public class Order {

    private final int bot;
    private final int top;
    private final int amount;

    /**
     * Basic constructor. It takes three parameters and cannot be constructed
     * without all three present. These three parameters is the information of
     * the order item, the bottom type matching with the bottom id in the
     * database. The topping type, also matching the topping id in the database.
     * And finally an amount which is the amount of the specified cupcake has
     * been ordered.
     *
     * @param bot an int that matches up with a bottom id from the database
     * @param top an int that matches up with a topping id from the database
     * @param amount an int that specifies an amount of cupcakes of the matching
     * bottom and topping.
     */
    public Order(int bot, int top, int amount) {
        this.bot = bot;
        this.top = top;
        this.amount = amount;
    }

    /**
     * Returns an int with the id stored in the object
     * 
     * @return an int with the id stored in the object
     */
    public int getBot() {
        return bot;
    }

    /**
     * Returns an int with the id stored in the object
     * 
     * @return an int with the id stored in the object
     */
    public int getTop() {
        return top;
    }

    /**
     * Returns an int with the amount stored in the object
     * 
     * @return an int with the amount stored in the object
     */
    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("You have ordered %d cupcakes with bottom type %d and topping type %d", this.amount, this.bot, this.top);
    }
}
