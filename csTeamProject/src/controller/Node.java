
/**
 * Node
 * Nodes, has different configurations.
 * 
 * Can contain Apps
 */
import java.util.Random;
import java.util.ArrayList;

public class Node {
    
    // Privat variables
    private String id;              // this is the id the node is known as
    private String typeId;          // this identifies what kind of node it is (N_U, N)
    private String Mac;             // Mac is needed, works as the ID
    private String ipv6;            // Only when connected to a network

    // These are indicators for what kind of data this node can provide
    private boolean Temperature;
    private boolean Windspeed;
    private boolean Humidity;
    private boolean Vibration;
    private boolean Pressure;

    // List containing links and applications
    private ArrayList<String> links;
    private ArrayList<Apps> applications;

    // Constructors
    public Node(String id){
        this.id = id;
        this.typeId = "N";
        this.Mac = findMac();
        this.ipv6 = "N/A";
        this.links = new ArrayList<String>();
        this.applications = new ArrayList<Apps>();
    }
    public Node(){
        this.id = "Failed";
        this.typeId = "N";
        this.Mac = findMac();
        this.ipv6 = "N/A";
        this.links = new ArrayList<String>();
        this.applications = new ArrayList<Apps>();
    }

    // Getters
    public String getId(){
        return this.id;
    }
    public String getMac(){
        return this.Mac;
    }
    public String getIP(){
        return ipv6;
    }

    // DONT USE THIS
    public String getNode(){
        return "Node: "+ Mac + "\nTemperature: " + String.valueOf(Temperature) + ", Windspeed: " + String.valueOf(Windspeed) + ", Humidity: " + String.valueOf(Humidity) + ", Vibration: " + String.valueOf(Vibration) + ", Pressure: " + String.valueOf(Pressure);
    }
    
    // print the configurations
    public String printNodeConf(){
        String toRet = "Conf"+this.id+".(";

        toRet = toRet + "MAC(" + this.getMac() + ") | " + "IPv6("+ this.getIP() + ") |";

        if(this.Temperature){
            toRet = toRet+" T |";
        }
        if(this.Windspeed){
            toRet = toRet+" W |";
        }
        if(this.Humidity){
            toRet = toRet+" H |";
        }
        if(this.Vibration){
            toRet = toRet+" V |";
        }
        if(this.Pressure){
            toRet = toRet+" P |";
        }
        //Remove the last "|"
        if(toRet.substring(toRet.length() - 2).equals(" |")){
            toRet = toRet.substring(0,toRet.length()-2);
        }
        toRet = toRet+")";
        return toRet;
    }
    //print Apps and Links
    public String printAppsAndLinks(){
        String toRet = this.typeId + this.id + ".(";

        if(this.links.isEmpty() && this.applications.isEmpty()){
            return this.id;
        }
        
        if(!this.links.isEmpty()){
            toRet = toRet + "L.(";
            for (int i = 0; i < this.links.size();i++){
                toRet = toRet + " L_E{" + this.links.get(i) + "} |";
            }
            //Remove the last "|"
            if(toRet.substring(toRet.length()-2).equals(" |")){
                toRet = toRet.substring(0,toRet.length()-2);
            }
            toRet = toRet+")";
        }
        

        if(!this.applications.isEmpty()){
            toRet = toRet + "|";
            for (int i = 0; i < this.applications.size(); i++){
                toRet = toRet + this.applications.get(i).getId() + " |";
            }
            if(toRet.substring(toRet.length() - 2).equals(" |")){
                toRet = toRet.substring(0,toRet.length()-2);
            }
        }
        
        toRet = toRet+")";
        
        return toRet;
    }

    // get configurations
    public boolean getTemperature(){
        return Temperature;
    }
    public boolean getWindspeed(){
        return Windspeed;
    }
    public boolean getHumidity(){
        return Humidity;
    }
    public boolean getVibration(){
        return Vibration;
    }
    public boolean getPressure(){
        return Pressure;
    }
    // links
    public ArrayList<String> getLinks(){
        return links;
    }
    // apps
    public ArrayList<Apps> getApps(){
        return applications;
    }

    // Setters
    public void setMac(String mac){
        this.Mac = mac;
    }
    public void setIP(String ipv6){
        this.ipv6 = ipv6;
    }
    // set configurations
    public void setTemperature(boolean temp){
        this.Temperature = temp;
    }
    public void setWindspeed(boolean wind){
        this.Windspeed = wind;
    }
    public void setHumidity(boolean humidity){
        this.Humidity = humidity;
    }
    public void setVibration(boolean vibration){
        this.Vibration = vibration;
    }
    public void setPressure(boolean pressure){
        this.Pressure = pressure;
    }
    // set all configs
    public void setAllConf(boolean temperature, boolean windspeed, boolean humidity, boolean vibration, boolean pressure){
        this.Temperature = temperature;
        this.Windspeed = windspeed;
        this.Humidity = humidity;
        this.Vibration = vibration;
        this.Pressure = pressure;
    }

    // Addres
    public void addLink(String newLink){
        this.links.add(newLink);
    }
    public void addApp(Apps newApp){
        this.typeId = "N_U";
        this.applications.add(newApp);
    }
    // create Mac
    private String findMac(){

        Random rand = new Random();
        byte[] macAddr = new byte[6];
        rand.nextBytes(macAddr);

        macAddr[0] = (byte)(macAddr[0] & (byte)254);
        StringBuilder sb = new StringBuilder(18);
        for(byte b : macAddr){

            if(sb.length() > 0)
                sb.append(":");

            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

}
