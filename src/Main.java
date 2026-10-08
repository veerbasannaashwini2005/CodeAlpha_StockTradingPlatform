public class Main {

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("      WELCOME TO STOCK TRADING PLATFORM");
        System.out.println("==========================================");

        User user =
                new User(
                        1001,
                        "Ashwini",
                        100000
                );

        // Automatically load saved portfolio
        DataManager.loadPortfolio(user);

        TradingPlatform platform =
                new TradingPlatform(user);

        platform.start();
    }
}