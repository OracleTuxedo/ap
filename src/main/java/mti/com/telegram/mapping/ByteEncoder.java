package mti.com.telegram.mapping;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.List;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;
import mti.com.telegram.util.TelegramUtil;

public class ByteEncoder {
    private String charSet = "UTF-8";

    private boolean limited = true;

    public void setCharSet(String paramString) {
        this.charSet = paramString;
    }

    public byte[] convertObject2Bytes(Object paramObject, boolean paramBoolean) throws Exception {
        this.limited = paramBoolean;
        ByteBuffer byteBuffer = null;
        if (paramObject == null)
            return null;
        int i = TelegramUtil.getPacketSize(paramObject);
        byteBuffer = ByteBuffer.allocate(i);
        for (Field field : paramObject.getClass().getDeclaredFields()) {
            FIELD fIELD = field.<FIELD>getAnnotation(FIELD.class);
            Object object = field.get(paramObject);
            try {
                if (object == null) {
                    byte b1;
                    DATATYPE dATATYPE;
                    byte b2;
                    int j = 0;
                    byte[] arrayOfByte = null;
                    switch (fIELD.type()) {
                        case STRING:
                            j = fIELD.length();
                            arrayOfByte = new byte[j];
                            for (b1 = 0; b1 < j; b1++)
                                arrayOfByte[b1] = 32;
                            break;
                        case NUMBER:
                            j = fIELD.length();
                            dATATYPE = field.<DATATYPE>getAnnotation(DATATYPE.class);
                            j += dATATYPE.sign_length();
                            j += dATATYPE.point_length();
                            arrayOfByte = new byte[j];
                            for (b2 = 0; b2 < j; b2++)
                                arrayOfByte[b2] = 48;
                            break;
                        case LIST:
                            switch (fIELD.kind()) {
                                case STRING:
                                    arrayOfByte = new byte[8];
                                    for (b2 = 0; b2 < 8; b2++)
                                        arrayOfByte[b2] = 48;
                                    break;
                                case NUMBER:
                                    arrayOfByte = new byte[2];
                                    for (b2 = 0; b2 < 2; b2++)
                                        arrayOfByte[b2] = 48;
                                    break;
                            }
                            break;
                        case VO:
                            object = TelegramUtil.getObjectFromField(field);
                            j = TelegramUtil.getPacketSize(object);
                            arrayOfByte = convertObject2Bytes(object, this.limited);
                            break;
                        case BYTES:
                            j = fIELD.length();
                            arrayOfByte = new byte[j];
                            break;
                    }
                    if (arrayOfByte != null)
                        byteBuffer.put(arrayOfByte);
                } else {
                    byte[] arrayOfByte = appendSerializedField(object, field);
                    if (arrayOfByte != null)
                        byteBuffer.put(arrayOfByte);
                }
            } catch (Exception exception) {
                TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                        exception.toString());
                telegramNestedRuntimeException.setFieldName(field.getName());
                telegramNestedRuntimeException.setFtype(fIELD.type().getTypeName());
                telegramNestedRuntimeException.setObjName(paramObject.getClass().getName());
                telegramNestedRuntimeException.setParser("ByteEncoder");
                telegramNestedRuntimeException.setStackTrace(exception.getStackTrace());
                throw telegramNestedRuntimeException;
            }
        }
        return (byteBuffer != null) ? byteBuffer.array() : null;
    }

    private byte[] appendSerializedField(Object paramObject, Field paramField) throws Exception {
        byte[] arrayOfByte = null;
        Type type = paramField.getGenericType();
        if (TelegramUtil.isPrimitiveType(type)) {
            String str = type.getTypeName();
            if ("int".equals(str) && TelegramUtil.verifyIntegerNumber(paramObject, paramField, "ByteEncoder"))
                arrayOfByte = convertStringToBytes((new Integer(((Integer) paramObject).intValue())).toString(),
                        paramField);
            if ("short".equals(str) && TelegramUtil.verifyShortNumber(paramObject, paramField, "ByteEncoder"))
                arrayOfByte = convertStringToBytes((new Short(((Short) paramObject).shortValue())).toString(),
                        paramField);
            if ("long".equals(str) && TelegramUtil.verifyLongNumber(paramObject, paramField, "ByteEncoder"))
                arrayOfByte = convertStringToBytes((new Long(((Long) paramObject).longValue())).toString(), paramField);
            if ("float".equals(str)) {
                String str1 = TelegramUtil
                        .getStringFromDecimalNumberRound(new Float(((Float) paramObject).floatValue()), paramField);
                if (TelegramUtil.verifyFloatNumber(Float.valueOf(Float.parseFloat(str1)), paramField, "ByteEncoder"))
                    arrayOfByte = convertStringToBytes(str1, paramField);
            }
            if ("double".equals(str)) {
                String str1 = TelegramUtil
                        .getStringFromDecimalNumberRound(new Double(((Double) paramObject).doubleValue()), paramField);
                if (TelegramUtil.verifyDoubleNumber(Double.valueOf(Double.parseDouble(str1)), paramField,
                        "ByteEncoder"))
                    arrayOfByte = convertStringToBytes(str1, paramField);
            }
        } else if (((FIELD) paramField.<FIELD>getAnnotation(FIELD.class)).type() == FieldType.VO) {
            arrayOfByte = convertObject2Bytes(paramObject, this.limited);
        } else if (paramField.getType().isAssignableFrom(Integer.class)) {
            if (TelegramUtil.verifyIntegerNumber(paramObject, paramField, "ByteEncoder")) {
                String str = ((Integer) paramObject).toString();
                arrayOfByte = convertStringToBytes(str, paramField);
            }
        } else if (paramField.getType().isAssignableFrom(Short.class)) {
            if (TelegramUtil.verifyShortNumber(paramObject, paramField, "ByteEncoder")) {
                String str = ((Short) paramObject).toString();
                arrayOfByte = convertStringToBytes(str, paramField);
            }
        } else if (paramField.getType().isAssignableFrom(Long.class)) {
            if (TelegramUtil.verifyLongNumber(paramObject, paramField, "ByteEncoder")) {
                String str = ((Long) paramObject).toString();
                arrayOfByte = convertStringToBytes(str, paramField);
            }
        } else if (paramField.getType().isAssignableFrom(Float.class)) {
            String str = TelegramUtil.getStringFromDecimalNumberRound(paramObject, paramField);
            if (TelegramUtil.verifyFloatNumber(new Float(str), paramField, "ByteEncoder"))
                arrayOfByte = convertStringToBytes(str, paramField);
        } else if (paramField.getType().isAssignableFrom(Double.class)) {
            String str = TelegramUtil.getStringFromDecimalNumberRound(paramObject, paramField);
            if (TelegramUtil.verifyDoubleNumber(new Double(str), paramField, "ByteEncoder"))
                arrayOfByte = convertStringToBytes(str, paramField);
        } else if (paramField.getType().isAssignableFrom(BigDecimal.class)) {
            String str = TelegramUtil.getStringFromDecimalNumberRound(paramObject, paramField);
            if (TelegramUtil.verifyDoubleNumber(Double.valueOf(Double.parseDouble(str)), paramField, "ByteEncoder"))
                arrayOfByte = convertStringToBytes(str, paramField);
        } else if (paramField.getType().isAssignableFrom(String.class)) {
            arrayOfByte = convertStringToBytes(paramObject, paramField);
        } else if (paramField.getType().isAssignableFrom(byte[].class)) {
            arrayOfByte = (byte[]) paramObject;
        } else if (paramField.getType().isAssignableFrom(List.class)) {
            byte b1;
            byte b2;
            FIELD fIELD = paramField.<FIELD>getAnnotation(FIELD.class);
            int i = TelegramUtil.getPacketSize((List) paramObject);
            int j = 0;
            if (paramObject != null)
                j = ((List) paramObject).size();
            ByteBuffer byteBuffer = null;
            switch (fIELD.kind()) {
                case STRING:
                    b1 = 8;
                    byteBuffer = ByteBuffer.allocate(i + b1);
                    byteBuffer.put(TelegramUtil.lpadString2Byte((new Integer(j)).toString(), b1, "0", this.charSet));
                    if (this.limited && (List) paramObject != null) {
                        int k = ((List) paramObject).size();
                        if (k > 10000L) {
                            TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                                    "NumberFormat Exception");
                            telegramNestedRuntimeException
                                    .setMsg("Data Count [" + k + "] is over Maximuim [" + 10000L + "]");
                            throw telegramNestedRuntimeException;
                        }
                    }
                    for (Object object : paramObject)
                        byteBuffer.put(convertObject2Bytes(object, this.limited));
                    break;
                case NUMBER:
                    b2 = 2;
                    byteBuffer = ByteBuffer.allocate(i + b2);
                    byteBuffer.put(TelegramUtil.lpadString2Byte((new Integer(j)).toString(), b2, "0", this.charSet));
                    for (Object object : paramObject)
                        byteBuffer.put(convertObject2Bytes(object, this.limited));
                    break;
            }
            arrayOfByte = byteBuffer.array();
        } else {
            FIELD fIELD = paramField.<FIELD>getAnnotation(FIELD.class);
            switch (fIELD.type()) {
                case VO:
                    arrayOfByte = convertObject2Bytes(paramObject, this.limited);
                    break;
            }
        }
        return arrayOfByte;
    }

    private byte[] convertStringToBytes(Object paramObject, Field paramField) throws Exception {
        byte[] arrayOfByte = null;
        FIELD fIELD = paramField.<FIELD>getAnnotation(FIELD.class);
        if (paramObject == null)
            return arrayOfByte;
        String str = (paramObject != null) ? (String) paramObject : "";
        int i = fIELD.length();
        int j = 0;
        if (fIELD.type() == FieldType.NUMBER) {
            DATATYPE dATATYPE = paramField.<DATATYPE>getAnnotation(DATATYPE.class);
            i += dATATYPE.sign_length();
            i += dATATYPE.point_length();
            j = dATATYPE.decimal();
        }
        int k = (str.getBytes(this.charSet)).length;
        if (k > i) {
            byte[] arrayOfByte1 = str.getBytes(this.charSet);
            arrayOfByte = (new String(arrayOfByte1, 0, i)).getBytes(this.charSet);
        } else {
            switch (fIELD.type()) {
                case STRING:
                    arrayOfByte = TelegramUtil.rpadString2Byte(str, i, " ", this.charSet);
                    break;
                case NUMBER:
                    arrayOfByte = TelegramUtil.lpadString2ByteWithDecimal(str, i, "0", this.charSet, j);
                    break;
            }
        }
        return arrayOfByte;
    }
}
