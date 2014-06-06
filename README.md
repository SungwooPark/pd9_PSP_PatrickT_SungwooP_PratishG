<h2>Flash Card App</h2>
<h6> by Patrick Tsai, Sungwoo Park, and Pratish Gheewala, pd. 9</h6>

<img src="http://i287.photobucket.com/albums/ll128/patmaster/flashcard_zps2c1065a8.png" width="200" alt="Flashcards" align="right">

<h4>What is the Flash Card App?</h4>
This application will:
- Test users on vocabulary words, terms, or anything with definitions that they input
- Place emphasis on terms that the user has gotten incorrect
- Ensure learning of input!

<h4>Behind the Scenes</h4>
- <b>Flash Card App</b> uses an arraylist of card objects. A card object represents a single flash card, and new card is added to the arraylist when user enters a new word.
- A random queue is used to randomly choose a word and test a user.
- When user clicks a testing button, a random queue with words that a user previously inputted is iniatialized.
- When a user gets a word correct, instance of that word is removed from random queue, thus decreasing the frequency of that word being chosen again.
- When a user gets a word incorrectly, opposite happens. Instead of removing a word, another instance of that word is created and added to the random queue. 
