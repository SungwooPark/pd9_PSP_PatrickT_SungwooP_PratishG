public class Test{
	private RQueue<Card> Testable;
	public Test(ArrayList<Card> Words){
	//Random Queue with words from ArrayList.
		Testable = new RQueue();
		for(Card x: Words){
		
			Testable.enqueue(x);
			if(x.getNumWrong > 0){
				int numElem = x.getNumWrong;
				while (numElem > 0){
					Testable.enqueue(x);
					numElem --;
					}	
				}
			}
		}
		//this selects the word from the random queue
	private Card picker(){
		Card returner = Testable.peekFront();
		return returner;
		}
		
		//actual tester
	//If user gets it right, remove word from the queue, else add the word.  If no more words left inform user
	public 	void Tester(){
		Card rightWord = picker();
		Array[] wrongChoices = 

	}
	//unsure how exactly the program would work, how would it check if it user's response is right or wrong	
