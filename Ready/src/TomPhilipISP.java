import hsa.Console;
import java.awt.*;
import java.util.Random;

public class TomPhilipISP {
    Console c;
    int currentX;
    int currentY;
    int[][] surrounding;
    boolean[][] bombs;
    boolean[][] revealed;
    boolean[][] flagged;
    int menu = 4;
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
        boolean first = true; //used to detect whether it is the first move
        long startTime = System.currentTimeMillis();
        updateAll();
        while(true){
            char ch = c.getChar();
            switch(ch){
                case 'w': {
                    if(currentY!=0) {
                        currentY--;
                        drawSquare(currentX, currentY);
                        drawSquare(currentX, currentY+1);
                    }
                    break;
                } case 'a': {
                    if(currentX!=0) {
                        currentX--;
                        drawSquare(currentX, currentY);
                        drawSquare(currentX+1, currentY);
                    }
                    break;
                } case 's': {
                    if(currentY!=7) {
                        currentY++;
                        drawSquare(currentX, currentY);
                        drawSquare(currentX, currentY-1);
                    }
                    break;
                } case 'd': {
                    if(currentX!=7) {
                        currentX++;
                        drawSquare(currentX, currentY);
                        drawSquare(currentX-1, currentY);
                    }
                    break;
                } case 'r': {
                    if(first&&bombs[currentX][currentY]){
                        move(currentX, currentY);

                    }
                    if(cheat){
                        floodFill(currentX, currentY);
                    } else {
                        if(bombs[currentX][currentY]){
                            revealAll();
                            updateAll();
                            c.getChar();
                            System.out.println("Clicked Bomb!");
                            return;

                        } else {
                            floodFill(currentX, currentY);
                        }
                    }
                    first = false;
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
        }
    }
    public void medium(){
        //12 by 24 board 20%
        c.close(); //close the main menu screen
        c= new Console(24, 120,"Minesweeper - Easy"); //initialize a new console with the proper size
        int[][][] arr = populate(2); //get the populating data
        revealed = new boolean[24][12]; //initialize the game arrays
        bombs = new boolean[24][12];
        flagged = new boolean[24][12];
        surrounding = new int[24][12];
        for(int i = 0; i<24; i++){ //add all the values from populate() into the gam arrays.
            for(int j = 0; j<12; j++){
                bombs[i][j] = arr[1][i][j]==1;
                surrounding[i][j] = arr[0][i][j];
            }
        }
        currentX = 0; //more initializing
        currentY = 0;
        cheat = false;
        boolean first = true; //used to detect whether it is the first move
        long startTime = System.currentTimeMillis();
        updateAll();
        while(true){
            char ch = c.getChar();
            switch(ch){
                case 'w': {
                    if(currentY!=0) {
                        currentY--;
                        drawSquare(currentX, currentY);
                        drawSquare(currentX, currentY+1);
                    }
                    break;
                } case 'a': {
                    if(currentX!=0) {
                        currentX--;
                        drawSquare(currentX, currentY);
                        drawSquare(currentX+1, currentY);
                    }
                    break;
                } case 's': {
                    if(currentY!=11) {
                        currentY++;
                        drawSquare(currentX, currentY);
                        drawSquare(currentX, currentY-1);
                    }
                    break;
                } case 'd': {
                    if(currentX!=23) {
                        currentX++;
                        drawSquare(currentX, currentY);
                        drawSquare(currentX-1, currentY);
                    }
                    break;
                } case 'r': {
                    if(first&&bombs[currentX][currentY]){
                        move(currentX, currentY);

                    }
                    if(cheat){
                        floodFill(currentX, currentY);
                    } else {
                        if(bombs[currentX][currentY]){
                            revealAll();
                            updateAll();
                            c.getChar();
                            System.out.println("Clicked Bomb!");
                            return;

                        } else {
                            floodFill(currentX, currentY);
                        }
                    }
                    first = false;
                    break;
                } case 'f': {

                        flagged[currentX][currentY] ^= true;
                        drawSquare(currentX, currentY);

                    break;
                } case 'e': {

                    return;
                } case 'c': {
                    cheat = !cheat;
                }

            }
            int count = 0;
            for(int i = 0; i<24; i++){
                for(int j = 0; j<12; j++){
                    if(revealed[i][j]&&!bombs[i][j]){
                        count++;
                    }
                }
            }
            if(count==12*24-45){
                endGame(true, startTime);
                return;
            }
        }
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
        if(currentX==x&&currentY==y&&(surrounding[x][y]!=0||!revealed[x][y])){
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
                if(currentX==x/40&&currentY==y/40){
                    c.setColor(new Color(100, 100, 100));
                } else c.setColor(new Color(140, 140, 140));
                c.fillRect(x, y, 41, 41);
                break;
            } case 1: {
                c.setColor(new Color(0, 0, 255));
                c.drawString("1", x+10, y+35);
                break;
            } case 2: {
                c.setColor(new Color(0, 123, 0));
                c.drawString("2", x+10, y+35);
                break;
            } case 3: {
                c.setColor(new Color(255, 0, 0));
                c.drawString("3", x+10, y+35);
                break;
            } case 4: {
                c.setColor(new Color(0, 0, 123));
                c.drawString("4", x+10, y+35);
                break;
            } case 5: {
                c.setColor(new Color(123, 0, 0));
                c.drawString("5", x+10, y+35);
                break;
            } case 6: {
                c.setColor(new Color(0, 123, 123));
                c.drawString("6", x+10, y+35);
                break;
            } case 7: {
                c.setColor(new Color(0, 0, 0));
                c.drawString("7", x+10, y+35);
                break;
            } case 8: {
                c.setColor(new Color(123, 123, 123));
                c.drawString("8", x+10, y+35);
                break;
            }
        }
    }
    private void printFlag(int x, int y) {
        c.setColor(new Color(255,0,0));
        c.fillPolygon(new int[]{x+9, x+22, x+22}, new int[]{y+13, y+7, y+19}, 3);
        c.setColor(new Color(0, 0, 0));
        c.fillRect(x+20, y+20, 3, 4);
        c.fillRect(x+15, y+25, 10, 3);
        c.fillRect(x+10, y+28,20, 5);
    }
    private void printBomb(int x, int y){
        c.setColor(new Color(0, 0, 0));
        c.fillRect(x+8, y+8, 4, 4);
        c.fillRect(x+8, y+30, 4, 4);
        c.fillRect(x+30, y+30, 4, 4);
        c.fillRect(x+30, y+8, 4, 4);
        c.fillRect(x+19, y+4, 4, 34);
        c.fillRect(x+4, y+19, 34, 4);
        c.fillRect(x+15, y+9, 12, 24);
        c.fillRect(x+9, y+15, 24, 12);
        for(int i = 0; i<5; i++){
            c.drawLine( x+10+i, y+14-i, x+14, y+14-i);
            c.drawLine(x+27, y+14-i, x+31-i, y+14-i);
            c.drawLine( x+10+i, y+27+i, x+14, y+27+i);
            c.drawLine(x+27, y+27+i, x+14, y+27+i);
        }
        c.setColor(new Color(255, 255, 255));
        c.fillRect(x+14, y+14, 5, 5);
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
        drawSquare(x, y);
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
            arr = new int[2][24][12];
            for(int i = 0; i<45; i++){
                boolean placed = false;
                while(!placed){
                    int x = rand.nextInt(24);
                    int y = rand.nextInt(12);
                    if(arr[1][x][y]==0){
                        arr[1][x][y] = 1;
                        placed = true;
                    }
                }
            }
        } else {
            arr = new int[2][30][18];
            for(int i = 0; i<115; i++){
                boolean placed = false;
                while(!placed){
                    int x = rand.nextInt(30);
                    int y = rand.nextInt(18);
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
                            if (o != 0 || p != 0) {
                                try {
                                    if (arr[1][x + o][y + p] == 1) {
                                        arr[0][x][y]++;
                                    }
                                } catch (ArrayIndexOutOfBoundsException ignored) {}
                            }
                        }
                    }
                } else {
                    arr[0][x][y]=-1;
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
        surrounding = new int[bombs.length][bombs.length];
        for(int i = 0; i<bombs.length; i++){
            for(int j = 0; j<bombs[0].length; j++){
                if(!bombs[i][j]) {
                    for (int o = -1; o <= 1; o++) {
                        for (int p = -1; p <= 1; p++) {
                            if (o != 0 || p != 0) {
                                try {
                                    if (bombs[i + o][j + p]) {
                                        surrounding[i][j]++;
                                    }
                                } catch (ArrayIndexOutOfBoundsException ignored) {
                                }
                            }
                        }
                    }
                } else {
                    surrounding[i][j]=-1;
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
        t.splashScreen();
        while(t.menu!=-1){
            if(t.menu==0){
                t.mainMenu();
            } else if(t.menu==1){
                t.instructions();
            } else if(t.menu==2){
                t.displayScores();
            } else if(t.menu==3){
                t.easy();
            } else if(t.menu==4){
                t.medium();
            } else if(t.menu==5){
                t.hard();
            }
        }
        t.goodBye();
    }
}