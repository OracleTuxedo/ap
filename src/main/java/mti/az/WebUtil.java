package mti.az;

import jakarta.servlet.http.HttpServletRequest;

public class WebUtil {

    public static final String JSON_ACCEPT = "application/json";

    /**
     * return Browser String.
     * 
     * <pre>
     * msie, chrome, mozilla, opera, Etc(webkit)
     * </pre>
     * 
     * @param request
     * @return Browser String
     */
    public static String getBrowser(HttpServletRequest request) {
        return getBrowser(request.getHeader("User-Agent"));
    }

    /**
     * return Browser String.
     * 
     * <pre>
     * msie, chrome, mozilla, opera, Etc(webkit)
     * </pre>
     * 
     * @param userAgent
     * @return Browser String
     */
    public static String getBrowser(String userAgent) {
        if (!StringUtil.isNullOrEmpty(userAgent)) {
            String userAgetntL = userAgent.toLowerCase();
            if (userAgetntL.indexOf("trident") > -1 || userAgetntL.indexOf("msie") > -1) {
                return "msie";
            } else if (userAgetntL.indexOf("chrome") > -1) {
                return "chrome";
            } else if (userAgetntL.indexOf("mozilla") > -1 && userAgetntL.indexOf("webkit") == -1) {
                return "mozilla";
            } else if (userAgetntL.indexOf("opera") > -1) {
                return "opera";
            }
        }
        return "webkit";
    }

    /**
     * Client IP
     * 
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        String clientIp = request.getHeader("Proxy-Client-IP");
        if (clientIp == null) {
            clientIp = request.getHeader("X-Forwarded-For");
        }
        if (clientIp != null) {
            int idx = clientIp.indexOf(',');
            if (idx > -1) {
                return clientIp.substring(0, idx);
            }
            return clientIp;
        }
        return request.getRemoteAddr();
    }

    /**
     * JSON Request YN("application/json" String check on Header "Accept"
     * information).
     * 
     * @param request
     * @return JSON Request YN
     */
    public static boolean isJson(HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.indexOf(JSON_ACCEPT) > -1) {
            return true;
        }
        return false;
    }

    /**
     * Return String URI.
     * 
     * <pre>
     * WebUtil.getURI(request) == "/app/member/userInfoListPageView.do?param1=value"
     * </pre>
     * 
     * @param request
     * @return URI String
     */
    public static String getURI(HttpServletRequest request) {
        return getURI(request, true);
    }

    /**
     * Return String URI.
     * 
     * <pre>
     * WebUtil.getURI(request, true)  == "/app/member/userInfoListPageView.do?param1=value"
     * WebUtil.getURI(request, false) == "/app/member/userInfoListPageView.do"
     * </pre>
     * 
     * @param request
     * @param appendQueryString
     * @return URI String
     */
    public static String getURI(HttpServletRequest request, boolean appendQueryString) {
        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();

        StringBuilder buff = new StringBuilder();
        buff.append(requestURI);
        if (appendQueryString && queryString != null) {
            buff.append("?").append(queryString);
        }
        return buff.toString();
    }
}
