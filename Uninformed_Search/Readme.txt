Name	: Pramodh Aravindan
Student ID	: 9930-7510-26
Email	: aravinda@usc.edu	

Instructions for Compiling & Executing the code
-------------------------------------------------------

1. There are 2 java files in the zip archive, Node.java and Uninformed_Search.java
2. First compile Node.java using the command

    javac Node.java

2. Next compile Uninformed_Search.java using javac. This class contains the main method for execution.

    javac Uninformed_Search.java

3. Execute the program by running Uninformed_Search using java command

    java Uninformed_Search

Instructions on selecting search algorithm from the user interface
-------------------------------------------------------------------------

-- Upon executing Uninformed_Search, the user would see an UI screen like this

Menu
1.Breadth First Search
2.Depth First Search
3.Uniform Cost Search

Enter Choice: 

-- Select any one choice from the above to get the traversal path and stitching curve. Eg. 1 for BFS.
-- Similarly execute the program again for DFS by selecting 2 and UCS by selecting 3. 

Program Structure
---------------------

Node.java
-- The Node.java file contains the definition/creation of the primitive Node object with which we are to perform our search/traversal upon.
-- It also includes methods for getting and setting the values of Node members.
-- Some methods like compareTo(), toString(), equals() are overriden in this file.

Uninformed_Search.java
-- This file contains the main method for execution of the program.
-- This java program creates the data structures such as Queue, Priority Queue to hold Node data.
-- The node array(grid) is initialized and the start and goal nodes are set up upon calling the initNodes() method.
-- The UI menu is displayed and the user inputs his/her corresponding choice using scanner class.
-- The switch case selects the search algorithm depending on the user input choice.
-- The methods bfs, dfs and ucs are defined outside the main method
-- The methods return true if the goal node is reached else false.
-- If the goal node is reached, the traversal path, stitching curve and its pathcost are displayed on screen.

Issues faced while porting and testing on aludra
------------------------------------------------------

-- The java version on aludra was 1.5.0_18. So I had to change the version by using the following 2 commands

	source /usr/usc/jdk/1.6.0_23/setup.csh
	setenv CLASSPATH .

-- While transferring the java files from windows to unix, a new line character ^M was introduced in the files. So, I had to remove them in vi editor using the following commands. 

	:%s/.$//
	:w
-- However the program worked even without removing the ^M character. I just did it for improving readabillity.
