package mti.com.telegram.vo;

import lombok.Builder;
import lombok.Data;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.FIELD;

@Builder
@Data
public class TelegramSubMessage {
    @FIELD(kind = Kind.MESSAGE, length = 100, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String subMsg;
}
