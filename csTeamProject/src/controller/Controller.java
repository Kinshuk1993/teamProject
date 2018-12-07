package controller;

/** 
 * Controller for Node, Area, Apps
 * Makes sure the interactions between the Node, Area and Apps are done in a correct manner.
 * Also keeps track of every node, area, app and link
 * 
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Controller{

    //name of scene
    private String sceneName;

    //list of links
    private ArrayList<String> listOfLinks;

    //list of application
    private ArrayList<Apps> listOfApps;

    //list of Areas
    private ArrayList<Area> listOfAreas;
    private ArrayList<Area> topLevelAreas;
    private ArrayList<Area> innerAreas;

    //list of Nodes
    private ArrayList<Node> listOfNodes;


    public Controller(){
        this.sceneName = "DefaultName";
        this.listOfLinks = new ArrayList<String>();
        this.listOfApps = new ArrayList<Apps>();
        this.listOfAreas = new ArrayList<Area>();
        this.topLevelAreas = new ArrayList<Area>();
        this.innerAreas = new ArrayList<Area>();
        this.listOfNodes = new ArrayList<Node>();
    }
    public Controller(String name){
        this.sceneName = name;
        this.listOfLinks = new ArrayList<String>();
        this.listOfApps = new ArrayList<Apps>();
        this.listOfAreas = new ArrayList<Area>();
        this.topLevelAreas = new ArrayList<Area>();
        this.innerAreas = new ArrayList<Area>();
        this.listOfNodes = new ArrayList<Node>();
    }

    public String getSceneName(){
        return this.sceneName;
    }
    public void setSceneName(String newName){
        this.sceneName = newName;
    }

    // get list of Apps 
    public  ArrayList<Apps> getApps(){
        return this.listOfApps;
    }
    // get list of Links
    public  ArrayList<String> getLinks(){
        return this.listOfLinks;
    }
    // get list of Nodes
    public ArrayList<Node> getNodes(){
        return this.listOfNodes;
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

    // Add top level Area
    public String addTopArea(String name){
        Area newArea = new Area(name);
        this.listOfAreas.add(newArea);
        this.topLevelAreas.add(newArea);

        return newArea.getId();

    }
    // Add inner Area
    public String addInnerArea(String parent, String newId){
        // find the parent Area
        Area parentArea = findArea(parent);

        // add new area to parent ara
        Area newArea = new Area(newId);
        parentArea.addArea(newArea);
        this.listOfAreas.add(newArea);
        this.innerAreas.add(newArea);

        return newArea.getId();
    }

    // ---------------- Node ----------------------------

    // Add new node
    public String addNodeToArea(String areaName, boolean temperature, boolean windspeed, boolean humidity, boolean vibration, boolean pressure, String appName){
        // find the area
        Area areaToAddTo = findArea(areaName); 

        // Create nodeId
        String nodeId = "Nu"+ (listOfNodes.size()+1);
        // create new node
        Node newNode = new Node(nodeId);
        // set nodes configurations
        newNode.setAllConf(temperature, windspeed, humidity, vibration, pressure);

        this.listOfNodes.add(newNode);

        if(!appName.isEmpty()){
            // find app
            Apps findApp = findApp(appName);
            // add app to node
            newNode.addApp(findApp);
        }
        
        // add node to area
        areaToAddTo.addNode(newNode);

        //retur node name
        return nodeId;

    }
    // change config of node
    public void addConfigToNode(String nodeName, boolean temperature, boolean windspeed, boolean humidity, boolean vibration, boolean pressure){
        // find node
        Node newNode = findNode(nodeName);
        // set nodes configurations
        newNode.setAllConf(temperature, windspeed, humidity, vibration, pressure);
        
    }
    // Add app to node
    public void addAppToNode(String appName, String nodeId){
        Node findNode = findNode(nodeId);
        Apps findApp = findApp(appName);

        findNode.addApp(findApp);
    }


    // ---------------- Apps ----------------------------
    public String newApp(String appName){
        String appID = "A" + (this.listOfApps.size()+1);
        Apps  newApp = new Apps(appName, appID);
        this.listOfApps.add(newApp);

        return appID;
    }



    // ---------------- Links ----------------------------
    // add new link
    public String addNewLink(String firstNodeName, String secondNodeName){
        // create new link
        String linkName = "l"+(this.listOfLinks.size()+1);
        this.listOfLinks.add(linkName);

        // find the nodes   
        Node firstNode = findNode(firstNodeName);
        Node secondNode = findNode(secondNodeName);

        firstNode.addLink(linkName);
        secondNode.addLink(linkName);

        return linkName;

    }


    
    // ---------------- Print out ----------------------------
    public String exportBigraph(){

        // set up the function
        String finalFunc = "";

        // add links
        finalFunc = finalFunc+printLinks();
        finalFunc = finalFunc+"(";

        // print areas and contained nodes
        finalFunc = finalFunc + printAreas();

        // add in config at the end
        finalFunc = finalFunc + "||";
        
        // add configuration of nodes
        
        if(!this.listOfNodes.isEmpty()){
            finalFunc = finalFunc + printNodesConf();
        }
        
        // add ending bracket
        finalFunc = finalFunc+")";

        return finalFunc;
    }

    // return a string with all Node fonfigurations
    public String printNodesConf(){
        String toRet = "(";

        for (int i = 0; i < this.listOfNodes.size(); i++){
            toRet = toRet + this.listOfNodes.get(i).printNodeConf() + "|";
        }
        if(toRet.substring(toRet.length() - 1).equals("|")){
            toRet = toRet.substring(0,toRet.length()-1);
        }
        toRet = toRet+")";

        return toRet;
    }  

    // print out link expression
    private String printLinks(){
        String toReturn = "/";

        for(int i = 0; i < this.listOfLinks.size(); i++){
            toReturn = toReturn + this.listOfLinks.get(i) + "/";
        }

        if(toReturn.substring(toReturn.length() - 1).equals("/")){
            toReturn = toReturn.substring(0,toReturn.length()-1);
        }

        return toReturn;
    }
    

    // print out Areas
    private String printAreas(){
        String toRet = "";

        for(int i = 0; i < this.topLevelAreas.size(); i++){
            toRet = toRet + this.topLevelAreas.get(i).printArea() + "|";
        }
        if(toRet.substring(toRet.length() - 1).equals("|")){
            toRet = toRet.substring(0,toRet.length()-1);
        }

        return toRet;
    }


    // ---------------- Export BIG file ----------------------------

    public void exportBIG(){

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("model.big"));

            // Everything that is unchangable in the bigfile
            String toWrite = "# Signature\n\n# Data types\natomic fun ctrl Int(x) = 0;\natomic fun ctrl Float(x) = 0;\natomic fun ctrl String(x) = 0;\n\n# Node types\nctrl N = 1;                 # Idle node\nctrl N_U = 1;               # Node in use\natomic ctrl N_F = 1;        # Node with failure\nctrl L = 0;                 # Links\natomic ctrl L_E = 1;        # Link end\n\nfun ctrl App(x) = 0;        # Application\natomic fun ctrl A(x) = 0;   # Application token\n\n# Node configuration\nctrl Conf = 1;\n\n# Node configuration values\natomic fun ctrl MAC(x) = 0;\natomic fun ctrl IPv6(x) = 0;\natomic ctrl IPv6_unassigned = 0;\nctrl Sensors = 0;\natomic fun ctrl Date(x) = 0;\natomic fun ctrl Temperature(x) = 0;\natomic fun ctrl Humidity(x) = 0;\natomic fun ctrl Light_level(x) = 0;\natomic fun ctrl Pressure(x) = 0;\natomic fun ctrl Energy_consumed(x) = 0;\natomic fun ctrl Energy_generated(x) = 0;\natomic fun ctrl Battery_state(x) = 0;\natomic ctrl Battery_depleted = 0;\natomic fun ctrl Max_battery(x) = 0;\n\n";

            // Construct the rest of the file


            // Write out the file
            writer.write(toWrite);


            writer.flush();
            writer.close();

        } catch (IOException e){
            e.printStackTrace();
        }

    }


    // ---------------- Private helpers ----------------------------

    // find node in list of nodes
    private Node findNode(String nodeName){
        Node toReturn = new Node();
        // find Node
        for(int i = 0; i < this.listOfNodes.size(); i++){
            if(listOfNodes.get(i).getId().equals(nodeName)){
                toReturn = this.listOfNodes.get(i);
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
    private Apps findApp(String appName){
        Apps toReturn = new Apps();
        // find App
        for(int i = 0; i < this.listOfApps.size(); i++){
            if(listOfApps.get(i).getId().equals(appName)){
                toReturn = this.listOfApps.get(i);
            }
        }

        return toReturn;
    }

}