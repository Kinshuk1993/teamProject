# Operating interface for large-scale Wireless Sensor Networks

University of Glasgow
CS+ MSc Computing Science Team Project 2018.

## Members

* Kinshuk Bhardwaj
* Yidi Cao
* Thomas Mannsverk Eliassen
* Yiting Zhang

## Project description

This repository contains the source code for a test version of an Operating Interface for large-scale Wireless Sensor Networks. This application allows a user to create a WSN while a bigraph algebraic representation is build in the background. When a user is done building their network they can export the WSN to a BIG file.

This application follows the paper "Modelling and Verification of Large-Scale Sensor Network Infrastructures" by Michele Sevegnani et al. and provides an easier way to create WSN model. However, this is just a Proof of concept and does not provide a fully implemented version, and has some problems that have not been fixed at this point.

The program is available to run in its current state with the BigraphUI.jar file.

### Work with this program

This program was build following Eclipse standard package structure, and is split in two packages, controller and application.
The application contains the GUI, and interacts with the controller to create the bigraph and the BIG file.

To work with this application download the project and open it using Eclipse. The package files are contained in the "src" directory and are compiled to the "bin" directory. If you wish to work with another IDE some restructuring might be necessary.