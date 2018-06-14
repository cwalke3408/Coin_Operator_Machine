package hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OperatorSpring {

    private Map<String, ArrayList<Machine>> springMap;
    Oraganization christian = new Oraganization();

    public OperatorSpring(String location, String id){
        SnackMachine machine = new SnackMachine(location, id);
        christian.setTotalNumCoins(25, 25, 25, machine);
        christian.addVendingMachineToMap(machine);
        springMap = christian.getMyVMachinesMap();
    }

    public OperatorSpring() {


        SnackMachine clemsonVM1 = new SnackMachine("clemson", "clemsonVM1");
        SnackMachine clemsonVM2 = new SnackMachine("clemson", "clemsonVM2");
        SnackMachine atlantaVM1 = new SnackMachine("atlanta", "atlantaVM1");

        christian.setTotalNumCoins(25, 25, 25, clemsonVM1);
        christian.setTotalNumCoins(25, 25, 25, clemsonVM2);
        christian.setTotalNumCoins(25, 25, 25, atlantaVM1);

        christian.addVendingMachineToMap(clemsonVM1);
        christian.addVendingMachineToMap(clemsonVM2);
        christian.addVendingMachineToMap(atlantaVM1);

//        christian.printLocations();

        springMap = christian.getMyVMachinesMap();
        christian.stringMyVendingMachines();
    }

    public Map<String, ArrayList<Machine>> getSpringMap() {
        return springMap;
    }
}
