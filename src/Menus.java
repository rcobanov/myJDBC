import java.sql.SQLOutput;

public class Menus {
    //Class holding all menus and submenus

    public void printMainMenu(){
        System.out.println("*******************************************************");
        System.out.println("HW Applications Version 1.5                           *");
        System.out.println("                                                      *");
        System.out.println("Please make a choice:                                 *");
        System.out.println("1. Fetch information                                  *");
        System.out.println("2. Add/Change information                             *");
        System.out.println("9. Exit program                                       *");
        System.out.println("*******************************************************");
    }

    public void printFetchMenu(){
        System.out.println("*********************************************************");
        System.out.println("HW Applications Version 1.5                             *");
        System.out.println("                                                        *");
        System.out.println("1. Show the product catalog (All Products)              *");
        System.out.println("2. Search for products                                  *");
        System.out.println("3. Show all purchase orders                             *");
        System.out.println("4. Search purchase order lines (Using order number)     *");
        System.out.println("5. Show all customer orders                             *");
        System.out.println("6. Search customer order lines (Using order number)     *");
        System.out.println("7. Show unpaid invoices                                 *");
        System.out.println("9. Back to Main Menu.                                   *");
        System.out.println("*********************************************************");
    }

    public void printAddMenu(){
        System.out.println("*********************************************************");
        System.out.println("HW Applications Version 1.5                             *");
        System.out.println("                                                        *");
        System.out.println("1. Add a new product                                    *");
        System.out.println("2. Delete an unpaid invoice                             *");
        System.out.println("3. Change invoice status from Unpaid to paid            *");
        System.out.println("9. Back to Main Menu.                                   *");
        System.out.println("*********************************************************");
    }
}
