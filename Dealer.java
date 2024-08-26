import java.util.ArrayList;

public class Dealer {
    private Card hiddenCard;
    private int sum;
    private int aceCount;
    private ArrayList<Card> hand;

    public Dealer(int sum, int aceCount) {
        this.hand = new ArrayList<Card>();
        this.sum = sum;
        this.aceCount = aceCount;
    }

    public void addCardToHand(Card hand) {
        this.hand.add(hand);
    }

    public void setAceCount(int dealerAceCount) {
        this.aceCount += dealerAceCount;
    }

    public void setSum(int dealerSum) {
        this.sum += dealerSum;
    }

    public void setHiddenCard(Card hiddenCard) {
        this.hiddenCard = hiddenCard;
    }

    public int reducedAceCount() {
        while (sum > 21 && aceCount > 0) {
            this.sum -= 10;
            this.aceCount -= 1;
        }
        return sum;
    } 

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getSum() {
        return sum;
    }

    public int getAceCount() {
        return aceCount;
    }

    public int getHiddenCardValue() {
        return hiddenCard.getValue();
    }

    public Card getHiddenCard() {
        return hiddenCard;
    }

}
