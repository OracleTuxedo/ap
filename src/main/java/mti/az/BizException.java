package mti.az;

import java.util.Locale;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

public class BizException extends EgovBizException {
    private static final long serialVersionUID = 2836654851801916293L;

    public BizException() {
        super();
        setJson();
    }

    public BizException(String defaultMessage) {
        super(defaultMessage);
        setJson();
    }

    public BizException(MessageSource messageSource, String messageKey) {
        super(messageSource, messageKey);
        setJson();
    }

    public BizException(String defaultMessage, Exception wrappedException) {
        super(defaultMessage, wrappedException);
        setJson();
    }

    public BizException(MessageSource messageSource, String messageKey, Exception wrappedException) {
        super(messageSource, messageKey, wrappedException);
        setJson();
    }

    public BizException(MessageSource messageSource, String messageKey, Locale locale, Exception wrappedException) {
        super(messageSource, messageKey, locale, wrappedException);
        setJson();
    }

    public BizException(MessageSource messageSource, String messageKey, Object[] messageParameters,
            Exception wrappedException) {
        super(messageSource, messageKey, messageParameters, wrappedException);
        setJson();
    }

    public BizException(MessageSource messageSource, String messageKey, Object[] messageParameters, Locale locale,
            Exception wrappedException) {
        super(messageSource, messageKey, messageParameters, locale, wrappedException);
        setJson();
    }

    public BizException(MessageSource messageSource, String messageKey, Object[] messageParameters,
            String defaultMessage, Exception wrappedException) {
        super(messageSource, messageKey, messageParameters, defaultMessage, wrappedException);
        setJson();
    }

    // TODO Original Code chat GPT
    // private void setJson() {
    // HttpServletRequest request = ((ServletRequestAttributes)
    // RequestContextHolder.getRequestAttributes())
    // .getRequest();
    // request.setAttribute("isJson", WebUtil.isJson(request));
    // }

    // chat GPT
    private void setJson() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            request.setAttribute("isJson", WebUtil.isJson(request));
        } else {
            throw new IllegalStateException("No request attributes found");
        }
    }
}
