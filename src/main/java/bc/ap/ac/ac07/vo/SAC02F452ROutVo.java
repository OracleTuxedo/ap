package bc.ap.ac.ac07.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.NumberType;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SAC02F452ROutVo {
    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.INT, decimal = 0)
    private long tot_cnt;

    @FIELD(kind = Kind.DATA, length = 0, type = FieldType.LIST, trim = TrimType.LTRIM)
    private final List<SAC02F452ROutSubVo> subVo = new ArrayList<>();
}
