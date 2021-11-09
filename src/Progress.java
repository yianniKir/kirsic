import javazoom.jl.player.*;
import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.net.*;
import java.security.Provider.Service;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Progress
{
    public static void main(String[] args) throws Exception 
    {   
       Thread thread1 = new Thread(){
           public void run(){
            try{
                FileInputStream fis = new FileInputStream("/home/yianni/Music/dream_on.mp3");
                Player playMP3 = new Player(fis);
            
                playMP3.play();
            
                }catch(Exception e){System.out.println(e);}
           }
       };
        thread1.start();
        Thread.sleep(500);
        startTimer(4,28,"Dream On","Aerosmith");
        thread1.join();
    }

    public static void startTimer(int len_m, int len_s, String songName, String artist) throws Exception{
        //startTimer(0,15,"Dream On","Aerosmith");
        int charsWritten = 0;
        long start = System.currentTimeMillis();
        while (1 > 0) {
            Thread.sleep(1000);
            long elapsedTime = System.currentTimeMillis() - start;
            elapsedTime = elapsedTime / 1000;

            if(elapsedTime < (len_m*60+len_s+1)){
                String seconds = Integer.toString((int)(elapsedTime % 60));
                String minutes = Integer.toString((int)((elapsedTime % 3600) / 60));
    
                if (seconds.length() < 2) {
                    seconds = "0" + seconds;
                }
    
                if (minutes.length() < 2) {
                    minutes = "0" + minutes;
                }
    
                String writeThis = "\r" + songName + " - " + artist + ": " + minutes + ":" + seconds + "/" + len_m + ":" + len_s;
    
                for (int i = 0; i < charsWritten; i++) {
                    System.out.print("\b");
                }
                System.out.print(writeThis);
                charsWritten = writeThis.length();
            }
            else{
                System.out.println();
                break;
            }
        }
        System.out.flush();
    }
}
