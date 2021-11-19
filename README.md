# Brick Destroyer
This README provides a summary on the major refactoring activities and additions made to the Brick Destroyer Game provided for the Software Maintenance Coursework 2021.<br>
<b>Project Start Date</b> - 30th October 2021 (Repository #2 was created on 10th November 2021 due to IDE Issues)
# About The Project
This is a Platformer arcade game. The Player's goal is to destroy a wall with multiple bricks with a ball. <br><br> <b>The game has very simple commmands:</b> <br> SPACE - Start/Pause the game <br> A Key - Move the Player Left <br> D Key - Move the Player Right<br> ESC Key -  Enter/Exit the Pause Menu <br> ALT+SHITF+F1  - Open Debug Console <br> Automatic Pause of the Game when the Window loses Focus
# Gradle Run
Pre-requisite : Java 10 or Newer

A build file is added as it automatically downloads and configures the dependencies and other libraries used. Gradle Wrapper allows us to run the build file without installing Gradle. When we invoke "gradlew", it downloads and builds the Gradle version specified. In order to run the Brick Destroy application from the command line, the following steps can be followed:
1. Open command prompt and change the directory to where the build.gradle file is found.
2. Run the application using the command "gradlew run".

` > gradlew run`

# Major Refactoring Activities and Corrective Maintainence
<b>1. MVC Pattern</b>
<br> The MVC pattern has been used to arrange the classes. The Model handles data and represents the game objects. It also allows us to update the controller of the data changes. The Controller controls the data flow into model object and update the view whenever data changes. The view is responsible for rendering the data received from the model.<br><br>
<b>2. Insertion of Javadocs</b>
<br>JavaDoc tool is a document generator tool in Java programming language for generating standard documentation in HTML format. It generates API documentation. It parses the declarations and documentation in a set of source file describing classes, methods, constructors, and fields. The purpose of the API is to provide information about code so other programmers can use it without needing a thorough knowledge of its inner workings. I had also added inline comments to enhance readability , and allow me to understand the code for further additions and mainainence.<br><br>
<b>3.	Enhance Variable and Identifier Name</b></br>
<b>4.	Indentation</b></br>
<b>5.	Encapsulation Implementation</b></br>
The Encapsulate Fields refactoring lets data be hidden and create the necessary accessors for it.
Hiding data and accessing it through an outward interface based on accessor methods enabling changes to be made to this storage without the necessity of changing the code that references the data.<br>
Considerable Changes have been made to the PlayerModel , BallController , and BrickController Class<br><br>
<b>6.	Reduce Large Classes – Improve Single Responsibility</b></br>
When one class does the work of two, awkwardness results.Instead, create a new class and place the fields and methods responsible for the relevant functionality in it.<br>
This refactoring method will help maintain adherence to the Single Responsibility Principle. The code of the classes will be more obvious and understandable.<br>
Considerable changes have been made to the WallController Class and BallController Class . The level making functions were extracted , and a new class was created , LevelModelClass. From BallController Class , the CrackController Class was created.<br>
<b>7. 


