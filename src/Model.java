import java.util.Scanner;

/**
 * CS 151 Group Assignment Fall 2024 code
 * @author Isaac Chan, Vincent Do, Kunal Pradhan 
 * @version 1.0 11/15/2024
 */

import java.util.Scanner;

public class Model {

	// Represent the number of stones in respective player's mancala
	private int a_score, b_score;

	/*
	 * int array representing the number of stones in each pit (not mancalas)
	 * indexes 0 - 5 represent player A side pits
	 * indexes 6 - 11 represent player B side pits
	 */
	private int[] board;

	// Represent the state of the game (title screen, in game, etc)
	private int game_state;

	// The number of stones in each pit at start of game
	private int starting_stones_per_pit;

	// Boolean representing whether it is Player A's turn or not
	private boolean is_a_turn;

	// Whether a player can make a move
	private boolean can_make_move;

	// The number of undos this turn
	private int num_undos;

	// The pit index that was moved (should be -1 if it player has not made a move yet)
	private int previous_pit_index;

	// The amount of stone in the previous pit index before the move
	private int previous_pit_stone_count;

	// Non-zero only if move made drops last stone into pit on the side of the player
	// Tracks the number of stone picked up from the opposite side
	// Used to keep track of stones picked up for undo functionality
	private int previous_super_move_count;

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
		Model m = new Model();
		m.setup_game();
		Scanner s = new Scanner(System.in);

		while (m.check_win() == 0) {
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
				String choice = s.next().toLowerCase();

				while (!choice.equals("y") && !choice.equals("n"))
					choice = s.next().toLowerCase();

				if (choice.equals("n") && m.getNumUndos() < 3) {
					m.undo_move();
				} else {
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

		s.close();
	}

	/**
	 * Returns the which player has won the game
	 *
	 * @return 0 - no wins, positive - player A wins, negative - player B wins
	 *
	 * NOTE possible bug is that if there is a tie, it is indistinguishable from a game that has not ended
	 */
	public int check_win() {
		if (this.a_score + this.b_score == 12 * this.starting_stones_per_pit) {
			return this.a_score - this.b_score;
		}
		return 0;
	}

	/**
	 * Setup/reset the game to initial state
	 */
	public void setup_game() {
		this.a_score = 0;
		this.b_score = 0;
		this.is_a_turn = true;

		// Setup the game with correct number of stones in each pit
		for (int i = 0; i < this.board.length; i++) {
			this.board[i] = this.starting_stones_per_pit;
		}
	}

	/**
	 * Make a move if a move has not already been made this turn by current player
	 *
	 * @param pit_index the index of the pit to pick up the stones from
	 */
	public void make_move(int pit_index) {
		this.previous_pit_index = pit_index;
		this.previous_pit_stone_count = this.board[pit_index];
		this.board[pit_index] = 0;

		int temp_stone_count = this.previous_pit_stone_count;
		if (this.is_a_turn && pit_index == 5 && temp_stone_count > 0) {
			temp_stone_count--;
			this.a_score++;
		} else if (!this.is_a_turn && pit_index == 11 && temp_stone_count > 0) {
			temp_stone_count--;
			this.b_score++;
		}

		int i = (pit_index + 1) % 12;
		while (temp_stone_count > 0) {
			this.board[i]++;
			temp_stone_count--;
			if (temp_stone_count == 0 && this.board[i] == 1) {
				if (this.is_a_turn && i >= 0 && i <= 6) {
					this.previous_super_move_count = this.board[11 - i];
					this.a_score += this.previous_super_move_count;
					this.board[11 - i] = 0;
				} else if (!this.is_a_turn && i >= 6 && i <= 11) {
					this.previous_super_move_count = this.board[11 - i];
					this.b_score += this.previous_super_move_count;
					this.board[11 - i] = 0;
				}
			}

			if (i == 5 && this.is_a_turn && temp_stone_count > 0) {
				this.a_score++;
				temp_stone_count--;
			} else if (i == 11 && !this.is_a_turn && temp_stone_count > 0) {
				this.b_score++;
				temp_stone_count--;
			}

			i = (i + 1) % 12;
		}

		this.can_make_move = false;
	}

	/**
	 * Undo the previous move if current player has made less than 3 undos and has already made a move
	 */
	public void undo_move() {
		if (this.num_undos >= 3 || this.can_make_move)
			return;

		this.board[this.previous_pit_index] = this.previous_pit_stone_count;

		int temp_stone_count = this.previous_pit_stone_count;

		if (this.is_a_turn && this.previous_pit_index == 5 && temp_stone_count > 0) {
			temp_stone_count--;
			this.a_score--;
		} else if (!this.is_a_turn && this.previous_pit_index == 11 && temp_stone_count > 0) {
			temp_stone_count--;
			this.b_score--;
		}

		int i = (this.previous_pit_index + 1) % 12;
		while (temp_stone_count > 0) {
			this.board[i]--;
			temp_stone_count--;
			if (temp_stone_count == 0 && this.board[i] == 0) {
				if (this.is_a_turn && i >= 0 && i <= 6) {
					this.a_score -= this.previous_super_move_count;
					this.board[11 - i] = this.previous_super_move_count;
					this.previous_super_move_count = 0;
				} else if (!this.is_a_turn && i >= 6 && i <= 11) {
					this.b_score -= this.previous_super_move_count;
					this.board[11 - i] = this.previous_super_move_count;
					this.previous_super_move_count = 0;
				}
			}

			if (i == 5 && this.is_a_turn && temp_stone_count > 0) {
				this.a_score--;
				temp_stone_count--;
			} else if (i == 11 && !this.is_a_turn && temp_stone_count > 0) {
				this.b_score--;
				temp_stone_count--;
			}

			i = (i + 1) % 12;
		}

		this.num_undos++;
		this.previous_pit_index = -1;
		this.can_make_move = true;
	}

	/**
	 * Pass the turn to the next player if the current player has made a move
	 */
	public void pass_turn() {
		if (this.can_make_move)
			return;

		this.can_make_move = true;
		this.is_a_turn = !this.is_a_turn;
		this.previous_pit_stone_count = 0;
		this.previous_pit_index = -1;
		this.num_undos = 0;
	}

	public boolean getisATurn() {
		return this.is_a_turn;
	}

	public int getNumUndos() {
		return this.num_undos;
	}

	// Getter for board
	public int[] getBoard() {
		return this.board;
	}

	// Getter for a_score
	public int getAScore() {
		return this.a_score;
	}

	// Getter for b_score
	public int getBScore() {
		return this.b_score;
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
}