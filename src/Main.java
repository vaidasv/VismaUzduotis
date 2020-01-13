
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vaidas
 */

public class Main {
   
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException {
    
    Product instance = new Product();
    //////////////////////duomenų nuskaitymas/////////////////////
    int quantity;
    int count = 0;
    String line;
    File file = new File("sample.csv");
    Scanner read = new Scanner(file);
    while (read.hasNextLine())
    {
     if (count != 0){
    line = read.nextLine();
    String[] token = line.split(",");
    
    try {
    quantity = Integer.parseInt(token[2]);
  
    instance.addProducts(token[0], token[1], quantity, LocalDate.parse(token[3]));
    } catch (NumberFormatException e) {}  
     }
     
     count ++;
    }
   //////////////////////////endof duomenų nuskaitymas//////////////
      
       String choice = null;
       int intChoice;
       
       Scanner menuScan = new Scanner(System.in);
       Scanner intScan = new Scanner (System.in);
       
       do {
           System.out.println("/////////////////////////////////////////////////////////////////");
           System.out.println("                         MENU");
           System.out.println("To use program functions input desired choice and press enter ");
           System.out.println("1.Display product list");
           System.out.println("2.Check for running out of stock products.");
           System.out.println("3.Check for products that are expired or will soon be expired.");
           System.out.println("0.Exit program.");
           System.out.println("/////////////////////////////////////////////////////////////////");
           choice = menuScan.nextLine();
           
           switch (choice){
               case "1":
                   instance.printProducts();
                   break;
               case "2":
                   System.out.println("Input quantity that is considered as running out of stock: ");
                   intChoice = intScan.nextInt();
                   instance.runningOutOfStock(intChoice);
                   break;
               case "3":
                   int day;
                   int month;
                   int year;
                   int check;
                   System.out.println("Input year to be checked:");
                   year = intScan.nextInt();
                   System.out.println("Input number of the month to be checked :");
                   month = intScan.nextInt();
                   System.out.println("Input Day number:");
                   day = intScan.nextInt();
                   System.out.println("Input number of days that are considered as soon to be expired");
                   check = intScan.nextInt();
                   instance.checkProductForExpiration(year, month, day, check);
                   break;
                   
           }
           
           
       } while (!choice.equals("0") );
       
        
}
    
    
  
   
   
   

}
