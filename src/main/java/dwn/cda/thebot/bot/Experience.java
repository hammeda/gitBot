package dwn.cda.thebot.bot;

public class Experience {
    public static int experience = 0;
    public static boolean aGagne = false;

    public static int gagne(){
        if(aGagne){
            experience += 1;
        }
        return experience;
    }

    public static int perd(){
        if(aGagne){
            experience -= 1;
        }
        return experience;
    }
}


