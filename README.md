# CONNECT M COMPUTER GAME

## OVERVIEW 
This  assignment  will  require  you  to  implement  a  computer  game  for  a  human  user  to  play  the  game  Connect  M 
against a machine. Your program must use adversarial search techniques, specifically alpha-beta pruning, along with 
a min and max function to evaluate a game tree and select moves in the game to win it. 

## THE PROGRAM 
Implement a program connectM in Java, C, C++, or Python. In this game, two players drop colored discs into a grid 
of columns and rows from the top. The goal is to connect M disks of the same color contiguously across the rows 
and  columns  in  the  grid  either  horizontally,  vertically,  or  diagonally.  The  first  player  that  connects  all  M  disks 
contiguously wins the game. If, at any point, it is no longer possible to connect M disks in the grid due to a lack of 
space for disks, the game ends with no winner. 
The  program  requires  two  input  parameters  that  define  the  size  of  the  grid  as  a  quadratic  board  (note  that  the 
original Connect Four board is not a quadratic grid) and the number of disks to connect contiguously. Your program 
must be started from the command line according to the following syntax: 
 connectM N M H 
Here,  parameter  N  is  the  number  of columns  and rows in the  N  x  N grid,  M is  the  number  of  disks  that must  be 
connected contiguously in the grid, and H is a flag that indicates if the human or computer makes the first move in 
the game. The grid size should be at least three and no larger than 10. Parameter M must be higher than one but no 
higher  than  N.  Parameter  H  must  be  1  or  0  if  the  human  or  computer  makes  the  first  move,  respectively.  Your 
computer program will automatically choose a disk color/shape for the human and computer player. 
Once  the  program  starts,  the  program  prompts  the  human  player  to  select  a  move. Your  program may  allow  the 
human player to enter a number to select a column and  add a disk to the board. Finally, your program checks for 
winning moves and terminates when it detects a winner or a draw. 


## Final Evaluations 
* Is a board visualized according to the input parameter? 
* Are disks set correctly in the game for the player and the computer? 
* Is a winning move recognized? 
* Does the computer attempt to play a winning game? 
