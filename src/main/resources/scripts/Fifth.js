function computeRateAndFees(amount) {
    var HashMap = Java.type("java.util.HashMap");
    var map = new HashMap;

    rate = 2.5;
    fees = 1000.0;
    if (amount >= 5000.0 && amount < 10000.0) {
        rate = 2.5;
        fees = 750;
    }
    else if (amount >= 10000.0) {
        rate = 2.75;
        fees = 500;
    }

    map.put("rate", rate);
    map.put("fees", fees);

    return map;
}
