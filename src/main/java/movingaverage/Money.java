package movingaverage;

public class Money {

    private final int amountInCents;

    private final String currency;

    public Money(int amountInCents, String currency) {
        this.amountInCents = amountInCents;
        this.currency = currency;
    }

    public int getAmountInCents() {
        return amountInCents;
    }

    public String getCurrency() {
        return currency;
    }

}
