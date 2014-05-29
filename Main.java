import java.util.*;

public class Main {

	private ArrayList<Card> deck;
	private boolean cont; //while this is true, program continues

	public Main() {
            deck = new ArrayList<Card>();
            cont = true;
	}

        public static void main(String[] args){
            Main m = new Main();

            //Calls main screen method
            while (m.cont()){
                m.mainScreen();
            }

        }
	
	public void add(String term, String definition) {
		Card newCard = new Card(term, definition);
                deck.add(newCard); //Adding new word to the deck
        }
	
	public void remove(String term) {
		for (Card i : deck) {
			if (i.getName().equals(term)) {
				deck.remove(i);
				return;
			}
		}
	}
    
        //Decidesss whether program continues or not
        public boolean cont(){
            return cont;
        }

        public void mainScreen(){
            Scanner scan = new Scanner(System.in);
            System.out.println("Choose what you want to do");
            System.out.print("1. Input new word 2. View inputted words 3. Remove words 4. Test 5. End Program :");
            int command  = scan.nextInt();
            //adding new word
            if (command == 1){
                System.out.print("Input your word: ");
                String word = scan.next();
                System.out.print("Input definition: ");
                String def = scan.next();
                add(word, def);
            }
            //View inputted words
            else if(command == 2){
                for (Card i: deck){
                    System.out.println(i.getName());
                }
            }
            //Removing words
            else if (command == 3){
                System.out.print("Which word do you want to remove? ");
                String word = scan.next();
                remove(word);
            }
            //Test
            else if (command == 4){
                //Call testing function/class
            }
            else if(command == 5){
                cont = false;
                System.out.println("Ending program");
            }
        }
}
