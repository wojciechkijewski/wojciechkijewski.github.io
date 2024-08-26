import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class Frame {
    private final int BOARD_WIDTH = 600;
    private final int BOARD_HEIGHT = BOARD_WIDTH;
    private final int CARD_WIDTH = 110;
    private final int CARD_HEIGHT = 154;
    private Player player;
    private Dealer dealer;
    private Deck deck;
    private boolean activeGame = true;

    public Frame(Player player, Dealer dealer, Deck deck) {
        this.dealer = dealer;
        this.player = player;
        this.deck = deck;
        frame.setVisible(true);
        frame.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(new Color(53, 101, 77));
        frame.add(gamePanel);
        hitButton.setFocusable(false);
        buttonPanel.add(hitButton);
        stayButton.setFocusable(false);
        buttonPanel.add(stayButton);
        exitButton.setFocusable(false);
        buttonPanel.add(exitButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        hitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Card card = deck.removeLastCard();
                player.setSum(card.getValue());
                player.setAceCount(card.isAce() ? 1 : 0);
                player.getHand().add(card);
                gamePanel.repaint();
                if (player.reducedAceCount() > 21) {
                    hitButton.setEnabled(false);
                }
                System.out.println(player.getSum());
            }
        });
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        stayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hitButton.setEnabled(false);
                stayButton.setEnabled(false);

                while (dealer.getSum() < 17) {
                    Card card = deck.removeLastCard();
                    dealer.setSum(card.getValue());
                    dealer.setAceCount(card.isAce() ? 1 : 0);
                    dealer.getHand().add(card);
                }
                gamePanel.repaint();

            }
        });
        gamePanel.repaint();
    }

    JFrame frame = new JFrame("BlackJack");
    JPanel gamePanel = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            try {
                if (activeGame) {
                    // draw hidden card
                    Image hiddenCardImg = new ImageIcon(getClass().getResource("./cards/BACK.png")).getImage();
                    if (!stayButton.isEnabled()) {
                        hiddenCardImg = new ImageIcon(getClass().getResource(dealer.getHiddenCard().getImagePath()))
                                .getImage();
                    }
                    g.drawImage(hiddenCardImg, 20, 20, CARD_WIDTH, CARD_HEIGHT, null);
                    // draw dealer's hand
                    for (int i = 0; i < dealer.getHand().size(); i++) {
                        Card card = dealer.getHand().get(i);
                        Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
                        g.drawImage(cardImg, CARD_WIDTH + 25 + (CARD_WIDTH + 5) * i, 20, CARD_WIDTH, CARD_HEIGHT, null);
                    }
                    // draw players's hand
                    for (int i = 0; i < player.getHand().size(); i++) {
                        Card card = player.getHand().get(i);
                        Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
                        g.drawImage(cardImg, 20 + (CARD_WIDTH + 5) * i, 320, CARD_WIDTH, CARD_HEIGHT, null);
                    }

                    if (!stayButton.isEnabled()) {
                        dealer.reducedAceCount();
                        player.reducedAceCount();
                        // DISPLAY POINTS
                        System.out.println("DEALER HAS " + dealer.getSum());
                        System.out.println("PLAYER HAS " + player.getSum());

                        String message = "";
                        if (player.getSum() > 21) {
                            message = "You Lose!";
                        } else if (dealer.getSum() > 21) {
                            message = "You Win!";
                        } else if (dealer.getSum() == player.getSum()) {
                            message = "Tie!";
                        } else if (player.getSum() > dealer.getSum()) {
                            message = "You Win!";
                        } else {
                            message = "You Lose!";
                        }
                        g.setFont(new Font("Arial", Font.PLAIN, 30));
                        g.setColor(Color.WHITE);
                        g.drawString(message, 220, 250);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    JPanel buttonPanel = new JPanel();
    JButton hitButton = new JButton("HIT");
    JButton stayButton = new JButton("STAY");
    JButton exitButton = new JButton("EXIT");
}
