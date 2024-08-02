package mti.az;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);
    public static final String EMPTY = "";
    public static final String BLANK = " ";

    /**
     * Function to check if String is null.
     * 
     * @param str
     * @return boolean
     * @see #nvl(String, String)
     */
    public static boolean isNull(String str) {
        return str == null;
    }

    /**
     * Function to check if String is null or Empty.
     * 
     * <pre>
     * boolean chk = StringUtil.isNullOrEmpty(request.getParameter("id1"));
     * </pre>
     * 
     * @param str
     * @return boolean
     * @see #nvl2(String, String)
     */
    public static boolean isNullOrEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Function to check if String is null or Empty or Blank.
     * 
     * <pre>
     * boolean chk = StringUtil.isNullOrBlank(request.getParameter("id1"));
     * </pre>
     * 
     * @param str
     * @return boolean
     * @see #nvl3(String, String)
     */
    public static boolean isNullOrBlank(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Function to check if String is null and replace it with a specified String if
     * it is null.
     * 
     * <pre>
     * String id1 = StringUtil.nvl(request.getParameter("id1"), "");
     * 
     * </pre>
     * 
     * @param str
     * @param def String to replace if null
     * @return String
     */
    public static String nvl(String str, String def) {
        return str == null ? def : str;
    }

    /**
     * Function to check if String is null and replace it with ""({@link #EMPTY}) if
     * it is null.
     * 
     * <pre>
     * String id1 = StringUtil.nvl(request.getParameter("id1"), "");
     * 
     * </pre>
     * 
     * @param str
     * @return String
     * @see #nvl(String, String)
     */
    public static String nvl(String str) {
        return nvl(str, EMPTY);
    }

    /**
     * Function to check if String is null and replace it with a specified String if
     * it is null or Empty.
     * 
     * <pre>
     * String id1 = StringUtil.nvl2(request.getParameter("id1"),"unKnown");
     * id1 to replace "unKnown" if null or Empty.
     * </pre>
     * 
     * @param str
     * @param def String to replace if null or Empty
     * @return String
     */
    public static String nvl2(String str, String def) {
        return isNullOrEmpty(str) ? def : str;
    }

    /**
     * Function to check if String is null and replace it with a specified String if
     * it is null or Empty or Blank.
     * 
     * <pre>
     * String id1 = StringUtil.nvl3(request.getParameter("id1"),"unKnown");
     * id1 to replace "unKnown" if null or Empty or Blank.
     * </pre>
     * 
     * @param str
     * @param def String to replace if null or Empty or Blank.
     * @return String
     */
    public static String nvl3(String str, String def) {
        if (str == null) {
            return def;
        }
        str = str.trim();
        if (str.length() == 0) {
            return def;
        }
        return str;
    }

    /**
     * Function is String replace
     * 
     * <pre>
     * StringUtil.replace(null, "a", "A")   = null
     * StringUtil.replace("", "a", "A")     = ""
     * StringUtil.replace("1234", "a", "A") = "1234"
     * StringUtil.replace("aaaa", "a", "A") = "AAAA"
     * </pre>
     * 
     * @param source
     * @param subject
     * @param object
     * @return
     * @see #replace(String, String, String, boolean)
     */
    public static String replace(String source, String subject, String object) {
        return replace(source, subject, object, true);
    }

    /**
     * Function is String replace.
     * 
     * <pre>
     * StringUtil.replace(null, "a", "A", false)   = null
     * StringUtil.replace("", "a", "A", false)     = ""
     * StringUtil.replace("1234", "a", "A", false) = "1234"
     * StringUtil.replace("aaaa", "a", "A", false) = "Aaaa"
     * </pre>
     * 
     * @param source
     * @param subject
     * @param object
     * @param doEnd
     * @return
     */
    public static String replace(String source, String subject, String object, boolean doEnd) {
        if (isNullOrEmpty(source)) {
            return source;
        }
        StringBuilder buff = new StringBuilder();
        String nextStr = null;
        int idx;
        while ((idx = source.indexOf(subject)) >= 0) {
            if (nextStr == null) {
                buff = new StringBuilder();
            }
            String preStr = source.substring(0, idx);
            nextStr = source.substring(idx + subject.length(), source.length());
            source = nextStr;
            buff.append(preStr).append(object);
            if (!doEnd) {
                break;
            }
        }
        if (nextStr != null) {
            return buff.append(nextStr).toString();
        }
        return source;
    }

    /**
     * A function that returns a string as a list object by the specified separator.
     * 
     * @param str
     * @param delim
     * @param trim
     * @return
     */
    private static List<String> splitListInternal(String str, String delim, boolean trim) {
        List<String> array = new ArrayList<String>();
        String token;
        int pos;
        do {
            pos = str.indexOf(delim);
            if (pos >= 0) {
                token = str.substring(0, pos);
                str = str.substring(pos + 1);
            } else {
                token = str;
            }
            array.add(trim ? token.trim() : token);
        } while (pos >= 0);
        return array;
    }

    /**
     * A function that returns a string as array by the specified separator.
     * 
     * <pre>
     * String[] rst = StringUtil.split("2016-01-20","-");
     * result rst[0] = 2016, rst[1] = 01, rst[2] = 20
     *
     * String[] rst = StringUtil.split("20160120","-");
     * result rst[0] = 20160120
     * </pre>
     * 
     * @param str
     * @param delim
     * @return array or null
     */
    public static String[] split(String str, String delim) {
        return split(str, delim, true);
    }

    /**
     * A function that returns a string as array by the specified separator.
     * 
     * <pre>
     * String[] rst = StringUtil.split("2016-01-20","-");
     * result rst[0] = 2016, rst[1] = 01, rst[2] = 20
     *
     * String[] rst = StringUtil.split("20160120","-");
     * result rst[0] = 20160120
     * </pre>
     * 
     * @param str
     * @param delim
     * @param trim
     * @return array or null
     */
    public static String[] split(String str, String delim, boolean trim) {
        if (isNullOrEmpty(str)) {
            return new String[0];
        }
        return splitListInternal(str, delim, trim).toArray(new String[0]);
    }

    /**
     * A function that returns a string as an array of the length specified by the
     * specified separator.
     * 
     * <pre>
     * String[] rst = StringUtil.split("2016-01-20","-",2);
     * result rst[0] = 2016, rst[1] = 01
     *
     * String[] rst = StringUtil.split("20160120","-",3);
     * result rst[0] = 20160120, rst[1]="", rst[2]=""
     * </pre>
     * 
     * @param str
     * @param delim
     * @param len
     * @return
     */
    public static String[] split(String str, String delim, int len) {
        return split(str, delim, len, true);
    }

    /**
     * A function that returns a string as an array of the length specified by the
     * specified separator.
     * 
     * <pre>
     * String[] rst = StringUtil.split("2016-01-20","-",2);
     * result rst[0] = 2016, rst[1] = 01
     *
     * String[] rst = StringUtil.split("20160120","-",3);
     * result rst[0] = 20160120, rst[1]="", rst[2]=""
     * </pre>
     * 
     * @param str
     * @param delim
     * @param len
     * @param trim
     * @return
     */
    public static String[] split(String str, String delim, int len, boolean trim) {
        if (len < 0) {
            LOGGER.error("Error");
            len = 0;
        }
        List<String> list = splitListInternal(nvl(str), delim, trim);
        int listSize = list.size();
        if (listSize == len) {
            return list.toArray(new String[0]);
        }
        String[] rtn = new String[len];
        for (int ii = 0; ii < rtn.length; ++ii) {
            if (ii < listSize) {
                rtn[ii] = list.get(ii);
            } else {
                rtn[ii] = EMPTY;
            }
        }
        return rtn;
    }

    /**
     * Fill char to the left by the specified length(cut).
     * 
     * <pre>
     * StringUtil.lpad(null, 4, '0')   = "0000"
     * StringUtil.lpad("", 4, '0')     = "0000"
     * StringUtil.lpad("test", 6, '0') = "00test"
     * StringUtil.lpad("test", 2, '0') = "te"
     * </pre>
     * 
     * @param str
     * @param len
     * @param format
     * @return
     */
    public static String lpad(String str, int len, char format) {
        if (str == null) {
            str = EMPTY;
        }
        int paddingLength = len - str.length();
        if (paddingLength > 0) {
            return repeat(format, paddingLength) + str;
        } else if (paddingLength < 0) {
            return str.substring(0, len);
        }
        return str;
    }

    /**
     * Fill char to the right by the specified length(cut).
     * 
     * <pre>
     * StringUtil.rpad(null, 4, '0')   = "0000"
     * StringUtil.rpad("", 4, '0')     = "0000"
     * StringUtil.rpad("test", 6, '0') = "test00"
     * StringUtil.rpad("test", 2, '0') = "te"
     * </pre>
     * 
     * @param str
     * @param len
     * @param format
     * @return
     */
    public static String rpad(String str, int len, char format) {
        if (str == null) {
            str = EMPTY;
        }
        int paddingLength = len - str.length();
        if (paddingLength > 0) {
            return str + repeat(format, paddingLength);
        } else if (paddingLength < 0) {
            return str.substring(0, len);
        }
        return str;
    }

    /**
     * Fill char with the specified number of bars.
     * 
     * @param format
     * @param len
     * @return
     */
    public static String repeat(char format, int len) {
        char ch[] = new char[len];
        for (int ii = 0; ii < len; ++ii) {
            ch[ii] = format;
        }
        return new String(ch);
    }

    /**
     * Convert String to type double (comma is automatically removed).
     * 
     * <pre>
     * String str = "1,000";
     * double rtn = StringUtil.parseDouble(str, 0.0d);
     * </pre>
     * 
     * @param str
     * @param def
     * @return double
     */
    public static double parseDouble(String str, double def) {
        if (isNullOrEmpty(str)) {
            return def;
        }
        try {
            return Double.parseDouble(removeCommaNotNull(str));
        } catch (Throwable t) {
            return def;
        }
    }

    /**
     * Convert String to type double (comma is automatically removed).
     * 
     * <pre>
     * String str = "1,000";
     * double rtn = StringUtil.parseDouble(str);
     * </pre>
     * 
     * @param str
     * @return double
     */
    public static double parseDouble(String str) {
        return parseDouble(str, 0.0d);
    }

    /**
     * Convert String to type float (comma is automatically removed).
     * 
     * <pre>
     * String str = "1,000";
     * float rtn = StringUtil.parseFloat(str, 0.0f);
     * </pre>
     * 
     * @param str
     * @param def
     * @return float
     */
    public static float parseFloat(String str, float def) {
        if (isNullOrEmpty(str)) {
            return def;
        }
        try {
            return Float.parseFloat(removeCommaNotNull(str));
        } catch (Throwable t) {
            return def;
        }
    }

    /**
     * Convert String to type float (comma is automatically removed).
     * 
     * <pre>
     * String str = "1,000";
     * float rtn = StringUtil.parseFloat(str);
     * </pre>
     * 
     * @param str
     * @return float
     */
    public static float parseFloat(String str) {
        return parseFloat(str, 0.0f);
    }

    /**
     * Convert String to type long (comma is automatically removed).
     * 
     * <pre>
     * String str = "1,000";
     * long rtn = StringUtil.parseLong(str, 0L);
     * </pre>
     * 
     * @param str
     * @param def
     * @return long
     */
    public static long parseLong(String str, long def) {
        if (isNullOrEmpty(str)) {
            return def;
        }
        str = str.trim();
        try {
            return Long.parseLong(removeCommaNotNull(str));
        } catch (Throwable t) {
            return def;
        }
    }

    /**
     * Convert String to type long (comma is automatically removed).
     * 
     * <pre>
     * String str = "1,000";
     * long rtn = StringUtil.parseLong(str);
     * </pre>
     * 
     * @param str
     * @return long
     */
    public static long parseLong(String str) {
        return parseLong(str, 0L);
    }

    /**
     * Convert String to type int (comma is automatically removed).
     * 
     * <pre>
     * String str = "1,000";
     * int rtn = StringUtil.parseInt(str, 0);
     * </pre>
     * 
     * @param str
     * @param def
     * @return int
     */
    public static int parseInt(String str, int def) {
        if (isNullOrEmpty(str)) {
            return def;
        }
        str = str.trim();
        try {
            return Integer.parseInt(removeCommaNotNull(str));
        } catch (Throwable t) {
            return def;
        }
    }

    /**
     * Error inputting null during processing of comma removal function.
     * 
     * @param str
     * @return
     * @see #removeComma(String)
     */
    public static String removeCommaNotNull(String str) {
        boolean comma = false;
        StringBuilder buff = new StringBuilder();
        int strLen = str.length();
        for (int ii = 0; ii < strLen; ++ii) {
            char chr = str.charAt(ii);
            if (chr == ',') {
                if (!comma) {
                    buff = new StringBuilder(strLen);
                    buff.append(str.substring(0, ii));
                    comma = true;
                }
            } else if (comma) {
                buff.append(chr);
            }
        }
        if (comma) {
            return buff.toString();
        }
        return str;
    }

    /**
     * UUID excluding '-'
     * 
     * @return
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        char[] buff = new char[32];
        int idx = -1;
        for (int ii = 0; ii < uuid.length(); ii++) {
            char chr = uuid.charAt(ii);
            if (chr != '-') {
                buff[++idx] = chr;
            }
        }
        return new String(buff);
    }
}
