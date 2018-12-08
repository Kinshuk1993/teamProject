/**
 * University of Glasgow
 * MSc CS+ Team Project, fall 2018
 * 
 * Skeleton for an application class.
 * has an id, and the name of the App
 */

public class Apps{

    private String name;
    private String id;

    // constructor
    public Apps(){
        this.name = "DefaultName";
        this.id = "";
    }
    // constructor with name and id
    public Apps(String name, String id){
        this.name = name;
        this.id = id;
    }

    // setters
    public void setName(String newName){
        this.name = newName;
    }

    // getters
    public String getName(){
        return this.name;
    }
    public String getId(){
        return this.id;
    }
}