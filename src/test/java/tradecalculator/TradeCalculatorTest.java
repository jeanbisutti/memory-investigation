package tradecalculator;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TradeCalculatorTest {

    public static final String USD = "USD";

    public static final String APPLE_INSTRUMENT = "AAPL";

    public static final String ALPHABET_INSTRUMENT = "GOOG";

    public static final String TESLA_INSTRUMENT = "TSLA";


    @Test
    public void
    computed_moving_average_should_be_0_with_0_window_size() {

        // Given
        TradeCalculator tradeCalculator = new TradeCalculator(0);

        tradeCalculator.consume(anAppleTrade(12185));
        tradeCalculator.consume(anAppleTrade(12190));

        // When
        int rollingAverage = tradeCalculator.computeRollingAverageAmountFor(APPLE_INSTRUMENT);

        //Then
        assertThat(rollingAverage).isEqualTo(0);

    }

    @Test
    public void
    should_compute_moving_average_with_one_instrument_type() {

        //Given
        int windowSize = 3;
        TradeCalculator tradeCalculator = new TradeCalculator(windowSize);

        tradeCalculator.consume(anAppleTrade(12185));
        tradeCalculator.consume(anAppleTrade(12190));
        tradeCalculator.consume(anAppleTrade(12195));

        //When
        int rollingAverage = tradeCalculator.computeRollingAverageAmountFor(APPLE_INSTRUMENT);

        //Then
        assertThat(rollingAverage).isEqualTo(12190);
    }

    private Trade anAppleTrade(int amount) {
        Money usdMoney = new Money(amount, USD);
        return new Trade(APPLE_INSTRUMENT, 10, usdMoney);
    }

    @Test
    public void
    should_compute_moving_average_with_one_instrument_type_and_trade_number_less_than_window_size() {

        //Given
        int windowSize = 3;
        TradeCalculator tradeCalculator = new TradeCalculator(windowSize);

        tradeCalculator.consume(anAlphabetTrade(12185));
        tradeCalculator.consume(anAlphabetTrade(12195));

        //When
        int rollingAverage = tradeCalculator.computeRollingAverageAmountFor(ALPHABET_INSTRUMENT);

        //Then
        assertThat(rollingAverage).isEqualTo(12190);
    }

    private Trade anAlphabetTrade(int amount) {
        Money usdMoney = new Money(amount, USD);
        return new Trade(ALPHABET_INSTRUMENT, 10, usdMoney);
    }

    @Test
    public void
    should_compute_moving_average_with_one_instrument_type_and_trade_number_greater_than_window_size() {

        //Given
        int windowSize = 3;
        TradeCalculator tradeCalculator = new TradeCalculator(windowSize);

        tradeCalculator.consume(aTeslaTrade(11101));
        tradeCalculator.consume(aTeslaTrade(12185));
        tradeCalculator.consume(aTeslaTrade(12190));
        tradeCalculator.consume(aTeslaTrade(12195));

        //When
        int rollingAverage = tradeCalculator.computeRollingAverageAmountFor(TESLA_INSTRUMENT);

        //Then
        assertThat(rollingAverage).isEqualTo(12190);
    }

    private Trade aTeslaTrade(int amount) {
        Money usdMoney = new Money(amount, USD);
        return new Trade(TESLA_INSTRUMENT, 10, usdMoney);
    }

}