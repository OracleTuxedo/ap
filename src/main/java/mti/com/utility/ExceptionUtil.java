package mti.com.utility;

import org.apache.logging.log4j.Logger;

public class ExceptionUtil {
    public static void logPrintStackTrace(Logger paramLogger, Exception paramException) {
        if (paramException != null) {
            paramLogger.error(paramException.toString());
            StackTraceElement[] arrayOfStackTraceElement = paramException.getStackTrace();
            if (arrayOfStackTraceElement != null)
                for (StackTraceElement stackTraceElement : arrayOfStackTraceElement)
                    paramLogger.error(stackTraceElement.toString());
        }
    }
}
