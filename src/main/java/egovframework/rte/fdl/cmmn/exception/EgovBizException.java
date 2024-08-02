package egovframework.rte.fdl.cmmn.exception;

import java.text.MessageFormat;
import java.util.Locale;
import org.springframework.context.MessageSource;

public class EgovBizException extends BaseException {
    private static final long serialVersionUID = 1L;

    public EgovBizException() {
        this("BaseException without message", (Object[]) null, (Exception) null);
    }

    public EgovBizException(String defaultMessage) {
        this(defaultMessage, (Object[]) null, (Exception) null);
    }

    public EgovBizException(String defaultMessage, Exception wrappedException) {
        this(defaultMessage, (Object[]) null, wrappedException);
    }

    public EgovBizException(String defaultMessage, Object[] messageParameters, Exception wrappedException) {
        String userMessage = defaultMessage;
        if (messageParameters != null)
            userMessage = MessageFormat.format(defaultMessage, messageParameters);
        this.message = userMessage;
        this.wrappedException = wrappedException;
    }

    public EgovBizException(MessageSource messageSource, String messageKey) {
        this(messageSource, messageKey, (Object[]) null, (String) null, Locale.getDefault(), (Exception) null);
    }

    public EgovBizException(MessageSource messageSource, String messageKey, Exception wrappedException) {
        this(messageSource, messageKey, (Object[]) null, (String) null, Locale.getDefault(), wrappedException);
    }

    public EgovBizException(MessageSource messageSource, String messageKey, Locale locale, Exception wrappedException) {
        this(messageSource, messageKey, (Object[]) null, (String) null, locale, wrappedException);
    }

    public EgovBizException(MessageSource messageSource, String messageKey, Object[] messageParameters, Locale locale,
            Exception wrappedException) {
        this(messageSource, messageKey, messageParameters, (String) null, locale, wrappedException);
    }

    public EgovBizException(MessageSource messageSource, String messageKey, Object[] messageParameters,
            Exception wrappedException) {
        this(messageSource, messageKey, messageParameters, (String) null, Locale.getDefault(), wrappedException);
    }

    public EgovBizException(MessageSource messageSource, String messageKey, Object[] messageParameters,
            String defaultMessage, Exception wrappedException) {
        this(messageSource, messageKey, messageParameters, defaultMessage, Locale.getDefault(), wrappedException);
    }

    public EgovBizException(MessageSource messageSource, String messageKey, Object[] messageParameters,
            String defaultMessage, Locale locale, Exception wrappedException) {
        this.messageKey = messageKey;
        this.messageParameters = messageParameters;
        this.message = messageSource.getMessage(messageKey, messageParameters, defaultMessage, locale);
        this.wrappedException = wrappedException;
    }
}
