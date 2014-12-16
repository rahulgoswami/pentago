package com.pentago.controller;

import java.util.ArrayList;
import com.pentago.myutil.MyUtil;

public class GameEngine {
	
	private static Status status_of_game = new Status(0);
	private static int turns_left = 36;
	static MoveResult result = new MoveResult();
	static String winner;
	public static MoveResult playMove(String Board, Move move, int player )
	{
		ArrayList<Row> rows = (ArrayList<Row>) MyUtil.stringToList(Board);
		int [][] board = MyUtil.listToArray(rows);
		int[][] new_board;
		ArrayList<Row> new_board_list = new ArrayList<Row>();
		String new_board_string = null;
		boolean who;
		
		if(player==1)
			who=true;
		else 
			who=false;
			
		//check status_of_game is OK and only then continue else return pass
		int ver = move.getRow()-1;
		int hor = move.getCol()-1;
		int quadrant = move.getGrid();
		boolean dir = move.isClock();
		//check for validity
		if(board[ver][hor] != 0 || quadrant<1 || quadrant >4 || ver <0 || ver >6 || hor <0 || hor > 6)
		{
			//status_of_game.value(4);
			System.out.println("invalid move null returned");
			return null;// or return "invalid moveee!!!"
		}
		else
		{
			board[ver][hor] = player;
			new_board = rotate(quadrant,dir, board);
			new_board_list = (ArrayList<Row>) MyUtil.arrayToList(new_board);
			new_board_string = MyUtil.listToString(new_board_list);
			if(checkWin(board,who)){
				System.out.println("GAMEENGINE: checkWin() : "+ winner);
				result.setWinner(winner);
				result.setNewBoard(new_board_string);
			}
			else if(turns_left == 0){
				result.setWinner("0");
				result.setNewBoard(new_board_string);
			}
			else
			{
				result.setWinner("none");
				result.setNewBoard(new_board_string);
			}
		}		
		return result;

	}
	
	
	public static int[][] rotate(int quadrant,boolean dir,int[][] board)
	{
		int[][] tempboard = new int[3][3]; // temporary board for constructing
        int clock;
        //change clockwise (boolean) to numeric
        if (dir) {
            clock = 1;
        } else {
            clock = -1;
        }
        // the rotation
        int fixx = 0; 		// variable for the x position of the fix point
        int fixy = 0; 		// variable for the y position of the fix point
        switch (quadrant) { // classify coordinates to fix point and fix it
            case 1: {
                fixy = 1;
                fixx = 1;
            }
            break;
            case 2: {
                fixy = 1;
                fixx = 4;
            }
            break;
            case 3: {
                fixy = 4;
                fixx = 1;
            }
            break;
            case 4: {
                fixy = 4;
                fixx = 4;
            }
            break;
        }

        int fix = (fixy * 6) + fixx; // numeric value of the fix point
        int j = 5; // first variable for shifting
        int ver = 0, hor = 0;

        // shifting
        for (int i = 0; i <= 2; i++, j++) {
            ver = (fix + (j * clock)) / 6;
            hor = (fix + (j * clock)) % 6;
            tempboard[i][0] = board[ver][hor];
        }
        j = -1; // second variable for shifting

        // shifting
        for (int i = 0; i <= 2; i++, j++) {
            ver = (fix + (j * clock)) / 6;
            hor = (fix + (j * clock)) % 6;
            tempboard[i][1] = board[ver][hor];
        }
        j = 7; // third variable for shifting

        // shifting
        for (int i = 0; i <= 2; i++, j--) {
            ver = (fix - (j * clock)) / 6;
            hor = (fix - (j * clock)) % 6;
            tempboard[i][2] = board[ver][hor];
        }
        
        // writing the rotation in the game board
        int next = fix - 7; // next is the numeric value for writing
        for (int i = 0; i <= 2; i++) {
            for (j = 0; j <= 2; j++) {
                ver = next / 6;
                hor = next % 6;
                board[ver][hor] = tempboard[i][j];
                next++;
            }
            next += 3;//go to the next field on this quadrant
        }
        turns_left--;
        return board;

	}

	public static boolean checkWin(int[][] board,boolean who)
	{
		if(checkWinRow(board, who) || checkWinColumn(board, who) || checkWinMainDiag(board, who) || checkWinSecondDiag(board, who))
			return true;
		return false;
	}
	
	/**
     * This function calculates the sum of the 3 diagonals
     * @param who This parameter shows who is playing. If who = true, Black plays, else Red plays
     */
    public static boolean checkWinMainDiag(int[][] board, boolean who) {

    	boolean flag=false;
    	// Diagonal from 0|0 to 5|5

        // initialize sum with 0
        int sum = 0;
        for (int i = 0; i < 6; i++) {

            if (who) {
                if (board[i][i] == 1) {
                    sum += board[i][i];
                } else {
                    sum = 0;
                }
                if (sum == 5) {
                    flag=true;
                    winner="1";
                }
            } else if (!who) {

                if (board[i][i] == -1) {
                    sum += board[i][i];
                } else {
                    sum = 0;
                }

                if (sum == -5) {
                    flag=true;
                    winner="-1";
                }
            }
        }
        if(flag)
        	return flag;
        
        // Diagonal from 0|1 to 4|5
        // initialize sum with 0
        sum = 0;
        for (int i = 0; i < 5; i++) {

            if (who) {
                if (board[i + 1][i] == 1) {
                    sum += board[i + 1][i];
                } else {
                    sum = 0;
                }
                if (sum == 5) {
                    flag=true;
                    winner="1";
                }
            } else if (!who) {

                if (board[i + 1][i] == -1) {
                    sum += board[i + 1][i];
                } else {
                    sum = 0;
                }

                if (sum == -5) {
                    flag=true;
                    winner="-1";
                }
            }
        }
        if(flag)
        	return flag;
        
        // Diagonal from 1|0 to 5|4
        // initialize sum with 0
        sum = 0;
        for (int i = 0; i < 5; i++) {

            if (who) {
                if (board[i][i + 1] == 1) {
                    sum += board[i][i + 1];
                } else {
                    sum = 0;
                }
                if (sum == 5) {
                    flag=true;
                    winner="1";
                }
            } else if (!who) {

                if (board[i][i + 1] == -1) {
                    sum += board[i][i + 1];
                } else {
                    sum = 0;
                }

                if (sum == -5) {
                    flag=true;
                    winner="-1";
                }
            }
        }
        return flag;
    }
	
    
    /**
     * This function calculates the sum of the 3 second diagonals
     * @param who This parameter shows who is playing. If who = true, Black plays, else Red plays
     */
    public static boolean checkWinSecondDiag(int[][] board,boolean who) {
    	boolean flag=false;
    	int sum = 0;
        // Diagonal from 0|5 to 5|0
        for (int i = 0; i < 6; i++) {
            int j = 5 - i;

            if (who) {
                if (board[j][i] == 1) {
                    sum += board[j][i];
                } else {
                    sum = 0;
                }
                if (sum == 5) {
                    flag=true;
                    winner="1";
                }
            } else if (!who) {

                if (board[j][i] == -1) {
                    sum += board[j][i];
                } else {
                    sum = 0;
                }

                if (sum == -5) {
                    flag=true;
                    winner="-1";
                }
            }
        }
        if(flag)
        	return flag;
        
        // Diagonal from 0|4 to 4|0
        sum = 0;
        for (int i = 0; i < 5; i++) {
            int j = 4 - i;

            if (who) {
                if (board[j][i] == 1) {
                    sum += board[j][i];
                } else {
                    sum = 0;
                }
                if (sum == 5) {
                    flag=true;
                    winner="1";
                }
            } else if (!who) {

                if (board[j][i] == -1) {
                    sum += board[j][i];
                } else {
                    sum = 0;
                }

                if (sum == -5) {
                    flag=true;
                    winner="-1";
                }
            }
        }
        if(flag)
        	return flag;

        // Diagonal from 1|5 to 5|1
        sum = 0;
        for (int i = 1; i < 6; i++) {
            int j = 6 - i;

            if (who) {
                if (board[i][j] == 1) {
                    sum += board[i][j];
                } else {
                    sum = 0;
                }
                if (sum == 5) {
                    flag=true;
                    winner="1";
                }
            } else if (!who) {

                if (board[i][j] == -1) {
                    sum += board[i][j];
                } else {
                    sum = 0;
                }

                if (sum == -5) {
                    flag=true;
                    winner="-1";
                }
            }
        }
        return flag;
    }
    	
    /**
     * This function calculates the sum of the rows
     * @param who This parameter shows who is playing. If who = true, Black plays, else Red plays
     */
    public static boolean checkWinRow(int[][] board, boolean who) {
    	boolean flag = false;
    	
    	for (int i = 0; i < 6; i++) {
            // initialize sum with
            int sum = 0;
            for (int j = 0; j < 6; j++) {
                if (who) {
                    if (board[i][j] == 1) {
                        sum += board[i][j];
                    } else {
                        sum = 0;
                    }
                    if (sum == 5) {
                        flag=true;
                        winner="1";
                    }
                } else if (!who) {
                    if (board[i][j] == -1) {
                        sum += board[i][j];
                    } else {
                        sum = 0;
                    }

                    if (sum == -5) {
                        flag=true;
                        winner="-1";
                    }
                }
            }
        }
    	return flag;
    }
    
    /**
     * This function calculates the sum of the columns
     * @param who This parameter shows who is playing. If who = true, Black plays, else Red plays
     */
    public static boolean checkWinColumn(int[][] board, boolean who) {
    	boolean flag = false;
        for (int i = 0; i < 6; i++) {
            // initialize sum with 0
            int sum = 0;
            for (int j = 0; j < 6; j++) {
               
            	if (who) {
                    if (board[j][i] == 1) {
                        sum += board[j][i];
                    } else {
                        sum = 0;
                    }
                    if (sum == 5) {
                        flag=true;
                        winner="1";
                        System.out.println("checkWinCOlumn: CCCCCC "+flag);
                    }
                } else if (!who) {

                    if (board[j][i] == -1) {
                        sum += board[j][i];
                    } else {
                        sum = 0;
                    }

                    if (sum == -5) {
                    	flag=true;
                    	winner="-1";
                    }
                }
            }
       }
       return flag;
    }
    
}
    
    
    

