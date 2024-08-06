package mti.com.telegram.vo;

import java.util.ArrayList;
import java.util.List;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.NumberType;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;

public class TelegramDataList {
    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String dataType;

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.INT, decimal = 0)
    public int length;

    @FIELD(kind = Kind.DATA, length = 21, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String reserved;

    @FIELD(kind = Kind.DATA, length = 0, type = FieldType.LIST, trim = TrimType.NONE)
    public List<?> data = new ArrayList();

    public String getDataType() {
        return this.dataType;
    }

    public void setDataType(String paramString) {
        this.dataType = paramString;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int paramInt) {
        this.length = paramInt;
    }

    public String getReserved() {
        return this.reserved;
    }

    public void setReserved(String paramString) {
        this.reserved = paramString;
    }

    public List<?> getData() {
        return this.data;
    }

    public void setData(List<?> paramList) {
        this.data = paramList;
    }

    public String toString() {
        return "TelegramDataList [dataType=" + this.dataType + ", length=" + this.length + ", reserved=" + this.reserved
                + ", data=" + this.data + "]";
    }
}
