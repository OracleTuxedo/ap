package bc.ap.ac.ac07.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SAC02F452RInVo {
    private long page_no;

    private long page_size;

    private String mid;

    private String auth_no;

    private String auth_strt_date;

    private String auth_end_date;

    public String pmt_strt_date;

    public String pmt_end_date;

}
