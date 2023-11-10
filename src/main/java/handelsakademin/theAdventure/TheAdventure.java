package handelsakademin.theAdventure;

import java.util.ArrayList;
import java.util.Scanner;

public class TheAdventure {
    //The two lists which are used in the application are created here
    public ArrayList <Item> itemList = new ArrayList<>();
    public ArrayList<Item> yourItems = new ArrayList<>();

    // The following attributes are used at various places inside TheAdventure-class
    // which is why they are declared here in the beginning.
    private boolean beenToGarden = false;
    private boolean beenToAttic = false;
    private boolean beenToStables = false;
    private String goTo;

    // In the constructor for TheAdventure, 10 Items are added into the itemList.
    // These are the Items that can be chosen from by the user when going to the different places.
    // The list is made up of object created using the Item-class
    public TheAdventure() {
        itemList.add(new Item("Mjolnir", Item.ItemType.Hammer));
        itemList.add(new Item("Gyrid", Item.ItemType.Axe));
        itemList.add(new Item("Ulv", Item.ItemType.Hammer));
        itemList.add(new Item("Proysen", Item.ItemType.Potion));
        itemList.add(new Item("Gertrud", Item.ItemType.Scissors));
        itemList.add(new Item("Verner", Item.ItemType.Hammer));
        itemList.add(new Item("Ubba", Item.ItemType.Potion));
        itemList.add(new Item("Saxo", Item.ItemType.Scissors));
        itemList.add(new Item("Ibsen", Item.ItemType.Axe));
        itemList.add(new Item("Arsbal", Item.ItemType.Hammer));
    }

    //This is the method which is called from the main class.
    // It is in here that the structure of The Adventure is created.
    public void run(){

        boolean acceptMission = false;

        //The user is asked if they are lost as an introduction
        System.out.println("Hello! Are you lost? (Yes/No)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // The user is given the choice to accept the mission or not through a series of questions
        switch (input.toLowerCase()){
            case "no":
                System.out.println("- Ah, then you must be here for the mission? (Yes/No) ");
                String answer = scanner.nextLine();
                switch(answer.toLowerCase()) {
                    case "no":
                        System.out.println("- I see, if you are not here for the mission I can't help you. ");
                        System.out.println("- Continue on your way then, adventurer.");
                        break;
                    case "yes":
                        System.out.println("Brilliant! Thank you.");
                        acceptMission = true;
                        break;
                    default:
                        System.out.println("- I am going to take that answer as a no then. Safe travels!");
                        break;
                }
                break;
            case "yes":
                System.out.println("- I see, a lost adventurer...");
                System.out.println("- We have a problem that is plaguing our kingdom, our prince has been kidnapped!");
                System.out.println("- Would you be willing to take on the mission of saving our prince from the dragon? (Yes/No)");
                String anotherAnswer = scanner.nextLine();
                if(anotherAnswer.equalsIgnoreCase("yes")){
                    System.out.println("- That is wonderful! Thank you.");
                    acceptMission = true;
                }
                else {
                    System.out.println("- I understand, just follow the road straight ahead and you should be fine.");
                }
                break;
            default:
                System.out.println("- Mhm, I can't seem to understand you but you have a good day. Bye!");
                break;
        }

        // If the user accepts the mission they will be asked to introduce themselves.
        if (acceptMission) {
            System.out.println("- Welcome brave adventurer!");
            System.out.println("- What should I call you?");
            input = scanner.nextLine();
            Adventurer theAdventurer = new Adventurer(input);
            System.out.println("- Well met brave " + theAdventurer.getName() + ", you have chosen to undertake the invaluable " +
                    "mission of rescuing the prince from the abominable dragon...");

            // The do-while loop keeps the user inside as long as they don't choose option 6 in the menu-method,
            // which is to give up.
            do {
                // The menu-method is made up of a loop that keeps repeating until  either option 5 or option 6 is chosen.
                // Option 6 will just end the game with a message.
                // Option 5 will let the user challenge the dragon, which brings them into the if-statement.
                menu();

                // This if-statement will determine whether the user will win or lose.
                // To win the user either has to have visited the three different places that exists
                // or their items combined stat has to be above 10.
                if(goTo.equals("dragon")){
                    System.out.println("The abominable dragon is huge up close!");
                    System.out.println("But you, as a brave adventurer, have chosen to challenge it.");
                    System.out.println("Battle!\n");
                    if(beenToStables && beenToAttic && beenToGarden){
                        victory();
                    }
                    else{
                        int attackStrength = 0;
                        for (Item item: yourItems){
                            System.out.println("You attacked with " + item.getName() + "!");
                            attackStrength += item.getStat();
                        }
                        if(attackStrength >= 10){
                            victory();
                        }
                        else{
                            System.out.println("Your stamina is dropping!");
                            while (theAdventurer.getStamina() > 0){
                                System.out.println(theAdventurer.getStamina());
                                theAdventurer.setStamina(theAdventurer.getStamina()-10);
                            }
                            defeat();
                        }
                    }
                    break;
                }
                else
                {
                    goToPlace(goTo);
                }

            } while(!goTo.equals("giveUp"));

        }
    }


    // The menu-method is where the user can make a choice of what to do next.
    private void menu(){

        Scanner scanner = new Scanner(System.in);
        String theChoice = "";

        // The user will have to make a correct choice in order to move on from the menu
        do {
            System.out.println("What do you want to do now?");
            System.out.println("1. Go to the stables");
            System.out.println("2. Go to the attic");
            System.out.println("3. Go to the garden");
            System.out.println("4. Show Item");
            System.out.println("5. Challenge dragon");
            System.out.println("6. Give up...");
            theChoice = scanner.nextLine();

            // The goTo attribute is assigned a different value depending on the input from the user.
            switch (theChoice) {
                case "1":
                    System.out.println("You have chosen to go to the stables");
                    goTo = "stables";
                    break;
                case "2":
                    System.out.println("You have chosen to go to the attic");
                    goTo = "attic";
                    break;
                case "3":
                    System.out.println("You have chosen to go to the garden");
                    goTo = "garden";
                    break;
                case "4":
                    goTo = "displayItems";
                    break;
                case "5":
                    System.out.println("You are now challenging the dragon!!!");
                    goTo = "dragon";
                    break;
                case "6":
                    System.out.println("- You are one scaredy cat... But I will respect your choice, ");
                    System.out.println("   only because you would probably survive the zombie apocalypse though. Bye!");
                    goTo = "giveUp";
                    break;
                default:
                    System.out.println("That choice is not valid");
                    goTo = "Menu";
                    break;
            }
        } while (goTo.equals("Menu"));
        // The goTo variable is returned from this method, is that necessary though?
        // Because the goTo variable is actually already available from the outside
        // So maybe I don't need to return it?
        // Try doing that once and see if it works anyway
        //return goTo;

    }


    // The method choosingItem is activated when a user goes to a place
    // in order for them to get an item added to their yourItems-list
    public void choosingItem() {
        Scanner scanner = new Scanner(System.in);
        int chosenNumber = 0;

        //As long as the user hasn't inserted a correct number, that is a number between 1-11,
        // the following code will continue to be executed.
        boolean insertedCorrectNumber = false;
        while (!insertedCorrectNumber) {
            System.out.println("Write a number between 1 and 11 to get a random item!");
            String input = scanner.nextLine();

            // This next portion is put into a try-catch as we are trying to convert a String into an Int
            try {
                chosenNumber = Integer.parseInt(input);
                // If the user has inputted a number outside the range 1-11
                // they will get message and go back to the beginning of the while-loop
                if (chosenNumber > 11 || chosenNumber < 1) {
                    System.out.println("- Are you a ninnyhammer? The number should be between 1-11!");
                }
                // If they chose the number 1, they don't get an item
                else if(chosenNumber == 1){
                    System.out.println("- Ha! Fooled you, the number one doesn't have an item.");
                    System.out.println("- Good luck anyway, you are definitely going to need it...");
                    insertedCorrectNumber = true;
                }
                // If they have inserted a number between 2-11
                // they will get specific message depending on where they are
                // as well as get an item and choose a name for it, if they so want.
                // The item will also be added to their yourItems list.
                // The user will then go out from the while-loop because the boolean is changed.
                else {
                    int itemChosenNumber = chosenNumber - 2;
                    if (goTo.equals("stables")){
                        System.out.println("- If you were an animal you would be a dolphin! Genius!");
                    }
                    else if (goTo.equals("attic")) {
                        System.out.println("- You are probably not even afraid of spiders, ");
                        System.out.println("   your brilliant mind can surely think of a way to defeat even the hairiest spider in the world!");
                    }
                    else {
                        System.out.println("- Good job at following the instructions.");
                        System.out.println("- I bet you can follow a recipe and bake a mean brownie!");
                        System.out.println("- Mmm mmm mmm");
                    }

                    System.out.println("You chose the number " + chosenNumber);
                    yourItems.add(new Item(itemList.get(itemChosenNumber).getName(), itemList.get(itemChosenNumber).getType()));
                    System.out.println("This gives you the incredible " +
                                        itemList.get(itemChosenNumber).getType() + " called " +
                                        itemList.get(itemChosenNumber).getName() + "!");
                    System.out.println("- Would you like to change the name of your chosen item?");
                    System.out.println("- If you want to, write \" Yes \", otherwise the opportunity will disappear when you press enter.");
                    System.out.println("- You know what they say, no second chances.");
                    System.out.println("Answer: ");
                    input = scanner.nextLine();
                    if(input.equalsIgnoreCase("yes")) {
                        System.out.println("Write what you would like to call it instead:");
                        String inputtedItemName = scanner.nextLine();
                        int lastAddedItem = yourItems.size() - 1;
                        yourItems.get(lastAddedItem).setName(inputtedItemName);
                        System.out.println("The name is changed to " + yourItems.get(lastAddedItem).getName());
                    }
                    insertedCorrectNumber = true;

                }

            }
            // The catch will give the user a message and return them to the beginning of the while-loop
            catch (Exception e) {
                System.out.println("- You pillock, you have to choose a NUMBER!");
            }
        }
    }

    // In the goToPlace-method the user will be "go" to different places based on their choice in method.
    // If they have already visited the place they have visited they will not be able to go back.
    // If it is the first time visiting they will get to choose an item.
    // If they want to display their items a loop will loop through the yourItems array.
    public void goToPlace (String goTo){
        switch(goTo){
            case "garden":
                if (!beenToGarden){
                    System.out.println("The garden is in full bloom.");
                    System.out.println("You have come here to choose an item.");
                    System.out.println("- Choose wisely!");
                    choosingItem();
                    beenToGarden = true;
                }
                else {
                    System.out.println("But you have already visited the garden. \nChoose another place to go.");
                }
                break;

            case "attic":
                if (!beenToAttic){
                    System.out.println("It's windy and dark in the attic...");
                    System.out.println("This is a place to get an important item.");
                    choosingItem();
                    beenToAttic = true;
                }
                else {
                    System.out.println("But you have already visited the attic. \nChoose another place to go.");
                }
                break;

            case "stables":
                if (!beenToStables){
                    System.out.println("All around there is majestic horses and it is very lively.");
                    System.out.println("You have a choice to make here.");
                    choosingItem();
                    beenToStables = true;
                }
                else{
                    System.out.println("But you have already visited the stables. \nChoose another place to go.");
                }
                break;

            case "displayItems":
                if(yourItems.isEmpty()){
                    System.out.println("- You have yet to acquire any items...");
                }

                for (Item Item : yourItems){
                    System.out.println("Item name: " + Item.getName());
                    System.out.println("Item type: " + Item.getType());
                    System.out.println("Item stat: " + Item.getStat() + "\n");
                }
                break;
        }
    }

    // If they manage to defeat the dragon through either having visited all three places
    // or having had good stats, the victory method will be run.
    public void victory(){
        System.out.println("-----------------\n");
        System.out.println("Victory!");
        System.out.println("You have won over the dragon!");
        for( Item Item : yourItems){
            System.out.println("The " + Item.getType() + " " + Item.getName() + " helped you defeat the dragon!");
        }
        System.out.println("\n- We will be forever grateful to you for saving our prince! ");
    }

    // If they don't win the defeat-method will be run instead
    public void defeat(){
        System.out.println("You lost!");
        System.out.println("- You are as strong as uncooked spaghetti!");
        System.out.println("- Just a little force and you snap...");
        System.out.println("The dragon defeated you.");
        System.out.println("The prince will continue to be guarded by the undefeated dragon until someone stronger than you comes along...");
    }

}
