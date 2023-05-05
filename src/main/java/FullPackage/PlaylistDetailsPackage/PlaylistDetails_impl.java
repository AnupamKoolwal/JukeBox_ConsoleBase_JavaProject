package FullPackage.PlaylistDetailsPackage;

import FullPackage.SongPackage.Song_impl;
import FullPackage.SongPackage.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlaylistDetails_impl
{
    Connection con = Song_impl.createConnection();
    Scanner sc = new Scanner(System.in);
    PlaylistDetails playlistDetails= new PlaylistDetails();

    public void insertNewSong() throws SQLException
    {
        System.out.println("To Add Songs in the Playlist you should Remember 3 things SongId, UserId and PlayListId");
        System.out.println("Enter PlayListId");
        playlistDetails.setPlaylist_Id(sc.nextInt());
        System.out.println("Enter Song Id");
        playlistDetails.setSong_Id(sc.nextInt());
        System.out.println("Enter Your User Id");
        playlistDetails.setUser_Id(sc.nextInt());

        String query = "insert into PlaylistDetails values(?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, playlistDetails.getUser_Id());
        ps.setInt(2, playlistDetails.getPlaylist_Id());
        ps.setInt(3, playlistDetails.getSong_Id());
        ps.executeUpdate();

        System.out.println("---------------------------------");
        System.out.println("     Song Added Successfully     ");
        System.out.println("---------------------------------");
    }

    public void deleteSongsFromPlaylist() throws SQLException {

        System.out.println("Enter Your PlaylistId");
        playlistDetails.setPlaylist_Id(sc.nextInt());
        System.out.println("Enter Your SongId which You want to delete");
        playlistDetails.setSong_Id(sc.nextInt());

        String del = "delete from PlaylistDetails where song_Id="+playlistDetails.getSong_Id()
                +" and Playlist_id="+playlistDetails.getPlaylist_Id()+";";
        Statement st= con.createStatement();
        st.executeUpdate(del);
        System.out.println("* Your Song Has Been Deleted Successfully *");
    }
    public void deletePlaylist() throws SQLException
    {
        System.out.println("Enter Your PlaylistId");
        int id = sc.nextInt();

        String del = "delete from PlaylistDetails where Playlist_id= "+id+";";
        String del2 = "delete from Playlist where Playlist_id= "+id+";";
        Statement st= con.createStatement();
        st.executeUpdate(del);
        st.executeUpdate(del2);
        System.out.println("* Your PlayList Has Been Deleted Successfully *");
    }

    public List<Song> displaySongsFromPlaylist(List<Song> songsList) throws SQLException
    {
        System.out.println("Enter Your PlaylistId");
        playlistDetails.setPlaylist_Id(sc.nextInt());
        System.out.println("Enter Your User Id");
        playlistDetails.setUser_Id(sc.nextInt());

        String abc= "select song_Id from PlaylistDetails where Playlist_Id="+playlistDetails.getPlaylist_Id()+
                " and User_Id="+playlistDetails.getUser_Id()+";";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(abc);
        List<Integer> listInt = new ArrayList<>();

        while(rs.next())
        {
            listInt.add(rs.getInt(1)); //only one column here
        }

        List<Song> playlist = new ArrayList<>();

        for(int i=0; i<songsList.size(); i++)
        {
            for (int j = 0; j < listInt.size(); j++)
            {
                if (listInt.get(j) == songsList.get(i).getSongId())
                {
                    playlist.add(songsList.get(i));
                }
            }
        }
        // This loop iterates over the songsList and listInt lists and compares the song_Id of each Song object in
        // songsList with each integer in listInt.
        // If a match is found, the Song object is added to the playlist list
        return playlist;
    }
}
