import java.math.*;
import java.util.*;

public class Test{
	private Card rightWord;
	//private int choice;
	private RQueue<Card> Testable;
	private int rightIndex; //Index of position for right index
        private ArrayList<Card> choiceWords; //temporary storage of words that are in the choices given to the user.
        //This temporary storage is necessary to make sure that we do not have duplicate answer choice for the reader.

        //Main function created to check whether this class works. Will be deleted
        /* 
        public static void main(String[] args){
            Card newCard = new Card("hello","world");
            Card newCard2 = new Card("apple","fruit");
            Card newCard3 = new Card("banana","another fruit");
            Card newCard4 = new Card("kiwi","delicious fruit");
            Card newCard5 = new Card("orange","good fruit");

            ArrayList<Card> testDeck = new ArrayList<Card>();
            testDeck.add(newCard);
            testDeck.add(newCard2);
            testDeck.add(newCard3);
            testDeck.add(newCard4);
            testDeck.add(newCard5);

            Test testClass = new Test(testDeck);
            //testClass.checkTestable();
            String[] userChoices = testClass.choicesPopulate();
            System.out.println("Right word: " + testClass.getRightWord().getName());
            for (String s:userChoices){
                System.out.println(s);
            }
            System.out.println("Right Index: " + testClass.getRightIndex());
            testClass.update(false);
            testClass.checkTestable();
        }
    
        //Temporary test functions
        public void checkTestable(){
            while (!Testable.isEmpty()){
                System.out.println(Testable.dequeue().getName());
            }
        }*/
        
        public Card getRightWord(){
            return rightWord;
        }
        public int getRightIndex(){
            return rightIndex;
        }

        public String getRightDefinition(){
            return rightWord.getDef();
        }
        
        public Test(ArrayList<Card> Words){

	//Random Queue with words from ArrayList.
		Testable = new RQueue();
		for(Card x: Words){
		//initial adding.  make it two?
			Testable.enqueue(x);
			Testable.enqueue(x);
			if(x.getNumWrong() > 0){
				int numElem = x.getNumWrong();
				while (numElem > 0){
					Testable.enqueue(x);
					numElem --;
					}	
				}
			}
		}
		//this selects the word from the random queue
                //Shouldn't this be dequeue? This does not shuffle the words.
	private Card picker(){
		//Card retWord = Testable.peekFront();
		Card retWord = Testable.dequeue();  //when it populates it.
		//Testable.enqueue(retWord)
		return retWord;
		}
		
		
		
	//populate an array with wrong, and right words.	
	public String[] choicesPopulate(){
                Random  r = new Random();
		//Changed var name Choices to choices. We usually make var name lowercase.
                String[] choices = new String[4];
                rightWord = picker();
                choiceWords = new ArrayList<Card>();
                choiceWords.add(rightWord);
                //rightIndex = 1;//For now
                rightIndex = r.nextInt(4);
                choices[rightIndex] = rightWord.getDef();
		for (int i = 0; i <= 3; i ++){
			if(choices[i] == null){
                                while (choiceWords.contains(Testable.peekFront())){
                                    Testable.sample(); //If a word that is about to be inserted is already in the choices, choose another one.
                                }
                            Card choiceToAdd = Testable.dequeue();
                            choiceWords.add(choiceToAdd);
			    choices[i] = choiceToAdd.getDef();
		    
                        }

                }
                    //Return arraylist with the definition choice for user
                return choices;
	    }
        
        public void update(boolean userAnswer){
            if (userAnswer){
                for (Card i: choiceWords){
                    System.out.println("i is " + i.getName());
                    System.out.println(i.getName() + "," + rightWord.getName());
                    if (!i.getName().equals(rightWord.getName())){
                        Testable.enqueue(i);
                        System.out.println("enqueing " + i.getName());
                    }
                }
            }else{
                    Testable.enqueue(rightWord);
                    for (Card i:choiceWords){
                        Testable.enqueue(i);
                    }
                }
            }
			
		public String toString() {
			return Testable.toString();
		}

        /*
         * Instead of this tester function. We will use update function to update random queue.
         * Checking whether user got a word correct will be done in main class.
        public boolean Tester(int selected){
            if (selected == rightIndex){
                for (Card i: choiceWords){
                    System.out.println("i is " + i.getName());
                    System.out.println(i.getName() +","+ rightWord.getName());
                    if (!i.getName().equals(rightWord.getName())){
                        Testable.enqueue(i);
                        System.out.println("enqueueing " + i.getName()); 
                    }
                }
                return true;
            }else{
                Testable.enqueue(rightWord); //Insert another rightWord card to the random queue;
                for (Card i:choiceWords){
                    Testable.enqueue(i);
                }
                return false;
            }
        }
        */

}	
