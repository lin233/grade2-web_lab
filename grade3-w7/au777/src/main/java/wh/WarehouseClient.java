package wh;

import javax.naming.*;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The client for the wh program.
 * @version 1.0 2007-10-09
 * @author Cay Horstmann
 */
public class WarehouseClient
{
   public static void main(String[] args) throws NamingException, RemoteException
   {
//      System.setProperty("java.security.policy", "client.policy");
//      System.setSecurityManager(new SecurityManager());
      Context namingContext = new InitialContext();
      
      System.out.print("RMI registry bindings: ");
      NamingEnumeration<NameClassPair> e = namingContext.list("rmi://localhost/");
      while (e.hasMore())
         System.out.println(e.next().getName());
      
      String url = "rmi://localhost:1099/central_warehouse";
      Warehouse centralWarehouse = (Warehouse) namingContext.lookup(url);
      
      Scanner in = new Scanner(System.in);
      System.out.print("Enter keywords: ");
      List<String> keywords = Arrays.asList(in.nextLine().split("\\s+"));
      Product prod = centralWarehouse.getProduct(keywords);


      System.out.println(prod.getDescription() + ": " + prod.getPrice()+"-"+prod.getLocation().getName());
      System.out.print("Add keywords: ");
      in = new Scanner(System.in);
      String str = in.nextLine();
      Product book = new Book("I always love U", "0132354799", 44.95);
      centralWarehouse.add(str, book);

      in = new Scanner(System.in);
      System.out.print("Enter keywords: ");
      keywords = Arrays.asList(in.nextLine().split("\\s+"));
      prod = centralWarehouse.getProduct(keywords);

      System.out.println(prod.getDescription() + ": " + prod.getPrice()+"-"+prod.getLocation().getName());

   }
}
