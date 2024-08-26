import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private int sum;
    private int aceCount;

    public Player(int sum, int aceCount){
        this.hand = new ArrayList<Card>();
        this.sum = sum;
        this.aceCount = aceCount;
    }
    
    public void setAceCount(int playerAceCount) {
       this.aceCount = playerAceCount;
    }

    public void setSum(int playerSum) {
        this.sum += playerSum;
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

}
