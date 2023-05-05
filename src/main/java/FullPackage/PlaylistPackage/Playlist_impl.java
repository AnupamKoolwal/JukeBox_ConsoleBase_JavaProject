package FullPackage.PlaylistPackage;

import FullPackage.SongPackage.Song_impl;

import java.sql.*;
import java.util.Scanner;

public class Playlist_impl implements Playlist_implInterface
{
    Scanner sc=new Scanner(System.in);
    Playlist p=new Playlist();
    Connection con= Song_impl.createConnection();

    public Playlist_impl() {
    }
    @Override
    public void createPlaylist()
    {
        try {
            System.out.println("*******************************");
            System.out.println("    Create New PlayList     ");
            System.out.println("******************************");
            System.out.println("Enter Your UserId");
            int id = sc.nextInt();
            p.setUser_id(id);
            System.out.println("Enter PlayListId and Please Remember For Add Songs in this Playlist");
            int id1 = sc.nextInt();
            p.setPlaylist_id(id1);
            System.out.println("Enter PlayList Name");
            String name = sc.next();
            p.setPlaylist_name(name);

            String q = "insert into Playlist values(?,?,?)";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, p.getPlaylist_id());
            ps.setString(2, p.getPlaylist_name());
            ps.setInt(3, p.getUser_id());
            ps.executeUpdate();

            System.out.println("**********************************************************");
            System.out.println("*    Your Playlist Has Been Created Successfully     *");
            System.out.println("**********************************************************");
            System.out.println();
        }catch (SQLException s)
        {
            s.printStackTrace();
        }
    }
}
