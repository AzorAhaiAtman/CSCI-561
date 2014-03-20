import java.util.*;

public class Uninformed_Search {
    
    // Frontier to hold nodes that are to be explored for BFS and DFS
    static Deque<Node> frontier = new LinkedList<Node>();
    
    // Frontier to sort and hold nodes that are to be explored for UCS
    static PriorityQueue<Node> pqueue= new PriorityQueue<Node>();
    
    // Queue to store nodes in traversal order
    static Queue<Node> explored = new LinkedList<Node>();                        
    
    // Double Ended Queue to store nodes in stitching curve from start to goal
    static Deque<Node> path = new LinkedList<Node>();
    
                  
    // A 5 X 8 Node array holding 40 nodes
    static Node nodeArray[][] = new Node[5][8];
    
    public static void main(String[] args) {
        
        // Reading input fron user
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Menu\n1.Breadth First Search\n2.Depth First Search\n3.Uniform Cost Search\n");
        System.out.println("Enter Choice: ");
        
        int choice = scan.nextInt();

        // Initializing Nodes locations, weights, neighbours etc. 
        
        initNodes();
        
        // Initializing Start and Goal Nodes
        Node start = nodeArray[1][0];        
        Node goal = nodeArray[3][7];        
        
        // switch case to choose among the 3 search algorithms
        switch(choice)
        {
            case 1:{
            if(bfs(start)){
                
                System.out.println("Breadth First Search");
                System.out.println("Source  :" + start);
                System.out.println("Goal    :" + goal);
                System.out.println("\nTraversal Order");
                System.out.print(explored);        
                
                System.out.println("\n\nPathcost from start to goal:" + Integer.toString(goal.getPathCost())); 
                while(goal.getParent() != null ){            
                    path.addFirst(goal);
                    Node temp = goal.getParent();
                    goal = temp;        
                }
                path.addFirst(start);
                
                System.out.println("\nStitching Curve");
                System.out.println(path);

                break;
            }
            else{
                System.out.println("No solution obtained");
                break;
            }
                
        }
            
            
            case 2:{                
                
                if(dfs(start)){
                    System.out.println("Depth First Search");      
                    System.out.println("Source  :" + start);
                    System.out.println("Goal    :" + goal);                
                    System.out.println("\nTraversal Order");
                    System.out.print(explored);
                    
                    System.out.println("\n\nPathcost from start to goal:" + Integer.toString(goal.getPathCost())); 
                    
                    while(goal.getParent() != null ){            
                        path.addFirst(goal);
                        Node temp = goal.getParent();
                        goal = temp;        
                    }
                    path.addFirst(start);
                    System.out.println("\nStitching Curve");
                    System.out.println(path);
                    
                    break;
                }
                else{
                    System.out.println("No solution obtained");
                    break;
                }                    
            }
            
            case 3:{
                if(ucs(start)){
                
                    System.out.println("\nUniform Cost Search Traversal");
                    System.out.println("Source  :" + start);
                    System.out.println("Goal    :" + goal);                
                    System.out.println("\nTraversal Order");                    
                    System.out.print(explored);        
                    
                    System.out.println("\nPathcost from start to goal:" + Integer.toString(goal.getPathCost())); 

                    while(goal.getParent() != null){
                        path.addFirst(goal);
                        Node temp = goal.getParent();
                        goal = temp;
                    }
                    path.addFirst(start);
                    
                    System.out.println("\nStitching Curve");
                    System.out.println(path);
                    
                    break;
                }
                else{
                    System.out.println("No solution obtained");
                    break;
                }   
            }
            
            default:{
                System.out.println("Wrong choice. Choose between 1,2 and 3. Applicaiton will exit");
            }
                
                
        }
        
    }        
    
    // method that calculates the breadth first traversal of the 5 X 8 grid from start to goal
    public static boolean bfs(Node s){        
                        
        frontier.add(s);
       
        while(!frontier.isEmpty()){
            Node current = frontier.remove();
            explored.add(current);
            if(current.isGoalNode()){
                return true;
            }
            //For each successor of current node
            for(Node i : current.getSuccessors()){                                 
                if(!explored.contains(i) && !frontier.contains(i)){                                     
                            
                    i.setParent(current);
                    i.setPathCost();                            
                    frontier.add(i);                                                                            

                }               
            }
        }
        return false;
                   
    }
    
    // method that calculates depth first traversal of the 5 X 8 grid from start to goal
     public static boolean dfs(Node s){        
                        
        frontier.push(s);
       
        while(!frontier.isEmpty()){
      
            Node current = frontier.pop();              
            explored.add(current);
              
            if(current.isGoalNode()){
                return true;
            }
              
            Node[] successors = current.getSuccessors();
            // For each successor of current node
            for(int i = successors.length - 1; i >= 0; i--){                                 
                if(!explored.contains(successors[i])){
                    successors[i].setParent(current);
                    successors[i].setPathCost();
                    frontier.push(successors[i]);                   
                }               
            }
        }               
        return true;
    }
    
     // method that calculate the uniform cost traversal of the 5 X 8 grid from start to goal
     public static boolean ucs(Node s){        
                
        pqueue.add(s);
        
        while(!pqueue.isEmpty()){
              Node current = pqueue.remove();              
              
              if(current.isGoalNode()){                    
                  explored.add(current);  
                  return true;
              }
                      
        
              Node[] i = current.getSuccessors();
              // For each successor of current node
              for(int j = i.length - 1; j >= 0; j--){                                 
                                        
                    if(!explored.contains(i[j]) && !pqueue.contains(i[j])){                                                                                                    
                        i[j].setParent(current);              
                        current.setChild(i[j]);
                        i[j].setPathCost();
                        i[j].setDepth();                                            
                        pqueue.add(i[j]);                            
                    }
                    else if(pqueue.contains(i[j])){                                                        
                                if(current.getPathCost() + i[j].getWeight() <= i[j].getPathCost()){
                                    i[j].setParent(current);              
                                    current.setChild(i[j]);
                                    i[j].setPathCost();
                                    i[j].setDepth();                                            
                        }
                        
                   } 
                    else if(explored.contains(i)){                                                        
                                if(current.getPathCost() + i[j].getWeight()  <= i[j].getPathCost()){

                                    i[j].setParent(current);              
                                    current.setChild(i[j]);
                                    i[j].setPathCost();
                                    i[j].setDepth();                        
                   
                        }
        
                    }
                }                        
              
              explored.add(current);      
              
        }                             
        
        return false;
    }
       
                   
    public static void initNodes()
    {
       // Initializing nodes in the 5 X 8 Grid
        
        nodeArray[0][0] = new Node(1,1,12);
        nodeArray[1][0] = new Node(2,1,16);
        nodeArray[2][0] = new Node(3,1,20);
        nodeArray[3][0] = new Node(4,1,19);
        nodeArray[4][0] = new Node(5,1,18);
        
        nodeArray[0][1] = new Node(1,2,11);
        nodeArray[1][1] = new Node(2,2,17);
        nodeArray[2][1] = new Node(3,2,10);
        nodeArray[3][1] = new Node(4,2,20);
        nodeArray[4][1] = new Node(5,2,15);
        
        nodeArray[0][2] = new Node(1,3,17);
        nodeArray[1][2] = new Node(2,3,9);
        nodeArray[2][2] = new Node(3,3,14);
        nodeArray[3][2] = new Node(4,3,13);
        nodeArray[4][2] = new Node(5,3,19);
        
        nodeArray[0][3] = new Node(1,4,16);
        nodeArray[1][3] = new Node(2,4,15);
        nodeArray[2][3] = new Node(3,4,8);
        nodeArray[3][3] = new Node(4,4,7);
        nodeArray[4][3] = new Node(5,4,10);
        
        nodeArray[0][4] = new Node(1,5,7);
        nodeArray[1][4] = new Node(2,5,15);
        nodeArray[2][4] = new Node(3,5,17);
        nodeArray[3][4] = new Node(4,5,6);
        nodeArray[4][4] = new Node(5,5,8);
        
        nodeArray[0][5] = new Node(1,6,13);
        nodeArray[1][5] = new Node(2,6,7);
        nodeArray[2][5] = new Node(3,6,9);
        nodeArray[3][5] = new Node(4,6,9);
        nodeArray[4][5] = new Node(5,6,9);
        
        nodeArray[0][6] = new Node(1,7,12);
        nodeArray[1][6] = new Node(2,7,6);
        nodeArray[2][6] = new Node(3,7,13);
        nodeArray[3][6] = new Node(4,7,15);
        nodeArray[4][6] = new Node(5,7,14);
        
        nodeArray[0][7] = new Node(1,8,15);
        nodeArray[1][7] = new Node(2,8,12);
        nodeArray[2][7] = new Node(3,8,16);
        nodeArray[3][7] = new Node(4,8,9);
        nodeArray[4][7] = new Node(5,8,12);
        
        
        // Setting the start node, goal node and initializing the pathcost of start node
        nodeArray[1][0].setStartNode(true);
        nodeArray[3][7].setGoalNode(true);
        
        nodeArray[1][0].setPathCost();      
        
        
        // Creates links (if any) between a node and its neighbours (east, north, west and south)
        for (int i = 0; i < 5; i++){
            for(int j = 0; j < 8; j++){
                // If no node beyond x = 5 i.e Neighbour on East
                if(i+1 >= 5){
                    nodeArray[i][j].setEast(null);                    
                }
                else{
                    nodeArray[i][j].setEast(nodeArray[i+1][j]);
                }
                                
                // If no node beyond x = 0 i.e Neighbour on West
                if(i-1 < 0){
                    nodeArray[i][j].setWest(null);
                }                
                else{
                    nodeArray[i][j].setWest(nodeArray[i-1][j]);
                }
                
                // If no node beyond y = 0 i.e Neighbour on North
                if(j-1 < 0){
                    nodeArray[i][j].setNorth(null);
                }
                else{
                    nodeArray[i][j].setNorth(nodeArray[i][j-1]);                    
                }
                
                // If no node beyond y = 8 i.e Neighbour on South
                if(j+1 >=8){
                    nodeArray[i][j].setSouth(null);
                }
                else{
                    nodeArray[i][j].setSouth(nodeArray[i][j+1]);
                }
            }
        }
        
    }
}
