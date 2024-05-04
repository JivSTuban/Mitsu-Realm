package GUI;

import Entities.*;
import Entities.Items.*;
import LoginRegister.LoginForm;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Random;

public class AssetSetter {
    GamePanel gp;
    Random rand = new Random();
    int index=0;


    public int spawnRand() {
        return rand.nextInt(78) + 1;
    }

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setImage(int index, int x, int y, SuperItem item,boolean collision) {
        try {
            gp.objItem[index] = item.getClass().newInstance();
            gp.objItem[index].worldX = x * gp.tileSize;
            gp.objItem[index].worldY = y * gp.tileSize;
            gp.objItem[index].collision = collision;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public void setFootStep(int i, int x, int y) {
        gp.footStep[i] = new FootStep();
        gp.footStep[i].worldX = x * gp.tileSize;
        gp.footStep[i].worldY = y * gp.tileSize;
    }

    public void setItem(LoginForm loginForm) throws SQLException {
       // if (loginForm.getItemCountInDatabase("Salve") < 2) {
            setImage(0, 5, 76, new ItemSalve(), false);
     //   }
     //   if (loginForm.getItemCountInDatabase("Boots") == 0){
            setImage(1,3,78,  new ItemBoots(), false);
      //  }
      //  if (loginForm.getItemCountInDatabase("Salve") < 2) {
            setImage(2,spawnRand(),spawnRand(),  new ItemSalve(),false);
            setImage(9,4,75,  new Chest(),false);
      //  }
     //   if (loginForm.getItemCountInDatabase("AccessCard") < 2) {
            setImage(3,4,78,  new AccessCard(),false);
            setImage(10,10,75,  new Chest(),false);
      //  }
        setImage(4,8,50,  new DoorOpen(),false);
        setImage(5,8,50,  new DoorClose(),true);
        setImage(6,2,19,  new DoorOpen(),false);
        setImage(7,2,19,  new DoorClose(),true);
        setImage(8,1,6,  new DoorOpen(),false);
        setImage(9,1,6,  new DoorClose(),true);

    }
    public void setToxin(int x ,int y) {

        setFootStep(index,x,y);
            index++;
            if(index == 99)
                index = 0;

    }



    public void setNPCChar(int index, int x, int y, Entity npc) {
        try {
            gp.npc[index] = npc.getClass().getDeclaredConstructor(GamePanel.class).newInstance(gp);
            gp.npc[index].worldX = x * gp.tileSize;
            gp.npc[index].worldY = y * gp.tileSize;
        } catch ( InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public void setNPC() {
        setNPCChar(0, 34, 74, new NPC_Drone(gp));
        setNPCChar(1, 54, 65, new NPC_Drone(gp));
        setNPCChar(2, 6, 72, new NPC_Drone(gp));
        setNPCChar(3, 69, 77, new NPC_Drone(gp));
        setNPCChar(4, 5, 77, new NPC_Console(gp));
        setNPCChar(5,5,77,new NPC_Veemax(gp));
        setNPCChar(6,71,5,new NPC_OptimusKhai(gp));//Boss

        setNPCChar(7, 0, 0, new NPC_Drone(gp));;

    }
//    public void footStep(int x, int y) {
//        setFootStep(index, x,y);
//        index++;
//    }
}