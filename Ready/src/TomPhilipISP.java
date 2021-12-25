import hsa.Console;

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
        c.close();
        c= new Console("Minesweeper - Easy");
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

    }
    private void drawSquare(int x, int y){

    }
    private void printNumber(int x, int y, int n){

    }
    private void printFlag(int x, int y) {

    }
    private void printBomb(int x, int y){

    }
    private void printSolidLightSquare(int x, int y){

    }
    private void printSolidDarkSquare(int x, int y){

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

    }
    private void move(int x, int y){

    }
    private void addScore(String score){

    }
    private void endGame(){

    }
}