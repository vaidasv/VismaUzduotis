import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vaidas
 */
public class Product {

    private String name;
    private String code;
    private int quantity;
    private LocalDate expDate;
    ArrayList<Product> products = new ArrayList<>();

    public Product(String name, String code, int quantity, LocalDate expDate) {
        this.name = name;
        this.code = code;
        this.quantity = quantity;
        this.expDate = expDate;
    }

    public Product() {
        this.name = "";
        this.code = "";
        this.quantity = 0;
        this.expDate = LocalDate.of(2020, 01, 10);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void checkProductForExpiration(int year, int month, int day, int check) {
        LocalDate inputDate = LocalDate.of(year, month, day);
        long daysLeft;
        
        System.out.println("Products that passed expiration day:");
        
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getExpDate().isBefore(inputDate)) {
                System.out.println(products.get(i));
            }
        }
        System.out.println("");
        System.out.println("Products that will expire in " + check + " day/days or less:");

        for (int i = 0; i < products.size(); i++) {

            daysLeft = ChronoUnit.DAYS.between(inputDate, products.get(i).getExpDate());
            if (daysLeft < check && daysLeft > -1) {
                System.out.println(products.get(i) + " " + " days Left until product is expired: "  + daysLeft);

            }

        }
    }

    public void sortByName() {
        Collections.sort(products, Comparator.comparing(Product::getName) // sąrašo rikiavimas pagal produkto pavadinimą
        );
    }

    public void printProducts() {
        sortByName();
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));

        }

    }

    public void runningOutOfStock(int stock) {
        System.out.println("Products that are running out of stock (are below) " +stock+ " units left: ");
        sortByName();
        for (Product prod : products) {
            if (prod.getQuantity() < stock) {
                System.out.println(prod);

            }

        }

    }

    public void addProducts(String name, String code, int quantity, LocalDate expDate) {

        for (Product prod : products) { // tikrina ar egzistuoja toks pats produktas sarase;
            if (prod.getName().equals(name) && prod.getCode().equals(code) && prod.getExpDate().equals(expDate)) {
                prod.setQuantity(prod.getQuantity() + quantity);//jei taip, prideda prie egzistuojančio objekto kiekio, produkto kiekį
                return;//baigia funkciją
            }
        }
        //jei tas pats productas neegzistuoja, prideda naują objektą į sarašą
        Product newProduct = new Product(name, code, quantity, expDate);
        products.add(newProduct);

    }
    
    
   
    

    @Override
    public String toString() {
        return "product{" + "name=" + name + ", code=" + code + ", quantity=" + quantity + ", expDate=" + expDate + '}';
    }

}
