package FullPackage.MainMusic;

import FullPackage.SongPackage.Song;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AudioPlayTest
{
    AudioPlay audioPlay;
    List<Song> list = Menu.mainList;
    int sId = 10;

    public AudioPlayTest()
    {
        AudioPlay.mylist = list;
        AudioPlay.songId = sId;
        for (int i = 0; i < list.size(); i++) {
            if (sId == list.get(i).getSongId()) {
                AudioPlay.filePath = list.get(i).getSongPath();
                break;
            }
        }
    }
    @BeforeEach
    void setUp() throws UnsupportedAudioFileException, LineUnavailableException, IOException
    {
        audioPlay = new AudioPlay();
    }

    @AfterEach
    void tearDown() {
        audioPlay = null;
    }

    @Test
    public void testResumeAudio() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException, NullPointerException
    {
        audioPlay.status = "play";
        // Pause the audio file
        audioPlay.pause();

        // Verify that the audio file is paused
        assertEquals("paused", audioPlay.status);

        // Resume playing the audio file
        audioPlay.resumeAudio();

        // Verify that the audio file is being played
        assertEquals("play", audioPlay.status);
    }

    @Test
    public void testPlay() {

        audioPlay.status = "paused";
        // Call the play() method
        audioPlay.play();

        // Check that the status is "play"
        assertEquals("play", audioPlay.status);

    }
}

