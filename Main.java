import java.util.*;

public class Main {
	private ArrayList<Card> deck;
	
	public Main() {
		deck = new ArrayList<Card>();
	}
	
	public void add(String term, String definition) {
		Card newCard = new Card(term, definition);
	}
	
	public void remove(String term) {
		for (Card i : deck) {
			if (i.getName().equals(term)) {
				deck.remove(i);
				return;
			}
		}
	}
}