package de.mpaap.test;


public class Labyrinth {

    private static final  String[] fancyWalls = {" ", " " , " ", "═", " ", "╗", "╔", "╦", " ", "╝", "╚", "╩", "║", "╣", "╠", "╬"};
    
    public enum WallPlacement {left, right, up, down};
    private LabyrinthItem[][] labyrinth;
    private int sizeX, sizeY;
    private boolean finishSet, startSet;
    private boolean tileRendering;
    private boolean fancyWallRendering;
    private int trapCount;
    
    public Labyrinth(int sizeX, int sizeY, boolean tileRendering, boolean fancyWallRendering) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.finishSet = false;
        this.startSet = false;
        this.tileRendering = tileRendering;
        this.fancyWallRendering = fancyWallRendering;
        this.trapCount = 0;
        this.labyrinth = new LabyrinthItem[2*this.sizeX + 1][2*this.sizeY + 1];
        
        //initialize labyrinth borders
        for(int x = 0; x < this.sizeX; x++) {
            setWall(x, 0, WallPlacement.up);
            setWall(x, this.sizeY - 1, WallPlacement.down);
        }
        
        for (int y = 0; y < this.sizeY; y++) {
           setWall(0, y, WallPlacement.left);
           setWall(this.sizeX -1, y,WallPlacement.right);  
        }
    }
    
    public void setWall(int tileX, int tileY, WallPlacement placement){
        int xOffset = 0;
        int yOffset = 0;
        
        if(tileX < 0 || tileY < 0 || tileX > sizeX - 1 || tileY > sizeY - 1)
            return;
        
        switch(placement) {
        case down:
            yOffset +=1;
            break;
        case up:
            yOffset -= 1;
            break;
        case left:
            xOffset -=1;
            break;
        case right:
            xOffset +=1;
            break;
            
        }
 
         //place new wall object into labyrinth matrix between tile positions
         int scaledWallX = (tileX * 2 + 1)+ xOffset;
         int scaledWallY = (tileY * 2 + 1)+yOffset;
         labyrinth[scaledWallX][scaledWallY] = new LabyrinthWall(scaledWallX, scaledWallY);        
    }
    
    public void setTrap(int tileX, int tileY) {
        if(labyrinth[tileX * 2 +1][tileY * 2 +1] == null) {
            labyrinth[tileX * 2 +1][tileY * 2 +1] = new LabyrinthTrap();
            trapCount++;
        }   
    }
    
    public int countTraps() {
        return trapCount;
    }
   
    public void setStart(int tileX, int tileY) {
        if(!startSet) {
            startSet = true;
            labyrinth[tileX * 2 +1][tileY * 2 +1] = new  LabyrinthStart();
        }
    }
   
    public void setFinish(int tileX, int tileY) {
        if(!finishSet) {
            finishSet = true;
            labyrinth[tileX * 2 +1][tileY * 2 +1] = new  LabyrinthFinish();
        }
    }
    
    
    
    public void print() {
        for(int y = 0; y <(2* this.sizeY) +1; y++) {
            for (int x = 0; x < (2* this.sizeX) +1; x++) {
                //print all objects in labyrinth matrix
                if(labyrinth[x][y] != null)
                    labyrinth[x][y].print();
                
                //for all matrix entries that are not set...
                else {
                    //...if the entry is a tile and if tileRendering is enabled, we can print boxes for each tile of the labyrinth
                    if(x % 2 == 1 && y% 2 == 1&& x>0 && y >0 && y < (2* this.sizeY) && x < (2*this.sizeX) && tileRendering){
                        String box = "□";
                        System.out.print(box);
                    }
                   
                    else {
                        //... if entry is a diagonal wall coordinate and fancy wall rendering is enabled, render them
                        if(x % 2 == 0 && y % 2 == 0 && fancyWallRendering) {                       
                           System.out.print(fancyWalls[(int) generateFancyWalls(x, y)]);       
                        }
                        else {
                            System.out.print(" ");
                        }
                    }  
                }
            }
                System.out.println("");
        } 
    }
    
    private int generateFancyWalls(int x, int y) {
        int leftX = x - 1;
        int upperY = y -1;
        int rightX = x + 1;
        int lowerY = y +1;
        
       int upper, lower, right, left;
        upper = lower = right = left = 0;
        
        //check if diagonal wall position is surrounded by walls from top, bottom, left or right
        if (upperY >= 0 && labyrinth[x][upperY] instanceof LabyrinthWall)
            upper = 1;
        
        if(lowerY < (2* this.sizeY) +1 && labyrinth[x][lowerY] instanceof LabyrinthWall)
            lower =1;
        
        if(leftX >= 0 && labyrinth[leftX][y] instanceof LabyrinthWall)
            left = 1;
        
        if(rightX < (2* this.sizeX) +1 && labyrinth[rightX][y] instanceof LabyrinthWall)
            right = 1;
        
        //generate a byte code corresponding to which sides are surrounded by walls
        byte fancyWallsSelector =  0;
        fancyWallsSelector |= upper << 3;
        fancyWallsSelector |= lower << 2;
        fancyWallsSelector |= right << 1;
        fancyWallsSelector |= left << 0;
        
        //return byte code so the right connecting ascii letter can be looked up in fancyWalls array
        return (int) fancyWallsSelector;  
    }  
}
