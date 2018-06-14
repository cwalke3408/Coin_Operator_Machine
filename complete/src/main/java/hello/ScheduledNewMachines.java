//package hello;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.Random;
//
//@Component
//public class ScheduledNewMachines {
//
//    Oraganization christian = new Oraganization();
//
//    private static final Logger log = LoggerFactory.getLogger(ScheduledNewMachines.class);
//
//    @Scheduled(fixedRate = 5000)
//    public void createNewMachine() {
//        Random rand = new Random();
//
//        String location = "Clem" + rand.nextInt(4);
//        String myID = "id_" + rand.nextInt(100);
//
//        SnackMachine machine = new SnackMachine(location, myID);
//
//        christian.setTotalNumCoins(25,25, 25, machine);
//        christian.addVendingMachineToMap(machine);
//
//        log.info(christian.stringMyVendingMachines());
//    }
//}
