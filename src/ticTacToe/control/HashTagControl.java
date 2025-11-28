package ticTacToe.control;

import java.util.Optional;
import java.util.Random;
import ticTacToe.common.Mark;
import ticTacToe.model.HashTagModel;
import ticTacToe.model.HashTagModelException;
import ticTacToe.model.ScoreModel;
import ticTacToe.player.Player;
import ticTacToe.view.HashTagView;
import ticTacToe.view.ScoreView;

public class HashTagControl {

	private static final int SIZE = 3;
	private HashTagModel model;
	private HashTagView view;

	private Player playerA;
	private Player playerB;
	private Player startPlayer;
	private Optional<Player> winner = Optional.empty();

	public HashTagControl (HashTagModel model , HashTagView view ) {
		this.model = model;
		this.view = view;
	}

	public HashTagControl(HashTagModel model2, HashTagView view2, ScoreModel scoreModel, ScoreView scoreView) {
		// TODO Auto-generated constructor stub
	}

	public void setPlayerA(Player player) {
		this.playerA = player;
	}

	public void setPlayerB (Player player) {
		this.playerB = player;
	}

	public void go() {

		gameStart();
		Player currentPlayer = startPlayer;

		while(model.hasBlanc()) {
			
			doPlay(currentPlayer);
			view.print();

			checkForWinner();
			if(winner.isPresent())
				break;

			currentPlayer = ((currentPlayer == playerA) ? playerB : playerA);
		}
		gameOver();

	}

	private Player getPlayer(Mark mark) {

		if (playerA.getMark() == mark) {
			return playerA;
		}

		if (playerB.getMark() == mark) {
			return playerB;
		}

		throw new RuntimeException("Marca inválida ou Player não configurado.");

	}

	private void defineStartPlayer() {

		if (winner.isPresent()) {
			startPlayer = winner.get();
			return;

		}

		if (startPlayer != null) {
			startPlayer = (startPlayer == playerA) ? playerB : playerA;
			return;
		}

		Random rand = new Random();
		if (rand.nextBoolean()) {
			startPlayer = playerA;
		} else {
			startPlayer = playerB;
		}
	}

	private void checkForWinner (Mark[] vMark) {

		if (winner.isPresent()) {
			return;
		}

		Mark firstMark = Mark.BLANK;

		for (Mark mark : vMark) {
			if (mark == Mark.BLANK) {
				return;
			}

			if (firstMark == Mark.BLANK) {
				firstMark = mark;
			} else if (mark != firstMark) {
				return;
			}
		}

		if (firstMark != Mark.BLANK) {
			winner = Optional.of(getPlayer(firstMark));
		}
	}

	private void checkForWinner() {

		if (winner.isPresent()) {
			return;
		}

		for (int i = 0; i < SIZE; i++) {

			checkForWinner(model.getMarksOfLine(i));
			if(winner.isPresent()) return;

			checkForWinner(model.getMarksOfColumn(i));
			if(winner.isPresent()) return;
		}

		checkForWinner(model.getMarksOfMainDiagonal());
		if(winner.isPresent()) return;

		checkForWinner(model.getMarksOfSecondDiagonal());
	}

	private void gameStart() {

		defineStartPlayer();

		model.reset();

		winner = Optional.empty();

		view.print();
	}

	private void gameOver() {

		Mark winnerMark = winner.isPresent()? winner.get().getMark() : Mark.BLANK;
			System.out.println("FIM DE JOGO! O vencedor é: " + winner.get().getMark());
			
			view.printGameOver(winnerMark);
			scoreModel.incScore(winnerMark);
			scoreView.printScore();
	}
	
	private void doPlay(Player player) {
		
		while(true) {
			try {
				player.play();
				return;
			}
			catch(HashTagModelException e) {
				view.printError(e.getMessage());
			}
		}
	}
}

