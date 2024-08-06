package mti.com.telegram.vo;

import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.FIELD;

public class TelegramTail {
    @FIELD(kind = Kind.TAIL, length = 2, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String tail;

    public String getTail() {
        return this.tail;
    }

    public void setTail(String paramString) {
        this.tail = paramString;
    }

    public String toString() {
        return "TelegramTail [tail=" + this.tail + "]";
    }
}
