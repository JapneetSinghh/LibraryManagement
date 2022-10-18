package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        Teachersportal obj = new Teachersportal();
        Librarymanagerportal lib=new Librarymanagerportal();
        Issue_Return student=new Issue_Return();
        Return book=new Return();
        Fine fine1=new Fine();
        student.date_of_issue();

//        SQLComm tril=new SQLComm();
//        tril.updatetrial();
        int choice, choice2;
        try {

            do {
                System.out.println("------------------------------------------------");
                System.out.println("------------------SFS LIBRARY-------------------");
                System.out.println("------------------------------------------------");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("How Can I Help You ? ");
                System.out.println("------------------------------------------------");
                System.out.println("1.Open Teachers Portal");
                System.out.println("- - - - - - - - - - - - - - - - -");
                System.out.println("2.Issue a Book");
                System.out.println("- - - - - - - - - - - - - - - - -");
                System.out.println("3.Return a Book");
                System.out.println("- - - - - - - - - - - - - - - - -");
                System.out.println("4.Check Fine Status");
                System.out.println("- - - - - - - - - - - - - - - - -");
                System.out.println("5.Open Library Manager Portal");
                System.out.println("- - - - - - - - - - - - - - - - -");
                System.out.println("6.Exit");
                System.out.println("- - - - - - - - - - - - - - - - -");
                System.out.print("Enter choice: ");
                choice = Integer.parseInt(br.readLine());


                switch (choice) {
                    case 1:
                        try {
                            do {
                                System.out.println("------------------------------------------------");
                                System.out.println("-----------Welcome to Teachers Portal-----------");
                                System.out.println("------------------------------------------------");
                                System.out.println("1.Create Library Card ");
                                System.out.println("- - - - - - - - - - - - - - - - -");
                                System.out.println("2.Edit Record ");
                                System.out.println("- - - - - - - - - - - - - - - - -");
                                System.out.println("3.Display All Records ");
                                System.out.println("- - - - - - - - - - - - - - - - -");
                                System.out.println("4.Display List of Students ");
                                System.out.println("- - - - - - - - - - - - - - - - -");
                                System.out.println("5.Delete Record ");
                                System.out.println("- - - - - - - - - - - - - - - - -");
                                System.out.println("6.Search Record");
                                System.out.println("- - - - - - - - - - - - - - - - -");
                                System.out.println("7.Exit");
                                System.out.println("- - - - - - - - - - - - - - - - -");
                                System.out.print("Enter choice: ");
                                choice2 = Integer.parseInt(br.readLine());
                                switch (choice2) {
                                    case 1:
                                        obj.insert_rec();
                                        System.out.println("Data Inserted Successfully");
                                        break;
                                    case 2:
                                        obj.edit_rec();
                                        System.out.println("Data Edited Successfully");
                                        break;
                                    case 3:
                                        obj.display_all();
                                        break;
                                    case 4:
                                        obj.display_list();
                                        break;
                                    case 5: obj.delete_record();
                                        break;
                                    case 6:obj.search_record();

                                        break;
                                    case 7:break;

                                    default:
                                        System.out.println("Please Enter A Valid Choice");
                                }
                            } while (choice2 != 7);

                        } catch (IOException e) {
                            System.out.println(e);
                        }
                        break;

//                ISSUE A BOOK
                    case 2:student.Issue();
                        break;
//                RETURN A BOOK
                    case 3:book.return_book();
                        break;
//                    CHECK FINE STATUS
                    case 4:
                    fine1.fine_status();
                        break;
//                        Open Library Managers portal
                    case 5:
                        do {
                            System.out.println("------------------------------------------------");

                            System.out.println("Welcome Librarian ");
                            System.out.println("------------------------------------------------");

                            System.out.println("1.Display All Books in Library");
                            System.out.println("- - - - - - - - - - - - - - - - -");
                            System.out.println("2.Search Book Record");
                            System.out.println("- - - - - - - - - - - - - - - - -");
                            System.out.println("3.Insert New Book in Library stock");
                            System.out.println("- - - - - - - - - - - - - - - - -");
                            System.out.println("4.Delete Book Record");
                            System.out.println("- - - - - - - - - - - - - - - - -");
                            System.out.println("5.Update Book Record");
                            System.out.println("- - - - - - - - - - - - - - - - -");
                            System.out.println("6.Delete paid fine records");
                            System.out.println("- - - - - - - - - - - - - - - - -");
                            System.out.println("7.Exit");
                            System.out.println("- - - - - - - - - - - - - - - - -");
                            System.out.println("Enter choice: ");
                            choice2=Integer.parseInt(br.readLine());

                            switch (choice2){
                                case 1:lib.display_list();
                                break;
                                case 2:lib.search_list();
                                    break;
                                case 3:lib.insert_stock();
                                    break;
                                case 4:lib.delete_record();
                                    break;
                                case 5:lib.edit_stock();
                                    break;
                                case 6:fine1.delete_fine_record();
                                case 7:break;

                                default:
                                    System.out.println("Enter valid choice");
                            }
                        }while (choice2!=7);
                        break;
//                    EXIT
                    case 6:
                        return;
                }


            } while ((choice != 6));
        } catch (IOException e) {
            System.out.println(e);
        }


    }
}
