package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperatorController {

    @RequestMapping("/operator")
    public OperatorSpring operator(){
        return new OperatorSpring();
    }
}
