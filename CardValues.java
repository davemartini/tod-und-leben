import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
//import java.awt.Image;
//import javax.swing.ImageIcon;
//import javax.swing.JFrame;

/**
 * Creates a string array representing all possible card face properties in a standard 52 card deck. Possible card ranks and suits are populated in the array
 * sequentially by their relative value. Each index corrosponds to the value of the card face in relation
 * to the other cards in the deck. ie. lowest card value is 0 and is found at index 0, highest card value is 51 and found at index 51. When Card is passed an integer, it acts as the value and the reference
 * for the corrosponding card face properties. To change the value behavior of the card, the ordering of the strings must be changed here. 
 * <br>
 * All Card objects inherit the same string thus preserving the value and face properties between Cards.
 * 
 * 
 * @author David Martinez
 * @version (11/12/2020)
 * 
 */
public class CardValues{
    static boolean aceHigh = false;
    static boolean unicodePreference = false;
    private final int CARD_COUNT;
    String[] valueOrder;
    Image[] images;
    private String ranks;
    private String suits;

    /**
     * Constructor sets the order of precendence for the cards to go in. Rank and Suit are populated in the orders
     * of relative value. Lower values at the beginning (2s of all suit,3s of all suits then 4s etc.) ending with the highest values.
     * 
     * 
     * 
     */
    public CardValues(){
        CARD_COUNT = 52;        
        ranks = setOrder();
        suits = setSuitPreference();        
        valueOrder = new String[CARD_COUNT];
        

    }

    public void loadImages() {
        images = new Image[CARD_COUNT];

        for (int i = 0; i < CARD_COUNT; i++) {
            String cardID = String.format("Carding/"+i+".gif");
            images[i] = new ImageIcon(cardID).getImage();
                        
        }

    }

    /**
     * Populates an array with strings representing each card's rank and suit properties in a standard deck. Filled 
     * first by rank, then by suit. Loops through so that each sequential card increases in relative value by 1, in-line with their index in the array,
     * by precendent set in the constructor. Since the value is relative to the location in the array, the value can be changed by modifying the order of the suits or ranks string characters.
     * 
     */
    public void cardInfo(){
        //valueOrder[0] = "0";
        int cardIndex = 0;
        for(int y = 0; y < ranks.length(); y++){
            char currentRank = ranks.charAt(y);

            for(int z = 0; z < suits.length(); z++){
                char currentSuit = suits.charAt(z);
                valueOrder[cardIndex] = ""+currentRank+currentSuit;
                cardIndex++;                
            }
        }        
    }

    /**
     * Sets the order of the ranks in the deck array. Checks if class variable aceHigh is set to true. If true, the order of the ranks is changed so the aces are the highest value cards.
     * If false the order has the Ace cards as the lowest value card.
     */
    private String setOrder(){
        if(aceHigh == true){

            return "23456789TJQKA";
        }                
        else{

            return "A23456789TJQK";
        }
    }

    /**
     * Sets which characters should be used in instances of Card objects. By default the letter representations are used for the suits, C for clubs H for hearts etc. however, if variable unicodePreference
     * is set to true, this method will retun the unicode character representations of the suits instead of the letters. Functionality of the program is preserved with either. 
     */
    private String setSuitPreference(){
        if(unicodePreference == true){
            return "\u2663\u2666\u2665\u2660";
        }
        else{
            return "CDHS";
        }
    }

}
