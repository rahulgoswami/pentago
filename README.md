# pentago
Code for the board game "Pentago" developed using JSF 2.2.  Stack: JSF 2.2, JBoss 7.1, JPA Hibernate,, MySql 5.6

Game Rules:
===========
The game starts with an empty game board. The game board is a 6x6 grid consisting of the four separate quadrants. Thus each quadrant will be of size 3x3. The starting player places a marble in a socket of his or her choice. After placing a marble the player turns any one of the four quadrants one notch (90 degrees) clock- or counter clockwise.
A quadrant, not necessarily the one on which the marble has been placed, must be turned each move. Then the second player does the same, i.e. places a marble and turns a quadrant. So on and so forth. The first player to get five marbles at the end of a move(placing a marble followed by a quadrant rotation) in a row wins! The row can be horizontal, vertical or diagonal and run over two or three quadrants. If all the sockets have been filled without any player getting five in a row the game is a draw. If both players get five in a row as a player turns a quadrant, the player who rotated the quadrant wins.

The game is played over a secure TCP/IP connection between two players and is made as secure as possible by minimizing the attack surface and implementing several other secure software practices(eg: input validation using regEx, protectiong against sql injection, use of salted hashes for storing passwords, etc).
