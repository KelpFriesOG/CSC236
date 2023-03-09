package chapter6;

import javax.swing.ImageIcon;

public class Card implements Comparable<Card> {

    public enum Rank {
        Two, Three, Four, Five, Six, Seven, Eight,
        Nine, Ten, Jack, Queen, King, Ace
    }

    public enum Suit {
        Club, Diamond, Heart, Spade
    }

    protected final Rank rank;
    protected final Suit suit;
    protected ImageIcon image;

    Card(Rank rank, Suit suit, ImageIcon image) {
        this.rank = rank;
        this.suit = suit;
        this.image = image;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        } else {
            Card c = (Card) obj;
            return (this.rank == c.rank);
        }
    }

    public int compareTo(Card o) {
        return this.rank.compareTo(o.rank);
        // You might be asking yourself, how are
        // enumerable types compared?
        // Well they are compared based on the order
        // in which they are defined.

        // Since I ordered the enumberable rank as
        // [Two, Three, Four...]
        // Java will know that Three is greater than Two
        // and that Four is greater than Three and Two.
        // If the rank of o and this are identical,
        // it knows to return 0.

    }

}
