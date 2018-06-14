package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class VM_Controller {

    private static final String template = "ID_%s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/vMachine")
    public OperatorSpring vMachine(@RequestParam(value="location", defaultValue = "clemson") String location){
        return new OperatorSpring(location, "ID_"+counter);
    }

}
