package controller;

/**
 * Area
 * Can contain nodes and other Areas
 */
import java.util.ArrayList;

public class Area {

    // Id is name of the area
    private String id;
    private boolean containsArea;

    // these list contains the nodes and areas in this area
    private ArrayList<Node> nodes;
    private ArrayList<Area> areas;

    // Constructors
    public Area(){
        this.id = "DeafaultName";
        this.nodes = new ArrayList<Node>();
        this.areas = new ArrayList<Area>();
    }
    public Area(String id){
        this.id = id;
        this.nodes = new ArrayList<Node>();
        this.areas = new ArrayList<Area>();
    }

    // Setter
    public void setId(String newId){
        this.id = newId;
    }
    
    // getters
    public String getId(){
        return this.id;
    }
    public boolean hasArea(){
        return this.containsArea;
    }
    public ArrayList<Node> getNodes(){
        return this.nodes;
    }
    public ArrayList<Area> getAreas(){
        return this.areas;
    }

    // adders
    public void addNode(Node newNode){
        this.nodes.add(newNode);
    }
    public void addArea(Area newArea){
        this.containsArea = true;
        this.areas.add(newArea);
    }    

    // print Area and its content
    public String printArea(){
        String toRet = this.id + ".(";

        if(!this.nodes.isEmpty()){
            for(int i = 0; i < this.nodes.size(); i++){
                toRet = toRet + this.nodes.get(i).printAppsAndLinks() + "|";
            }
            
        }

        if(this.hasArea()){
            for(int i = 0; i < this.areas.size(); i++){
                toRet = toRet + this.areas.get(i).printArea() + "|";
            }
        }

        //Remove the last "|"
        if(toRet.substring(toRet.length() - 1).equals("|")){
            toRet = toRet.substring(0,toRet.length()-1);
        }
        // Add closing bracket
        toRet = toRet + ")";

        return toRet;
    }

}