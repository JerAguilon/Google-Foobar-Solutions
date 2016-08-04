Explanation
==========

Pretty simple solution with some research. I googled how to find the internal points of a shape on a graph, and ran across Pick's theorem (https://en.wikipedia.org/wiki/Pick%27s_theorem). Instead of solving for area, I simply had to solve for the interior points of a shape.

The only major roadbump was for large magnitude inputs, finding the area of the triangle through vector multiplication would exceed the size of `int`. As always, `BigInteger` came to the rescue.
