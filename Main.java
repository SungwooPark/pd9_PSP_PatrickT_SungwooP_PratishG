import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;
import java.nio.file.*;
import java.io.*;
import java.nio.charset.*;
import java.util.*;

public class Main extends JPanel {

	private ArrayList<Card> deck;
	private GUISetting guiSetting;

	private JTextField word;
	private JTextPane definition1;
	private JTextPane definition2;
	private JTextPane definition3;
	private JTextPane definition4;

	private JTextArea selectedBox;

	public Main() {
        deck = new ArrayList<Card>();
        guiSetting = GUISetting.MAIN;
        setPreferredSize(new Dimension(600, 400));
		initializeGUI();
	}
	
	public enum GUISetting {
    	MAIN, TESTING, ADD, REMOVE, VIEW
    }

	//this creates a text field containing the word; places at top middle
	public void initializeGUI() {
		setLayout(new BorderLayout());
		word = new JTextField("testing");
		word.setEditable(false);
		word.setHorizontalAlignment(JTextField.CENTER);
		Font font = new Font("Courier", Font.BOLD, 42);
		word.setFont(font);

		add(word, BorderLayout.PAGE_START);
		add(choiceFields(), BorderLayout.CENTER);
	}
	
	public void typeDefinitionVersion() {
		
	}
	
	//returns a JPanel containing definition choices
	public JPanel choiceFields() {
		JPanel choiceFields = new JPanel(new GridLayout(2,2, 10, 10));
		//JPanel choiceFields = new JPanel(new GridBagLayout());
		JSeparator horizSeparator = new JSeparator(SwingConstants.HORIZONTAL);
		JSeparator vertSeparator = new JSeparator(SwingConstants.VERTICAL);
		horizSeparator.setMaximumSize( new Dimension(Integer.MAX_VALUE, 1) );
		
		definition1 = new JTextPane();
		definition1.setText("definition1");
		definition1.addMouseListener(new clickListener());
		definition1.setEditable(false);
		StyledDocument doc = definition1.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		definition2 = new JTextPane();
		definition2.setText("definition2");
		definition2.addMouseListener(new clickListener());
		definition2.setEditable(false);
		doc = definition2.getStyledDocument();
		center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		definition3 = new JTextPane();
		definition3.setText("definition4");
		definition3.addMouseListener(new clickListener());
		definition3.setEditable(false);
		doc = definition3.getStyledDocument();
		center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		definition4 = new JTextPane();
		definition4.setText("definition4");
		definition4.addMouseListener(new clickListener());
		definition4.setEditable(false);
		doc = definition4.getStyledDocument();
		center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		choiceFields.add(definition1);
		choiceFields.add(definition2);
		choiceFields.add(definition3);
		choiceFields.add(definition4);

		return choiceFields;
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
            //frame.getContentPane().add(m.choiceFields());
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
    class clickListener extends MouseAdapter {
    	public void mouseClicked(MouseEvent e) {
    		//JTextArea selectedBox = e.getSource();
    		//System.out.println(selectedBox.getText());
    		//System.out.println(e.getSource());
    	}
    }
	*/
	class clickListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			JTextPane h = (JTextPane)e.getSource();
			System.out.println(h.getText());
		}
	}
}
