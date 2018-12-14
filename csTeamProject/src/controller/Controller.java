package controller;

/** 
 * University of Glasgow
 * MSc CS+ Team Project, fall 2018
 * 
 * Controller for the mathematical backend of a bigraph application
 * Works as an overlay to the Apps, Area, and Node class to makes sure each interaction is correctly done.
 * 
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Controller{

    //name of scene, the controller is the base scene where everything happens
    private String sceneName;

    //list of links
    private static ArrayList<String> listOfLinks = new ArrayList<>();
    //list of application
    private static ArrayList<Apps> listOfApps = new ArrayList<Apps>();
    //list of Areas
    private ArrayList<Area> listOfAreas;        //all areas
    private ArrayList<Area> topLevelAreas;      //areas on the scene
    private ArrayList<Area> innerAreas;         //areas within another area
    //list of Nodes
    private static ArrayList<Node> listOfNodes;

    // constructor
    public Controller(){
        this.sceneName = "DefaultName";
        Controller.listOfLinks = new ArrayList<String>();
        Controller.listOfApps = new ArrayList<Apps>();
        this.listOfAreas = new ArrayList<Area>();
        this.topLevelAreas = new ArrayList<Area>();
        this.innerAreas = new ArrayList<Area>();
        Controller.listOfNodes = new ArrayList<Node>();
    }
    // named constructor (preferred)
    public Controller(String name){
        this.sceneName = name;
        Controller.listOfLinks = new ArrayList<String>();
        Controller.listOfApps = new ArrayList<Apps>();
        this.listOfAreas = new ArrayList<Area>();
        this.topLevelAreas = new ArrayList<Area>();
        this.innerAreas = new ArrayList<Area>();
        Controller.listOfNodes = new ArrayList<Node>();
    }

    // get the name of the scene
    public String getSceneName(){
        return this.sceneName;
    }
    // rename the scene
    public void setSceneName(String newName){
        this.sceneName = newName;
    }

    // get list of Apps 
    public static ArrayList<Apps> getApps(){
        return Controller.listOfApps;
    }
    // get list of Links
    public static ArrayList<String> getLinks(){
        return Controller.listOfLinks;
    }
    // get list of Nodes
    public static ArrayList<Node> getNodes(){
        return Controller.listOfNodes;
    }
    // get list of Areas
    public ArrayList<Area> getAreas(){
        return this.listOfAreas;
    }
    // get list of topAreas
    public ArrayList<Area> getTopLvlAreas(){
        return this.topLevelAreas;
    }
    // get list of innerAreas
    public ArrayList<Area> getInnerAreas(){
        return this.innerAreas;
    }

    // ---------------- Area ----------------------------
    /**
     * Add top level Area
     * takes the name of the new area, creates an area and then add it to
     * the topLevelAras list and the listOfAreas.
     *  
     * @param name
     * @return newArea.getId()
     */
    public String addTopArea(String name){
        Area newArea = new Area(name);
        this.listOfAreas.add(newArea);
        this.topLevelAreas.add(newArea);

        return newArea.getId();

    }
    /**
     * Add inner area
     * takes the name of the newArea and the area it is places inside
     * finds the parent area in the list of parents, and then creates a newArea
     * then add the newArea to the parent.
     * 
     * @param parent
     * @param newId
     * @return newAra.getId()
     */ 
    public String addInnerArea(String parent, String newId){
        // find the parent Area
        Area parentArea = findArea(parent);

        // add new area to parent area
        Area newArea = new Area(newId);
        parentArea.addArea(newArea);
        this.listOfAreas.add(newArea);
        this.innerAreas.add(newArea);

        return newArea.getId();
    }

    // ---------------- Node ----------------------------

    // Add new node
    /**
     * Takes the name of the area the node is placed in to find the area in the list.
     * takes the paramaters of each configuration a node can have.
     * 
     * @param areaName
     * @param temperature
     * @param windspeed
     * @param humidity
     * @param vibration
     * @param pressure
     * @param appName
     * @return the id of the node
     */
    public String addNodeToArea(String areaName, boolean temperature, boolean windspeed, boolean humidity, boolean vibration, boolean pressure, double X, double Y){
        // find the area
        Area areaToAddTo = findArea(areaName); 

        // Create nodeId
        String nodeId = "{c"+(listOfNodes.size()+1)+"}";
        // create new node
        Node newNode = new Node(nodeId);
        //set the node X coordinate
        newNode.setxCoord(X);
        //set the node Y coordinate
        newNode.setyCoord(Y);
        // set nodes configurations
        newNode.setAllConf(temperature, windspeed, humidity, vibration, pressure);

        Controller.listOfNodes.add(newNode);
        
        // add node to area
        areaToAddTo.addNode(newNode);

        //return node name
        return nodeId;

    }
    // change config of node
    /**
     * takes in the name of a node and finds it in the list of nodes.
     * then we cange the configurations based on the given paramaters.
     * 
     * @param nodeName
     * @param temperature
     * @param windspeed
     * @param humidity
     * @param vibration
     * @param pressure
     */
    public void addConfigToNode(String nodeName, boolean temperature, boolean windspeed, boolean humidity, boolean vibration, boolean pressure){
        // find node
        Node newNode = findNode(nodeName);
        // set nodes configurations
        newNode.setAllConf(temperature, windspeed, humidity, vibration, pressure);
        
    }
    
    /**
     * Takes node id and appName and finds both of them in their lists, before adding the app to the node
     * 
     * @param appName
     * @param nodeId
     */
    public static void addAppToNode(String appName, String nodeId){
        Node findNode = findNode(nodeId);
        Apps findApp = findApp(appName);

        findNode.addApp(findApp);
    }


    // ---------------- Apps ----------------------------
    /**
     * takes the name of the application you want to create, generate and automatic id and creates the new application.
     *  
     * @param appName
     * @return generated appID
     */
    public static String newApp(String appName){
        String appID = "A(" + (Controller.listOfApps.size()+1)+")";
        Apps  newApp = new Apps(appName, appID);
        Controller.listOfApps.add(newApp);
        return appID;
    }


    // ---------------- Links ----------------------------
    // add new link
    /**
     * takes the names of the nodes to be connected and find them in the list of nodes
     * adds the link to the two nodes
     * generate the linkId
     * 
     * @param firstNodeName
     * @param secondNodeName
     * @return linkId
     */
    public static String addNewLink(String firstNodeName, String secondNodeName){
        // create new link
        String linkName = "l"+(Controller.listOfLinks.size()+1);
        Controller.listOfLinks.add(linkName);

        // find the nodes   
        Node firstNode = findNode(firstNodeName);
        Node secondNode = findNode(secondNodeName);

        firstNode.addLink(linkName);
        firstNode.setNodesLinkedTo(secondNodeName);
        secondNode.addLink(linkName);
        secondNode.setNodesLinkedTo(firstNodeName);

        return linkName;

    }


    
    // ---------------- Print out ----------------------------
    /**
     * Put together a string that represents the bigrap algebraic expression
     * @return bigraph expression
     */
    public String exportBigraph(){

        // set up the function
        String finalFunc = "";

        // add links in the beginning
        finalFunc = finalFunc+printLinks();
        finalFunc = finalFunc+"(";

        // print the areas and the nodes within them
        finalFunc = finalFunc + printAreas();

        // prepare to show configs
        finalFunc = finalFunc + "||";
        
        // add configuration of nodes
        if(!Controller.listOfNodes.isEmpty()){
            finalFunc = finalFunc + printNodesConf();
        }
        
        // add ending bracket
        finalFunc = finalFunc+")";

        return finalFunc;
    }

    // return a string with all Node fonfigurations
    /**
     * loopes through the list of nodes and print the configurations of each node
     * 
     * @return string with nodeconfigurations
     */
    public String printNodesConf(){
        String toRet = "(";

        for (int i = 0; i < Controller.listOfNodes.size(); i++){
            if (i == Controller.listOfNodes.size()-1){
                toRet = toRet + Controller.listOfNodes.get(i).printNodeConf();    
            }
            else{
                toRet = toRet + Controller.listOfNodes.get(i).printNodeConf() + "\n\t\t\t\t| ";
            }
        }

        toRet = toRet+")";

        return toRet;
    }  

    // print out link expression
    /**
     * loops through the list of links and print out each one
     * @return /l1/ln... and so fort
     */
    private String printLinks(){
        String toReturn = "/";

        for(int i = 0; i < Controller.listOfLinks.size(); i++){
            toReturn = toReturn + Controller.listOfLinks.get(i) + "/";
        }

        if(toReturn.substring(toReturn.length() - 1).equals("/")){
            toReturn = toReturn.substring(0,toReturn.length()-1);
        }

        return toReturn;
    }
    

    // print out Areas
    /**
     * loops through the list of top level areas and calls the printArea Function from each top area
     * this nestes through all areas in the scene. By the recursive printArea function in the area class.
     * 
     * @return string with all area and node information
     */
    private String printAreas(){
        String toRet = "";

        for(int i = 0; i < this.topLevelAreas.size(); i++){
            if(i == this.topLevelAreas.size()-1){
                toRet = toRet + " " + this.topLevelAreas.get(i).printArea();
            }
            else if(i == 0 && !(i == this.topLevelAreas.size()-1)){
                toRet = toRet + this.topLevelAreas.get(i).printArea()+ " |";
            }
            else{
                toRet = toRet + " " + this.topLevelAreas.get(i).printArea()+ " |";
            }
        }
        // if(toRet.substring(toRet.length() - 1).equals("|")){
        //     toRet = toRet.substring(0,toRet.length()-1);
        // }

        return toRet;
    }

    /**
     * loopes through all applications and print them
     * 
     * @return string of all aps
     */
    private String printApps(){
        String toRet = "(";

        for(int i = 0; i < Controller.listOfApps.size();i++){
            if(i == Controller.listOfApps.size()-1){
                toRet = toRet + "App(" + (i+1) + ")." + "(" + Controller.listOfApps.get(i).getId() + ")";
            }
            else{
                toRet = toRet + "App(" + (i+1) + ")." + "(" + Controller.listOfApps.get(i).getId() + ")" + "\n\t\t\t\t| ";
            }
        }

        toRet = toRet + ")";

        return toRet;

    }

    /**
     * print each id of every node in this scene
     * 
     * @return /c1/c2... etc
     */
    private String printNodes(){
        String toRet = "/";

        for(int i = 0; i < Controller.listOfNodes.size();i++){
            toRet = toRet + Controller.listOfNodes.get(i).getId().substring(1, Controller.listOfNodes.get(i).getId().length()-1) + "/";
        }
        if(toRet.substring(toRet.length() - 1).equals("|")){
            toRet = toRet.substring(0,toRet.length()-1);
        }

        return toRet;

    }


    // ---------------- Export BIG file ----------------------------

    /**
     * This method writs out a model.big file.
     * When called it builds a string by calling the print functions above.
     * Then writes the string to the model.bif file.
     */
    public void exportBIG(){

        try {
            // creates the writer
            BufferedWriter writer = new BufferedWriter(new FileWriter("model.big"));

            // Everything that is unchangable in the bigfile
            String toWrite = "# Signature\n\n# Node types\n";
            toWrite = toWrite + "ctrl N = 1;                 # Idle node\nctrl N_U = 1;               # Node in use\natomic ctrl N_F = 1;        # Node with failure\nctrl L = 0;                 # Links\natomic ctrl L_E = 1;        # Link end\n\n";
            toWrite = toWrite + "fun ctrl App(x) = 0;        # Application\natomic fun ctrl A(x) = 0;   # Application token\n\n";
            toWrite = toWrite + "# Node configuration\nctrl Conf = 1;\n\n";
            toWrite = toWrite + "# Node configuration values\natomic fun ctrl MAC(x) = 0;\natomic fun ctrl IPv6(x) = 0;\natomic ctrl T = 0;\natomic ctrl H = 0;\natomic ctrl V = 0;\natomic ctrl P = 0;\natomic ctrl W = 0;\n\n";

            // All the areas in this scene
            toWrite = toWrite + "# Topology\n";
            for(int i = 0; i < this.listOfAreas.size();i++){
                toWrite = toWrite +"ctrl " + this.listOfAreas.get(i).getId() + " = 0;\n";
            }
            // Adds the different perspectives (unchangable)
            toWrite = toWrite + "\n# Perspectives\nctrl PHY = 0;\nctrl DATA = 0;\nctrl CONF = 0;\nctrl SERVICE = 0;\n\n";

            // write PHYSICAL State
            toWrite = toWrite + "# Current state\nbig s0_P = \n  "+printLinks()+"\n\t";
            toWrite = toWrite + "PHY.("+printAreas()+");\n\n";

            // write DATA state
            toWrite = toWrite + "big s0_D = ";
            toWrite = toWrite + "DATA."+printNodesConf()+";\n\n";

            // write SERVICE stat
            toWrite = toWrite + "big s0_S = ";
            toWrite = toWrite + "SERVICE."+printApps()+";\n\n";

            // write NODES stat
            toWrite = toWrite + "big s0 = \n  ";
            toWrite = toWrite + printNodes()+"\n\t(s0_P || s0_D || s0_S);";

            // Write out the file
            writer.write(toWrite);

            // flush and close the writer
            writer.flush();
            writer.close();

        } catch (IOException e){
            // catch possibe IOException
            e.printStackTrace();
        }

    }


    // ---------------- Private helpers ----------------------------
    // These helpers are for internal use to find the Node, Area, and App based on a given name.

    // find node in list of nodes
    public static Node findNode(String nodeName){
        Node toReturn = new Node();
        // find Node
        for(int i = 0; i < Controller.listOfNodes.size(); i++){
            if(listOfNodes.get(i).getId().equals(nodeName)){
                toReturn = Controller.listOfNodes.get(i);
            }
        }
        return toReturn;
    }

    // find area in list of areas
    private Area findArea(String areaName){
        Area toReturn = new Area();
        // find Area
        for(int i = 0; i < this.listOfAreas.size(); i++){
            if(listOfAreas.get(i).getId().equals(areaName)){
                toReturn = this.listOfAreas.get(i);
            }
        }
        return toReturn;
    }

    // find app in list of apps
    private static Apps findApp(String appName){
        Apps toReturn = new Apps();
        // find App
        for(int i = 0; i < Controller.listOfApps.size(); i++){
            if(listOfApps.get(i).getId().equals(appName)){
                toReturn = Controller.listOfApps.get(i);
            }
        }
        return toReturn;
    }

}