package bc.ap.ac.ac07.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Builder
// @Getter
// @Setter
// @ToString
// @Jacksonized
@Data
public class AC0701DTO {
    private String mid;
    private String authNo;
    private String authStartDate;
    private String authEndDate;
    private String pmtStartDate;
    private String pmtEndDate;
    private int pageNo;
}
