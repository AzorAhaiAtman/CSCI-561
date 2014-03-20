import java.util.*;

public class Movie_Node
{
    private int movie_index;
    private int path_cost;
    private int heuristic_cost;
    
    private boolean start_node;
    private boolean goal_node;
    
    private List<Movie_Node> neighbours = new ArrayList<Movie_Node>(); 
    
    private Movie_Node parent;
    private Movie_Node child;
    
    //Default Constructor
    public Movie_Node()
    {
        movie_index = 0;
        path_cost = Integer.MAX_VALUE;
        heuristic_cost = 0;
        
        start_node = false;
        goal_node = false;
        
        parent = null;
        child = null;        
    }

    //Constructor to set Movie Index and Heuristic Cost
    public Movie_Node(int index, int h_cost)
    {
        movie_index = index;
        heuristic_cost = h_cost;         
    }
    
    public void setMovieIndex(int index)
    {
        movie_index = index;
    }
    
    public void setPathCost(int p_cost)
    {
        path_cost = parent.path_cost + p_cost;
    }
    
    public void setHeuristicCost(int h_cost)
    {
        heuristic_cost = h_cost;
    }
    
    public void setParent(Movie_Node p)
    {
        parent = p;
    }
    
    public void setChild(Movie_Node c)
    {
        child = c;
    }
    
    public void setStartNode(boolean value)
    {
        start_node = value;
    }
    
    public void setGoalNode(boolean value)
    {
        goal_node = value;
    }
    
    public void setNeighbours(Movie_Node n)
    {
        neighbours.add(n);
    }
    
    public int getMovieIndex()
    {
        return movie_index;
    }
    
    public int getPathCost()
    {
        return path_cost;
    }
    
    public int getHeuristicCost()
    {
        return heuristic_cost;
    }
    
    public Movie_Node getParent()
    {
        return parent;
    }
    
    public Movie_Node getChild()
    {
        return child;
    }
    
    public boolean isStartNode()
    {
        return start_node;
    }
    
    public boolean isGoalNode()
    {
        return goal_node;
    }
    
    public List<Movie_Node> getNeighbours()
    {
        return neighbours;
    }
    
    @Override
    public String toString()
    {
       return "m" + Integer.toString(movie_index); 
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (obj == null) 
        {
            return false;
        }
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        
        final Movie_Node other = (Movie_Node) obj;
        
        if (this.movie_index != other.movie_index) 
        {
            return false;
        }
        if(this.heuristic_cost != other.heuristic_cost)
        {
            return false;
        }
        return true;
    }        
}
