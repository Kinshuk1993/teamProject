package controller;
/**
 * University of Glasgow
 * MSc CS+ Team Project, fall 2018
 * 
 * Node class, has different sensor configurations, represented as booleans
 * can also be linked to other nodes, done by adding the link to the linklist
 * can also contain Applications, done by adding the app to the applist.
 * 
 * N_U = Node in Use, a node is in use if it has an application
 * N = Idle node
 * 
 */
import java.util.Random;
import java.util.ArrayList;
public class Node {

	// Private variables
	private String id; // this is the id the node is known as
	private String typeId; // this identifies what kind of node it is
	private String Mac; // MAC is needed, works as the ID
	private String ipv6; // Only when connected to a network
	private double xCoord; //to store the x coordinate of this node on the scene
	private double yCoord; //to store the y coordinate of this node on the scene
	// These are indicators for what kind of data this node can provide
	private boolean Temperature;
	private boolean Windspeed;
	private boolean Humidity;
	private boolean Vibration;
	private boolean Pressure;
	// List containing links, applications and nodes it is currently linked to
	private ArrayList<String> links;
	private ArrayList<Apps> applications;
	private ArrayList<String> nodesLinkedTo;

	// Constructors
	public Node(String id) {
		this.id = id;
		this.typeId = "N";
		this.Mac = findMac();
		this.ipv6 = "N/A";
		this.links = new ArrayList<String>();
		this.applications = new ArrayList<Apps>();
		this.nodesLinkedTo = new ArrayList<String>();
	}

	public Node() {
		this.id = "Failed";
		this.typeId = "N";
		this.Mac = findMac();
		this.ipv6 = "N/A";
		this.links = new ArrayList<String>();
		this.applications = new ArrayList<Apps>();
		this.nodesLinkedTo = new ArrayList<String>();
	}

	// Getters
	public String getId() {
		return this.id;
	}

	public String getMac() {
		return this.Mac;
	}

	public String getIP() {
		return ipv6;
	}

	// getters for nodes linked to
	public ArrayList<String> getNodesLinkedTo() {
		return this.nodesLinkedTo;
	}

	// setter for nodes linked to
	public void setNodesLinkedTo(String nodeId) {
		this.nodesLinkedTo.add(nodeId.substring(1, nodeId.toString().length() - 1));
	}

	// getNode prints the node with a MAC address and each configuration
	public String getNode() {
		return "Node: " + Mac + "\nTemperature: " + String.valueOf(Temperature) + ", Windspeed: "
				+ String.valueOf(Windspeed) + ", Humidity: " + String.valueOf(Humidity) + ", Vibration: "
				+ String.valueOf(Vibration) + ", Pressure: " + String.valueOf(Pressure);
	}

	// print the configurations for use in Bigraph or BIG files
	public String printNodeConf() {
		String toRet = "Conf" + this.id + ".(";
		toRet = toRet + "MAC(" + this.getMac() + ") | " + "IPv6(" + this.getIP() + ") |";
		if (this.Temperature) {
			toRet = toRet + " T |";
		}
		if (this.Windspeed) {
			toRet = toRet + " W |";
		}
		if (this.Humidity) {
			toRet = toRet + " H |";
		}
		if (this.Vibration) {
			toRet = toRet + " V |";
		}
		if (this.Pressure) {
			toRet = toRet + " P |";
		}
		// Remove the last "|"
		if (toRet.substring(toRet.length() - 2).equals(" |")) {
			toRet = toRet.substring(0, toRet.length() - 2);
		}
		toRet = toRet + ")";
		return toRet;
	}

	// print Apps and Links
	/**
	 * Print the Node type and each link and app this node has exs:
	 * N_U{c1}.(L.(L_E{l1} | L_E{l2}) | A(1))
	 * 
	 * @return string with apps and links
	 */
	public String printAppsAndLinks() {
		String toRet = this.typeId + this.id + ".(";
		if (this.links.isEmpty() && this.applications.isEmpty()) {
			return this.id;
		}
		if (!this.links.isEmpty()) {
			toRet = toRet + "L.(";
			for (int i = 0; i < this.links.size(); i++) {
				if (i == this.links.size() - 1) {
					toRet = toRet + " L_E{" + this.links.get(i) + "}";
				} else if (i == 0 && !(i == this.links.size() - 1)) {
					toRet = toRet + "L_E{" + this.links.get(i) + "} |";
				} else {
					toRet = toRet + " L_E{" + this.links.get(i) + "} |";
				}
			}
			toRet = toRet + ")";
			toRet = toRet + " |";
		}
		if (!this.applications.isEmpty()) {
			for (int i = 0; i < this.applications.size(); i++) {
				if (i == this.applications.size() - 1) {
					toRet = toRet + " " + this.applications.get(i).getId();
				} else {
					toRet = toRet + " " + this.applications.get(i).getId() + " |";
				}
			}
		}
		toRet = toRet + ")";
		return toRet;
	}

	// get configurations
	public boolean getTemperature() {
		return Temperature;
	}

	public boolean getWindspeed() {
		return Windspeed;
	}

	public boolean getHumidity() {
		return Humidity;
	}

	public boolean getVibration() {
		return Vibration;
	}

	public boolean getPressure() {
		return Pressure;
	}

	// links
	public ArrayList<String> getLinks() {
		return links;
	}

	// apps
	public ArrayList<Apps> getApps() {
		return applications;
	}

	// Setters
	public void setMac(String mac) {
		this.Mac = mac;
	}

	public void setIP(String ipv6) {
		this.ipv6 = ipv6;
	}

	// set configurations
	public void setTemperature(boolean temp) {
		this.Temperature = temp;
	}

	public void setWindspeed(boolean wind) {
		this.Windspeed = wind;
	}

	public void setHumidity(boolean humidity) {
		this.Humidity = humidity;
	}

	public void setVibration(boolean vibration) {
		this.Vibration = vibration;
	}

	public void setPressure(boolean pressure) {
		this.Pressure = pressure;
	}

	// set all configs in one
	public void setAllConf(boolean temperature, boolean windspeed, boolean humidity, boolean vibration,
			boolean pressure) {
		this.Temperature = temperature;
		this.Windspeed = windspeed;
		this.Humidity = humidity;
		this.Vibration = vibration;
		this.Pressure = pressure;
	}

	// Add link to list of links
	public void addLink(String newLink) {
		if(this.ipv6.equals("N/A")){
			this.ipv6 = findIPv6();
		}
		this.links.add(newLink);
	}

	// Add app to list of apps
	public void addApp(Apps newApp) {
		this.typeId = "N_U";
		this.applications.add(newApp);
	}

	// create Mac
	private String findMac() {
		Random rand = new Random();
		byte[] macAddr = new byte[6];
		rand.nextBytes(macAddr);
		macAddr[0] = (byte) (macAddr[0] & (byte) 254);
		StringBuilder sb = new StringBuilder(18);
		for (byte b : macAddr) {
			if (sb.length() > 0)
				sb.append(":");
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

	// create IPv6
	private String findIPv6() {
		Random rand = new Random();
		byte[] ipv6Addr = new byte[8];
		rand.nextBytes(ipv6Addr);
		ipv6Addr[0] = (byte) (ipv6Addr[0] & (byte) 254);
		StringBuilder sb = new StringBuilder(40);
		for (byte b : ipv6Addr) {
			if (sb.length() > 0)
				sb.append(":");
			sb.append(String.format("%04x", b));
		}
		return sb.toString();
	}

	public double getxCoord() {
		return xCoord;
	}

	public void setxCoord(double xCoord) {
		this.xCoord = xCoord;
	}

	public double getyCoord() {
		return yCoord;
	}

	public void setyCoord(double yCoord) {
		this.yCoord = yCoord;
	}
}
