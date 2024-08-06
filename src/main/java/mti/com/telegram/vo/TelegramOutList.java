package mti.com.telegram.vo;

import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.FIELD;

public class TelegramOutList {
    @FIELD(kind = Kind.DATA, length = 0, type = FieldType.VO, trim = TrimType.NONE)
    public TelegramHeader header;

    @FIELD(kind = Kind.MESSAGE, length = 0, type = FieldType.VO, trim = TrimType.NONE)
    public TelegramMessage message;

    @FIELD(kind = Kind.DATA, length = 0, type = FieldType.VO, trim = TrimType.NONE)
    public TelegramDataOutList data;

    @FIELD(kind = Kind.TAIL, length = 2, type = FieldType.VO, trim = TrimType.NONE)
    public TelegramTail tail;

    public TelegramHeader getHeader() {
        return this.header;
    }

    public void setHeader(TelegramHeader paramTelegramHeader) {
        this.header = paramTelegramHeader;
    }

    public TelegramMessage getMessage() {
        return this.message;
    }

    public void setMessage(TelegramMessage paramTelegramMessage) {
        this.message = paramTelegramMessage;
    }

    public TelegramDataOutList getData() {
        return this.data;
    }

    public void setData(TelegramDataOutList paramTelegramDataOutList) {
        this.data = paramTelegramDataOutList;
    }

    public TelegramTail getTail() {
        return this.tail;
    }

    public void setTail(TelegramTail paramTelegramTail) {
        this.tail = paramTelegramTail;
    }

    public String toString() {
        return "TelegramOutList [header=" + this.header + ", message=" + this.message + ", data=" + this.data
                + ", tail=" + this.tail + "]";
    }
}

/*
 * Location: E:\Cranium
 * MAAS\Apps\mti_web\mti_web\src\main\webapp\WEB-INF\lib\mti_telegram.jar!\mti\
 * com\telegram\vo\TelegramOutList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version: 1.1.3
 */