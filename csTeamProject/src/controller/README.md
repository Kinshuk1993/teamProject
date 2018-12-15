# Controller package for bigraph structure.

University of Glasgow
CS+ MSc Computing Science Team Project 2018.

## Content

* Node.java
* Area.java
* Apps.java
* Controller.java


## Description

This package handles the interactions needed to create a bigraph and big file for a Wireless Sensor Network model. The structure is maintained by the controller class and all interactions regarding the model should be done through the controller.

To use the controller create a new controller object and give it a name.
When creating new Areas, Nodes, Applications, or Links they all return a string with the automatically generated id of that object. 

### Area

To add a top-level area use the addTopArea(String name).
To add an inner area use the addInnerArea(String parent, String name), where "parent" is the name of the area you are adding the new area within.

### Node

Add a new node by using addNodeToArea(String parent, boolean temperature, boolean windspeed, boolean humidity, boolean vibration, boolean pressure) where the booleans are the configurations of the sensor.
Changing the configurations of a node is done by addConfigToNode(String nodeName, boolean temperature, boolean windspeed, boolean humidity, boolean vibration, boolean pressure) where the nodeName is the node to change.

### Link

To link, two nodes use addNewLink(String firstNode, String secondNode), where the two strings are the names of the node you are linking.

### App

To create an application use newApp(String name)
To add the app to an application use addAppToNode(String app, String node), where "app" is the name of the application and node is the name of the node you are adding the application to.

### Exports

To get the bigraph algebraic expression use exportBigraph() 
To get the big file use exportBIG()
 