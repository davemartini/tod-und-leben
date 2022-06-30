import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;

/**
 *  The view of the current Tod Und Leben game. Graphical representation of the cards drawn, played, and scores for the round and between rounds.
 *  @author David Martinez
 *  @version 1.0
 */
public class GameField extends Canvas implements ActionListener {
    JFrame frame;
    JButton showCard;
    JButton dealCard;
    JButton redChoice;
    JButton blackChoice;
    JButton todUndLeben;
    JButton endUndLeben;
    JButton helpMe;

    JButton nextRound;

    Image playerCard;
    Image compCard;

    JPanel buttonPanel;
    JPanel deckCheck;
    JPanel nextRoundPanel;

    Image spaceHolder;
    Image deckPlace;
    Image compCardUpdate;
    Image playerCardUpdate;

    int playerDeckChoice;
    GameController myGameController;

    Image warCard1;
    Image warCard2;

    Image warCard3;
    Image warCard4;

    Image warCard5;
    Image warCard6;

    Image warCard7;
    Image warCard8;

    boolean showWinnerLoser;
    boolean gameStartConditon;
    boolean tableRunning;
    boolean cardCounterOn;

    Font myNewFont;
    public GameField(GameController currentGame){
        myGameController = currentGame;
        frame = new JFrame("Tod und Leben");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        this.setBackground(new Color(0xFFD2F1));
        frame.setPreferredSize(new Dimension(800,500)); 

        frame.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(0x4d4d4d),2,false));

        defaultState();
    }

    public void defaultState(){
        helpMe = new JButton("?");
        helpMe.addActionListener(this);

        showCard = new JButton("Play Card");
        dealCard = new JButton("Draw Card");
        todUndLeben = new JButton("TOD UND LEBEN!");
        endUndLeben = new JButton("Finish Tod Und Leben");

        nextRound = new JButton("Next round");

        dealCard.addActionListener(this);
        showCard.addActionListener(this);
        todUndLeben.addActionListener(this);
        endUndLeben.addActionListener(this);

        nextRound.addActionListener(this);


        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0xf0ffff));
        buttonPanel.add(dealCard);
        buttonPanel.add(showCard);
        buttonPanel.add(todUndLeben);
        buttonPanel.add(endUndLeben);

        buttonPanel.add(nextRound);


        frame.add(buttonPanel, BorderLayout.SOUTH);

        buttonPanel.setVisible(false);
        deckCheck = new JPanel();
        redChoice = new JButton("Play as Red");
        blackChoice = new JButton("Play as Black");
        deckCheck.setBackground(new Color(0xf0ffff));

        redChoice.addActionListener(this);
        blackChoice.addActionListener(this);

        //deckCheck.setLayout(new BorderLayout());

        deckCheck.add(redChoice);
        deckCheck.add(blackChoice);
        frame.add(deckCheck, BorderLayout.NORTH);

        myNewFont = new Font("Tahoma", Font.BOLD, 13);

        deckCheck.add(helpMe);
        //helpMe.setLocation(EAST);

        playerCard = null;
        compCard = null;

        compCardUpdate = null;
        playerCardUpdate = null;

        todUndLeben.setVisible(false);
        endUndLeben.setVisible(false);
        nextRound.setVisible(false);
        deckCheck.setVisible(true);

        tableRunning = true;
        showWinnerLoser = false;
        cardCounterOn = false;

        buttonPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        deckCheck.setBorder(BorderFactory.createRaisedBevelBorder());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void cardStateDeal(Graphics g) {        
        g.drawImage(playerCard, 363, 275,null);

        g.drawImage(compCard, 363, 25,null); 
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == dealCard){
            showCard.setVisible(true);
            dealCard.setVisible(false);
            myGameController.oneHand(this);
            getImagesDealState();            
            this.repaint();
        }
        else if(e.getSource() == showCard){
            showCard.setVisible(false);
            dealCard.setVisible(true);
            myGameController.calculateHand(this);
            getImagesShowState();
            this.repaint();         
        }
        else if(e.getSource() == redChoice){
            myGameController.playerDeckChoice = 1; 
            tableRunning = true;
            buttonPanel.setVisible(true);            
            deckCheck.setVisible(false);
            showCard.setVisible(false);           
            myGameController.gameSetup(this);
            cardCounterOn = true;

            dealCard.setVisible(true);
            this.repaint();

        }
        else if(e.getSource() == blackChoice){
            myGameController.playerDeckChoice = 2;
            tableRunning = true; 
            buttonPanel.setVisible(true);  

            deckCheck.setVisible(false);
            showCard.setVisible(false);

            myGameController.gameSetup(this);
            cardCounterOn = true;

            dealCard.setVisible(true);
            this.repaint();

        }
        else if(e.getSource() == todUndLeben){
            myGameController.todUndLeben(this);                        
            this.repaint();

        }
        else if(e.getSource() == endUndLeben){
            myGameController.lebenHandEnd(this);

        }
        else if(e.getSource() == nextRound){

            deckCheck.setVisible(true); 
            myGameController.roundReset(this);
            nextRound.setVisible(false);            
            this.repaint();
        }
        else if(e.getSource() == helpMe){
            PlayTodUndLeben.printDirections();
        }

    }   
    public void paint(Graphics g){
        g.setFont(myNewFont);
        if(tableRunning == true){
            tableStart(g);
            cardStateDeal(g);                    
            warStateStart(g);
            roundScore(g);
            handWinLose(g);
            roundsWon(g);

        }

        if(tableRunning == false){
            endGame(g);
        }
        if(cardCounterOn == true && tableRunning == true){
            cardCounter(g);
        }

    }

    public void tableStart(Graphics g){        
        spaceHolder = new ImageIcon("Carding/CardSpace.gif").getImage();
        deckPlace = new ImageIcon("Carding/CardBack2.gif").getImage();
        g.drawImage(deckPlace, 75, 275,null);
        g.drawImage(deckPlace, 75, 25,null);
        g.drawImage(deckPlace, 78, 275,null);
        g.drawImage(deckPlace, 78, 25,null); 
        g.drawImage(deckPlace, 81, 275,null);
        g.drawImage(deckPlace, 81, 25,null); 
        g.drawImage(spaceHolder, 363, 275,null);
        g.drawImage(spaceHolder, 363, 25,null); 

    }

    public void getImagesDealState(){                
        compCard = deckPlace;
        playerCard = playerCardUpdate;                
    }

    public void getImagesShowState(){
        playerCard = playerCardUpdate;
        compCard = compCardUpdate;
    }

    public void warStateStart(Graphics g){

        g.drawImage(warCard1,  445, 275,null);
        g.drawImage(warCard2,  445, 25,null);

        g.drawImage(warCard3,  522, 275,null);
        g.drawImage(warCard4,  522, 25,null);

        g.drawImage(warCard5,  599, 275,null);
        g.drawImage(warCard6,  599, 25,null);

    }

    public void recieveWarCard(Image playerWar, Image computerWar){
        int warCardPlace = myGameController.getWarCardPlace();

        if(warCardPlace == 1){
            warCard1 = playerWar;
            warCard2 = computerWar;
            this.repaint();
        }
        else if(warCardPlace == 2){
            warCard3 = playerWar;
            warCard4 = computerWar; 
            this.repaint();
        }
        else if(warCardPlace == 3){
            warCard5 = playerWar;
            warCard6 = computerWar;
            this.repaint();
        }

    }

    public void roundScore(Graphics g){
        g.drawString("Computer",85,135);
        g.drawString("You",104,273);
        g.drawString("Round Score: "+myGameController.getRoundScorePlayer()+" cards claimed", 160, 290);
        g.drawString("Round Score: "+myGameController.getRoundScoreComputer()+" cards claimed", 160, 117);

    }

    public void endGame(Graphics g){
        if(tableRunning == false){
            g.drawString(myGameController.getEndGameString(), 20, 250);
            g.drawString(myGameController.getEndGameStringLeben(), 20, 270);

        }
    }

public void cardCounter(Graphics g){
        g.drawString(myGameController.getCompDeckCount()+"",129, 20);
        g.drawString(myGameController.getPlayerDeckCount()+"",129, 387);
    }

    public void handWinLose(Graphics g){
        if(showWinnerLoser == true){
            g.drawString(myGameController.getWinnerLoser(),374, 225);

        }
    }
public void roundsWon(Graphics g){
        g.drawString("Rounds Won: "+myGameController.roundsWonPlayer,160, 330);
        g.drawString("Rounds Won: "+myGameController.roundsWonComp,160, 80);

    }
}

