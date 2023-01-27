package labs.lab1;

public class ShoppingBag {

    // Instance variables
    double taxRate = 0;
    int nItems = 0;
    double total = 0;

    // #region Constructors

    public ShoppingBag() {

    }

    public ShoppingBag(double taxRate) {
        this.taxRate = taxRate;
    }

    // #endregion

    // #region Methods

    void place(int n, double unitPrice) {
        total += n * unitPrice;
        nItems += n;
    }

    public int getNumItems() {
        return nItems;
    }

    public double getTotalRetail() {
        return total;
    }

    double getTotal() {
        return Math.round((total * (1 + taxRate)) * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        String result = "The bag contains" + nItems +
                " items.\nThe retail cost of the items is $" + getTotalRetail() +
                ".\n Total cost, including tax rate of " +
                taxRate * 100 + "%, is $" + getTotal();
        return result;
    }

    // #endregion

}