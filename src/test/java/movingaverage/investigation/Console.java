package movingaverage.investigation;

import movingaverage.CalculateurMoyenneMobile;

import java.util.Set;

import static java.util.stream.Collectors.joining;

public class Console implements ConsoleMBean {

    private final TradeGenerator tradeGenerator;
    private final CalculateurMoyenneMobile calculateurMoyenneMobile;

    public Console(TradeGenerator tradeGenerator, CalculateurMoyenneMobile calculateurMoyenneMobile) {
        this.tradeGenerator = tradeGenerator;
        this.calculateurMoyenneMobile = calculateurMoyenneMobile;
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
        Set<String> receivedInstuments = calculateurMoyenneMobile.searchReceivedInstuments();
        return receivedInstuments.stream().collect(joining(", "));
    }

    @Override
    public int computeRollingAverageAmount(String instrument) {
        return calculateurMoyenneMobile.computeRollingAverageAmountFor(instrument);
    }
}
