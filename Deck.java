import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck;
    private Random random = new Random();

    public void buildDeck(){
        deck = new ArrayList<Card>();
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] types = {"C", "D", "H", "S"};

        for (int i = 0; i < types.length; i++) {
            for (int j = 0; j < values.length; j++ ) {
                Card card = new Card(values[j], types[i]);
                deck.add(card);
            }
        } 
    }

    public void shuffleDeck() {
        for (int i = 0; i < deck.size(); i++){
            int j = random.nextInt(deck.size());
            Card currCard = deck.get(i);
            Card randomCard = deck.get(j);
            deck.set(i, randomCard);
            deck.set(j, currCard);
        }
    }

    public void getNumberOfCardsCurretlyInDeck() {
        System.out.println(deck.size());
    }

    public Card removeLastCard() {
        return deck.remove(deck.size() -1);
    }
    
}
