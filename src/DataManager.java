import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static final String FILE_NAME =
            "data/portfolio.txt";

    private static final String TRANSACTION_FILE =
            "data/transactions.txt";

    public static void savePortfolio(User user) {

        try {

            File directory = new File("data");

            if (!directory.exists()) {
                directory.mkdirs();
            }

            PrintWriter writer =
                    new PrintWriter(
                            new FileWriter(FILE_NAME)
                    );

            writer.println(
                    "USER_ID=" + user.getUserId()
            );

            writer.println(
                    "NAME=" + user.getName()
            );

            writer.println(
                    "BALANCE=" + user.getBalance()
            );

            writer.println("PORTFOLIO");

            for (String symbol :
                    user.getPortfolio()
                            .getHoldings()
                            .keySet()) {

                int quantity =
                        user.getPortfolio()
                                .getHoldings()
                                .get(symbol);

                double purchaseValue =
                        user.getPortfolio()
                                .getPurchasePrices()
                                .get(symbol);

                writer.println(
                        symbol + "," +
                        quantity + "," +
                        purchaseValue
                );
            }

            writer.close();

            System.out.println(
                    "\nPortfolio data saved successfully!"
            );

        } catch (IOException e) {

            System.out.println(
                    "Error while saving portfolio: "
                            + e.getMessage()
            );
        }
    }

    public static void loadPortfolio(User user) {

        File file = new File(FILE_NAME);

        if (!file.exists()) {

            System.out.println(
                    "\nNo previous portfolio data found."
            );

            return;
        }

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(file)
                    );

            String line;

            boolean portfolioSection = false;

            while ((line = reader.readLine()) != null) {

                if (line.startsWith("BALANCE=")) {

                    double balance =
                            Double.parseDouble(
                                    line.substring(8)
                            );

                    user.setBalance(balance);

                    continue;
                }

                if (line.equals("PORTFOLIO")) {

                    portfolioSection = true;
                    continue;
                }

                if (portfolioSection) {

                    String[] data =
                            line.split(",");

                    if (data.length == 3) {

                        String symbol = data[0];

                        int quantity =
                                Integer.parseInt(data[1]);

                        double purchaseValue =
                                Double.parseDouble(data[2]);

                        user.getPortfolio()
                                .setHolding(
                                        symbol,
                                        quantity,
                                        purchaseValue
                                );
                    }
                }
            }

            reader.close();

            System.out.println(
                    "\nPortfolio data loaded successfully!"
            );

        } catch (IOException |
                 NumberFormatException e) {

            System.out.println(
                    "Error while loading portfolio: "
                            + e.getMessage()
            );
        }
    }

    public static void saveTransactions(
            List<Transaction> transactions) {

        try {

            File directory = new File("data");

            if (!directory.exists()) {
                directory.mkdirs();
            }

            PrintWriter writer =
                    new PrintWriter(
                            new FileWriter(
                                    TRANSACTION_FILE
                            )
                    );

            for (Transaction transaction :
                    transactions) {

                writer.println(
                        transaction.toFileFormat()
                );
            }

            writer.close();

            System.out.println(
                    "Transaction history saved successfully!"
            );

        } catch (IOException e) {

            System.out.println(
                    "Error while saving transactions: "
                            + e.getMessage()
            );
        }
    }

    public static List<Transaction> loadTransactions() {

        List<Transaction> transactions =
                new ArrayList<>();

        File file =
                new File(TRANSACTION_FILE);

        if (!file.exists()) {
            return transactions;
        }

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(file)
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data =
                        line.split(",");

                if (data.length == 4) {

                    String type = data[0];

                    String symbol = data[1];

                    int quantity =
                            Integer.parseInt(data[2]);

                    double price =
                            Double.parseDouble(data[3]);

                    transactions.add(
                            new Transaction(
                                    type,
                                    symbol,
                                    quantity,
                                    price
                            )
                    );
                }
            }

            reader.close();

        } catch (IOException |
                 NumberFormatException e) {

            System.out.println(
                    "Error while loading transactions: "
                            + e.getMessage()
            );
        }

        return transactions;
    }
}