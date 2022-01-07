package info.fivecdesign.gamecollection.blackjack.ui;

import java.io.PrintStream;
import java.util.Scanner;

import info.fivecdesign.gamecollection.blackjack.backend.BlackJackGame;
import info.fivecdesign.gamecollection.portal.GameConsole;
import info.fivecdesign.gamecollection.portal.Portal;

public class ConsoleBlackJack implements GameConsole {

	@Override
	public String getName() {
		return "Black Jack";
	}

	@Override
	public int run(PrintStream out, Scanner in, Portal portal) {

		BlackJackGame game = new BlackJackGame();

		while (game.getMoney() > 0) {

			out.printf("How much do you want to bet? (Max: %d or enter 0 to quit): ", game.getMoney());

			String entry = in.next();

			if (portal.isNumeric(entry)) {

				int betAmount = Integer.parseInt(entry);

				if (betAmount <= 0) {
					// player wants to quit, the amount of money is the acheived score!
					return game.getMoney();
				}

				if (betAmount <= game.getMoney()) {
					playOneRoundOfBlackJack(out, in, game, betAmount);
				}
			}
		}

		return game.getMoney();
	}

	private void playOneRoundOfBlackJack(PrintStream out, Scanner in, BlackJackGame game, int bet) {

		game.startNewGame(bet);

		while (game.isGameRunning()) {

			out.printf("Dealer: %s%n", game.getVisibleDealerCardsAsString());
			out.printf("Player: %s%n", game.getPlayerCardsAsString());

			out.print("One more card (Y/N)?");

			String entry = "";
			while (!"Y".equals(entry) && !"N".equals(entry)) {
				entry = in.next().toUpperCase();
			}

			if ("Y".equals(entry)) {
				game.hit();
			} else {
				game.stand();
			}
		}

		displayBlackJackResult(out, in, game);
	}

	private void displayBlackJackResult(PrintStream out, Scanner in, BlackJackGame game) {

		out.printf("Dealers Deck: %s%n", game.getVisibleDealerCardsAsString());
		out.printf("Players Deck: %s%n", game.getPlayerCardsAsString());
		if (game.isGameDraw()) {
			out.println("Game is a draw!");
		} else if (game.isGameWon()) {
			out.println("You won this round!");
		} else {
			out.println("You lost this round!");
		}
		out.printf("You have %d left!%n", game.getMoney());

	}

}
