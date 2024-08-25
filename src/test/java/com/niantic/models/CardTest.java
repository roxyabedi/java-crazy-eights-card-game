package com.niantic.models;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CardTest
{
    @Test
    public void updateSuit_shouldUpdateSuitOfTopCardOfDiscardPile()
    {
        DiscardPile discardPile = new DiscardPile();
        Card card = new Card("Spades", "9");
        discardPile.addCard(card);
        discardPile.updateSuit("Hearts");

        String newSuit = discardPile.getTopCard().getSuit();
        String expectedSuit = "Hearts";

        assertEquals(expectedSuit, newSuit, "Because the new suit should be Hearts.");

    }

    @Test
    public void setSuit_shouldSetSuitToNewValue()
    {
        Card card = new Card("Spades", "9");
        card.setSuit("Clubs");

        String expectedSuit = "Clubs";
        String actualSuit = card.getSuit();

        assertEquals(expectedSuit, actualSuit, "Because the new suit should be Clubs.");
    }

    @Test
    public void getTopCard_shouldGetTopCardOfDiscardPile()
    {
        DiscardPile discardPile = new DiscardPile();
        Card card = new Card("Diamonds", "K");
        discardPile.addCard(card);

        Card actualCard = discardPile.getTopCard();

        assertEquals(card, actualCard, "Because the top card should be the K of Diamonds.");
    }

    @Test
    public void getCardCount_shouldReturnNumberOfCardsInEachHand()
    {
        Player player = new Player("Player One");
        Card card1 = new Card("Diamonds", "K");
        Card card2 = new Card("Diamonds", "Q");
        Card card3 = new Card("Diamonds", "J");
        Card card4 = new Card("Diamonds", "9");
        Card card5 = new Card("Diamonds", "2");
        player.dealTo(card1);
        player.dealTo(card2);
        player.dealTo(card3);
        player.dealTo(card4);
        player.dealTo(card5);

        int expectedCardCount = 5;
        int actualCardCount = player.getHand().getCardCount();

        assertEquals(expectedCardCount, actualCardCount, "Because the player was dealt 5 cards and should have 5 cards in their hand.");
    }

    @Test
    public void removeCard_shouldRemoveCardFromHand()
    {
        Player player = new Player("Player One");
        Card card1 = new Card("Diamonds", "K");
        Card card2 = new Card("Diamonds", "Q");
        Card card3 = new Card("Diamonds", "J");
        Card card4 = new Card("Diamonds", "9");
        Card card5 = new Card("Diamonds", "2");
        player.dealTo(card1);
        player.dealTo(card2);
        player.dealTo(card3);
        player.dealTo(card4);
        player.dealTo(card5);
        player.getHand().removeCard(card1);

        int expectedCardsInHand = 4;
        int actualCardsInHand = player.getHand().getCardCount();

        assertEquals(expectedCardsInHand, actualCardsInHand, "Because 1 card was removed, there should now be only 4 cards in the player's hand.");

    }

    @Test
    public void deck_shouldStartWith52Cards()
    {
        Deck deck = new Deck();

        int expectedDeckSize = 52;
        int actualDeckSize = deck.getCardCount();

        assertEquals(expectedDeckSize, actualDeckSize, "Because a deck starts with 52 cards.");
    }

    @Test
    public void takeCard_shouldRemoveFirstCardFromDeck()
    {
        Deck deck = new Deck();
        deck.shuffle();

        Card expectedTopCard = deck.getCards().getFirst();
        Card topCard = deck.takeCard();

        assertEquals(expectedTopCard, topCard, "Because it should remove the first card from the deck.");
    }
}