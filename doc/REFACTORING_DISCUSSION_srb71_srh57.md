#Duplicate Code
- We found that a lot of duplicated code was in our tests classes, so we went through and made before each methods to do the pre-work for the tests
- We refactored the front end design into classes based on type of front end feature instead of based on the location of the feature, which allowed for more specific and common methods
- We refactored PPCell because it had duplicate code so we put more code into methods

#Long Methods
- PPCell.checkNeighborStatus: We decided that we could put model.grid.cell states into it as a map so instead of having a lot of checks to see if the next state was already set, now we just have to check if the model.grid.cell is still in map which simplified the code and removed duplication.
- Grid.Grid: We realized that the method wasn't very specific- it was both reading in the file and making the model.grid.cell objects, so we split up the code into two methods that each did 1 thing.
- Play.changeCellState: We improved this method by making SimType a stronger Enum that could store the number of states it cycled through.

#Checklist/general refactoring
- We made SharkCell/FishCell classes that extend PPCell to make the classes more DRY
- 

#Future refactoring todos:
- Exception handling
- get rid of downcasting (1 instance)

