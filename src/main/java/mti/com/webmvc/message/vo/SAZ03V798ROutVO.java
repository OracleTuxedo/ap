package mti.com.webmvc.message.vo;

import java.util.List;

import lombok.Data;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.FIELD;

@Data
public class SAZ03V798ROutVO {
    @FIELD(kind = Kind.DATA, length = 0, type = FieldType.LIST, trim = TrimType.LTRIM)
    public List<SAZ03V798ROutSubVO> list;

}
