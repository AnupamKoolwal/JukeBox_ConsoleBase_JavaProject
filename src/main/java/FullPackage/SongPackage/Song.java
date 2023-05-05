package FullPackage.SongPackage;
import java.sql.Time;

public class Song {

    private int songId;
    private String songName;
    private String songArtist;
    private String songAlbum;
    private String songGenre;
    private Time songduration;
    private int songyearNo;
    private String songPath;

    public Song(){}
    public Song(int songId, String songName, String songArtist, String songAlbum, String songGenre, Time songduration, int songyearNo, String songPath) {
        this.songId = songId;
        this.songName = songName;
        this.songArtist = songArtist;
        this.songAlbum = songAlbum;
        this.songGenre = songGenre;
        this.songduration = songduration;
        this.songyearNo = songyearNo;
        this.songPath = songPath;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSongAlbum() {
        return songAlbum;
    }

    public void setSongAlbum(String songAlbum) {
        this.songAlbum = songAlbum;
    }

    public String getSongGenre() {
        return songGenre;
    }

    public void setSongGenre(String songGenre) {
        this.songGenre = songGenre;
    }

    public Time getSongduration() {
        return songduration;
    }

    public void setSongduration(Time songduration) {
        this.songduration = songduration;
    }

    public int getSongyearNo() {
        return songyearNo;
    }

    public void setSongyearNo(int songyearNo) {
        this.songyearNo = songyearNo;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", songName='" + songName + '\'' +
                ", songArtist='" + songArtist + '\'' +
                ", songAlbum='" + songAlbum + '\'' +
                ", songGenre='" + songGenre + '\'' +
                ", songduration=" + songduration +
                ", songyearNo=" + songyearNo +
                ", songPath='" + songPath + '\'' +
                '}';
    }

}
