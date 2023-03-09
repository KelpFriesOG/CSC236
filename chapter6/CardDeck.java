package chapter6;

import java.util.Iterator;
import java.util.Random;
import javax.swing.ImageIcon;

public class CardDeck {

    public static final int NUMCARDS = 52;

    protected ABList<Card> deck;
    protected Iterator<Card> dealer;

    public CardDeck() {

        deck = new ABList<Card>(NUMCARDS);
        ImageIcon image;
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {

                image = new ImageIcon("/cards/" + suit + "_" + rank
                        + "_RA.gif");

                deck.add(new Card(rank, suit, image));
            }
        }
        dealer = deck.iterator();

    }

    public void shuffle() {

        Random rand = new Random();
        int randLoc;
        Card temp;

        for (int i = (NUMCARDS - 1); i > 0; i--) {

            randLoc = rand.nextInt(i);

            temp = deck.get(randLoc);

            deck.set(randLoc, deck.get(i));

            deck.set(i, temp);

        }

        dealer = deck.iterator();

    }

    public boolean hasNextCard() {
        return dealer.hasNext();
    }

    public Card nextCard() {
        return dealer.next();
    }

}
