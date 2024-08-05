package mti.com.telegram.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelegramInputUserData {
    private String tx_code;

    private String client_ip_no;

    private String client_mac;

    private String scrn_id;

    private String op_id;

    private String sync_type;

    private String async_rspn_yn;

    private String rspn_svc_code;

    @Builder.Default
    private int ttl_use_flag = 0;

    @Builder.Default
    private int ttl = 0;

    @Builder.Default
    private int long_msg_type = 1;

    private String dst_inst_code;

    private String fail_knd;

    private String ap_host_name;

    private String ap_caller_id;

    @Builder.Default
    private String lang_type = "EN";

}