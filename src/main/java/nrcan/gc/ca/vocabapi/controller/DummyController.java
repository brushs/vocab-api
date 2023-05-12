package nrcan.gc.ca.vocabapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    @GetMapping("/version")
    public String getVersion() {
        return "0.1";
    }
}
