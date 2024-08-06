package mti.com.telegram.mapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;
import mti.com.telegram.util.TelegramUtil;

public class ByteDecoder {
    private String charSet = "UTF-8";

    private boolean limited = true;

    public void setCharSet(String paramString) {
        this.charSet = paramString;
    }

    public Object convertBytes2Object(byte[] paramArrayOfbyte, Object paramObject, boolean paramBoolean)
            throws Exception {
        try {
            this.limited = paramBoolean;
            parseBytes(paramArrayOfbyte, paramObject);
        } catch (Exception exception) {
            throw exception;
        }
        return paramObject;
    }

    public Object convertBytes2Object(byte[] paramArrayOfbyte, Object paramObject, int paramInt) throws Exception {
        try {
            int i = paramArrayOfbyte.length - paramInt;
            byte[] arrayOfByte = TelegramUtil.cutBytes(paramArrayOfbyte, paramInt, i);
            parseBytes(arrayOfByte, paramObject);
        } catch (Exception exception) {
            throw exception;
        }
        return paramObject;
    }

    private void parseBytes(byte[] paramArrayOfbyte, Object paramObject) throws Exception {
        int i = 0;
        if (paramArrayOfbyte == null) {
            TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                    "Byte input is null");
            telegramNestedRuntimeException.setParser("ByteDecoder");
            throw telegramNestedRuntimeException;
        }
        if (paramObject == null) {
            TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                    "Input Object is null");
            telegramNestedRuntimeException.setParser("ByteDecoder");
            throw telegramNestedRuntimeException;
        }
        Field[] arrayOfField = paramObject.getClass().getDeclaredFields();
        int j = arrayOfField.length;
        for (byte b = 0; b < j; b++) {
            FIELD fIELD = arrayOfField[b].<FIELD>getAnnotation(FIELD.class);
            int k = fIELD.length();
            String str = arrayOfField[b].getName();
            try {
                byte[] arrayOfByte1;
                String str1;
                String str2;
                Method method1;
                DATATYPE dATATYPE;
                byte[] arrayOfByte2;
                String str3;
                int m;
                String str4;
                Method method2;
                byte[] arrayOfByte3;
                Object object;
                String str5;
                byte[] arrayOfByte4;
                boolean bool;
                int n;
                ArrayList arrayList1;
                byte[] arrayOfByte5;
                byte b1;
                byte[] arrayOfByte6;
                String str6;
                ArrayList arrayList2;
                Method method3;
                byte b2;
                byte[] arrayOfByte7;
                String str7;
                Method method4;
                switch (fIELD.type()) {
                    case STRING:
                        arrayOfByte1 = TelegramUtil.cutBytes(paramArrayOfbyte, i, k);
                        str1 = TelegramUtil.byte2StringTrimmed(arrayOfByte1, this.charSet);
                        str2 = TelegramUtil.getSetterMethodName(str);
                        method1 = arrayOfField[b].getDeclaringClass().getDeclaredMethod(str2,
                                new Class[] { String.class });
                        TelegramUtil.invokeMethod(method1, paramObject, str1);
                        i += k;
                        break;
                    case NUMBER:
                        dATATYPE = arrayOfField[b].<DATATYPE>getAnnotation(DATATYPE.class);
                        k += dATATYPE.sign_length();
                        k += dATATYPE.point_length();
                        arrayOfByte2 = TelegramUtil.cutBytes(paramArrayOfbyte, i, k);
                        str3 = TelegramUtil.getSetterMethodName(str);
                        TelegramUtil.setterMethodForNumberInvoke(paramObject, arrayOfField[b], str3, arrayOfByte2,
                                this.charSet, "ByteDecoder");
                        i += k;
                        break;
                    case LIST:
                        m = 0;
                        str4 = TelegramUtil.getSetterMethodName(str);
                        method2 = arrayOfField[b].getDeclaringClass().getDeclaredMethod(str4,
                                new Class[] { List.class });
                        switch (fIELD.kind()) {
                            case STRING:
                                arrayOfByte3 = TelegramUtil.cutBytes(paramArrayOfbyte, i, 8);
                                str5 = (new String(arrayOfByte3)).trim();
                                bool = Pattern.matches("^[0-9]*$", str5);
                                if (!bool) {
                                    TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                                            "NumberFormat Exception");
                                    telegramNestedRuntimeException.setFieldName(str);
                                    telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                                    telegramNestedRuntimeException.setObjName(paramObject.getClass().getName());
                                    telegramNestedRuntimeException
                                            .setMsg("Data Count String is [" + str5 + "]. is not NumberType");
                                    throw telegramNestedRuntimeException;
                                }
                                m = (new Integer(str5)).intValue();
                                i += 8;
                                arrayList1 = new ArrayList();
                                for (b1 = 0; b1 < m; b1++) {
                                    Type type = arrayOfField[b].getGenericType();
                                    ParameterizedType parameterizedType = (ParameterizedType) type;
                                    Type[] arrayOfType = parameterizedType.getActualTypeArguments();
                                    Class<Object> clazz = (Class) arrayOfType[0];
                                    Object object1 = clazz.newInstance();
                                    int i1 = TelegramUtil.getPacketSize(object1);
                                    byte[] arrayOfByte = TelegramUtil.cutBytes(paramArrayOfbyte, i, i1);
                                    parseBytes(arrayOfByte, object1);
                                    arrayList1.add(object1);
                                    i += i1;
                                    if (paramArrayOfbyte != null && i > paramArrayOfbyte.length) {
                                        TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                                                "Data List length is abnormal");
                                        telegramNestedRuntimeException.setParser("ByteDecoder");
                                        throw telegramNestedRuntimeException;
                                    }
                                }
                                TelegramUtil.invokeMethod(method2, paramObject, arrayList1);
                                break;
                            case NUMBER:
                                arrayOfByte6 = TelegramUtil.cutBytes(paramArrayOfbyte, i, 2);
                                m = (new Integer(new String(arrayOfByte6))).intValue();
                                i += 2;
                                arrayList2 = new ArrayList();
                                for (b2 = 0; b2 < m; b2++) {
                                    Type type = arrayOfField[b].getGenericType();
                                    ParameterizedType parameterizedType = (ParameterizedType) type;
                                    Type[] arrayOfType = parameterizedType.getActualTypeArguments();
                                    Class<Object> clazz = (Class) arrayOfType[0];
                                    Object object1 = clazz.newInstance();
                                    int i1 = TelegramUtil.getPacketSize(object1);
                                    byte[] arrayOfByte = TelegramUtil.cutBytes(paramArrayOfbyte, i, i1);
                                    parseBytes(arrayOfByte, object1);
                                    arrayList2.add(object1);
                                    i += i1;
                                }
                                TelegramUtil.invokeMethod(method2, paramObject, arrayList2);
                                break;
                        }
                        break;
                    case VO:
                        object = arrayOfField[b].get(paramObject);
                        if (object == null)
                            object = TelegramUtil.getObjectFromField(arrayOfField[b]);
                        arrayOfByte4 = TelegramUtil.cutBytes(paramArrayOfbyte, i, paramArrayOfbyte.length - i);
                        n = TelegramUtil.getPacketSize(object, arrayOfByte4, this.limited);
                        arrayOfByte5 = TelegramUtil.cutBytes(paramArrayOfbyte, i, n);
                        parseBytes(arrayOfByte5, object);
                        str6 = TelegramUtil.getSetterMethodName(str);
                        arrayList2 = null;
                        if (paramObject instanceof mti.com.telegram.vo.TelegramDataOut) {
                            method3 = arrayOfField[b].getDeclaringClass().getDeclaredMethod(str6,
                                    new Class[] { Object.class });
                        } else if (paramObject instanceof mti.com.telegram.vo.TelegramDataOutList) {
                            method3 = arrayOfField[b].getDeclaringClass().getDeclaredMethod(str6,
                                    new Class[] { List.class });
                        } else {
                            method3 = arrayOfField[b].getDeclaringClass().getDeclaredMethod(str6,
                                    new Class[] { object.getClass() });
                        }
                        TelegramUtil.invokeMethod(method3, paramObject, object);
                        i += n;
                        break;
                    case BYTES:
                        arrayOfByte7 = TelegramUtil.cutBytes(paramArrayOfbyte, i, k);
                        str7 = TelegramUtil.getSetterMethodName(str);
                        method4 = arrayOfField[b].getDeclaringClass().getDeclaredMethod(str7,
                                new Class[] { byte[].class });
                        TelegramUtil.invokeMethod(method4, paramObject, arrayOfByte7);
                        i += k;
                        break;
                }
            } catch (Exception exception) {
                TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                        exception.toString());
                telegramNestedRuntimeException.setFieldName(str);
                telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                telegramNestedRuntimeException.setObjName(paramObject.getClass().getName());
                telegramNestedRuntimeException.setPointer(i);
                telegramNestedRuntimeException.setMsg(exception.toString());
                telegramNestedRuntimeException.setParser("ByteDecoder");
                telegramNestedRuntimeException.setStackTrace(exception.getStackTrace());
                throw telegramNestedRuntimeException;
            }
        }
    }
}
