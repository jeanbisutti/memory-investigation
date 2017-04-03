package movingaverage.investigation;

import movingaverage.CalculateurMoyenneMobile;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class Launcher {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {

        TradeGenerator tradeGenerator = TradeGenerator.INSTANCE;
        CalculateurMoyenneMobile calculateurMoyenneMobile = new CalculateurMoyenneMobile(100);
        Console consoleMBean = new Console(tradeGenerator, calculateurMoyenneMobile);

        ObjectName objectName = new ObjectName("trade.calculator:type=ConsoleMBean");

        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        mBeanServer.registerMBean(consoleMBean, objectName);

        tradeGenerator.getStreamOfTrades().forEach(calculateurMoyenneMobile::consume);

        while (true) {

            if(!tradeGenerator.isGeneratingTrades()) {
                Thread.sleep(100);
            }

        }

    }

}
