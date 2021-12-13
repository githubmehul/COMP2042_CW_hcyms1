# Brick Destroyer
This README provides a summary on the major refactoring activities and additions made to the Brick Destroyer Game provided for the Software Maintenance Coursework 2021.<br>
<b>Project Start Date</b> - 30th October 2021 (Repository #2 was created on 10th November 2021 due to IDE Issues)
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
<br>Classes that were previously all in one package have been arranged using the MVC pattern. The resources used have also been arranged into a resources folder. The JUnit Test Cases have been also divided. It Allows easy identification of the numerous resources and easily set the path.<br>

<b>2. MVC Pattern</b>
<br> The MVC pattern has been used to arrange the classes. Considering changes have been made to the code in this aspect. The Model handles data and represents the game objects. It also allows us to update the controller of the data changes. The Controller controls the data flow into model object and update the view whenever data changes. The view is responsible for rendering the data received from the model.<br><br>

<b>3. Insertion of Javadocs</b>
<br>JavaDoc tool is a document generator tool in Java programming language for generating standard documentation in HTML format. It generates API documentation. It parses the declarations and documentation in a set of source file describing classes, methods, constructors, and fields. The purpose of the API is to provide information about code so other programmers can use it without needing a thorough knowledge of its inner workings. I had also added inline comments to enhance readability ,for the user.<br><br>

<b>4.	Enhance Variable and Identifier Name</b>
<br>Variables and Identifiers are important to the code that they deserve a good name that accurately describes their purpose. Rename refractoring as per Java Naming Conventions for Constants, Variables, Methods and Classes. were made.</br>

<b>5.	Indentation</b>
<br>Indentation is used to format program source code to improve readability. Indentation formatting was made on all classes.</br>

<b>6.	Encapsulation Implementation </b><br>
The Encapsulate Fields refactoring lets data be hidden and create the necessary accessors for it.
Hiding data and accessing it through an outward interface based on accessor methods enabling changes to be made to this storage without the necessity of changing the code that references the data.<br>
Considerable Changes have been made to each class to improve concelation of object data. Refractoring was done in a way that it performs complicated operations, related to access of data fields and it makes the code much easier to maintain and develop.</br>

<b>7.	Reduce Large Classes(Code Smell) – Improve Single Responsibility(SOLID Principle)</b></br>
When one class does the work of two, awkwardness results.Instead, create a new class and place the fields and methods responsible for the relevant functionality in it.<br>
This refactoring method helped maintain adherence to the Single Responsibility Principle. The code of the classes are now obvious and understandable.<br>
Considerable changes have been made to the WallController Class and BallController Class. The level making functions were extracted , and a new class was created , LevelModelClass. From BallController Class , the CrackController Class was created(upper class). Adhering to MVC as well, considerable extraction were made to extract the View Classes like, ExitButtonView, InstructionButtonView, HighScoreButtonView and StartButtonView from HomeMenuView <br>

<b>7. Singleton Principle (Design Pattern Implementation)</b>
<br>Singleton is a creational design pattern that ensures that a class has only one instance, while providing a global access point to this instance. It ensures that a class has exactly one instance. Singleton pattern has been applied to classes to ensure controlled access to some shared resource. For instance, the HighScoreController Class has the Singleton Pattern implementated. It is also initialized only when it’s requested for the first time.<br>

<b>8. Reduction in Method Size (Code Smell)</b>
<br>Bloaters are code, methods and classes that have increased to such gargantuan proportions that they are hard to work with. Alot of classes, and their methods were really long to work with.Hence, to solve this code smell issue, a number of method extractions and class extractions were made. For instance, the GameBoardController class was made considerable changes on to make it the game controller. Initially, it had the functions for rendering the view, as well as the functionality of the pausemenu. Hence, all that has been removed to a seperate class.<br>

<b>9. Removal of Dead Code (Code Smell)</b>
<br>A variable, parameter, field, method or class is no longer used (usually because it’s obsolete). After considerable changes to the code, dead code and unnecessary parameters were removed. 

# MajorExtensions and Additions


