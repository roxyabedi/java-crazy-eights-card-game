package com.niantic.ui;

import com.niantic.models.Card;
import com.niantic.models.Player;
import com.niantic.models.DiscardPile;

public class UserInterface {
    public static void displayAllPlayersCards(Player player, DiscardPile discardPile) {
        System.out.println(ColorCodes.WHITE_BRIGHT + ColorCodes.PURPLE_BACKGROUND + "Welcome to Crazy Eights!" + ColorCodes.RESET);
        System.out.println("-".repeat(36));
        displayDiscardPile(discardPile);
        System.out.println();
        System.out.println(ColorCodes.WHITE_BRIGHT + player.getName() + " Hand: " + ColorCodes.RESET);
        for (Card card : player.getHand().getCards()) {
            System.out.println(ColorCodes.WHITE_BRIGHT + "  " + card.getFaceValue() + " " + card.getSuit() + ColorCodes.RESET);
        }
        System.out.println();
    }

    public static void displayDiscardPile(DiscardPile discardPile) {
        System.out.println(ColorCodes.WHITE_BRIGHT + ColorCodes.OUTLINE + " Top Card of Discard Pile: " + ColorCodes.GREEN_BACKGROUND + " " + discardPile.getTopCard().getFaceValue() + " " + discardPile.getTopCard().getSuit() + " " + ColorCodes.RESET);
    }

    public static void displayWinner(Player winner) {
        System.out.println();
        System.out.println(ColorCodes.CYAN + "******************************************" + ColorCodes.RESET);
        System.out.println(ColorCodes.WHITE_BRIGHT + ColorCodes.CYAN_BACKGROUND + "          WINNER: " + winner.getName() + "            " + ColorCodes.RESET);
        System.out.println(ColorCodes.CYAN + "******************************************" + ColorCodes.RESET);
    }
}

