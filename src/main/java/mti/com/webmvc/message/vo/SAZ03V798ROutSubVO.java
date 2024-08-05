package mti.com.webmvc.message.vo;

import lombok.Data;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.FIELD;

@Data
public class SAZ03V798ROutSubVO {
    @FIELD(kind = Kind.DATA, length = 10, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String dtl_cd_id;

    @FIELD(kind = Kind.DATA, length = 3, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String biz_ctgo_cd;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String grup_cd_id;

    @FIELD(kind = Kind.DATA, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String lang_clcd;

    @FIELD(kind = Kind.DATA, length = 256, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String dtl_cd_nm;

    @FIELD(kind = Kind.DATA, length = 128, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String clss_info_val1;

    @FIELD(kind = Kind.DATA, length = 128, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String clss_info_val2;

    @FIELD(kind = Kind.DATA, length = 128, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String clss_info_val3;

    @FIELD(kind = Kind.DATA, length = 128, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String clss_info_val4;

    @FIELD(kind = Kind.DATA, length = 128, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String clss_info_val5;

    @FIELD(kind = Kind.DATA, length = 128, type = FieldType.STRING, trim = TrimType.RTRIM)
    public String clss_info_val6;

}