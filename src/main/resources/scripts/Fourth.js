function computeRate(amount) {
    rate = 2.5;
    if (amount >= 5000.0 && amount < 10000.0) {
        rate = 2.5;
    }
    else if (amount >= 10000.0) {
        rate = 2.75;
    }
    return rate;
}
