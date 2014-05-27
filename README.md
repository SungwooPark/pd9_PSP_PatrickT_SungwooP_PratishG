<h2>Flash Card App</h2>
<h6> by Patrick Tsai, Sungwoo Park, and Pratish Gheewala, pd. 9</h6>

<img src="http://i287.photobucket.com/albums/ll128/patmaster/flashcard_zps2c1065a8.png" width="200" alt="Flashcards" align="right">

<h4>Overall idea</h4>
This program will emulate the functions of "flash card app" that a user can use to memorize words. 
Using the data structure that we learned in this term, 
the program will store the words and definition that user inputted, 
and randomly choose one of them to test users.

<h4>User Experience</h4> 
Users will be able to add words that they want to study. 
They will type in words and the definitions of the words. 
And then users can use this program to test themselves. 
The program will randomly choose the words based on the number of 
times that user got certain word correct (the words that user frequently 
got wrong will come up more often) and a user has to type the definition of that word.

<h4>Topics Incorporated</h4> 
Array list of linked list will be used as a data structure to 
store a word and its attributes, such as its definition and the number of 
times that a user answered it correctly. A linked list will represent one 
word and first element of it will be the word itself. Second and third element 
would be the definition and the number of times a user typed the definition correctly. 
If we want to add more attributes, we can add more elements to a linked list.

<h4>Anticipated Hurdles</h4>
We are looking to implement GUI and dictionary API if we have time left at the end. 
These are topics that we need to teach ourselves if we want to implement them in our program.

<h3>Mechanism of testing</h3> 
The application will randomly choose an inputted word to test. 
When an user calls a testing function of the program, a random queue of words from the arraylist will be initialized.
If the user gets the word wrong, another object of the word will be added to the random queue
so the frequency of the word is raised, thus increasing the chance that the user will be tested on 
that word again.
If the user gets the word right, the word will be deleted from the queue, thus decreasing the frequency
of that word appearing.