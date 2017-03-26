package tradecalculator.investigation;

import tradecalculator.TradeCalculator;

import java.util.Set;

import static java.util.stream.Collectors.joining;

public class Console implements ConsoleMBean {

    private final TradeGenerator tradeGenerator;
    private final TradeCalculator tradeCalculator;

    public Console(TradeGenerator tradeGenerator, TradeCalculator tradeCalculator) {
        this.tradeGenerator = tradeGenerator;
        this.tradeCalculator = tradeCalculator;
    }

    @Override
    public void generateTrades() {
        tradeGenerator.generateTrades();
    }

    @Override
    public void stopToGenerateTrades() {
        tradeGenerator.stopToGenerateTrades();

    }

    @Override
    public boolean isGeneratingTrades() {
        return tradeGenerator.isGeneratingTrades();
    }

    @Override
    public String searchReceivedInstuments() {
        Set<String> receivedInstuments = tradeCalculator.searchReceivedInstuments();
        return receivedInstuments.stream().collect(joining(", "));
    }

    @Override
    public int computeRollingAverageAmount(String instrument) {
        return tradeCalculator.computeRollingAverageAmountFor(instrument);
    }
}
