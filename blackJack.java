import java.util.*;

/* 
This project is based on simulating the game Black Jack in a program.
It is currently unfinished and will have the following implementations:
- Two player functionality (1v1)
- Betting system
- GUI

INSTRUCTIONS:
The basics of the game involves the player and a dealer.
The goal of the game is to not get your hand total to not break 21 and
either have the dealer break 21 or have a higher hand total than the dealer.
The player and dealer gets two cards and the dealer only reveals one card.
The player will keep on asking for cards, "hitting", until the user is satisfied with their hand.
After the player stands, the dealer will reveal the second card and will draw cards until their hand
total is over 16.
Once the dealer reaches greater than 16, and if the player hasn't already busted, the player's and dealers
hand values are compared and the highest total wins.

*/
public class blackJack {
    public static void generateDeck(ArrayList<Card> deck) {
        deck.clear();
        for (int i = 1; i <= 13; ++i) {
            deck.add(new Card(i, '♤'));
            deck.add(new Card(i, '♧'));
            deck.add(new Card(i, '♢'));
            deck.add(new Card(i, '♡'));
        }
    }

    public static void shuffleDeck(ArrayList<Card> deck) {
        for (int i = 0; i < 52; ++i) {
            Random randNum = new Random();
            int indexToSwap = randNum.nextInt(52);
            Card temp1 = new Card(deck.get(i));
            deck.set(i, deck.get(indexToSwap));
            deck.set(indexToSwap, temp1);
        }
    }

    public static void playGame1(Player p1, Player dealer, ArrayList<Card> deck) {
        boolean gameLoop = true;
        char option = '-';
        Scanner sc = new Scanner(System.in);
        while (gameLoop) {
            for (int i = 0; i < 2; ++i) {
                p1.deal(deck);
                dealer.deal(deck);
            }
            while (p1.getTotal() <= 21) {
                System.out.print('\n');
                p1.printHand();
                System.out.println("Your total:\n" + p1.getTotal()); // To be removed, used in testing

                dealer.printDealerHand();
                System.out.println("Dealers's total:\n" + dealer.getTotal()); // To be removed, used in testing

                // Reading user input
                System.out.println("Would you like to hit? Enter Y/N");
                do {
                    option = sc.nextLine().charAt(0);
                    if (option == 'Y') {
                        p1.deal(deck);
                    }
                    else if (option == 'N') {
                        System.out.println("You stood. Your total is: " + p1.getTotal());
                        // After standing, if your didn't bust, 
                        // the dealer must hit until their hand value is >= 17
                        while (dealer.getTotal() <= 17) {
                            dealer.deal(deck);
                        }
                        break;
                    }
                    else {
                        System.out.println("Invalid character. Please try again");
                    }
                } while (option != 'Y' && option != 'N'); // Loop for valid option
                
                // If user input wants to stay
                if (option == 'N') {
                    System.out.println('\n');
                    break;
                }
            }
            if (p1.getTotal() <= 21) {
                System.out.println("You stood.");
                p1.printHand();
                dealer.printHand();

                if (dealer.getTotal() > 21) {
                    System.out.println("The dealer busted. You win!");
                }
                else if (p1.getTotal() > dealer.getTotal()) {
                    System.out.println("You beat the dealer!");
                }
                else {
                    System.out.println("You lose!");
                }
            }
            if (p1.getTotal() > 21) {
                System.out.println("Your total is: " + p1.getTotal() + "\nYou busted!");
            }

            System.out.println("Play again? Enter Y/N");
            option = sc.nextLine().charAt(0);
            do {
                if (option == 'Y') {
                    generateDeck(deck);
                    shuffleDeck(deck);
                    p1.clearHand();
                    dealer.clearHand();
                }
                if (option == 'N') {
                    gameLoop = false;
                }
            } while (option != 'Y' && option != 'N');
        }   
        sc.close();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int gametype;
        Player p1 = new Player();
        Player p2 = new Player();
        ArrayList<Card> deck = new ArrayList<>();

        generateDeck(deck);
        shuffleDeck(deck);

        /* 
        Prints out every card in the deck with its value and suit
        Used for testing purposes

        for (int i = 0; i < deck.size(); ++i) {
            System.out.println(deck.get(i).getValue() + " " + deck.get(i).getSuit());
        }

        */

        System.out.println("Welcome to Black Jack!\n");
        System.out.println("Choose the gamemode:");
        System.out.println("1 - 1 Player");
        System.out.println("2 - 2 Players");

        do {
            //gametype = sc.nextInt(); 
            gametype = 1; // To be removed after gamemodes are implemented
            sc.nextLine();
            if (gametype != 1 && gametype != 2) {
                System.out.println("Invalid Gamemode, try again.");
            }
        } while (gametype != 1 && gametype != 2);
        
        if (gametype == 1) {
            System.out.println("\nEnter the first player's name");
            p1.setName(sc.nextLine());
            p1.setGamemode(1);
            p2.setName("Dealer");
            playGame1(p1, p2, deck);
        }

        // Currently not working
        /*
        else if (gametype == 2) {
            System.out.println("Enter the first player's name"); 
            p1.setName(sc.nextLine());
            p1.setGamemode(2);
            System.out.println("Enter the second player's name");
            p2.setName(sc.nextLine());
            p2.setGamemode(2);
        }
        */


        System.out.println("\nThank you for playing!\n");
        sc.close();
    }
}