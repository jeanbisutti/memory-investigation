package movingaverage.investigation;

import movingaverage.Money;
import movingaverage.Trade;

import java.util.stream.Stream;

public class TradeGenerator {

    public static final TradeGenerator INSTANCE = new TradeGenerator();

    private TradeGenerator() {}

    private boolean generatingTrades;

    public Stream<Trade> streamOfTrades = buildStreamOfTrades();

    public Stream<Trade> getStreamOfTrades() {
        return streamOfTrades;
    }

    public void generateTrades() {
        generatingTrades = true;
    }

    public void stopToGenerateTrades() {
        generatingTrades = false;
    }

    public boolean isGeneratingTrades() {
        return generatingTrades;
    }

    private Stream<Trade> buildStreamOfTrades() {
        return Stream.generate(

                () -> {

                    try {
                        while (!generatingTrades) {
                            Thread.sleep(200);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    String appleInstrument = "AAPL";
                    Money usdMoney = new Money(12185, "USD");
                    return new Trade(appleInstrument, 10, usdMoney);

                }

        );
    }

}
