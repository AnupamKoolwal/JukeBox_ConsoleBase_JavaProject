package FullPackage.PlaylistPackage;
public class Playlist
{
    private int user_id;
    private int playlist_id;
    private String playlist_name;

    public Playlist(int user_id, int playlist_id, String playlist_name) {
        this.user_id = user_id;
        this.playlist_id = playlist_id;
        this.playlist_name = playlist_name;
    }
    public Playlist() {}

    public int getUser_id() {
        return user_id;
    }

    public int setUser_id(int user_id){
        return this.user_id = user_id;
    }

    public int getPlaylist_id() {
        return playlist_id;
    }

    public void setPlaylist_id(int playlist_id) {
        this.playlist_id = playlist_id;
    }

    public String getPlaylist_name() {
        return playlist_name;
    }

    public void setPlaylist_name(String playlist_name) {
        this.playlist_name = playlist_name;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "user_id=" + user_id +
                ", playlist_id=" + playlist_id +
                ", playlist_name='" + playlist_name + '\'' +
                '}';
    }
}
