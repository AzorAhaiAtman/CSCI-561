package minimax_alpha_beta;
import java.util.*;

/**
 *
 * @author Pramodh_Aravindan
 */
//Intersection class represents an intersection on the board
public class Intersection
{
    int row;
    int col;
    char color;
    int liberty;    //To store the value of the libert for this intersection
    Set<Intersection> libertySet = new HashSet<Intersection>(); //To store the connected components of the current intersection with liberty
    
    //Default constructor
    public Intersection()
    {
        row = 0;
        col = 0;
        color = 'E';
        liberty = 0;
    }
            
    public Intersection(int r, int c, char co)
    {
        row = r;
        col = c;
        color = co;
    }
    
    public void setIntersection(int r, int c, char co)
    {
        row = r;
        col = c;
        color = co;
    }
    
    public char getIntersection()
    {
        return color;
    }
    
    public int getRow()
    {
        return row;
    }
    
    public int getCol()
    {
        return col;        
    }
    
    @Override
    public String toString()
    {
        return ("Player " + this.getIntersection() + " places stone at (" + (this.getCol()+1) + "," + (this.getRow()+1) + ")"); 
    }
    
    
           
}
