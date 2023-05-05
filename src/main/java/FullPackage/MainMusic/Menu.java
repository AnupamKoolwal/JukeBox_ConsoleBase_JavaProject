package FullPackage.MainMusic;

import FullPackage.SongPackage.Song_impl;
import FullPackage.SongPackage.Song;
import FullPackage.PlaylistPackage.Playlist_impl;
import FullPackage.PlaylistDetailsPackage.PlaylistDetails_impl;
import FullPackage.UserPackage.User_impl;

import java.util.List;
import java.util.Scanner;

public class Menu
{
    static Scanner sc = new Scanner(System.in);
    static User_impl userImpl = new User_impl();
    static Song_impl songimpl = new Song_impl();
    static Playlist_impl playlist_impl = new Playlist_impl();
    static PlaylistDetails_impl playlistDetails_impl = new PlaylistDetails_impl();
    static List<Song> mainList = songimpl.createList();

    public static void operation() throws Exception
    {
        System.out.println("Enter Your UserId");
        int idNum = sc.nextInt();
        System.out.println("Enter password");
        String pass = sc.next();

        if (userImpl.loginUser(idNum, pass) == true) {
            System.out.println("************************");
            System.out.println("   login successfully   ");
            System.out.println("************************");
            operationMenu();
        } else {
            System.out.println("User Id and Password Not match. Please Login Again");
            Main.forUser();
        }
    }
    public static void operationMenu() throws Exception
    {
        while (true) {
            System.out.println("***************************************************************" +
                    "\nPress 1: For Show All Songs List" +
                    "\nPress 2: Specified Category for Search " +
                    "\nPress 3: Create PlayList " +
                    "\nPress 4: Add Song in Your PlayList " +
                    "\nPress 5: Delete Songs from PlayList " +
                    "\nPress 6: Delete Your PlayList " +
                    "\nPress 7: Display All Song From Your Playlist And Play the song" +
                    "\nPress 8: Exit " +
                    "\n***************************************************************");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    songimpl.displayFromList(mainList);//all songs print
                    break;
                case 2:
                    songimpl.sortedMenu(); // sorting print
                    break;
                case 3:
                    playlist_impl.createPlaylist();
                    break;
                case 4:
                    playlistDetails_impl.insertNewSong();
                    break;
                case 5:
                    playlistDetails_impl.deleteSongsFromPlaylist();
                    break;
                case 6:
                    playlistDetails_impl.deletePlaylist();
                    break;
                case 7:
                    List<Song> play = playlistDetails_impl.displaySongsFromPlaylist(mainList);
                    songimpl.displayFromList(play);

                    music(play);
                    break;
                case 8:
                    System.exit(0);
                    break;
                default: {
                    System.out.println("Choose correct Option");
                    operationMenu();
                }
            }
        }
    }
    public static void music(List<Song> playList)
    {
        AudioPlay.mylist = playList;
        System.out.println("Enter Song-Id to Play the \"Song\" ");
        int sId = sc.nextInt();
        AudioPlay.songId = sId;
        String filePath = "";
        boolean songFound = false;
        for (int i = 0; i < playList.size(); i++) {
            if (sId == playList.get(i).getSongId()) {
                filePath = playList.get(i).getSongPath();
                songFound = true;
                break;
            }
        }
        try {
            if (!songFound) {
                throw new SongNotFoundException("Song with id " + sId + " not found in play list");
            }
        }
        catch (SongNotFoundException s)
        {
            System.out.println(s);
        }
        System.out.println(filePath);
        try {
            AudioPlay.filePath = filePath;
            AudioPlay audioPlayer = new AudioPlay();
            audioPlayer.play();

            while (true) {
                System.out.println("Press 1: Pause");
                System.out.println("Press 2: Resume");
                System.out.println("Press 3: Restart");
                System.out.println("Press 4: Forward");
                System.out.println("Press 5: Backward");
                System.out.println("Press 6: Get Remaining Time");
                System.out.println("Press 7: For Shuffle");
                System.out.println("Press 8: For Next");
                System.out.println("Press 9: Stop");
                System.out.println("Press 10: Back to the Menu");
                int c = sc.nextInt();
                audioPlayer.gotoChoice(c);
            }
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            music(playList);
        }
    }
}
