import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String type;
    private String stockSymbol;
    private int quantity;
    private double price;
    private LocalDateTime dateTime;

    public Transaction(
            String type,
            String stockSymbol,
            int quantity,
            double price) {

        this.type = type;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;

        this.dateTime =
                LocalDateTime.now();
    }

    public String getType() {
        return type;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String toFileFormat() {

        return type + "," +
                stockSymbol + "," +
                quantity + "," +
                price;
    }

    public void displayTransaction() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                        "dd-MM-yyyy HH:mm:ss"
                );

        double total =
                quantity * price;

        System.out.printf(
                "Rs. %-10.2f Rs. %-10.2f %s%n",
                type,
                stockSymbol,
                quantity,
                price,
                total,
                dateTime.format(formatter)
        );
    }
}