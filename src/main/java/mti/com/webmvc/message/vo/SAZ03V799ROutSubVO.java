package mti.com.webmvc.message.vo;

import lombok.Data;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.FIELD;

@Data
public class SAZ03V799ROutSubVO {
    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String msg_clcd;

    @FIELD(kind = Kind.DATA, length = 100, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String msg_id;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String lang_clcd;

    @FIELD(kind = Kind.DATA, length = 256, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String msg_nm;

}