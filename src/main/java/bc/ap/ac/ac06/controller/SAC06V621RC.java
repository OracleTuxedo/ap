package bc.ap.ac.ac06.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bc.ap.ac.ac06.service.SAC06V621RS;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// @Controller
@RestController
@RequestMapping(value = "/AC/AC06")
public class SAC06V621RC {
    private SAC06V621RS sac06v621rs;

    public SAC06V621RC(SAC06V621RS sac06v621rs) {
        this.sac06v621rs = sac06v621rs;
    }

    // Retrieve advance payment condition information
    @GetMapping("/retrievePaymentList")
    public String retrievePaymentList(){
        
        String result = sac06v621rs.get();
        return result;
    }
}
