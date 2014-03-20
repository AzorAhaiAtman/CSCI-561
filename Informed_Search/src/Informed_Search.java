import java.util.*;

public class Informed_Search 
{

    static int[][] indicatorMatrix = { {1,0,1,0,0,0,0,0,0,0,0,0},
                                    {0,1,0,0,0,0,0,1,0,0,0,0},
                                    {0,0,0,1,0,0,0,1,0,0,0,0},
                                    {0,0,1,0,1,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,1,0,0,1,0,0,0},
                                    {0,0,0,1,0,0,1,0,0,0,0,0},
                                    {1,1,0,0,0,0,0,0,0,0,0,0},
                                    {0,0,1,0,0,0,1,0,0,0,0,0},
                                    {0,0,0,1,1,0,0,0,0,0,0,0},
                                    {0,1,0,0,0,1,0,0,0,0,0,0},
                                    {0,0,0,0,1,0,0,0,1,0,0,0},
                                    {0,0,0,1,0,0,1,0,0,0,0,0},
                                    {1,0,1,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,1,0,0,0,1,0,0},
                                    {0,1,0,0,1,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,1,0,1,0,0,0},
                                    {0,0,1,0,0,1,0,0,0,0,0,0},
                                    {0,0,0,1,1,0,0,0,0,0,0,0},
                                    {0,1,0,0,0,0,0,1,0,0,0,0},
                                    {1,0,1,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,1,0,1,0,0},
                                    {1,0,0,1,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,1,0,0,0,1,0},
                                    {1,0,1,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,0,0},
                                    {1,1,0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,1,0,0,1},
                                    {0,0,0,0,0,0,1,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,1,1},
                                    {0,0,0,0,0,0,0,0,0,1,0,1} };
        
    
    public static void main(String[] args) 
    {
        
        //constant t0 for dissimilarity calculations
        int t0 = 5;
        
        //Integer values indicating start and goal nodes
        int start, goal;

        //12 X 12 Matrix to store dissimilarity values of 2 neighbouring movies mi and mj
        int[][] d = new int[12][12];
        
        //A heuristic dissimilarity matrix showing dissimilarity values between mi and mj where mj = 12
        int[] hd = {12,10,9,10,7,6,7,6,4,4,3,0};        
                        
        //Movies are stored in an array of Movie Nodes
        Movie_Node[] movies = new Movie_Node[12];
        
        //A frontier queue to place Nodes that are to be explored in order
        Queue<Movie_Node> frontier = new LinkedList<Movie_Node>();
        
        //An explored queue that contains nodes that have already been explored
        Queue<Movie_Node> explored = new LinkedList<Movie_Node>();
        
        //A Double ended queue to traverse the path from the goal node back to start node
        Deque<Movie_Node> path = new LinkedList<Movie_Node>();
                
                                   
        // Initialize values in 12 X 12 dissimilarity matrix to be 0
        for(int i = 0; i < 12; i++)
        {
            for(int j = 0; j < 12; j++)
            {
                d[i][j] = 0;
            }
        }
        
        // Calculating the d[mi][mj] values of a dissimilarity matrix
        for(int u = 0; u < 30; u++){
            for(int i = 0; i < 12; i++){
                for(int j = i + 1; j < 12; j++){
                    if(indicatorMatrix[u][i] == 1 && indicatorMatrix[u][j] == 1){
                        if(d[i][j] == 0 &&  d[j][i] == 0){
                            d[i][j] = d[j][i] = ((i+j+2) % t0 + 1) * (t0 - dot(i,j));                    
                        }
                        
                    }                    
                }
            }
        }
                
        //Assign index value for eaxh movie and set its heuristic cost from hd matrix
        //Movies are assigned index value of i+1 because array indices range from 0 to 11
        for(int i = 0; i < 12; i++)
        {
            movies[i] = new Movie_Node(i+1,hd[i]);            
        }
        
        
        //Set neighbours of each movie node by looking at values of the indicator matrix
        //If the value of d[mi][mj] is greater than 0, then add mj to the list of neighbour nodes of mi
        for(int i = 0; i < 12; i++)
        {                        
            for(int j = 0; j < 12; j++)
            {
                if(d[i][j] != 0)
                {                    
                    movies[i].setNeighbours(movies[j]);                    
                }                
            }            
        }
                
        start = 1;
        goal = 12;
        
        Informed_Search.best_first_search(movies,frontier,explored,path,d,start,goal);
        System.out.println("");
        Informed_Search.astar_search(movies,frontier,explored,path,d,start,goal);                                 
        
    }
    
    //Method to calculate dot product of vectors mi and mj
    public static int dot(int i, int j)
    {
        int dotproduct = 0;
        int row;
        
        for(row = 0; row < 30; row++)
        {           
           dotproduct = dotproduct + (indicatorMatrix[row][i]) * (indicatorMatrix[row][j]);            
        }
        return dotproduct;
    }
        
    public static void astar_search(Movie_Node[] m, Queue<Movie_Node> f, Queue<Movie_Node> e, Deque<Movie_Node> p, int[][] d, int start, int goal)
    {
      
        //clear frontier, explored and path queues first
        f.clear();
        e.clear();
        p.clear();
        
        //set start and goal nodes
        m[start-1].setStartNode(true);
        m[goal-1].setGoalNode(true);
        System.out.println("A* Search");
        
        //add start node to the frontier
        f.add(m[start-1]);
        
        //Loop while frontier is not empty
        while(!f.isEmpty())
        {
            //Remove the best node from the frontier, sorted according to an evaluation function and add it to explored
            Movie_Node current = f.remove();           
            e.add(current);
            
            //If the current node is the goal node, then print the traversal sequence, dissimilarity value and path
            if(current.isGoalNode())
            {
                System.out.println("Traversal Sequence");
                System.out.println(e);
                System.out.println("Dissimilarity");
                System.out.println(m[goal-1].getPathCost());
                Movie_Node temp = m[goal-1];
                while(temp.getParent() != null)
                {
                    p.addFirst(temp);
                    temp = temp.getParent();
                }
                p.addFirst(m[start-1]);
                System.out.println("Propogating Path");
                System.out.println(p);
                break;
            }
            
            //Generate successors of current node and evaluate them
            List<Movie_Node> successors = current.getNeighbours();
            Iterator<Movie_Node> iter = successors.listIterator();
            while(iter.hasNext())
            {
                Movie_Node temp = iter.next();
                if(!e.contains(temp))
                {
                    //If the node is not in frontier, then set its parent to current and add it to frontier
                    if(!f.contains(temp))
                    {
                        temp.setParent(current);
                        temp.setPathCost(d[temp.getParent().getMovieIndex() - 1][temp.getMovieIndex() - 1]);
                        f.add(temp);                       
                    }
                    //Else if the node is in the frontier and the current path cost + heuristic cost is less than the previous path cost + heuristic cost, replace the previous node with current.
                    else if(f.contains(temp)) 
                    {                        
                        if(current.getPathCost() + d[current.getMovieIndex() - 1][temp.getMovieIndex() - 1] < temp.getPathCost())
                        {                            
                            f.remove(temp);                            
                            temp.setParent(current);
                            temp.setPathCost(d[temp.getParent().getMovieIndex() - 1][temp.getMovieIndex() - 1]);
                            f.add(temp);                            
                            
                        }
                    }
                }
            }
            
            //The sorted set sorts the nodes according to the evaluation function, i.e path cost + heuristic cost. Priorities are broken by choosing movies with smaller movie indices.
            SortedSet<Movie_Node> sorter = new TreeSet<Movie_Node>(new
                    Comparator<Movie_Node>()
               {
                   @Override
                   public int compare(Movie_Node a, Movie_Node b)
                   {
                       if(a.getPathCost() + a.getHeuristicCost() == b.getPathCost() + b.getHeuristicCost())
                       {
                           return a.getMovieIndex() - b.getMovieIndex();                           
                       }
                       
                       return ((a.getPathCost() + a.getHeuristicCost()) - (b.getPathCost() + b.getHeuristicCost()));
                   }
               });
                        
            sorter.addAll(f);
            f.removeAll(sorter);
            f.addAll(sorter);
        }
    }
    
    public static void best_first_search(Movie_Node[] m, Queue<Movie_Node> f, Queue<Movie_Node> e, Deque<Movie_Node> p, int[][] d, int start, int goal)
    {
        //clear frontier, explored and path queues first
        f.clear();
        e.clear();
        p.clear();
                
        //set start and goal nodes
        m[start-1].setStartNode(true);
        m[goal-1].setGoalNode(true);
        System.out.println("Greedy Best First Search");
        
        //add start node to the frontier
        f.add(m[start-1]);
        
        //Loop while frontier is not empty
        while(!f.isEmpty())
        {
            //Remove the best node from the frontier, sorted according to an evaluation function and add it to explored
            Movie_Node current = f.remove();           
            e.add(current);
            
            //If the current node is the goal node, then print the traversal sequence, dissimilarity value and path
            if(current.isGoalNode())
            {
                System.out.println("Traversal Sequence");
                System.out.println(e);
                System.out.println("Dissimilarity");
                System.out.println(m[goal-1].getPathCost());
                Movie_Node temp = m[goal-1];
                while(temp.getParent() != null)
                {
                    p.addFirst(temp);
                    temp = temp.getParent();
                }
                p.addFirst(m[start-1]);
                System.out.println("Propogating Path");
                System.out.println(p);
                break;
            }
            
            //Generate successors of current node and evaluate them
            List<Movie_Node> successors = current.getNeighbours();
            Iterator<Movie_Node> iter = successors.listIterator();
            while(iter.hasNext())
            {
                Movie_Node temp = iter.next();
                if(!e.contains(temp))
                {
                    //If the node is not in frontier, then set its parent to current and add it to frontier
                    if(!f.contains(temp))
                    {
                        temp.setParent(current);
                        temp.setPathCost(d[temp.getParent().getMovieIndex() - 1][temp.getMovieIndex() - 1]);
                        f.add(temp);                       
                    }
                    
                }
            }
            
            //The sorted set sorts the nodes according to the evaluation function, i.e heuristic cost. Priorities are broken by choosing movies with smaller movie indices.
            SortedSet<Movie_Node> sorter = new TreeSet<Movie_Node>(new
                    Comparator<Movie_Node>()
               {
                   @Override
                   public int compare(Movie_Node a, Movie_Node b)
                   {
                     if(a.getHeuristicCost() == b.getHeuristicCost())
                     {
                         return a.getMovieIndex() - b.getMovieIndex();
                     }
                     return a.getHeuristicCost() - b.getHeuristicCost();
                   }
               });
            
            sorter.addAll(f);
            f.removeAll(sorter);
            f.addAll(sorter);
            
        }
    }
}
   