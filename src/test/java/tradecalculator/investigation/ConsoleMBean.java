package tradecalculator.investigation;

public interface ConsoleMBean {

    void generateTrades();

    void stopToGenerateTrades();

    boolean isGeneratingTrades();

    String searchReceivedInstuments();

    int computeRollingAverageAmount(String instrument);
}
