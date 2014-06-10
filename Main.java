import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
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

	private JTextPane word;
	private JTextPane definition1;
	private JTextPane definition2;
	private JTextPane definition3;
	private JTextPane definition4;

	private long currentTime;

	public Main() {
        deck = new ArrayList<Card>();
        guiSetting = GUISetting.MAIN;
        setFocusable(true);
        addKeyListener(new escapeListener());
        setPreferredSize(new Dimension(600, 400));
		setLayout(new BorderLayout());
		graphicalMainScreen();
	}
	
	public enum GUISetting {
    	MAIN, TEST, ADD, REMOVE, VIEW
    }

	//this creates a text field containing the word; places at top middle
	public void graphicalMainScreen() {
		removeAll();
		add(drawMainScreen(), BorderLayout.CENTER);
		revalidate();
	}
	
	public void multipleChoiceTest() {
		removeAll();
		add(currentWord(), BorderLayout.PAGE_START);
		add(choiceFields(), BorderLayout.CENTER);
		setFocusable(true);
		addKeyListener(new escapeListener());
		revalidate();
		currentTime = System.currentTimeMillis();
	}
	
	public void viewWordsScreen() {
		removeAll();
		add(viewWordList(), BorderLayout.PAGE_START);
		setFocusable(true);
		addKeyListener(new escapeListener());
		revalidate();
	}
	
	public void redraw() {
		switch (guiSetting) {
			case MAIN:
				graphicalMainScreen();
				break;
			case TEST:
				multipleChoiceTest();
				break;
			case VIEW:
				viewWordsScreen();
				break;
			case ADD:
				break;
			case REMOVE:
				break;
			default:
				break;
		}
	}
	
	public static void main(String[] args){
        JFrame frame = new JFrame("Flashcards");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(600, 400));


        Main m = new Main();
        frame.getContentPane().add(m);
		frame.pack();
    }

    public JPanel timer() {
    	JPanel panel = new JPanel();
    	JTextPane timerPane = new JTextPane();
    	Font font = new Font("Courier", Font.PLAIN, 30);
    	timerPane.setFont(font);
    	panel.setBackground(Color.GRAY);

    	if (System.currentTimeMillis() - currentTime > 1000){
    		currentTime = System.currentTimeMillis();
    		timerPane.setText((currentTime / 60000) + ":" + (currentTime / 1000));
    		panel.add(timerPane);
    	}
    	else {
			timerPane.setText((currentTime / 60000) + ":" + (currentTime / 1000));
    		panel.add(timerPane);
    	}
    	return panel;
    }
	
	///// heavy duty stuff below
	public JPanel drawMainScreen() {
		JPanel panel = new JPanel(new BorderLayout());
		
		JTextPane textPane = new JTextPane();
		textPane.setText("Flashcards");
		Font font = new Font("Courier", Font.BOLD, 60);
		textPane.setFont(font);
		StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		textPane.setBackground(Color.GRAY);
		textPane.setForeground(Color.BLUE);
		panel.add(textPane, BorderLayout.PAGE_START);
		
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.insets = new Insets(10,0,0,0);
		
		JButton viewWords = new JButton("View words");
		viewWords.addActionListener(new actionListener());
		viewWords.setActionCommand("viewWords");
		viewWords.setPreferredSize(new Dimension(150, 30));
		c.gridy = GridBagConstraints.RELATIVE;
		buttonPanel.add(viewWords, c);
		
		JButton addWords = new JButton("Add words");
		addWords.addActionListener(new actionListener());
		addWords.setActionCommand("addWords");
		addWords.setPreferredSize(new Dimension(150, 30));
		buttonPanel.add(addWords, c);
		
		JButton removeWords = new JButton("Remove words");
		removeWords.addActionListener(new actionListener());
		removeWords.setActionCommand("removeWords");
		removeWords.setPreferredSize(new Dimension(150, 30));
		buttonPanel.add(removeWords, c);
		
		JButton test = new JButton("Test");
		test.addActionListener(new actionListener());
		test.setActionCommand("timeToTest");
		test.setPreferredSize(new Dimension(150, 30));
		buttonPanel.add(test, c);
		
		c.insets = new Insets(30,0,0,0);
		JButton quit = new JButton("Quit");
		quit.addActionListener(new actionListener());
		quit.setActionCommand("quit");
		quit.setPreferredSize(new Dimension(150, 30));
		buttonPanel.add(quit, c);
		
		panel.add(buttonPanel, BorderLayout.CENTER);
		
		return panel;
	}

	public JPanel viewWordList() {
		JPanel panel = new JPanel(new BorderLayout());
		JTextPane textPane = new JTextPane();
		textPane.setText("Word List");
		textPane.setBackground(Color.GRAY);
		StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		Font font = new Font("Courier", Font.BOLD, 42);
		textPane.setFont(font);
		panel.add(textPane, BorderLayout.PAGE_START);

		JPanel wordListPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		////get words somehow////
		JTextPane wordList = new JTextPane();
		JScrollPane wordListScroll = new JScrollPane(wordList);
		wordListScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.gridy = 0;
		wordListScroll.add(wordList);
		wordListPanel.add(wordListScroll, c);

		JTextPane definitionList = new JTextPane();
		JScrollPane defListScroll = new JScrollPane(definitionList);
		defListScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		c.gridx = 1;
		defListScroll.add(definitionList);
		wordListPanel.add(defListScroll, c);

		wordList.setText("testing");
		panel.add(wordListPanel, BorderLayout.CENTER);
		return panel;
	}
	
	public JPanel currentWord() {
		JPanel panel = new JPanel(new BorderLayout());
		word = new JTextPane();
		word.setText("testing");
		word.setEditable(false);
		StyledDocument doc = word.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		Font font = new Font("Courier", Font.BOLD, 42);
		word.setFont(font);
		word.setBackground(new Color(176, 176, 176));
		panel.add(word);

		return panel;
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
		try {
            definition1.setEditorKit(new MyEditorKit());
            SimpleAttributeSet attrs=new SimpleAttributeSet();
            StyleConstants.setAlignment(attrs,StyleConstants.ALIGN_CENTER);
            doc=(StyledDocument)definition1.getDocument();
            doc.insertString(0,"definition1",attrs);
            doc.setParagraphAttributes(0,doc.getLength()-1,attrs,false);
        } catch (Exception e) {}
		
		definition2 = new JTextPane();
		definition2.setText("definition2");
		definition2.addMouseListener(new clickListener());
		definition2.setEditable(false);
		try {
            definition2.setEditorKit(new MyEditorKit());
            SimpleAttributeSet attrs=new SimpleAttributeSet();
            StyleConstants.setAlignment(attrs,StyleConstants.ALIGN_CENTER);
            doc=(StyledDocument)definition2.getDocument();
            doc.insertString(0,"definition2",attrs);
            doc.setParagraphAttributes(0,doc.getLength()-1,attrs,false);
        } catch (Exception e) {}
		
		definition3 = new JTextPane();
		definition3.setText("definition4");
		definition3.addMouseListener(new clickListener());
		definition3.setEditable(false);
		try {
            definition3.setEditorKit(new MyEditorKit());
            SimpleAttributeSet attrs=new SimpleAttributeSet();
            StyleConstants.setAlignment(attrs,StyleConstants.ALIGN_CENTER);
            doc=(StyledDocument)definition3.getDocument();
            doc.insertString(0,"definition3",attrs);
            doc.setParagraphAttributes(0,doc.getLength()-1,attrs,false);
        } catch (Exception e) {}
		
		definition4 = new JTextPane();
		definition4.setText("definition4");
		definition4.addMouseListener(new clickListener());
		definition4.setEditable(false);
		try {
            definition4.setEditorKit(new MyEditorKit());
            SimpleAttributeSet attrs=new SimpleAttributeSet();
            StyleConstants.setAlignment(attrs,StyleConstants.ALIGN_CENTER);
            doc=(StyledDocument)definition4.getDocument();
            doc.insertString(0,"definition4",attrs);
            doc.setParagraphAttributes(0,doc.getLength()-1,attrs,false);
        } catch (Exception e) {}
		
		choiceFields.add(definition1);
		choiceFields.add(definition2);
		choiceFields.add(definition3);
		choiceFields.add(definition4);

		return choiceFields;
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

    class escapeListener extends KeyAdapter {
    	public void keyPressed(KeyEvent e) {
    		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
    			guiSetting = GUISetting.MAIN;
    			redraw();
    		}
    	}
    }
	class clickListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			JTextPane h = (JTextPane)e.getSource();
			h.setText("it works");
		}
	}
	class actionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
				case "viewWords":
					guiSetting = GUISetting.VIEW;
					redraw();
					break;
				case "addWords":
					guiSetting = GUISetting.ADD;
					redraw();
					break;
				case "removeWords":
					guiSetting = GUISetting.REMOVE;
					redraw();
					break;
				case "timeToTest":
					guiSetting = GUISetting.TEST;
					redraw();
					break;
				case "quit":
					System.exit(0);
					break;
				default:
					break;
			}
		}
	}
	
	class MyEditorKit extends StyledEditorKit {

		public ViewFactory getViewFactory() {
			return new StyledViewFactory();
		}
	 
		class StyledViewFactory implements ViewFactory {

			public View create(Element elem) {
				String kind = elem.getName();
				if (kind != null) {
					if (kind.equals(AbstractDocument.ContentElementName)) {

						return new LabelView(elem);
					} else if (kind.equals(AbstractDocument.ParagraphElementName)) {
						return new ParagraphView(elem);
					} else if (kind.equals(AbstractDocument.SectionElementName)) {

						return new CenteredBoxView(elem, View.Y_AXIS);
					} else if (kind.equals(StyleConstants.ComponentElementName)) {
						return new ComponentView(elem);
					} else if (kind.equals(StyleConstants.IconElementName)) {

						return new IconView(elem);
					}
				}
	 
				return new LabelView(elem);
			}

		}
	}
	 
	class CenteredBoxView extends BoxView {
		public CenteredBoxView(Element elem, int axis) {

			super(elem,axis);
		}
		protected void layoutMajorAxis(int targetSpan, int axis, int[] offsets, int[] spans) {

			super.layoutMajorAxis(targetSpan,axis,offsets,spans);
			int textBlockHeight = 0;
			int offset = 0;
	 
			for (int i = 0; i < spans.length; i++) {

				textBlockHeight = spans[i];
			}
			offset = (targetSpan - textBlockHeight) / 2;
			for (int i = 0; i < offsets.length; i++) {
				offsets[i] += offset;
			}

		}
	}    
}
