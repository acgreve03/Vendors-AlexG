import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendorTest {

    static Vending v;
    static Item item;

    @BeforeAll
    public static void setup(){
        v = new Vending(1, 1);
        //item = new Item(10, 5);
    }

    @Test
    void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    void addMoneyTest(){
        v.addMoney(2.0);
        assertEquals(2.0, v.getBalance());
    }

    @Test
    void addMoneyTest2(){
        v.addMoney(-1.0);
        assertEquals(0.0, v.getBalance());
    }

    @Test
    void addMoneyTest3(){
        v.addMoney(0);
        assertEquals(0.0, v.getBalance());
    }

    @Test
    void buyAnItemTest(){
        //item.purchase(1);
        //assertEquals(4, item.getStock());

        Vending v1 = new Vending(4, 4);
        v1.addMoney(10.0);
        v1.purchase("Gum");
        assertEquals(3, v1.getItemStock("Gum"));
    }

    @Test
    void buyAnItemTest2(){
        //item.purchase(-1);
        //assertEquals(5, item.getStock());

        Vending v1 = new Vending(4, 4);
        v1.resetBalance();
        v1.purchase("Gum");
        assertEquals(4, v1.getItemStock("Gum"));
    }

    @Test
    void buyAnItemTest3(){
        //item.purchase(0);
        //assertEquals(5, item.getStock());

        Vending v1 = new Vending(4, 4);
        v1.addMoney(-1.0);
        v1.purchase("Gum");
        assertEquals(4, v1.getItemStock("Gum"));

    }

    @Test
    void emptyInventoryTest() {
        Vending v1 = new Vending(4, 4);
        v1.addMoney(7.0); // min amount of money to purchase 4 candy and 4 gum (4*1.25 + 4*0.5)
        for (int i = 0; i < 4; i++) {
            v1.purchase("Candy");
            v1.purchase("Gum");
        }
        assertEquals(true, v1.isStockEmpty());
        System.out.println(v1.getBalance());
    }

    @Test
    void emptyInventoryTest2() {
        Vending v1 = new Vending(4, 4);
        v1.addMoney(6.0); // not enough money to purchase all items and empty out inventory
        for (int i = 0; i < 4; i++) {
            v1.purchase("Candy");
            v1.purchase("Gum");
        }
        assertEquals(false, v1.isStockEmpty());
        System.out.println(v1.getBalance());
    }

    @Test
    void restockUSTest(){
        Vending v1 = new Vending(4, 4);
        v1.addMoney(7.0); // not enough money to purchase all items and empty out inventory
        for (int i = 0; i < 4; i++) {
            v1.purchase("Candy");
            v1.purchase("Gum");
        }
        v1.restock("Candy", 4);
        assertEquals(false, v1.isStockEmpty());
    }

    @Test
    void changeNameTest(){
        Vending v1 = new Vending(4, 4);
        assertEquals("Chips", v1.renameItem("Gum", "Chips"));
    }

    @Test
    void addItemTest(){
        Vending v1 = new Vending(4, 4);
        v1.resetBalance();
        v1.addMoney(20.0);
        v1.addItem("Soda", 5, 4);
        for(int i = 0; i<4; i++){
            v1.purchase("Soda");
        }
        v1.restock("Soda", 4);
        assertEquals(true, v1.doesItemExist("Soda"));
    }

    @Test
    void addItemTest2(){
        Vending v1 = new Vending(4, 4);
        v1.resetBalance();
        assertEquals(false, v1.doesItemExist("Soda"));
    }

    @Test
    void printInventoryTest(){
        Vending v1 = new Vending(4, 4);
        v1.printInventory();
    }

    @Test
    void removeItemTest(){
        Vending v1 = new Vending(4, 4);
        v1.removeItem("Candy");
        assertEquals(false, v1.doesItemExist("Candy"));
    }

    @Test
    void removeItemTest2(){
        Vending v1 = new Vending(4, 4);
        v1.removeItem("Candy");
        v1.addItem("Candy", 1, 4);
        assertEquals(true, v1.doesItemExist("Candy"));
    }

    @Test
    void purchaseHistoryTest(){
        Vending v1 = new Vending(6, 4);
        v1.resetBalance();
        v1.addMoney(20.0);
        for(int i = 0; i< 5; i++){
            v1.purchase("Candy");
        }
        assertEquals(5, v1.getPurchaseHistory("Candy"));
    }

    @Test
    void purchaseHistoryTest2(){
        Vending v1 = new Vending(6, 4);
        v1.resetBalance();
        v1.addMoney(20.0);
        for(int i = 0; i< 5; i++){
            v1.purchase("Candy");
        }
        assertEquals(-1, v1.getPurchaseHistory("Soda"));
    }

    @Test
    void viewItemDetailsTest(){
        Vending v1 = new Vending(4, 4);
        v1.viewItemDetails("Gum");
    }

    @Test
    void discountTest(){
        Vending v1 = new Vending(4, 4);
        v1.addItem("Cookies", 4.0, 4);
        v1.applyDiscount("Cookies", 0.25);
        assertEquals(3.0, v1.getItemPrice("Cookies"));
    }

    @Test
    void bestSellersTest(){
        Vending v1 = new Vending(4, 4);
        v1.addItem("Cookies", 10.0, 5);
        v1.addItem("Soda", 3.00, 10);
        v1.addBestSeller("Cookies");
        assertEquals(true, v1.isBestSeller("Cookies"));
    }
    @Test
    void bestSellersTest2(){
        Vending v1 = new Vending(4, 4);
        v1.addItem("Cookies", 10.0, 5);
        v1.addItem("Soda", 3.00, 10);
        v1.addBestSeller("Cookies");
        v1.addBestSeller("Gum");
        assertEquals(false, v1.isBestSeller("Soda"));
    }
}