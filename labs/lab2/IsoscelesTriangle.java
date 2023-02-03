package labs.lab2;

public class IsoscelesTriangle implements FigureInterface {

    // #region Class variables

    protected double base;
    protected double height;
    protected double sideLengths;

    // #endregion

    // #region Constructors

    public IsoscelesTriangle(double base, double height) {
        this.base = base;
        this.height = height;

        // An isosceles triangle can be broken into two right triangles
        // The length of the hypotenuse of either right triangle is the
        // length of the congruent sides.
        // Each right triangle has a base of isosceles triangle()'s base divided by 2.
        // Each right triangle shares the same adj side which is the height line that
        // bisects the bigger triangle into the two right triangles.
        // Therefore c^2 = (base / 2)^2 + height^2
        // c = sqrt((base / 2)^2 + height^2)
        // c = sqrt((base^2 / 4) + height^2)
        sideLengths = Math.sqrt(((base * base) / 4) + (height * height));

    }

    // #endregion

    // #region Methods

    @Override
    public double area() {
        return 0.5 * base * height;
    }

    @Override
    public double perimeter() {
        // This formula is only clean because the sideLengths were calculated
        // when the object is instantiated.
        return base + (2 * sideLengths);
    }

    // #endregion

}
