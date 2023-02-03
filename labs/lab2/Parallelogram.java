package labs.lab2;

public class Parallelogram implements FigureInterface {

    // #region Class Variables

    protected double base;
    protected double height;
    protected double angleDeg;
    protected double slantSide;

    // #endregion

    // #region Constructors

    public Parallelogram(double base, double height, double angleDeg) {

        this.base = base;
        this.height = height;
        this.angleDeg = angleDeg;

        // This is tricky but here is the source:
        // https://byjus.com/maths/perimeter-of-a-parallelogram/
        slantSide = height / Math.cos(Math.toRadians(angleDeg));

    }

    // #endregion

    // #region Methods

    @Override
    public double area() {
        return base * height;
    }

    @Override
    public double perimeter() {
        // Non-expanded formula: 2 (b + h / cos(angle))
        return (2 * slantSide) + (2 * base);
    }

    // #endregion

}
