package de.mpaap.test;

public class LabyrinthWall extends LabyrinthItem{
 
    private int scaledPosX, scaledPosY;
    
    public LabyrinthWall(int scaledPosX, int scaledPosY) {
        this.scaledPosX = scaledPosX;
        this.scaledPosY = scaledPosY;
    }
    
    @Override
    public void print() {
        if (scaledPosX % 2 == 1 && scaledPosY % 2 == 0)
            System.out.print("═");
        else if(scaledPosX % 2 == 0 && scaledPosY % 2 == 1)
            System.out.print("║");
        else
            return;
    }
    
}
