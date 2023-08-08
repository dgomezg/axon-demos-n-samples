package dev.dgomezg.axoniq.samples.shoppingcart.util;

import org.apache.commons.logging.Log;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DemoLogger {

    public enum LogAggregateLifecyclePhase {
        AGGREGATE_LOAD("🏗"),
        CREATE("✨"),
        SEND_EVENT("📮"),
        EVENT_RECEIVED("📩"),
        AUTODECTECT("");


        private String icon;

        private LogAggregateLifecyclePhase(String icon) {
            this.icon = icon;
        }
        public String getIcon() {
            return icon;
        }
    }

    private static boolean LOG_ENABLED = true;


    public static void log(String message) {
        if (LOG_ENABLED) {
            doLog(LogAggregateLifecyclePhase.AUTODECTECT,
                    StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getSimpleName(),
                    message);
        }
    }

    public static void log(LogAggregateLifecyclePhase phase, String message) {
        if (LOG_ENABLED) {
            doLog(phase,
                    StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getSimpleName(),
                    message);
        }
    }

    private static void doLog(LogAggregateLifecyclePhase phase, String caller, String message) {
        System.out.println(resolvePhase(phase) + " \t [" + caller + "] " + message);
    }

    private static String resolvePhase(LogAggregateLifecyclePhase phase) {
        return switch (phase) {
            case AUTODECTECT -> AggregateLifecycle.isLive() ?
                    LogAggregateLifecyclePhase.EVENT_RECEIVED.getIcon()
                    : LogAggregateLifecyclePhase.AGGREGATE_LOAD.getIcon();
            default -> phase.getIcon();
        };
    }

    private static String resolveCaller() {
        StackWalker instance = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
        return instance.getCallerClass().getSimpleName();
    }

    @Value("${shoppingcart.log.enabled:true}")
    public void setLogEnabled(boolean enabled) {
        DemoLogger.LOG_ENABLED = enabled;
    }


}
