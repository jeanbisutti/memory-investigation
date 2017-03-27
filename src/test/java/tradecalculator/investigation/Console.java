package tradecalculator.investigation;

import tradecalculator.MovingAverageCalculator;

import java.util.Set;

import static java.util.stream.Collectors.joining;

public class Console implements ConsoleMBean {

    private final TradeGenerator tradeGenerator;
    private final MovingAverageCalculator movingAverageCalculator;

    public Console(TradeGenerator tradeGenerator, MovingAverageCalculator movingAverageCalculator) {
        this.tradeGenerator = tradeGenerator;
        this.movingAverageCalculator = movingAverageCalculator;
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
        Set<String> receivedInstuments = movingAverageCalculator.searchReceivedInstuments();
        return receivedInstuments.stream().collect(joining(", "));
    }

    @Override
    public int computeRollingAverageAmount(String instrument) {
        return movingAverageCalculator.computeRollingAverageAmountFor(instrument);
    }
}
