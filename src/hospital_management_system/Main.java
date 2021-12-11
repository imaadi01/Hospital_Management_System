package hospital_management_system;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException{
        Rooms room  = new Rooms();
        Staff staff = new Staff();
        Scanner input = new Scanner(System.in);
        String username,password;
        while(true)
        {
        System.out.println(":-:-:-:-:-\tLog In\t-:-:-:-:-:-");
        System.out.println("Enter UserName :-");
        username=input.nextLine();
        System.out.println("Enter Password :-");
        password=input.nextLine();
        String inp=null;
        if(username.equals("admin01") && password.equals("admin"))
        {
        while(true)
        {
        System.out.println("----------------->Press Any of the following keys to go to desire method");
        System.out.println("----------------->For Entry of Patient or Staff press A");
        System.out.println("----------------->For Upading  patient or Staff Information press U");
        System.out.println("----------------->For Searching in patient list press S");
        System.out.println("----------------->For Printing patient or staff list press P");
        System.out.println("----------------->For Advanced Searching in patient list press AS");
        System.out.println("----------------->For Discharge of patients press D");
        System.out.println("----------------->For Deletion of doctor press Z");
        System.out.println("----------------->For Exit press E");
        try
        {
        inp=input.next();
        inp=inp.toLowerCase();
        if(inp.equals("a"))
        {
            
            System.out.println("----------------->For Entry of Patient press 1");
            System.out.println("----------------->For Entry of staff press 2");
            inp=input.next();
        inp=inp.toLowerCase();
        if(inp.equals("1"))
        {
            Patient patient = new Patient();
            room.Enter_Patient(patient);
        }
        else if(inp.equals("2"))
        {
        Doctors doctor = new Doctors();
        staff.Enter_Staff(doctor);
        }
        else
                System.out.println("Wrong Input");
        
        }
        
       else if(inp.equals("u"))
       {
           int id;
           System.out.println("----------------->For Updation of Patient press 1");
            System.out.println("----------------->For Updation of staff press 2");
           inp=input.next();
        if(inp.equals("1"))
        {
            System.out.println("Enter Id");
        id=input.nextInt();
        room.Update_Patient(id);
        }
        else if(inp.equals("2"))
        {
            System.out.println("Enter Id");
            id=input.nextInt();
        }
        else
                System.out.println("Wrong Input");
            
       }
       else if(inp.equals("as"))
       {
               System.out.println("For advance search by Id Press 1\nFor advance search by Name Press 2");
               inp=input.next();
               if(inp.equals("1"))
               {
               System.out.println("Enter Id");
               Patient.Advance_Search_Data_From_Database(input.nextInt());
               }
               else if(inp.equals("2"))
               {
                   Scanner scan = new Scanner(System.in);
               System.out.println("Enter Name");
               Patient.Advance_Search_Data_From_Database(scan.nextLine());
               }
               else
                   System.out.println("Wrong Input");
       }
       else if(inp.equals("s"))
       {
           System.out.println("For Search In Patient press 1\nFor Search In Doctor press 2");
           inp=input.next();
           int id,room_number;
           String name;
           if(inp.equals("1"))
           {
           System.out.println("To search\nBy id prees 1 \nBy Room prees 2 \nBy Name prees 3 \n");
           inp=input.next();
           if(inp.equals("1"))
           {
           System.out.println("Enter id");
           id=input.nextInt();
           room.Search_By_Id(id);
           }
           else if(inp.equals("2"))
           {
           System.out.println("Enter Room Number");
           room_number=input.nextInt();
           room.Search_By_Room(room_number);
           }
           else if(inp.equals("3"))
           {
               Scanner scan = new Scanner(System.in);
           System.out.println("Enter Patient's Name");
           name=scan.nextLine();
           room.Search_By_Name(name);
           }
           else
               System.out.println("Wrong Input!");
           }
           else if(inp.equals("2"))
           {
           System.out.println("To search\nBy id prees 1 \nBy Name prees 2\n");
           inp=input.next();
           if(inp.equals("1"))
           {
           System.out.println("Enter id");
           id=input.nextInt();
            staff.Search_By_Id(id);
           }
           else if(inp.equals("2"))
           {
               Scanner scan = new Scanner(System.in);
           System.out.println("Enter Doctor's Name");
           name=scan.nextLine();
           }
           else
               System.out.println("Wrong Input!");
           }
           else
               System.out.println("Wrong Input!");
       }
       
       else if(inp.equals("e"))
       {
           System.exit(0);
       }
       
       else if(inp.equals("z"))
       {
           System.out.println("Enter id to delete, if you don't know id you can search");
           staff.Delete_Doctor(input.nextInt());
       }
       
       else if(inp.equals("p"))
       {
           System.out.println("If you want to Print patient's deetails Press 1 \nIf you want to Print doctor's deetails Press 2");
           inp=input.next();
           if(inp.equals("1"))
       room.Print_Patients_list();
           else if(inp.equals("2"))
               staff.Print_staff();
           else
               System.out.println("Wrong Input");
       }
       else if(inp.equals("d"))
       {
           System.out.println("Enter Room Number");
        int roomNumber = input.nextInt();
       room.Discharge_Patient(roomNumber);
       }
       
       else
       {
           System.out.println("Please Enter one of the following keys");
       }
       }
        catch(InputMismatchException e)
        {
            System.out.println(e);
        }
        }
    }
        
        // Receptoion Account
        
        
        else if(username.equals("reception01") && password.equals("reception"))
        {
        while(true)
        {
        System.out.println("----------------->Press Any of the following keys to go to desire method");
        System.out.println("----------------->For Entry of Patient A");
        System.out.println("----------------->For Upading  patient press U");
        System.out.println("----------------->For Searching in patient or Staff list press S");
        System.out.println("----------------->For Advanced Searching in patient list press AS");
        System.out.println("----------------->For Discharge of patients press D");
        System.out.println("----------------->For Exit press E");
        try
        {
        inp=input.next();
        inp=inp.toLowerCase();
        if(inp.equals("a"))
        {
            Patient patient = new Patient();
            room.Enter_Patient(patient);
        }
       else if(inp.equals("u"))
       {
           int id;
           System.out.println("Enter Id");
        id=input.nextInt();
        room.Update_Patient(id);
       }
       else if(inp.equals("as"))
       {
           System.out.println("For Patient's Advanced Search Prees 1 \nFor Doctor's Advanced Search Prees 2");
           inp=input.next();
           if(inp.equals("1"))
           {
               System.out.println("For advance search by Id Press 1\nFor advance search by Name Press 2");
               inp=input.next();
               if(inp.equals("1"))
               {
               System.out.println("Enter Id");
               Patient.Advance_Search_Data_From_Database(input.nextInt());
               }
               else if(inp.equals("2"))
               {
                   Scanner scan = new Scanner(System.in);
               System.out.println("Enter Name");
               Patient.Advance_Search_Data_From_Database(scan.nextLine());
               }
           }
           else
               System.out.println("Wrong Input");
       }
       else if(inp.equals("s"))
       {
           System.out.println("For Search In Patient press 1\nFor Search In Doctor press 2");
           inp=input.next();
           int id,room_number;
           String name;
           if(inp.equals("1"))
           {
           System.out.println("To search\nBy id prees 1 \nBy Room prees 2 \nBy Name prees 3 \n");
           inp=input.next();
           if(inp.equals("1"))
           {
           System.out.println("Enter id");
           id=input.nextInt();
           room.Search_By_Id(id);
           }
           else if(inp.equals("2"))
           {
           System.out.println("Enter Room Number");
           room_number=input.nextInt();
           room.Search_By_Room(room_number);
           }
           else if(inp.equals("3"))
           {
               Scanner scan = new Scanner(System.in);
           System.out.println("Enter Patient's Name");
           name=scan.nextLine();
           room.Search_By_Name(name);
           }
           else
               System.out.println("Wrong Input!");
           }
           else if(inp.equals("2"))
           {
           System.out.println("To search\nBy id prees 1 \nBy Name prees 2\n");
           inp=input.next();
           if(inp.equals("1"))
           {
           System.out.println("Enter id");
           id=input.nextInt();
           }
           else if(inp.equals("2"))
           {
               Scanner scan = new Scanner(System.in);
           System.out.println("Enter Doctor's Name");
           name=scan.nextLine();
           }
           else
               System.out.println("Wrong Input!");
           }
           else
               System.out.println("Wrong Input!");
       }
       
       else if(inp.equals("e"))
       {
           System.exit(0);
       }
       else if(inp.equals("p"))
       {
           System.out.println("If you want to Print patient's deetails Press 1 \nIf you want to Print doctor's deetails Press 2");
           inp=input.next();
           if(inp.equals("1"))
       room.Print_Patients_list();
           else if(inp.equals("2"))
               staff.Print_staff();
           else
               System.out.println("Wrong Input");
       }
       else if(inp.equals("d"))
       {
           System.out.println("Enter Room Number");
        int roomNumber = input.nextInt();
       room.Discharge_Patient(roomNumber);
       }
       
       else
       {
           System.out.println("Please Enter one of the following keys");
       }
        }
        catch(InputMismatchException e)
        {
            System.out.println(e);
        }
        }
        }
        else
                System.out.println("Wrong Username OR Password");
            System.out.println("Try Again");
    }
        }
        }
    

