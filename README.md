# CAP4601 Project 1 | ConnectM Computer Game

Reference for Report: [Adversarial Search](https://www.javatpoint.com/ai-adversarial-search)

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


## HOW TO RUN THE PROGRAM
Download and unzip the program in an accessible file. Import the file into Eclipse, JGrasp, or any Java IDE where GitBash is available. Once the program is imported onto the IDE, open GitBash and navigate to the file the program is saved in. Compile the program just to make sure there are no errors with importing. In Gitbash you will run the command: "java connectM N M H", where N is the number of columns and rows in the N x N grid (greater than 3, but no higher than 10), M is the number of disks that must be connected contiguosly in the grid(must be greater than 1 and no larger than N), and H is the parameter that determines whether the computer or the human goes first (0 for the computer to go first, and 1 for the human to go first). For example a valid command would be : "java connectM 6 4 1" (this gives a 6x6 grid where you have to get 4 in a row and the human player goes first).
