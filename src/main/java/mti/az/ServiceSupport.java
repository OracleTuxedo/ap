package mti.az;

import mti.com.system.SessionManager;
import mti.com.system.SessionVO;
import mti.com.telegram.vo.TelegramInputUserData;
import mti.com.telegram.vo.TelegramMessage;
import mti.com.telegram.vo.TelegramOutputUserData;

import java.util.Locale;

import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

import jakarta.servlet.http.HttpServletRequest;

// import
// javax.servlet.http.HttpServletRequest;
// jakarta.servlet.http.HttpServlet

public class ServiceSupport extends LogObject {
    private static final long serialVersionUID = 586954696745616431L;

    public static ThreadLocal<HttpServletRequest> request = new ThreadLocal<HttpServletRequest>();

    public ServiceSupport() {
        super();
    };

    public ServiceSupport(String loggerName) {
        super(loggerName);
    }

    public boolean isErrorCode(TelegramMessage msgVO) {
        if (msgVO == null) {
            return true;
        }
        String kind = msgVO.getKind();

        if (StringUtil.isNullOrEmpty(kind) || "E".equals(kind)) {
            return true;
        }
        return false;
    }

    public boolean isSuccessCode(TelegramMessage msgVo) {
        if (msgVo == null) {
            return false;
        }
        String kind = msgVo.getKind();

        if (!StringUtil.isNullOrEmpty(kind) && "N".equals(kind)) {
            return true;
        }
        return false;
    }

    // TODO public void txErrorStatus(TelegramOutputUserData txVO)
    // TODO public void txErrorStatus(TelegramOutputUserData txVO, String
    // exceptErrCd)
    // TODO public void txErrorStatus(TelegramOutputUserData txVO, String[]
    // exceptErrCd)

    protected TelegramInputUserData getTxHeader(HttpServletRequest request, String txCode, String scrnId) {
        SessionVO userVo = SessionManager.getUserData(request);

        TelegramInputUserData userData = TelegramInputUserData
                .builder()
                .tx_code(txCode)
                .client_ip_no(WebUtil.getClientIp(request))
                .scrn_id(scrnId)
                .op_id(userVo == null ? "undefined" : userVo.getSUserId())
                .sync_type("A")
                .rspn_svc_code("0")
                .ttl_use_flag(0)
                .lang_type(getLocaleCd(request))
                .build();
        return userData;
    }

    protected TelegramOutputUserData txTransaction(TelegramInputUserData userData, Object in, Object out)
            throws Exception {

        // TelegramOutputUserData result = (TelegramOutputUserData)
        // InterfaceTelegram.interfaceTuxedo(userData, in, out);
        TelegramOutputUserData result = TelegramOutputUserData.builder().build();
        return result;
    }

    protected String getLocaleCd(HttpServletRequest request) {
        String lang = "EN";
        Locale locale = (Locale) WebUtils.getSessionAttribute(request,
                SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
        if (locale != null)
            lang = (locale.toString().toUpperCase()).equals("EN") ? "EN" : "ID";

        return lang;
    }
}
