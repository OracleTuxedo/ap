package bc.ap.ac.ac07.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

// @Getter
// @Setter
// @ToString
// @Jacksonized
@Builder
@Data
public class AC0701Dto {
    private int page_no;
    private int page_size;
    private String mid;
    private String auth_no;
    private LocalDate auth_strt_date;
    private LocalDate auth_end_date;
    private LocalDate pmt_strt_date;
    private LocalDate pmt_end_date;

}
