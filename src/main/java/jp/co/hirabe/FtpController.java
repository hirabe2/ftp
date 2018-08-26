package jp.co.hirabe;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FtpController {

    @RequestMapping("/ftp")
    public String greeting() {
        return "hello";
    }
}
