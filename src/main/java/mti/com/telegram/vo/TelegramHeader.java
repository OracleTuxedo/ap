package mti.com.telegram.vo;

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
public class TelegramHeader {
    @FIELD(kind = Kind.HEADER, length = 8, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.INT, decimal = 0)
    private int msg_len;

    @FIELD(kind = Kind.HEADER, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String gid_sysname;

    @FIELD(kind = Kind.HEADER, length = 8, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String gid_yyyyymmdd;

    @FIELD(kind = Kind.HEADER, length = 6, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String gid_hhmmss;

    @FIELD(kind = Kind.HEADER, length = 3, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String gid_seq;

    @FIELD(kind = Kind.HEADER, length = 5, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String gid_pid;

    @FIELD(kind = Kind.HEADER, length = 2, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String gid_stat;

    @FIELD(kind = Kind.HEADER, length = 24, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String tx_code;

    @FIELD(kind = Kind.HEADER, length = 4, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String inst_no;

    @FIELD(kind = Kind.HEADER, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String send_rspn_type;

    @FIELD(kind = Kind.HEADER, length = 24, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String rspn_svc_code;

    @FIELD(kind = Kind.HEADER, length = 32, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String ori_global_id;

    @FIELD(kind = Kind.HEADER, length = 20, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String ori_send_time;

    @FIELD(kind = Kind.HEADER, length = 10, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String chnl_id;

    @FIELD(kind = Kind.HEADER, length = 32, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String client_ip_no;

    @FIELD(kind = Kind.HEADER, length = 12, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String client_mac;

    @FIELD(kind = Kind.HEADER, length = 11, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String scrn_id;

    @FIELD(kind = Kind.HEADER, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String scrn_lock_yn;

    @FIELD(kind = Kind.HEADER, length = 15, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String op_id;

    @FIELD(kind = Kind.HEADER, length = 1, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.INT, decimal = 0)
    private int xa_begin_flag;

    @FIELD(kind = Kind.HEADER, length = 20, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String send_time;

    @FIELD(kind = Kind.HEADER, length = 20, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String rspn_time;

    @FIELD(kind = Kind.HEADER, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String sync_type;

    @FIELD(kind = Kind.HEADER, length = 1, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String async_rspn_yn;

    @FIELD(kind = Kind.HEADER, length = 1, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.INT, decimal = 0)
    private int call_depth;

    @FIELD(kind = Kind.HEADER, length = 3, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.INT, decimal = 0)
    private int msg_count_no;

    @FIELD(kind = Kind.HEADER, length = 1, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.INT, decimal = 0)
    private int ttl_use_flag;

    @FIELD(kind = Kind.HEADER, length = 6, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String ttl_from_time;

    @FIELD(kind = Kind.HEADER, length = 3, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.INT, decimal = 0)
    private int ttl;

    @FIELD(kind = Kind.HEADER, length = 1, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.INT, decimal = 0)
    private int long_msg_type;

    @FIELD(kind = Kind.HEADER, length = 1, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type = NumberType.INT, decimal = 0)
    private int err_flag;

    @FIELD(kind = Kind.HEADER, length = 4, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String err_src;

    @FIELD(kind = Kind.HEADER, length = 3, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String err_type;

    @FIELD(kind = Kind.HEADER, length = 9, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String err_code;

    @FIELD(kind = Kind.HEADER, length = 4, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String dst_inst_code;

    @FIELD(kind = Kind.HEADER, length = 2, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String fail_knd;

    @FIELD(kind = Kind.HEADER, length = 16, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String ap_host_name;

    @FIELD(kind = Kind.HEADER, length = 10, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String ap_caller_id;

    @FIELD(kind = Kind.HEADER, length = 24, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String inf_id;

    @FIELD(kind = Kind.HEADER, length = 2, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String lang_type;

    @FIELD(kind = Kind.HEADER, length = 141, type = FieldType.STRING, trim = TrimType.RTRIM)
    private String reserved;

}
