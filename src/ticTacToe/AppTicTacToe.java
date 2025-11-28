package ticTacToe;

import ticTacToe.common.Mark;
import ticTacToe.control.HashTagControl;
import ticTacToe.model.HashTagModel;
import ticTacToe.model.ScoreModel;
import ticTacToe.player.Player;
import ticTacToe.player.UserPlayer;
import ticTacToe.player.VirtualPlayer;
import ticTacToe.view.HashTagView;
import ticTacToe.view.ScoreView;

public class AppTicTacToe {

	public static void main(String[] args) {

		HashTagModel model = new HashTagModel();
		HashTagView view = new HashTagView(model);
		ScoreModel scoreModel = new ScoreModel();
		ScoreView scoreView = new ScoreView(scoreModel);
		HashTagControl control = new HashTagControl(model, view, scoreModel, scoreView);

		Player user = new UserPlayer(model);
		user.setMark(Mark.X);
		Player robot = new VirtualPlayer(model);
		robot.setMark(Mark.O);
		
		control.setPlayerA(user);
		control.setPlayerB(robot);
		control.go();

	}
}
