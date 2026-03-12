import java.util.*;

class Recipe {
    String name;
    int price;

    Recipe(String name, int price) {
        this.name = name;
        this.price = price;
    }
}

class Order {
    String food;
    String customer;
    int total;

    Order(String food, String customer, int total) {
        this.food = food;
        this.customer = customer;
        this.total = total;
    }
}

public class Main {

    static ArrayList<Recipe> recipes = new ArrayList<>();
    static Stack<Order> orderHistory = new Stack<>();
    static Queue<Order> orderQueue = new LinkedList<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        recipes.add(new Recipe("Chicken Biryani",250));
        recipes.add(new Recipe("Mutton Keema",300));
        recipes.add(new Recipe("Butter Chicken",280));
        recipes.add(new Recipe("Paneer Curry",220));
        recipes.add(new Recipe("Veg Fried Rice",180));

        login();
    }

    static void login() {

        System.out.println("===== Recipe Hub Login =====");

        System.out.print("Username: ");
        String user = sc.nextLine();

        System.out.print("Password: ");
        String pass = sc.nextLine();

        if(user.equals("admin") && pass.equals("1234")) {
            menu();
        } else {
            System.out.println("Invalid Login");
        }
    }

    static void menu() {

        while(true) {

            System.out.println("\n===== Recipe Hub Menu =====");
            System.out.println("1. Show Recipes");
            System.out.println("2. Add Recipe");
            System.out.println("3. Search Recipe");
            System.out.println("4. Sort Recipes by Price");
            System.out.println("5. Order Recipe");
            System.out.println("6. Process Order");
            System.out.println("7. Order History");
            System.out.println("8. Logout");

            System.out.print("Enter Choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch(ch) {

                case 1:
                    showRecipes();
                    break;

                case 2:
                    addRecipe();
                    break;

                case 3:
                    searchRecipe();
                    break;

                case 4:
                    sortRecipes();
                    break;

                case 5:
                    orderRecipe();
                    break;

                case 6:
                    processOrder();
                    break;

                case 7:
                    showOrderHistory();
                    break;

                case 8:
                    System.out.println("Logged Out Successfully");
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    static void showRecipes() {

        System.out.println("\n--- Recipes ---");

        for(int i=0;i<recipes.size();i++) {

            Recipe r = recipes.get(i);
            System.out.println((i+1) + ". " + r.name + " ₹" + r.price);
        }
    }

    static void addRecipe() {

        System.out.print("Enter Recipe Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Price: ");
        int price = sc.nextInt();
        sc.nextLine();

        recipes.add(new Recipe(name, price));

        System.out.println("Recipe Added Successfully");
    }

    static void searchRecipe() {

        System.out.print("Enter Recipe Name to Search: ");
        String key = sc.nextLine();

        boolean found = false;

        for(Recipe r : recipes) {

            if(r.name.equalsIgnoreCase(key)) {

                System.out.println("Found: " + r.name + " ₹" + r.price);
                found = true;
                break;
            }
        }

        if(!found) {
            System.out.println("Recipe Not Found");
        }
    }

    static void sortRecipes() {

        Collections.sort(recipes, Comparator.comparingInt(r -> r.price));

        System.out.println("Recipes Sorted by Price");
    }

    static void orderRecipe() {

        showRecipes();

        System.out.print("Select Recipe Number: ");
        int num = sc.nextInt();
        sc.nextLine();

        if(num < 1 || num > recipes.size()) {
            System.out.println("Invalid Recipe Selection");
            return;
        }

        Recipe r = recipes.get(num-1);

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        int total = r.price * qty;

        Order o = new Order(r.name, name, total);

        orderQueue.add(o);
        orderHistory.push(o);

        System.out.println("Order Placed Successfully");
        System.out.println("Total Bill: ₹" + total);
    }

    static void processOrder() {

        if(orderQueue.isEmpty()) {
            System.out.println("No Orders To Process");
            return;
        }

        Order o = orderQueue.poll();

        System.out.println("Processing Order: " + o.food + " for " + o.customer);
    }

    static void showOrderHistory() {

        if(orderHistory.isEmpty()) {
            System.out.println("No Orders Yet");
            return;
        }

        System.out.println("\n--- Order History ---");

        for(Order o : orderHistory) {

            System.out.println(o.food + " | " + o.customer + " | ₹" + o.total);
        }
    }
}