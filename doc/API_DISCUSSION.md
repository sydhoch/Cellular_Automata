##External API
- SimulationDriver

Documentation:
To run an existing simulation, the user would run the SimulationDriver class. They could edit an existing properties file to give their own specifications.

Vision: To run a simulation, the user could run the SimulationDriver class with the name of any resource property file, which would then be run.

##Internal API
- Grid class + methods
- Neighborhood class + methods
- everything in view (to modify GUI)
- all other Cells (to modify rules and special values)
- Exceptions
- Cell
- Enums

Documentation: To create a new simulation type, the client would need to make a new extension to the Cell class and add the simulation type to the enum file. They'd also need to edit the resource property files Image.properties and SideBar.properties to update the intended visuals.
Vision: 
