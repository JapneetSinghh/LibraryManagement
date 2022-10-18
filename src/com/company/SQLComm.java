package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class SQLComm extends Teachersportal{
    //    SQL COMMANDS USED
    public void create_table(){
        try {
            st.execute("CREATE TABLE IF NOT EXISTS students_data "+"(RollNo int,Class text,Name text,No_Of_Books_Issued int,Book_1 text,Book_2 text,Book_3 text,Fine int)");
        }catch (SQLException e){
            System.out.println("Something Went Wrong "+e.getMessage());
        }
    }
    public void insert_table(){
        System.out.println("Inserting Data In Table");
        try {
            st.executeUpdate("INSERT INTO students_data VALUES"+"(25,'Class','Name',3,'Book_1','Book_2' ,'Book_3',500)");

        }catch (SQLException e){
            System.out.println("Something Went Wrong "+e.getMessage());
        }
    }

    public void edit(){

        try {
            System.out.println("What do you want to edit: ");
            st.executeUpdate("UPDATE students_data SET Name='TRIAL' WHERE RollNo=25");


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
public void updatetrial(){
    try {
        System.out.println("UPDATING ");
        st.executeUpdate("UPDATE students_data SET Book_1='Let Us C++',Book_2='Not Issued' WHERE RollNo="+1);


    } catch (SQLException e) {
        e.printStackTrace();
    }

}

}
