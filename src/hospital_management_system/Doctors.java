/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital_management_system;

import Database_Connection.Database_Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author aadi02
 */
public class Doctors {
    private int doctor_ID;
    private String doctor_Name;
    private String doctor_Qualification;
    private String doctor_Profession;
    private int doctor_Salary;

    public Doctors() 
    {
        this.doctor_ID = ID_Input("staff_details",20000);
        this.doctor_Name = Patient.Name_Input();
        this.doctor_Qualification = Qualification_Input();
        this.doctor_Profession = "Doctor";
        this.doctor_Salary = Salary_Input();
    }

    public Doctors(int doctor_ID, String doctor_Name, String doctor_Qualification, String doctor_Profession, int doctor_Salary) {
        this.doctor_ID = doctor_ID;
        this.doctor_Name = doctor_Name;
        this.doctor_Qualification = doctor_Qualification;
        this.doctor_Profession = doctor_Profession;
        this.doctor_Salary = doctor_Salary;
    }
    

    @Override
    public String toString() {
        return "Doctors{" + "doctor_ID=" + doctor_ID + ", doctor_Name=" + doctor_Name + ", doctor_Qualification=" + doctor_Qualification + ", doctor_Profession=" + doctor_Profession + ", doctor_Salary=" + doctor_Salary + '}';
    }

    
    public int getDoctor_ID() {
        return doctor_ID;
    }

    public String getDoctor_Name() {
        return doctor_Name;
    }

    public String getDoctor_Qualification() {
        return doctor_Qualification;
    }

    public String getDoctor_Profession() {
        return doctor_Profession;
    }

    public int getDoctor_Salary() {
        return doctor_Salary;
    }

    
    
    public int ID_Input(String database_name,int id_set)
    {
        int id=id_set;
    try{
        Database_Connection db = new Database_Connection();
        Connection cn = db.Database_Connectivity();
        String read="SELECT * FROM "+database_name;
        Statement statement = cn.createStatement();
        ResultSet resultset= statement.executeQuery(read);
        System.out.println("-----------------------------------------------------------------------------------");
        while(resultset.next())
            id=Integer.parseInt(resultset.getString(1));
        id++;
        }
            catch(SQLException e)
            {
                System.out.println("Database Connection Failed!-ID");
                }
                return id;
    }
    
    
    
    public String Qualification_Input()
    {
        String qualification;
        System.out.println("Enter Qualification");
        Scanner input = new Scanner(System.in);
    qualification=input.nextLine();
    return qualification;
    }
    
    
    
    public int Salary_Input()
    {
        int salary;
        System.out.println("Enter Salary");
        Scanner input = new Scanner(System.in);
        salary=input.nextInt();
        if(salary>0)
            return salary;
        else
        {
            System.out.println("Salary can not be negative");
            return Salary_Input();
        }
        }
    
    
  
    public static void Save_To_Database(Doctors doctor,String database_name)
    {
        try{
        Database_Connection db = new Database_Connection();
        Connection cn=db.Database_Connectivity();
         String query="INSERT INTO "+database_name+"(Id,Name,Qualification,Profession,Salary) VALUES ('"+doctor.doctor_ID+"','"+doctor.doctor_Name+"','"+doctor.doctor_Qualification+"','"+doctor.doctor_Profession+"','"+doctor.doctor_Salary+"')";
        Statement statement = cn.createStatement();
        statement.executeUpdate(query);
        }
        catch(SQLException e)
            {
                System.out.println(e);
                System.out.println("Database Connection Failed!"+database_name);
            }
    }
    
        public static void Read_Data_From_Database()
        {
            try{
        Database_Connection db = new Database_Connection();
        Connection cn = db.Database_Connectivity();
        String read="SELECT * FROM staff_details";
        Statement statement = cn.createStatement();
        ResultSet resultset= statement.executeQuery(read);
        System.out.println("-----------------------------------------------------------------------------------");
        while(resultset.next())
        {
            System.out.println(resultset.getString(1)+"------"+resultset.getString(2)+"------"+resultset.getString(3)+"------"+resultset.getString(4)+"------"+resultset.getString(5)+"------"+resultset.getString(6));
            System.out.println("-----------------------------------------------------------------------------------");
        }
            }
            catch(SQLException e)
            {
                System.out.println("Database Connection Failed!-Staff");
            }
        }
        
        
        }
      

