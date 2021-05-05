package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        Player delroy = new Player();

        String[] enemies = {"Skeleton", "Zombie", "Assassin", "Giant Spider", "Goblin",
                                "Dwarf", "Soldier", "Dragon", "Demon", "Orc"};
        int maxEnemyHealth = 75;
        int enemyAttackDamage = 25;
        int healthPotionDropRate = 50; //this will be %

        boolean running = true;

        System.out.println("Welcome to Delroy's Dungeon");

        GAME:
        while(running) {
            System.out.println("----------------------------------------------------------");

            int enemyHealth = random.nextInt(maxEnemyHealth);

            String enemy = enemies[random.nextInt(enemies.length)];
            //this grabs random enemy from the String[]

            System.out.println("\t# " + enemy + " has appeared! #\n");

            COMBAT:
            while (enemyHealth > 0) {
                System.out.println("\tYour HP: " + delroy.playerHealth);
                System.out.println("\t" + enemy + "'s HP: " + enemyHealth + "\n");
                System.out.println("\t1. Attack with weapon");
                System.out.println("\t2. Cast Magic Spell");
                System.out.println("\t3. Drink health potion");
                System.out.println("\t4. Run!");

                String input = scanner.nextLine();
                switch (input) {
                    case "1":
                        int damageDealt = random.nextInt(delroy.playerAttackDamage);
                        int damageTaken = random.nextInt(enemyAttackDamage);
                        //we will need to add weapons later.

                        enemyHealth -= damageDealt;
                        delroy.playerHealth -= damageTaken;

                        System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage");
                        System.out.println("\t> You receive " + damageTaken + " damage, by " + enemy);

                        if (delroy.playerHealth < 1) {
                            System.out.println("\t> You have taken too much damage and you are too weak to continue.");
                            //lets consider changing this to something more aggressive.
                            break;
                        }
                        break;
                    case "2":
                        delroy.checkForSpell();//Do we really need this twice?
                        if (delroy.checkForSpell()) {
                            delroy.castSpell();

                            damageDealt = random.nextInt(delroy.playerAttackDamage);
                            damageTaken = random.nextInt(enemyAttackDamage);

                            enemyHealth -= damageDealt;
                            delroy.playerHealth -= damageTaken;

                            System.out.println("\t> You strike " + enemy + " with your spell, dealing " + damageDealt + " damage");
                            System.out.println("\t> You receive " + damageTaken + " damage, by " + enemy);

                            if (delroy.playerHealth < 1) {
                                System.out.println("\t> You have taken too much damage and you are too weak to continue.");
                                //lets consider changing this to something more aggressive.
                                break;
                            }
                        } else {
                            System.out.println("\t> You do not know magic. Consult with the Wizard at the store");
                        }
                        break;

                    case "3"://health potion
                        delroy.healthPotions();
                        break;
                    case "4"://run
                        System.out.println("\tYou have chosen to run away from " + enemy + " like the swine that you are.");
                        continue GAME;//breaks from combat loop.

                    default:
                        System.out.println("\tYou have entered an unknown language.");//Something like that.
                        break;
                }//end of switch statement
                if (delroy.playerHealth < 1) {//this handles dying
                    System.out.println("You crawl out of the dungeon. Weak and pitiful.");
                    break COMBAT;
                }
            }//end of combat loop

            System.out.println("---------------------------------------------------------------------");
            System.out.println(" # " + enemy + " was defeated! #");
            System.out.println(" # You have " + delroy.playerHealth + " HP left. #");

            if (random.nextInt(100) < healthPotionDropRate) {
                delroy.numberOfHealthPotions++;
                System.out.println(" # The enemy dropped a health potion. #");
                System.out.println(" # You have " + delroy.numberOfHealthPotions + " health potion(s). #");
            } else {
                System.out.println(" # The enemy had nothing on them #");
            }

            System.out.println("---------------------------------------------------------------------");
            System.out.println("What would you like to do next?");
            System.out.println("1. Continue your quest");
            System.out.println("2. Exit Delroy's Dungeon");

            String leaving = scanner.nextLine();
            switch (leaving) {
                case "1":
                    System.out.println("You continue your adventure");
                case "2":
                    System.out.println("You exit the dungeon");
                    //we will need to add how much money and level you got to, ect.
                default:
                    System.out.println("Please enter your response in a known language");
            }
        }//end of game while loop

        System.out.println("######################");
        System.out.println("# THANKS FOR PLAYING #");
        System.out.println("######################");


        /*
        Just a few issues with the game at this point:
            1. When on "do next" screen, if no user input, the game will just continue instead of re-looping and asking for input.
            2. Need to work on the spacing of the after damage report part, and HP part.
            3. We cannot die. We need to be able to die. For now, it just seems that when health = 0 or less, the game still continues.
            4. Leaving the dungeon does not work. It gives default, then returns to GAME loop. Look into leaving String answer.


        */

    }//end of psvm
}//end of main class
