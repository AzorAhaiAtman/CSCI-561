package minimax_alpha_beta;
import java.util.*;
import java.lang.*;

/**
 *
 * @author Pramodh Aravindan
 */
public class Minimax_Alpha_Beta {
   
    public static int depth = 4;    
    static int calls = 0;
    
    static Queue<Intersection> moves = new LinkedList<Intersection>();
    
    public static void main(String[] args) {
             
        //Create a new GameBoard and set it to its initial configuration as mentioned in the description
        GameBoard go = new GameBoard();       
        go.initGameBoard();
        GameBoard go_minimax = new GameBoard();
        go_minimax.initGameBoard();
        
        //generate the nodes in the game tree recursively upto a depth of 4
        generateGameTree(go);                          
        generateGameTree(go_minimax);
        
        //Alpha-Beta Algorithm
          long ab_starttime = System.currentTimeMillis();
          int ab_optimal = alpha_beta_minimax(go);        
          long ab_stoptime = System.currentTimeMillis() - ab_starttime;        
          int alpha_beta_nodes = calls;
          
          //Minimax Algorithm
          calls = 0;         
          long starttime = System.currentTimeMillis();
          int optimal = minimax(go_minimax);
          long stoptime = System.currentTimeMillis() - starttime;
          int minimax_nodes = calls;
          
          //Get the path for the utility value and print it
          getPath(go, ab_optimal);
          Iterator<Intersection> moveIter = moves.iterator();         
          int movecount = 0;
          System.out.println("Best Strategy");
          while(moveIter.hasNext())
          {
              Intersection move = moveIter.next();
              System.out.println("Depth " + movecount + ": " + move);
              movecount++;
          }                    
          System.out.println("Depth " + movecount + ": Utility value of the current board configuration " + ab_optimal);
          System.out.println("Comparison");
          System.out.println("Minimax with pruning " + ab_stoptime + " ms");
          System.out.println("Minimax with pruning: pruned " + (minimax_nodes - alpha_beta_nodes) + " nodes");
          System.out.println("Minimax without pruning " + stoptime + " ms");          
          
    }
    
    //A static method that generates a gametree for Go upto a depth of 4
    public static void generateGameTree(GameBoard gb)
    {        
        //Black makes the first move at a depth of 0
        move_black(gb,0);
    }
    
    public static void move_black(GameBoard gb, int d)
    {
        if(gb.getDepth() == depth)
        {
            return;
        }
        gb.generateSuccessors('B', d);
        List<GameBoard> successorList = gb.getSuccessors();
        for(int i = 0; i < successorList.size(); i++)
        {
            GameBoard child = successorList.get(i);
            move_white(child,d+1);
        }
    }
    
    public static void move_white(GameBoard gb, int d)
    {
        if(gb.getDepth() == depth)
        {
            return;
        }
        gb.generateSuccessors('W', d);
        List<GameBoard> successorList = gb.getSuccessors();
        for(int i = 0; i < successorList.size(); i++)
        {
            GameBoard child = successorList.get(i);
            move_black(child,d+1);
        }
    }
    
    public static int minimax(GameBoard gb)
    {        
        int value;
        value = maxvalue(gb);
        return value;
    }
        
    public static int maxvalue(GameBoard gb)
    {
        calls++;
        if(gb.getDepth() == depth)
        {
            gb.calculateDirectLiberty();
            gb.calculateLiberty();
            gb.calculateLiberty();
            gb.calculateLiberty();            
            return gb.calculateUtility();            
        }
        
        int value = Integer.MIN_VALUE;        
        
        for(int i = 0; i < gb.getSuccessors().size(); i++)
        {
            GameBoard child = gb.getSuccessors().get(i);
            value = Math.max(value, minvalue(child));                        
        }
        gb.setUtiilty(value);            
        return value;
    }
    
    public static int minvalue(GameBoard gb)
    {
        calls++;
        if(gb.getDepth() == depth)
        {
            gb.calculateDirectLiberty();
            gb.calculateLiberty();
            gb.calculateLiberty();
            gb.calculateLiberty();
            return gb.calculateUtility();            
        }
        
        int value = Integer.MAX_VALUE;
        
        for(int i = 0; i < gb.getSuccessors().size(); i++)
        {
            GameBoard child = gb.getSuccessors().get(i);
            value = Math.min(value, maxvalue(child));            
        }        
        gb.setUtiilty(value);
        return value;
    } 
    
    public static int alpha_beta_minimax(GameBoard gb)
    {
        int value;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        value = alpha_beta_maxvalue(gb,alpha,beta);
        return value;
    }
    
    public static int alpha_beta_maxvalue(GameBoard gb,int alpha,int beta)
    {
        calls++;
        if(gb.getDepth() == depth)
        {
            gb.calculateDirectLiberty();
            gb.calculateLiberty();
            gb.calculateLiberty();
            gb.calculateLiberty();
            return gb.calculateUtility();            
        }
        
        int value = Integer.MIN_VALUE;
        
        for(int i = 0; i < gb.getSuccessors().size(); i++)
        {
            GameBoard child = gb.getSuccessors().get(i);
            value = Math.max(value, alpha_beta_minvalue(child,alpha,beta));            
            gb.setUtiilty(value);      
            if(value >= beta)
            {
                return value;                
            }
            alpha = Math.max(alpha, value);
        }
        return value;        
    }
    
    public static int alpha_beta_minvalue(GameBoard gb,int alpha,int beta)
    {
        calls++;
        if(gb.getDepth() == depth)
        {
            gb.calculateDirectLiberty();
            gb.calculateLiberty();
            gb.calculateLiberty();
            gb.calculateLiberty();
            gb.calculateLiberty();
            return gb.calculateUtility();            
        }
        
        int value = Integer.MAX_VALUE;
        
        for(int i = 0; i < gb.getSuccessors().size(); i++)
        {
            GameBoard child = gb.getSuccessors().get(i);
            value = Math.min(value, alpha_beta_maxvalue(child,alpha,beta));            
            gb.setUtiilty(value);      
            if(value <= alpha)
            {
                return value;                
            }
            beta = Math.min(beta, value);
        }
        return value;
    }
    
    public static void getPath(GameBoard gb, int util)
    {
                       
        List<GameBoard> childList = gb.getSuccessors();
        for(int i = 0; i < childList.size(); i++)
        {
            GameBoard child = childList.get(i);
            if(child.getUtility() == util)
            {                
                moves.add(child.getMove());                                                
                getPath(child,util);
                break;
            }           
        }
                
    }
}

