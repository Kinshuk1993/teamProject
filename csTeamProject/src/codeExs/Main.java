package codeExs;

public class Main {

    public static void main(String[] args) {

        // Create new Scene
        Controller newScene = new Controller("Ekeberg");

        // Add top Area
        String north = newScene.addTopArea("North");
        String south = newScene.addTopArea("South");

        // Add inner Area
        String bridge = newScene.addInnerArea(north, "Bridge");

        // Node A created
        String nodeA = newScene.addNodeToArea(bridge, false, false, false, false, true, "");

        // Node B created
        String nodeB = newScene.addNodeToArea(north, false, false, false, true, false, "");

        // Node C created
        String nodeC = newScene.addNodeToArea(north, false, false, true, true, true, "");

        // Node D created
        String nodeD = newScene.addNodeToArea(north, true, true, false, false, true, "");

        // Node E created
        String nodeE = newScene.addNodeToArea(north, false, false, true, false, true, "");        

        // Node F created
        String nodeF = newScene.addNodeToArea(south, false, false, true, false, true, "");        



        // link nodes together
        String link1 = newScene.addNewLink(nodeA, nodeB);
        String link2 = newScene.addNewLink(nodeB, nodeD);
        String link3 = newScene.addNewLink(nodeD, nodeC);
        String link4 = newScene.addNewLink(nodeD, nodeE);
        String link5 = newScene.addNewLink(nodeC, nodeF);

        // Create applications
        String app1 = newScene.newApp("App1");
        String app2 = newScene.newApp("App2");
        
        // add applications to nodes
        newScene.addAppToNode(app1, nodeB);
        newScene.addAppToNode(app1, nodeD);

        newScene.addAppToNode(app2, nodeC);
        newScene.addAppToNode(app2, nodeD);
        newScene.addAppToNode(app2, nodeF);

        // Get the bigraph Expression
        String exp = newScene.exportBigraph();

        // Get the model.big file
        newScene.exportBIG();
        // Print the bigraph
        System.out.println(exp);

    }

}