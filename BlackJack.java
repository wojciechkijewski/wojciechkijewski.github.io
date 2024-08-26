

public class BlackJack {
    // DECK
    Deck deck = new Deck();
    Player player = new Player(0, 0);
    Dealer dealer = new Dealer(0,0);
    Frame frame = new Frame(player, dealer, deck);

    BlackJack() {
        startGame();
    }

    public void startGame(){
        deck.buildDeck();
        deck.shuffleDeck();
        dealer.setHiddenCard(deck.removeLastCard());
        dealer.setSum(dealer.getHiddenCardValue());
        dealer.setAceCount(dealer.getHiddenCard().isAce() ? 1: 0);
        Card drawCard = deck.removeLastCard();
        dealer.setSum(drawCard.getValue());
        dealer.setAceCount(drawCard.isAce() ? 1 : 0);
        dealer.getHand().add(drawCard);
      
        for (int i = 0; i < 2; i++){
            drawCard = deck.removeLastCard();
            player.setSum(drawCard.getValue());
            player.setAceCount(drawCard.isAce() ? 1 : 0);
            player.getHand().add(drawCard);
        }
    }
}
