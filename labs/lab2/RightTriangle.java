package labs.lab2;

public class RightTriangle implements FigureInterface {

    // #region Class Variables

    protected double side1;
    protected double side2;
    protected double hypotenuse;

    // #endregion

    // #region Constructors

    public RightTriangle(double side1, double side2) {
        this.side1 = side1;
        this.side2 = side2;

        // Calculated the hypotenuse using basic pythagorean theorem.
        hypotenuse = Math.sqrt((side1 * side1) + (side2 * side2));

    }

    // #endregion

    // #region Methods

    @Override
    public double area() {
        return (side1 * side2) / 2;
    }

    @Override
    public double perimeter() {
        return side1 + side2 + hypotenuse;
    }

    // #endregion

}
