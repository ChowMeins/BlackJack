public class Card {
    private int value;
    private char suit;

    Card() {
        value = 0;
        suit = '-';
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
    public int getValue() {
        return this.value;
    }
    public char getSuit() {
        return this.suit;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public void setSuit(char suit) {
        this.suit = suit;
    }

}
