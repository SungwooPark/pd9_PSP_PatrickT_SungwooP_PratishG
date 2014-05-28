<h2>Flash Card App</h2>
<h6> by Patrick Tsai, Sungwoo Park, and Pratish Gheewala, pd. 9</h6>

<img src="http://i287.photobucket.com/albums/ll128/patmaster/flashcard_zps2c1065a8.png" width="200" alt="Flashcards" align="right">

<h4>What is the Flash Card App?</h4>
This application will:
- Test users on vocabulary words, terms, or anything with definitions that they input
- Place emphasis on terms that the user has gotten incorrect
- Ensure learning of input!

<h4>Behind the Scenes</h4>
- <b>Flash Card App</b> uses an ArrayList of LinkedLists. Each time a word is entered, a new LinkedList is created.
- The LinkedList will contain attributes associated with the word, such as the definition.
- A random LinkedList (word, definition, etc.) from the ArrayList will be chosen when the user clicks a button.
- If the user gets the item correct, great!
- If the user gets it incorrect, a copy of the LinkedList will be added to the ArrayList, increasing that term's frequency in the ArrayList.
