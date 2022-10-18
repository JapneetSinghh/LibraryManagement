package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Fine extends Teachersportal {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int zee = 0;

    public void fine_status() {
        int choice;
        try {

            System.out.println("------------------------------------------------");
            System.out.println("--------------CHECK FINE STATUS-----------------");
            System.out.println("------------------------------------------------");
            do {

                System.out.println("1.Check Your Fine");
                System.out.println("2.Exit");
                System.out.println("Enter Choice: ");
                choice = Integer.parseInt(br.readLine());
                switch (choice) {
                    case 1:
                        print_fine();
                        break;
                    default:
                        System.out.println("Enter Valid Choice");
                }
            } while (choice != 2);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void print_fine() {
        int rollno, fine_check = 0, days_check = 0, book_id_check = 0, k = 0;
        String book_name_check = null, book_date_check = null, class_check = null, name_check = null;
        int i = 0;

        try {
            do {

                System.out.println("Enter RollNumber: ");
                rollno = Integer.parseInt(br.readLine());
                st.execute("SELECT * FROM fine_record where Rollno=" + rollno);
                ResultSet resultSet = st.getResultSet();
                while (resultSet.next()) {
                    k = resultSet.getInt(1);
                    name_check = resultSet.getString(2);
                    class_check = resultSet.getString(3);
                    book_id_check = resultSet.getInt(4);
                    book_name_check = resultSet.getString(5);
                    fine_check = resultSet.getInt(6);
                    book_date_check = resultSet.getString(7);
                    days_check = resultSet.getInt(8);


                }
                if (k == 0) {
                    System.out.println("RollNumber Not found, Enter a valid Roll Number with A Due Fine");
                }

            } while (k == 0);
            System.out.println("-----------------------------------------------------------------");
            System.out.println("Book Issued On: " + book_date_check);
            System.out.println("Extra Days Book Was Kept: " + days_check);
            System.out.println("-----------------------------------------------------------------");

            System.out.println("Name: " + name_check + " Class: " + class_check);
            System.out.println("Book Name: " + book_name_check + "  Book ID: " + book_id_check);
            System.out.println("-----------------------------------------------------------------");
            System.out.println("Fine Due: " + fine_check);
            System.out.println("-----------------------------------------------------------------");

            if (zee == 1) {
                int ch,x=0;
                do {

                    System.out.println("Are you sure you want to delete the fine record ?");
                    System.out.println("1.Yes");
                    System.out.println("2.No");
                    ch = Integer.parseInt(br.readLine());

                    switch (ch) {
                        case 1:
                            st.execute("DELETE FROM fine_record WHERE RollNo=" + rollno);
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("FINE RECORD FOR ROLLNO: "+rollno+" DELETED SUCCESSFULLY");
                            System.out.println("-----------------------------------------------------------------");

                            break;
                        case 2:return;
                        default:
                            System.out.println("Please enter a vaild choice");
                            x=1;
                    }
                }while (x==1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void delete_fine_record() {
        System.out.println("--------DELETE FINE RECORD IF PAID--------");
        zee = 1;
        print_fine();
    }
}
