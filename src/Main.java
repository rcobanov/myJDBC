import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* Use to run in CMD!!!
        javac -classpath c:\Users\robin\IdeaProjects\myJDBC\lib\mysql-connector-java-8.0.28.jar *.java
        java -classpath .;c:\Users\robin\IdeaProjects\myJDBC\lib\mysql-connector-java-8.0.28.jar Main
         */
        Scanner input = new Scanner(System.in);
        Menus men = new Menus();
        MyJDBC con = new MyJDBC();
        men.printMainMenu();
        int choice = Integer.parseInt(input.nextLine());
        boolean keepGoing = true;
        while (keepGoing){
            try{
                switch(choice){
                    case 1:
                        //when submenu 1 (fetch information) is selected:
                        men.printFetchMenu();
                        int subchoicefetch = Integer.parseInt(input.nextLine());
                        switch(subchoicefetch) {
                            case 1:
                                con.getAllParts();
                                break;
                            case 2:
                                System.out.println("Search for a product description. ");
                                System.out.println("It is possible to search for keywords(part of description), such as SCREW");
                                String keyword = input.nextLine();
                                con.searchForProducts(keyword);
                                break;
                            case 3:
                                con.getPurchaseOrders();
                                break;
                            case 4:
                                System.out.println("Search for an order number.");
                                int orderNo = Integer.parseInt(input.nextLine());
                                con.searchPurchaseOrderLines(orderNo);
                                break;
                            case 5:
                                con.getCustomerOrders();
                                break;
                            case 6:
                                System.out.println("Search for an order number");
                                int custOrderNo = Integer.parseInt(input.nextLine());
                                con.searchCustomerOrderLines(custOrderNo);
                                break;
                            case 7:
                                con.getUnpaidInvoices();
                                break;
                            case 9:
                                men.printMainMenu();
                                choice = Integer.parseInt(input.nextLine());
                                break;
                        }
                        break;
                    case 2:
                        // when submenu 2 (add information) is selected:
                        men.printAddMenu();
                        int subchoiceupdate = Integer.parseInt(input.nextLine());
                        switch(subchoiceupdate){
                            case 1:
                                System.out.println("Enter product number");
                                int prodNo = Integer.parseInt(input.nextLine());
                                System.out.println("Enter Product Description:");
                                String prodDesc = input.nextLine();
                                System.out.println("Enter Purchase price");
                                double purchasePrice = Integer.parseInt(input.nextLine());
                                System.out.println("How much quantity do we have in stock?");
                                int qtyInStock = Integer.parseInt(input.nextLine());
                                System.out.println("Which Supplier do we buy this from?");
                                int suppId = Integer.parseInt(input.nextLine());
                                con.addProduct(prodNo, prodDesc, purchasePrice, qtyInStock, suppId);
                                break;
                            case 2:
                                System.out.println("Enter invoice number to delete: ");
                                int delInvoiceNo = Integer.parseInt(input.nextLine());;
                                con.deleteUnpaidInvoice(delInvoiceNo);
                                break;
                            case 3:
                                System.out.println("Enter invoice number to update: ");
                                int invoiceNo = Integer.parseInt(input.nextLine());
                                con.updateInvoiceStatus(invoiceNo);
                                break;
                            case 9:
                                men.printMainMenu();
                                choice = Integer.parseInt(input.nextLine());
                                break;
                        }
                        break;
                    case 9:
                        keepGoing = false;
            }

            } catch (SQLException e){
                e.printStackTrace();
            }
        }

    }
}
