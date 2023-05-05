package FullPackage.UserPackage;

import FullPackage.SongPackage.Song_impl;
import FullPackage.MainMusic.Main;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User_impl
{
    Connection con = Song_impl.createConnection();
    Scanner sc = new Scanner(System.in);
    User ob = new User();

    public User_impl() {
    }

    public void getUserId()
    {
        try {
            System.out.println("----------------------------------------------");
            System.out.println(" *           Create Your User ID            *");
            System.out.println("-----------------------------------------------");
            System.out.println("Please Enter 4 digit Number as a User Id");
            System.out.println("Please Remember The Your ID");
            int user = sc.nextInt();

            String str = "^[0-9]{4}$";
            Pattern p = Pattern.compile(str);
            Matcher m = p.matcher(String.valueOf(user));
            // A pattern is a compiled representation of a regular expression.
            // Patterns are used by matchers to perform match operations on a character string.
            // A regular expression is a string that is used to match another string, using a specific syntax.

            if (m.matches()) // matches method give the boolean value
            {
                ob.setUser_id(user);
            } // if it works it will run otherwise it will show exception
            else {
                System.out.println("UserId is not in valid Format");
                getUserId();
            }
        }
        catch (InputMismatchException ime)
        {
            System.out.println("Please Enter only 4 Digit Number & Re-enter Your Choose Please");
            Main.forUser();
        }
    }

    public void getMobileNo()
    {
        try {
            System.out.println("------------------------------------------------");
            System.out.println(" *           Enter Your MobileNo            *   ");
            System.out.println("-------------------------------------------------");
            System.out.println("Please Enter 10 digit Number as a Mobile No");
            long mob = sc.nextLong();

            String reg = "^[0-9]{10}$";
            Pattern p1 = Pattern.compile(reg);
            Matcher m1 = p1.matcher(String.valueOf(mob));

            if (m1.matches()) {
                ob.setMobileno(mob);
            }
            else {
                System.out.println("Invalid Mobile Number Format");
                getMobileNo();
            }
        }
        catch (InputMismatchException ime)
        {
            System.out.println("Please Enter 10 digit Number only as a Mobile No & Re-enter Your Choose Please");
            Main.forUser();
        }
    }

    public void getPassword()
    {
        try {
            System.out.println("--------------------------------------------------");
            System.out.println("  *          Enter Your Password                 *  ");
            System.out.println("-------------------------------------------------");
            String pass = sc.next();

            String regex = "^(?=.*[a-z])" + "(?=.*[A-Z])" + "(?=.*\\d)" + "(?=.*[-+_!@#$%^&*., ?]).+$";
            Pattern p2 = Pattern.compile(regex);
            Matcher m2 = p2.matcher(pass);

            if (m2.matches())
            {
                ob.setUser_password(pass);
            }
            else {
                System.out.println("Password must contain one lower letter,one upper case,one number and one symbol");
                getPassword();
            }
        }
        catch (InputMismatchException ime) {
            System.out.println("Password must contain one lower letter,one upper case,one number and one symbol");
            System.out.println("Re-enter Your Choose Please");
            Main.forUser();
        }
    }
    public void create_userid()
    {
        try
        {
            getUserId();
            getMobileNo();
            getPassword();

            System.out.println("-------------------------------------------------");
            System.out.println(" *           Confirm Password                   *");
            System.out.println("--------------------------------------------------");
            String pass1 = sc.next();

            if (ob.getUser_password().equals(pass1))
            {
                String query1 = "insert into Users values(?,?,?)";
                PreparedStatement pus = con.prepareStatement(query1);
                pus.setInt(1, ob.getUser_id());
                pus.setString(2, ob.getUser_password());
                pus.setLong(3, ob.getMobileno());
                pus.executeUpdate();

                System.out.println("***************************************************************");
                System.out.println("            Your Account has been Created successfully          ");
                System.out.println("****************************************************************");
            }
            else {
                System.out.println("Password Not match.Please Re-Enter Your Details");
                create_userid();
            }
        }
        catch (Exception e) {
            System.out.println("Error " + e);
            Main.forUser();
        }
    }
    public void editUserProfile()
    {
        try {
            System.out.println("Enter Your User_Id");
            int id = sc.nextInt();

            System.out.println("Choose Option " +
                    "\nPress 1: Change password " +
                    "\nPress 2: Change MobileNo");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter New Password");
                    ob.setUser_password(sc.next());
                    System.out.println("Confirm Password");
                    String pass = sc.next();
                    String regex = "^(?=.*[a-z])" + "(?=.*[A-Z])" + "(?=.*\\d)" + "(?=.*[-+_!@#$%^&*., ?]).+$";
                    Pattern p2 = Pattern.compile(regex);
                    Matcher m2 = p2.matcher(pass);

                    if (m2.matches()) {
                        if (ob.getUser_password().equals(pass)) {
                            String query = "update Users set User_password=" + "'" + pass + "'" + " where User_id=" + id + ";";
                            Statement st = con.createStatement();
                            st.executeUpdate(query);
                            System.out.println("Edit Successfully");
                            Main.forUser();
                        } else {
                            System.out.println("*    Password Not Match! Please Enter Again       *");
                            editUserProfile();
                        }
                    }
                    else {
                        System.out.println("Password must contain one lower letter,one upper case,one number and one symbol");
                        System.out.println("Re-Edit");
                        editUserProfile();
                    }
                    break;

                case 2:
                    System.out.println("Enter New MobileNo");
                    ob.setMobileno(sc.nextLong());
                    System.out.println("ReEnter MobileNo");
                    long mobile = sc.nextLong();

                    String reg = "^[0-9]{10}$";
                    Pattern p1 = Pattern.compile(reg);
                    Matcher m1 = p1.matcher(String.valueOf(mobile));

                    if (m1.matches()) {
                        if (ob.getMobileno() == mobile) {
                            String q = "Update Users set User_mobileNo=" + mobile + " where User_id=" + id + ";";
                            Statement st = con.createStatement();
                            st.executeUpdate(q);
                            System.out.println("Edit Successfully");
                            Main.forUser();
                        } else {
                            System.out.println("*   Mobile Number Not match. Please Try Again   *");
                            editUserProfile();
                        }
                    }
                    else {
                        System.out.println("Invalid Mobile Number Format");
                        editUserProfile();
                    }
                    break;
                default: {
                    System.out.println("Choose Correct Option");
                    Main.forUser();
                }
            }
        }
        catch (Exception ime) {
            System.out.println("Please Enter Correct Value");
            Main.forUser();
        }
    }

    public boolean loginUser(int userId,String password) throws SQLException, InputMismatchException
    {
        PreparedStatement ps = con.prepareStatement("select * from Users where user_id=? and user_password=? ");
        ps.setInt(1,userId);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();

        if(rs.next())
        {
            rs.close();
            ps.close();
            return true;
        }
        rs.close();
        ps.close();
        return false;
    }
}
