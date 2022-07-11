import java.util.*;

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
                System.out.println("Your total:\n" + p1.getTotal());
                dealer.printDealerHand();
                System.out.println("Dealers's total:\n" + dealer.getTotal());
                System.out.println("Would you like to hit? Enter Y/N");
                do {
                    option = sc.nextLine().charAt(0);
                    if (option == 'Y') {
                        p1.deal(deck);
                    }
                    else if (option == 'N') {
                        System.out.println("You stood. Your total is: " + p1.getTotal());
                        while (dealer.getTotal() <= 17) {
                            dealer.deal(deck);
                        }
                        break;
                    }
                    else {
                        System.out.println("Invalid character. Please try again");
                    }
                } while (option != 'Y' && option != 'N'); // Loop for valid option

                if (option == 'N') {
                    break;
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
        for (int i = 0; i < deck.size(); ++i) {
            System.out.println(deck.get(i).getValue() + " " + deck.get(i).getSuit());
        }

        System.out.println("Welcome to Black Jack!");
        System.out.println("Choose the gamemode:");
        System.out.println("1 - 1 Player");
        System.out.println("2 - 2 Players");

        do {
            gametype = sc.nextInt();
            sc.nextLine();
            if (gametype != 1 && gametype != 2) {
                System.out.println("Invalid Gamemode, try again.");
            }
        } while (gametype != 1 && gametype != 2);
        
        if (gametype == 1) {
            System.out.println("Enter the first player's name");
            p1.setName(sc.nextLine());
            p1.setGamemode(1);
            p2.setName("Dealer");
            playGame1(p1, p2, deck);
        }
        else if (gametype == 2) {
            System.out.println("Enter the first player's name"); 
            p1.setName(sc.nextLine());
            p1.setGamemode(2);
            System.out.println("Enter the second player's name");
            p2.setName(sc.nextLine());
            p2.setGamemode(2);
        }


        System.out.println("Thank you for playing!");
        sc.close();
    }
}