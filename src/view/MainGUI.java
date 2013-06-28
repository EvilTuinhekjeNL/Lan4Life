package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controller.FileController;
import controller.GameController;
import controller.PlayerController;
import controller.ScoreController;

public class MainGUI extends JFrame {

	/**
	 * Generated Serial Version
	 */
	private static final long serialVersionUID = -5056050661369885282L;
	private GameController gameController;
	private PlayerController playerController;
	private ScoreController scoreController;
	private FileController fileController;
	private BufferedImage bitches;
	private JPanel wrapper, buttonPanel, contentPanel, littleWrap;
	private JLabel intro, status;
	private JButton playBtn, viewPlayersBtn, viewScoreBtn;

	public MainGUI() {
		// fileController = new FileController();
		gameController = GameController.getInstance();// new GameController();
		playerController = PlayerController.getInstance();
		playerController.setGames(gameController.getGames());
		scoreController = ScoreController.getInstance();
		init();
	}

	private void init() {
		// Settings
		getContentPane().setSize(800, 600);
		setDefaultCloseOperation(exit());
		setTitle("LAN4LIFE");
		setResizable(false);

		defaultScreen();

		pack();
		setLocationRelativeTo(null);
	}

	private void defaultScreen() {
		wrapper = new JPanel(new BorderLayout());
		buttonPanel = new JPanel(new FlowLayout());
		littleWrap = new JPanel(new BorderLayout());
		contentPanel = new JPanel();
		littleWrap.setBackground(Color.decode("#1c1c1c"));
		contentPanel.setBackground(Color.decode("#1c1c1c"));
		buttonPanel.setBackground(Color.decode("#1c1c1c"));
		wrapper.setForeground(Color.WHITE);
		wrapper.setBackground(Color.decode("#1c1c1c"));
		wrapper.setAlignmentX(CENTER_ALIGNMENT);
		wrapper.setPreferredSize(new Dimension(800, 600));

		try {
			bitches = ImageIO.read(new File("src/resources/bitches.png"));
		} catch (IOException ex) {
			ex.printStackTrace(System.out);
		}

		// Adding
		intro = new JLabel(new ImageIcon(bitches));
		wrapper.add(intro, BorderLayout.NORTH);

		playBtn = new JButton("Play-A-Game!");
		playBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playGame();
				System.out.println("Clicky clicked");

			}
		});
		viewPlayersBtn = new JButton("View Players");
		viewScoreBtn = new JButton("View Scores");
		Dimension buttonSize = new Dimension(150, 75);
		playBtn.setPreferredSize(buttonSize);
		viewPlayersBtn.setPreferredSize(buttonSize);
		viewScoreBtn.setPreferredSize(buttonSize);
		buttonPanel.add(playBtn);
		buttonPanel.add(viewPlayersBtn);
		buttonPanel.add(viewScoreBtn);

		littleWrap.add(buttonPanel, BorderLayout.NORTH);
		littleWrap.add(contentPanel, BorderLayout.CENTER);
		wrapper.add(littleWrap, BorderLayout.CENTER);

		String statustxt = String.format(
				"Ronde %d; Spelers %d (actief %d); Games %d;  Leader: %s",
				scoreController.getCountRounds(), playerController
						.getCountPlayers(), playerController
						.getCountActivePlayers(), gameController.getGames()
						.size(), scoreController.getLeader());
		status = new JLabel(statustxt);
		status.setForeground(Color.WHITE);
		wrapper.add(status, BorderLayout.SOUTH);

		getContentPane().add(wrapper);
	}

	private void playGame() {
		JLabel lablab = new JLabel("Play Game!");
		lablab.setForeground(Color.WHITE);
		contentPanel.add(lablab);
	}

	private void viewPlayers() {
		JLabel lablab = new JLabel("View Players!");
		lablab.setForeground(Color.WHITE);
		contentPanel.add(lablab);
	}

	private void viewScore() {
		JLabel lablab = new JLabel("View Score!");
		lablab.setForeground(Color.WHITE);
		contentPanel.add(lablab);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == playBtn) {
			playGame();
		} else if (e.getSource() == viewPlayersBtn) {
			viewPlayers();
		} else {
			viewScore();
		}
	}

	private int exit() {
		return WindowConstants.EXIT_ON_CLOSE;
	}
}
