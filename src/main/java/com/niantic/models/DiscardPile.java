package com.niantic.models;

import java.util.ArrayList;
import java.util.Stack;

public class DiscardPile {
    private Stack<Card> cards = new Stack<>();

    public Stack<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.push(card);
    }

    public Card getTopCard()
    {
        return cards.peek();
    }

    public void updateSuit(String newSuit)
    {
        Card topCard = getTopCard();
        topCard.setSuit(newSuit);
    }


}
