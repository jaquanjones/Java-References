package lab13.model;


public class CXResult {
    final String from;
    final String to;
    final String amount;


    public CXResult(String from, String to, String amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public String convert() {
        double conversion = Double.valueOf(amount);
        double rate = 1;

        if (from.equals(to)) {
            return amount;
        } else if (from.equals("USD")) {
            switch (to) {
                case "JPY":
                    rate = 105.9839;
                case "GBP":
                    rate = 0.7738;
                case "EUR":
                    rate = 0.85;
                default:
                    return "Conversion not found.";
            }
        } else if (from.equals("JPY")) {
            switch (to) {
                case "USD":
                    rate = 0.0094;
                case "GBP":
                    rate = 0.0073;
                case "EUR":
                    rate = 0.008;
                default:
                    return "Conversion not found.";
            }
        } else if (from.equals("GPB")) {
            switch (to) {
                case "USD":
                    rate = 1.2924;
                case "JPY":
                    rate = 136.9693;
                case "EUR":
                    rate = 1.0985;
                default:
                    return "Conversion not found.";
            }
        } else if (from.equals("EUR")) {
            switch (to) {
                case "USD":
                    rate = 1.1765;
                case "JPY":
                    rate = 124.69;
                case "GPB":
                    rate = 0.9104;
                default:
                    return "Conversion not found.";
            }
        }
        return String.valueOf(conversion * rate);
    }
}
