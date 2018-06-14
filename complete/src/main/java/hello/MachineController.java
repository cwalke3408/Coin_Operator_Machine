package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MachineController {

    private static final String template = "ID_%s";

    @RequestMapping("/snackMachine")
    public SnackMachine snackMachine(@RequestParam(value="theID", defaultValue = "009") String theID){
        return new SnackMachine("Clemson",  String.format(template, theID));
    }
}
