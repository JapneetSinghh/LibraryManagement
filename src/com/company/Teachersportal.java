package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Teachersportal {
    protected Connection conn;
    Statement st;

    public Teachersportal() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:/Users/japneetsingh/Desktop/JavaSelf/Library Management System/Library.db");
            st = conn.createStatement();
        } catch (SQLException E) {
            System.out.println("Something Went Wrong " + E.getMessage());
        }
    }

    public void insert_rec() {
        int rollno, nobs, fine,k=0;
        nobs = 0;
        fine = 0;
        String clas, name, Book_1, Book_2, Book_3;
        Book_1 = "Not Issued";
        Book_2 = "Not Issued";
        Book_3 = "Not Issued";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter details of student for creating library card");
            System.out.println("Enter Rollno: ");
            do{
            rollno = Integer.parseInt(br.readLine());
            st.execute("SELECT * from students_data where RollNo="+rollno);
            ResultSet result = st.getResultSet();
            while (result.next()){
                k=result.getInt(1);
                System.out.println("Roll number already Exists, Enter Again");

            }
            }
            while (k==rollno);
            System.out.println("Enter Class: ");
            clas = br.readLine();
            System.out.println("Enter Name: ");
            name = br.readLine();
            st.executeUpdate("INSERT INTO students_data VALUES" + "(" + rollno + ",'" + clas + "','" + name + "'," + nobs + ",'" + Book_1 + "','" + Book_2 + "' ,'" + Book_3 + "'," + fine + ")");
            System.out.println("Library Card Number for " + name + " is: " + 147 + rollno);

        } catch (IOException e) {
            System.out.println(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit_rec() {
        int rollno, choice, nobs;
        String str;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter RollNo of student whose data is to be edited: ");
            rollno = Integer.parseInt(br.readLine());
            System.out.println("What do you want to edit: ");
            System.out.println("1.Update Name");
            System.out.println("2.Update Class");
            System.out.println("3.Update No Of Books Issued");
            System.out.println("4.Update Book-1 Issued: ");
            System.out.println("5.Update Book-2 Issued: ");
            System.out.println("6.Update Book-3 Issued: ");
            System.out.println("7.Update Fine: ");
            System.out.print("Enter Choice: ");
            choice = Integer.parseInt(br.readLine());
            switch (choice) {
                case 1:
                    System.out.println("Enter New Name: ");
                    str = br.readLine();
                    st.executeUpdate("UPDATE students_data SET Name='" + str + "' WHERE RollNo=" + rollno);
                    System.out.println("Updating name of RollNo: " + rollno);
                    System.out.println("-----------------------------------------");
                    System.out.println("NAME UPDATED SUCCESSFULLY AS :" + str);
                    System.out.println("-----------------------------------------");

                    break;
                case 2:
                    System.out.println("Enter New Class: ");
                    str = br.readLine();
                    st.executeUpdate("UPDATE students_data SET Class='" + str + "' WHERE RollNo=" + rollno);
                    System.out.println("Updating name of RollNo: " + rollno);
                    System.out.println("-----------------------------------------");
                    System.out.println("CLASS UPDATED SUCCESSFULLY AS :" + str);
                    System.out.println("-----------------------------------------");
                    break;
                case 3:
                    System.out.println("Enter Updated No of Books Issued: ");
                    nobs = Integer.parseInt(br.readLine());

                    st.executeUpdate("UPDATE students_data SET No=" + nobs + " WHERE RollNo=" + rollno);
                    System.out.println("Updating name of RollNo: " + rollno);
                    System.out.println("-----------------------------------------");
                    System.out.println("NO OF BOOKS ISSUED UPDATED SUCCESSFULLY AS :" + nobs);
                    System.out.println("-----------------------------------------");
                    break;
                case 4:
                    System.out.println("Enter Updated Book-1: ");
                    str = br.readLine();
                    st.executeUpdate("UPDATE students_data SET Book_1='" + str + "' WHERE RollNo=" + rollno);
                    System.out.println("Updating BOOK-1 of RollNo: " + rollno);
                    System.out.println("-----------------------------------------");
                    System.out.println("BOOK-1 UPDATED SUCCESSFULLY AS :" + str);
                    System.out.println("-----------------------------------------");
                    break;
                case 5:
                    System.out.println("Enter Updated Book-2: ");
                    str = br.readLine();
                    st.executeUpdate("UPDATE students_data SET Book_2='" + str + "' WHERE RollNo=" + rollno);
                    System.out.println("Updating BOOK-1 of RollNo: " + rollno);
                    System.out.println("-----------------------------------------");
                    System.out.println("BOOK-2 UPDATED SUCCESSFULLY AS :" + str);
                    System.out.println("-----------------------------------------");
                    break;
                case 6:
                    System.out.println("Enter Updated Book-3: ");
                    str = br.readLine();
                    st.executeUpdate("UPDATE students_data SET Book_3='" + str + "' WHERE RollNo=" + rollno);
                    System.out.println("Updating BOOK-3 of RollNo: " + rollno);
                    System.out.println("-----------------------------------------");
                    System.out.println("BOOK-1 UPDATED SUCCESSFULLY AS :" + str);
                    System.out.println("-----------------------------------------");
                    break;
                case 7:
                    System.out.println("Enter Updated Fine: ");
                    nobs = Integer.parseInt(br.readLine());
                    st.executeUpdate("UPDATE students_data SET No=" + nobs + " WHERE RollNo=" + rollno);
                    System.out.println("Updating name of RollNo: " + rollno);
                    System.out.println("-----------------------------------------");
                    System.out.println("NO OF BOOKS ISSUED UPDATED SUCCESSFULLY AS :" + nobs);
                    System.out.println("-----------------------------------------");
                    break;
                default:
                    System.out.println("Please enter a valid choice");
            }


        } catch (IOException e) {
            System.out.println(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void display_all() {
        try {

            System.out.println("Displaying All Student Records: ");
            System.out.println("------------------------------------------------");

            st.execute("SELECT * from students_data order by RollNo");
            ResultSet result = st.getResultSet();

            while (result.next()) {
                System.out.println("Rollno: " + result.getInt("Rollno") + "\nName: " + result.getString("Name") + "\nClass: " + result.getString("Class") + "\nBook-1: " + result.getString("Book_1") + "\nBook-2: " + result.getString("Book_2") + "\nBook-3: " + result.getString("Book_3") + "\nFine: " + result.getInt("Fine"));
                System.out.println("------------------------------------------------");

            }
        } catch (SQLException E) {
            System.out.println("Something went wrong " + E.getMessage());
        }

    }
    public void display_list(){
        try {
            System.out.println("Displaying List of all students: ");
            st.execute("SELECT * from students_data");
            ResultSet result=st.getResultSet();
            System.out.println("------------------------------------------------");

            System.out.println("RollNo  Library_Card_Number     Name");
            System.out.println("------------------------------------------------");

            //  8 21
            while (result.next()){
                System.out.println("  "+result.getInt("RollNo")+"           147"+result.getInt("RollNo")+"           "+result.getString("Name"));
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());

        }
}
    public void delete_record(){
        try{

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int rollno;
        System.out.println("Enter Rollno whose record is to be deleted: ");
        rollno=Integer.parseInt(br.readLine());
        st.execute("DELETE FROM students_data Where RollNo="+rollno);
            System.out.println("DELETION SUCCESSFUL");
        }catch (IOException e){
            System.out.println(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void search_record(){
       int k=0,i=0;
        try {
do{
            System.out.println("Enter Rollno of student to open record: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int rollno;
            rollno = Integer.parseInt(br.readLine());

            st.execute("SELECT * from students_data WHERE RollNo=" + rollno);
            ResultSet result = st.getResultSet();
            while (result.next()) {
                k = result.getInt(1);
                i++;
            }
            if (i == 0) {
                System.out.println("Rollno Doesn't Exist");
            }
            st.execute("SELECT * from students_data WHERE RollNo=" + rollno);
            ResultSet resultSet=st.getResultSet();
            while (result.next()) {
                System.out.println("Rollno: " + resultSet.getInt("Rollno") + "\nName: " + resultSet.getString("Name") + "\nClass: " + resultSet.getString("Class") + "\nBook-1: " + resultSet.getString("Book_1") + "\nBook-2: " + resultSet.getString("Book_2") + "\nBook-3: " + resultSet.getString("Book_3") + "\nFine: " + resultSet.getInt("Fine"));
                System.out.println("------------------------------------------------");
            }
        }while (i==0);
        }catch (SQLException e){
            System.out.println("Something went wrong "+e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void fine_table(int rollno,String name,String clas,int bookid,String Book_Name,int fine,String date,int extradays){
        try {
//            st.execute("CREATE TABLE IF NOT EXISTS fine_record "+"(RollNo int,Student_Name String,Class String,Book_ID int,Book_Name String,Fine int,Date String,Extra_Days int )");
            st.executeUpdate("INSERT INTO fine_record VALUES"+"("+rollno+",'"+name+"','"+clas+"',"+bookid+",'"+Book_Name+"',"+fine+" ,'"+date+"',"+extradays+")");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
