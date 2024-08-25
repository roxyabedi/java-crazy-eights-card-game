package com.niantic.models;

import java.util.ArrayList;

public class Hand
{
    private final ArrayList<Card> cards = new ArrayList<>();

    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public int getCardCount()
    {
        return cards.size();
    }

    public void dealTo(Card card)
    {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }
}
