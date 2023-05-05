package FullPackage.MainMusic;

import FullPackage.UserPackage.User_impl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        forUser();
    }

    public static void forUser()
    {
        try {
            Menu menu = new Menu();
            Scanner sc = new Scanner(System.in);
            User_impl userImpl = new User_impl();

            System.out.println("----------------------------  Welcome To JukeBox  ------------------------------");
            System.out.println("Press 1: Are you existing User " +
                    "\nPress 2: Create New Account " +
                    "\nPress 3: Edit Profile and change Password or mobile Number" +
                    "\nPress 4: For Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    try {
                        System.out.println("*****************************************");
                        System.out.println("            Please Login                 ");
                        System.out.println("*****************************************");
                        Menu.operation(); // for access the menu operation
                    }
                    catch (Exception ime) {
                        System.out.println("Invalid Input!, Please Re-login");
                    }
                    break;

                case 2:
                    try {
                        userImpl.create_userid();
                        System.out.println("*****************************************");
                        System.out.println("            Please Login                 ");
                        System.out.println("*****************************************");
                        Menu.operation();
                    }catch (Exception ime) {
                        System.out.println("Invalid Input!, Please Re-login");
                        forUser();
                    }
                    break;

                case 3:
                    try {
                        System.out.println("*****************************************");
                        System.out.println("    Change Password or Mobile Number     ");
                        System.out.println("*****************************************");
                        userImpl.editUserProfile();
                    }catch (Exception ime) {
                        System.out.println("Invalid Input!, Please Re-login");
                        forUser();
                    }
                    break;

                case 4:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Choose Correct Choise");
                    forUser();
            }
        }catch (InputMismatchException ime)
        {
            System.out.println("Enter Correct Option");
            forUser();
        }
    }
}

