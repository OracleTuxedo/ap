package bc.ap.ac.ac07.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bc.ap.ac.ac07.dto.AC0701DTO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
// @Controller
@RequestMapping("/AC/AC0701")
public class AC0701C {

    @PostMapping("/salesTransactionListAjax")
    @ResponseBody
    public AC0701DTO salesTransactionListAjax(
            @RequestBody AC0701DTO entity) {
        // return AC0701DTO.builder()
        // .inp_mid("mid")
        // .inp_auth_no("auth no")
        // .inp_auth_strtDate("auth start date")
        // .inp_auth_endDate("auth end date")
        // .inp_pmt_strtDate("pmt start date")
        // .inp_pmt_endDate("pmt end date")
        // .build();
        return entity;
    }

    @GetMapping("/")
    public String get() {
        return AC0701DTO.builder()
                .mid("mid")
                .authNo("auth no")
                .authStartDate("auth start date")
                .authStartDate("auth end date")
                .pmtStartDate("pmt start date")
                .pmtEndDate("pmt end date")
                .build()
                .toString();
    }
}
