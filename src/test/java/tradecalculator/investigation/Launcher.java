package tradecalculator.investigation;

import tradecalculator.TradeCalculator;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class Launcher {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {

        TradeGenerator tradeGenerator = TradeGenerator.INSTANCE;
        TradeCalculator tradeCalculator = new TradeCalculator(100);
        Console consoleMBean = new Console(tradeGenerator, tradeCalculator);

        ObjectName objectName = new ObjectName("trade.calculator:type=ConsoleMBean");

        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        mBeanServer.registerMBean(consoleMBean, objectName);

        tradeGenerator.getStreamOfTrades().forEach(tradeCalculator::consume);

        while (true) {

            if(!tradeGenerator.isGeneratingTrades()) {
                Thread.sleep(100);
            }

        }

    }

}
