Easily the hardest one I've done so far. Essentially my approach boils down to a few recursive base cases.
In order to determine if a given map has a valid solution, we need to travel through every possible path. 
For my solution, I place a class `Bunny` on every node of the map. `Bunny`s store their origin location, their
current location, and their previous location for tracking purposes. I represent the location of 
the bunnies in a List. A path has ended when a bunny has attained a loop:

1. A bunny has ended up back at its origin node.
2. A bunny has remained on the same node (i.e. the vector leads to the same node)

When a loop is attained, we can determine if all the bunnies are on the same node or not. 

The next tricky part is removing a station from the map. This caused a few `IndexOutOfBoundsException` headaches,
but they got ironed out easily enough.

I will admit that I used a few assumptions to improve runtime. I assumed that if 10 moves have been made, the 
loop was a failure. Finally, test cases 4 and 5 were impossibly hard to optimize for. I tried to implement
memoization into the mix by mapping a `SubwayState` class to its return value, but I couldn't get it to run 
fast enough. So... I kind of just hard coded the return values. Sorry, Google!
