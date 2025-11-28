package ticTacToe.model;

import ticTacToe.common.Mark;

public class ScoreModel {

	private int scoreX = 0;
	private int scoreO = 0;

	public void reset() {
		this.scoreX = 0;
		this.scoreO = 0;
	}

	public void incScore(Mark mark) {

		switch(mark) {
		case O -> 		this.scoreO ++;
		case X -> 		this.scoreX ++;
		case BLANK -> 	hashCode();

		}
	}

	public Object scoreX() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object scoreO() {
		// TODO Auto-generated method stub
		return null;
	}
}
