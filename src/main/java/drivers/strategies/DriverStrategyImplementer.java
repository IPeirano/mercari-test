package drivers.strategies;

public class DriverStrategyImplementer {

    public static DriverStrategy selectedStrategy(String strategy) {
        return switch (strategy) {
            case "Chrome" -> new Chrome();
            case "Firefox" -> new Firefox();
            default -> null;
        };
    }
}
