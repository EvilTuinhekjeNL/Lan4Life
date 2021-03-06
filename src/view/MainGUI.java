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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import model.Player;
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
	private JLabel intro, status, winnerLab;
	private JButton playBtn, viewPlayersBtn, viewScoreBtn;

	public MainGUI() {
		// fileController = new FileController();
		gameController = GameController.getInstance();// new GameController();
		playerController = PlayerController.getInstance();
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

			}
		});
		viewPlayersBtn = new JButton("View Players");
		viewPlayersBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewPlayers();

			}
		});
		viewScoreBtn = new JButton("View Scores");
		viewScoreBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewScore();

			}
		});
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
		littleWrap.remove(contentPanel);
		contentPanel = new JPanel(new FlowLayout());
		JPanel gekkodesprinkhaan = new JPanel(new BorderLayout());
		gekkodesprinkhaan.setBackground(Color.decode("#1c1c1c"));
		contentPanel.setBackground(Color.decode("#1c1c1c"));
		scoreController.newGame();
		String gameTxt = String.format("<html>Ronde %d, het spel dat gespeeld gaat worden is: %s<br><br></html>", scoreController.getCountRounds(), scoreController.getCurrentGame());
		JLabel lablab = new JLabel(gameTxt);
		lablab.setForeground(Color.WHITE);
		gekkodesprinkhaan.add(lablab, BorderLayout.NORTH);
		
		StringBuilder teamA = new StringBuilder();
		teamA.append("<html>Team A:");
		for(Player noob: scoreController.getCurrentTeamA()){
			teamA.append("<br>- "+noob.getName()+";");
		}
		teamA.append("<br><br></html>");
		
		StringBuilder teamB = new StringBuilder();
		teamB.append("<html>Team B:");
		for(Player noob: scoreController.getCurrentTeamB()){
			teamB.append("<br>-\n"+noob.getName()+";");
		}
		teamB.append("<br><br></html>");
		
		JLabel TeamAList = new JLabel(teamA.toString());
		TeamAList.setForeground(Color.WHITE);
		JLabel TeamBList = new JLabel(teamB.toString());
		TeamBList.setForeground(Color.WHITE);
		winnerLab = new JLabel("Winner is: ");
		winnerLab.setForeground(Color.WHITE);
		
		gekkodesprinkhaan.add(TeamAList, BorderLayout.CENTER);
		gekkodesprinkhaan.add(TeamBList, BorderLayout.SOUTH);
		
		JButton teamAbtn = new JButton("Team A");
		teamAbtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				scoreController.setTeamAWin(true);		
				winnerLab.setText("Team A won");
				System.out.println("Team A Won");
				playGame();
			}
		});
		JButton teamBbtn = new JButton("Team B");
		teamBbtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				scoreController.setTeamAWin(false);	
				winnerLab.setText("Team B won");
				System.out.println("Team B Won");
				playGame();
			}
		});
		JPanel buttonPan = new JPanel(new FlowLayout());
		buttonPan.add(teamAbtn);
		buttonPan.add(teamBbtn);
		
		contentPanel.add(gekkodesprinkhaan, BorderLayout.NORTH);
		contentPanel.add(buttonPan, BorderLayout.SOUTH);

		littleWrap.add(contentPanel, BorderLayout.CENTER);
		littleWrap.revalidate();
	}

	private void viewPlayers() {
		littleWrap.remove(contentPanel);
		contentPanel = new JPanel(new FlowLayout());
		contentPanel.setBackground(Color.decode("#1c1c1c"));

		String columnNames[] = { "#", "Naam", "Played", "Won", "K/d" };
		ArrayList<Player> players = playerController.getPlayers();
		ArrayList<String[]> playerList = new ArrayList<String[]>();
		for(Player noob : players){
			int played = playerController.getPlayed(noob.getName());
			int won = playerController.getWon(noob.getName());
			
			ArrayList<String> data = new ArrayList<String>();
			data.add(""+playerController.getRank(noob.getName()));
			data.add(noob.getName());
			data.add(" "+played);
			data.add(""+ won);
			data.add(""+played+"/"+won);
			playerList.add(data.toArray(new String[data.size()]));
		}
		
		String dataValues[][] = playerList.toArray(new String[players.size()][playerList.size()]);
		

		JTable playerTable = new JTable(dataValues, columnNames);
		playerTable.setEnabled(false);
		playerTable.setAutoCreateRowSorter(true);
		JScrollPane scrollPane = new JScrollPane(playerTable);
		scrollPane.setBackground(Color.decode("#1c1c1c"));
		scrollPane.getViewport().setBackground(Color.decode("#1c1c1c"));
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		contentPanel.add(scrollPane);

		littleWrap.add(contentPanel, BorderLayout.CENTER);
		littleWrap.revalidate();

	}

	private void viewScore() {
		littleWrap.remove(contentPanel);
		contentPanel = new JPanel(new FlowLayout());
		contentPanel.setBackground(Color.decode("#1c1c1c"));

		JLabel lablab = new JLabel("View Score!");
		lablab.setForeground(Color.WHITE);
		contentPanel.add(lablab);

		littleWrap.add(contentPanel, BorderLayout.CENTER);
		littleWrap.revalidate();
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
