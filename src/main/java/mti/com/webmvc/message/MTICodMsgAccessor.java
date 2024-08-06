package mti.com.webmvc.message;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
// import javax.servlet.jsp.JspException;
import mti.com.utility.ExceptionUtil;
import mti.com.webmvc.message.MTICodMsgAccessor;
import mti.com.webmvc.message.MTIMessageSource;
import mti.com.webmvc.message.vo.CodeEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MTICodMsgAccessor {
    private static final Logger logger = LogManager.getLogger(MTICodMsgAccessor.class);

    private final MTIMessageSource messageSource;

    private String argumentSeparator = ",";

    public List<String> clusteringips;

    public MTICodMsgAccessor(MTIMessageSource paramMTIMessageSource) {
        this.messageSource = paramMTIMessageSource;
    }

    // public String getMessageWithArguments(String paramString1, String
    // paramString2, String paramString3)
    // throws NoSuchMessageException {
    // // null = null;
    // paramString1 = "M@" + paramString1;
    // String[] arrayOfString = null;
    // try {
    // arrayOfString = resolveArguments(paramString2);
    // } catch (JspException jspException) {
    // ExceptionUtil.logPrintStackTrace(logger, (Exception) jspException);
    // }
    // return this.messageSource.resolveMessageWithArguments(paramString1, null,
    // arrayOfString, paramString3);
    // }

    // public String getMessageWithArguments(String paramString1, Locale
    // paramLocale, String paramString2,
    // String paramString3) throws NoSuchMessageException {
    // null = null;
    // paramString1 = "M@" + paramString1;
    // String[] arrayOfString = null;
    // try {
    // arrayOfString = resolveArguments(paramString2);
    // } catch (JspException jspException) {
    // ExceptionUtil.logPrintStackTrace(logger, (Exception) jspException);
    // }
    // return this.messageSource.resolveMessageWithArguments(paramString1,
    // paramLocale, arrayOfString, paramString3);
    // }

    // public String getMessage(String paramString) throws NoSuchMessageException {
    // paramString = "M@" + paramString;
    // MessageFormat messageFormat = this.messageSource.resolveCode(paramString,
    // null);
    // return (messageFormat == null) ? null : messageFormat.toPattern();
    // }

    // public String getMessage(String paramString, Locale paramLocale) throws
    // NoSuchMessageException {
    // paramString = "M@" + paramString;
    // MessageFormat messageFormat = this.messageSource.resolveCode(paramString,
    // paramLocale);
    // return (messageFormat == null) ? null : messageFormat.toPattern();
    // }

    public String getCode(String paramString) throws NoSuchMessageException {
        paramString = "C@" + paramString;
        MessageFormat messageFormat = this.messageSource.resolveCode(paramString, null);
        return (messageFormat == null) ? null : messageFormat.toPattern();
    }

    public String getCode(String paramString, Locale paramLocale) throws NoSuchMessageException {
        paramString = "C@" + paramString;
        MessageFormat messageFormat = this.messageSource.resolveCode(paramString, paramLocale);
        return (messageFormat == null) ? null : messageFormat.toPattern();
    }

    // public String getCodeExt(String paramString, Locale paramLocale, int
    // paramInt) throws NoSuchMessageException {
    // paramString = "C@" + paramString;
    // MessageFormat messageFormat =
    // this.messageSource.getExtMessageFormatFromCode(paramLocale, paramString,
    // paramInt);
    // String str = null;
    // if (messageFormat != null)
    // str = messageFormat.toPattern();
    // return str;
    // }

    // public ArrayList<CodeEntry> getCodeList(String paramString) {
    // paramString = "C@" + paramString;
    // return this.messageSource.resolveCodeList(null, paramString);
    // }

    // public ArrayList<CodeEntry> getCodeList(String paramString, Locale
    // paramLocale) {
    // paramString = "C@" + paramString;
    // return this.messageSource.resolveCodeList(paramLocale, paramString);
    // }

    // protected String[] resolveArguments(String paramString) throws JspException {
    // return (paramString instanceof String)
    // ? StringUtils.delimitedListToStringArray(paramString, this.argumentSeparator)
    // : null;
    // }

    // public void setClusteringips(List<String> paramList) {
    // this.clusteringips = paramList;
    // }

    // public List<String> getClusteringips() {
    // return (this.clusteringips == null) ? new ArrayList<>() : this.clusteringips;
    // }
}
