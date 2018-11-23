package edu.tum.cs.i1.eist;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import javax.swing.JOptionPane;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;
	private BumpersWindow gameWindow;
	private Action startAction;
	private Action stopAction;

	public ToolBar(BumpersWindow gameWindow) {
		super();

		setFloatable(false);
		initActions();
		add(startAction);
		add(stopAction);
		addSeparator();

		this.gameWindow = gameWindow;
	}

	private void initActions() {
		startAction = new AbstractAction("start") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				gameWindow.gameBoardUI.startGame();
			}
		};

		stopAction = new AbstractAction("stop") {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				gameWindow.gameBoardUI.stopGame();
				int ret = JOptionPane.showConfirmDialog(null,
						"Do you really want to stop the game ?",
						"Stop Game Confirmation", JOptionPane.YES_NO_OPTION);
				if (ret == 0) {
					gameWindow.gameBoardUI.gameSetup();
				} else {
					gameWindow.gameBoardUI.startGame();
				}
			}
		};
	}

	public void resetToolBarButtonStatus(boolean running) {
		startAction.setEnabled(!running);
		stopAction.setEnabled(running);
	}

}
