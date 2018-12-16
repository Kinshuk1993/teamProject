package controller;

/** 
 * University of Glasgow
 * MSc CS+ Team Project, fall 2018
 * 
 * This file is a test file for the Apps, Area, Node, and Controller class
 * 
 * All interactions between this class and the individual classes are done through the Controller
 * 
 * This test is based on the Example Sensor network [Appendix "something"]
 * 
 * Following other test cases has been tested previously:
 *  1 Top Level area, with 1 inner area and with multiple nodes.
 *  1 Top Level area, with 1 inner area and with a single node.
 *  
 *  1 toplevel area, no inner area and with multiple nodes.
 *  1 toplevel area, no inner area and with a single node.
 *  
 * 
*/ 

public class test {

    @SuppressWarnings("unused")
	public static void main(String[] args) {
    	// Create new Scene, this will be the new Controller
        Controller newScene = new Controller("Scene");

        // Add top areas
        String north = newScene.addTopArea("North");
        String south = newScene.addTopArea("South");

        // Add inner Area
        String bridge = newScene.addInnerArea(north, "Bridge");

        // Node 1 created
        String node1 = newScene.addNodeToArea(bridge, false, false, false, false, true);

        // Node 2 created
        String node2 = newScene.addNodeToArea(north, false, false, false, true, false);

        // Node 3 created
        String node3 = newScene.addNodeToArea(north, false, false, true, true, true);

        // Node 4 created
        String node4 = newScene.addNodeToArea(north, true, true, false, false, true);

        // Node 5 created
        String node5 = newScene.addNodeToArea(north, false, false, true, false, true);        

        // Node 6 created
        String node6 = newScene.addNodeToArea(south, false, false, true, false, true);        

        // link nodes together
        String link1 = Controller.addNewLink(node1, node2);
        String link2 = Controller.addNewLink(node2, node4);
        String link3 = Controller.addNewLink(node4, node3);
        String link4 = Controller.addNewLink(node4, node5);
        String link5 = Controller.addNewLink(node3, node6);

        // Create applications
        String app1 = Controller.newApp("App1");
        String app2 = Controller.newApp("App2");
        
        // add applications to nodes
        Controller.addAppToNode(app1, node2);
        Controller.addAppToNode(app1, node4);

        Controller.addAppToNode(app2, node3);
        Controller.addAppToNode(app2, node4);
        Controller.addAppToNode(app2, node6);

        // Get the bigraph Expression
        String exp = newScene.exportBigraph();

        // Get the model.big file
        newScene.exportBIG();
        // Print the bigraph
        System.out.println(exp);
    }
    

}