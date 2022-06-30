import java.awt.Desktop;  
import java.io.*; 
/**
 * Write a description of class PlayTodUndLeben here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PlayTodUndLeben{

    public PlayTodUndLeben(){

    }

    public static void main(String args[]){
        GameController.play();
    }

    public static void printDirections(){
        Desktop desktop = Desktop.getDesktop();  
        File file = new File("HELPME.txt"); 
        try{
        desktop.open(file);
    }
        catch(Exception e){
            System.out.println("Help file missing, check the folder that the game is dowloaded to for HELPME");
        }
        
    }


}