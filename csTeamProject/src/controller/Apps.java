package controller;

/**
 * Application deployed to individual nodes
 */

public class Apps{

    private String name;
    private String id;

    // constructor
    public Apps(){
        this.name = "DefaultName";
        this.id = "";
    }
    public Apps(String name, String id){
        this.name = name;
        this.id = id;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public String getName(){
        return this.name;
    }
    public String getId(){
        return this.id;
    }
}