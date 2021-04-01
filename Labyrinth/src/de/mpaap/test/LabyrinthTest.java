package de.mpaap.test;

public class LabyrinthTest {
 
    public static void main(String[] args){
            Labyrinth lab = new Labyrinth(5,5, false, true);
            lab.setFinish(1, 2);
            lab.setStart(4,4);
            lab.setTrap(2, 3);
            lab.setTrap(3, 4);
            lab.setWall(3, 4, Labyrinth.WallPlacement.right);
            lab.setWall(2, 3,Labyrinth.WallPlacement.right);
            lab.setWall(3, 3, Labyrinth.WallPlacement.right);
            lab.setWall(3, 2, Labyrinth.WallPlacement.right);
            lab.setWall(2, 2, Labyrinth.WallPlacement.right);
            lab.setWall(3, 3, Labyrinth.WallPlacement.down);
            lab.setWall(1, 0,Labyrinth.WallPlacement.down);
            lab.setWall(2, 0, Labyrinth.WallPlacement.down);
            lab.setWall(2, 0, Labyrinth.WallPlacement.down);
           lab.setWall(3, 0, Labyrinth.WallPlacement.down);
           lab.setWall(2, 1, Labyrinth.WallPlacement.right);
           lab.setWall(2, 1, Labyrinth.WallPlacement.right);
           lab.setWall(1, 1, Labyrinth.WallPlacement.down);
           lab.setWall(0, 2,  Labyrinth.WallPlacement.right);
           lab.setWall(0, 3,  Labyrinth.WallPlacement.right);
           lab.setWall(1, 2,  Labyrinth.WallPlacement.right);
           lab.setWall(1, 3,  Labyrinth.WallPlacement.right);
           lab.setWall(2, 3, Labyrinth.WallPlacement.down);

            lab.print();
            System.out.println("Trap Count: " + lab.countTraps());
            
    }
}

