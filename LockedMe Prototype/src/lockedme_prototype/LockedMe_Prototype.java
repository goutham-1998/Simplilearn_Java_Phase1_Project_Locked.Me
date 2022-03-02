package lockedme_prototype;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class LockedMe_Prototype {
	
	static String Dir;
	File folder_name;
	
	//DIRECTORY
	public LockedMe_Prototype()
	{
		Dir = System.getProperty("user.dir");
		folder_name = new File(Dir+"/files");
		
		if(!folder_name.exists())
		{
			folder_name.mkdirs();
			System.out.println("Directory : " + folder_name.getAbsolutePath());
		}
		
	}
	
	//DECLARATION OF MENUS
	private static final String MainMenu =
            "\n\tMain Menu "
            + "\nSelect your option: \n"+
                    "1) List files in directory\n"+
                    "2) Add / Delete / Search\n"+
                    "3) Exit";

	private static final String SubMenu =
            "   \nSelect any of the following: \n"+
                    "a) Add file\n"+
                    "b) Delete file\n"+
                    "c) Search file\n"+
                    "d) GoBack";
	
	
    //OPTIONS FOR PRIMARY MENU
	void MainMenu() {
        System.out.println(MainMenu);
        try{
            Scanner sc = new Scanner(System.in);
            int opt = sc.nextInt();
            switch (opt){
                case 1 : {
                    ViewFiles();
                    MainMenu();
                }
                case 2 : {
                	SubMenu();
                }
                case 3 : {
                    System.out.println("Thanks for using LockedMe.com, Have a great day!!!");
                    System.exit(0);
                }
                default: MainMenu();
            }
        }
        catch (Exception e){
            System.out.println("Please enter 1 / 2 / 3");
            MainMenu();
        }
    }
    
	
	
    //OPTIONS FOR SUB-MENU
	private void SubMenu() {
		System.out.println(SubMenu);
        try{
            Scanner sc = new Scanner(System.in);
            char[] input = sc.nextLine().toLowerCase().trim().toCharArray();
            char option = input[0];

            switch (option){
                case 'a' : {
                    System.out.print("-> Enter File Name to Add : ");
                    String filename = sc.next().trim().toLowerCase();
                    retriveFile(filename);
                    break;
                }
                case 'b' : {
                    System.out.print("-> Enter File Name to Delete : ");
                    String filename = sc.next().trim();
                    deleteFile(filename);
                    break;
                }
                case 'c' : {
                    System.out.print("-> Enter File Name to Search : ");
                    String filename = sc.next().trim();
                    searchFile(filename);
                    break;
                }
                case 'd' : {
                    System.out.println("Exit to Main Menu");
                    MainMenu();
                    break;
                }
                default : System.out.println("Invalid selection, Please select a / b / c / d");
            }
            SubMenu();
        }
        catch (Exception e){
            System.out.println("Invalid selection, Please select a / b / c / d");
            SubMenu();
        }
		
	}
	

/////////////////////////////////FOR SEARCHING FILES/////////////////////////////////////
	private void searchFile(String filename) {
        String[] list = folder_name.list();
        for (String file: list) {
            if (filename.equals(file)) {
                System.out.println("Your Searched file " + filename + " exists at " + folder_name);
                return;
            }
        }
        System.out.println("No such file exists");    //IN CASE OF WRONG FILE NAME
    }

////////////////////////////////////FOR DELETING FILES////////////////////////////////////
	private void deleteFile(String filename) {
        File filepath = new File(folder_name +"/"+filename);
        String[] list = folder_name.list();
        for (String file: list) {
            if (filename.equals(file) && filepath.delete()) {
                System.out.println("Your Selected File " + filename + " is deleted from " + folder_name);
                return;
            }
        }
        System.out.println("Deletion Failed. FILE NOT FOUND...");   //IN CASE OF WRONG FILE NAME
    }

//////////////////////////////////FOR ADDING FILES///////////////////////////////////////
	private void retriveFile(String filename) throws IOException {
        File filepath = new File(folder_name +"/"+filename);
        String[] list = folder_name.list();
        for (String file: list) {
            if (filename.equalsIgnoreCase(file)) {
                System.out.println("File " + filename + " already exists at " + folder_name);
                return;
            }
        }
        filepath.createNewFile();
        System.out.println("File "+filename+" added to "+ folder_name+" successfully");
    }

////////////////////////////////////FOR VIEWING FILES/////////////////////////////////////
	private void ViewFiles() {
		if (folder_name.list().length==0)  //EMPTY DIRECTORY
            System.out.println("The folder is empty");
        else {                            // (!EMPTY) DIRECTORY
            String[] list = folder_name.list();
            System.out.println("The files in "+ folder_name +" are :");
            Arrays.sort(list);            //SORTING THE ARRAY AS ASCENDING
            for (String str:list) {
                System.out.println(str);
            }
        }
		
	}


	public static void main(String[] args) {
		System.out.println("\t\t\t\t\t\tAPPLICATION NAME : LockedMe.com");
		System.out.println("\n\t\t\t\t\t\tDEVELOPER : Goutham Gummadipudi");
		LockedMe_Prototype Demo = new LockedMe_Prototype();
        Demo.MainMenu();

	}

}




//files = demo1,demo2