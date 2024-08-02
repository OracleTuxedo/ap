package mti.com.telegram.vo;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TelegramOutputUserData {
    private Object output;

    private List<Object> outputList;

    private TelegramHeader header;

    private TelegramMessage message;
}