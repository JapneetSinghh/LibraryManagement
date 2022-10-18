package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class  Issue_Return extends Librarymanagerportal {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String Bookname = null;
    String Book_1, Book_2, Book_3;
    int k = 0, number_of_books_issued = 0;
    private int rollno, id,  run = 1;

    public void Issue() {
        try {
            String str1 = null;
            int flag = 0;
            do {
                do {
// 
                    System.out.println("Enter your Rollno: ");
                    rollno = Integer.parseInt(br.readLine());
                    st.execute("Select * from students_data where RollNo=" + rollno);
                    ResultSet result = st.getResultSet();
                    while (result.next()) {
                        k = result.getInt(1);
                        str1 = result.getString("Name");
                        number_of_books_issued = result.getInt("No");
                        Book_1 = result.getString("Book_1");
                        Book_2 = result.getString("Book_2");
                        Book_3 = result.getString("Book_3");

                    }
                    if (k == 0) {
                        System.out.println("Roll Number does not exist, Enter valid Roll number");
                    }
                } while (k == 0);
                System.out.println("------------------------------------------------");
                System.out.println("Welcome " + str1);
                System.out.println("------------------------------------------------");

                System.out.println("You have Issued " + number_of_books_issued + " books from our Library");
                if (number_of_books_issued >= 3) {
                    System.out.println("You can't issue more books, Return previous books to issue more\nYou can issue maximum 3 books at a time");
                    System.out.println("You have already issued \n" + Book_1 + "\n" + Book_2 + "\n" + Book_3);
                    run = 0;
                }
                else {
                    if (number_of_books_issued == 1)
                        System.out.println("You have already issued " + Book_1);
                    else if (number_of_books_issued == 2)
                        System.out.println("You have already issued \n" + Book_1 + "\n" + Book_2);

                    System.out.println("Displaying Books We have in our Library");
                    display_list();
                    System.out.println("------------------------------------------------------------------");

                    k = 0;
                    String str3=null;
                    do {
                        System.out.println("Enter the ID of the book you want to issue: ");
                        id = Integer.parseInt(br.readLine());
                        st.execute("SELECT * from library_stock WHERE ID=" + id);
                        ResultSet resultSet = st.getResultSet();
                        while (resultSet.next()) {
                            k = resultSet.getInt("ID");
                            str3 = resultSet.getString("Availability");
                            Bookname = resultSet.getString("Name");
                        }

                        if (k == 0) {
                            System.out.println("Book ID not found, Please enter Valid ID");
                        }
                        else {
                            check_book_availability(str3);

                        }


                    } while (k == 0);
//                    String  str2="Available";
//                    int z=str2.compareTo(str3);
//                    System.out.println("-------------------------------------------");
//                    if (z == 0 ) {
//                        System.out.println(Bookname + " Book Available In Our Library");
//                        System.out.println(str3);
//                        k=1;
//                    }
//                    else {
//                        System.out.println("Sorry, Someone has already issued " + Bookname + " book...Choose Another Book");
//                        k = 0;
//                        return;
//                    }
                    System.out.println("------------------------------------------------");

                    System.out.println("For how many days you want this book ?");
                    int days = Integer.parseInt(br.readLine());
                    String date;
                    System.out.println("Enter today's date: ");
                    date = br.readLine();
                    if (number_of_books_issued == 0) {
                        st.execute("UPDATE students_data SET Book_1='" + Bookname + "' where RollNo= " + rollno);
                    } else if (number_of_books_issued == 1) {
                        st.execute("UPDATE students_data SET Book_2='" + Bookname + "' where RollNo= " + rollno);
                    } else if (number_of_books_issued == 2) {
                        st.execute("UPDATE students_data SET Book_3='" + Bookname + "' where RollNo= " + rollno);

                    }
                    number_of_books_issued = number_of_books_issued + 1;
                    st.execute("UPDATE library_stock SET Issued_by='" + str1 + "',Days_to_return=" + days + ",Date_Issued='" + date + "',Lib_Card_Number=147" + rollno + ",Availability='Issued' WHERE ID=" + k);
                    st.execute("UPDATE students_data SET NO=" + number_of_books_issued + " where RollNo= " + rollno);
                    System.out.println("------------------------------------------------");
                    System.out.println(Bookname+" BOOK ISSUED SUCCESSFULLY ");
                    System.out.println("Issuer Name: "+str1);
                    System.out.println("Library Card Number: 147"+rollno);
                    System.out.println("Issued on: "+date);
                    System.out.println("Issued for "+days+" days");
                    System.out.println("Book ID: "+id);
                    System.out.println("Books Issued Till Now: "+number_of_books_issued);

                    System.out.println("------------------------------------------------");

                    System.out.println("NOTE: Return the book after " + days + " days or else you will be fined !");

                }

                int choice, flag2 = 0;
                if (run == 1) {
                    do {
                        System.out.println("Do you want to issue one more book ");
                        System.out.println("1.Yes\n2.No\nEnter choice 1 or 2");
                        choice = Integer.parseInt(br.readLine());
                        switch (choice) {
                            case 1:
                                flag = 0;
                                System.out.println("==ISSUING ONE MORE BOOK==");
                                break;
                            case 2:
                                flag = 1;
                                System.out.println("==Have a nice day==");
                                break;
                            default:
                                System.out.println("Enter valid choice 1 or 2");
                                flag2 = 1;
                        }
                    } while (flag2 == 1);
                }
                else
                    flag=1;
            } while (flag == 0);

        } catch (IOException e) {
            System.out.println(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
public void check_book_availability(String str3){
    String  str2="Available";
                    int z=str2.compareTo(str3);
                    System.out.println("-------------------------------------------");
                    if (z == 0 ) {
                        System.out.println(Bookname + " Book Available In Our Library");
                        System.out.println(str3);

                    }
                    else {
                        System.out.println("Sorry, Someone has already issued " + Bookname + " book...Choose Another Book");
                        k = 0;
                        return;
                    }
}

void date_of_issue(){

        // Instantiate a Date object
        Date date = new Date();

        // display time and date
        System.out.printf("%1$s %2$tB %2$td, %2$tY", "Today's Date:", date);
    System.out.println();
}

}
