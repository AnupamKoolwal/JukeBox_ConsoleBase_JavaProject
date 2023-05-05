package FullPackage.MainMusic;// Java program to play an Audio file using Clip Object
import FullPackage.SongPackage.Song;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlay
{
    // to store current position
    Long currentFrame;
    Clip clip; // clip is a interface

    // current status of clip
    String status;

    AudioInputStream audioInputStream;
    static String filePath;
    static int songId;
    static List<Song> mylist;

    // constructor to initialize streams and clip
    public AudioPlay() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        // create AudioInputStream object // AudioInputStream converts an audio file into stream.
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        // This line creates an AudioInputStream object by calling the getAudioInputStream method of the
        // AudioSystem class and passing it the absolute file path of a file represented by a File object.

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY); //tracks keep on repeating
    }

    // Method to play the audio
    public void play()
    {
        //start the clip
        clip.start();
        status = "play";
    }

    // Work as the user enters his choice
    public void gotoChoice(int c) throws Exception
    {
        switch (c)
        {
            case 1:
                pause();
                break;
            case 2:
                resumeAudio();
                break;
            case 3:
                restart();
                break;
            case 4:
                forward();
                break;
            case 5:
                backward();
                break;
            case 6:
                getRemainingTime();
                break;
            case 7:
                stop();
                forShuffle();
                restart();
                break;
            case 8:
                stop();
                forNext();
                restart();
                break;
            case 9:
                stop();
                break;
            case 10:
                stop();
                Menu.operationMenu();
                break;
            default:
                System.out.println("Enter valid Input");
                break;
        }
    }

    // Method to pause the audio
    public void pause()
    {
        if (status.equals("paused"))
        {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    // Method to resume the audio
    public void resumeAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        if (status.equals("play"))
        {
            System.out.println("Audio is already being played");
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    // Method to restart the audio
    public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException
    {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    // Method to stop the audio
    public void stop() {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    public void forward()
    {
        if (clip.getMicrosecondPosition()<clip.getMicrosecondLength()-10000000)
            clip.setMicrosecondPosition(clip.getMicrosecondPosition()+10000000);
        else
            clip.setMicrosecondPosition(clip.getMicrosecondLength());
    }

    public void backward()
    {
        if (clip.getMicrosecondPosition()>10000000)
            clip.setMicrosecondPosition(clip.getMicrosecondPosition()-10000000);
        else
            clip.setMicrosecondPosition(0);
    }

    public void getRemainingTime()
    {   // 1 second = 10,00,000 micro second
        long timeRemainingInSeconds =
                ( clip.getMicrosecondLength() -(clip.getMicrosecondPosition()) % clip.getMicrosecondLength() ) /1000000;
        long minutes = (timeRemainingInSeconds)/60;
        long seconds = (timeRemainingInSeconds)%60;
        System.out.println(minutes+":"+seconds);
    }
    public void forShuffle()
    {
        Collections.shuffle(mylist, new Random());
        this.filePath = mylist.get(0).getSongPath();
        System.out.println(mylist.get(0).getSongName());
    }

    public void forNext() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        // Find the current song in the list using an iterator
        Iterator<Song> iterator = mylist.iterator();
        Song currentSong = null;
        while (iterator.hasNext())
        {
            Song song = iterator.next();
            if (song.getSongPath().equals(filePath)) {
                currentSong = song;
                break;
            }
        }

        // Get the next song in the list using the iterator next() method
        Song nextSong = iterator.hasNext() ? iterator.next() : mylist.get(0); // Ternary Operator
        System.out.println(nextSong.getSongName());

        // Update the filePath and reset the audio stream
        filePath = nextSong.getSongPath();
        resetAudioStream();
    }

    // Method to reset audio stream
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
