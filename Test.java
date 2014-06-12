import java.math.*;
import java.util.*;

public class Test{
	public Card rightWord;
	//private int choice;
	private String[] choices;
	private RQueue<Card> Testable;
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
	private String[] choicesPopulate(){
		Random  r = new Random();
		//Changed var name Choices to choices. We usually make var name lowercase.ok
    	choices = new String[4];
		rightWord = picker();
		/* This while loop is unnecessary.
                 * for (int i = 0; i < 1, i ++){
			Choices[r.nextInt(4)] = rightWord.getDef();
			}*/
                choices[r.nextInt(4)] = rightWord.getDef();
		for (int i = 0; i < 3; i ++){
			if(choices[i] == null){
                                //Don't we have to use dequeue here too? Why peekfront?right. my bad
				//choices[i]=Testable.dequeue().getDef();
				Card tmp = Testable.dequeue();
				choices[i] = tmp.getDef();
				Testable.enqueue(tmp);
			}
			else if(choices[i] != null){
                                //Same here
				Card tmp = Testable.dequeue();
				choices[i + 1] = tmp.getDef();
				Testable.enqueue(tmp);
			}
		}
                //Returns an array with the definition choice for user
        return choices;
	}
	
	//Changed Selected to selected
	
	//It's not an array list, it's an array.
	public boolean Tester(int selected){
                //There is no getIndex method for an arraylist. I think we have to make a instance variable that stores the index of right word and compare it with the
                //argument in this method
                //it's an array, lol.
        
        for (int i = 0; i <choices.length; i ++){
        	if(choices[i] == rightWord.getDef()){
				if(i == selected){
				return true;
				}
				
				else{
					Testable.enqueue(rightWord);
					return false;
					
					}
				}
			}
		return false;}	
}	
			
				
					
		
	

			
		//actual tester
		
	
        //*******What happened with this code? Why are we not using it?
		//Unused section, kept just in case.
	//If user gets it right, remove word from the queue, else add the word.  If no more words left inform user
	/*
	public 	void Tester(int Selected){
		Word = picker();
		Random r = new Random();
		String[] Choices = new String[4];
		for(int i = 0;i<1;i++){
			Choices[r.nextInt(4)] = Word.getDef();
		}
		for(int m = 0; m < Choices.length; m++){
			if (Choices[m] == null){
				Choices[m] = Word.getDef();
			}
		}
		if(Choices[Selected] == Word.getDef()){
			System.out.println("You are Correct");

			
			int counter = 0;
			while(counter < Choices.length){
			for (int counter = 0; counter < Choices.length; counter ++){
				String [] Choices2 = new String[Choices.length - 1];
				if(counter == Selected){
					counter ++;
					}
				else {
					if(counter < Choices2.length){
						Choices2[counter] = Choices[counter];
					}
					else if(counter >= Choices2.length){
						for(int goThru = 0; goThru < Choices2.length; goThru++){
							if(Choices[goThru] = null){
								Choices2[goThru] =
						
		else{
			System.out.println("Sorry, Wrong Answer, Right Answer is: " + rightWord.getDef());
		}
		}
	*/
//}	

