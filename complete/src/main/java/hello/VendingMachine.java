package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class VendingMachine extends Machine {

    private int numOfShelves;
    private int numOfCompartments;
    private int numOfMaxSpace;

    ArrayList<Product>[][] inventory;

    public VendingMachine(int numOfShelves, int numOfCompartments, int maxNumOfSpace, String location, String location_id) {
        super(location, location_id);

        this.numOfShelves = numOfShelves;
        this.numOfCompartments = numOfCompartments;
        this.numOfMaxSpace = maxNumOfSpace;

        inventory = new ArrayList[numOfShelves][numOfCompartments];

        intializeInventory();
        //addStock();
        addJBCStock();
    }

    public void intializeInventory() {
        for (int i = 0; i < numOfShelves; i++) {
            for (int j = 0; j < numOfCompartments; j++) {
                inventory[i][j] = new ArrayList();
            }
        }
    }

    public void addToInventory(int row, int col, Double wholesalePrice, Double retailPrice, String itemName, Integer stock, Integer id) {

        Product product = new Product(wholesalePrice, retailPrice, itemName, stock, id);
        if (inventory[row][col].size() < numOfMaxSpace)
            inventory[row][col].add(product);
    }

    public void addToInventory(int row, int col, int amount, Double wholesalePrice, Double retailPrice, String itemName, Integer stock, Integer id) {


        int inventoryLength = inventory[row][col].size();
        for (int i = inventoryLength; (i < amount) && (inventory[row][col].size() <= numOfMaxSpace); i++) {
            Product product = new Product(wholesalePrice, retailPrice, itemName, stock, id);
            inventory[row][col].add(product);
        }
    }

    public void addToInventory(int row, int col, Product product, int stockAmount) {
        int inventoryLength = inventory[row][col].size();
        for (int i = inventoryLength; (i < stockAmount) && (inventory[row][col].size() <= numOfMaxSpace); i++) {
            inventory[row][col].add(product);
        }
    }

    public void selection(int row, int col, Coins_CurrentOrder myMoney) {
        if (inventory[row][col].size() > 0) {

            double changes = getCoin().purchased(inventory[row][col].get(0).getPrice(), myMoney);
            System.out.println("Price: " + inventory[row][col].get(0).getPrice());
            System.out.println("My money " + myMoney.getCurTotal());

            if (changes >= 0) {

                giveCustomerProduct(inventory[row][col]);
                System.out.println("Total Change is: ");
                for (Map.Entry<String, Integer> changeReturn : getCoin().returnChanges(changes).entrySet()) {
                    System.out.println(changeReturn.getValue() + " " + changeReturn.getKey() + "s");
                }
            } else {
                System.out.println("Not enough money to make purchase");
            }

        } else {
            System.out.println("Sorry Product Not Avaliable");
            myMoney.printCustomerCoinReturn();
            getCoin().returnMoney();
            myMoney.curReset();
        }
    }

    public void giveCustomerProduct(ArrayList<Product> itemRow) {
        itemRow.get(0).printThisPurchaseProduct();
        itemRow.remove(0);
    }

    public void addStock() {

        Double wholeSale = 1.0;
        Double retail = 2.0;
        String string = "item";
        int stock = 0;
        int id;


        for (int i = 0; i < numOfShelves; i++) {
            for (int j = 0; j < numOfCompartments; j++) {
                string = "item";
                string += i + "" + j;
                id = Integer.parseInt("" + i + j);
                addToInventory(i, j, 7, wholeSale, retail, string, stock, id);
            }
        }
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RestTemplate restTemplate;

    public void addJBCStock() {
        int id = 1;
        for (int i = 0; i < numOfShelves; i++) {
            for (int j = 0; j < numOfCompartments; j++) {
                //String SQL = "SELECT wholesalePrice, retailPrice, itemName, stock FROM product_table where id=" + id;
                String SQL = "SELECT * product_table";
                System.out.println("=======================");
                System.out.println(jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Product.class)));
                List<Product> m = jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Product.class));
                System.out.println("---"+m.get(0).getItemName());
                Product product = new Product(2.00, 3.00, "Candy", 25, 1);
                addToInventory(i, j, m.get(0), 10);
                id++;
            }
        }
    }


    public void printInventory() {
        for (int i = 0; i < numOfShelves; i++) {
            for (int j = 0; j < numOfCompartments; j++) {
                //System.out.print("[" + inventory[i][j].size() + "]");
                System.out.print("["+ inventory[i][j].get(0).getItemName() + "]");
            }
            System.out.println();
        }
    }
}
