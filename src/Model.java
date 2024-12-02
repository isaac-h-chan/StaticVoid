import java.util.Arrays;
import java.util.Scanner;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * CS 151 Group Assignment Fall 2024 code
 * @author Isaac Chan, Vincent Do, Kunal Pradhan 
 * @version 1.0 11/15/2024
 */

public class Model {
	private turnListener listener;
	private ChangeListener view_listener;
	
	//represents the view of the program
    private View v; 
	// represent the number of stones in respective player's mancala
	private int a_score, b_score;

	/*
	 * int array representing the number of stones in each pit (not mancalas)
	 * indexes 0 - 5 represent player A side pits
	 * indexes 6 - 11 represent player B side pits
	 */
	private int[] board;

	// represent the state of the game (title screen, in game, etc)
	private int game_state;

	// the number of stones in each pit at start of game
	private int starting_stones_per_pit;

	// boolean representing whether it is Player A's turn or not
	private boolean is_a_turn;

	// whether a player can make a move
	private boolean can_make_move;

	// the number of undos this turn
	private int num_undos;

	// the pit index that was moved (should be -1 if it player has not made a move yet)
	private int previous_pit_index;

	// the amount of stone in the previous pit index before the move
	private int previous_pit_stone_count;

	// non zero only if move made drops last stone into pit on the side of the player
	// tracks the number of stone picked up from the opposite side
	// used to keep track of stones picked up for undo functionality
	private int previous_super_move_count;

	/**
	 * Constructor for the Model Class
	 *
	 */
	public Model() {
		a_score = 0;
		b_score = 0;

		is_a_turn = true;
		
		board = new int[12];

		game_state = 0;

		can_make_move = true;

		num_undos = 0;

		starting_stones_per_pit = 4;
		previous_pit_index = -1;
		previous_pit_stone_count = 4;
	}

	public static void main(String[] args) {

		/*
		//Model m = new Model();
		//View v = new View(m);
		//Controller c = new Controller(v, m);

		//m.setup_game();
		Scanner s = new Scanner(System.in);

		while (m.check_win() == 1000) {
			if (m.getisATurn())
				System.out.println("Player A Turn (" + m.getNumUndos() + " undos)");
			else 
				System.out.println("Player B Turn (" + m.getNumUndos() + " undos)");
			System.out.println(m);

			boolean confirmed_move = false;
			while (!confirmed_move) {
				System.out.println("Please enter a index to move (0-11 starting from bottom left and clockwise)");
				int index = s.nextInt();

				while (index > 11 || index < 0)
					index = s.nextInt();

				m.make_move(index);
				if (m.getisATurn())
					System.out.println("Player A Turn (" + m.getNumUndos() + " undos)");
				else 
					System.out.println("Player B Turn (" + m.getNumUndos() + " undos)");
				System.out.println(m);

				System.out.println("Confirm move (Y) or Undo move (N)?");
				String choice = s.nextLine().toLowerCase();

				while (!choice.equals("y") && !choice.equals("n"))
					choice = s.nextLine().toLowerCase();

				if (choice.equals("n") && m.getNumUndos() < 3) {
					m.undo_move();
				}
				else {
					confirmed_move = true;
				}

				if (m.getisATurn())
					System.out.println("Player A Turn (" + m.getNumUndos() + " undos)");
				else 
					System.out.println("Player B Turn (" + m.getNumUndos() + " undos)");
				System.out.println(m);

			}
			
			m.pass_turn();

		}
		System.out.println(m.check_win());

		s.close();
		*/
		
	}


	/**
	 * returns the which player has won the game
	 *
	 * @return 0 - tie, 1000 - no win, positive and less than 1000 - player A wins, negative - player B wins
	 *
	 */
	public int check_win() {
		boolean contains_non_zero_pit = false;
		int i = 0;
		System.out.println(Arrays.toString(board));
		while (!contains_non_zero_pit && i < 6) {
			if (this.board[i++] > 0)
				contains_non_zero_pit = true;
		}

		if (!contains_non_zero_pit) {
			// count stones left on B's side
			int b_stones = 0;
			for (int j = 6; j < 12; j++)
				b_stones += board[j];
			return this.a_score - (this.b_score + b_stones);
		}

		contains_non_zero_pit = false;
		i = 6;

		while (!contains_non_zero_pit && i < 12) {
			if (this.board[i++] > 0)
				contains_non_zero_pit = true;
		}

		if (!contains_non_zero_pit) {
			// count stones left on B's side
			int a_stones = 0;
			for (int j = 0; j < 6; j++)
				a_stones += board[j];
			return this.a_score + a_stones - this.b_score;
		}
		//view_listener.stateChanged(new ChangeEvent(this));
		return 1000;
	}

	public void setListener(turnListener t){
		listener = t;
	}
	
	/**
	 * Attach a ChangeListener for the View
	 *
	 * @param c the ChangeListener to attach
	 */
	public void attach_view_listener(ChangeListener c) {
		this.view_listener = c;
	}


	/**
	 * setup/reset the game to intial state
	 */
	public void setup_game() {
		this.a_score = 0;
		this.b_score = 0;

		this.is_a_turn = true;

		// setup the game with correct number of stones in each pit
		for (int i = 0; i < this.board.length; i++) {
			this.board[i] = this.starting_stones_per_pit;
		}
		view_listener.stateChanged(new ChangeEvent(this));
	}


	/**
	 * make a move if a move has not already been made this turn by current player
	 *
	 * @param pit_index the index of the pit to pick up the stones from
	 */
	public void make_move(int pit_index) {

		this.previous_pit_index = pit_index;
		this.previous_pit_stone_count = this.board[pit_index];
		this.board[pit_index] = 0;

		int temp_stone_count = this.previous_pit_stone_count;
		// start at index after where we pick up the stones from
		
		if (this.is_a_turn && pit_index == 5 && temp_stone_count > 0) {
			temp_stone_count--;
			this.a_score++;
		}
		else if (!this.is_a_turn && pit_index == 11 && temp_stone_count > 0) {
			temp_stone_count--;
			this.b_score++;
		}

		int i = (pit_index + 1) % 12;
		while (temp_stone_count > 0) {
			
			// add a stone to the current pit
			this.board[i]++;
			temp_stone_count--;

			// if the last stone is placed in empty pit
			if (temp_stone_count == 0 && this.board[i] == 1) {

				// check whether the pit is on the side of the current player
				if (this.is_a_turn && i >= 0 && i <= 6) {
					this.previous_super_move_count = this.board[11 - i];
					this.a_score += this.previous_super_move_count;
					this.board[11 - i] = 0;
				}
				else if (!this.is_a_turn && i >= 6 && i <= 11) {
					this.previous_super_move_count = this.board[11 - i];
					this.b_score += this.previous_super_move_count;
					this.board[11 - i] = 0;
				}
			}
			
			// place a stone into appropriate player's mancala if applicable
			if (i == 5 && this.is_a_turn && temp_stone_count > 0) {
				this.a_score++;
				temp_stone_count--;
			}
			else if (i == 11 && !this.is_a_turn && temp_stone_count > 0) {
				this.b_score++;
				temp_stone_count--;
			}

			i = (i + 1) % 12;
		}
		

		this.can_make_move = false;
		view_listener.stateChanged(new ChangeEvent(this));
		listener.pauseButton();
	}
	
	
	/**
	 * undo the previous move if current player has made less than 3 undos and has already made a move
	 */
	public void undo_move() {

		// check if we have not used all undos for this turn
		if (this.num_undos >= 3 || this.can_make_move)
			return;

		this.board[this.previous_pit_index] = this.previous_pit_stone_count;

		int temp_stone_count = this.previous_pit_stone_count;

		if (this.is_a_turn && this.previous_pit_index == 5 && temp_stone_count > 0) {
			temp_stone_count--;
			this.a_score--;
		}
		else if (!this.is_a_turn && this.previous_pit_index == 11 && temp_stone_count > 0) {
			temp_stone_count--;
			this.b_score--;
		}

		// start at index after where we pick up the stones from
		int i = (this.previous_pit_index + 1) % 12;
		while (temp_stone_count > 0) {
			
			this.board[i]--;
			temp_stone_count--;

			// if the last stone is placed in empty pit
			if (temp_stone_count == 0 && this.board[i] == 0) {

				// check whether the pit is on the side of the current player
				if (this.is_a_turn && i >= 0 && i <= 6) {
					this.a_score -= this.previous_super_move_count;
					this.board[11 - i] = this.previous_super_move_count;
					this.previous_super_move_count = 0;
				}
				else if (!this.is_a_turn && i >= 6 && i <= 11) {
					this.b_score -= this.previous_super_move_count;
					this.board[11 - i] = this.previous_super_move_count;
					this.previous_super_move_count = 0;
				}
			}
			
			// place a stone into appropriate player's mancala if applicable
			if (i == 5 && this.is_a_turn && temp_stone_count > 0) {
				this.a_score--;
				temp_stone_count--;
			}
			else if (i == 11 && !this.is_a_turn && temp_stone_count > 0) {
				this.b_score--;
				temp_stone_count--;
			}

			i = (i + 1) % 12;
		}
		
		this.num_undos++;
		this.previous_pit_index = -1;
		this.can_make_move = true;
		view_listener.stateChanged(new ChangeEvent(this));
		listener.turnChanged();
	}

	
	/**
	 * pass the turn to the next player if the current player has made a move
	 */
	public void pass_turn() {
		if (this.can_make_move)
			return;

		this.can_make_move = true;
		this.is_a_turn = !this.is_a_turn;
		this.previous_pit_stone_count = 0;
		this.previous_pit_index = -1;
		this.num_undos = 0;
		view_listener.stateChanged(new ChangeEvent(this));
		listener.turnChanged();
	}

	/**
	 * Getter for is_a_turn
	 *
	 * @return the value of is_a_turn
	 */
	public boolean getisATurn() {
		return this.is_a_turn;
	}

	public String toString() {
		String res = "  ";
		for (int i = 11; i > 5; i--)
			res += this.board[i] + " ";
		res += "\n";
		res += this.b_score + " a b c d e f " + this.a_score + "\n" + "  ";
		for (int i = 0; i < 6; i++)
			res += this.board[i] + " ";
		res += "\n";

		return res;
	}

	/**
	 * returns an external BoardIterator for the board State
	 */
	public BoardIterator iterator() {
		return new BoardIterator() {

			private int i;

			public boolean hasNext() {
				return i < 12;
			}

			public int next() {
				return board[i++];
			}

		};
	}

	/**
	 * get the number of undos the player has made this turn
	 *
	 * @return the number of undos this turn
	 */
	public int getNumUndos() {
		return this.num_undos;
	}


	public boolean getCanMove(){
		return this.can_make_move;
	}

	/**
	 * get the score of player A
	 *
	 * @return the score of A
	 */
	public int getAScore() {
		return this.a_score;
	}

	/**
	 * get the score of player B
	 *
	 * @return the score of B
	 */
	public int getBScore() {
		return this.b_score;
	}

	/**
	 * returns a copy of the board
	 */
	public int[] getBoard() {
		int[] copy = new int[12];
		for (int i = 0; i < 12; i++)
			copy[i] = this.board[i];
		return copy;
	}
	public void setStartingPit(int n){
		this.starting_stones_per_pit = n;
	}

}
