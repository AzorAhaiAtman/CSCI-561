public class Node implements Comparable<Node>
{

    private int x;
    private int y;    
    
    private int weight;
    private int path_cost;
    private int depth;
    
    private boolean start_node;
    private boolean goal_node;
    private boolean visited_node;
    private boolean frontier_node;
    
    private Node east;
    private Node north;
    private Node west;
    private Node south;
    
    private Node parent;
    private Node child;
    
    // Default Constructor
    public Node()
    {
        x = 0;
        y = 0;
        weight = 0;
        path_cost = 0;
        depth = 0;
        
        east = null;
        north = null;
        west = null;
        south = null;
        
        parent = null;
        child = null;
    }
    
    // Constructor for Node object. X value, Y value, Weight Cost
    public Node(int x1,int y1,int w)
    {
        x = x1;
        y = y1;
        
        weight = w;
        path_cost = 1000;
        depth = 0;
        
        start_node = false;
        goal_node = false;        
        visited_node = false;
        frontier_node = false;
        
    }           
    
    //Set methods for variables
    
    public void setX(int x1)
    {
        x = x1;
    }
    
    public void setY(int y1)
    {
        y = y1;      
    }
    
    public void setXY(int x1,int y1)
    {
        x = x1;
        y = y1;       
    }
    
    public void setWeight(int w)
    {
        weight = w;
    }
    
    public void setPathCost()
    {
        if(parent != null){
            path_cost = weight + parent.path_cost;
        }
        else if(isStartNode() == true){
            path_cost = weight;
        }
            
    }
    
    public void setDepth()
    {
        if(parent!=null){
            depth = 1 + parent.depth;            
        }
        else if(isStartNode()== true){
            depth = 0;
        }
    }
    
    public void setStartNode(boolean start_val)
    {
        start_node = start_val; 
    }
    
    public void setGoalNode(boolean goal_val)
    {
        goal_node = goal_val;
    }
    
    public void setVisitedNode(boolean visited_val)
    {
        visited_node = visited_val;        
    }
    
    public void setFrontierNode(Boolean frontier_val)
    {
        frontier_node = frontier_val;
    }
    
    public void setEast(Node e)
    {
        east = e;
    }
    
    public void setNorth(Node n)
    {
        north = n;
    }
    
    public void setWest(Node w)
    {
        west = w;
    }
    
    public void setSouth(Node s)
    {
        south = s;
    }
    
    public void setParent(Node p)
    {
        parent = p;
    }
    
    public void setChild(Node c)
    {
        child = c;
    }
            
    
    //get methods for variables
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
         
    public int getWeight()
    {
        return weight;
    }
    
    public int getPathCost()
    {
        return path_cost;
        
    }
    
    public int getDepth()
    {
        return depth;
    }
    
    public boolean isStartNode()
    {
        return start_node;
    }
    
    public boolean isGoalNode()
    {
        return goal_node;
    }
    
    public boolean isVisitedNode()
    {
        return visited_node;
    }
    
    public boolean isFrontierNode()
    {
        return frontier_node;
    }
    
    public Node getEast()
    {
        return east;
    }
    
    public Node getNorth()
    {
        return north;
    }
    
    public Node getWest()
    {
        return west;
    }
    
    public Node getSouth()
    {
        return south;
    }
    
    public Node getParent()
    {
        return parent;
    }
    
    public Node getChild()
    {
        return child;
    }
    
    
    // Method to return successors (east,north,west and south) of a node 
    public Node[] getSuccessors()
    {
        if(east == null && north == null)
        {
            Node[] temp = {west,south};        
            return temp;
        }
        else if(north == null && west == null)
        {
            Node[] temp = {east,south};
            return temp;
        }
        else if(west == null && south == null)
        {
            Node[] temp = {east,north};
            return temp;
        }
        else if(south == null && east == null)
        {
            Node[] temp = {north,west};
            return temp;
        }
        else if(east == null)
        {
            Node[] temp = {north,west,south};
            return temp;
        }
        else if(north == null)
        {
            Node[] temp = {east,west,south};
            return temp;
        }
        else if(west == null)
        {
            Node[] temp = {east,north,south};
            return temp;           
        }
        else if(south == null)
        {
            Node[] temp = {east,north,west};
            return temp;
        }
        else
        {
            Node[] temp = {east,north,west,south};
            return temp;
        }
    }
    
    // Overriding compareTo method. Used for sorting nodes in ascending order. 
    @Override
    public int compareTo(Node other)
    {                    
          if(path_cost == other.path_cost) {
              return (depth * path_cost) - (other.depth * other.path_cost);
          }
          else{
            return path_cost - other.path_cost;
          }
    }
    
    // Overriding toString method. Helps in printing the node value on screen
    @Override
    public String toString()
    {
        return "(" + x + "," + y + ")"; 
    }

    // Overriding equals method. Helps to compare 2 node objects.
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if(this.weight != other.weight) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode()
    {
        return 13 * x + 17 * y;
    }
}
