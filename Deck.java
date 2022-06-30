import java.util.*;

public class Deck extends CardGroup{
    int topCard;

    public Deck(){        
        stackDeck();
        topCard = STANDARD_DECK_COUNT-1 ; //set to minus one to avoid out of bounds exception, 52 cards but since indexes start at 0, there are only 51 indexes
    }

    public void shuffle(){
        //System.out.println("Shuffling deck...\n");
        for(int i = 0; i < this.myGroup.size(); i++){
            Random randInt = new Random();
            int swapIndex = randInt.nextInt(STANDARD_DECK_COUNT);
            Collections.swap(this.myGroup, i, swapIndex);
        }
    }

    public void sort(){
        //System.out.println("Sorting Deck...\n");
        Collections.sort(this.myGroup);
    } 

    public CardGroup handSort(CardGroup userHand){        
        Collections.sort(userHand.myGroup);        
        return userHand;
    }

    public Card dealCard(){
        if(topCard >= 0){
            Card cardToDeal = this.myGroup.get(topCard);
            myGroup.remove(topCard);
            topCard--;        
            return cardToDeal;
        }
        else{
            return null;
        }
    }
}
