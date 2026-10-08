import java.util.HashMap;
import java.util.Map;

public class Market {

    private Map<String, Stock> stocks;

    public Market() {
        stocks = new HashMap<>();

        addDefaultStocks();
    }

    private void addDefaultStocks() {

        stocks.put("TCS",
                new Stock("TCS",
                        "Tata Consultancy Services",
                        3800));

        stocks.put("INFY",
                new Stock("INFY",
                        "Infosys Limited",
                        1650));

        stocks.put("RELIANCE",
                new Stock("RELIANCE",
                        "Reliance Industries",
                        2900));

        stocks.put("HDFCBANK",
                new Stock("HDFCBANK",
                        "HDFC Bank",
                        1750));

        stocks.put("ITC",
                new Stock("ITC",
                        "ITC Limited",
                        520));
    }

    public void displayMarket() {

        System.out.println("\n================ MARKET DATA ================");

        System.out.printf(
                "%-10s %-25s %s%n",
                "Symbol",
                "Company",
                "Price"
        );

        System.out.println("----------------------------------------------");

        for (Stock stock : stocks.values()) {
            stock.displayStock();
        }
    }

    public Stock getStock(String symbol) {
        return stocks.get(symbol.toUpperCase());
    }
}