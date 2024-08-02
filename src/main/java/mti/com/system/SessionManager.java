package mti.com.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionManager {
    private static final Logger LOGGER = LogManager.getLogger(SessionManager.class);

    public SessionManager() {
    }

    public static boolean isLoggedIn(HttpServletRequest request) {
        return true;
    }

    public static SessionVO getUserData(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        LOGGER.debug("HttpSession ", session);
        if (session == null) {
            LOGGER.debug("getUserData null", session);
            return null;
        }
        return (SessionVO) session.getAttribute(SessionVO.SESSION_DATA_KEY);
    }

    public static void setUserData(HttpServletRequest request, SessionVO sessionVO) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionVO.SESSION_DATA_KEY, session);
    }

    public static void alertSessionEnd(HttpServletRequest request, SessionVO sessionData) {
        HttpSession session = request.getSession();
        session.invalidate();
    }
}
