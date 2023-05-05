package FullPackage.SongPackage;

import FullPackage.MainMusic.Menu;

import java.sql.*;
import java.util.*;

public class Song_impl
{
    static Connection con;
    static Statement statement;
    static ResultSet resultSet;
    static String criteria;
    public static Connection createConnection()
    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukeboxdb", "root", "admin@123");
        }// port no :- 3306
        catch (SQLException so){
            so.printStackTrace();
        }
        return con;
    }

    public List<Song> createList()
    {
        con = createConnection();
        List<Song> songsList = new ArrayList<>();
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("select * from songs");
            while(resultSet.next())
            {
                Song songObj = new Song(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getTime(6),
                        resultSet.getInt(7),resultSet.getString(8));
                songsList.add(songObj);
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return songsList;
    }
    public void displayFromList(List<Song> songsList) throws Exception {
        Iterator<Song> itr = songsList.iterator();

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("%-10s%-25s%-35s%-35s%-15s%-15s","Song_Id","Song_Name","Song_Artist","Song_Album",
                "Song_Genre","Song_Duration \n");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
        if(itr.hasNext()==false)
        {
            System.out.println("List is not have any value");
            Menu.operationMenu();
        }
        else {
            while (itr.hasNext()) {
                Song song = itr.next();
                System.out.format("%-10s%-25s%-35s%-35s%-15s%-15s", song.getSongId(), song.getSongName(), song.getSongArtist(),
                        song.getSongAlbum() + " (" + song.getSongyearNo() + ")", song.getSongGenre(), song.getSongduration());
                System.out.println();
            }
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void sortedMenu() throws Exception {
        Scanner sc= new Scanner(System.in);
        while (true) {
            System.out.println("--------------------------------");
            System.out.println("         Enter criteria         ");
            System.out.println("--------------------------------");
            System.out.println("Press 1: Song Name" +
                              "\nPress 2: Artist" +
                              "\nPress 3: Album" +
                              "\nPress 4: Genre" +
                              "\nPress 5: Back to the Menu");
            int num = sc.nextInt();
            switch (num) {
                case 1:
                    criteria = "song_Name";
                    sortedMenuByChoise();
                    break;
                case 2:
                    criteria = "Artist";
                    sortedMenuByChoise();
                    break;
                case 3:
                    criteria = "Album";
                    sortedMenuByChoise();
                    break;
                case 4:
                    criteria = "Genre";
                    sortedMenuByChoise();
                    break;
                case 5:
                    Menu.operationMenu();
                    break;
                default:
                    System.out.println("Invalid Input Please Enter Again");
                    sortedMenu();
            }
        }
    }

    public void sortedMenuByChoise() throws Exception {
        List<Song> songsList= new ArrayList<>();
        Scanner sc= new Scanner(System.in);
        System.out.println("Search characters: ");
        String charMatch = sc.next();
        try
        {
            con= createConnection();
            statement = con.createStatement();
            resultSet =statement.executeQuery("select * from songs where "+criteria+" like '%"+charMatch+"%'");

            while (resultSet.next())
            {
                Song songObj = new Song( resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getTime(6),
                        resultSet.getInt(7),resultSet.getString(8));
                songsList.add(songObj);
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }

        Comparator<Song> amountComparator = (a,b) -> a.getSongName().compareTo(b.getSongName());
        // Expression Lambda
        // comparator functional interface (compare method use) // It will take two parameters
        // Anonymous class is created and overrides the method define in functional interface
        songsList.sort(amountComparator);
        displayFromList(songsList);
    }
}
