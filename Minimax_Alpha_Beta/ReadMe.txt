Name	: Pramodh Aravindan
Student ID	: 9930-7510-26
Email	: aravinda@usc.edu

Instructions for Compiling & Executing the code
------------------------------------------------------

1. There are 3 java files in the zip archive, Intersection.java, GameBoard.java and Minimax_Alpha_Beta.java

2. First compile Intersection.java using the command

    javac Intersection.java

3. Next compile GameBoard.java using javac. 

   javac GameBoard.java

4. Finally compile Minimax_Alpha_Beta using javac. This class contains the main method for execution.
  
   javac Minimax_Alpha_Beta.java

4. Execute the program by running Minimax_Alpha_Beta using java command

   java Informed_Search

Program Structure
---------------------

Intersection.java

-- This java file contains the definition of an Intersection, i.e a point on the gameboard(row,col) where the stones are placed.
-- It includes methods for getting and setting the values of the intersection.
-- More importantly it includes the libertySet or the connected components of the intersection. This is useful in calculating the liberty of an intersection which is used in calculating the utility of the gameboard.

GameBoard.java

-- This java file primarily defines the gameboard go with a 6 X 6 array of intersections (taken from the intersection class).
-- The initial configuration of the gameboard is hardcoded in this class with the method initGameBoard().
-- There are other get and set methods to get and set the values of the GameBoard, namely the intersections, depth, utility, current move, successors and parent.
-- The three important methods in this class are
  -- generateSuccessors() - Used to generate successors of a gameboard with its configuration. i.e. it finds empty intersections on the board and generates a successor list for each gameboard.
  -- calculateLiberty() - This method is used to calculate the liberty of each intersection on the board by means of a floodfilling algorithm. i.e it makes multiple passes over the gameboard and calculates Libertyset of each intersection.		  -- calculateUtility() - This method returns the difference between the number of uncaptured black stones and uncaptured white stones as the utility of the gameboard.

Minimax_Alpha_Beta

-- This java file contains the main method for the execution of the program.
-- The class contains methods for generating a game tree with the folloeing methods, generateGameTree, move_black and move_white
-- The other methods are minimax and alpha_beta_minimax that recursively calculates the optimal utility value of the gametree.
-- The best strategy is displayed for a depth of 4 and a comparison of the runtimes are shown for minimax with pruning and without pruning.

Issues faced while coding and testing
------------------------------------------

-- The java version on aludra was 1.5.0_18. So I had to change the version by using the following 2 commands

	source /usr/usc/jdk/1.6.0_23/setup.csh
	setenv CLASSPATH .

-- While transferring the java files from windows to unix, a new line character ^M was introduced in the files. So, I had to remove them in vi editor using the following commands.

	:%s/.$//
	:w
