import java.util.*;

/*
Player class
Gamemode currently not in use as gamemodes aren't implemented
*/
public class Player {
    private String name;
    private int gamemode;
    private ArrayList<Card> hand = new ArrayList<>();

    Player() {
        name = "";
        gamemode = 0;
    }
    public void deal(ArrayList<Card> deck) {
        Card currCard = deck.get(deck.size() - 1);
        hand.add(currCard);
        deck.remove(deck.get(deck.size() - 1));
    }
    public void printHand() {
        String currentHand = new String("[");
        System.out.println(name + " hand:");
        for (int i = 0; i < this.hand.size(); ++i) {
            Card currCard = this.hand.get(i);
            int cardVal = currCard.getValue();
            if (cardVal == 1) {
                currentHand += "1 " + currCard.getSuit() + ",";
            }
            else if (cardVal == 11) {
                currentHand += "J " + currCard.getSuit() + ",";
            }
            else if (cardVal == 12) {
                currentHand += "Q " + currCard.getSuit() + ",";
            }
            else if (cardVal == 13) {
                currentHand += "K " + currCard.getSuit() + ",";
            }
            else {
                currentHand += currCard.getValue() + " " + currCard.getSuit() + ",";
            }
        }
        currentHand = currentHand.substring(0, currentHand.length() - 1);
        currentHand += ']';
        System.out.println(currentHand);
    }
    public void printDealerHand() {
        Card dCard = this.hand.get(0);
        System.out.println("Dealer's upcard: [" + dCard.getValue() + " " + dCard.getSuit() + "]");
    }
    public void clearHand() {
        this.hand.clear();
    }
    public String getName() { 
        return this.name;
    }
    public int getTotal() {
        int newTotal = 0;
        for (int i = 0; i < hand.size(); ++i) {
            if (hand.get(i).getValue() > 10) {
                newTotal += 10;
            }
            else {
                newTotal += hand.get(i).getValue();
            }
        }
        return newTotal;
    }
    public ArrayList<Card> getHand() {
        return this.hand;
    }
    public void setName(String newName) {
        this.name = newName;
    }
    public void setGamemode(int newGM) {
        this.gamemode = newGM;
    }

}