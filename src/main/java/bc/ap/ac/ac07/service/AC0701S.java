package bc.ap.ac.ac07.service;

import org.springframework.stereotype.Service;

import bc.ap.ac.ac07.vo.SAC02F452RInVo;
import bc.ap.ac.ac07.vo.SAC02F452ROutVo;
import jakarta.servlet.http.HttpServletRequest;
import mti.az.ServiceSupport;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramInputUserData;
import mti.com.telegram.vo.TelegramOutputUserData;

@Service
public class AC0701S extends ServiceSupport {

    public TelegramOutputUserData getListTransaction(HttpServletRequest request, SAC02F452RInVo inVo) throws Exception {
        TelegramOutputUserData result;

        try {
            TelegramInputUserData userData = getTxHeader(request, "SAC02F452R", "WAC070100H");
            result = txTransaction(userData, inVo, new SAC02F452ROutVo());
        } catch (TelegramNestedRuntimeException e) {
            error(e.toString(), e);
            throw e;
        } catch (Exception e) {
            error(e.getMessage(), e);
            throw e;
        }
        return result;
    }
}
