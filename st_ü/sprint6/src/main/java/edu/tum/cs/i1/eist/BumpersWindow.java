package edu.tum.cs.i1.eist;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BumpersWindow extends JFrame {

	//constants
	private static final long serialVersionUID = 1L;

	//user interface
	private JPanel content = new JPanel(new BorderLayout(), true);
	public GameBoardUI gameBoardUI;
	public ToolBar toolBar;

	public BumpersWindow() {
		super("Bumpers");

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				BumpersWindow.this.close();
			}});

		ToolBar toolBar = new ToolBar(this);
		gameBoardUI = new GameBoardUI(toolBar);
		toolBar.resetToolBarButtonStatus(false);

		getContentPane().setLayout(new BorderLayout());
		content.add(toolBar, BorderLayout.NORTH);
		content.add(gameBoardUI, BorderLayout.CENTER);
		content.add(gameBoardUI.gameBoard.getPlayerCar().getInstrumentPanel(), BorderLayout.EAST);
		getContentPane().add(content, BorderLayout.CENTER);
		
		toolBar.setupStrategyBox();
	}


	@Override
	public void doLayout(){
		super.doLayout();
		content.doLayout();
		this.getContentPane().doLayout();
	}

	private void close() {
		setVisible(false);
		System.exit(0);
	}
}