import java.util.HashMap;
import java.util.Map;

public class Portfolio {

    private Map<String, Integer> holdings;
    private Map<String, Double> purchasePrices;

    public Portfolio() {
        holdings = new HashMap<>();
        purchasePrices = new HashMap<>();
    }

    public void buyStock(String symbol, int quantity, double price) {

        holdings.put(
                symbol,
                holdings.getOrDefault(symbol, 0) + quantity
        );

        purchasePrices.put(
                symbol,
                purchasePrices.getOrDefault(symbol, 0.0)
                        + (quantity * price)
        );
    }

    public boolean sellStock(String symbol, int quantity, double price) {

        int owned = holdings.getOrDefault(symbol, 0);

        if (owned < quantity) {
            return false;
        }

        int remaining = owned - quantity;

        double totalPurchaseValue =
                purchasePrices.get(symbol);

        double averagePrice =
                totalPurchaseValue / owned;

        double remainingPurchaseValue =
                averagePrice * remaining;

        if (remaining == 0) {
            holdings.remove(symbol);
            purchasePrices.remove(symbol);
        } else {
            holdings.put(symbol, remaining);
            purchasePrices.put(symbol, remainingPurchaseValue);
        }

        return true;
    }

    public int getQuantity(String symbol) {
        return holdings.getOrDefault(symbol, 0);
    }

    public Map<String, Integer> getHoldings() {
        return holdings;
    }

    public Map<String, Double> getPurchasePrices() {
        return purchasePrices;
    }

    public void setHolding(
            String symbol,
            int quantity,
            double purchaseValue) {

        holdings.put(symbol, quantity);
        purchasePrices.put(symbol, purchaseValue);
    }

    public double getTotalInvested() {

        double total = 0;

        for (double value : purchasePrices.values()) {
            total += value;
        }

        return total;
    }

    public double getCurrentValue(Market market) {

        double total = 0;

        for (Map.Entry<String, Integer> entry
                : holdings.entrySet()) {

            Stock stock =
                    market.getStock(entry.getKey());

            if (stock != null) {

                total += stock.getPrice()
                        * entry.getValue();
            }
        }

        return total;
    }

    public void displayPortfolio(Market market) {

        System.out.println(
                "\n================ PORTFOLIO ================"
        );

        if (holdings.isEmpty()) {

            System.out.println(
                    "Your portfolio is empty."
            );

            return;
        }

        System.out.printf(
                "%-10s %-10s %-15s %-15s%n",
                "Symbol",
                "Quantity",
                "Invested",
                "Current Value"
        );

        System.out.println(
                "--------------------------------------------"
        );

        for (Map.Entry<String, Integer> entry
                : holdings.entrySet()) {

            String symbol = entry.getKey();
            int quantity = entry.getValue();

            Stock stock =
                    market.getStock(symbol);

            double invested =
                    purchasePrices.get(symbol);

            double currentValue = 0;

            if (stock != null) {

                currentValue =
                        stock.getPrice() * quantity;
            }

            System.out.printf(
                    "%-10s %-10d ₹%-14.2f ₹%-14.2f%n",
                    symbol,
                    quantity,
                    invested,
                    currentValue
            );
        }
    }
}