public class User {

    private int userId;
    private String name;
    private double balance;
    private Portfolio portfolio;

    public User(int userId, String name, double balance) {
        this.userId = userId;
        this.name = name;
        this.balance = balance;
        this.portfolio = new Portfolio();
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean withdraw(double amount) {

        if (amount <= balance) {
            balance -= amount;
            return true;
        }

        return false;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void displayUser() {

        System.out.println(
                "\n========== USER DETAILS =========="
        );

        System.out.println(
                "User ID   : " + userId
        );

        System.out.println(
                "Name      : " + name
        );

        System.out.printf(
                "Balance   : ₹%.2f%n",
                balance
        );
    }
}