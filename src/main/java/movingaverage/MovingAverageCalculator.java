package movingaverage;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MovingAverageCalculator {

    private static final Map<String, CopyOnWriteArrayList<Integer>> TRADES_BY_INSTRUMENT = new ConcurrentHashMap<>();

    private final int inputWindowSize;

    public MovingAverageCalculator(int windowSize) {
        this.inputWindowSize = windowSize;
    }

    public void consume(Trade trade) {

        if(inputWindowSize == 0) {
            return;
        }

        String instrument = trade.getInstrument();

        CopyOnWriteArrayList<Integer> tradeAmounts = tradesFor(instrument);

        Integer tradeAmount = trade.getMoney().getAmountInCents();

        int currentNumberOfTrades = tradeAmounts.size();

        if(isWindowsSizeReached(currentNumberOfTrades)) {
            tradeAmounts.remove(0);
        }

        tradeAmounts.add(tradeAmount);

    }

    public int computeRollingAverageAmountFor(String instrument) {

        if(!receivedTradeFor(instrument)) {
            return 0;
        }

        CopyOnWriteArrayList<Integer> trades = TRADES_BY_INSTRUMENT.get(instrument);
        int numberOfTrades = trades.size();

        return computeTotalAmount(trades) / windowSizeFor(numberOfTrades);

    }

    public Set<String> searchReceivedInstuments() {
        return TRADES_BY_INSTRUMENT.keySet();
    }

    private CopyOnWriteArrayList<Integer> tradesFor(String instrument) {
        return TRADES_BY_INSTRUMENT
                .computeIfAbsent(instrument, key -> new CopyOnWriteArrayList<>());
    }

    private boolean isWindowsSizeReached(int currentNumberOfTrades) {
        return currentNumberOfTrades == inputWindowSize;
    }

    private boolean receivedTradeFor(String instrument) {
        return TRADES_BY_INSTRUMENT.containsKey(instrument);
    }

    private int computeTotalAmount(CopyOnWriteArrayList<Integer> amounts) {

        int totalAmount = 0;

        int numberOfTrades = amounts.size();
        int windowSize = windowSizeFor(numberOfTrades);

        for (int i = numberOfTrades - windowSize; i < numberOfTrades; i++) {
            Integer amount = amounts.get(i);
            totalAmount += amount;
        }

        return totalAmount;
    }

    private int windowSizeFor(int numberOfTrades) {

        if(numberOfTrades < inputWindowSize) {
            return numberOfTrades;
        }

        return inputWindowSize;

    }

}
