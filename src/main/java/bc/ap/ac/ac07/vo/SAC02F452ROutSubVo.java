package bc.ap.ac.ac07.vo;

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
public class SAC02F452ROutSubVo {
    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String auth_date;

    @FIELD(kind = Kind.DATA, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String pmt_date;

    @FIELD(kind = Kind.DATA, length = 19, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String card_no;

    @FIELD(kind = Kind.DATA, length = 6, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String auth_no;

    @FIELD(kind = Kind.DATA, length = 18, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0, sign_length = 0, point_length = 1)
    public long sale_amt;

    @FIELD(kind = Kind.DATA, length = 18, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0, sign_length = 0, point_length = 1)
    public long nonfareamt;

    @FIELD(kind = Kind.DATA, length = 2, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String sale_knd_clcd;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0)
    public long ins_mcnt;

    @FIELD(kind = Kind.DATA, length = 18, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0, sign_length = 0, point_length = 1)
    public long sale_pamt;

    @FIELD(kind = Kind.DATA, length = 18, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0, sign_length = 0, point_length = 1)
    public long sale_svc_fee;

    @FIELD(kind = Kind.DATA, length = 18, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0, sign_length = 0, point_length = 1)
    public long sale_tax;

    @FIELD(kind = Kind.DATA, length = 18, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.LONG, decimal = 0, sign_length = 0, point_length = 1)
    public long pwcw_csh_amt;

    @FIELD(kind = Kind.DATA, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String dcctrans_yn;

}
