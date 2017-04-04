package movingaverage;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class CalculateurMoyenneMobile {

    private static final Map<String, BlockingQueue<Integer>> TRADES_BY_INSTRUMENT = new ConcurrentHashMap<>();

    private final int inputWindowSize;

    public CalculateurMoyenneMobile(int windowSize) {
        this.inputWindowSize = windowSize;
    }

    public void consume(Trade trade) {

        if(inputWindowSize == 0) {
            return;
        }

        String instrument = trade.getInstrument();

        BlockingQueue<Integer> tradeAmounts = tradeAmountsFor(instrument);

        Integer tradeAmount = trade.getMoney().getAmountInCents();

        int currentNumberOfTrades = tradeAmounts.size();

        if(isWindowsSizeReached(currentNumberOfTrades)) {
            tradeAmounts.poll();
        }

        tradeAmounts.add(tradeAmount);

    }

    public int computeRollingAverageAmountFor(String instrument) {

        if(!receivedTradeFor(instrument)) {
            return 0;
        }

        BlockingQueue<Integer> tradeAmounts = TRADES_BY_INSTRUMENT.get(instrument);
        int numberOfTrades = tradeAmounts .size();

        return computeTotalAmount(tradeAmounts) / windowSizeFor(numberOfTrades);

    }

    public Set<String> searchReceivedInstuments() {
        return TRADES_BY_INSTRUMENT.keySet();
    }

    private BlockingQueue<Integer> tradeAmountsFor(String instrument) {
        return TRADES_BY_INSTRUMENT
                .computeIfAbsent(instrument, key -> new ArrayBlockingQueue<>(inputWindowSize));
    }

    private boolean isWindowsSizeReached(int currentNumberOfTrades) {
        return currentNumberOfTrades == inputWindowSize;
    }

    private boolean receivedTradeFor(String instrument) {
        return TRADES_BY_INSTRUMENT.containsKey(instrument);
    }

    private int computeTotalAmount(BlockingQueue<Integer> amounts) {

        return   amounts
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

    }

    private int windowSizeFor(int numberOfTrades) {

        if(numberOfTrades < inputWindowSize) {
            return numberOfTrades;
        }

        return inputWindowSize;

    }

}
