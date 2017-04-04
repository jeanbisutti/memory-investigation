package movingaverage;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class CalculateurMoyenneMobile {

    private static final Map<String, CopyOnWriteArrayList<Trade>> TRADES_BY_INSTRUMENT = new ConcurrentHashMap<>();

    private final int inputWindowSize;

    public CalculateurMoyenneMobile(int windowSize) {
        this.inputWindowSize = windowSize;
    }

    public void consume(Trade trade) {

        if(inputWindowSize == 0) {
            return;
        }

        String instrument = trade.getInstrument();

        CopyOnWriteArrayList<Trade> trades = tradesFor(instrument);

        int currentNumberOfTrades = trades.size();

        if(isWindowSizeReached(currentNumberOfTrades)) {
            trades.remove(0);
        }

        trades.add(trade);

    }

    public int computeRollingAverageAmountFor(String instrument) {

        if(!receivedTradeFor(instrument)) {
            return 0;
        }

        CopyOnWriteArrayList<Trade> trades = TRADES_BY_INSTRUMENT.get(instrument);
        int numberOfTrades = trades.size();

        return computeTotalAmount(trades) / windowSizeFor(numberOfTrades);

    }

    public Set<String> searchReceivedInstuments() {
        return TRADES_BY_INSTRUMENT.keySet();
    }

    private CopyOnWriteArrayList<Trade> tradesFor(String instrument) {
        return TRADES_BY_INSTRUMENT
                .computeIfAbsent(instrument, key -> new CopyOnWriteArrayList<>());
    }

    private boolean isWindowSizeReached(int currentNumberOfTrades) {
        return currentNumberOfTrades == inputWindowSize;
    }

    private boolean receivedTradeFor(String instrument) {
        return TRADES_BY_INSTRUMENT.containsKey(instrument);
    }

    private int computeTotalAmount(CopyOnWriteArrayList<Trade> trades) {

        return   trades
                .stream()
                .mapToInt(trade -> trade.getMoney().getAmountInCents())
                .sum();

    }

    private int windowSizeFor(int numberOfTrades) {

        if(numberOfTrades < inputWindowSize) {
            return numberOfTrades;
        }

        return inputWindowSize;

    }

}