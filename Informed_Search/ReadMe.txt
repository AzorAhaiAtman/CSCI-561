Name	: Pramodh Aravindan
Student ID	: 9930-7510-26
Email	: aravinda@usc.edu

Instructions for Compiling & Executing the code
------------------------------------------------------

1. There are 2 java files in the zip archive, Movie_Node.java and Informed_Search.java

2. First compile Movie_Node.java using the command

    javac Movie_Node.java

3. Next compile Informed_Search.java using javac. This class contains the main method for execution.

   javac Informed_Search.java

4. Execute the program by running Informed_Search using java command

   java Informed_Search


Program Structure
--------------------

Movie_Node.java

-- The Movie_Node.java file contains the definition of  a Movie class with which we are to perform our search/traversal.
-- It includes method for getting and setting the values of Movie_Node members.
-- methods like equals() and toString() are overriden in the Movie_Node class.

Informed_Search.java

-- This file contains the main method for the execution of the program.
-- The program creates Queue data structures like Queue/LinkedList to hold movie nodes while traversal.
-- The Indicator Matrix is hardcoded into the program by means of a static two-dimensional integer array.
-- A dissimilarity matrix of 12 X 12 is created to hold the dissimilarity values of neighbouring movie nodes.
-- The matrix values are calculated using the given formula {(i+j)%T0 + 1} . {T0 - Vmi . Vmj}
-- With the help of dissimilarity matrix the neighbouring movie nodes are set.
-- The functions best_first_search and astar_search traverse and prints the path from start node to goal node.

Issues faced while coding and testing
------------------------------------------

-- The java version on aludra was 1.5.0_18. So I had to change the version by using the following 2 commands

	source /usr/usc/jdk/1.6.0_23/setup.csh
	setenv CLASSPATH .

-- While transferring the java files from windows to unix, a new line character ^M was introduced in the files. So, I had to remove them in vi editor using the following commands.

	:%s/.$//
	:w
