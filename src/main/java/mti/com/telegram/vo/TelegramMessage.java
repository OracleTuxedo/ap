package mti.com.telegram.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.NumberType;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;

@Builder
@Data
public class TelegramMessage {
    @FIELD(kind = Kind.MESSAGE, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String kind;

    @FIELD(kind = Kind.MESSAGE, length = 8, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.INT, decimal = 0)
    private int length;

    @FIELD(kind = Kind.MESSAGE, length = 21, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String reserved;

    @FIELD(kind = Kind.MESSAGE, length = 1, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.INT, decimal = 0)
    private int msgAttr;

    @FIELD(kind = Kind.MESSAGE, length = 1, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.INT, decimal = 0)
    private int msgAlm;

    @FIELD(kind = Kind.MESSAGE, length = 200, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String message;

    @FIELD(kind = Kind.MESSAGE, length = 100, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String error_field_name;

    @FIELD(kind = Kind.MESSAGE, length = 100, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String error_info;

    @FIELD(kind = Kind.MESSAGE, length = 0, type = FieldType.LIST, trim = TrimType.NONE)
    private final List<TelegramSubMessage> list = new ArrayList<>();

}