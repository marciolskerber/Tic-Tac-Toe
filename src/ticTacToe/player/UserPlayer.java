package ticTacToe.player;

import ticTacToe.model.HashTagModel;
import ticTacToe.util.Console;

public class UserPlayer extends AbstractPlayer {

	public UserPlayer(HashTagModel hashTag) {
		super(hashTag);
		
	}

	@Override
	public void play() {
		
		Console.println("\nSua vez");
		
		int lin = Console.readInt("L: ");
		int col = Console.readInt("C: ");
		hashTag.setMark(lin, col, myMark);
		
	}

}
