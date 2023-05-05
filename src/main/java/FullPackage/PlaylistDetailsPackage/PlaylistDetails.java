package FullPackage.PlaylistDetailsPackage;

public class PlaylistDetails
{
    private int playlist_Id;
    private int user_Id;
    private int song_Id;

    public PlaylistDetails(){}
    public PlaylistDetails(int playlist_Id, int user_Id, int song_Id) {
        this.playlist_Id = playlist_Id;
        this.user_Id = user_Id;
        this.song_Id = song_Id;
    }

    public int getPlaylist_Id() {
        return playlist_Id;
    }

    public void setPlaylist_Id(int playlist_Id) {
        this.playlist_Id = playlist_Id;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    public int getSong_Id() {
        return song_Id;
    }

    public void setSong_Id(int song_Id) {
        this.song_Id = song_Id;
    }

    @Override
    public String toString() {
        return "PlaylistDetails{" +
                "playlist_Id=" + playlist_Id +
                ", user_Id=" + user_Id +
                ", song_Id=" + song_Id +
                '}';
    }
}
