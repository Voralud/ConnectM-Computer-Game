#CONNECT M COMPUTER GAME

##OVERVIEW 
This  assignment  will  require  you  to  implement  a  computer  game  for  a  human  user  to  play  the  game  Connect  M 
against a machine. Your program must use adversarial search techniques, specifically alpha-beta pruning, along with 
a min and max function to evaluate a game tree and select moves in the game to win it. 

##THE PROGRAM 
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

##DATA STRUCTURES 
You must  choose  appropriate  data  structures  to  represent the  board  and  state  information  about  the  game. You 
must describe these data structures in the project report. 

##VISUALIZATION 
Your program must use a two-dimensional visualization to show the board and its pieces. The visualization may be 
a  graphical  user  interface  or  a  text-based  user  interface.  If  the  visualization  is  text-based,  choose  simple  ASCII 
characters rather  than  colors  to represent  the  two  types  of  disks  in  the  game.  Below is  a  simple  ASCII character-
based grid visualization illustrating two players with X and O as the two disk pieces. 

##OPTIONAL FEATURE 
As an optional feature of your program, you may run your program to play against another computer program using 
socket communication for the two programs to exchange information about the moves. I have provided a starter 
code in Java to use a UDP socket for data exchange between two programs. The parameters for starting the program 
to play against another computer program are as follows: 
connectM N M H [IP PORT ] 
Again,  N,  M,  H  are  the  same  parameters  discussed  above.    IP  and  Port  are  optional  parameters  and  hence  are 
enclosed  by  [  ].  They  represent  the  IP  address  and  port  number  of  the  computer  program  to  communicate  with 
another program. You may assume that one program sets H to 1 to make the first move,  and the other sets H to 0 
to wait for the opponent to make the first move. Furthermore, you may assume that both programs share the same 
values for N and M.  
The program must use the following protocol to exchange a single move:  column 
Here, the column is a number from 0 to N-1 to indicate the column to play. Each program uses a text string with a 
single number. The text string is put into a datagram packet eight bytes long. The datagram packet must be sent to 
the  other  computer  program  to  communicate  the move. The  other  program  accepts  the  datagram  packet  on  the 
UDP socket, extracts the text string from it, converts the string into a column number, and uses the number to add 
the disk of the opposing player to the board before generating its move and sharing it with the opponent using the 
same  communication  process.  This  back-and-forth  communication  continues  until  the  programs  simultaneously 
determine a winner or a draw. At that point, the programs terminate. 

##Final Evaluations 
• Is a board visualized according to the input parameter? 
• Are disks set correctly in the game for the player and the computer? 
• Is a winning move recognized? 
• Does the computer attempt to play a winning game? 
