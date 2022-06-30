import java.util.*;
/**
 * Initlizes an ArrayList of Card objects. Constructor methods are overloaded, passing an integer will allow for specific amount of cards to be dealt, however was deprecated with the inclusion of addCard() method,
 * however functionality was left as is. Contains methods to add cards to the ArrayList, compare card values, and retrieve specific information about specifc Card objects or their position in the ArrayList.
 *  
 * <br>
 * Also includes the amount of card objects, the highest value card in the group, if another card can be added to the array, if the array is empty, and getting a string of the array.
 * <br>
 * Also implements the Comparator<Card> interface to allow for more efficient searching
 * 
 * @author David Martinez
 * @version (11/19/2020)
 * 
 */

public class CardGroup implements Comparator<Card>{
    ArrayList<Card> myGroup;    
    private int handSize;
    private int cardCount;
    final int STANDARD_DECK_COUNT;
    int halfDeck = 26;

    public CardGroup(int userHandSize){
        STANDARD_DECK_COUNT = 52;
        handSize = userHandSize;
        cardCount = 0;
        myGroup = new ArrayList<Card>(); 
        //dealGroup();
    }

    public CardGroup(){
        STANDARD_DECK_COUNT = 52;
        cardCount = 0;
        myGroup = new ArrayList<Card>();
    }

    public void dealGroup(){
        for (int i = 0; i < handSize; i++){  
            Random random = new Random();
            int nextHandCardInt = random.nextInt(51);
            Card nextDeltCard = new Card(nextHandCardInt);
            addCard(nextDeltCard);
            //myGroup[i] = nextDeltCard;             
            cardCount++;

        }
    }

    public void stackDeck(){ 
        handSize = STANDARD_DECK_COUNT;
        
        for(int i = 0; i < STANDARD_DECK_COUNT; i++){ // CHECK: possibly need to minus one here not sure
            Card nextDeltCard = new Card(i);            
            addCard(nextDeltCard);
            //cardCount++;                
        }

    }

    public Card getCard(int index){
        if(index <= myGroup.size() && index >= 0){
            Card requestCard = myGroup.get(index);
            return requestCard;
        }
        else{
            return null;
        }        
    }

    public int indexOf(Card card){
        for (int i = 0; i < myGroup.size(); i++){
            Card foundCard = myGroup.get(i);
            if(card.equals(foundCard) == true){
                return i;               
            }                       
        }
        return -1;
    }

    @Override
    public String toString(){
        String[] handArray = new String[myGroup.size()];
        for(int i = 0; i < myGroup.size(); i++){
            handArray[i] = myGroup.get(i).toString();

        }
        String handString = Arrays.toString(handArray);

        return handString;
    }

    public boolean isEmpty(){
        if(this.cardCount == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public int count(){
        return this.cardCount;
    }

    public boolean addCard(Card cardToAdd){
        if(cardCount <= STANDARD_DECK_COUNT){
            myGroup.add(cardToAdd);
            cardCount++;
            return true;
        }
        else{
            return false;
        }

    }

    public Card getHighCard(){
        Card highCard = myGroup.get(0);
        for(int i = 0; i < myGroup.size(); i++){
            highCard = highCard.findMyWinner(myGroup.get(i));

        }

        return highCard;
    }

    public Card[] toArray(){ 
        Card[] cardsInHand = new Card[myGroup.size()];        
        for(int i = 0; i < myGroup.size(); i++){
            Card transferCard = myGroup.get(i);
            transferCard = cardsInHand[i];
        }        
        return cardsInHand;
    }

    @Override 
    public int compare(Card one, Card two){
        return one.compareTo(two);
    }

    public int indexOfCard(Card card){
        CardGroup deckComparator = new CardGroup();
        int foundIndex = Collections.binarySearch(this.myGroup, card, deckComparator);              
        return foundIndex;
    }

    public void shuffle(){
        //System.out.println("Shuffling deck...\n");
        for(int i = 0; i < this.myGroup.size(); i++){
            Random randInt = new Random();
            int swapIndex = randInt.nextInt(this.myGroup.size());
            Collections.swap(this.myGroup, i, swapIndex);
        }
    }

    public Card drawCard(){
        if(cardCount >= 1){
            int cardIndex = cardCount-1;
            Card cardToDeal = myGroup.get(cardIndex);
            myGroup.remove(cardIndex);
            cardCount--;        
            return cardToDeal;
        }
        else{
            return null;
        }
    }

}
