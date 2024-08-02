package mti.az;

import java.util.HashMap;
import java.util.Map;

import mti.com.telegram.vo.TelegramHeader;
import mti.com.telegram.vo.TelegramOutputUserData;

public class ControllerSupport extends LogObject {
    private static final long serialVersionUID = 1763503697421392060L;

    public ControllerSupport() {
        super();
    }

    public ControllerSupport(String loggerName) {
        super(loggerName);
    }

    // protected void handlerRequest(HttpServletRequest request, Runnable callback)
    // {
    // try {
    // ServiceSupport.request.set(request);
    // callback.run();
    // } finally {
    // ServiceSupport.request.remove();
    // }
    // }

    // TODO protected Map<String, Object> failMsg(Map<String, Object> map, String
    // message)

    // TODO protected Map<String, Object> failMsg(Map<String, Object> map, String
    // message, int failCd)

    protected Map<String, Object> successMsg(Map<String, Object> map, String message) {
        if (map == null) {
            map = new HashMap<String, Object>();
        }
        // Status status = new Status();
        // status.setCode(0);
        // status.setMessage(message);
        Status status = Status.builder()
                .code(0)
                .message(message)
                .build();
        map.put("Status", status);
        return map;
    }

    /**
     * Get Result Code of Telegram
     */
    protected String getTxResultCode(TelegramOutputUserData userData) throws Exception {
        if (userData == null) {
            throw new BizException("Error occured[txResult-UserData is null]");
        }
        TelegramHeader header = userData.getHeader();
        if (header == null) {
            throw new BizException("Error occured[txResult-TelegramHeader is null]");
        }

        return StringUtil.nvl(header.getErr_code());
    }

    protected boolean isTxEmptyResult(TelegramOutputUserData userData) throws Exception {
        return (("EMCAP7005".equals(getTxResultCode(userData))) || ("IAZAP0005".equals(getTxResultCode(userData)))
                || ("IMCAP7005".equals(getTxResultCode(userData))) || (userData.getOutput() == null));
    }
}
