package tradecalculator;

public class Trade {

    private final String instrument;

    private final int quantity;

    private final Money money;

    public Trade(String instrument, int quantity, Money money) {
        this.instrument = instrument;
        this.quantity = quantity;
        this.money = money;
    }

    public String getInstrument() {
        return instrument;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getMoney() {
        return money;
    }

}
