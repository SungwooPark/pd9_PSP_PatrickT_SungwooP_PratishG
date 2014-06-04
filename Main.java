import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import java.nio.file.*;
import java.io.*;
import java.nio.charset.*;
import java.util.*;

public class Main extends JPanel {

	private ArrayList<Card> deck;
	private GUISetting guiSetting;

	private JTextField word;
	private JTextArea definition1;
	private JTextArea definition2;
	private JTextArea definition3;
	private JTextArea definition4;

	public Main() {
        deck = new ArrayList<Card>();
        guiSetting = GUISetting.MAIN;
        setPreferredSize(new Dimension(600, 400));
		initializeGUI();
	}

	public void initializeGUI() {
		word = new JTextField("testing");
		word.setEditable(false);
		word.setHorizontalAlignment(JTextField.CENTER);
		Font font = new Font("Courier", Font.BOLD, 42);
		word.setFont(font);

		add(word, BorderLayout.CENTER);

		JPanel chooseDefinition = new JPanel(new BorderLayout(2,2));
		definition1 = new JTextArea("definition1 testing");
		definition1.setPreferredSize(new Dimension(300,150));
		chooseDefinition.add(definition1, BorderLayout.WEST);

		//add(chooseDefinition, BorderLayout.SOUTH);
	}
	
	public void typeDefinitionVersion() {
		
	}
	
	public void multipleChoiceVersion() {
		
	}

	public void loadWords() {

	}

   	public static void main(String[] args){

            JFrame frame = new JFrame("Flashcards");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setPreferredSize(new Dimension(600, 400));
			//frame.setSize(600, 400);

            Main m = new Main();
            frame.getContentPane().add(m);
			frame.pack();
            

            //Calls main screen method
            while (true){
                //m.repaint();
                //m.mainScreen();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {}
            }
        }
	

        //Reads the txt file and add the words to the arraylist deck
        //txt file is formatted such that each line represent one word
        //each line has a term and definition separated by comma
        public void populateDeck(){
            Charset charset = Charset.forName("US-ASCII");
            Path path = FileSystems.getDefault().getPath("words.txt");
            try (BufferedReader reader = Files.newBufferedReader(path,charset)){
                String line = null;
                while ((line = reader.readLine()) != null){
                    String[] wordSet = new String[2]; //Term and defintion as an arraylist
                    wordSet = line.split(",");
                    add(wordSet[0],wordSet[1]);
                }
            }catch (IOException x){
                System.out.println("IO Exception");
            } 
        
        }

        //When user presses "Save button", the program writes the words in the arrayList into txt file.
        //Txt file is overwrited.
        public void saveWords(){
            Path path = FileSystems.getDefault().getPath("words.txt");//Path to the word file
            Charset charset = Charset.forName("US-ASCII"); //Indicate to use ASCII to convert byte to characters
            try(BufferedWriter writer = Files.newBufferedWriter(path,charset)){
                for (Card i:deck){
                    String s = i.getName() + "," + i.getDef() + "\n";
                    writer.write(s,0,s.length());
                }    
            }catch (IOException x){
                System.out.println("IO Exception");
            }
            
        }

        //Just a test function to see whether reading and writing works
        //It will be deleted
        public void test(){
            for (Card i:deck){
                System.out.println(i.getName() + " -------> " + i.getDef());
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

	/*
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
    */

    public enum GUISetting {
    	MAIN, TESTING, ADD, REMOVE, VIEW
    }
}
