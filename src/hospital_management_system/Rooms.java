package hospital_management_system;

import Database_Connection.Database_Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Rooms 

{
    private int room_number;
    private int[] vacant_room_number;
    private int vacant_room,update_room;
    private Patient[] patients_entry;
    public Rooms() throws SQLException
    {
    patients_entry = new Patient[5];
    vacant_room=0;
    vacant_room_number = new int[20];
    for(int i=0;i<vacant_room_number.length;i++)
        vacant_room_number[i]=-1;
          
    
    
        Database_Connection db = new Database_Connection();
        Connection cn = db.Database_Connectivity();
        String read="SELECT * FROM patient_details";
        Statement statement = cn.createStatement();
        ResultSet resultset= statement.executeQuery(read);
        while(resultset.next())
        {
            room_number=Integer.parseInt(resultset.getString(1))-1;
            Patient patient = new Patient(Integer.parseInt(resultset.getString(2)),resultset.getString(3),resultset.getString(4),resultset.getString(5),Integer.parseInt(resultset.getString(6)),resultset.getString(7),Integer.parseInt(resultset.getString(8)));
            increase_length();
                patients_entry[room_number]=patient;
            if(Integer.parseInt(resultset.getString(2))==0)
            {
            vacant_room_number[vacant_room]=Integer.parseInt(resultset.getString(1))-1;
            vacant_room++;
            }
        }
        }
    
    
    public void increase_length()
    {
    if(vacant_room_number[0]==-1 && room_number>=patients_entry.length)
        {
            Patient[] newPatientEntry = new Patient[patients_entry.length*2];   // Vaccant room index      Enter Index        Delete Index      Printing
            for(int i=0;i<room_number;i++)                                      //   -1                    0                null                  1
                newPatientEntry[i]=patients_entry[i];                           //     0                     null                 0                     
            patients_entry=newPatientEntry;                                     //     -1                        0                                   1
        }
    }
    
    
    
    public void Enter_Patient(Patient patient) throws SQLException
    {
        Database_Connection db = new Database_Connection();
        Connection cn = db.Database_Connectivity();
        increase_length();
        if(vacant_room_number[0]!=-1)
        {
        patients_entry[vacant_room_number[0]]=patient;
        Patient.Update_Data_Null_Database(patient,vacant_room_number[0]+1);
        for(int i=0;i<vacant_room;i++)
        {
            vacant_room_number[i]=vacant_room_number[i+1];
        }
        vacant_room--;
        }
        else
        {
            room_number++;
        patients_entry[room_number]=patient;
        
        Patient.Save_To_Database(patient,"patient_details");
        }
        Patient.Save_To_Database(patient,"patient_admitted");
        }
    
    public Patient Discharge_Patient(int room) throws SQLException
    {
        Scanner scan = new Scanner(System.in);
        Patient discharged = patients_entry[room-1];
        System.out.println("Patient Details:");
        System.out.println(patients_entry[room-1]);
        Database_Connection db = new Database_Connection();
        Connection cn = db.Database_Connectivity();
        String discahrge1="UPDATE patient SET Id='"+patients_entry[room-1].getPatient_ID()+"',Name='"+patients_entry[room-1].getPatient_name()+"', Disease='"+patients_entry[room-1].getPatient_disease()+"', Gender='"+patients_entry[room-1].getPatient_Gender()+"', Age='"+patients_entry[room-1].getPatient_Age()+"', Admit_Date='"+patients_entry[room-1].getPatient_Admit_Date()+"', Bill='' WHERE Id="+patients_entry[room-1].getPatient_ID();
        PreparedStatement statement_update = cn.prepareStatement(discahrge1);
        int i=statement_update.executeUpdate();
        String discahrge="UPDATE patient SET Id=0,Name='null', Disease='null', Gender='null', Age=0, Admit_Date='0', Bill=0 WHERE Room="+room;
        PreparedStatement statement = cn.prepareStatement(discahrge);
        i=statement.executeUpdate();
        if(i>0)
                System.out.println("Update Successfully");
        else
                System.out.println("Update Failed");
        patients_entry[room-1]=null;
        vacant_room_number[vacant_room]=(room-1);
        vacant_room++;
        return discharged;
    }
    
    public void Update_Patient(int id)
    {
            Patient p_for_update = Search_By_Id(id);
            int age=p_for_update.getPatient_Age();
            String name=p_for_update.getPatient_name();
            String disease=p_for_update.getPatient_disease();
            String gender=p_for_update.getPatient_Gender();
            String date=p_for_update.getPatient_Admit_Date();
            int bill=p_for_update.getPatient_Bill();
            int option;
            Scanner scan = new Scanner(System.in);
            Scanner scan1 = new Scanner(System.in);
            System.out.println("If you want to update name press 1");
            System.out.println("If you want to update disease press 2");
            System.out.println("If you want to update age press 3");
            option=scan.nextInt();
            if(option==1){
                System.out.println("Enter updated name");
                name=scan1.nextLine();
            }
            else if(option==2)
            {
            System.out.println("Enter updated Disease");
                disease=scan1.nextLine();
            }
            else if(option==3)
            {
                System.out.println("Enter updated Age");
                age=scan.nextInt();
            }
            
            Patient patient = new Patient(id, name.toUpperCase(), disease.toUpperCase(), gender.toUpperCase(), age,date,bill);
            patients_entry[update_room]=patient;
            Patient.Update_Data_Null_Database(patient,update_room+1);
    }
    
    
    public void Search_By_Name(String name)
    {
        name=name.toUpperCase();
        int a=0;
        for (int i = 0; i <= room_number; i++)
        {
            if(patients_entry[i].getPatient_name().equals(name))
            {
                System.out.println(patients_entry[i]);
                a++;
            }
        }
        if(a==0)
            System.out.println("No patient exist with such name");
    
    }
    public void Search_By_Room(int room)
    {
        if(room<=room_number)
        System.out.println(patients_entry[room-1]);
        else
            throw new NoSuchElementException();
    }
    
    public Patient Search_By_Id(int id)
    {
        Patient p = null;
        int a=0;
        for (int i = 0; i < room_number; i++)
        {
            if(patients_entry[i].getPatient_ID()==id)
            {
                System.out.println("Room: "+(i+1)+" "+patients_entry[i]);
                p=patients_entry[i];
                update_room=i;
                a++;
            }
        }
        if(a==0)
            System.out.println("Id not Found!");
        return p;
    }
    

    
    public void Print_Patients_list()
    {
    for(int i=0;i<=room_number;i++)
    {
        System.out.println(patients_entry[i]);
    }
    }
}
