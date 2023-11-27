package GameComponents;

public class testClass {
    public static void main(String args[]){
        Player p = new Player(true);
        System.out.println(p.getMaxEnergy()+" "+p.getMaxFile()+" "+p.getResearchNum());
        p.addCard(new UpgradeCard(1, 1, "Black", 1, "Upgrade", "1_0_1"));
        p.File(new UpgradeCard(1, 1, "Black", 1, "Upgrade", "1_0_1"));
        p.addCard(new BuildCard(3,5,"Yellow",5,"Build","Black_Red","File"));
        System.out.println(p.getMaxEnergy()+" "+p.getMaxFile()+" "+p.getResearchNum());
        System.out.println(p.getCardNum());
        System.out.println(p.getCardNum3());
    }
}
