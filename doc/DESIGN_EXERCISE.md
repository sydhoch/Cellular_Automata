#Names: Sara Behn (sbr71), Sydney Hochberg (srh57), Arilia Frederick (asf41)

#Example Simulations
- commonalities: grid structure, set of states (usually 3), behavior/state influenced by neighbors, concept of being killed/killing/reproducing (life cycle), 0 player games
- differences: some are based on probability/randomness, different behavior rules/# of behaivors, some have potential to not move

#High level design ideas
- object needs to know if it's on/off and what all of its neighbors are (gets passed state of neighbors through list of ints or something)
- grid needs to keep track of cells generally