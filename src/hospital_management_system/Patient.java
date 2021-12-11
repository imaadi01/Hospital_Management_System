package hospital_management_system;

import Database_Connection.Database_Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Patient {

    private int Patient_ID;
    private String Patient_name;
    private String Patient_disease;
    private String Patient_Gender;
    private int Patient_Age;
    private String Patient_Admit_Date;
    private int Patient_Bill=5000;     //Room Charges per day
    private int id_length=20;
    private int disease_length=20;

    public Patient(int Patient_ID, String Patient_name, String Patient_disease, String Patient_Gender, int Patient_Age, String Patient_Admit_Date, int Patient_Bill) {
        this.Patient_ID = Patient_ID;
        this.Patient_name = Patient_name;
        this.Patient_disease = Patient_disease;
        this.Patient_Gender = Patient_Gender;
        this.Patient_Age = Patient_Age;
        this.Patient_Admit_Date = Patient_Admit_Date;
        this.Patient_Bill = Patient_Bill;
    }
    
    
    
    
    
    public Patient() {
        
        this.Patient_ID=ID_Input("patient_admitted",10000);
        this.Patient_name = Name_Input().toUpperCase();
        this.Patient_disease = Patient_Disease_Input().toUpperCase();
        this.Patient_Gender=Patient_Gender_Input().toUpperCase();
        this.Patient_Age=Patient_Age_Input();
        this.Patient_Admit_Date=Patient_Admit_Date_Input();
        this.Patient_Bill=Patient_Bill;
    }

    @Override
    public String toString() {
        return "Patient{" + "Patient_ID=" + Patient_ID + ", Patient_name=" + Patient_name + ", Patient_disease=" + Patient_disease + ", Patient_Gender=" + Patient_Gender + ", Patient_Age=" + Patient_Age + ", Patient_Admit_Date=" + Patient_Admit_Date + ", Patient_Bill=" + Patient_Bill + '}';
    }

    

    public int getPatient_ID() {
        return Patient_ID;
    }

    public String getPatient_name() {
        return Patient_name;
    }

    public String getPatient_disease() {
        return Patient_disease;
    }

    public String getPatient_Gender() {
        return Patient_Gender;
    }

    public int getPatient_Age() {
        return Patient_Age;
    }

    public String getPatient_Admit_Date() {
        return Patient_Admit_Date;
    }

    public int getPatient_Bill() {
        return Patient_Bill;
    }

    

    
    
    
    public static String Name_Input()
    {
        String name;
        System.out.println("Enter Patient's Name, name should not contain any number");
        Scanner input = new Scanner(System.in);
        name=input.nextLine();
        char[] c = name.toCharArray();
        boolean name_validty=true;
        for (int i = 0; i < name.length(); i++)
        {
            if(c[i]=='0' || c[i]=='1' || c[i]=='2' || c[i]=='3' || c[i]=='4' || c[i]=='5' || c[i]=='6' || c[i]=='7' || c[i]=='8' || c[i]=='9' )
            {
                name_validty=false;
                System.out.println(c[i]+" Character is not allowed in the name category");
            }
        }
        if(name_validty==true)
           return name;
        else
            return Name_Input();
    }
    
    
    public String Patient_Disease_Input()
    {
        String disease;
        System.out.println("Enter Disease, disease length should not be above "+disease_length);
        Scanner input = new Scanner(System.in);
    disease=input.next();
    if(disease.length()>disease_length)
       return Patient_Disease_Input();
    else
        return disease;
    }
    
    public String Patient_Admit_Date_Input()
    {
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       Date dateobj = new Date();
       String s=df.format(dateobj);
       return s;
    }
    
    
    public String Patient_Gender_Input()
    {
        String gender;
        System.out.println("Enter Gender");
        Scanner input = new Scanner(System.in);
    gender=input.next();
    if(gender.toLowerCase().equals("male") || gender.toLowerCase().equals("female"))
       return gender;
    else
    return Patient_Gender_Input();
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
    
    
    public int Patient_Age_Input()
    {
        int age;
        System.out.println("Enter Age");
        Scanner input = new Scanner(System.in);
        age=input.nextInt();
        if(age>0 && age<=100)
            return age;
        else
            return Patient_Age_Input();
    }
    
    
  
    public static void Save_To_Database(Patient patient,String database_name)
    {
        try{
        Database_Connection db = new Database_Connection();
        Connection cn=db.Database_Connectivity();
         String query="INSERT INTO "+database_name+"(Id,Name,Disease,Gender,Age,Admit_Date,Bill) VALUES ('"+patient.Patient_ID+"','"+patient.Patient_name+"','"+patient.Patient_disease+"','"+patient.Patient_Gender+"','"+patient.Patient_Age+"','"+patient.Patient_Admit_Date+"','"+patient.Patient_Bill+"')";
        Statement statement = cn.createStatement();
        statement.executeUpdate(query);
        }
        catch(SQLException e)
            {
                System.out.println(e);
                System.out.println("Database Connection Failed!");
            }
    }
    
        public static void Read_Data_From_Database()
        {
            try{
        Database_Connection db = new Database_Connection();
        Connection cn = db.Database_Connectivity();
        String read="SELECT * FROM patient_details";
        Statement statement = cn.createStatement();
        ResultSet resultset= statement.executeQuery(read);
        System.out.println("-----------------------------------------------------------------------------------");
        while(resultset.next())
        {
            System.out.println(resultset.getString(1)+"------"+resultset.getString(2)+"------"+resultset.getString(3)+"------"+resultset.getString(4)+"------"+resultset.getString(5)+"------"+resultset.getString(6)+"------"+resultset.getString(7)+"------"+resultset.getString(8));
            System.out.println("-----------------------------------------------------------------------------------");
        }
            }
            catch(SQLException e)
            {
                System.out.println("Database Connection Failed!");
            }
        }
        
        public static void Advance_Search_Data_From_Database(String name) throws SQLException 
        {
        Database_Connection db = new Database_Connection();
        Connection cn = db.Database_Connectivity();
        String search="SELECT * FROM patient_details WHERE Name ='"+name+"'";
        Statement statement = cn.createStatement();
        ResultSet resultset= statement.executeQuery(search);
        while(resultset.next())
        {
            System.out.println("--------------------------------------------------");
            System.out.println(resultset.getString(1)+"\t"+resultset.getString(2)+"\t"+resultset.getString(3)+"\t"+resultset.getString(4)+"\t"+resultset.getString(5));
            System.out.println("--------------------------------------------------");
        }
        }
        public static void Advance_Search_Data_From_Database(int id) throws SQLException 
        {
        Database_Connection db = new Database_Connection();
        Connection cn = db.Database_Connectivity();
        String search="SELECT * FROM patient_details WHERE Id ='"+id+"'";
        Statement statement = cn.createStatement();
        ResultSet resultset= statement.executeQuery(search);
        while(resultset.next())
        {
            System.out.println("--------------------------------------------------");
            System.out.println(resultset.getString(1)+"\t"+resultset.getString(2)+"\t"+resultset.getString(3)+"\t"+resultset.getString(4)+"\t"+resultset.getString(5));
            System.out.println("--------------------------------------------------");
        }
        }
        
        public static void Update_Data_Null_Database(Patient patient,int room)
        {
            String update="UPDATE patient_details SET Id='"+patient.Patient_ID+"',Name='"+patient.Patient_name+"', Disease='"+patient.Patient_disease+"', Gender='"+patient.Patient_Gender+"', Age='"+patient.Patient_Age+"', Admit_Date='"+patient.Patient_Admit_Date+"', Bill='"+patient.Patient_Bill+"' WHERE Room="+room;
            try{
        Database_Connection db = new Database_Connection();
        Connection cn = db.Database_Connectivity();
            PreparedStatement statement = cn.prepareStatement(update);
        int i=statement.executeUpdate();
                System.out.println("Update Succeessful");
            }
            catch(SQLException e)
            {
                System.out.println(e);
            }
        }
        
        }

      
