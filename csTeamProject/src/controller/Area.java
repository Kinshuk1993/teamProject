package controller;

/**
 * University of Glasgow
 * MSc CS+ Team Project, fall 2018
 * 
 * Area class has a string id.
 * Area can also contain another area, if it does then the boolean containsArea is set to true;
 * It can also contain nodes.
 * 
 */
import java.util.ArrayList;

public class Area {

    // Id is name of the area
    private String id;
    private boolean containsArea;

    // these list contains the nodes and areas contained within this area
    private ArrayList<Node> nodes;
    private ArrayList<Area> areas;

    // Default constructor
    public Area(){
        this.id = "DeafaultName";
        this.nodes = new ArrayList<Node>();
        this.areas = new ArrayList<Area>();
    }
    // Constructor with given name
    public Area(String id){
        this.id = id;
        this.nodes = new ArrayList<Node>();
        this.areas = new ArrayList<Area>();
    }

    // Sett a new Id
    public void setId(String newId){
        this.id = newId;
    }
    
    // getId
    public String getId(){
        return this.id;
    }
    // check if this area contains an area
    public boolean hasArea(){
        return this.containsArea;
    }
    // get all nodes in this area
    public ArrayList<Node> getNodes(){
        return this.nodes;
    }
    // get all Areas in this area
    public ArrayList<Area> getAreas(){
        return this.areas;
    }

    // add new node to the area
    public void addNode(Node newNode){
        this.nodes.add(newNode);
    }
    // add new area to the area
    public void addArea(Area newArea){
        // set the containsArea boolean to true
        this.containsArea = true;
        this.areas.add(newArea);
    }    

    // remove given node
    public void removeNode(Node node){
        int index = this.nodes.indexOf(node);
        if(index != -1){
            this.nodes.remove(index);
        }
    }

    // remove given area and checks if the area no longer contains other areas.
    public void removeArea(Area area){
        int index = this.areas.indexOf(area);
        if(index != -1){
            this.areas.remove(index);
        }

        if(this.areas.isEmpty()){
            this.containsArea = false;
        }
    }

    // print Area and its content
    public String printArea(){
        String toRet = this.id + ".(";

        // check if this area has an area, if it does then call that areas printArea method to build the string. 
        if(this.hasArea()){
            for(int i = 0; i < this.areas.size(); i++){
                toRet = toRet + this.areas.get(i).printArea() + " | ";
            }
        }

        // call each node in the list of nodes printAppsAndLinks method to print out all the apps and links for each node.
        if(!this.nodes.isEmpty()){
            for(int i = 0; i < this.nodes.size(); i++){
                if(i == 0){
                    toRet = toRet + this.nodes.get(i).printAppsAndLinks() + " |";
                }
                else if(i == this.nodes.size()-1){
                    toRet = toRet + " " + this.nodes.get(i).printAppsAndLinks();
                }
                else {
                    toRet = toRet + " " + this.nodes.get(i).printAppsAndLinks() + " |";
                }
            }
            
        }

        //Remove the last "|"
        if(toRet.substring(toRet.length() - 2).equals(" |")){
            toRet = toRet.substring(0,toRet.length()-2);
        }
        // Add closing bracket
        toRet = toRet + ")";

        return toRet;
    }

}