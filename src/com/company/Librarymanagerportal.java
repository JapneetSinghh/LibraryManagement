package com.company;

import com.company.Teachersportal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Librarymanagerportal extends Teachersportal {

// CREATE TABLE
    public void create_table() {
        try {
            st.execute("CREATE TABLE IF NOT EXISTS library_stock " + "(ID int, Name text,Author text,Availability text)");
        } catch (SQLException e) {
            System.out.println("Something Went Wrong " + e.getMessage());
        }
    }
// INSERT STOCK
    public void insert_stock() {
        String bookname, bookauthor, availibility = "Available",issuer="none",dateissued="DD/MM/YYYY";
        int bookid,price,libcardnum=0,daystoreturn=0,k=0;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
do {
    System.out.println("Enter Book ID: ");
    bookid = Integer.parseInt(br.readLine());
    st.execute("SELECT * from library_stock where ID="+bookid);
    ResultSet result=st.getResultSet();
    while (result.next()){
        k=result.getInt(1);
        if(k==bookid){
            System.out.println("Book ID Already Exists");
        }
    }
}while (k==bookid);
            System.out.println("Enter Book name: ");
            bookname = br.readLine();
            System.out.println("Enter Author name: ");
            bookauthor = br.readLine();
            System.out.println("Enter Book Price: ");
            price=Integer.parseInt(br.readLine());
            st.executeUpdate("INSERT INTO library_stock VALUES" + "(" + bookid + ",'" + bookname + "','" + bookauthor + "','" + availibility + "',"+price+",'"+issuer+"','"+dateissued+"',"+daystoreturn+","+libcardnum+")");
        } catch (IOException e) {
            System.out.println(e);
            ;
        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
    }
//EDIT STOCK
    public void edit_stock() {
        try {
            String bookname, bookauthor, availibility = "Available";
            int bookid, choice, choice2,price;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter Book ID of book whose data is to be edited: ");
            bookid = Integer.parseInt(br.readLine());
            System.out.println("1.Edit Book Name");
            System.out.println("2.Edit Author's Name");
            System.out.println("3.Edit Availability");
            System.out.println("4.Edit Price");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(br.readLine());
            switch (choice) {
                case 1:
                    System.out.println("Enter new Book Name: ");
                    bookname = br.readLine();
                    st.executeUpdate("UPDATE library_stock SET Name='" + bookname + "' WHERE ID=" + bookid);
                    System.out.println("BOOK("+bookid+") Book name UPDATED TO: "+bookname);

                    break;
                case 2:
                    System.out.println("Enter new Author's Name: ");
                    bookname = br.readLine();
                    st.executeUpdate("UPDATE library_stock SET Author='" + bookname + "' WHERE ID=" + bookid);
                    System.out.println("BOOK("+bookid+") Author's name UPDATED TO: "+bookname);

                    break;
                case 3:
                    System.out.println("Changing Books Availability\nWhat is Availability Status\n1.Available\n2.Issued  ");
                    System.out.println("Enter choice: ");
                    choice2 = Integer.parseInt(br.readLine());
                    int flag = 1;
                    do {
                        if (choice2 == 1) {
                            availibility = "Available";
                        } else if (choice2 == 2) {
                            availibility = "Issued";
                        } else {
                            System.out.println("Enter Valid choice");
                            flag = 0;
                        }
                    } while (flag == 0);
                    st.executeUpdate("UPDATE library_stock SET Availability='" + availibility + "' WHERE ID=" + bookid);
                    System.out.println("BOOK("+bookid+") AVAILABILITY UPDATED TO: "+availibility);
                    break;
                case 4:
                    System.out.print("Enter new Price for Updation: ");
price=Integer.parseInt(br.readLine());
                    st.executeUpdate("UPDATE library_stock SET Price=" + price + " WHERE ID=" + bookid);
                    System.out.println("BOOK("+bookid+") Price UPDATED TO: "+price);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
// DELETE RECORD
    public void delete_record(){
        try{

            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            int ID;
            System.out.println("Enter Book ID whose record is to be deleted: ");
            ID=Integer.parseInt(br.readLine());
            st.execute("DELETE FROM library_stock Where ID="+ID);
            System.out.println("==DELETION SUCCESSFUL==");
        }catch (IOException e){
            System.out.println(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void display_list(){
        try {
            st.execute("SELECT * from library_stock");
            ResultSet result=st.getResultSet();

            System.out.println("------------------------------------------------------------------");
            System.out.println("    ID    Availability    Price      Book Name      Author Name    ");
            System.out.println("------------------------------------------------------------------");

            while (result.next()){
                System.out.println("  "+result.getInt("ID")+"      "+result.getString("Availability")+"    "+result.getInt("Price")+"    "+result.getString("Name")+"      "+result.getString("Author"));
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());

        }
    }
//    SEARCHING
    public void search_list(){
        try {
            System.out.println("Enter Book ID of book to be searched: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int ID=Integer.parseInt(br.readLine());
            st.execute("SELECT * from library_stock WHERE ID="+ID);
            ResultSet result=st.getResultSet();

            System.out.println("------------------------------------------------------------------");
            System.out.println("   ID    Availability    Price      Book Name      Author Name    ");
            System.out.println("------------------------------------------------------------------");

            while (result.next()){
                System.out.println("  "+result.getInt("ID")+"      "+result.getString("Availability")+"    "+result.getInt("Price")+"    "+result.getString("Name")+"      "+result.getString("Author"));
            }
            System.out.println("------------------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    ADD COLUMN
    public void add_colum(){
     try {
         st.executeUpdate("ALTER TABLE library_stock\n" +
                 "ADD Lib_Card_Number int");
     } catch (SQLException e) {
         e.printStackTrace();
     }
    }
}
