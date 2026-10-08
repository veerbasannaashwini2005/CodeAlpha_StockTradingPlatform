
import java.util.List;
import java.util.Scanner;

public class TradingPlatform {

    private User user;
    private Market market;
    private List<Transaction> transactions;
    private Scanner scanner;

    public TradingPlatform(User user) {

        this.user = user;
        this.market = new Market();

        // Load previously saved transactions
        this.transactions =
                DataManager.loadTransactions();

        this.scanner = new Scanner(System.in);
    }

    public void start() {

        int choice;

        do {

            displayMenu();

            System.out.print("Enter your choice: ");

            try {

                choice =
                        Integer.parseInt(
                                scanner.nextLine()
                        );

                switch (choice) {

                    case 1:
                        market.displayMarket();
                        break;

                    case 2:
                        buyStock();
                        break;

                    case 3:
                        sellStock();
                        break;

                    case 4:
                        user.getPortfolio()
                                .displayPortfolio(market);
                        break;

                    case 5:
                        displayTransactions();
                        break;

                    case 6:
                        displayPerformance();
                        break;

                    case 7:
                        user.displayUser();
                        break;

                    case 8:
                        saveData();
                        break;

                    case 9:
                        DataManager.loadPortfolio(user);
                        break;

                    case 10:
                        System.out.println(
                                "\nThank you for using Stock Trading Platform!"
                        );
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please try again."
                        );
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );

                choice = 0;
            }

        } while (choice != 10);
    }

    private void displayMenu() {

        System.out.println("\n==========================================");
        System.out.println("        STOCK TRADING PLATFORM");
        System.out.println("==========================================");
        System.out.println("1. View Market Data");
        System.out.println("2. Buy Stock");
        System.out.println("3. Sell Stock");
        System.out.println("4. View Portfolio");
        System.out.println("5. Transaction History");
        System.out.println("6. Portfolio Performance");
        System.out.println("7. User Details");
        System.out.println("8. Save Portfolio");
        System.out.println("9. Load Saved Data");
        System.out.println("10. Exit");
        System.out.println("==========================================");
    }

    private void buyStock() {

        market.displayMarket();

        System.out.print("\nEnter stock symbol: ");

        String symbol =
                scanner.nextLine().toUpperCase();

        Stock stock =
                market.getStock(symbol);

        if (stock == null) {

            System.out.println(
                    "Stock not found."
            );

            return;
        }

        System.out.print("Enter quantity: ");

        try {

            int quantity =
                    Integer.parseInt(
                            scanner.nextLine()
                    );

            if (quantity <= 0) {

                System.out.println(
                        "Quantity must be greater than zero."
                );

                return;
            }

            double total =
                    stock.getPrice() * quantity;

            if (user.withdraw(total)) {

                user.getPortfolio()
                        .buyStock(
                                symbol,
                                quantity,
                                stock.getPrice()
                        );

                transactions.add(
                        new Transaction(
                                "BUY",
                                symbol,
                                quantity,
                                stock.getPrice()
                        )
                );

                System.out.printf(
                        "Purchase successful! Total: Rs. %.2f%n",
                        total
                );

            } else {

                System.out.println(
                        "Insufficient balance."
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid quantity."
            );
        }
    }

    private void sellStock() {

        System.out.print(
                "\nEnter stock symbol: "
        );

        String symbol =
                scanner.nextLine().toUpperCase();

        Stock stock =
                market.getStock(symbol);

        if (stock == null) {

            System.out.println(
                    "Stock not found."
            );

            return;
        }

        int owned =
                user.getPortfolio()
                        .getQuantity(symbol);

        if (owned == 0) {

            System.out.println(
                    "You do not own this stock."
            );

            return;
        }

        System.out.println(
                "You currently own: " + owned
        );

        System.out.print(
                "Enter quantity to sell: "
        );

        try {

            int quantity =
                    Integer.parseInt(
                            scanner.nextLine()
                    );

            if (quantity <= 0) {

                System.out.println(
                        "Quantity must be greater than zero."
                );

                return;
            }

            if (quantity > owned) {

                System.out.println(
                        "You cannot sell more than you own."
                );

                return;
            }

            boolean success =
                    user.getPortfolio()
                            .sellStock(
                                    symbol,
                                    quantity,
                                    stock.getPrice()
                            );

            if (success) {

                double total =
                        stock.getPrice() * quantity;

                user.deposit(total);

                transactions.add(
                        new Transaction(
                                "SELL",
                                symbol,
                                quantity,
                                stock.getPrice()
                        )
                );

                System.out.printf(
                        "Sale successful! Received: ₹%.2f%n",
                        total
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid quantity."
            );
        }
    }

    private void displayTransactions() {

        System.out.println(
                "\n================ TRANSACTION HISTORY ================"
        );

        if (transactions.isEmpty()) {

            System.out.println(
                    "No transactions found."
            );

            return;
        }

        System.out.printf(
                "%-8s %-10s %-10s %-13s %-13s %s%n",
                "Type",
                "Symbol",
                "Quantity",
                "Price",
                "Total",
                "Date"
        );

        System.out.println(
                "----------------------------------------------------------------"
        );

        for (Transaction transaction :
                transactions) {

            transaction.displayTransaction();
        }
    }

    private void displayPerformance() {

        double invested =
                user.getPortfolio()
                        .getTotalInvested();

        double currentValue =
                user.getPortfolio()
                        .getCurrentValue(market);

        double cash =
                user.getBalance();

        double totalPortfolio =
                currentValue + cash;

        double profitLoss =
                currentValue - invested;

        System.out.println(
                "\n========== PORTFOLIO PERFORMANCE =========="
        );

        System.out.printf(
                "Cash Balance       : Rs. %.2f%n",
                cash
        );

        System.out.printf(
                "Amount Invested    : Rs. %.2f%n",
                invested
        );

        System.out.printf(
                "Current Stock Value: Rs. %.2f%n",
                currentValue
        );

        System.out.printf(
                "Total Portfolio    : Rs. %.2f%n",
                totalPortfolio
        );

        System.out.printf(
                "Profit / Loss      : Rs. %.2f%n",
                profitLoss
        );
    }

    private void saveData() {

        DataManager.savePortfolio(user);

        DataManager.saveTransactions(
                transactions
        );
    }
}