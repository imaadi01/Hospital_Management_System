package hospital_management_system;

import Database_Connection.Database_Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Staff {

    Doctors doctors_entry[];
    private int[] vacant_space;
    private int vacant_room,update_room;
    int doctor_count=0;
    
    public Staff() throws SQLException
    {
    doctors_entry = new Doctors[5];
    vacant_room=0;
    vacant_space = new int[20];
    for(int i=0;i<vacant_space.length;i++)
        vacant_space[i]=-1;
          
    
    
        Database_Connection db = new Database_Connection();
        Connection cn = db.Database_Connectivity();
        String read="SELECT * FROM staff_details";
        Statement statement = cn.createStatement();
        ResultSet resultset= statement.executeQuery(read);
        while(resultset.next())
        {
            Doctors doctor = new Doctors(Integer.parseInt(resultset.getString(1)),resultset.getString(2),resultset.getString(3),resultset.getString(4),Integer.parseInt(resultset.getString(5)));
            increase_length();
            
                doctors_entry[doctor_count]=doctor;
                doctor_count++;
        }
        System.out.println(doctors_entry[doctor_count]+":"+doctor_count);
        }
    public void Enter_Staff(Doctors doctor) throws SQLException
    {
        increase_length();
        if(vacant_space[0]!=-1)
        {
        doctors_entry[vacant_space[0]]=doctor;
        for(int i=0;i<vacant_room;i++)
        {
            vacant_space[i]=vacant_space[i+1];
        }
        vacant_room--;
        }
        else
        {
        doctors_entry[doctor_count]=doctor;
        doctor_count++;
        }
        Doctors.Save_To_Database(doctor,"staff_details");
        }
    
        
        
    
        public Doctors Delete_Doctor(int id) throws SQLException
        {
            Doctors delete_doctor = null;
            System.out.println(Search_By_Id(id));
            Database_Connection db = new Database_Connection();
        Connection cn = db.Database_Connectivity();
        String delete="DELETE FROM staff_details WHERE Id="+id;
        PreparedStatement statement = cn.prepareStatement(delete);
        int i=statement.executeUpdate();
        if(i>0)
        {
                System.out.println("Deleted Successfully");
        }
        else
        {
                System.out.println("Deletion Failed");
        }
        if(update_room!=-1)
        {
         delete_doctor = doctors_entry[update_room];
         doctors_entry[update_room]=null;
         vacant_space[vacant_room]=update_room;
         vacant_room++;
        }
        return delete_doctor;
        }
        
        public Doctors Search_By_Id(int id)
    {
        Doctors p = null;
        int a=0;
        for (int i = 0; i < doctor_count; i++)
        {
            if(doctors_entry[i].getDoctor_ID()==id)
            {
                System.out.println(doctors_entry[i]);
                p=doctors_entry[i];
                update_room=i;
                a++;
            }
        }
        if(a==0)
        {
            update_room=-1;
            System.out.println("Id not Found!");
        }
            return p;
    }
        
        public void increase_length()
    {
    if(vacant_space[0]==-1 && doctor_count>=doctors_entry.length)
        {
            Doctors[] newDoctorEntry = new Doctors[doctors_entry.length*2];   // Vaccant room index      Enter Index        Delete Index      Printing
            for(int i=0;i<doctor_count;i++)                                      //   -1                    0                null                  1
                newDoctorEntry[i]=doctors_entry[i];                           //     0                     null                 0                     
            doctors_entry=newDoctorEntry;                                     //     -1                        0                                   1
            System.out.println("Length Increased");
        }
    }
    
//    public void Save_To_Database()
//    {
//        try{
//        Database_Connection db = new Database_Connection();
//        Connection cn=db.Database_Connectivity2();
//         String query="INSERT INTO staff_details (Id,Name,Qualification,Profession,Timing,Salary) VALUES ('"+staff_ID+"','"+staff_Name+"','"+staff_Qualification+"','"+staff_profession+"','"+staff_timing+"','"+staff_Salary+"')";
//        Statement statement = cn.createStatement();
//        statement.executeUpdate(query);
//        }
//        catch(SQLException e)
//            {
//                System.out.println(e);
//                System.out.println("Database Connection Failed!");
//            }
//    }
    
    
    public static void Read_Staff_Data_From_Database()
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
    
    public static void Delete_From_Database(int id) throws SQLException
        {
            Database_Connection db = new Database_Connection();
        Connection cn = db.Database_Connectivity();
        String delete="DELETE FROM staff_details WHERE Id="+id;
        PreparedStatement statement = cn.prepareStatement(delete);
        int i=statement.executeUpdate();
        if(i>0)
                System.out.println("Deleted Successfully");
        else
                System.out.println("Deletion Failed");
        }
    
    
    
    public void Print_staff()
    {
        for(int i=0;i<doctor_count;i++)
        System.out.println(doctors_entry[i]);
    }
    
}
