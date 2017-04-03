# memory-investigation

This code presents memory issues.

Use Launcher.java with -Xms100m and -Xmx100m JVM parameters. Execute generateTrades() method of trade.calculator.ConsoleMBean. See what happens.


Tools could help you to understand the observed memory behaviors:
- JDK commands
    - https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jps.html
    - https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jstat.html
    - https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jmap.html
    - https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jcmd.html
- GC logs: -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc.log
- GCViewer (https://github.com/chewiebug/GCViewer)
- Memory Analyzer Tool (http://www.eclipse.org/mat/)
- Java Mission Control (add -XX:+UnlockCommercialFeatures and -XX:+FlightRecorder JVM arguments)
- https://github.com/jmaloney10/allocation-instrumenter
- ...

Good luck !

