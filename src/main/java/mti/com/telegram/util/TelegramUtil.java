package mti.com.telegram.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.NumberFormat;
import java.util.List;
import java.util.regex.Pattern;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.NumberType;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;
import mti.com.utility.ExceptionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TelegramUtil {
    private static final Logger logger = LogManager.getLogger(TelegramUtil.class);

    public static String getStringFromDecimalNumberRound(Object paramObject, Field paramField) throws Exception {
        BigDecimal bigDecimal;
        DATATYPE dATATYPE = paramField.<DATATYPE>getAnnotation(DATATYPE.class);
        int i = dATATYPE.decimal();
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(false);
        numberFormat.setMaximumFractionDigits(i);
        numberFormat.setRoundingMode(RoundingMode.HALF_UP);
        double d = 0.0D;
        switch (dATATYPE.type()) {
            case DATA:
                bigDecimal = (BigDecimal) paramObject;
                d = bigDecimal.doubleValue();
                break;
            case MESSAGE:
                d = ((Double) paramObject).doubleValue();
                break;
            case null:
                d = ((Float) paramObject).doubleValue();
                break;
        }
        return numberFormat.format(d);
    }

    public static String getStringFromDecimalNumberCeil(Object paramObject, Field paramField) throws Exception {
        BigDecimal bigDecimal;
        DATATYPE dATATYPE = paramField.<DATATYPE>getAnnotation(DATATYPE.class);
        int i = dATATYPE.decimal();
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(false);
        numberFormat.setMaximumFractionDigits(i);
        numberFormat.setRoundingMode(RoundingMode.CEILING);
        double d = 0.0D;
        switch (dATATYPE.type()) {
            case DATA:
                bigDecimal = (BigDecimal) paramObject;
                d = bigDecimal.doubleValue();
                break;
            case MESSAGE:
                d = ((Double) paramObject).doubleValue();
                break;
            case null:
                d = ((Float) paramObject).doubleValue();
                break;
        }
        return numberFormat.format(d);
    }

    public static String getStringFromDecimalNumberFloor(Object paramObject, Field paramField) throws Exception {
        BigDecimal bigDecimal;
        DATATYPE dATATYPE = paramField.<DATATYPE>getAnnotation(DATATYPE.class);
        int i = dATATYPE.decimal();
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(false);
        numberFormat.setMaximumFractionDigits(i);
        numberFormat.setRoundingMode(RoundingMode.FLOOR);
        double d = 0.0D;
        switch (dATATYPE.type()) {
            case DATA:
                bigDecimal = (BigDecimal) paramObject;
                d = bigDecimal.doubleValue();
                break;
            case MESSAGE:
                d = ((Double) paramObject).doubleValue();
                break;
            case null:
                d = ((Float) paramObject).doubleValue();
                break;
        }
        return numberFormat.format(d);
    }

    public static byte[] convertStringToBytes(Object paramObject, Field paramField, String paramString)
            throws Exception {
        byte[] arrayOfByte = null;
        FIELD fIELD = paramField.<FIELD>getAnnotation(FIELD.class);
        if (paramObject == null)
            return arrayOfByte;
        String str = (paramObject != null) ? (String) paramObject : "";
        int i = fIELD.length();
        int j = (str.getBytes(paramString)).length;
        if (j > i) {
            byte[] arrayOfByte1 = str.getBytes(paramString);
            arrayOfByte = (new String(arrayOfByte1, 0, i)).getBytes(paramString);
        } else {
            TrimType trimType = fIELD.trim();
            switch (fIELD.type()) {
                case DATA:
                    if (trimType == TrimType.LTRIM) {
                        arrayOfByte = lpadString2Byte(str, i, " ", paramString);
                        break;
                    }
                    arrayOfByte = rpadString2Byte(str, i, " ", paramString);
                    break;
                case MESSAGE:
                    if (trimType == TrimType.LTRIM) {
                        arrayOfByte = lpadString2Byte(str, i, "0", paramString);
                        break;
                    }
                    arrayOfByte = rpadString2Byte(str, i, "0", paramString);
                    break;
            }
        }
        return arrayOfByte;
    }

    public static String convertByteToHexString(byte[] paramArrayOfbyte) {
        if (paramArrayOfbyte == null)
            return null;
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : paramArrayOfbyte) {
            stringBuilder.append(Integer.toString((b & 0xF0) >> 4, 16));
            stringBuilder.append(Integer.toString(b & 0xF, 16));
        }
        return stringBuilder.toString();
    }

    public static byte[] rpadString2Byte(String paramString1, int paramInt, String paramString2, String paramString3)
            throws Exception {
        byte[] arrayOfByte1 = null;
        StringBuffer stringBuffer = new StringBuffer();
        int i = paramInt;
        int j = 0;
        byte[] arrayOfByte2 = null;
        if (paramString1 != null && !"".equals(paramString1)) {
            arrayOfByte2 = paramString1.getBytes(paramString3);
            j = arrayOfByte2.length;
            i -= j;
            if (j <= paramInt) {
                stringBuffer.append(paramString1);
                for (byte b = 0; b < i; b++)
                    stringBuffer.append(paramString2);
            } else {
                byte[] arrayOfByte = cutBytes(arrayOfByte2, 0, paramInt);
                stringBuffer.append(new String(arrayOfByte, paramString3));
            }
        } else {
            for (byte b = 0; b < paramInt; b++)
                stringBuffer.append(paramString2);
        }
        if (stringBuffer.toString() != null)
            arrayOfByte1 = stringBuffer.toString().getBytes(paramString3);
        return arrayOfByte1;
    }

    public static byte[] lpadString2Byte(String paramString1, int paramInt, String paramString2, String paramString3)
            throws Exception {
        byte[] arrayOfByte1 = null;
        StringBuffer stringBuffer = new StringBuffer();
        int i = paramInt;
        int j = 0;
        byte[] arrayOfByte2 = null;
        if (paramString1 != null && !"".equals(paramString1)) {
            arrayOfByte2 = paramString1.getBytes(paramString3);
            j = arrayOfByte2.length;
            i -= j;
            if (j <= paramInt) {
                for (byte b = 0; b < i; b++)
                    stringBuffer.append(paramString2);
                stringBuffer.append(paramString1);
            } else {
                byte[] arrayOfByte = cutBytes(arrayOfByte2, 0, paramInt);
                stringBuffer.append(new String(arrayOfByte, paramString3));
            }
        } else {
            for (byte b = 0; b < paramInt; b++)
                stringBuffer.append(paramString2);
        }
        if (stringBuffer.toString() != null)
            arrayOfByte1 = stringBuffer.toString().getBytes(paramString3);
        return arrayOfByte1;
    }

    public static byte[] lpadString2ByteWithDecimal(String paramString1, int paramInt1, String paramString2,
            String paramString3, int paramInt2) throws Exception {
        byte[] arrayOfByte = null;
        int i = 0;
        int j = paramString1.indexOf(".");
        boolean bool = false;
        if (j > 0) {
            String str = paramString1.substring(j + 1);
            i = paramInt2 - (str.getBytes(paramString3)).length;
        } else {
            i = paramInt2;
            bool = true;
        }
        int k = paramInt1 - (paramString1.getBytes(paramString3)).length - i;
        if (bool && paramInt2 > 0)
            k--;
        char[] arrayOfChar = paramString1.toCharArray();
        if (arrayOfChar[0] == '-') {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("-");
            byte b;
            for (b = 0; b < k; b++)
                stringBuffer.append(paramString2);
            stringBuffer.append(paramString1.substring(1));
            for (b = 0; b < i; b++) {
                if (b == 0 && bool)
                    stringBuffer.append(".");
                stringBuffer.append(paramString2);
            }
            if (stringBuffer.toString() != null)
                arrayOfByte = stringBuffer.toString().getBytes(paramString3);
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            byte b;
            for (b = 0; b < k; b++)
                stringBuffer.append(paramString2);
            stringBuffer.append(paramString1);
            for (b = 0; b < i; b++) {
                if (b == 0 && bool)
                    stringBuffer.append(".");
                stringBuffer.append(paramString2);
            }
            if (stringBuffer.toString() != null)
                arrayOfByte = stringBuffer.toString().getBytes(paramString3);
        }
        return arrayOfByte;
    }

    public static String rightNumberPaddingStringWithDecimal(String paramString1, int paramInt1, String paramString2,
            String paramString3, int paramInt2) throws Exception {
        byte[] arrayOfByte = null;
        int i = 0;
        int j = paramString1.indexOf(".");
        boolean bool = false;
        if (j > 0) {
            String str = paramString1.substring(j + 1);
            i = paramInt2 - (str.getBytes(paramString3)).length;
        } else {
            i = paramInt2;
            bool = true;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(paramString1);
        for (byte b = 0; b < i; b++) {
            if (b == 0 && bool)
                stringBuffer.append(".");
            stringBuffer.append(paramString2);
        }
        if (stringBuffer.toString() != null)
            arrayOfByte = stringBuffer.toString().getBytes(paramString3);
        return new String(arrayOfByte);
    }

    public static int getPacketSize(Object paramObject) throws Exception {
        int i = 0;
        for (Field field : paramObject.getClass().getDeclaredFields()) {
            DATATYPE dATATYPE;
            Object object = field.get(paramObject);
            FIELD fIELD = field.<FIELD>getAnnotation(FIELD.class);
            switch (fIELD.type()) {
                case null:
                    i += getPacketSize((List<Object>) object);
                    switch (fIELD.kind()) {
                        case DATA:
                            i += 8;
                            break;
                        case MESSAGE:
                            i += 2;
                            break;
                    }
                    break;
                case null:
                    if (object == null)
                        object = getObjectFromField(field);
                    i += getPacketSize(object);
                    break;
                case MESSAGE:
                    dATATYPE = field.<DATATYPE>getAnnotation(DATATYPE.class);
                    i += fIELD.length();
                    i += dATATYPE.point_length();
                    i += dATATYPE.sign_length();
                    break;
                default:
                    i += fIELD.length();
                    break;
            }
        }
        return i;
    }

    public static int getPacketSize(Object paramObject, byte[] paramArrayOfbyte, boolean paramBoolean)
            throws Exception {
        int i = 0;
        for (Field field : paramObject.getClass().getDeclaredFields()) {
            String str;
            boolean bool;
            int j;
            byte[] arrayOfByte2;
            DATATYPE dATATYPE;
            Object object = field.get(paramObject);
            FIELD fIELD = field.<FIELD>getAnnotation(FIELD.class);
            byte[] arrayOfByte1 = null;
            switch (fIELD.type()) {
                case null:
                    switch (fIELD.kind()) {
                        case DATA:
                            arrayOfByte1 = cutBytes(paramArrayOfbyte, i, 8);
                            i += true;
                            break;
                        case MESSAGE:
                            arrayOfByte1 = cutBytes(paramArrayOfbyte, i, 2);
                            i += true;
                            break;
                    }
                    str = (new String(arrayOfByte1)).trim();
                    bool = Pattern.matches("^[0-9]*$", str);
                    if (!bool) {
                        TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                                "NumberFormat Exception");
                        telegramNestedRuntimeException.setMsg("Data Count String is [" + str + "]. is not NumberType");
                        throw telegramNestedRuntimeException;
                    }
                    j = Integer.parseInt(str);
                    if (paramBoolean && j > 10000L) {
                        TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                                "NumberFormat Exception");
                        telegramNestedRuntimeException
                                .setMsg("Data Count [" + str + "] is over Maximuim [" + 10000L + "]");
                        throw telegramNestedRuntimeException;
                    }
                    i += getListFieldSizeByCount(field, j);
                    break;
                case null:
                    if (object == null)
                        object = getObjectFromField(field);
                    arrayOfByte2 = cutBytes(paramArrayOfbyte, i, paramArrayOfbyte.length - i);
                    i += getPacketSize(object, arrayOfByte2, paramBoolean);
                    break;
                case MESSAGE:
                    dATATYPE = field.<DATATYPE>getAnnotation(DATATYPE.class);
                    i += fIELD.length();
                    i += dATATYPE.point_length();
                    i += dATATYPE.sign_length();
                    break;
                default:
                    i += fIELD.length();
                    break;
            }
        }
        return i;
    }

    public static int getPacketSize(List<Object> paramList) throws Exception {
        int i = 0;
        if (paramList != null)
            for (Object object : paramList)
                i += getPacketSize(object);
        return i;
    }

    public static int getPacketSize(Object[] paramArrayOfObject) throws Exception {
        int i = 0;
        if (paramArrayOfObject != null)
            for (Object object : paramArrayOfObject)
                i += getPacketSize(object);
        return i;
    }

    public static int getListFieldSizeByCount(Field paramField, int paramInt) throws Exception {
        Type type = paramField.getGenericType();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] arrayOfType = parameterizedType.getActualTypeArguments();
        Class<Object> clazz = (Class) arrayOfType[0];
        Object object = clazz.newInstance();
        int i = getPacketSize(object);
        return i * paramInt;
    }

    public static byte[] cutBytes(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws Exception {
        byte[] arrayOfByte = new byte[paramInt2];
        int i = paramInt1 + paramInt2;
        int j = paramInt1;
        for (byte b = 0; j < i; b++) {
            arrayOfByte[b] = paramArrayOfbyte[j];
            j++;
        }
        return arrayOfByte;
    }

    public static byte[] cutBytes1(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws Exception {
        byte[] arrayOfByte = new byte[paramInt2];
        int i = paramInt1 + paramInt2;
        System.out
                .println("소스[" + paramInt1 + ":" + i + ":" + paramInt2 + "] : [" + new String(paramArrayOfbyte) + "]");
        System.out.println("소스 길이 : " + paramArrayOfbyte.length);
        int j = paramInt1;
        for (byte b = 0; j < i; b++) {
            arrayOfByte[b] = paramArrayOfbyte[j];
            System.out.println("bytes[" + j + "] : " + (new Byte(paramArrayOfbyte[j])).toString());
            j++;
        }
        for (j = 0; j < paramArrayOfbyte.length; j++)
            System.out.print("bytes[" + j + "] : " + (new Byte(paramArrayOfbyte[j])).toString() + ",");
        System.out.println("");
        System.out.println("Result : " + new String(arrayOfByte));
        return arrayOfByte;
    }

    public static String byte2StringTrimmed(byte[] paramArrayOfbyte, String paramString) throws Exception {
        return (paramArrayOfbyte == null) ? null : (new String(paramArrayOfbyte, paramString)).trim();
    }

    public static String getSetterMethodName(String paramString) {
        String str = getAccessorName(paramString);
        StringBuffer stringBuffer = new StringBuffer(paramString.length() + 3);
        stringBuffer.append("set");
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    public static String getAccessorName(String paramString) {
        if (paramString != null && paramString.length() > 0) {
            char[] arrayOfChar = paramString.toCharArray();
            StringBuffer stringBuffer = new StringBuffer(paramString.length());
            stringBuffer.append(Character.toUpperCase(arrayOfChar[0]));
            stringBuffer.append(paramString.substring(1));
            return stringBuffer.toString();
        }
        return "";
    }

    public static void invokeMethod(Method paramMethod, Object paramObject1, Object paramObject2) throws Exception {
        try {
            paramMethod.invoke(paramObject1, new Object[] { paramObject2 });
        } catch (Exception exception) {
            TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                    exception.toString());
            telegramNestedRuntimeException.setMethod(paramMethod.getName());
            telegramNestedRuntimeException.setObjName(paramObject1.getClass().getName());
            throw telegramNestedRuntimeException;
        }
    }

    public static Object getObjectFromField(Field paramField) throws Exception {
        Object object = null;
        Type type = paramField.getGenericType();
        String str = type.getTypeName();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] arrayOfType = parameterizedType.getActualTypeArguments();
            Class<Object> clazz = (Class) arrayOfType[0];
            object = clazz.newInstance();
        } else {
            object = Class.forName(str).newInstance();
        }
        return object;
    }

    public static void setterMethodForNumberInvoke(Object paramObject, Field paramField, String paramString1,
            byte[] paramArrayOfbyte, String paramString2, String paramString3) throws Exception {
        Method method = null;
        Type type = paramField.getGenericType();
        String str1 = type.getTypeName();
        DATATYPE dATATYPE = paramField.<DATATYPE>getAnnotation(DATATYPE.class);
        String str2 = byte2StringTrimmed(paramArrayOfbyte, paramString2);
        double d = Double.parseDouble(str2);
        if (isPrimitiveType(type)) {
            method = paramField.getDeclaringClass().getDeclaredMethod(paramString1, new Class[] { int.class });
            if ("int".equals(str1)
                    && verifyIntegerNumber(Integer.valueOf((new Double(d)).intValue()), paramField, paramString3))
                invokeMethod(method, paramObject, Integer.valueOf((new Double(d)).intValue()));
            method = paramField.getDeclaringClass().getDeclaredMethod(paramString1, new Class[] { short.class });
            if ("short".equals(str1)
                    && verifyShortNumber(Short.valueOf((new Double(d)).shortValue()), paramField, paramString3))
                invokeMethod(method, paramObject, Short.valueOf((new Double(d)).shortValue()));
            method = paramField.getDeclaringClass().getDeclaredMethod(paramString1, new Class[] { long.class });
            if ("long".equals(str1)
                    && verifyLongNumber(Long.valueOf((new Double(d)).longValue()), paramField, paramString3))
                invokeMethod(method, paramObject, Long.valueOf((new Double(d)).longValue()));
            method = paramField.getDeclaringClass().getDeclaredMethod(paramString1, new Class[] { float.class });
            if ("float".equals(str1)
                    && verifyFloatNumber(Float.valueOf((new Double(d)).floatValue()), paramField, paramString3))
                invokeMethod(method, paramObject, Float.valueOf((new Double(d)).floatValue()));
            method = paramField.getDeclaringClass().getDeclaredMethod(paramString1, new Class[] { double.class });
            if ("double".equals(str1)
                    && verifyDoubleNumber(Double.valueOf((new Double(d)).doubleValue()), paramField, paramString3))
                invokeMethod(method, paramObject, Double.valueOf((new Double(d)).doubleValue()));
        } else {
            BigDecimal bigDecimal;
            switch (dATATYPE.type()) {
                case DATA:
                    method = paramField.getDeclaringClass().getDeclaredMethod(paramString1,
                            new Class[] { BigDecimal.class });
                    bigDecimal = BigDecimal.valueOf((new Double(d)).doubleValue());
                    if (verifyDoubleNumber(Double.valueOf(d), paramField, paramString3))
                        invokeMethod(method, paramObject, bigDecimal);
                    break;
                case MESSAGE:
                    method = paramField.getDeclaringClass().getDeclaredMethod(paramString1,
                            new Class[] { Double.class });
                    if (verifyDoubleNumber(Double.valueOf(d), paramField, paramString3))
                        invokeMethod(method, paramObject, new Double(d));
                    break;
                case null:
                    method = paramField.getDeclaringClass().getDeclaredMethod(paramString1,
                            new Class[] { Float.class });
                    if (verifyFloatNumber(new Float((float) d), paramField, paramString3))
                        invokeMethod(method, paramObject, new Float((float) d));
                    break;
                case null:
                    method = paramField.getDeclaringClass().getDeclaredMethod(paramString1,
                            new Class[] { Integer.class });
                    if (verifyIntegerNumber(new Integer((int) d), paramField, paramString3))
                        invokeMethod(method, paramObject, new Integer((int) d));
                    break;
                case null:
                    method = paramField.getDeclaringClass().getDeclaredMethod(paramString1, new Class[] { Long.class });
                    if (verifyLongNumber(new Long((int) d), paramField, paramString3))
                        invokeMethod(method, paramObject, new Long((int) d));
                    break;
                case null:
                    method = paramField.getDeclaringClass().getDeclaredMethod(paramString1,
                            new Class[] { Short.class });
                    if (verifyShortNumber(new Short((short) (int) d), paramField, paramString3))
                        invokeMethod(method, paramObject, new Short((short) (int) d));
                    break;
            }
        }
    }

    public static boolean isPrimitiveType(Type paramType) {
        boolean bool = false;
        String str = paramType.getTypeName();
        if ("int".equals(str) || "short".equals(str) || "long".equals(str) || "float".equals(str)
                || "double".equals(str))
            bool = true;
        return bool;
    }

    public static void viewObjectMethod(Object paramObject) {
        Field[] arrayOfField = paramObject.getClass().getDeclaredFields();
        int i = arrayOfField.length;
        System.out.println("****************** " + paramObject.getClass().getName());
        for (byte b = 0; b < i; b++) {
            Class<?> clazz = arrayOfField[b].getDeclaringClass();
            Method[] arrayOfMethod = clazz.getMethods();
            for (byte b1 = 0; b1 < arrayOfMethod.length; b1++) {
                System.out.println(clazz.getName() + "                    " + arrayOfMethod[b1].getName());
                Type[] arrayOfType = arrayOfMethod[b1].getGenericParameterTypes();
                for (byte b2 = 0; b2 < arrayOfType.length; b2++)
                    System.out.println(clazz.getName() + "                        " + arrayOfType[b2].getTypeName());
            }
        }
        System.out.println("*****************************************************");
    }

    public static String getHexaString(byte[] paramArrayOfbyte) {
        String str = null;
        try {
            StringBuffer stringBuffer = new StringBuffer(paramArrayOfbyte.length * 2);
            for (byte b = 0; b < paramArrayOfbyte.length; b++) {
                String str1 = "0" + Integer.toHexString(0xFF & paramArrayOfbyte[b]);
                stringBuffer.append(str1.substring(str1.length() - 2));
            }
            str = stringBuffer.toString();
        } catch (Exception exception) {
            ExceptionUtil.logPrintStackTrace(logger, exception);
        }
        return str;
    }

    public static String getMacAddress() {
        String str = null;
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(inetAddress);
            if (networkInterface != null) {
                byte[] arrayOfByte = networkInterface.getHardwareAddress();
                str = getHexaString(arrayOfByte);
            }
        } catch (IOException iOException) {
            ExceptionUtil.logPrintStackTrace(logger, iOException);
        }
        return str;
    }

    public static String getIpAddress() {
        String str = null;
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            str = inetAddress.getHostAddress();
        } catch (IOException iOException) {
            ExceptionUtil.logPrintStackTrace(logger, iOException);
        }
        return str;
    }

    public static boolean verifyShortNumber(Object paramObject, Field paramField, String paramString) throws Exception {
        boolean bool = true;
        FIELD fIELD = paramField.<FIELD>getAnnotation(FIELD.class);
        DATATYPE dATATYPE = paramField.<DATATYPE>getAnnotation(DATATYPE.class);
        int i = fIELD.length();
        int j = dATATYPE.sign_length();
        int k = i + j;
        Type type = paramField.getGenericType();
        if (isPrimitiveType(type)) {
            String str = type.getTypeName();
            if ("short".equals(str)) {
                short s = ((Short) paramObject).shortValue();
                String str1 = (new Short(s)).toString();
                int m = str1.length();
                if (s < 0) {
                    if (m > k) {
                        String str2 = "Short Value Verify is Failed";
                        TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                                str2);
                        telegramNestedRuntimeException.setFieldName(paramField.getName());
                        telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                        telegramNestedRuntimeException.setParser(paramString);
                        telegramNestedRuntimeException.setMsg("Value : " + str1 + "[" + k + ":" + m + "]");
                        throw telegramNestedRuntimeException;
                    }
                } else if (m > i) {
                    String str2 = "Short Value Verify is Failed";
                    TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                            str2);
                    telegramNestedRuntimeException.setFieldName(paramField.getName());
                    telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                    telegramNestedRuntimeException.setParser(paramString);
                    telegramNestedRuntimeException.setMsg("Value : " + str1 + "[" + i + ":" + m + "]");
                    throw telegramNestedRuntimeException;
                }
            } else {
                new Exception("Insert Object is not Short Type");
            }
        } else if (paramField.getType().isAssignableFrom(Short.class)) {
            Short short_ = (Short) paramObject;
            String str = short_.toString();
            int m = str.length();
            if (short_.shortValue() < 0) {
                if (m > k) {
                    String str1 = "Short Value Verify is Failed";
                    TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                            str1);
                    telegramNestedRuntimeException.setFieldName(paramField.getName());
                    telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                    telegramNestedRuntimeException.setParser(paramString);
                    telegramNestedRuntimeException.setMsg("Value : " + str + "[" + k + ":" + m + "]");
                    throw telegramNestedRuntimeException;
                }
            } else if (m > i) {
                String str1 = "Short Value Verify is Failed";
                TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                        str1);
                telegramNestedRuntimeException.setFieldName(paramField.getName());
                telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                telegramNestedRuntimeException.setParser(paramString);
                telegramNestedRuntimeException.setMsg("Value : " + str + "[" + i + ":" + m + "]");
                throw telegramNestedRuntimeException;
            }
        } else {
            new Exception("Insert Object is not Short Type");
        }
        return bool;
    }

    public static boolean verifyIntegerNumber(Object paramObject, Field paramField, String paramString)
            throws Exception {
        boolean bool = true;
        FIELD fIELD = paramField.<FIELD>getAnnotation(FIELD.class);
        DATATYPE dATATYPE = paramField.<DATATYPE>getAnnotation(DATATYPE.class);
        int i = fIELD.length();
        int j = dATATYPE.sign_length();
        int k = i + j;
        Type type = paramField.getGenericType();
        if (isPrimitiveType(type)) {
            String str = type.getTypeName();
            if ("int".equals(str)) {
                int m = ((Integer) paramObject).intValue();
                String str1 = (new Integer(m)).toString();
                int n = str1.length();
                if (m < 0) {
                    if (n > k) {
                        String str2 = "Integer Value Verify is Failed";
                        TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                                str2);
                        telegramNestedRuntimeException.setFieldName(paramField.getName());
                        telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                        telegramNestedRuntimeException.setParser(paramString);
                        telegramNestedRuntimeException.setMsg("Value : " + str1 + "[" + k + ":" + n + "]");
                        throw telegramNestedRuntimeException;
                    }
                } else if (n > i) {
                    String str2 = "Integer Value Verify is Failed";
                    TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                            str2);
                    telegramNestedRuntimeException.setFieldName(paramField.getName());
                    telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                    telegramNestedRuntimeException.setParser(paramString);
                    telegramNestedRuntimeException.setMsg("Value : " + str1 + "[" + i + ":" + n + "]");
                    throw telegramNestedRuntimeException;
                }
            } else {
                new Exception("Insert Object is not Integer Type");
            }
        } else if (paramField.getType().isAssignableFrom(Integer.class)) {
            Integer integer = (Integer) paramObject;
            String str = integer.toString();
            int m = str.length();
            if (integer.intValue() < 0) {
                if (m > k) {
                    String str1 = "Integer Value Verify is Failed";
                    TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                            str1);
                    telegramNestedRuntimeException.setFieldName(paramField.getName());
                    telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                    telegramNestedRuntimeException.setParser(paramString);
                    telegramNestedRuntimeException.setMsg("Value : " + str + "[" + k + ":" + m + "]");
                    throw telegramNestedRuntimeException;
                }
            } else if (m > i) {
                String str1 = "Integer Value Verify is Failed";
                TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                        str1);
                telegramNestedRuntimeException.setFieldName(paramField.getName());
                telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                telegramNestedRuntimeException.setParser(paramString);
                telegramNestedRuntimeException.setMsg("Value : " + str + "[" + i + ":" + m + "]");
                throw telegramNestedRuntimeException;
            }
        } else {
            new Exception("Insert Object is not Integer Type");
        }
        return bool;
    }

    public static boolean verifyLongNumber(Object paramObject, Field paramField, String paramString) throws Exception {
        boolean bool = true;
        FIELD fIELD = paramField.<FIELD>getAnnotation(FIELD.class);
        DATATYPE dATATYPE = paramField.<DATATYPE>getAnnotation(DATATYPE.class);
        int i = fIELD.length();
        int j = dATATYPE.sign_length();
        int k = i + j;
        Type type = paramField.getGenericType();
        if (isPrimitiveType(type)) {
            String str = type.getTypeName();
            if ("long".equals(str)) {
                long l = ((Long) paramObject).longValue();
                String str1 = (new Long(l)).toString();
                int m = str1.length();
                if (l < 0L) {
                    if (m > k) {
                        String str2 = "Long Value Verify is Failed";
                        TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                                str2);
                        telegramNestedRuntimeException.setFieldName(paramField.getName());
                        telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                        telegramNestedRuntimeException.setParser(paramString);
                        telegramNestedRuntimeException.setMsg("Value : " + str1 + "[" + k + ":" + m + "]");
                        throw telegramNestedRuntimeException;
                    }
                } else if (m > i) {
                    String str2 = "Long Value Verify is Failed";
                    TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                            str2);
                    telegramNestedRuntimeException.setFieldName(paramField.getName());
                    telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                    telegramNestedRuntimeException.setParser(paramString);
                    telegramNestedRuntimeException.setMsg("Value : " + str1 + "[" + i + ":" + m + "]");
                    throw telegramNestedRuntimeException;
                }
            } else {
                new Exception("Insert Object is not Long Type");
            }
        } else if (paramField.getType().isAssignableFrom(Long.class)) {
            Long long_ = (Long) paramObject;
            String str = long_.toString();
            int m = str.length();
            if (long_.longValue() < 0L) {
                if (m > k) {
                    String str1 = "Long Value Verify is Failed";
                    TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                            str1);
                    telegramNestedRuntimeException.setFieldName(paramField.getName());
                    telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                    telegramNestedRuntimeException.setParser(paramString);
                    telegramNestedRuntimeException.setMsg("Value : " + str + "[" + k + ":" + m + "]");
                    throw telegramNestedRuntimeException;
                }
            } else if (m > i) {
                String str1 = "Long Value Verify is Failed";
                TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                        str1);
                telegramNestedRuntimeException.setFieldName(paramField.getName());
                telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                telegramNestedRuntimeException.setParser(paramString);
                telegramNestedRuntimeException.setMsg("Value : " + str + "[" + i + ":" + m + "]");
                throw telegramNestedRuntimeException;
            }
        } else {
            new Exception("Insert Object is not Long Type");
        }
        return bool;
    }

    public static boolean verifyFloatNumber(Object paramObject, Field paramField, String paramString) throws Exception {
        boolean bool = true;
        FIELD fIELD = paramField.<FIELD>getAnnotation(FIELD.class);
        DATATYPE dATATYPE = paramField.<DATATYPE>getAnnotation(DATATYPE.class);
        int i = fIELD.length();
        int j = dATATYPE.decimal();
        int k = dATATYPE.sign_length();
        int m = dATATYPE.point_length();
        int n = i + k + m;
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(false);
        numberFormat.setMaximumFractionDigits(j);
        Type type = paramField.getGenericType();
        if (isPrimitiveType(type)) {
            String str = type.getTypeName();
            if ("float".equals(str)) {
                float f = ((Float) paramObject).floatValue();
                String str1 = numberFormat.format((new Float(f)).doubleValue());
                str1 = rightNumberPaddingStringWithDecimal(str1, n, "0", "UTF-8", j);
                if (!verifyDecimalStringValue(str1, j)) {
                    String str2 = "Float Value Verify is Failed";
                    TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                            str2);
                    telegramNestedRuntimeException.setFieldName(paramField.getName());
                    telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                    telegramNestedRuntimeException.setParser(paramString);
                    telegramNestedRuntimeException.setMsg("decimal is not correct : " + str1 + " decimal[" + j + "]");
                    throw telegramNestedRuntimeException;
                }
                int i1 = str1.length();
                if (f < 0.0F) {
                    if (i1 > n) {
                        String str2 = "Float Value Verify is Failed";
                        TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                                str2);
                        telegramNestedRuntimeException.setFieldName(paramField.getName());
                        telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                        telegramNestedRuntimeException.setParser(paramString);
                        telegramNestedRuntimeException.setMsg("Value : " + str1 + "[" + n + ":" + i1 + "]");
                        throw telegramNestedRuntimeException;
                    }
                } else if (i1 > i + m) {
                    String str2 = "Float Value Verify is Failed";
                    TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                            str2);
                    telegramNestedRuntimeException.setFieldName(paramField.getName());
                    telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                    telegramNestedRuntimeException.setParser(paramString);
                    telegramNestedRuntimeException.setMsg("Value : " + str1 + "[" + (i + m) + ":" + i1 + "]");
                    throw telegramNestedRuntimeException;
                }
            } else {
                new Exception("Insert Object is not Float Type");
            }
        } else if (paramField.getType().isAssignableFrom(Float.class)) {
            Float float_ = (Float) paramObject;
            String str = numberFormat.format(float_.doubleValue());
            str = rightNumberPaddingStringWithDecimal(str, n, "0", "UTF-8", j);
            if (!verifyDecimalStringValue(str, j)) {
                String str1 = "Float Value Verify is Failed";
                TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                        str1);
                telegramNestedRuntimeException.setFieldName(paramField.getName());
                telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                telegramNestedRuntimeException.setParser(paramString);
                telegramNestedRuntimeException.setMsg("decimal is not correct : " + str + " decimal[" + j + "]");
                throw telegramNestedRuntimeException;
            }
            int i1 = str.length();
            if (float_.floatValue() < 0.0F) {
                if (i1 > n) {
                    String str1 = "Float Value Verify is Failed";
                    TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                            str1);
                    telegramNestedRuntimeException.setFieldName(paramField.getName());
                    telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                    telegramNestedRuntimeException.setParser(paramString);
                    telegramNestedRuntimeException.setMsg("Value : " + str + "[" + n + ":" + i1 + "]");
                    throw telegramNestedRuntimeException;
                }
            } else if (i1 > i + m) {
                String str1 = "Float Value Verify is Failed";
                TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                        str1);
                telegramNestedRuntimeException.setFieldName(paramField.getName());
                telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                telegramNestedRuntimeException.setParser(paramString);
                telegramNestedRuntimeException.setMsg("Value : " + str + "[" + (i + m) + ":" + i1 + "]");
                throw telegramNestedRuntimeException;
            }
        } else {
            new Exception("Insert Object is not Float Type");
        }
        return bool;
    }

    public static boolean verifyDoubleNumber(Object paramObject, Field paramField, String paramString)
            throws Exception {
        boolean bool = true;
        FIELD fIELD = paramField.<FIELD>getAnnotation(FIELD.class);
        DATATYPE dATATYPE = paramField.<DATATYPE>getAnnotation(DATATYPE.class);
        int i = fIELD.length();
        int j = dATATYPE.decimal();
        int k = dATATYPE.sign_length();
        int m = dATATYPE.point_length();
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(false);
        numberFormat.setMaximumFractionDigits(j);
        int n = i + k + m;
        Type type = paramField.getGenericType();
        if (isPrimitiveType(type)) {
            String str = type.getTypeName();
            if ("double".equals(str)) {
                double d = ((Double) paramObject).doubleValue();
                String str1 = numberFormat.format(d);
                str1 = rightNumberPaddingStringWithDecimal(str1, n, "0", "UTF-8", j);
                if (!verifyDecimalStringValue(str1, j)) {
                    String str2 = "Double Value Verify is Failed";
                    TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                            str2);
                    telegramNestedRuntimeException.setFieldName(paramField.getName());
                    telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                    telegramNestedRuntimeException.setParser(paramString);
                    telegramNestedRuntimeException.setMsg("decimal is not correct : " + str1 + " decimal[" + j + "]");
                    throw telegramNestedRuntimeException;
                }
                int i1 = str1.length();
                if (d < 0.0D) {
                    if (i1 > n) {
                        String str2 = "Double Value Verify is Failed";
                        TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                                str2);
                        telegramNestedRuntimeException.setFieldName(paramField.getName());
                        telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                        telegramNestedRuntimeException.setParser(paramString);
                        telegramNestedRuntimeException.setMsg("Value : " + str1 + "[" + n + ":" + i1 + "]");
                        throw telegramNestedRuntimeException;
                    }
                } else if (i1 > i + m) {
                    String str2 = "Double Value Verify is Failed";
                    TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                            str2);
                    telegramNestedRuntimeException.setFieldName(paramField.getName());
                    telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                    telegramNestedRuntimeException.setParser(paramString);
                    telegramNestedRuntimeException.setMsg("Value : " + str1 + "[" + (i + m) + ":" + i1 + "]");
                    throw telegramNestedRuntimeException;
                }
            } else {
                new Exception("Insert Object is not Double Type");
            }
        } else if (paramField.getType().isAssignableFrom(Double.class)) {
            Double double_ = (Double) paramObject;
            String str = numberFormat.format(double_);
            str = rightNumberPaddingStringWithDecimal(str, n, "0", "UTF-8", j);
            if (!verifyDecimalStringValue(str, j)) {
                String str1 = "Double Value Verify is Failed";
                TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                        str1);
                telegramNestedRuntimeException.setFieldName(paramField.getName());
                telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                telegramNestedRuntimeException.setParser(paramString);
                telegramNestedRuntimeException.setMsg("decimal is not correct : " + str + " decimal[" + j + "]");
                throw telegramNestedRuntimeException;
            }
            int i1 = str.length();
            if (double_.doubleValue() < 0.0D) {
                if (i1 > n) {
                    String str1 = "Double Value Verify is Failed";
                    TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                            str1);
                    telegramNestedRuntimeException.setFieldName(paramField.getName());
                    telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                    telegramNestedRuntimeException.setParser(paramString);
                    telegramNestedRuntimeException.setMsg("Value : " + str + "[" + n + ":" + i1 + "]");
                    throw telegramNestedRuntimeException;
                }
            } else if (i1 > i + m) {
                String str1 = "Double Value Verify is Failed";
                TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                        str1);
                telegramNestedRuntimeException.setFieldName(paramField.getName());
                telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                telegramNestedRuntimeException.setParser(paramString);
                telegramNestedRuntimeException.setMsg("Value : " + str + "[" + (i + m) + ":" + i1 + "]");
                throw telegramNestedRuntimeException;
            }
        } else {
            new Exception("Insert Object is not Double Type");
        }
        return bool;
    }

    public static boolean verifyDecimalStringValue(String paramString, int paramInt) {
        boolean bool = false;
        if (!paramString.contains(".")) {
            bool = true;
        } else {
            String str = paramString.substring(paramString.indexOf(".") + 1);
            if (paramInt >= str.length())
                bool = true;
        }
        return bool;
    }

    public static String addLeftZeroPaddingByLength(String paramString, int paramInt) {
        String str = "";
        if (paramString == null) {
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b = 0; b < paramInt; b++)
                stringBuffer.append("0");
            str = stringBuffer.toString();
        } else if (paramString.length() > paramInt) {
            str = paramString.substring(paramString.length() - paramInt, paramString.length());
        } else if (paramString.length() == paramInt) {
            str = paramString;
        } else {
            int i = paramInt - paramString.length();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b = 0; b < i; b++)
                stringBuffer.append("0");
            stringBuffer.append(paramString);
            str = stringBuffer.toString();
        }
        return str;
    }

    public static String addRightZeroPaddingByLength(String paramString, int paramInt) {
        String str = "";
        if (paramString == null) {
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b = 0; b < paramInt; b++)
                stringBuffer.append("0");
            str = stringBuffer.toString();
        } else if (paramString.length() > paramInt) {
            str = paramString.substring(0, paramInt);
        } else if (paramString.length() == paramInt) {
            str = paramString;
        } else {
            int i = paramInt - paramString.length();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(paramString);
            for (byte b = 0; b < i; b++)
                stringBuffer.append("0");
            str = stringBuffer.toString();
        }
        return str;
    }

    public static void main(String[] paramArrayOfString) {
        String str = "abcd";
        System.out.println(addRightZeroPaddingByLength(str, 5));
    }
}
