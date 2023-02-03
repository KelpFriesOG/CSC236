package labs.lab2;

public class Square implements FigureInterface {

    // #region Class Variables

    protected double side;

    // #endregion

    // #region Constructors

    public Square(double side) {
        this.side = side;
    }

    // #endregion

    // #region Methods

    @Override
    public double area() {
        return side * side;
    }

    @Override
    public double perimeter() {
        return 4 * side;
    }

    // #endregion

}
