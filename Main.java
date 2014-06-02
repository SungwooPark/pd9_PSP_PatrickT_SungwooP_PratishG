import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Main extends JPanel {

	private ArrayList<Card> deck;
	private GUISetting guiSetting;

	public Main() {
        deck = new ArrayList<Card>();
        initializeGUI();
        guiSetting = GUISetting.MAIN;
	}

	public void initializeGUI() {
		
	}

	public void loadWords() {

	}

   	public static void main(String[] args){
            JFrame frame = new JFrame("Flashcards");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setPreferredSize(new Dimension(500, 300));
            Main m = new Main();
            frame.getContentPane().add(m);

            //Calls main screen method
            while (true){
                m.repaint();
                //m.mainScreen();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {}
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
            System.out.println("Ending program");
            System.exit(0);
        }
    }

    //~~~~~~~~~~~~ Painting class ~~~~~~~~~~~~~//
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	switch (guiSetting) {
    		case MAIN:
    			break;
    		case TESTING:
    			break;
    		case ADD:
    			break;
    		case REMOVE:
    			break;
    		case VIEW:
    			break;
    	}
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    public enum GUISetting {
    	MAIN, TESTING, ADD, REMOVE, VIEW
    }
}
