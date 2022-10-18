package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Return extends Teachersportal  {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     int fine_check=0,rollno = 0;
     int  flag = 0, rollnocheck = 0, no_of_books_check = 0;
     String book_1_check = null, book_2_check = null, book_3_check = null, name_check = null, class_check = null;

    public void return_book() {
        System.out.println("WELCOME STUDENT YOU CAN RETURN YOUR BOOK FROM HERE");
        try {
            do {
                System.out.println("Enter your Roll number:");
                rollno = Integer.parseInt(br.readLine());
                st.execute("SELECT * from students_data where RollNo=" + rollno);
                ResultSet result1 = st.getResultSet();

                while (result1.next()) {
                    rollnocheck = result1.getInt(1);
                    book_1_check = result1.getString("Book_1");
                    book_2_check = result1.getString("Book_2");
                    book_3_check = result1.getString("Book_3");
                    name_check = result1.getString("Name");
                    no_of_books_check = result1.getInt(4);
                    class_check = result1.getString("Class");
                    fine_check=result1.getInt("Fine");

                }
                if (rollnocheck == rollno) {
                    flag = 1;
                }
            } while (flag == 0);
            System.out.println("------------------------------------------------");
            System.out.println("Welcome " + name_check);
            System.out.println("------------------------------------------------");
            System.out.println("Your Details: ");
            System.out.println("Class: " + class_check);
            System.out.println("Library Card Number: 147" + rollno);
            System.out.println("Books you have issued: ");
            System.out.println("Book 1: " + book_1_check);
            System.out.println("Book 2: " + book_2_check);
            System.out.println("Book 3: " + book_3_check);
            System.out.println("You have issued " + no_of_books_check + " book till now");
            System.out.println("Fine Due Rs "+fine_check);
            System.out.println("------------------------------------------------");


            if (no_of_books_check == 1) {
                System.out.println("Returning " + book_1_check);
                update_book_record(book_1_check,no_of_books_check,1);

            } else if (no_of_books_check == 2) {
                int choice, flag2 = 0;
                do {
                    System.out.println("Which book you want to return: ?");
                    System.out.println("1." + book_1_check);
                    System.out.println("2." + book_2_check);
                    System.out.println("Enter Choice: ");
                    choice = Integer.parseInt(br.readLine());
                    if (choice == 1) {
                        System.out.println("Returning " + book_1_check);
                        update_book_record(book_1_check,no_of_books_check,choice);
                        update_student_record(book_2_check,rollnocheck,choice,2);
//                        st.executeUpdate("UPDATE students_data SET Book_"+choice+"='"+book_2_check+"',Book_"+choice2+"='Not Issued' WHERE RollNo="+rollnocheck);

                        flag2 = 1;
                    } else if (choice == 2) {
                        System.out.println("Returning " + book_2_check);
//                        update_student_record(name_check,1,book_2_check,2);
                        update_book_record(book_2_check,no_of_books_check,choice);
                        flag2 = 2;

                    } else {
                        System.out.println("Please Enter a valid choice");
                        flag2 = 0;
                    }
                } while (flag2 == 0);
            } else if (no_of_books_check == 3) {
                int choice, flag2 = 0;
                do {
                    System.out.println("Which book you want to return: ?");
                    System.out.println("1." + book_1_check);
                    System.out.println("2." + book_2_check);
                    System.out.println("3." + book_3_check);

                    System.out.println("Enter Choice: ");
                    choice = Integer.parseInt(br.readLine());
                    if (choice == 1) {
                        System.out.println("Returning " + book_1_check);
//                        update_student_record(name_check,1,book_2_check,2);
                        update_book_record(book_1_check,no_of_books_check,choice);
                        update_student_record(book_3_check,rollnocheck,choice,3);

                        flag2 = 1;
                    } else if (choice == 2) {
                        System.out.println("Returning " + book_2_check);
                        flag2 = 1;

                        update_book_record(book_2_check,no_of_books_check,choice);
                        update_student_record(book_3_check,rollnocheck,choice,3);


                    } else if (choice == 3) {
                        System.out.println("Returning " + book_3_check);
                        flag2 = 2;
                        update_book_record(book_3_check,no_of_books_check,choice);

                    } else {
                        System.out.println("Please Enter A valid choice");
                        flag2 = 0;
                    }
                } while (flag2 == 0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
   public void update_student_record(String book_2_check,int rollnocheck,int choice,int choice2){
        try {
            st.executeUpdate("UPDATE students_data SET Book_"+choice+"='"+book_2_check+"',Book_"+choice2+"='Not Issued' WHERE RollNo="+rollnocheck);

//            st.executeUpdate("UPDATE students_data SET Book_1='"+book_2_check+"',Book_2='Not Issued' WHERE RollNo="+rollnocheck);
        } catch (SQLException e) {
            e.printStackTrace();
        }
   }
    String book_name_check , book_author_check = null, date_issued_check = null, avalablility_check = null, issued_by = null;
    int days_to_return_check = 0, lib_card_number_check = 0, price_check = 0, book_id_check = 0;

    public void update_book_record(String bookname,int no_of_books,int book_num) {
        System.out.println("------------------------------------------------");
        System.out.println("UPDATING BOOK RECORD FOR " + bookname);
        System.out.println("------------------------------------------------");
        book_name_check=bookname;



        try {
            st.execute("SELECT * FROM library_stock where Name='" + bookname + "'");
            ResultSet result = st.getResultSet();
            while (result.next()) {
                book_author_check = result.getString("Author");
                days_to_return_check = result.getInt("Days_to_return");
                book_id_check = result.getInt(1);
                lib_card_number_check = result.getInt("Lib_Card_Number");
                price_check = result.getInt("Price");
                issued_by = result.getString("Issued_by");
                date_issued_check = result.getString("Date_Issued");
                avalablility_check = result.getString("Availability");
            }
            System.out.println("Book Name: " + book_name_check);
            System.out.println("Author: " + book_author_check);
            System.out.println("Book ID: " + book_id_check);
            System.out.println("Book Price: " + price_check);
            System.out.println("Book Availability: " + avalablility_check);
            System.out.println("------------------------------------------------");
            System.out.println("Issued by: " + issued_by);
            System.out.println("Library Card Number: " + lib_card_number_check);
            System.out.println("Days to return: " + days_to_return_check);
            System.out.println("Date Issued: " + date_issued_check);
            System.out.println("------------------------------------------------");
            int days;                int extra = 0;

            System.out.println("How many days ago  you took the book ? ");
            days = Integer.parseInt(br.readLine());
            if (days > days_to_return_check) {
                System.out.println("------------------------------------------------");

                System.out.println("YOU ARE NOT RETURNING THIS BOOK ON TIME");
                System.out.println("You have to pay a fine i.e Rs 30 per day");
                extra = days - days_to_return_check;
                System.out.println("You had the book for extra " + extra + " days,\nYou were Supposed to return the book after " + days_to_return_check + " days of issuing");
                extra = extra * 30;
                System.out.println("------------------------------------------------");
                System.out.println("Fine for this book: Rs "+extra);
                extra=extra+fine_check;
                System.out.println("Previous Fine: Rs "+fine_check);
                System.out.println("Calculated fine is: Rs " + extra);

                fine_table(rollno,name_check,class_check,book_id_check,book_name_check,extra,date_issued_check,days);

                System.out.println("Pay this fine in next 5 working days or it will be doubled i.e Rs " + extra * 2);
                System.out.println("------------------------------------------------");

            } else {
                System.out.println("Book Returned on time");
                System.out.println("------------------------------------------------");

            }
            no_of_books=no_of_books-1;
            st.execute("UPDATE library_stock SET Availability='Available',Issued_by='none',Date_Issued='DD/MM/YYYY',Days_to_return=0,Lib_Card_Number=0 WHERE ID="+book_id_check);
            st.executeUpdate("UPDATE students_data SET No="+no_of_books+",Book_"+book_num+"='Not Issued',Fine="+extra+" WHERE Name='"+issued_by+"'");


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
