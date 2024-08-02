package mti.az;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Status extends LogObject {

    private static final long serialVersionUID = -487096832750835738L;

    private int code;
    private String message;

}
