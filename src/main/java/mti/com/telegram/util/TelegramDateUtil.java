package mti.com.telegram.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class TelegramDateUtil {
    public static String getYYYYMMDD() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(gregorianCalendar.get(1)));
        stringBuffer.append(addLeftZeroFor2Bytes(String.valueOf(gregorianCalendar.get(2) + 1)));
        stringBuffer.append(addLeftZeroFor2Bytes(String.valueOf(gregorianCalendar.get(5))));
        return stringBuffer.toString();
    }

    public static String getHHMMSS() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        StringBuffer stringBuffer = new StringBuffer(addLeftZeroFor2Bytes(String.valueOf(gregorianCalendar.get(11))));
        stringBuffer.append(addLeftZeroFor2Bytes(String.valueOf(gregorianCalendar.get(12))));
        stringBuffer.append(addLeftZeroFor2Bytes(String.valueOf(gregorianCalendar.get(13))));
        return stringBuffer.toString();
    }

    public static String addLeftZeroFor2Bytes(String paramString) {
        return (paramString == null) ? "00" : ((paramString.length() < 2) ? ("0" + paramString) : paramString);
    }

    public static String addLeftZeroFor3Bytes(String paramString) {
        return (paramString == null) ? "000"
                : ((paramString.length() < 2) ? ("00" + paramString)
                        : ((paramString.length() < 3) ? ("0" + paramString) : paramString));
    }

    public static String getMicroTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSSSSS");
        return simpleDateFormat.format(date);
    }
}
