# Brick Destroyer
This README provides a summary on the major refactoring activities and additions made to the Brick Destroyer Game provided for the Software Maintenance Coursework 2021.<br>
(https://github.com/githubmehul/COMP2042_CW_hcyms1/tree/master)

# About The Project
This is a Platformer arcade game. The Player's goal is to destroy a wall with multiple bricks with a ball. <br><br> <b>The game has very simple commmands:</b> <br> SPACE - Start/Pause the game <br> A Key - Move the Player Left <br> D Key - Move the Player Right<br> ESC Key -  Enter/Exit the Pause Menu <br> ALT+SHITF+F1  - Open Debug Console <br> Automatic Pause of the Game when the Window loses Focus

# Gradle Run
Pre-requisite : Java 10 or Newer

A build file is added as it automatically downloads and configures the dependencies and other libraries used. Gradle Wrapper allows us to run the build file without installing Gradle. When we invoke "gradlew", it downloads and builds the Gradle version specified. In order to run the Brick Destroy application from the command line, the following steps can be followed:
1. Open command prompt and change the directory to where the build.gradle file is found.
2. Run the application using the command "gradlew run".<br><br>
`   > cd "C:\Users\<Admin_Name>\<Folder_Name>\COMP2042_CW_hcyms1-master\"`<br><br>
`   > gradlew run ` <br><br>

# Major Refactoring Activities and Corrective Maintainence
<b>1. Rearranging the classes and resources</b>
<br>Classes that were previously all in one package have been arranged using the MVC pattern. The resources used have also been arranged into a resources folder. The JUnit Test Cases have been also divided. It allows easy identification of the numerous resources and easily set the path.<br>

<b>2. MVC Pattern</b>
<br> The MVC pattern has been used to arrange the classes. Considering changes have been made to the code in this aspect. The Model handles data and represents the game objects. It also allows us to update the controller of the data changes. The Controller controls the data flow into model object and update the view whenever data changes. The view is responsible for rendering the data received from the model.<br>

<b>3. Insertion of Javadocs</b>
<br>JavaDoc tool is a document generator tool in Java programming language for generating standard documentation in HTML format. It generates API documentation. It parses the declarations and documentation in a set of source file describing classes, methods, constructors, and fields. The purpose of the API is to provide information about code so other programmers can use it without needing a thorough knowledge of its inner workings. I had also added inline comments to enhance readability, for the user.<br><br>

<b>4. Enhance Variable and Identifier Name</b>
<br>Variables and Identifiers are important to the code that they deserve a good name that accurately describes their purpose. Rename refractoring as per Java Naming Conventions for Constants, Variables, Methods and Classes were made.</br>

<b>5. Indentation</b>
<br>Indentation is used to format program source code to improve readability. Indentation formatting was made on all classes.</br>

<b>6. Encapsulation Implementation </b><br>
The Encapsulate Fields refactoring lets data be hidden and create the necessary accessors for it.
Hiding data and accessing it through an outward interface based on accessor methods enabling changes to be made to this storage without the necessity of changing the code that references the data.<br>
Considerable Changes have been made to each class to improve concelation of object data. Refractoring was done in a way that it performs complicated operations, related to access of data fields and it makes the code much easier to maintain and develop.</br>

<b>7. Reduce Large Classes(Code Smell) – Improve Single Responsibility(SOLID Principle)</b></br>
When one class does the work of two, awkwardness results.Instead, create a new class and place the fields and methods responsible for the relevant functionality in it.<br>
This refactoring method helped maintain adherence to the Single Responsibility Principle. The code of the classes are now obvious and understandable.<br>
Considerable changes have been made to the WallController Class and BallController Class. The level making functions were extracted , and a new class was created , LevelModelClass. From BallController Class , the CrackController Class was created(upper class). Adhering to MVC as well, considerable extraction were made to extract the View Classes like, ExitButtonView, InstructionButtonView, HighScoreButtonView and StartButtonView from HomeMenuView<br>

<b>7. Singleton Principle (Design Pattern Implementation)</b>
<br>Singleton is a creational design pattern that ensures that a class has only one instance, while providing a global access point to this instance. Singleton pattern has been applied to classes to ensure controlled access to some shared resource. For instance, the HighScoreController Class has the Singleton Pattern implementated. It is also initialized only when it’s requested for the first time.<br>

<b>8. Reduction in Method Size (Code Smell)</b>
<br>Bloaters are code, methods and classes that have increased to such gargantuan proportions that they are hard to work with. Alot of classes, and their methods were really long to work with. Hence, to solve this code smell issue, a number of method extractions and class extractions were made. For instance, the GameBoardController class was made considerable changes on to make it the game controller. Initially, it had the functions for rendering the view, as well as the functionality of the pausemenu. Hence, all that has been removed to a seperate class.<br>

<b>9. Removal of Dead Code (Code Smell)</b>
<br>A variable, parameter, field, method or class is no longer used (usually because it’s obsolete). After considerable changes to the code, dead code and unnecessary parameters were removed. 

# Major Extensions and Additions

<b>1. Build File Creation</b><br>
Gradle is an advanced general-purpose build management tool that is based on Groovy and Kotlin.The build system allows you to define flexible custom build configurations, where one can build, deploy and test. Gradle was used as an extension to enhance the runnability of the project and easy access by the user.</br>

<b>2. Major Edits to HomeMenu Screen</b><br>
Major changes and extentions have been made to the HomeMenuScreen to support MVC and various additions. There is a change the background looks of it as well. Seperate Button have been added in the HomeMenuScreen. The <b>StartButtonView</b> is responsible for the looks and functionality of the start button that calles the enableGameboard(). The <b>InstructionButtonView</b> is created for a help screen for the user with an included exit button from it that disposes the frame. The <b>HighScoreButtonView</b> is responsible for displaying the sorted highscores by reading the leaderboard.dat file, with an included exit button as well. Lastly, <b>ExitButtonView</b> is responsible for the looks and functionality ot the exit button. All the buttons have been added audio functionality when clicked.</br>

<b>3. Game Timer System - Penalty System</b>
<bR>Considerable changes have been made to the Game by adding a Timer Function. The timer is responsible to track the time the user took at every level. If the user exceeds the time limit for that particular level, the user is made to restart the level that they are on. If they manage to pass the level before the time ends, they proceed to the next level, where a Score and Time text arises to remind the user of how much they scored, in what time in the previous level. The changes here have also been added in the PauseMenuController as well, to regulate the timer if the user wishes to continue (continue the timer) or restart the game(restart the timer). When the user Pauses or loses focus, the timer is paused as well.</br>
  
<b>4. Brick based score - Reward System</b>
<br>Each brick has been alotted a specific score. For instance, if the ball impacts the ClayBrick, the user gets 1 point, if the ball impacts the CementBrick, the user gets 2 points and so on.</br>
  
<b>5. Added Playable Levels</b><br>
There are a total of 6 levels that the user has to play. 6 levels have been added to comply with the user retention when playing the game. Additional Bricks have been created, the IceBrickModel and the FireBrickModel.</br>

<b>6. Brick Based Speed - Penalty System</b>
As for the additionally created bricks, the IceBrickModel has a strength of 1 impacts, and when the ball finally manages to break the brick, the speed reduces drastically. The FireBrickModel has a strength of 2 as well, and when the ball manages to break the brick, the speed increases drastically. So the user would have to be very attentive and plan their next move of impact.</br>
 
<b>7. Audio Implementation</b>
Audio features have been added when the user presses the homescreen buttons, finally wins the game, progresses to the next level and when the ball impacts the wall,bricks, player as well as when the ball is lost.</br>
  
<b>8. HighScore Implementation</b>
The score of the user is tracked in every level, considering the score of each brick. After each level is completed, the user sees their accumulated score and time taken in the previous level. The HighScore of the player is checked against the highScore.dat file . The highscore is checked when the number of balls are all lost(progresses the user to the homescreen after inputting name), or when the user completes all the levels and wins the game. If the score attained by the user is greater than the previous user's score, a dialog appears which asks their name and saves the scores, which is then further sorted in the leaderboard.dat to preview in the HighScoreButton View.

<b>9. Level Indicator</b>
<br> Once a user progresses, a level indicator is shown telling the user what level they are currently in, a feature that was not present in the original version of the game.</br>

<b>10. DebugPanel</b>
<br>The Ball speed has been kept constant, as the randomized ball speed at each play would reduce the retention of the player. The bricks additionally created changes the speeds as well.</br>

# Reflections
<br>After making the changes, it can be deduced that it is now better to manage the classes as well as the relationships between them while also improving encapsulation. The classes have been made more maintainable and we can easily add more game obstacles or levels. The game has also been made more interesting with the varying audio and different levels. There are also test classes that have been implemented to check the proper functionality of several elements.</br>

# Amended File Structure
```
src
|
|-- main
|	|-- java
|	|	|-- controller
|	|	|	|-- AudioController
|	|	|	|-- BallController
|	|	|	|-- BrickController
|	|	|	|-- CrackController
|	|	|	|-- DebugPanelController
|	|	|	|-- GameBoardController
|	|	|	|-- HighScoreController
|	|	|	|__ PauseMenuController
|	|	|       |__ PlayerController
|	|	|       |__ TimerController
|	|	|       |__ WallController
|	|	| 
|	|	| 
|	|	|-- model
|	|	|	|-- CementBrickModel
|	|	|	|-- ClayBrickModel
|	|	|	|-- FireBrickModel
|	|	|	|-- GameFrameModel
|	|	|	|-- HighScoreModel
|	|	|	|-- IceBrickModel
|	|	|	|-- IntersectingObject
|	|	|	|-- LevelModel
|	|	|	|-- PlayerModel
|	|	|	|-- RubberBallModel
|	|	|	|-- SteelBrickModel
|	|	|
|	|	|-- view
|	|	|	|-- DebugPanelView
|	|	|	|-- ExitButtonView
|	|	|	|-- GameBoardView
|	|	|	|-- HighScoreButtonView
|	|	|	|-- HomeMenuView
|	|	|	|-- InstructionButtonView
|	|	|	|-- PauseMenuView
|	|	|	|__ StartButtonView
|	|	|
|	|	|__ game
|	|	
|	|__ resources	
|	
|-- test
	|-- java
	|__ resources
```
# Screenshots of Game
<b>Game Home Screen</b><br>
<img width="449" alt="GameView" src="https://user-images.githubusercontent.com/53290160/145739037-265c533a-68f0-4634-a5d2-01f68961691a.png"> <br>
<b>Sample Game Level</b><br>
<img width="392" alt="GameLevel" src="https://user-images.githubusercontent.com/53290160/145739120-197cafb1-b2bc-4e90-ada9-c331c5023e27.png"><br>
<b>Help Screen</b><br>
<img width="521" alt="HelpScreen" src="https://user-images.githubusercontent.com/53290160/145739151-596646f2-9fe4-40f1-a46f-7552341e1077.png"><br>
<b>Leaderboard Screen</b><br>
<img width="500" alt="LeaderBoardScreen" src="https://user-images.githubusercontent.com/53290160/145739177-43e19038-28e9-4358-9098-7cf10968d183.png"><br>
<b>HighScore Dialog Entry</b><br>
<img width="394" alt="HighScoreDialog" src="https://user-images.githubusercontent.com/53290160/145739207-50023d64-8ea7-4034-a567-52b0977190fa.png"><br>

