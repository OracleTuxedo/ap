package bc.ap.ac.ac07.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bc.ap.ac.ac07.dto.AC0701Dto;
import bc.ap.ac.ac07.service.AC0701S;
import bc.ap.ac.ac07.vo.SAC02F452RInVo;
import bc.ap.ac.ac07.vo.SAC02F452ROutVo;
import jakarta.servlet.http.HttpServletRequest;
import mti.az.ControllerSupport;
import mti.com.telegram.vo.TelegramOutputUserData;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/AC/AC0701")
public class AC0701C extends ControllerSupport {

    private AC0701S ac0701s;

    // @Autowired
    public AC0701C(AC0701S ac0701s) {
        this.ac0701s = ac0701s;
    }

    @PostMapping("/salesTransactionListAjax")
    @ResponseBody
    public AC0701Dto salesTransactionListAjax(
            @RequestBody AC0701Dto entity) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyMMdd");

        SAC02F452RInVo inVo = SAC02F452RInVo.builder()
                .page_no(1)
                .page_size(10)
                .mid(entity.getMid())
                .auth_no(entity.getAuth_no())
                .auth_strt_date(entity.getAuth_strt_date().format(formatter))
                .auth_end_date(entity.getAuth_end_date().format(formatter))
                .pmt_strt_date(entity.getPmt_strt_date().format(formatter))
                .pmt_end_date(entity.getPmt_end_date().format(formatter))
                .build();

        TelegramOutputUserData result = ac0701s.getListTransaction(null, inVo);
        SAC02F452ROutVo output = (SAC02F452ROutVo) result.getOutput();
        if (isTxEmptyResult(result)) {
            // return successMsg(Has, name)
        }
        return entity;
    }

    @GetMapping("/")
    public String get(
            HttpServletRequest request) {
        return AC0701Dto.builder()
                // .mid("mid")
                // .auth_no("auth no")
                // .auth_strt_date("auth start date")
                // .auth_end_date("auth end date")
                // .pmt_strt_date("pmt start date")
                // .pmt_end_date("pmt end date")
                .build()
                .toString();
    }
}
