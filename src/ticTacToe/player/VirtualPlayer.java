package ticTacToe.player;

import ticTacToe.common.Mark;
import ticTacToe.model.HashTagModel;
import ticTacToe.util.Delay;

public class VirtualPlayer extends AbstractPlayer {
	
	private static final int SIZE = 3;

	public VirtualPlayer(HashTagModel hashTag) {
		super(hashTag);
	}

	@Override
	public void play() {
		Delay.pauseSeconds(3);

		if (playToWin())
			return;

		if (playToNotLose())
			return;

		if (playStrategy())
			return;

		playRandom();
	}

	private void playRandom() {

		int lin = ((int) (Math.random() * 10)) % 3;
		for (int i = 0; i < SIZE; i++, lin = (++lin % 3)) {

			int col = ((int) (Math.random() * 10)) % 3;
			for (int j = 0; j < SIZE; j++, col = (++col % 3)) {

				if (hashTag.isBlank(lin, col)) {
					hashTag.setMark(lin, col, myMark);
					return;
				}

			}

		}

	}

	private boolean playStrategy() {

		if (hashTag.isBlank(1, 1)) {
			hashTag.setMark(1, 1, myMark);
			return true;
		}

		int[][] corners = {{0, 0}, {0, 2}, {2, 0}, {2, 2}};
		for (int[] pos : corners) {
			int lin = pos[0];
			int col = pos[1];
			if (hashTag.isBlank(lin, col)) {
				hashTag.setMark(lin, col, myMark);
				return true;
			}
		}

		return false;
	}

	private boolean playToNotLose() {

		for (int lin = 0; lin < SIZE; lin++) {
			for (int col = 0; col < SIZE; col++) {

				if (hashTag.isBlank(lin, col)) {
					if (canWinOnNextMove(lin, col, opponentMark)) {
						hashTag.setMark(lin, col, myMark); 
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean playToWin() {

		for (int lin = 0; lin < SIZE; lin++) {
			for (int col = 0; col < SIZE; col++) {

				if (hashTag.isBlank(lin, col)) {
					if (canWinOnNextMove(lin, col, myMark)) {
						hashTag.setMark(lin, col, myMark);
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean canWinOnNextMove(int lin, int col, Mark mark) {

		if (checkLineForWin(lin, mark, col)) { 
			return true;
		}

		if (checkColumnForWin(col, mark, lin)) { 
			return true;
		}

		if (lin == col && checkMainDiagonalForWin(mark, lin, col)) { 
			return true;
		}

		if (lin + col == SIZE - 1 && checkSecondDiagonalForWin(mark, lin, col)) {
			return true;
		}

		return false;
	}

	private boolean checkLineForWin(int lin, Mark mark, int ignoredCol) {
		
		Mark[] line = hashTag.getMarksOfLine(lin);
		int count = 0;
		for (int c = 0; c < SIZE; c++) {
			if (c == ignoredCol || line[c] == mark) {
				count++;
			}
		}
		return count == SIZE;
	}

	private boolean checkColumnForWin(int col, Mark mark, int ignoredLin) {
		
		Mark[] column = hashTag.getMarksOfColumn(col);
		int count = 0;
		for (int l = 0; l < SIZE; l++) {
			if (l == ignoredLin || column[l] == mark) {
				count++;
			}
		}
		return count == SIZE;
	}

	private boolean checkMainDiagonalForWin(Mark mark, int ignoredLin, int ignoredCol) {
		
		Mark[] diagonal = hashTag.getMarksOfMainDiagonal();
		int count = 0;
		for (int i = 0; i < SIZE; i++) {
			if ((i == ignoredLin && i == ignoredCol) || diagonal[i] == mark) {
				count++;
			}
		}
		return count == SIZE;
	}

	private boolean checkSecondDiagonalForWin(Mark mark, int ignoredLin, int ignoredCol) {
		
		Mark[] diagonal = hashTag.getMarksOfSecondDiagonal();
		
		int count = 0;
		int[][] positions = {{2, 0}, {1, 1}, {0, 2}};
		
		for (int i = 0; i < SIZE; i++) {
			int lin = positions[i][0];
			int col = positions[i][1];

			if ((lin == ignoredLin && col == ignoredCol) || diagonal[i] == mark) {
				count++;
			}
		}
		return count == SIZE;
	}
}
