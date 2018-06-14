package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class AutomaticCustomer {

    private static final Logger log = LoggerFactory.getLogger(hello.AutomaticCustomer.class);

    OperatorSpring myMachines = new OperatorSpring();

    VendingMachine vm;
    Map<String, Machine> customerChoice = new HashMap<>();

    public AutomaticCustomer() {


        for (Map.Entry<String, ArrayList<Machine>> map : myMachines.getSpringMap().entrySet()) {
            for (Machine machine : map.getValue()) {
                customerChoice.put(machine.getMachine_id(), machine);
                System.out.println("\t"+machine.getLocation() + " ["+ machine.getMachine_id() +"] -> " + machine );
            }
        }

        System.out.println("\t :: " + customerChoice.get("clemsonVM1"));
        vm = (VendingMachine)customerChoice.get("clemsonVM1");
        vm.printInventory();

    }

    @Scheduled(fixedRate = 5000)
    public void makeAPerchase() {
        Random rand = new Random();

        int max = 7;
        int min = 5;


        int NickelAmount = rand.nextInt((max - min) + 1) + min;
        int DimeAmount = rand.nextInt((max - min) + 1) + min;
        int QuarterAmount = rand.nextInt((max - min) + 1) + min;

        System.out.println("<------------->");

        Coins_CurrentOrder myMoneyForSnacks = new Coins_CurrentOrder(NickelAmount, DimeAmount, QuarterAmount);
        vm.getCoin().insertion(myMoneyForSnacks);

        int randomRow = rand.nextInt(5);
        int randomCol = rand.nextInt(5);
        vm.selection(randomRow, randomCol, myMoneyForSnacks);

        System.out.println();
        printCustomerChoice();
        System.out.println();
        printMachinesList();
        //        vm.printInventory();
        System.out.println();

//        log.info(christian.stringMyVendingMachines());
    }

    public void printCustomerChoice(){
        for(Map.Entry<String, Machine> machineEntry : customerChoice.entrySet()){
            System.out.println("\t"+machineEntry.getKey() + " ["+ machineEntry.getValue() +"]");
        }
    }

    public void printMachinesList(){
        for (Map.Entry<String, ArrayList<Machine>> map : myMachines.getSpringMap().entrySet()) {
            for (Machine machine : map.getValue()) {
                System.out.println("\t"+machine.getLocation() + " ["+ machine.getMachine_id() +"] -> " + machine );
            }
        }

    }

}
