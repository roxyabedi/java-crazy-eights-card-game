# Review Project - Crazy Eights Card Game

<img width="512" alt="Screenshot 2024-09-01 at 2 58 02 PM" src="https://github.com/user-attachments/assets/2bb6b4a6-6e60-4ce5-a6fa-492bd4bc6eeb">

## Overview

This Java console application is a simulation of the classic card game Crazy Eights. The game is played with 4 players, where each player tries to be the first to get rid of all their cards. The game follows the standard rules of Crazy Eights, with players taking turns to match the top card of the discard pile by either rank or suit, or by playing an eight to change the current suit.

### Game Rules

1. **Starting the Game:**
   - Each player is dealt 5 cards at the beginning of the game.
   - The remaining cards form the draw pile, and the top card of the draw pile is placed face-up to start the discard pile.

2. **Player Turns:**
   - On a player's turn, they must play a card that matches the rank or suit of the top card on the discard pile.
   - If a player cannot match the top card, they must draw cards from the draw pile. If the drawn card matches, it will automatically be played. Otherwise it is added to the player's hand and their turn is over.
  
3. **Crazy Eights:**
   - Playing an eight allows the player to change the suit to any suit they choose, regardless of the top card's suit.
  
4. **Winning the Game:**
   - The first player to play all their cards wins the game.

### Features

- Four Player Simulation: The game is played with 4 players (with user input).
- Randomized Deck: The deck is shuffled randomly at the beginning of the game.
- Turn-Based Play: The game proceeds in turns, with each player having an opportunity to play or draw.

### Technologies Used
- Java: The core programming language used for implementing game logic.
- OOP Principles: The game utilizes Object-Oriented Programming principles, such as classes and objects, to model the game components (e.g., Card, Player, Deck).
- Console I/O: The game uses standard input and output (console) for player interactions and game updates.

## Instructions

### Unit Testing, Inheritance, Streams and Collections

This week you learned about Unit Testing, Inheritance, Streams and Collections. 
This project is intended to help you solidify the concepts that you learned
throughout the week.

You will create a console application that is based on a card game of your choice.
Think about a card game that you like to play and create an application that takes you through
the flow of the game.

Your starter code includes a few classes that you may choose to use. You can modify them as
needed or you can even replace them with your own classes completely.

Attempt to use some of the new collections that we discussed in class this week.
Your first priority is to get your game to work correctly. You can alway modify
the code later if needed.

### Evaluation criteria and functional requirements

* The project must compile (it cannot have any build errors).
* Create a class and inheritance structure where appropriate
  * Depending on the type of card game you choose may determine if cards should use inheritance
* Write unit tests for your classes
  * The application flow and the user interface classes do not need Unit Tests
    (UnitTests are more difficult to write for these types of classes)
* Try to find code where Streams could simplify your code
* Consider using collections besides an ArrayList if possible (not required)
