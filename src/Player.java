import javax.sound.sampled.*;
import java.io.*;
import java.io.IOException;
import java.util.*;

public class Player{
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException{
        int i = 0;
        Scanner scanner = new Scanner(System.in);
        File folder = new File("/home/yianni/Music/");
        File[] listOfFiles = folder.listFiles();
        File audio = new File(listOfFiles[i].getPath());

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audio);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        String controls = "";
        String state = "";
        clip.start();
        
        boolean isPaused = false;
        while(!controls.equals("q")){
            String audioName = audio.toString();
            System.out.println(audioName.substring(audioName.lastIndexOf("/") + 1, audioName.lastIndexOf("."))+state);
            controls = scanner.next();
             
            switch(controls){
                case "e":clip.stop();
                i++;
                clip.close();
                audio = new File(listOfFiles[i].getPath());
                audioStream = AudioSystem.getAudioInputStream(audio);
                clip.open(audioStream);
                clip.start();
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
                case "r":clip.stop();
                clip.setFramePosition(0);
                clip.start();
                break;
                case "q":clip.close();
                System.out.println("Closed");
                break;
                default:
                System.out.println("Invalid Input");
            }
        }
    }
}