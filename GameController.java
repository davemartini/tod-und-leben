import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
 * Manages the GameField view of the current Tod Und Leben game as well as the deck and player decks of card objects. Keeps track of score, card and deck state, as well as overall round scores. Maintains the various visibility variables found in game view and allows them to be visible when 
 * neccesary and hiding others when they are not.
 *
 * @author David Martinez
 * @version 1.0
 */
public class GameController{

    //final int GAME_HAND_SIZE;
    //final int DECK_SIZE;
    static int playerDeckChoice;

    Card showCardPlayer;
    Card showCardComp;

    CardGroup playerDeck;
    CardGroup computerDeck;
    int playerScore;
    int computerScore;
    int roundCount;
    int handPoints;
    int warCardPlace;
    String endGameMessage;
    String endGameStringLeben;
    String winnerLoserString;

    int roundsWonPlayer;
    int roundsWonComp;

    //String endGameMessageComputer;
    public GameController(){
        //GAME_HAND_SIZE = 26;
        //DECK_SIZE = 51;
        playerScore = 0;
        computerScore = 0;
        handPoints = 2;
        playerDeckChoice = 0;
        warCardPlace = 1; //TODO : change this back to 0
        endGameMessage = "";
        endGameStringLeben ="";
        winnerLoserString = "";

        roundsWonPlayer = 0;
        roundsWonComp = 0;

    }

    public static void play(){
        GameController currentGame = new GameController();
        GameField currentTable = new GameField(currentGame);

    }

    public void gameSetup(GameField currentTable){ 
        Deck playingDeck = new Deck();
        playingDeck.sort(); 
        CardGroup blackDeck = new CardGroup();
        CardGroup redDeck = new CardGroup();

        for(int i = 0; i < 13; i++){
            Card Card1 = playingDeck.dealCard();
            blackDeck.addCard(Card1);

            Card Card2 = playingDeck.dealCard();            
            redDeck.addCard(Card2);
            Card Card3 = playingDeck.dealCard();            
            redDeck.addCard(Card3);

            Card Card4 = playingDeck.dealCard();
            blackDeck.addCard(Card4);
        }
        deckAssign(blackDeck,redDeck);
        blackDeck.shuffle();
        redDeck.shuffle();
        blackDeck.shuffle();
        redDeck.shuffle();

        //oneHand(currentTable);

    }

    public void oneHand(GameField currentTable){        
        getPlayersCard(currentTable);
        currentTable.showWinnerLoser = false;
        //show winner loser to false so it wont paint the winner loser

    }

    public void calculateHand(GameField currentTable){

        currentTable.showWinnerLoser = true;
        handWinningCard(currentTable);

        //show winner loser to true in table so it shows the winner of the most recent thing 
        //can put the same thing in todundleben win condtions

        //System.out.println(playerScore+"Your score");
        //System.out.println(computerScore+"Computer score");

    }

    public void startTodUndLeben(GameField currentTable){
        currentTable.dealCard.setVisible(false);
        currentTable.showCard.setVisible(false);
        currentTable.todUndLeben.setVisible(true);
        currentTable.showWinnerLoser = false;
        //todUndLeben(currentTable);

    }

    public void getPlayersCard(GameField currentTable){
        if(computerDeck.count() == 0 || playerDeck.count() == 0){ //TODO: finish win conditon with tied value
            endGame(currentTable);
        }
        else{
            showCardPlayer = playerDeck.drawCard();
            showCardComp = computerDeck.drawCard();

            Image showCardPlayerFace = showCardPlayer.getCardGraphic();
            Image showCardCompFace = showCardComp.getCardGraphic();

            currentTable.playerCardUpdate = showCardPlayerFace;
            currentTable.compCardUpdate = showCardCompFace;        
        }
    }

    public void handWinningCard(GameField currentTable){
        int playerValue;
        int computerValue;
        playerValue = showCardPlayer.getValue();
        computerValue = showCardComp.getValue();
        //handPoints = 2;
        Card winningCard = null;

        if(playerValue > computerValue){
            winningCard = showCardPlayer;
            playerScore = playerScore+handPoints;
            winnerLoserString = "You won";

        }
        else if(computerValue > playerValue){
            winningCard = showCardComp;
            computerScore = computerScore+handPoints;
            winnerLoserString = "You lost";
        }
        else if(computerValue == playerValue){

            startTodUndLeben(currentTable);


        }
        

    }

    public void todUndLeben(GameField currentTable){
        Card playerWarCard = null;
        Card computerWarCard = null;
        Card winningWarCard = null;
        handPoints = handPoints+2;


        if(computerDeck.count() == 0 || playerDeck.count() == 0){ //TODO: finish win conditon with tied value
            endGameLeben(currentTable);
            return;
        }
        else{

            playerWarCard = playerDeck.drawCard();
            Image playerWarCardFace = playerWarCard.getCardGraphic(); 

            computerWarCard = computerDeck.drawCard();
            Image computerWarCardFace = computerWarCard.getCardGraphic();

            currentTable.recieveWarCard(playerWarCardFace, computerWarCardFace);
            warCardPlace++;
        }

        int playerValue;
        int computerValue;
        playerValue = playerWarCard.getValue();
        computerValue = computerWarCard.getValue();

        if(playerValue > computerValue){
            winningWarCard = playerWarCard;
            playerScore = playerScore+handPoints;
            currentTable.todUndLeben.setVisible(false);
            currentTable.endUndLeben.setVisible(true);
            currentTable.showWinnerLoser = true;
            winnerLoserString = "You won";

        }
        else if(computerValue > playerValue){
            winningWarCard = computerWarCard;
            computerScore = computerScore+handPoints;
            currentTable.todUndLeben.setVisible(false);
            currentTable.endUndLeben.setVisible(true);
            currentTable.showWinnerLoser = true;
            winnerLoserString = "You lost";

        }
        else if(computerValue == playerValue){

            startTodUndLeben(currentTable);

        }

        

    }

    public void deckAssign(CardGroup blackDeck, CardGroup redDeck){
        if(playerDeckChoice == 1){
            playerDeck = redDeck; 
            computerDeck = blackDeck;
        }
        if(playerDeckChoice == 2){
            playerDeck = blackDeck;
            computerDeck = redDeck;
        }

    }

    public int getRoundScoreComputer(){
        return computerScore;
    }

    public int getRoundScorePlayer(){
        return playerScore;
    }

    public int getWarCardPlace(){
        return warCardPlace;
    }

    public void lebenHandEnd(GameField currentTable){
        warCardPlace = 1;
        handPoints = 2;
        currentTable.warCard1 = null;
        currentTable.warCard2 = null;
        currentTable.warCard3 = null;
        currentTable.warCard4 = null;
        currentTable.warCard5 = null;
        currentTable.warCard6 = null;
        currentTable.compCard = currentTable.spaceHolder;
        currentTable.playerCard = currentTable.spaceHolder;
        currentTable.showWinnerLoser = false;

        currentTable.dealCard.setVisible(true);
        currentTable.endUndLeben.setVisible(false);
        currentTable.repaint();

    }

    public void endGame(GameField currentTable){
        currentTable.showCard.setVisible(false);
        currentTable.dealCard.setVisible(false);


        currentTable.nextRound.setVisible(true);

        currentTable.tableRunning = false;

        if(playerScore > computerScore){
            endGameMessage = "You won this round! With a round score of "+playerScore+" against the computer's measely "+computerScore;
            endGameStringLeben = "";
            roundsWonPlayer++;
        }
        else if(computerScore > playerScore){
            endGameMessage = "Sorry, but you did not win this round."; 
            endGameStringLeben = "Your round score was "+playerScore+" and the computer's score was "+computerScore;
            roundsWonComp++;
        }
        else if(computerScore == playerScore){
            endGameMessage = "You tied! No round points awarded. Maybe next round.";
            endGameStringLeben = "Your round score was "+playerScore+" and the computer's score was "+computerScore;
        }
        currentTable.repaint();

    }

    public void endGameLeben(GameField currentTable){
        currentTable.showCard.setVisible(false);
        currentTable.dealCard.setVisible(false);


        currentTable.nextRound.setVisible(true);

        currentTable.tableRunning = false;        
        currentTable.todUndLeben.setVisible(false);

        playerScore = playerScore+1;        
        computerScore = computerScore+1;

        if(playerScore > computerScore){
            endGameMessage = "You ran out of cards! Ending a round in a Tod Und Leben means your current card is added to your score.";
            endGameStringLeben = "Congratulations! You won with a score of "+playerScore+" against the computer's measely "+computerScore;
            roundsWonPlayer++;
        }
        else if(computerScore > playerScore){
            endGameMessage = "You ran out of cards! Ending a round in a Tod Und Leben means your current card is added to your score.";
            endGameStringLeben = "Sorry, but you did not win. Your final score was "+playerScore+" and the computer's score was "+computerScore;
            roundsWonComp++;
        }
        else if(computerScore == playerScore){
            endGameMessage = "You ran out of cards! Ending a round in a Tod Und Leben means your current card is added to your score.";
            endGameStringLeben = "You tied! No round points awarded. Maybe next round. Your final score was "+playerScore+" and the computer's score was "+computerScore;
        }

        currentTable.repaint();
    }

    public String getEndGameString(){
        return endGameMessage;
    }

    public String getEndGameStringLeben(){
        return endGameStringLeben;
    }

    public int getCompDeckCount(){
        return computerDeck.count();
    }

    public int getPlayerDeckCount(){
        return playerDeck.count();
    }

    public String getWinnerLoser(){
        return winnerLoserString;
    }

    public void roundReset(GameField currentTable){
        if(roundsWonPlayer == 3 || roundsWonComp == 3){
            currentTable.buttonPanel.setVisible(false);
            currentTable.deckCheck.setVisible(false);
            totalWarWinner(currentTable);
        }
        else{

            currentTable.compCard = currentTable.spaceHolder;
            currentTable.playerCard = currentTable.spaceHolder;
            currentTable.cardCounterOn = false;
            playerScore = 0;
            computerScore = 0;
        }

    }

    public void totalWarWinner(GameField currentTable){

        if(roundsWonPlayer == 3){
            endGameMessage = "Game over! Congratulations, you won the last round and beat the computer!";
            endGameStringLeben = "You were the first to 3 round wins, I hope you enjoyed playing Tod Und Leben!";

        }
        else if(roundsWonComp == 3){
            endGameMessage = "Game over! Sorry, the computer won the last round and that was its third win.";
            endGameStringLeben = "Although you didn't win, I hope you enjoyed playing Tod Und Leben!";

        }

    }

}

