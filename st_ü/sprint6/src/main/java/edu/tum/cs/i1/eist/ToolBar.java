package edu.tum.cs.i1.eist;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import org.reflections.Reflections;

public class ToolBar extends JToolBar implements ItemListener {

	private static final long serialVersionUID = 1L;
	private BumpersWindow gameWindow;
	private Action startAction;
	private Action stopAction;
	
	private JComboBox<Object> strategyBox = new JComboBox<Object>();

	public ToolBar(BumpersWindow gameWindow) {
		super();
		this.gameWindow = gameWindow;
		
		setFloatable(false);
		initActions();
		add(startAction);
		add(stopAction);
		addSeparator();
		
		add(new JLabel("Collision Strategy:"));
        add(strategyBox);

		
	}
	
	public void setupStrategyBox() {
		List<Object> strategies = this.getStrategies();
        Iterator<Object> iter = strategies.iterator();
        while (iter.hasNext()) {
            strategyBox.addItem(iter.next());
        }
        
        strategyBox.setSelectedItem(gameWindow.gameBoardUI.gameBoard.getCollisionStrategy());
        strategyBox.addItemListener(this);

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
	
	private List<Object> strategies = null;
	
	public  List<Object> getStrategies() {
        if (strategies == null) {
        	Reflections reflections = new Reflections("edu.tum.cs.i1.eist");
    		Set<Class<? extends CollisionStrategy>> subTypes = reflections.getSubTypesOf(CollisionStrategy.class);
    		strategies = new ArrayList<Object>();
    		for (Class<? extends CollisionStrategy> selectedCollisionClass: subTypes) {
    			try {
					strategies.add(selectedCollisionClass.newInstance());
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
    		}
        }
        return strategies;
    }

	@Override
	public void itemStateChanged(ItemEvent e) {
		CollisionStrategy selectedCollision = (CollisionStrategy) strategyBox.getSelectedItem();
		gameWindow.gameBoardUI.gameBoard.setCollisionStrategy(selectedCollision);
	}

}
