package action;


//这里面只能调 service里面的函数

import model.Cart;
import wh.*;
import service.AppService;

import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WarehouseAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    private String title;
    private double price;
    private int id;

    //控制反转
    private AppService appService;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String add() throws Exception {
        Context namingContext = new InitialContext();
        System.out.print("RMI registry bindings: ");
        System.out.println(title+String.valueOf(id)+price);
        NamingEnumeration<NameClassPair> e = namingContext.list("rmi://localhost/");
        while (e.hasMore())
            System.out.println(e.next().getName());
        String url = "rmi://localhost:1099/central_warehouse";
        Warehouse centralWarehouse = (Warehouse) namingContext.lookup(url);
        System.out.println("1");
     //   url = "rmi://localhost:1099/backup_warehouse";
     //   Warehouse backupWarehouse = (Warehouse) namingContext.lookup(url);
        System.out.println("2");
        Product book = new Book("title:< "+title+">", "isbn: "+String.valueOf(id), price);
       // if(id%2==0){
        centralWarehouse.add(title, book);
      //  else {backupWarehouse.add(title,book);}
        System.out.println("!31231321");
        return SUCCESS;
    }


    public String get() throws NamingException, RemoteException,Exception
    {
        Context namingContext = new InitialContext();
        System.out.print("RMI registry bindings: ");
        NamingEnumeration<NameClassPair> e = namingContext.list("rmi://localhost/");
        while (e.hasMore())
            System.out.println(e.next().getName());
        System.out.println("1");
        String url = "rmi://localhost:1099/central_warehouse";
        Warehouse centralWarehouse = (Warehouse) namingContext.lookup(url);
        System.out.println("4");
        List<String> keywords = Arrays.asList(title.split("\\s+"));

        Product prod = centralWarehouse.getProduct(keywords);
        System.out.println("5");
        System.out.println(prod.getDescription() + ": " + prod.getPrice()+"-"+prod.getLocation().getName());

        JSONObject wh3 = new JSONObject(false);
        System.out.println(prod.getDescription());
    //    wh3 = JSONObject.fromObject("{\"Description\":\"I always love U 0132354799\",\"Price\":44.95,\"Location\":\"centralWarehouse\"}");
        String des = prod.getDescription();
        String pri = String.valueOf(prod.getPrice());
        String loc = prod.getLocation().getName();
        wh3 = JSONObject.fromObject("{\"Description\":\""+prod.getDescription()+"\",\"Price\":"+pri+",\"Location\":\""+loc+"\"}");

        /*
        wh3.put("Description",prod.ge   tDescription());
        wh3.put("Price",prod.getPrice());
        wh3.put("Location",prod.getLocation().getName());
        */
        System.out.println("6");
        System.out.println(wh3.get("Price"));
        System.out.println(wh3.get("Description"));
        System.out.println(wh3.get("Location"));

        session().setAttribute("Warehouse",wh3);
        return SUCCESS;

    }

    public static void main(String[] args) throws NamingException, RemoteException
    {
        Context namingContext = new InitialContext();
        System.out.print("RMI registry bindings: ");
        NamingEnumeration<NameClassPair> e = namingContext.list("rmi://localhost/");
        while (e.hasMore())
            System.out.println(e.next().getName());
        System.out.println("1");
        String url = "rmi://localhost:1099/central_warehouse";
        Warehouse centralWarehouse = (Warehouse) namingContext.lookup(url);
        System.out.println("4");
        List<String> keywords = Arrays.asList("1".split("\\s+"));
        Product prod = centralWarehouse.getProduct(keywords);
        System.out.println("5");
        System.out.println(prod.getDescription() + ": 1" + prod.getPrice()+"-"+prod.getLocation().getName());

        JSONObject wh3 = new JSONObject();
        wh3.put("Description",prod.getDescription());
        wh3.put("Price",prod.getPrice());
        wh3.put("Location",prod.getLocation().getName());

        System.out.println("6");
        System.out.println(wh3.toString());


    }
}

