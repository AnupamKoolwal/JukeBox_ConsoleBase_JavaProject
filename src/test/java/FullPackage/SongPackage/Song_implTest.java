package FullPackage.SongPackage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Song_implTest {
    Song_impl songImpl;
    static List<Song> songs;
    @BeforeEach
    void setUp() {
        songImpl = new Song_impl();
    }

    @AfterEach
    void tearDown() {
        songImpl=null;
    }

    @Test
    public void testCreateList() {
        songs = songImpl.createList();
        assertFalse(songs.isEmpty());
    }

}