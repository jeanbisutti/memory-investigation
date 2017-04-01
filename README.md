# memory-investigation

This code presents memory issues.

Use Launcher.java with -Xms100m and -Xmx100m JVM parameters. Execute generateTrades() method of trade.calculator.ConsoleMBean. See what happens.


Tools could help you to understand the observed memory behaviors:
- JDK commands: jps, jstat, jmap, jcmd
- JVM aguments: -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc.log
- GCViewer
- Memory Analyzer Tool (MAT)
- Java Mission Control (add -XX:+UnlockCommercialFeatures and -XX:+FlightRecorder JVM aguments)
- https://github.com/jmaloney10/allocation-instrumenter
- ...

Good luck !
