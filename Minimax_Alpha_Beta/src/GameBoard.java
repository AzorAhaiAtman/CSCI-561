package minimax_alpha_beta;
import java.util.*;
/**
 *
 * @author Pramodh_Aravindan
 */
public class GameBoard {
       
    static int count = 0;   
    private int depth;
    private int utility;    
    private Intersection[][] inter = new Intersection[6][6];    
    private Intersection move = new Intersection();
    private List<GameBoard> successors = new ArrayList<GameBoard>();        
    private GameBoard parent;
    
    public GameBoard()
    {
        parent = null;
        depth = 0;
        utility = 0;
        for(int i = 0; i < 6; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                inter[i][j] = new Intersection(i,j,'E');
            }
        }
    }
    public void initGameBoard()
    {
                    
        inter[0][0].setIntersection(0, 0, 'W');
        inter[0][1].setIntersection(0, 1, 'E');
        inter[0][2].setIntersection(0, 2, 'B');
        inter[0][3].setIntersection(0, 3, 'W');
        inter[0][4].setIntersection(0, 4, 'E');
        inter[0][5].setIntersection(0, 5, 'B');
        
        inter[1][0].setIntersection(1, 0, 'E');
        inter[1][1].setIntersection(1, 1, 'W');
        inter[1][2].setIntersection(1, 2, 'B');
        inter[1][3].setIntersection(1, 3, 'E');
        inter[1][4].setIntersection(1, 4, 'B');
        inter[1][5].setIntersection(1, 5, 'E');
        
        inter[2][0].setIntersection(2, 0, 'W');
        inter[2][1].setIntersection(2, 1, 'E');
        inter[2][2].setIntersection(2, 2, 'W');
        inter[2][3].setIntersection(2, 3, 'B');
        inter[2][4].setIntersection(2, 4, 'E');
        inter[2][5].setIntersection(2, 5, 'B');
        
        inter[3][0].setIntersection(3, 0, 'E');
        inter[3][1].setIntersection(3, 1, 'B');
        inter[3][2].setIntersection(3, 2, 'B');
        inter[3][3].setIntersection(3, 3, 'B');
        inter[3][4].setIntersection(3, 4, 'W');
        inter[3][5].setIntersection(3, 5, 'B');
                
        inter[4][0].setIntersection(4, 0, 'W');
        inter[4][1].setIntersection(4, 1, 'B');
        inter[4][2].setIntersection(4, 2, 'W');
        inter[4][3].setIntersection(4, 3, 'E');
        inter[4][4].setIntersection(4, 4, 'W');
        inter[4][5].setIntersection(4, 5, 'B');
                
        inter[5][0].setIntersection(5, 0, 'W');
        inter[5][1].setIntersection(5, 1, 'E');
        inter[5][2].setIntersection(5, 2, 'W');
        inter[5][3].setIntersection(5, 3, 'B');
        inter[5][4].setIntersection(5, 4, 'W');
        inter[5][5].setIntersection(5, 5, 'W');
                
    }
    
    public void setIntersection(int i, int j,char c)
    {      
        inter[i][j].setIntersection(i, j, c);
    }
    
    public void setDepth(int d)
    {
        depth = d;
    }
    
    public void setParent(GameBoard p)
    {
        parent = p;
    }
    
    public void setSuccessor(GameBoard gb)
    {
        this.successors.add(gb);
    }
    
    public void setUtiilty(int u)
    {
        utility = u;
    }
    
    public void setMove(int i, int j, char c)
    {
        move.row = i;
        move.col = j;
        move.color = c;
    }
    
    public char getIntersection(int i, int j)
    {     
        return inter[i][j].getIntersection();
    }
    
    public int getDepth()
    {
        return depth;
    }
    
    public GameBoard getParent()
    {
        return parent;
    }
    
    public int getUtility()
    {
        return utility;
    }
    
    public Intersection getMove()
    {
        return move;
    }
    
    public void generateSuccessors(char color, int depth)
    {
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                if(this.getIntersection(i, j) == 'E')
                {
                    //Create a temporary gameboard                    
                    GameBoard temp = new GameBoard();
                    
                    //Set the parent and depth of the temp game board
                    temp.setDepth(this.getDepth() + 1);                    
                    temp.setParent(this);
                    
                    //Copy the state of the parent gameboard to the temp gameboard
                    for(int r = 0; r < 6; r++)
                    {
                        for(int c = 0; c < 6; c++)
                        {
                            char x = this.getIntersection(r, c);                            
                            temp.setIntersection(r, c, x);
                        }
                    }                    
                    
                    //Set the color of the empty intersection to the designated color
                    temp.setIntersection(i, j, color);                    
                    temp.setMove(i, j, color);
                    
                    //Set temp as the successor of the current node
                    this.setSuccessor(temp);
                    count++;
                }
            }
        }
    }
    
    public List<GameBoard> getSuccessors()
    {
        return successors;
    }
    
    //method to calculate the liberty of each intersection
    public void calculateLiberty()
    {
                
        /*//For each row and each column
        //Check the bounds of i and j. 
        //If the immediate adjacent row is empty add to its liberty set
        //This represents the direct liberty of the Intersection
        for(int i = 0; i < 6; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                if(this.inter[i][j].getIntersection() != 'E')
                {                    
                    if(i-1 >= 0)
                    {
                        if(this.inter[i-1][j].getIntersection() == 'E')
                        {
                            this.inter[i][j].libertySet.add(this.inter[i-1][j]);                            
                        }
                    }
                    if(i+1 <= 5)
                    {
                        if(this.inter[i+1][j].getIntersection() == 'E')
                        {
                            this.inter[i][j].libertySet.add(this.inter[i+1][j]);
                        }
                    }
                    if(j-1 >= 0)
                    {
                        if(this.inter[i][j-1].getIntersection() == 'E')
                        {
                            this.inter[i][j].libertySet.add(this.inter[i][j-1]);
                        }
                    }
                    if(j+1 <= 5)
                    {
                        if(this.inter[i][j+1].getIntersection() == 'E')
                        {
                            this.inter[i][j].libertySet.add(this.inter[i][j+1]);
                        }
                    }
                }
            }
        }*/
        
        //Calculate the total liberty of all intersections by doing a flood filling algorithm. 
        //i.e multipass algorithm to calculate the connected components and liberty.
        //for(int c = 0; c < 6; c++)
        //{
            for(int i = 0; i < 6; i++)
            {
                for(int j = 0; j < 6; j++)
                {
                    if(this.inter[i][j].getIntersection() != 'E')
                    {
                        if(i-1 >= 0)
                        {
                            if(this.inter[i][j].getIntersection() == this.inter[i-1][j].getIntersection())
                            {
                                this.inter[i][j].libertySet.addAll(this.inter[i-1][j].libertySet);
                            }
                        }
                    
                        if(i+1 <= 5)
                        {
                            if(this.inter[i][j].getIntersection() == this.inter[i+1][j].getIntersection())
                            {
                                this.inter[i][j].libertySet.addAll(this.inter[i+1][j].libertySet);
                            }
                        }
                    
                        if(j-1 >= 0)
                        {
                            if(this.inter[i][j].getIntersection() == this.inter[i][j-1].getIntersection())
                            {
                                this.inter[i][j].libertySet.addAll(this.inter[i][j-1].libertySet);
                            }
                        }
                    
                        if(j+1 <= 5)
                        {
                            if(this.inter[i][j].getIntersection() == this.inter[i][j+1].getIntersection())
                            {
                                this.inter[i][j].libertySet.addAll(this.inter[i][j+1].libertySet);
                            }
                        }
                    }
                }
            }
        //}
    }
    
    public void calculateDirectLiberty()
    {
        for(int i = 0; i < 6; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                if(this.inter[i][j].getIntersection() != 'E')
                {                    
                    if(i-1 >= 0)
                    {
                        if(this.inter[i-1][j].getIntersection() == 'E')
                        {
                            this.inter[i][j].libertySet.add(this.inter[i-1][j]);                            
                        }
                    }
                    if(i+1 <= 5)
                    {
                        if(this.inter[i+1][j].getIntersection() == 'E')
                        {
                            this.inter[i][j].libertySet.add(this.inter[i+1][j]);
                        }
                    }
                    if(j-1 >= 0)
                    {
                        if(this.inter[i][j-1].getIntersection() == 'E')
                        {
                            this.inter[i][j].libertySet.add(this.inter[i][j-1]);
                        }
                    }
                    if(j+1 <= 5)
                    {
                        if(this.inter[i][j+1].getIntersection() == 'E')
                        {
                            this.inter[i][j].libertySet.add(this.inter[i][j+1]);
                        }
                    }
                }
            }
        }
    }
    public int calculateUtility()
    {
        int black = 0;
        int white = 0;
        //For each row and each colum i.e each intersection
        //Calculate the number of stones of black and white that are not captured
        //Return utility as the difference between the number of black and white stones.
        
        for(int i = 0; i < 6; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                if(inter[i][j].getIntersection() == 'B' && !inter[i][j].libertySet.isEmpty())
                {
                    black++;
                }
                else if(inter[i][j].getIntersection() == 'W' && !inter[i][j].libertySet.isEmpty())
                {
                    white++;
                }
            }
        }
        this.setUtiilty(black-white);
        return black - white;
        
    }
    
    public void printGameBoard()
    {
        System.out.println();
        for(int i = 0; i < 6; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                System.out.print(this.getIntersection(i, j) + "\t");                
            }
            System.out.println();
        }
    }
    
    public void printLiberty()
    {
        for(int i = 0; i < 6; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                System.out.println("Liberty of intersection " + "(" + (i) + "," + (j) + ") is " + inter[i][j].libertySet.size());
            }
        }
    }     
}
