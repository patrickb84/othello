/****************************************
 * OTHELLO
 * A Board Game
 * 
 * Gerald Brady & Patrick Bradshaw
 * January 27, 2018
 * CSIS 2410
 * 
 ****************************************/

package othello;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Insets;

/**
 * OthelloGUI is the interface for the Othello Board Game.
 * @author Patrick Bradshaw & Gerald Brady
 *
 */
public class BoardGUI extends JFrame {
	private static final long serialVersionUID = 3355080580191028657L;
	
	private JPanel contentPane;
	private JLabel lblBlackScore;
	private JLabel lblWhiteScore;
	private JLabel lblMoved;
	private JLabel lblStatus1;
	private JLabel lblStatus2;
	private JLabel lblStatus3;
	
	private Square[][] squares = new Square[8][8];
	private Status currentDiscColor = Status.BLACK;

	/**
	 * Where it all begins.
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardGUI frame = new BoardGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the Game GUI window interface
	 */
	public BoardGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBoard = createBoard();
		contentPane.add(panelBoard);

		JPanel panelTitle = createPanelTitle();
		contentPane.add(panelTitle, BorderLayout.NORTH);
		
		JPanel panelSideBar = createPanelSideBar();
		contentPane.add(panelSideBar, BorderLayout.EAST);
		
		System.out.print(toConsoleString());
		System.out.println(currentDiscColor + "'s turn");
		System.out.println();
	}

	/**
	 * Creates the game's Title Panel.
	 * 
	 * @return
	 */
	private JPanel createPanelTitle() {
		JPanel panelTitle = new JPanel();
		panelTitle.setLayout(new GridLayout(1, 1, 0, 0));
		{
			JLabel lblOthello = new JLabel("Othello");
			lblOthello.setBorder(new EmptyBorder(20, 0, 10, 0));
			lblOthello.setOpaque(true);
			lblOthello.setForeground(Color.WHITE);
			lblOthello.setBackground(Color.BLACK);
			lblOthello.setFont(new Font("ChunkFive", Font.BOLD, 54));
			lblOthello.setHorizontalAlignment(SwingConstants.CENTER);
			panelTitle.add(lblOthello);
		}
		return panelTitle;
	}

	/**
	 * Creates the play status GUI as a sidebar.
	 * 
	 * @return
	 */
	private JPanel createPanelSideBar() {
		JPanel panelSideBar = new JPanel();
		panelSideBar.setBackground(Color.GRAY);
		panelSideBar.setBorder(new EmptyBorder(7, 17, 7, 17));
		panelSideBar.setLayout(new GridLayout(9, 1, 0, 0));
		{
			JLabel lblBlackDiscs = new JLabel("Black discs");
			lblBlackDiscs.setFont(new Font("Futura", Font.PLAIN, 16));
			panelSideBar.add(lblBlackDiscs);
		}
		{
			lblBlackScore = new JLabel("2");
			lblBlackScore.setForeground(Color.WHITE);
			lblBlackScore.setFont(new Font("ChunkFive", Font.PLAIN, 32));
			lblBlackScore.setHorizontalTextPosition(SwingConstants.CENTER);
			lblBlackScore.setHorizontalAlignment(SwingConstants.CENTER);
			panelSideBar.add(lblBlackScore);
		}
		{
			JLabel lblWhiteDiscs = new JLabel("White discs");
			lblWhiteDiscs.setFont(new Font("Futura", Font.PLAIN, 16));
			panelSideBar.add(lblWhiteDiscs);
		}
		{
			lblWhiteScore = new JLabel("2");
			lblWhiteScore.setForeground(Color.WHITE);
			lblWhiteScore.setHorizontalTextPosition(SwingConstants.CENTER);
			lblWhiteScore.setHorizontalAlignment(SwingConstants.CENTER);
			lblWhiteScore.setFont(new Font("ChunkFive", Font.PLAIN, 32));
			panelSideBar.add(lblWhiteScore);
		}
		{
			JLabel lblGame = new JLabel("-= GAME =-");
			lblGame.setHorizontalAlignment(SwingConstants.CENTER);
			lblGame.setFont(new Font("Futura", Font.BOLD, 14));
			lblGame.setVerticalAlignment(SwingConstants.BOTTOM);
			lblGame.setForeground(Color.YELLOW);
			panelSideBar.add(lblGame);
		}
		{
			lblMoved = new JLabel();
			lblMoved.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblMoved.setHorizontalAlignment(SwingConstants.CENTER);
			lblMoved.setForeground(Color.WHITE);
			panelSideBar.add(lblMoved);
		}
		{
			lblStatus1 = new JLabel();
			lblStatus1.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblStatus1.setHorizontalAlignment(SwingConstants.CENTER);
			lblStatus1.setForeground(Color.WHITE);
			panelSideBar.add(lblStatus1);
		}
		{
			lblStatus2 = new JLabel();
			lblStatus2.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblStatus2.setHorizontalAlignment(SwingConstants.CENTER);
			lblStatus2.setForeground(Color.WHITE);
			panelSideBar.add(lblStatus2);
		}
		{
			lblStatus3 = new JLabel();
			lblStatus3.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblStatus3.setForeground(Color.WHITE);
			lblStatus3.setHorizontalAlignment(SwingConstants.CENTER);
			panelSideBar.add(lblStatus3);
		}
		return panelSideBar;
	}

	/**
	 * Creates the Game Board play grid.
	 * 
	 * @return
	 */
	private JPanel createBoard() {
		JPanel boardGUI = new JPanel();
		boardGUI.setBackground(Color.GRAY);
		boardGUI.setSize(new Dimension(200, 200));
		boardGUI.setPreferredSize(new Dimension(200, 200));
		boardGUI.setMinimumSize(new Dimension(200, 200));
		boardGUI.setBounds(new Rectangle(0, 0, 200, 200));
		boardGUI.setLayout(new GridLayout(0, 8, 0, 0));
		
		EventHandler eventHandler = new EventHandler();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				squares[i][j] = new Square(i, j, Status.OPEN);
				if ((i == 3 && j == 3) || (i == 4 && j == 4)) {
					squares[i][j] = new Square(i, j, Status.WHITE);
				}
				if ((i == 4 && j == 3) || (i == 3 && j == 4)) {
					squares[i][j] = new Square(i, j, Status.BLACK);
				}
				squares[i][j].setPreferredSize(new Dimension(50, 50)); 
				squares[i][j].setOpaque(true);
				squares[i][j].setMinimumSize(new Dimension(50, 50)); 
				squares[i][j].setMaximumSize(new Dimension(50, 50));
				squares[i][j].setMargin(new Insets(0, 0, 0, 0)); 
				squares[i][j].setBorder(new LineBorder(new Color(0, 0, 0)));
				squares[i][j].setBackground(new Color(51, 204, 102));
				
				squares[i][j].addActionListener(eventHandler);
				squares[i][j].setIcon(squares[i][j].getStatus()
						.getStatusImage());
				boardGUI.add(squares[i][j]);
			}
		}
		findLegalSquaresInEveryDirection(currentDiscColor);
		return boardGUI;
	}
	
	/**
	 * Determines the possible legal moves for current player.
	 * 
	 * @param currentDiscColor
	 * @param rowStep
	 * @param colStep
	 */
	private void findLegalSquares(Status currentDiscColor, int rowStep, 
			int colStep) {
		int rowX;
		int colX;
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				/* Find current players discs, move in a direction */
				if (squares[i][j].getStatus() == currentDiscColor) {
					rowX = i + rowStep;
			  		colX = j + colStep;
			  		
			  		/* Stay within board boundaries */
			  		if (rowX < 0 || rowX > 7 || colX < 0 || colX > 7) {
			  			continue;
			  		}
			  		/* If the next square is opposite, keep moving */
			  		while (squares[rowX][colX].getStatus()
			  				== oppositeDisc(currentDiscColor)) {
			  			rowX = rowX + rowStep; 
			  			colX = colX + colStep;
			  			/* Stay in bounds */
			  			if (rowX < 0 || rowX > 7 || colX < 0 || colX > 7) {
				  			break;
				  		}
			  			/* Then if the next square is open, it is legal */
			  			if (squares[rowX][colX].getStatus() == Status.OPEN) {
				  			squares[rowX][colX].updateStatus(Status.LEGAL);
				  			repaint();
				  		}
					}
			  	}
			}
		}
	}
	
	/**
	 * Passes 8 directions for findLegalSquares() method.
	 * 
	 * @param discColor
	 */
	private void findLegalSquaresInEveryDirection(Status discColor) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				findLegalSquares(discColor, i, j);
			}
		}
		updateGUI();
		repaint();
	}
	
	/**
	 * Exchanges players turn, if player has no legal moves, current player 
	 * is skipped.
	 * 
	 * @param legalMovesPresent
	 * @param whoseTurn
	 * @return true if change of players will happen, false if current player 
	 * is skipped.
	 */
	public boolean changeTurn(boolean legalMovesPresent, Status whoseTurn) {
		if (legalMovesPresent) {
			currentDiscColor = oppositeDisc(currentDiscColor);
			return true;
		}
		else return false;
	}
	
	private class EventHandler implements ActionListener {

		/**
		 * Places a disc when the square clicked is a legal square. Then
		 * performs some clean up, flips discs, changes images, updates
		 * text, and always checks if the game has been won. If square
		 * was invalid, it notifies the player.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			Square square = (Square) e.getSource();
			lblStatus3.setText("");
			if (square.getStatus() == Status.LEGAL) {
				removeLegalMarkers();
				square.updateStatus(currentDiscColor);
				flipDiscs(square);
				findLegalSquaresInEveryDirection(oppositeDisc(currentDiscColor));
				updateAllStrings(square);
				endOfTurn();
				if (checkWin()) {
					setLabelsWinner();
				}
			}
			else {
				invalidSquare();
			}
		}

		/**
		 * Changes status labels when there is a winner.
		 */
		private void setLabelsWinner() {
			Status winner = 
					(countSquares(Status.WHITE) > countSquares(Status.BLACK))
							? Status.WHITE : Status.BLACK;
			String winText = winner + " WON!!!";
			lblMoved.setText(winText);
			lblStatus1.setText(winText);
			lblStatus2.setText(winText);
			lblStatus3.setText(winText);
		}

		/**
		 * Notifies the user when they clicked on an invalid square.
		 */
		private void invalidSquare() {
			System.out.println("Not a valid square.\n");
			lblStatus3.setText("NOT VALID");
		}
	}
	
	/**
	 * Changes status text according to whether how the turn changed over.
	 */
	public void endOfTurn() {
		if (changeTurn(countSquares(Status.LEGAL) > 0, currentDiscColor)) {
			System.out.println(currentDiscColor + "'s turn.\n");
			lblStatus2.setText(currentDiscColor + " to move");
		} else {
			System.out.println("No legal moves. " + currentDiscColor
					+ "'s move again\n");
			lblStatus2.setText("No legal moves!");
			lblStatus3.setText(currentDiscColor + " to move");
		}
	}
	
	/**
	 * Updates status text on GUI and in console.
	 * @param square
	 */
	private void updateAllStrings(Square square) {
		System.out.print(toConsoleString());
		
		System.out.println(currentDiscColor + " moved to [" 
				+ square.getRow() + "," + square.getCol() + "]\n");
		
		lblMoved.setText(currentDiscColor + " moved");
		lblStatus1.setText( ("Down " + (square.getRow() + 1) + " Right " 
					+ (square.getCol() + 1)));
		
		System.out.println("B: " + countSquares(Status.BLACK) + " / W: " 
					+ countSquares(Status.WHITE));
		
		lblBlackScore.setText("" + countSquares(Status.BLACK));
		lblWhiteScore.setText("" + countSquares(Status.WHITE));
	}

	/**
	 * Runs method othelloFlip() in every direction to flip discs.
	 * @param square
	 */
	public void flipDiscs(Square square) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				othelloFlip(square.getRow(), square.getCol(), 
						square.getStatus(), i, j);
			}
		}
		updateGUI();
		repaint();
	}
	
	/**
	 * Cleans up legal markers so that new ones can be placed.
	 */
	public void removeLegalMarkers() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (squares[i][j].getStatus() == Status.LEGAL) {
					squares[i][j].updateStatus(Status.OPEN);
					squares[i][j].setIcon(Status.OPEN.getStatusImage());
				}
			}
		}
	}
	
	/**
	 * Updates the game board with the current status of everything.
	 */
	public void updateGUI() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				squares[i][j].updateStatus(squares[i][j].getStatus());
				squares[i][j].setIcon(squares[i][j].getStatus().getStatusImage());
			}
		}
	}
	
	/**
	 * Counts a status type, black, white, open, or legal, of each square.
	 * @param type
	 * @return
	 */
	public int countSquares(Status type) {
		int typeCount = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (squares[i][j].getStatus() == type)
					typeCount++;
			}
		}
		return typeCount;
	}
	
	/**
	 * Reference to the console for debug and secondary visual checking.
	 * 
	 * @return 
	 */
	public String toConsoleString() {
		String board = "";
		String token = " ";
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				switch (squares[i][j].getStatus()) {
					case OPEN:
						token = " ";
						break;
					case BLACK:
						token = "B";
						break;
					case WHITE:
						token = "W";
						break;
					case LEGAL:
						token = "*";
						break;
					default:
						token = " ";
				}
				board += "[" + token + "]";
			}
			board += String.format("\n");
		}
		return board;
	}

	/**
	 * Switches between the disk options, white or black.
	 * 
	 * @param disc
	 * @return the opposite colored disk
	 */
	public Status oppositeDisc(Status disc) {
		if (disc == Status.BLACK)
			return Status.WHITE;
		else
			return Status.BLACK;
	}
	
	/**
	 * Checks for disks to be flipped is an inputted direction. If such a 
	 * condition is present, disks are flipped.
	 * 
	 * @param rowStart
	 * @param colStart
	 * @param turnColor
	 * @param rowStep
	 * @param colStep
	 */
	public void othelloFlip(int rowStart, int colStart, Status turnColor,
			int rowStep, int colStep) {
		int rowX = rowStart + rowStep;
		int colX = colStart + colStep;

		/* Stay in bounds */
		if (rowX < 0 || rowX > 7 || colX < 0 || colX > 7) {
			return;
		}
		/**
		 *  We need status of square in the direction we are checking
		 *  Keep checking until we hit empty cells
		 */
		while (squares[rowX][colX].getStatus() == Status.BLACK 
				|| squares[rowX][colX].getStatus() == Status.WHITE) {
			/**
			 *  Return direction to flip chips
			 *  Run else statement till we hit a cell with the same color
			 */
			if (squares[rowX][colX].getStatus() == turnColor) {
				while(!(rowStart == rowX && colStart == colX)) {
					squares[rowX][colX].updateStatus(turnColor);
					rowX = rowX - rowStep;
					colX = colX - colStep;
				}
				break;
			}
			/* Moving to next cell in direction to check for chip color change */
			else {
				rowX = rowX + rowStep;
				colX = colX + colStep;    
			}
			
			/* Check to keep us on the board, break when we are off board */
			if (rowX < 0 || rowX > 7 || colX < 0 || colX > 7) {
				break;
			}
		}
	}
	
	/**
	 * Searches for the status of squares. If neither player has legal moves
	 * available, the game is over. If there are no open squares, the game is
	 * over.
	 * @return
	 */
	public boolean checkWin() {
		if (countSquares(Status.OPEN) == 0 && countSquares(Status.LEGAL) == 0) {
			return true;
		} else {
			findLegalSquaresInEveryDirection(currentDiscColor);
			if (countSquares(Status.LEGAL) == 0) {
				findLegalSquaresInEveryDirection(oppositeDisc(currentDiscColor));
				if (countSquares(Status.LEGAL) == 0)
					return true;
			}
		}
		return false;
	}
}