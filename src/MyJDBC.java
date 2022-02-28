import java.sql.*;

public class MyJDBC {
    private Connection con = getConnected();

    public Connection getConnected(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/hwstore", "root", "root");
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Could not connect!");
            e.printStackTrace();
            return null;
        }

    }
    public ResultSet doQuery(String query) throws SQLException{
        Statement st = con.createStatement();
        return st.executeQuery(query);
    }
    public void updateQuery(String statement){
        try{
            Statement st = con.createStatement();
            st.executeUpdate(statement);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void getAllParts() throws SQLException {
        ResultSet resu = doQuery("select * from product p, purchase_product pp where p.product_no = pp.product_no");
        System.out.printf("Product No | Product Description             | Qty in Stock   | Purchase Price   | Supplier%n");
        while (resu.next()) {
            System.out.printf("%-11s| %-32s| %-15s| %-17s| %s%n", resu.getString("product_no"), resu.getString("product_desc")
                                                                , resu.getString("quantity_in_stock"), resu.getString("purchase_price")
                                                                , resu.getString("supplier"));
        }

    }
    public void searchForProducts(String keyword) throws SQLException {
        ResultSet resu = doQuery("select * from product p, purchase_product pp where p.product_no = pp.product_no and product_desc like '%" + keyword + "%'");
        System.out.printf("Product No | Product Description             | Qty in Stock   | Purchase Price   | Supplier%n");
        while (resu.next()) {
            System.out.printf("%-11s| %-32s| %-15s| %-17s| %s%n", resu.getString("product_no"), resu.getString("product_desc")
                                                                , resu.getString("quantity_in_stock"), resu.getString("purchase_price")
                                                                , resu.getString("supplier"));
        }


    }
    public void getUnpaidInvoices() throws SQLException {
        ResultSet resu = doQuery("select * from customer_invoice where state = 'Unpaid'");
        System.out.printf("Invoice Number | Customer ID | State   | Date         | Total Amount %n");
        while (resu.next()) {
            System.out.printf("%-15s| %-12s| %-8s| %-13s| %s %n", resu.getString("invoice_no"), resu.getString("cust_id")
                                                                , resu.getString("state"), resu.getString("invoice_date")
                                                                , resu.getString("total_amount"));
        }

    }
    public void getPurchaseOrders() throws SQLException {
        ResultSet resu = doQuery("select * from purchase_order");
        System.out.printf("Order No | Supplier ID | State      | Order Date %n");
        while (resu.next()) {
            System.out.printf("%-9s| %-12s| %-11s| %-13s%n", resu.getString("purch_order_no"), resu.getString("supp_id")
                                                            , resu.getString("state"), resu.getString("order_date"));
        }

    }
    public void searchPurchaseOrderLines(int orderNo) throws SQLException {
        ResultSet resu = doQuery("select * from purchase_order_lines pol, product p where pol.product_no = p.product_no and purch_order_no =" + orderNo);
        System.out.printf("Order No | Line No | Product No | Product Desc                | Purchase Price | Purchase Qty |%n");
        while (resu.next()) {
            System.out.printf("%-9s| %-8s| %-11s| %-28s| %-15s| %-13s|%n", resu.getString("purch_order_no"), resu.getString("order_line_no")
                                                                        ,resu.getString("product_no"), resu.getString("product_desc")
                                                                        , resu.getString("purchase_price"), resu.getString("quantity"));
        }
    }

    public void getCustomerOrders() throws SQLException{
        ResultSet resu = doQuery("select * from customer_order");
        System.out.printf("Order No | Customer ID | State      | Order Date %n");
        while (resu.next()) {
            System.out.printf("%-9s| %-12s| %-11s| %-13s%n", resu.getString("cust_order_no"), resu.getString("cust_id")
                    , resu.getString("state"), resu.getString("order_date"));
        }
    }
    public void searchCustomerOrderLines(int orderNo) throws SQLException{
        ResultSet resu = doQuery("select * from customer_order_lines col, product p where col.product_no = p.product_no and cust_order_no = " + orderNo);
        System.out.printf("Order No | Line No | Product No | Product Desc                | Sales Price | Sold Qty     |%n");
        while (resu.next()) {
            System.out.printf("%-9s| %-8s| %-11s| %-28s| %-12s| %-13s|%n", resu.getString("cust_order_no"), resu.getString("order_line_no")
                    ,resu.getString("product_no"), resu.getString("product_desc")
                    , resu.getString("sales_price"), resu.getString("quantity"));
        }
    }

    public void updateInvoiceStatus(int invoiceNo) {
        updateQuery("UPDATE customer_invoice SET state = 'Paid' where invoice_no = " + invoiceNo);
    }
    public void deleteUnpaidInvoice(int invoiceNo){
        updateQuery("DELETE FROM customer_invoice WHERE state = 'Unpaid' and invoice_no = " + invoiceNo);
    }
    public void addProduct(int productNo, String productDesc, double purchasePrice, int qtyInStock, int suppId){
        updateQuery("INSERT INTO product VALUES ('"+ productNo +"','" + productDesc + "','" + purchasePrice + "','" + qtyInStock + "','" + suppId + "')");
    }


}
