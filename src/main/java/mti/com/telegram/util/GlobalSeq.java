package mti.com.telegram.util;

public class GlobalSeq {
    private static int g_seq = 1;

    public static synchronized String getSeq() {
        if (g_seq == 1000)
            g_seq = 1;
        g_seq++;
        return TelegramDateUtil.addLeftZeroFor3Bytes((Integer.valueOf(g_seq)).toString());
    }
}
