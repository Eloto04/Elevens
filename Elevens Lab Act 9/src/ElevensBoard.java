import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ElevensBoard extends Board {

	/**
	 * The size (number of cards) on the board.
	 */
	private static final int BOARD_SIZE = 9;

	/**
	 * The ranks of the cards for this game to be sent to the deck.
	 */
	private static final String[] RANKS =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

	/**
	 * The suits of the cards for this game to be sent to the deck.
	 */
	private static final String[] SUITS =
		{"spades", "hearts", "diamonds", "clubs"};

	/**
	 * The values of the cards for this game to be sent to the deck.
	 */
	private static final int[] POINT_VALUES =
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};

	/**
	 * Flag used to control debugging print statements.
	 */
	private static final boolean I_AM_DEBUGGING = false;


	/**
	 * Creates a new <code>ElevensBoard</code> instance.
	 */
	 public ElevensBoard() {
	 	super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
	 }

	/**
	 * Determines if the selected cards form a valid group for removal.
	 * In Elevens, the legal groups are (1) a pair of non-face cards
	 * whose values add to 11, and (2) a group of three cards consisting of
	 * a jack, a queen, and a king in some order.
	 * @param selectedCards the list of the indices of the selected cards.
	 * @return true if the selected cards form a valid group for removal;
	 *         false otherwise.
	 */
	public boolean isLegal(List<Integer> selectedCards) {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */
		
		return containsJQK(selectedCards) || containsPairSum11(selectedCards);
		
		//return false;
	}

	/**
	 * Determine if there are any legal plays left on the board.
	 * In Elevens, there is a legal play if the board contains
	 * (1) a pair of non-face cards whose values add to 11, or (2) a group
	 * of three cards consisting of a jack, a queen, and a king in some order.
	 * @return true if there is a legal play left on the board;
	 *         false otherwise.
	 */
	public boolean anotherPlayIsPossible() {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */
		
		Card[] c = new Card[9];
		
		boolean hasJack = false;
		boolean hasQueen = false;
		boolean hasKing = false;
		
		if(deckSize()<=9) return true;
		
		for(int i = 0; i < c.length; i++){
			c[i] = cardAt(i);
		}
		
		//need to figure out a way to continue this process when 
		//there are less than 9 cards left
		
		for(int i = 0; i < c.length-1; i++) {
			for(int j = i+1; j < c.length; j++) {
				if(c[i].pointValue()+c[j].pointValue()==11) {
					return true;
				}
			}
		}
		
		for(int i = 0; i < c.length; i++) {
			if(c[i].rank().contentEquals("jack")) {
				hasJack = true;
			}
			if(c[i].rank().contentEquals("queen")) {
				hasQueen = true;
			}
			if(c[i].rank().contentEquals("king")) {
				hasKing = true;
			}
		}
		
		return hasJack && hasQueen && hasKing;
	}

	/**
	 * Check for an 11-pair in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find an 11-pair.
	 * @return true if the board entries in selectedCards
	 *              contain an 11-pair; false otherwise.
	 */
	private boolean containsPairSum11(List<Integer> selectedCards) {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */
		//the list contains INDEXES not card values
		//must use helper method to grab the cards
		// use cardAt
		// a nested loop that checks ALL pairs
		
		if(selectedCards.size()!=2) return false;
		
		Card[] c = new Card[9];
		
		for(int i = 0; i < c.length; i++){
			c[i] = cardAt(i);
		}
		
		Card[] sc = new Card[2];
		
		sc[0] = c[selectedCards.get(0)];
		sc[1] = c[selectedCards.get(1)];
		
		if(selectedCards.size()==2){
			if(sc[0].pointValue()+
					sc[1].pointValue()==11){
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Check for a JQK in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find a JQK group.
	 * @return true if the board entries in selectedCards
	 *              include a jack, a queen, and a king; false otherwise.
	 */
	private boolean containsJQK(List<Integer> selectedCards) {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */
		//check to see if you have a jack, queen AND king!
		//parameter is the indexes of the cards selected
		//looks on the board for 3 cards
		if(selectedCards.size()!=3) return false;
		
		boolean hasJack = false;
		boolean hasQueen = false;
		boolean hasKing = false;
		
		Card[] c = new Card[9];
		
		for(int i = 0; i < c.length; i++){
			c[i] = cardAt(i);
		}
		
		Card[] sc = new Card[3];
		
		sc[0] = c[selectedCards.get(0)];
		sc[1] = c[selectedCards.get(1)];
		sc[2] = c[selectedCards.get(2)];
	
		//so far it responds to any triplet of face cards
		//make an arraylist containing possible facecards
		//for example if one of the three cards is a jack, jack gets
		//removed from the arraylist
		
		for(int i = 0; i < 3; i++) {
			if(sc[i].rank().equals("jack")) {
				hasJack = true;
			}
			if(sc[i].rank().equals("queen")) {
				hasQueen = true;
			}
			if(sc[i].rank().equals("king")) {
				hasKing = true;
			}
		}
		
		return hasJack && hasQueen && hasKing;
		
}
	
}
