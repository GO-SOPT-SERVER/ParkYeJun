package sopt.org.firstSeminar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    /**
     * HTTP Method : GET
     * http://localhost:8080/test 로 접속하면 리턴한 문자열이 뜬다!
     */
    @GetMapping("/test")
    public String test() {
        return "Welcome Spring";
    }
}