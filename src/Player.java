package src;

import javax.sound.sampled.*;
import java.io.*;
import java.io.IOException;
import java.util.*;

public class Player{
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException{
        int currentAudio = 0;
        Scanner scanner = new Scanner(System.in);
        File folder = new File("/home/yianni/Music/");
        File[] listOfFiles = folder.listFiles();
        int numOfFiles = listOfFiles.length;
        File audio = new File(listOfFiles[currentAudio].getPath());

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audio);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        
        String state = "";
        clip.start();
        boolean isPaused = false;
        String controls = "";
        do{
            System.out.println(clip.getMicrosecondLength());
            String audioName = audio.toString();
            String writeThis =  "" + clip.getMicrosecondLength(); 
            System.out.println(audioName.substring(audioName.lastIndexOf("/") + 1, audioName.lastIndexOf("."))+state+" "+writeThis);
            controls = scanner.next();
        
            switch(controls){
                case "e":
                clip.stop();
                currentAudio++;
                clip.close();
                if(currentAudio <= numOfFiles){
                    audio = new File(listOfFiles[currentAudio].getPath());
                    audioStream = AudioSystem.getAudioInputStream(audio);
                    clip.open(audioStream);
                    clip.start();
                }
                break;
                case "p":
                if(isPaused){
                    clip.start();
                    state = "";
                    isPaused = false;
                }else{
                    clip.stop();
                    state = "(Paused)";
                    isPaused = true;
                }
                break;
                case "r":
                clip.stop();
                clip.setFramePosition(0);   
                clip.start();
                break;
                case "q":
                clip.close();
                System.out.println("Closed");
                break;
                default:
                System.out.println("Invalid Input");
            }
        }while(!controls.equals("q"));
    }
}