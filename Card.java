/* 
Card class
Card value stored as an integer
Card suit stored as a character
*/
public class Card {
    private int value;
    private char suit;
    
    // Constructors
    Card() {
        value = 0;
        suit = '-'; // Temporary suit
    }
    Card(int value) {
        this.value = value;
    }
    Card(int value, char suit) {
        this.value = value;
        this.suit = suit;
    }
    Card (Card copy) {
        this.value = copy.value;
        this.suit = copy.suit;
    }
    
    // Getters
    public int getValue() {
        return this.value;
    }
    public char getSuit() {
        return this.suit;
    }
    
    // Setters
    public void setValue(int value) {
        this.value = value;
    }
    public void setSuit(char suit) {
        this.suit = suit;
    }
}