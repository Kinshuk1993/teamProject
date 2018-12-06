package node;

public class node {
    
    private String name;
    private configBox conf;

    public node(String name, configBox conf){
        this.name = name;
        this.conf = conf;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public configBox getConf(){
        return this.conf;
    }
}
