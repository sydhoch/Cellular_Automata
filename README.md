simulation
====

This project implements a cellular automata simulator.

Names: Sara Behn, Sydney Hochberg, Arilia Frederick

### Timeline

Start Date: February 20, 2019

Finish Date: March 8, 2019

Hours Spent: 150 (add in your hours)

### Primary Roles
Sara: I did all of the front end work, taking the lead on all of the classes in the view package. I also wrote the Grid class and some of the Neighborhood class.

Sydney: I made all of the Cell classes, set up the neighbors in the neighborhood class, wrote tests for cell class and neighborhood classes, made grids able to implement the grid outline feature, worked on the grid class, made the simulation driver class, and did some refactoring of the play class.

Arilia: I handled a lot of the configuration specifications, made most .properties and .csv files, and handled error checking. 
I wrote the Test implementation of model.grid.

### Resources Used
- http://www-cs-students.stanford.edu/~amitp/game-programming/grids/
- https://www.tutorialspoint.com/javafx/javafx_charts.htm
- https://www.programcreek.com/java-api-examples/?api=javafx.scene.control.Slider
- https://stackoverflow.com/questions/

### Running the Program

Main class: SimulationDriver

Data files needed: A wide variety of data files are available in the program. 
Data contains csv files, and Resources contains .properties files.

Interesting data files: I think the percolation files are interesting because 
they remind me of 201.

Features implemented:
- To step through, click on "start/end step through" and use the spacebar to step.
- To change an individual model.grid.cell state, pause the simulation and double click a model.grid.cell. 
It will switch to the next state.
-Errors checked for are: 
    - No File given in the property File. 
    - File Not Found
    - Resource not found
    - Bad Dimensions (<=3x3)
    - Any other invalid value that 

Assumptions or Simplifications:
- Images can only be implemented as rectangular. They cannot represent triangle or hexagon cells.
- Because a user only needs to change Play, assume that all of the parameters for model.grid are entered
- correctly means no errors should be generated. 
- imageViews in grid don't have outline

Known Bugs:
- The hexagon display doesn't always fill the screen properly.

Extra credit:


### Notes

A well designed test and basic implementation allowed for a robust complete version.

### Impressions
This project was a really good challenge for implementing strong design tactics. 
This was a good upgrade from the previous project. After learning more about how to be a better teammate,
we were able to be better programmers. 
