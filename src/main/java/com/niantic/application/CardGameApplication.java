package com.niantic.application;

import com.niantic.models.Card;
import com.niantic.models.Deck;
import com.niantic.models.DiscardPile;
import com.niantic.models.Player;
import com.niantic.ui.ColorCodes;
import com.niantic.ui.UserInterface;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CardGameApplication {
    Scanner input = new Scanner(System.in);

    Deck deck = new Deck();
    DiscardPile discardPile = new DiscardPile();
    ArrayList<Player> players = new ArrayList<>();
    Player winner = new Player("");

    public void run() {
        addPlayers();
        dealCards();
        dealTopDiscardPileCard();
        playRounds();
        findWinner();
        UserInterface.displayWinner(winner);
    }

    private void dealCards() {
        deck.shuffle();

        for (int i = 0; i < 5; i++) {
            for (Player player : players) {
                Card card = deck.takeCard();
                player.dealTo(card);
            }
        }
    }

    private void dealTopDiscardPileCard() {
        Card card = deck.takeCard();
        discardPile.addCard(card);
    }

    private void addPlayers() {
        players.add(new Player("Player One"));
        players.add(new Player("Player Two"));
        players.add(new Player("Player Three"));
        players.add(new Player("Player Four"));
    }

    private void playRounds() {
        Queue<Player> playerQueue = new LinkedList<>(players);

        while (!playerQueue.isEmpty()) {
            var player = playerQueue.poll();
            UserInterface.displayAllPlayersCards(player, discardPile);

            if (checkForMatchingSuitOrRank()) {
                playMatchingCard(player);
            } else if (checkForCrazyEight()) {
                playCrazyEight(player);
            } else {
                drawCard(player);
            }
            if (player.getHand().getCardCount() == 0) {
                break;
            }
            playerQueue.offer(player);
        }
    }

    private boolean checkForMatchingSuitOrRank() {
        while (true) {
            System.out.println(ColorCodes.WHITE_BRIGHT + ColorCodes.GRAY_BACKGROUND + "Do you have a card that matches suit or rank? (y/n)" + ColorCodes.RESET);
            String response = input.nextLine().strip().toLowerCase();
            if (response.equalsIgnoreCase("y")) {
                return true;
            } else if (response.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println(ColorCodes.WHITE_BRIGHT + ColorCodes.RED_BACKGROUND + "Oops! Your selection didn't match :( Try again!" + ColorCodes.RESET);
                System.out.println();
            }
        }
    }

    private void playMatchingCard(Player player) {
        System.out.println(ColorCodes.WHITE_BRIGHT + ColorCodes.GRAY_BACKGROUND + "Which card would you like to play? (rank suit e.g. 5 Spades)" + ColorCodes.RESET);
        String cardToPlay = input.nextLine().strip();

        String[] cardParts = cardToPlay.split(" ");
        String cardToPlayRank = cardParts[0];
        String cardToPlaySuit = cardParts[1];

        Card selectedCard = player.getHand().getCards().stream()
                .filter(card -> card.getSuit().equals(cardToPlaySuit) && card.getFaceValue().equals(cardToPlayRank))
                .findFirst()
                .orElse(null);

        if (selectedCard != null) {
            if (selectedCard.getSuit().equals(discardPile.getTopCard().getSuit()) || selectedCard.getFaceValue().equals(discardPile.getTopCard().getFaceValue())) {
                discardPile.addCard(selectedCard);
                player.getHand().removeCard(selectedCard);
            } else {
                System.out.println(ColorCodes.WHITE_BRIGHT + ColorCodes.RED_BACKGROUND + "Oops! Your selection didn't match :( Try again!" + ColorCodes.RESET);
                System.out.println();
                playMatchingCard(player);
            }
        } else {
            System.out.println(ColorCodes.WHITE_BRIGHT + ColorCodes.RED_BACKGROUND + "Oops! Your selection didn't match :( Try again!" + ColorCodes.RESET);
            System.out.println();
            playMatchingCard(player);
        }

    }

    private boolean checkForCrazyEight() {
        System.out.println(ColorCodes.WHITE_BRIGHT + ColorCodes.GRAY_BACKGROUND + "Do you have a Crazy Eight? (y/n)?" + ColorCodes.RESET);
        String answer = input.nextLine().strip().toLowerCase();
        return answer.equalsIgnoreCase("y");
    }

    private void playCrazyEight(Player player) {
        System.out.println(ColorCodes.WHITE_BRIGHT + ColorCodes.GRAY_BACKGROUND + "Which Crazy Eight would you like to play? (e.g. 8 Hearts)" + ColorCodes.RESET);
        String crazyEight = input.nextLine().strip();

        String[] cardParts = crazyEight.split(" ");
        String crazyEightRank = cardParts[0];

        Card selectedCard = player.getHand().getCards().stream()
                .filter(card -> card.getFaceValue().equals(crazyEightRank))
                .findFirst()
                .orElse(null);

        discardPile.addCard(selectedCard);
        player.getHand().removeCard(selectedCard);
        System.out.println(ColorCodes.WHITE_BRIGHT + ColorCodes.RED_BACKGROUND + "A Crazy Eight has been played! Which suit would you like to change to?" + ColorCodes.RESET);
        String newSuit = input.nextLine().strip();
        discardPile.updateSuit(newSuit);
    }

    private void drawCard(Player player) {
        Card card = deck.takeCard();
        player.dealTo(card);
        System.out.println();
        System.out.println(ColorCodes.WHITE_BRIGHT + ColorCodes.OUTLINE + " Card Drawn: " + ColorCodes.RED_BACKGROUND + " " + card.getFaceValue() + " " + card.getSuit() + " " + ColorCodes.RESET);
        System.out.println();

        if (card.getSuit().equals(discardPile.getTopCard().getSuit()) || card.getFaceValue().equals(discardPile.getTopCard().getFaceValue())) {
            discardPile.addCard(card);
            player.getHand().removeCard(card);
            System.out.println(ColorCodes.WHITE_BRIGHT + ColorCodes.RED_BACKGROUND + "Drawn card was played." + ColorCodes.RESET);
            System.out.println();
        }
    }

    private void findWinner() {
        winner = players.stream()
                .filter(player -> player.getHand().getCardCount() == 0)
                .findFirst()
                .orElse(null);
    }
}

//    playRounds() Method all as one big method before being broken down for readability
//    private void playRounds() {
//        Queue<Player> playerQueue = new LinkedList<>(players);
//
//        while (!playerQueue.isEmpty()) {
//            var player = playerQueue.poll();
//            UserInterface.displayAllPlayersCards(player, discardPile);
//            System.out.println("Do you have a card that matches suit or rank? (y/n)");
//            String response = input.nextLine().strip().toLowerCase();
//
//            if (response.equalsIgnoreCase("y")) {
//                System.out.println("Which card would you like to play? (rank suit e.g. 5 Spades)");
//                String cardToPlay = input.nextLine().strip();
//
//                String[] cardParts = cardToPlay.split(" ");
//                String cardToPlayRank = cardParts[0];
//                String cardToPlaySuit = cardParts[1];
//
//                Card selectedCard = null;
//                for (Card card : player.getHand().getCards()) {
//                    if (card.getSuit().equals(cardToPlaySuit) && card.getFaceValue().equals(cardToPlayRank)) {
//                        selectedCard = card;
//                        break;
//                    }
//                }
//
//                if (selectedCard.getSuit().equals(discardPile.getTopCard().getSuit()) || selectedCard.getFaceValue().equals(discardPile.getTopCard().getFaceValue())) {
//                    discardPile.addCard(selectedCard);
//                    player.getHand().removeCard(selectedCard);
//                }
//            } else {
//                System.out.println("Do you have a Crazy Eight? (y/n)?");
//                String answer = input.nextLine().strip().toLowerCase();
//                if (answer.equalsIgnoreCase("y")) {
//                    System.out.println("Which Crazy Eight would you like to play? (e.g. 8 Hearts)");
//                    String crazyEight = input.nextLine().strip();
//
//                    String[] cardParts = crazyEight.split(" ");
//                    String crazyEightRank = cardParts[0];
//
//                    Card selectedCard = null;
//                    for (Card card : player.getHand().getCards()) {
//                        if (card.getFaceValue().equals(crazyEightRank)) {
//                            selectedCard = card;
//                            break;
//                        }
//                    }
//                    discardPile.addCard(selectedCard);
//                    player.getHand().removeCard(selectedCard);
//                    System.out.println("A Crazy Eight has been played! Which suit would you like to change to?");
//                    String newSuit = input.nextLine().strip();
//                    discardPile.updateSuit(newSuit);
//                } else {
//                    Card card = deck.takeCard();
//                    player.dealTo(card);
//                    System.out.println("Card Drawn: " + card.getFaceValue() + " " + card.getSuit());
//
//                    if (card.getSuit().equals(discardPile.getTopCard().getSuit()) || card.getFaceValue().equals(discardPile.getTopCard().getFaceValue())) {
//                        discardPile.addCard(card);
//                        player.getHand().removeCard(card);
//                        System.out.println("Drawn card was played.");
//                    }
//                }
//            }
//            if (player.getHand().getCardCount() == 0) {
//                break;
//            }
//            playerQueue.offer(player);
//        }
//    }