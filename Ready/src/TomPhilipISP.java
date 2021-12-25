import hsa.Console;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class TomPhilipISP {
    Console c;
    int currentX;
    int currentY;
    int[][] surrounding;
    boolean[][] bombs;
    boolean[][] revealed;
    boolean[][] flagged;
    int menu;
    boolean cheat;
    public TomPhilipISP(){
        c = new Console("Minesweeper");
    }
    public void splashScreen(){

    }
    public void mainMenu(){

    }
    public void easy(){
        //8 by 8 board 9 bombs
        c.close(); //close the main menu screen
        c= new Console(16, 40,"Minesweeper - Easy"); //initialize a new console with the proper size
        int[][][] arr = populate(1); //get the populating data
        revealed = new boolean[8][8]; //initialize the game arrays
        bombs = new boolean[8][8];
        flagged = new boolean[8][8];
        surrounding = new int[8][8];
        for(int i = 0; i<8; i++){ //add all the values from populate() into the gam arrays.
            for(int j = 0; j<8; j++){
                bombs[i][j] = arr[1][i][j]==1;
                surrounding[i][j] = arr[0][i][j];
            }
        }
        currentX = 0; //more initializing
        currentY = 0;
        cheat = false;
        boolean end = false; //used to detect whether the game has ended
        boolean first = true; //used to detect whether it is the first move
        long startTime = System.currentTimeMillis();
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                System.out.print(bombs[i][j]+" ");
            }
            System.out.println();
        }
        while(!end){
            System.out.println(currentX+", "+currentY);
            System.out.println(surrounding[currentX][currentY]);
            for(int i = 0; i<8; i++){
                for(int j = 0; j<8; j++){
                    System.out.print(revealed[i][j]+" ");
                }
                System.out.println();
            }
            char ch = c.getChar();
            switch(ch){
                case 'w': {
                    if(currentY!=0) currentY--;
                    break;
                } case 'a': {
                    if(currentX!=0) currentX--;
                    break;
                } case 's': {
                    if(currentY!=7) currentY++;
                    break;
                } case 'd': {
                    if(currentX!=7) currentX++;
                    break;
                } case 'r': {
                    if(first&&bombs[currentX][currentY]){
                        move(currentX, currentY);
                        first = false;
                    }
                    if(cheat){
                        floodFill(currentX, currentY);
                    } else {
                        if(bombs[currentX][currentY]){
                            endGame(false, startTime);
                            end = true;
                        } else {
                            floodFill(currentX, currentY);
                        }
                    }
                    break;
                } case 'f': {
                    if(!revealed[currentX][currentY]){
                        flagged[currentX][currentY] = true;
                    }
                    break;
                } case 'e': {
                    return;
                } case 'c': {
                    cheat = !cheat;
                }

            }
            int count = 0;
            for(int i = 0; i<8; i++){
                for(int j = 0; j<8; j++){
                    if(revealed[i][j]&&!bombs[i][j]){
                        count++;
                    }
                }
            }
            if(count==55){
                endGame(true, startTime);
                return;
            }
            updateAll();
        }
    }
    public void medium(){
        //12 by 24 board 20%
    }
    public void hard(){
        //18 by 30 board 25%
    }
    public void displayScores(){

    }
    public void instructions(){

    }
    public void goodBye(){

    }
    private void pauseProgram(){
        c.print("Press any key to continue: ");
        c.readChar();
    }
    private void updateAll(){
        for(int i = 0; i<revealed.length; i++){
            for(int j = 0; j<revealed[0].length; j++){
                drawSquare(i, j);
            }
        }
    }
    private void drawSquare(int x, int y){
        if(currentX==x&&currentY==y){
            printSolidDarkSquare(40*x, 40*y);
        }
        else{
            printSolidLightSquare(40*x, 40*y);
        }
        if(revealed[x][y]){
            if(bombs[x][y]){
                printBomb(40*x, 40*y);
            } else {
                printNumber(40*x, 40*y, surrounding[x][y]);
            }
        } else {
            if(flagged[x][y]){
                printFlag(40*x, 40*y);
            }
        }
    }
    private void printNumber(int x, int y, int n) {
        c.setFont(new Font("Serif", Font.BOLD, 40));
        switch (n) {
            case 0: {
                c.setColor(new Color(100, 100, 100));
                c.fillRect(x, y, 40, 40);
                break;
            } case 1: {
                c.setColor(new Color(0, 0, 255));
                c.drawString("1", x, y+40);
                break;
            } case 2: {
                c.setColor(new Color(0, 123, 0));
                c.drawString("2", x, y+40);
                break;
            }
        }
    }
    private void printFlag(int x, int y) {

    }
    private void printBomb(int x, int y){

    }
    private void printSolidLightSquare(int x, int y){
        c.setColor(new Color(189, 189, 189));
        c.fillRect(x, y, 40, 40);
        c.setColor(new Color(123, 123, 123));
        for(int i = 0; i<7; i++){
            c.drawLine(x+i, y+40-i, x+40, y+40-i);
            c.drawLine(x+40-i, y+i, x+40-i, y+40);
        }
        c.setColor(new Color(255, 255, 255));
        for(int i = 0; i<7; i++){
            c.drawLine(x+40-i, y+i, x, y+i);
            c.drawLine(x+i, y+40-i, x+i, y);
        }
    }
    private void printSolidDarkSquare(int x, int y){
        c.setColor(new Color(159, 159, 159));
        c.fillRect(x, y, 40, 40);
        c.setColor(new Color(93, 93, 93));
        for(int i = 0; i<7; i++){
            c.drawLine(x+i, y+40-i, x+40, y+40-i);
            c.drawLine(x+40-i, y+i, x+40-i, y+40);
        }
        c.setColor(new Color(225, 225, 225));
        for(int i = 0; i<7; i++){
            c.drawLine(x+40-i, y+i, x, y+i);
            c.drawLine(x+i, y+40-i, x+i, y);
        }
    }
    private void floodFill(int x, int y){
        revealed[x][y] = true;
        if(surrounding[x][y]==0) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    try {
                        if (!revealed[x + i][y + j]) {
                            floodFill(x + i, y + j);
                        }
                    }catch (ArrayIndexOutOfBoundsException ignored) {}
                }
            }
        }
    }
    private int[][][] populate(int difficulty){
        Random rand = new Random();
        int[][][] arr;
        if(difficulty == 1){
            arr = new int[2][8][8];
            for(int i = 0; i<9; i++){
                boolean placed = false;
                while(!placed){
                    int x = rand.nextInt(8);
                    int y = rand.nextInt(8);
                    if(arr[1][x][y]==0){
                        arr[1][x][y] = 1;
                        placed = true;
                    }
                }
            }
        } else if (difficulty==2){
            arr = new int[2][12][24];
            for(int i = 0; i<45; i++){
                boolean placed = false;
                while(!placed){
                    int x = rand.nextInt(12);
                    int y = rand.nextInt(24);
                    if(arr[1][x][y]==0){
                        arr[1][x][y] = 1;
                        placed = true;
                    }
                }
            }
        } else {
            arr = new int[2][18][30];
            for(int i = 0; i<115; i++){
                boolean placed = false;
                while(!placed){
                    int x = rand.nextInt(18);
                    int y = rand.nextInt(30);
                    if(arr[1][x][y]==0){
                        arr[1][x][y] = 1;
                        placed = true;
                    }
                }
            }
        }

        for(int x = 0; x<arr[1].length; x++) {
            for (int y = 0; y < arr[1][0].length; y++) {
                if (arr[1][x][y] == 0) {
                    for (int o = -1; o <= 1; o++) {
                        for (int p = -1; p <= 1; p++) {
                            if (o != 0 && p != 0) {
                                try {
                                    if (arr[1][x + o][y + p] == 1) {
                                        arr[0][x][y]++;
                                    }
                                } catch (ArrayIndexOutOfBoundsException ignored) {}
                            }
                        }
                    }
                }
            }
        }
        return arr;
    }
    private void revealAll(){
        for(int i = 0; i<revealed.length; i++){
            for(int j = 0; j<revealed[0].length; j++){
                revealed[i][j] = true;
            }
        }
    }
    private void move(int x, int y){
        Random rand = new Random();
        boolean found = false;
        while(!found){
            int nX = rand.nextInt(bombs.length);
            int nY = rand.nextInt(bombs[0].length);
            if(!bombs[nX][nY]){
                bombs[nX][nY] = true;
                found = true;

            }
        }
        bombs[x][y] = false;
        for(int i = 0; i<bombs.length; i++){
            for(int j = 0; j<bombs[0].length; j++){
                for (int o = -1; o <= 1; o++) {
                    for (int p = -1; p <= 1; p++) {
                        if (o != 0 && p != 0) {
                            try {
                                if (bombs[x + o][y + p]) {
                                    surrounding[x][y]++;
                                }
                            } catch (ArrayIndexOutOfBoundsException ignored) {}
                        }
                    }
                }
            }
        }
    }
    private void addScore(String score){

    }
    private void endGame(boolean won, long startTime){
        long endTime = System.currentTimeMillis();
    }

    public static void main(String[] args){
        TomPhilipISP t = new TomPhilipISP();
        t.easy();
        System.out.println("Terminated");
    }
}