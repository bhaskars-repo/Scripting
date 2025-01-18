function computeRateAndFees(holder) {
    holder.rate = 2.5;
    holder.fees = 1000.0;

    if (holder.amount >= 5000.0 && holder.amount < 10000.0) {
        holder.rate = 2.5;
        holder.fees = 750;
    }
    else if (holder.amount >= 10000.0) {
        holder.rate = 2.75;
        holder.fees = 500;
    }

    return holder;
}
