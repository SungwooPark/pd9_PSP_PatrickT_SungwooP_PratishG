public class Card{
    private String name; //Name of a word
    private String def; //Definition
    private int numWrong; //The number of times a user got this word incorrect

    //Constructor takes name
	public Card(String name) {
		this.name = name;
	}
	
    public Card(String name, String def){
		this.name = name;
		this.def = def;
    }

    public String getName(){
        return name;
    }

    public String getDef(){
        return def;
    }

    public void setDef(String definition){
        def = definition;
    }

    public int getNumWrong(){
        return numWrong;
    }

    public void setNumWrong(int n){
        numWrong = n;
    }
	
	public void gotWrong() {
		numWrong++;
	}
}
