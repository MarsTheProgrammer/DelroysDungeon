package com.company;

import java.util.Random;

public class Player {

    public int playerHealth = 100;
    public int playerAttackDamage = 50;
    public int numberOfHealthPotions = 2;
    public int healthPotionHealthAmount = 30;
    public int healthPotionDropRate = 50; //this will be %

    Random random = new Random();

    public boolean checkForSpell (){
        //we also need a way to check if we have the spells from the store first.
        return true;
    }
    public void castSpell() {

        int result = random.nextInt(3);
        if (result == 0) {
            System.out.println("\t You cast the spell known as fireball");
            playerAttackDamage += 10;//need to test this to make sure it is adding when dealing the damage to the enemy
        } else if (result == 1) {
            System.out.println("\t You cast the spell known as ice blast");
            playerAttackDamage += 7;//instead of adding to the amount of damage, this just raises the
                                        //max amount possible. so if 50, now 57.
        } else {//don't need to check for "2" because it has to be at this point.
            System.out.println("\t You cast the spell known as acid spray");
            playerAttackDamage += 20;
        }
    }

    public void healthPotions() {
        if (numberOfHealthPotions > 0) {
            playerHealth += healthPotionHealthAmount;
            numberOfHealthPotions--;
            System.out.println("\t> You drink a health potion, healing yourself for " + healthPotionHealthAmount + " HP"
                    + "\n\t> Your health is now " + playerHealth
                    + "\n\t> You have " + numberOfHealthPotions + " health potion(s) left in your inventory");
        } else {
            System.out.println("\t> You have no health potions left, defeat this enemy for a chance to obtain one.  " +
                    "\n\t> Or, run like a coward and purchase more from the store.");
        }
    }
    public void chanceForHealthPotion() {
        if (random.nextInt(100) < healthPotionDropRate) {
            numberOfHealthPotions++;
            System.out.println(" # The enemy dropped a health potion. #");
            System.out.println(" # You have " + numberOfHealthPotions + " health potion(s). #");
        } else {
            System.out.println("\tThe enemy had nothing on them.");
        }
    }

}
