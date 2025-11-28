package ticTacToe.view;

import ticTacToe.common.Mark;
import ticTacToe.model.HashTagModel;
import ticTacToe.util.Console;

public class HashTagView {

	static final String alertFormat = "\nERRO - %s\nPreste mais atenção ";

	private final
	HashTagModel model;

	public HashTagView(HashTagModel model) {
		this.model = model;
	}

	private String mark(int lin, int col) {

		Mark mark = model.getMark(lin, col);
		return mark != Mark.BLANK ? mark.toString() : " ";
	}

	public void print() {

		Console.printf(hashFormat, mark(0,0), mark(0,1), mark(0,2),
				mark(1,0), mark(1,1), mark(1,2),
				mark(2,0), mark(2,1), mark(2,2));
	}

	public void printError(String message) {
		Console.printf(alertFormat, message);
	}

	public void printGameOver(Mark winner) {
		Console.println();
		if(winner != Mark.BLANK)
			Console.printf("'%s' Venceu.", winner);
		else
			Console.println("DEU VÉIA!!!");
	}

	static final 
	String hashFormat = """
			     |     |    
			  %s  |  %s  |  %s       
			_____|_____|______
			     |     |     
			  %s  |  %s  |  %s  
			_____|_____|______
			     |     |    
			  %s  |  %s  |  %s  
			     |     |      """;
}


