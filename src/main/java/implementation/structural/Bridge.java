package implementation.structural;

/**
 * Bridge splits a large class into two separate hierarchies - abstraction and implementation
 */
public class Bridge {
    void main() {
        IColor white = new White();
        Rectangle rectangle = new Rectangle(white);
        rectangle.getDescription();
    }
}

/**
 * Example: interface IColorShape has many subclasses likes BlueCircle, RedRectangle, DarkTriangle...
 * Apply Bridge like below
 */

interface IColor {
    String getRGB();
}

class White implements IColor {

    @Override
    public String getRGB() {
        return "#FFF";
    }
}

class Dark implements IColor {

    @Override
    public String getRGB() {
        return "#000";
    }
}

// Bridging shape and color
abstract class IShape {
    IColor color;
    abstract String getDescription();
}

class Rectangle extends IShape {

    public Rectangle(IColor color) {
        this.color = color;
    }

    @Override
    String getDescription() {
        return "I'm rectangle with color: " + this.color.getRGB();
    }
}