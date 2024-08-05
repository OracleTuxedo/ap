package mti.com.webmvc.message.vo;

import lombok.Data;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.FIELD;

@Data
public class SAZ03V798RInVO {
    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String dummy = " ";
}
