package mti.com.telegram.exception;

import org.springframework.core.NestedRuntimeException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TelegramNestedRuntimeException extends NestedRuntimeException {
    private static final long serialVersionUID = 3822354226516829667L;

    private String fieldName;

    private String msg;

    private String ftype;

    private String method;

    private long pointer;

    private String objName;

    private String parser;

    public TelegramNestedRuntimeException(String paramString) {
        super(paramString);
        this.msg = paramString;
    }

    public TelegramNestedRuntimeException(String paramString, Throwable paramThrowable) {
        super(paramString, paramThrowable);
        this.msg = paramString;
    }

}
