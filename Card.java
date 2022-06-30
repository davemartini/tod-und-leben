import java.util.*;
import java.awt.Image;
import javax.swing.*;

/**
 * Card extends CardValues class. Contains methods related to a single card. Including getting the value, suit, and rank of the single card. Card accepts an integer and then retrieves 
 * from CardValues the suit and rank when cardInfo() from CardValues class is called in the constructor of Card.
 * 
 *
 * @author David Martinez
 * @version (12/1/2020)
 */
public class Card extends CardValues implements Comparable<Card>{
    int cardRequest;

    /**
     * Identifies which index in the CardValues array this card object should draw values from with int cardRequest.
     * Calls cardInfo() to populate array with card suits, ranks, and matching values. 
     * @param myCard the integer value of the Card and reference to the CardValue array for corrosponding card face information
     */
    public Card(int myCard){
        cardRequest = myCard;
        cardInfo();
        loadImages();
    }

    /**
     * Checks to make sure the requested card is within legal range of 0 to 51 for 52 individual Cards in a standard
     * deck of playing cards. Since the CardValues array is populated in order of value by rank and suit
     * the requested card by integer will ultimiately be the value of that card.
     * 
     * @return cardRequest the int value of the card requested
     */
    public int getValue(){
        if (cardRequest < 0  || cardRequest > 51){
            System.out.println("Invalid card value, value not found");
            return -1;
        }
        else if(this.getRank() == 'K' ) {
            return 13;
        }
        else if(this.getRank() == 'Q'){
            return 12;
        }
        else if(this.getRank() == 'J'){
            return 11;
        }
        else if(this.getRank() == 'T'){
            return 10;
        }
        else if(this.getRank() == '9'){
            return 9;
        }
        else if(this.getRank() == '8'){
            return 8;
        }
        else if(this.getRank() == '7'){
            return 7;
        }
        else if(this.getRank() == '6'){
            return 6;
        }
        else if(this.getRank() == '5'){
            return 5;
        }
        else if(this.getRank() == '4'){
            return 4;
        }
        else if(this.getRank() == '3'){
            return 3;
        }
        else if(this.getRank() == '2'){
            return 2;
        }
        else if(this.getRank() == 'A'){
            return 1;
        }
        else{
            return 0;
        }

    }

    /**
     * Accesses the deck array index that corrosponds to the value of this card object. Retrieves the second character of the two character sting which is populated with the suits. 
     * 
     * @return cardSuit the suit of the current card
     */
    public char getSuit(){
        char cardSuit = valueOrder[cardRequest].charAt(1);        
        return cardSuit;
    }

    /**
     * Accesses the deck array index that corrosponds to the value of this card object. Retrieves the first character of the two character sting which is populated with the ranks. 
     * 
     * @return cardRank the rank of the current card
     */
    public char getRank(){
        char cardRank = valueOrder[cardRequest].charAt(0);        
        return cardRank;        
    }

    public Image getCardGraphic(){

        return this.images[cardRequest];
    }

    /**
     * Overrides toString and concatenates the rank and suit characters into a string of the current card.
     * 
     * @return cardInfo the string containing the rank and suit of the current card
     */
    @Override 
    public String toString(){
        String cardInfo =""+ getRank()+getSuit();

        return cardInfo;

    }

    /**
     * Retreieves value of current card and checks if it is equal to another card object's value.
     * 
     * @return whether the current card object's value is equal to second card objects value
     */
    public boolean equals(Card secondCard){
        int firstCardValue = this.getValue();
        int secondCardValue = secondCard.getValue();
        if(firstCardValue == secondCardValue){
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * Recieves a card object to compare to current card. Calls getValue() to get integer values then compares them. 
     * Returns a positive value if the current card value is greater than the passed card, a negative value if the second card is greater, and 0 if the values are equal.
     * 
     * Overrides compareTo() method to allow for the comparable<Card> interface 
     * 
     * @param secondCard the card object to be compared to current card
     * @return positve if this card is higher in value than passed card, negative if the second card is higher and 0 if the values are equal. 
     * 
     */
    @Override
    public int compareTo(Card secondCard){
        int cardOneValue = this.getValue();
        int cardTwoValue = secondCard.getValue();
        //Card winnerCard = null;        
        if(this.equals(secondCard)){
            return 0;
        }
        else if(cardOneValue > cardTwoValue){
            return 1;
        }
        else if(cardOneValue < cardTwoValue){
            return -1;
        }        
        return 0;
    }

    /**
     * Compares current Card object to passed Card object and returns the higher value Card
     * 
     * @param secondCard Card to compare values with against current card
     * 
     * @return Card with the higher value between this card and the passed card
     */
    public Card findMyWinner(Card secondCard){
        int cardOneValue = this.getValue();
        int cardTwoValue = secondCard.getValue();
        Card winnerCard = null;        
        if(this.equals(secondCard)){
            winnerCard = this;
        }
        else if(cardOneValue > cardTwoValue){
            winnerCard = this;
        }
        else if(cardOneValue < cardTwoValue){
            winnerCard = secondCard;
        }        
        return winnerCard;
    }

    /**
     * Static method that changes static variable aceHigh in Deck to true to populate deck array with aces in the highest indexes. This causes the value of aces to be higher than any other card. 
     * Acts as a toggle by setting the value to the NOT state of whatever it is currently set to. aceHigh defaults to false, meaning aces are the low cards, calling this method sets them to 
     * high. To switch back to default call setAceHigh again. 
     * 
     */
    public static void setAceHigh(){
        CardValues.aceHigh = !CardValues.aceHigh;
        if (CardValues.aceHigh == false){
            System.out.println("Aces are currently the low cards, run main again to revert back to aces high \n");
        }
        else if(CardValues.aceHigh == true){
            System.out.println("Aces are currently the high cards, run main again to revert to default \n");
        }
    }

    /**
     * Static method that changes the static variable unicodePreference to true in CardValues. This makes the suits of the cards change from letter representation, ie. C for clubs H for hearts to the
     * "Miscellaneous Symbols" unicode block characters for those respective suits. Functionality remains the same, this method simply swaps the picture characters for the letters. 
     */
    public static void setCardsToUnicode(){
        CardValues.unicodePreference = true;
    }
}
