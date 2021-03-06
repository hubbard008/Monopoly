package Monopoly;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.*;

/*****************************************************************
 Class displays a Monopoly board with basic GUI elements and player's
    on the board.
 *****************************************************************/
public class Board extends JPanel {
    private listener listener;

    private Dice dice;

    private PropertySpaces mediterraneanAve;
    private PropertySpaces balticAve;
    private PropertySpaces orientalAve;
    private PropertySpaces vermontAve;
    private PropertySpaces connecticutAve;
    private PropertySpaces stCharlesPlace;
    private PropertySpaces statesAve;
    private PropertySpaces virginiaAve;
    private PropertySpaces stJamesPlace;
    private PropertySpaces tennesseeAve;
    private PropertySpaces newYorkAve;
    private PropertySpaces kentuckyAve;
    private PropertySpaces indianaAve;
    private PropertySpaces illinoisAve;
    private PropertySpaces atlanticAve;
    private PropertySpaces ventnorAve;
    private PropertySpaces marvinGardens;
    private PropertySpaces pacificAve;
    private PropertySpaces northCarolinaAve;
    private PropertySpaces pennsylvaniaAve;
    private PropertySpaces parkPlace;
    private PropertySpaces boardWalk;

    private PropertySpaces go;
    private PropertySpaces commChest1;
    private PropertySpaces incomeTax;
    private PropertySpaces readingRR;
    private PropertySpaces chance1;
    private PropertySpaces inJail;
    private PropertySpaces electric;
    private PropertySpaces pennRR;
    private PropertySpaces commChest2;
    private PropertySpaces freePark;
    private PropertySpaces chance2;
    private PropertySpaces boRR;
    private PropertySpaces waterWorks;
    private PropertySpaces gotoJail;
    private PropertySpaces commChest3;
    private PropertySpaces shortRR;
    private PropertySpaces chance3;
    private PropertySpaces luxuryTax;

    private ArrayList<ChanceCommCard> chanceDeck = new ArrayList<ChanceCommCard>();
    private ArrayList<ChanceCommCard> communityChestDeck = new ArrayList<ChanceCommCard>();

    private MonopolyModel model;

    private Color bgColor = new Color(206, 230, 208);

    private ArrayList<PropertySpaces> propertyArray = new ArrayList<PropertySpaces>();

    private Rectangle player1;
    private Rectangle player2;
    private Rectangle player3;
    private Rectangle player4;
    private Rectangle currentPlayerRect;

    private CustomJButton rollDiceButton;
    private CustomJLabel moneyLabel;
    private CustomJLabel currentPlayerLabel;

    private JPanel buttonPanel;

    /*****************************************************************
     Constructor creates the board and player elements along with
        any buttons and labels. All arrays for community chest cards,
        chance cards, and properties are instantiated as well.
     *****************************************************************/
    public Board() {
        listener = new listener();
        model = new MonopolyModel();

        dice = model.getDice();

        buttonPanel = new JPanel();

        this.setLayout(null);
        buttonPanel.setLayout(null);

        createBoardAndPopulatePropArray();
        model.getCurrentPlayer().populateAllProperties(propertyArray);

        communityChestDeck = generateCommunityChestDeck();
        chanceDeck = generateChanceDeck();


        PropertySpaces currProp = propertyArray.get(getArrayIndexOfProperty(model.getCurrentPlayer()));

        if (model.getPlayerList().size() >= 1) {
            player1 = new Rectangle(currProp.x + currProp.width/2 + 10, currProp.y + currProp.height/2 + 5,
                    15, 15);
        }
        if (model.getPlayerList().size() >= 2) {
            player2 = new Rectangle(currProp.x + currProp.width/2 + 11, currProp.y + currProp.height/2 + 6,
                    15, 15);
        }
        if (model.getPlayerList().size() >= 3) {
            player3 = new Rectangle(currProp.x + currProp.width/2 + 12, currProp.y + currProp.height/2 + 7,
                    15, 15);
        }
        if (model.getPlayerList().size() == 4) {
            player4 = new Rectangle(currProp.x + currProp.width/2 + 13, currProp.y + currProp.height/2 + 8,
                    15, 15);
        }


        currentPlayerRect = player1;


        Color translucentC = new Color(1f, 0f, 0f, 0.0f);
        buttonPanel.setSize(388, 840);
        buttonPanel.setBackground(translucentC);

        buttonPanel.setLocation(new Point(parkPlace.x + parkPlace.height + 14, 14));

        rollDiceButton = new CustomJButton("Roll Dice", buttonPanel.getWidth() - 14, 50);
        rollDiceButton.setLocation(new Point(7, 7));
        rollDiceButton.addActionListener(listener);
        rollDiceButton.setBackground(translucentC);

        currentPlayerLabel = new CustomJLabel("Current Player: " + model.getCurrentPlayer().getPlayerNum(), rollDiceButton.getX(), rollDiceButton.getY() + rollDiceButton.getHeight() + 10);
        moneyLabel = new CustomJLabel("Money: $" + model.getCurrentPlayer().getMoney(), currentPlayerLabel.getX(), currentPlayerLabel.getY() + currentPlayerLabel.getHeight() + 10);

        buttonPanel.add(rollDiceButton);
        buttonPanel.add(moneyLabel);
        buttonPanel.add(currentPlayerLabel);

        add(buttonPanel);

        buttonPanel.setLocation(854, 7);
        rollDiceButton.setEnabled(true);
        rollDiceButton.setVisible(true);
    }

    /*****************************************************************
     Method that returns the current model for the game

     @return this.model
     *****************************************************************/
    public MonopolyModel getModel() {
        return this.model;
    }

    /*****************************************************************
     Method that returns the roll button to be used for JUnit testing

     @return this.rollDiceButton
     *****************************************************************/
    public JButton getRollButton() {
        return this.rollDiceButton;
    }

    /*****************************************************************
     Helper method that creates all properties and adds them to an
        array of all properties
     *****************************************************************/
    private void createBoardAndPopulatePropArray() {
        mediterraneanAve = new PropertySpaces(70, 106, 672, 741, "MEDITERRANEAN", "AVENUE","", 60, new Color(138, 76, 53), 0, 1, true, 1);
        balticAve = new PropertySpaces(70, 106, 532, 741, "BALTIC", "AVENUE","", 60, new Color(138, 76, 53), 0, 3, true, 1);
        propertyArray.add(mediterraneanAve);
        propertyArray.add(balticAve);

        orientalAve = new PropertySpaces(70, 106, 322, 741, "ORIENTAL", "AVENUE","", 100, new Color(164, 218, 250), 0, 6, true, 2);
        vermontAve = new PropertySpaces(70, 106, 182, 741, "VERMONT", "AVENUE","", 100, new Color(164, 218, 250), 0, 8, true, 2);
        connecticutAve = new PropertySpaces(70, 106, 112, 741, "CONNECTICUT", "AVENUE","", 150, new Color(164, 218, 250), 0, 9, true, 2);
        propertyArray.add(orientalAve);
        propertyArray.add(vermontAve);
        propertyArray.add(connecticutAve);

        stCharlesPlace = new PropertySpaces(70, 105, 25, 654, "ST. CHARLES", "PLACE","", 140, new Color(215, 63, 151), 1, 11, true, 3);
        statesAve = new PropertySpaces(70, 105, 25, 514, "STATES", "AVENUE","", 140, new Color(215, 63, 151), 1, 13, true, 3);
        virginiaAve = new PropertySpaces(70, 105, 25, 444, "VIRGINIA", "AVENUE","", 160, new Color(215, 63, 151), 1, 14, true, 3);
        propertyArray.add(stCharlesPlace);
        propertyArray.add(statesAve);
        propertyArray.add(virginiaAve);

        stJamesPlace = new PropertySpaces(70, 105, 25, 304, "ST. JAMES", "PLACE","", 180, new Color(245, 148, 49), 1, 16, true, 4);
        tennesseeAve = new PropertySpaces(70, 105, 25, 164, "TENNESSEE", "AVENUE","", 180, new Color(245, 148, 49), 1, 18, true, 4);
        newYorkAve = new PropertySpaces(70, 105, 25, 94, "NEW YORK", "AVENUE","", 200, new Color(245, 148, 49), 1, 19, true, 4);
        propertyArray.add(stJamesPlace);
        propertyArray.add(tennesseeAve);
        propertyArray.add(newYorkAve);

        kentuckyAve = new PropertySpaces(70, 104, 112, 7, "KENTUCKY", "AVENUE","", 220, new Color(235, 32, 46), 2, 21, true, 5);
        indianaAve = new PropertySpaces(70, 104, 252, 7, "INDIANA", "AVENUE","", 220, new Color(235, 32, 46), 2, 23, true, 5);
        illinoisAve = new PropertySpaces(70, 104, 322, 7, "ILLINOIS", "AVENUE","", 240, new Color(235, 32, 46), 2, 24, true, 5);
        propertyArray.add(kentuckyAve);
        propertyArray.add(indianaAve);
        propertyArray.add(illinoisAve);

        atlanticAve = new PropertySpaces(70, 104, 462, 7, "ATLANTIC", "AVENUE","", 260, new Color(253, 239, 53), 2, 26, true, 6);
        ventnorAve = new PropertySpaces(70, 104, 532, 7, "VENTNOR", "AVENUE","", 260, new Color(253, 239, 53), 2, 27, true, 6);
        marvinGardens = new PropertySpaces(70, 104, 672, 7, "MARVIN", "GARDENS","", 280, new Color(253, 239, 53), 2, 29, true, 6);
        propertyArray.add(atlanticAve);
        propertyArray.add(ventnorAve);
        propertyArray.add(marvinGardens);

        pacificAve = new PropertySpaces(70, 105, 759, 94, "PACIFIC", "AVENUE","", 300, new Color(43, 177, 94), 3, 31, true, 7);
        northCarolinaAve = new PropertySpaces(70, 105, 759, 164, "NORTH", "CAROLINA","AVENUE", 300, new Color(43, 177, 94), 3, 32, true, 7);
        pennsylvaniaAve = new PropertySpaces(70, 105, 759, 304, "PENNSYLVANIA", "AVENUE","", 320, new Color(43, 177, 94), 3, 34, true, 7);
        propertyArray.add(pacificAve);
        propertyArray.add(northCarolinaAve);
        propertyArray.add(pennsylvaniaAve);

        parkPlace = new PropertySpaces(70, 105, 759, 514, "PARK", "PLACE","", 350, new Color(14, 102, 176), 3, 37, true, 8);
        boardWalk = new PropertySpaces(70, 105, 759, 654, "BOARD", "WALK","", 400, new Color(14, 102, 176), 3, 39, true, 8);
        propertyArray.add(parkPlace);
        propertyArray.add(boardWalk);

        go = new PropertySpaces(742, 742, 105, 105, 200, 0, "GO", "", "", false, 0);
        commChest1 = new PropertySpaces(602, 742, 70, 105, 0, 2, "COMMUNITY", "CHEST", "", false, 0);
        incomeTax = new PropertySpaces(462, 742, 70, 105, 200, 4, "INCOME", "TAX", "", false, 0);
        readingRR = new PropertySpaces(392, 742, 70, 105, 200, 5, "READING", "RAILROAD", "", true, 9);
        chance1 = new PropertySpaces(252, 742, 70, 105, 0, 7, "CHANCE", "", "", false, 0);
        inJail = new PropertySpaces(8, 742, 105, 105, 200, 10, "JUST", "VISITING", "/JAIL", false, 0);
        electric = new PropertySpaces(7, 602, 105, 70, 150, 12, "ELECTRIC", "COMPANY", "", true, 0);
        pennRR = new PropertySpaces(7, 392, 105, 70, 200, 15, "PENNSYLVANIA", "RAILROAD", "", true, 9);
        commChest2 = new PropertySpaces(7, 252, 105, 70, 0, 17, "COMMUNITY", "CHEST", "", false, 0);
        freePark = new PropertySpaces(8, 8, 105, 105, 0, 20, "FREE", "PARKING", "", false, 0);
        chance2 = new PropertySpaces(182, 7, 70, 105, 0, 22, "CHANCE", "", "", false, 0);
        boRR = new PropertySpaces(392, 7, 70, 105, 200, 25, "B&O", "RAILROAD", "", true, 9);
        waterWorks = new PropertySpaces(602, 7, 70, 105, 150, 28, "WATER", "WORKS", "", true, 0);
        gotoJail = new PropertySpaces(742, 11, 105, 105, 0, 30, "GO", "TO", "JAIL", false, 0);
        commChest3 = new PropertySpaces(742, 252, 105, 70, 0, 33, "COMMUNITY", "CHEST", "", false, 0);
        shortRR = new PropertySpaces(742, 392, 105, 70, 200, 35, "SHORT", "LINE", "", true, 9);
        chance3 = new PropertySpaces(742, 462, 105, 70, 0, 36, "CHANCE", "", "", false, 0);
        luxuryTax = new PropertySpaces(742, 602, 105, 70, 100, 38, "LUXURY", "TAX", "", false, 0);

        propertyArray.add(go);
        propertyArray.add(commChest1);
        propertyArray.add(incomeTax);
        propertyArray.add(readingRR);
        propertyArray.add(chance1);
        propertyArray.add(inJail);
        propertyArray.add(electric);
        propertyArray.add(pennRR);
        propertyArray.add(commChest2);
        propertyArray.add(freePark);
        propertyArray.add(chance2);
        propertyArray.add(boRR);
        propertyArray.add(waterWorks);
        propertyArray.add(gotoJail);
        propertyArray.add(commChest3);
        propertyArray.add(shortRR);
        propertyArray.add(chance3);
        propertyArray.add(luxuryTax);
    }

    /*****************************************************************
     Helper method to create a deck of Chance cards

     @return ArrayList
     *****************************************************************/
    // change all to correct values
    private ArrayList<ChanceCommCard> generateChanceDeck() {
        ArrayList<ChanceCommCard> deck = new ArrayList<ChanceCommCard>();

        deck.add(new ChanceCommCard("Advance to Go (Collect $200)", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("Advance to Illinois Ave - If you pass Go, collect $200", 200, false, false, 24, true));
        deck.add(new ChanceCommCard("Advance to St. Charles Place - If you pass Go, collect $200", 200, false, false, 11, true));
        deck.add(new ChanceCommCard("Advance to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("Advance to nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay owner twice the rental", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("Bank pays you dividend of $50", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("Get Out of Jail Free", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("Go Back 3 Spaces", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("Go to Directly to Jail - If you pass Go, do not collect $200", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("Make general repairs on all your properties - For each house pay $25 - For each hotel pay $100", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("Pay Poor Tax of $15", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("Take a trip to Reading Railroad - If you pass Go, collect $200", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("Take a walk on the Boardwalk - Advance to Boardwalk", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("You have been elected Chairman of the Board - Pay each player $50", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("Your building and loan matures - Collect $150", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("You have won a crossword competition - Collect $100", 200, false, false, 0, true));

        return deck;
    }

    /*****************************************************************
     Helper method to create a deck of Community Chest Cards

     @return ArrayList
     *****************************************************************/
    // change all to correct values
    private ArrayList<ChanceCommCard> generateCommunityChestDeck() {
        ArrayList<ChanceCommCard> deck = new ArrayList<ChanceCommCard>();

        deck.add(new ChanceCommCard("Advance to Go (Collect $200)", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("Bank error in your favor - Collect $200", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("Doctor's fee - Pay $50", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("From sale of stock you get $50", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("Get Out of Jail Free", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("Got to Jail - Go directly to Jail - If you pass Go, do not collect $200", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("Grand Opera Night - Collect $50 from every player for opening night seats", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("Holiday Fund matures - Receive $100", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("Income tax refund - Collect $20", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("It is your birthday - Collect $10", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("Life insurance matures - Collect $100", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("Pay hospital fees of $100", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("Pay school fees of $150", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("Receive $25 consultancy fee", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("You are assessed for street repairs - $40 per house - $115 per hotel", 200, false, false, 0, true));
        deck.add(new ChanceCommCard("You have won second prize in a beauty contest - Collect $10", 10, false, false, 0, true));
        deck.add(new ChanceCommCard("You inherit $100", 100, false, false, 0, true));

        return deck;
    }

    /*****************************************************************
     Helper method to get the index of a property in the propert array
        who's position on the board matches the player's position

     @param p   Player
     @return int index
     *****************************************************************/
    private int getArrayIndexOfProperty(Player p) {
        for (int i = 0; i < propertyArray.size(); i++) {
            if (propertyArray.get(i).position == p.getPosition()) {
                return i;
            }
        }

        return 0;
    }

    /*****************************************************************
     Returns the property at the current player's position

     @return PropertySpaces property
     *****************************************************************/
    public PropertySpaces getPropertyOfCurrPosition () {
        for (int i = 0; i < propertyArray.size(); i++) {
            if (propertyArray.get(i).position == model.getCurrentPlayer().getPosition()) {
                return propertyArray.get(i);
            }
        }

        return propertyArray.get(22); // 22 == Go
    }

    /*****************************************************************
     Returns an ArrayList of the propert ArrayList

     @return ArrayList
     *****************************************************************/
    public ArrayList<PropertySpaces> getPropertyList() {
        return this.propertyArray;
    }

    private Player currPlayer;

    /*****************************************************************
     Private class ActionListener

     Performs all actions in accordance to the roll dice button being clicked
     *****************************************************************/
    private class listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (rollDiceButton == event.getSource()) {
                int currPos = model.getCurrentPlayer().getPosition();
                currPlayer = model.getCurrentPlayer();
                model.updatePlayerLocation(model.getCurrentPlayer());
                System.out.println("Rolled: " + dice.getRollAmount());
                int propIndex = getArrayIndexOfProperty(model.getCurrentPlayer());
                System.out.println("Property On: " + propertyArray.get(propIndex).getFullPropertyName() + "\n");


                int xAdj = 0;
                int yAdj = 0;

                if (model.getPlayerList().size() == 1) {
                    if (currPlayer.equals(model.getPlayerList().get(0))) {
                        currentPlayerRect = player1;
                        xAdj = 0;
                        yAdj = 0;
                    }
                } else if (model.getPlayerList().size() == 2) {
                    if (currPlayer.equals(model.getPlayerList().get(0))) {
                        currentPlayerRect = player1;
                        xAdj = 0;
                        yAdj = 0;
                    } else if (currPlayer.equals(model.getPlayerList().get(1))) {
                        currentPlayerRect = player2;
                        xAdj = 2;
                        yAdj = 2;
                    }
                } else if (model.getPlayerList().size() == 3) {
                    if (currPlayer.equals(model.getPlayerList().get(0))) {
                        currentPlayerRect = player1;
                        xAdj = 0;
                        yAdj = 0;
                    } else if (currPlayer.equals(model.getPlayerList().get(1))) {
                        currentPlayerRect = player2;
                        xAdj = 2;
                        yAdj = 2;
                    } else if (currPlayer.equals(model.getPlayerList().get(2))) {
                        currentPlayerRect = player3;
                        xAdj = 4;
                        yAdj = 4;
                    }
                } else if (model.getPlayerList().size() == 4) {
                    if (currPlayer.equals(model.getPlayerList().get(0))) {
                        currentPlayerRect = player1;
                        xAdj = 0;
                        yAdj = 0;
                    } else if (currPlayer.equals(model.getPlayerList().get(1))) {
                        currentPlayerRect = player2;
                        xAdj = 2;
                        yAdj = 2;
                    } else if (currPlayer.equals(model.getPlayerList().get(2))) {
                        currentPlayerRect = player3;
                        xAdj = 4;
                        yAdj = 4;
                    } else if (currPlayer.equals(model.getPlayerList().get(3))) {
                        currentPlayerRect = player4;
                        xAdj = 6;
                        yAdj = 6;
                    }
                }


                for (int i = 0; i < propertyArray.size(); i++) {
                    PropertySpaces prop = propertyArray.get(i);
                    if (prop.position == model.getCurrentPlayer().getPosition()) {


                        // player positioning
                        if (prop.position == 10 && !model.getCurrentPlayer().checkJailStatus()) {
                            // not in jail
                            currentPlayerRect = new Rectangle(prop.x + 7 + xAdj, prop.y + prop.height - 21 + yAdj, 15, 15);
                        } else {
                            if (prop.position >= 0 && prop.position < 11) {
                                // bottom row
                                currentPlayerRect = new Rectangle(prop.x + prop.width/2 - 10 + xAdj, prop.y + prop.height/2 + 10 + yAdj, 15, 15);
                            } else if (prop.position >= 20 && prop.position < 31){
                                // top row
                                currentPlayerRect = new Rectangle(prop.x + prop.width/2 - 10 + xAdj, prop.y + prop.height/2 - 15 + yAdj, 15, 15);
                            } else if (prop.position >= 11 && prop.position < 21) {
                                // left side
                                currentPlayerRect = new Rectangle(prop.x + prop.width/2 - 20 + xAdj, prop.y + prop.height/2 - 5 + yAdj, 15, 15);
                            } else {
                                // right side
                                currentPlayerRect = new Rectangle(prop.x + prop.width/2 + 10 + xAdj, prop.y + prop.height/2 - 5 + yAdj, 15, 15);
                            }

                            if (prop.position == 0) {
                                // on go
                                currentPlayerRect = new Rectangle(prop.x + prop.width/2 + 10 + xAdj, prop.y + prop.height/2 + 10 + yAdj, 15, 15);
                            } else if (prop.position == 10) {
                                // in jail
                                currentPlayerRect = new Rectangle(prop.x + prop.width/2 + xAdj, prop.y + prop.height/2 - 15 + yAdj, 15, 15);
                            } else if (prop.position == 20) {
                                // on free parking
                                currentPlayerRect = new Rectangle(prop.x + prop.width/2 - 20 + xAdj, prop.y + prop.height/2 - 15 + yAdj, 15, 15);
                            }

                        }

                        // try to buy property landed on
                        model.buyProperty();

                        // pay rent to owner of landed on propert, if it is owned by someone else
                        if (prop.getOwnedBy() != null && prop.getOwnedBy() != model.getCurrentPlayer()) {
                            model.payRent();
                        }



                        // temp: put wherever get out of jail is done
                        if (model.getCurrentPlayer().checkJailStatus()) {
                            model.getCurrentPlayer().changeJailStatus();
                        }

                        // update player money label when passing or landing on Go
                        moneyLabel.setText("Money: $" + model.getNextPlayer().getMoney());

                        // update current player label
                        if (model.getNextPlayer().getPlayerNum() == 1) {
                            currentPlayerLabel.setText("Current Player: " + model.getNextPlayer().getPlayerNum() + " - Blue");
                        } else if (model.getNextPlayer().getPlayerNum() == 2) {
                            currentPlayerLabel.setText("Current Player: " + model.getNextPlayer().getPlayerNum() + " - Green");
                        } else if (model.getNextPlayer().getPlayerNum() == 3) {
                            currentPlayerLabel.setText("Current Player: " + model.getNextPlayer().getPlayerNum() + " - Red");
                        } else if (model.getNextPlayer().getPlayerNum() == 4) {
                            currentPlayerLabel.setText("Current Player: " + model.getNextPlayer().getPlayerNum() + " - Pink");
                        }

                        break;
                    }
                }
            }

            if (model.getPlayerList().size() == 1) {
                if (currPlayer.equals(model.getPlayerList().get(0))) {
                    player1 = currentPlayerRect;
                }
            } else if (model.getPlayerList().size() == 2) {
                if (currPlayer.equals(model.getPlayerList().get(0))) {
                    player1 = currentPlayerRect;
                } else if (currPlayer.equals(model.getPlayerList().get(1))) {
                    player2 = currentPlayerRect;
                }
            } else if (model.getPlayerList().size() == 3) {
                if (currPlayer.equals(model.getPlayerList().get(0))) {
                    player1 = currentPlayerRect;
                } else if (currPlayer.equals(model.getPlayerList().get(1))) {
                    player2 = currentPlayerRect;
                } else if (currPlayer.equals(model.getPlayerList().get(2))) {
                    player3 = currentPlayerRect;
                }
            } else if (model.getPlayerList().size() == 4) {
                if (currPlayer.equals(model.getPlayerList().get(0))) {
                    player1 = currentPlayerRect;
                } else if (currPlayer.equals(model.getPlayerList().get(1))) {
                    player2 = currentPlayerRect;
                } else if (currPlayer.equals(model.getPlayerList().get(2))) {
                    player3 = currentPlayerRect;
                } else if (currPlayer.equals(model.getPlayerList().get(3))) {
                    player4 = currentPlayerRect;
                }
            }

            repaint();
            model.nextPlayer();
        }
    }

    /*****************************************************************
     Paint Component to create all GUI graphics

     @param g Graphics
     *****************************************************************/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // only use to make gui fit your screen, but will mess up jpanel, jbuttons, and jlabels positions
//        g2.scale(0.7, 0.7);


        g2.setBackground(Color.BLACK);

        AffineTransform old = g2.getTransform();    // original orientation

        g2.setColor(bgColor);
        g2.fill(new Rectangle(7, 7, 840, 840));

        // Middle area
        Image img1 = Toolkit.getDefaultToolkit().getImage("middle.png");
        g2.drawImage(img1, 112, 112, this);



        // Corners
        img1 = Toolkit.getDefaultToolkit().getImage("passGo.png");
        g2.drawImage(img1, 742, 738, this);

        img1 = Toolkit.getDefaultToolkit().getImage("inJail.png");
        g2.drawImage(img1, 8, 742, this);

        img1 = Toolkit.getDefaultToolkit().getImage("freeParking.png");
        g2.drawImage(img1, 8, 8, this);

        img1 = Toolkit.getDefaultToolkit().getImage("goToJail.png");
        g2.drawImage(img1, 742, 8, this);


        // Community Chests
        img1 = Toolkit.getDefaultToolkit().getImage("communityChest.png");
        g2.drawImage(img1, 602, 742, this);

        img1 = Toolkit.getDefaultToolkit().getImage("communityChest2.png");
        g2.drawImage(img1, 7, 252, this);

        img1 = Toolkit.getDefaultToolkit().getImage("communityChest3.png");
        g2.drawImage(img1, 742, 252, this);


        // Chance
        img1 = Toolkit.getDefaultToolkit().getImage("chance.png");
        g2.drawImage(img1, 252, 742, this);

        img1 = Toolkit.getDefaultToolkit().getImage("chance2.png");
        g2.drawImage(img1, 182, 7, this);

        img1 = Toolkit.getDefaultToolkit().getImage("chance3.png");
        g2.drawImage(img1, 742, 460, this);


        // RailRoads
        img1 = Toolkit.getDefaultToolkit().getImage("readingRR.png");
        g2.drawImage(img1, 392, 742, this);

        img1 = Toolkit.getDefaultToolkit().getImage("pennsylvaniaRR.png");
        g2.drawImage(img1, 7, 391, this);

        img1 = Toolkit.getDefaultToolkit().getImage("boRR.png");
        g2.drawImage(img1, 392, 7, this);

        img1 = Toolkit.getDefaultToolkit().getImage("shortLineRR.png");
        g2.drawImage(img1, 742, 391, this);


        // Utilities/Taxes
        img1 = Toolkit.getDefaultToolkit().getImage("incomeTax.png");
        g2.drawImage(img1, 462, 742, this);

        img1 = Toolkit.getDefaultToolkit().getImage("electricCompany.png");
        g2.drawImage(img1, 7, 602, this);

        img1 = Toolkit.getDefaultToolkit().getImage("waterWorks.png");
        g2.drawImage(img1, 602, 7, this);

        img1 = Toolkit.getDefaultToolkit().getImage("luxuryTax.png");
        g2.drawImage(img1, 742, 602, this);


        // Draw misc. property borders
        g2.setColor(Color.BLACK);
        g2.draw(new Rectangle(7, 7, 840, 840));
        g2.draw(new Rectangle(112, 111, 630, 630));
        g2.drawLine(462, 742, 462, 847);
        g2.drawLine(742, 461, 847, 461);


        ArrayList<PropertySpaces> currentPlayerProps = model.getCurrentPlayer().getPropertyCards();
        //draw shape/image
        for (int i = 0; i < propertyArray.size(); i++) {
            if (propertyArray.get(i).propColor != Color.BLACK) {

                // draw space
                PropertySpaces currProp = propertyArray.get(i);

                // reset rotation back to 0deg before rotating to correct deg
                g2.setTransform(old);
                switch (currProp.edge) {
                    // rotating in position and avoiding drift from rotate()
                    case 1:
                        g2.rotate(Math.toRadians(270), currProp.x+currProp.width/2, currProp.y+currProp.height/2);
                    case 2:
                        g2.rotate(Math.toRadians(270), currProp.x+currProp.width/2, currProp.y+currProp.height/2);
                    case 3:
                        g2.rotate(Math.toRadians(270), currProp.x+currProp.width/2, currProp.y+currProp.height/2);
                }

                // fill property space with main background color
                g2.setColor(bgColor);
                g2.draw(new Rectangle(currProp.x, currProp.y, currProp.width, currProp.height));
                g2.fillRect(currProp.x, currProp.y, currProp.width, currProp.height);

                // text attributes
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("Helvetica", Font.PLAIN, 8));

                // draw propName1
                String rectText = currProp.propName1;
                int rectTextWidth = g.getFontMetrics().stringWidth(rectText);
                int rectCenter = Math.round(((float)currProp.width/2)-((float)rectTextWidth/2));
                g2.drawString(currProp.propName1, currProp.x + rectCenter, currProp.y + 31);

                // draw propName2
                rectText = currProp.propName2;
                rectTextWidth = g.getFontMetrics().stringWidth(rectText);
                rectCenter = Math.round(((float)currProp.width/2)-((float)rectTextWidth/2));
                g2.drawString(currProp.propName2, currProp.x + rectCenter, currProp.y + 42);

                // draw propName3 if the space has it
                if (!currProp.propName3.equals("")) {
                    rectText = currProp.propName3;
                    rectTextWidth = g.getFontMetrics().stringWidth(rectText);
                    rectCenter = Math.round(((float)currProp.width/2)-((float)rectTextWidth/2));
                    g2.drawString(currProp.propName3, currProp.x + rectCenter, currProp.y + 52);
                }

                // draw propPrice
                rectText = "$" + currProp.propPrice;
                rectTextWidth = g.getFontMetrics().stringWidth(rectText);
                rectCenter = Math.round(((float)currProp.width/2)-((float)rectTextWidth/2));
                g2.drawString("$" + currProp.propPrice, currProp.x + rectCenter, currProp.y + 98);

                // draw space color
                g2.setColor(currProp.propColor);
                g2.fillRect(currProp.x, currProp.y, currProp.width, 21);

                g2.setColor(Color.BLACK);

                g2.draw(new Rectangle(currProp.x, currProp.y, currProp.width, currProp.height));
            }
        }

        // highlight owned properties with a border representing its owners color
        for (int i = 0; i < propertyArray.size(); i++) {
            // draw space
            PropertySpaces currProp = propertyArray.get(i);

            if (currProp.getOwnedBy() != null) {
                // reset rotation back to 0deg before rotating to correct deg
                g2.setTransform(old);

                switch (currProp.edge) {
                    // rotating in position and avoiding drift from rotate()
                    case 1:
                        g2.rotate(Math.toRadians(270), currProp.x + currProp.width / 2, currProp.y + currProp.height / 2);
                    case 2:
                        g2.rotate(Math.toRadians(270), currProp.x + currProp.width / 2, currProp.y + currProp.height / 2);
                    case 3:
                        g2.rotate(Math.toRadians(270), currProp.x + currProp.width / 2, currProp.y + currProp.height / 2);
                }
                g2.setColor(currProp.getOwnedBy().getPlayerColor());
                g2.draw(new Rectangle(propertyArray.get(i).x, propertyArray.get(i).y, propertyArray.get(i).width, propertyArray.get(i).height));
                g2.draw(new Rectangle(propertyArray.get(i).x + 1, propertyArray.get(i).y + 1, propertyArray.get(i).width - 2, propertyArray.get(i).height - 2));

                g2.setColor(Color.BLACK);
            }
        }

        g2.setTransform(old);


        // outline properties with color of player that owns it
        // x, y values to draw property rectangle
        int x = buttonPanel.getX() + 10;
        int y = 325;
        // draw owned prop rectangle
        g2.setColor(bgColor);
        g2.fill(new Rectangle(x+1, y+1, 196, 206));

        g2.setColor(Color.BLACK);
        g2.draw(new Rectangle(x, y, 200, 210));
        g2.draw(new Rectangle(x+1, y+1, 198, 208));

        for (int i = 0; i < propertyArray.size(); i++) {

            if (propertyArray.get(i).getOwnedBy() != null) {
                if (propertyArray.get(i).getOwnedBy() != model.getCurrentPlayer()) {
                    g2.setColor(Color.CYAN);
                } else {
                    if (propertyArray.get(i).position == 1 || propertyArray.get(i).position == 3) { // brown
                        g2.setColor(mediterraneanAve.propColor);
                    } else if (propertyArray.get(i).position == 6 || propertyArray.get(i).position == 8 || propertyArray.get(i).position == 9) {    // light blue
                        g2.setColor(orientalAve.propColor);
                    } else if (propertyArray.get(i).position == 11 || propertyArray.get(i).position == 13 || propertyArray.get(i).position == 14) { // magenta
                        g2.setColor(stCharlesPlace.propColor);
                    } else if (propertyArray.get(i).position == 16 || propertyArray.get(i).position == 18 || propertyArray.get(i).position == 19) { // orange
                        g2.setColor(stJamesPlace.propColor);
                    } else if (propertyArray.get(i).position == 21 || propertyArray.get(i).position == 23 || propertyArray.get(i).position == 24) { // red
                        g2.setColor(kentuckyAve.propColor);
                    } else if (propertyArray.get(i).position == 26 || propertyArray.get(i).position == 27 || propertyArray.get(i).position == 29) { // yellow
                        g2.setColor(atlanticAve.propColor);
                    } else if (propertyArray.get(i).position == 31 || propertyArray.get(i).position == 32 || propertyArray.get(i).position == 34) { // green
                        g2.setColor(pacificAve.propColor);
                    } else if (propertyArray.get(i).position == 37 || propertyArray.get(i).position == 39) {    // blue
                        g2.setColor(parkPlace.propColor);
                    } else if (propertyArray.get(i).position == 5 || propertyArray.get(i).position == 15
                            || propertyArray.get(i).position == 25 || propertyArray.get(i).position == 35) {    // black
                        g2.setColor(Color.BLACK);
                    } else if (propertyArray.get(i).position == 12 || propertyArray.get(i).position == 28) {    // light gray
                        g2.setColor(Color.lightGray);
                    }
                }

                switch (propertyArray.get(i).position) {
                    case 1: // mediterraneanAve
                        g2.fill(new Rectangle(x + 10, y + 50, 10, 10));
                        break;

                    case 3: // balticAve
                        g2.fill(new Rectangle(x + 30, y + 50, 10, 10));
                        break;

                    case 5: // readingRR
                        g2.fill(new Rectangle(x + 10, y + 30, 10, 10));
                        break;

                    case 6: // orientalAve
                        g2.fill(new Rectangle(x + 10, y + 70, 10, 10));
                        break;

                    case 8: // vermontAve
                        g2.fill(new Rectangle(x + 30, y + 70, 10, 10));
                        break;

                    case 9: // connecticutAve
                        g2.fill(new Rectangle(x + 50, y + 70, 10, 10));
                        break;

                    case 11: // stCharlesPlace
                        g2.fill(new Rectangle(x + 10, y + 90, 10, 10));
                        break;

                    case 12: // electric
                        g2.fill(new Rectangle(x + 50, y + 50, 10, 10));
                        break;

                    case 13: // statesAve
                        g2.fill(new Rectangle(x + 30, y + 90, 10, 10));
                        break;

                    case 14: // virginiaAve
                        g2.fill(new Rectangle(x + 50, y + 90, 10, 10));
                        break;

                    case 15: // pennsylvaniaRR
                        g2.fill(new Rectangle(x + 30, y + 30, 10, 10));
                        break;

                    case 16: // stJamesPlace
                        g2.fill(new Rectangle(x + 10, y + 110, 10, 10));
                        break;

                    case 18: // tennesseeAve
                        g2.fill(new Rectangle(x + 30, y + 110, 10, 10));
                        break;

                    case 19: // newYorkAve
                        g2.fill(new Rectangle(x + 50, y + 110, 10, 10));
                        break;

                    case 21: // kentuckyAve
                        g2.fill(new Rectangle(x + 10, y + 130, 10, 10));
                        break;

                    case 23: // indianaAve
                        g2.fill(new Rectangle(x + 30, y + 130, 10, 10));
                        break;

                    case 24: // illinoisAve
                        g2.fill(new Rectangle(x + 50, y + 130, 10, 10));
                        break;

                    case 25: // boRR
                        g2.fill(new Rectangle(x + 50, y + 30, 10, 10));
                        break;

                    case 26: // atlanticAve
                        g2.fill(new Rectangle(x + 10, y + 150, 10, 10));
                        break;

                    case 27: // ventnorAve
                        g2.fill(new Rectangle(x + 30, y + 150, 10, 10));
                        break;

                    case 28: // water
                        g2.fill(new Rectangle(x + 70, y + 50, 10, 10));
                        break;

                    case 29: // marvinGardens
                        g2.fill(new Rectangle(x + 50, y + 150, 10, 10));
                        break;

                    case 31: // pacificAve
                        g2.fill(new Rectangle(x + 10, y + 170, 10, 10));
                        break;

                    case 32: // northCarolinaAve
                        g2.fill(new Rectangle(x + 30, y + 170, 10, 10));
                        break;

                    case 34: // pennsylvaniaAve
                        g2.fill(new Rectangle(x + 50, y + 170, 10, 10));
                        break;

                    case 35: // shortLine
                        g2.fill(new Rectangle(x + 70, y + 30, 10, 10));
                        break;

                    case 37: // parkPlace
                        g2.fill(new Rectangle(x + 10, y + 190, 10, 10));
                        break;

                    case 39: // boardWalk
                        g2.fill(new Rectangle(x + 30, y + 190, 10, 10));
                        break;

                    default:
                        System.out.println();
                }
            }
        }

        // draw player on board as 10x10 square (maybe change to actual images)

        if (model.getPlayerList().size() == 4) {
            g2.setColor(model.getPlayerList().get(0).getPlayerColor());
            g2.setBackground(model.getPlayerList().get(0).getPlayerColor());
            g2.draw(player1);
            g2.fill(player1);

            g2.setColor(model.getPlayerList().get(1).getPlayerColor());
            g2.setBackground(model.getPlayerList().get(1).getPlayerColor());
            g2.draw(player2);
            g2.fill(player2);

            g2.setColor(model.getPlayerList().get(2).getPlayerColor());
            g2.setBackground(model.getPlayerList().get(2).getPlayerColor());
            g2.draw(player3);
            g2.fill(player3);

            g2.setColor(model.getPlayerList().get(3).getPlayerColor());
            g2.setBackground(model.getPlayerList().get(3).getPlayerColor());
            g2.draw(player4);
            g2.fill(player4);


        } else if (model.getPlayerList().size() == 3) {
            g2.setColor(model.getPlayerList().get(0).getPlayerColor());
            g2.setBackground(model.getPlayerList().get(0).getPlayerColor());
            g2.draw(player1);
            g2.fill(player1);

            g2.setColor(model.getPlayerList().get(1).getPlayerColor());
            g2.setBackground(model.getPlayerList().get(1).getPlayerColor());
            g2.draw(player2);
            g2.fill(player2);

            g2.setColor(model.getPlayerList().get(2).getPlayerColor());
            g2.setBackground(model.getPlayerList().get(2).getPlayerColor());
            g2.draw(player3);
            g2.fill(player3);


        } else if (model.getPlayerList().size() == 2) {
            g2.setColor(model.getPlayerList().get(0).getPlayerColor());
            g2.setBackground(model.getPlayerList().get(0).getPlayerColor());
            g2.draw(player1);
            g2.fill(player1);

            g2.setColor(model.getPlayerList().get(1).getPlayerColor());
            g2.setBackground(model.getPlayerList().get(1).getPlayerColor());
            g2.draw(player2);
            g2.fill(player2);


        } else if (model.getPlayerList().size() == 1) {
            g2.setColor(model.getPlayerList().get(0).getPlayerColor());
            g2.setBackground(model.getPlayerList().get(0).getPlayerColor());
            g2.draw(player1);
            g2.fill(player1);
        }


        // draw outlines for the owned property display
        g2.setColor(Color.BLACK);
        // draw RR owned squares
        g2.draw(new Rectangle(x + 10, y + 30, 10, 10));
        g2.draw(new Rectangle(x + 30, y + 30, 10, 10));
        g2.draw(new Rectangle(x + 50, y + 30, 10, 10));
        g2.draw(new Rectangle(x + 70, y + 30, 10, 10));

        // draw utilities owned
        g2.draw(new Rectangle(x + 50, y + 50, 10, 10));
        g2.draw(new Rectangle(x + 70, y + 50, 10, 10));

        //draw the rest of the owned properties
        g2.draw(new Rectangle(x + 10, y + 50, 10, 10));
        g2.draw(new Rectangle(x + 30, y + 50, 10, 10));

        g2.draw(new Rectangle(x + 10, y + 70, 10, 10));
        g2.draw(new Rectangle(x + 30, y + 70, 10, 10));
        g2.draw(new Rectangle(x + 50, y + 70, 10, 10));

        g2.draw(new Rectangle(x + 10, y + 90, 10, 10));
        g2.draw(new Rectangle(x + 30, y + 90, 10, 10));
        g2.draw(new Rectangle(x + 50, y + 90, 10, 10));

        g2.draw(new Rectangle(x + 10, y + 110, 10, 10));
        g2.draw(new Rectangle(x + 30, y + 110, 10, 10));
        g2.draw(new Rectangle(x + 50, y + 110, 10, 10));

        g2.draw(new Rectangle(x + 10, y + 130, 10, 10));
        g2.draw(new Rectangle(x + 30, y + 130, 10, 10));
        g2.draw(new Rectangle(x + 50, y + 130, 10, 10));

        g2.draw(new Rectangle(x + 10, y + 150, 10, 10));
        g2.draw(new Rectangle(x + 30, y + 150, 10, 10));
        g2.draw(new Rectangle(x + 50, y + 150, 10, 10));

        g2.draw(new Rectangle(x + 10, y + 170, 10, 10));
        g2.draw(new Rectangle(x + 30, y + 170, 10, 10));
        g2.draw(new Rectangle(x + 50, y + 170, 10, 10));

        g2.draw(new Rectangle(x + 10, y + 190, 10, 10));
        g2.draw(new Rectangle(x + 30, y + 190, 10, 10));

        g2.setFont(new Font("Helvetica", Font.PLAIN, 16));
        g2.drawString("Properties Owned", x + 35, y + 20);

        g2.setFont(new Font("Helvetica", Font.PLAIN, 12));
        g2.drawString("Get Out of Jail Cards: " + model.getCurrentPlayer().howManyOutOfJailCards(), x + 65, y + 200);
    }

    /*****************************************************************
     Custom button class to set size of button
     *****************************************************************/
    public class CustomJButton extends JButton {
        public CustomJButton(String x, int w, int h) {
            this.setText(x);
            this.setSize(new Dimension(w, h));

        }

        @Override
        public void repaint() {
            super.repaint();
        }
    }

    /*****************************************************************
     Custom label class to set setting for label text
     *****************************************************************/
    public class CustomJLabel extends JLabel {
        public CustomJLabel(String s, int x, int y) {
            this.setText(s);
            this.setLocation(new Point(x, y));
            this.setFont(new Font("Helvetica", Font.PLAIN, 14));
            int rectTextWidth = 200;
            int rectTextHeight = Board.this.getFontMetrics(new Font("Helvetica", Font.PLAIN, 14)).getHeight();
            this.setSize(new Dimension(rectTextWidth + 50, rectTextHeight + 10));
        }
    }

}


