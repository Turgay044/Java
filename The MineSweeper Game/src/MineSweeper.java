import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class MineSweeper {
        int row,col;
        int [][] map;
        String[][] gameBoard;
        boolean alive = true;
        public MineSweeper(int row, int col){
            Scanner input = new Scanner (System.in);
            System.out.print("Please Enter Your Rows Number : ");
            row = input.nextInt();
            System.out.print("Please Enter Your Columns Number : ");
            col = input.nextInt();
            System.out.println("\n================== Welcome to Minesweeper ! ==================\n");
            this.col = col;
            this.row = row;
            this.map = new int[row][col];
            this.gameBoard = new String[row][col];
            createMap( row, col );
        }



        public void createMap(int row, int col) {
            Random ran = new Random();
            int minesCount = ( row * col ) / 4;
            int count = 0;
            while (count < minesCount) {
                int rowMines = ran.nextInt( row );
                int colMines = ran.nextInt( col );
                if (this.map[rowMines][colMines] != 1) {
                    this.map[rowMines][colMines] = 1;
                    count++;
                }
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    System.out.print( map[i][j] + " " );
                }
                System.out.println();
            }
            System.out.println( "\n==================== Let's Begin The Game ====================\n" );
            for (String[] strings : gameBoard) {
                Arrays.fill( strings, "*" );
            }
        }

        public void createGameBoard(){
            System.out.println("\n================ Let's Continue To The Next Step ================\n");
            for(String[] i : this.gameBoard){
                for(String j: i){
                    System.out.print(j + " ");
                }
                System.out.println();
            }
            System.out.println("=========================================================================");
        }


        public void startGame()
        {
            Scanner scan = new Scanner( System.in );
            int requestRow, requestCol;
            while(alive){
                System.out.println();
                System.out.print("Your Request For The Row Number : ");
                requestRow = scan.nextInt();
                System.out.print("Your Request For The Column Number : ");
                requestCol = scan.nextInt();
                if(requestRow>=row || requestRow<0){
                    System.out.println("You Requested a Wrong Row Number...");
                    continue;
                }
                if(requestCol>=col || requestCol<0){
                    System.out.println("You Requested a Wrong Column Number...");
                    continue;
                }
                System.out.println();
                if(map[requestRow][requestCol] != 1) {
                    checkMine( requestRow, requestCol );
                    createGameBoard();

                    System.out.println();

                    int request;
                    do {
                        System.out.println( "Do You Wanna Mark a Bomb Place?\n1-Yes\n2-No" );
                        request = scan.nextInt();
                        if (request == 1) {
                            int bombRow, bombCol;
                            System.out.print( "Your Request For The Bomb Row Number : " );
                            bombRow = scan.nextInt();
                            System.out.print( "Your Request For The Bomb Col Number : " );
                            bombCol = scan.nextInt();
                            if(bombRow>=row || bombRow<0){
                                System.out.println("You Requested a Wrong Row Number...");
                                continue;
                            }
                            if(bombCol>=col || bombCol<0){
                                System.out.println("You Requested a Wrong Column Number...");
                                continue;
                            }
                            System.out.println();
                            gameBoard[bombRow][bombCol] = String.valueOf( "X" );
                            winTheGame();
                        }
                    }
                    while (( request == 1 ));


                }
                else {
                    System.out.println( "You Blowed Up. You Picked a Mine..." );
                    System.out.println();
                    playAgain();
                    }
                }
            }


        public void playAgain(){
            Scanner scan = new Scanner(System.in);
            int request;
            do {
                System.out.println("Do You Wanna Play Again : \n1- Yes\n2- No");
                request = scan.nextInt();
            }
            while ((request != 1) && (request !=2));
            if (request == 1){
                MineSweeper m = new MineSweeper(3,3);
                m.startGame();
            }
            else if (request == 2){
                System.out.println("Hava a Nice Day, See U Later...");
                System.exit( 0 );
            }
        }

        public void checkMine(int rows, int cols){
            int count = 0;
            if(map[rows][cols] == 8)
                this.alive = false;
            else{
                if (rows == 0){
                    if (cols == 0){
                        for (int i = rows; i<=rows+1; i++)
                            for (int j = cols; j <= cols+1; j++) {
                                if(rows==i && cols==j){
                                    continue;
                                }
                                count += map[i][j];
                            }
                    }
                    else if (cols == gameBoard[cols].length-1){       //0 - last col check
                        for (int i = rows; i<=rows+1; i++)
                            for (int j = cols-1; j <= cols; j++) {
                                if(rows==i && cols==j){
                                    continue;
                                }
                                count += map[i][j];
                            }
                    }
                    else{
                        for (int i = rows; i<=rows+1; i++)
                            for (int j = cols-1; j <= cols+1; j++) {
                                if(rows==i && cols==j){
                                    continue;
                                }
                                count += map[i][j];
                            }
                    }
                }
                else if(rows == gameBoard.length-1 ){
                    if (cols == 0){
                        for (int i = rows-1; i<=rows; i++)
                            for (int j = cols; j <= cols+1; j++) {
                                if(rows==i && cols==j){
                                    continue;
                                }
                                count += map[i][j];
                            }
                        }
                    else if (cols == gameBoard[cols].length-1){
                        for (int i = rows-1; i<=rows; i++)
                            for (int j = cols-1; j <= cols; j++) {
                                if(rows==i && cols==j){
                                    continue;
                                }
                                count += map[i][j];
                            }
                        }
                    else{
                        for (int i = rows-1; i<=rows; i++)
                            for (int j = cols-1; j <= cols+1; j++) {
                                if(rows==i && cols==j){
                                    continue;
                                }
                                count += map[i][j];
                            }
                    }
                }
                else if (cols == 0){
                    for (int i = rows-1; i<=rows+1; i++)
                        for (int j = cols; j <= cols+1; j++) {
                            if(rows==i && cols==j){
                                continue;
                            }
                            count += map[i][j];
                        }
                }
                else if (cols == gameBoard[cols].length-1)
                {
                    for (int i = rows-1; i<=rows+1; i++)
                        for (int j = cols-1; j <= cols; j++) {
                            if(rows==i && cols==j){
                                continue;
                            }
                            count += map[i][j];
                        }
                }
                else{
                    for (int i = rows-1; i<=rows+1; i++)
                        for (int j = cols - 1; j <= cols+1; j++) {
                            if(rows==i && cols==j){
                                continue;
                            }
                            count += map[i][j];
                        }
                }

            }
            this.gameBoard[rows][cols] = String.valueOf( count );
            if (count == 0){
                for (int i = rows-1; i<=rows+1; i++)
                    for (int j = cols - 1; j <= cols+1; j++) {
                        try{
                            gameBoard[i][j] = String.valueOf("0");
                        }catch (Exception e){
                        }
                    }
            }
        }

        public void winTheGame() {
            int count = 0;
            int minesCount = ( row * col ) / 4;
            for (int i = 0; i<map.length;i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] == 1 && gameBoard[i][j] == String.valueOf( "X" )) {
                        count++;
                    }
                }
            }
            if (count == minesCount){
                System.out.println("Congratulations, You Win The Game...");
                System.out.println();
                playAgain();
            }
            }
}
