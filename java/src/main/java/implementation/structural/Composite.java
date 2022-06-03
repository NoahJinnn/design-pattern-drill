package implementation.structural;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite composes objects into tree structure to represent part-whole hierarchy
 * Lets client treat individual object and composition of objects uniformly
 */
public class Composite {
    void main() {
        IComponent hamburger = new MenuItem("Hamburger");
        IComponent sandwich = new MenuItem("Sandwich");
        IComponent morningMenu = new Menu("Morning menu");
        morningMenu.addChild(hamburger);
        morningMenu.addChild(sandwich);
        // ... keep build tree hierarchy
    }
}

abstract class IComponent {

    String description;

    void addChild(IComponent child) {
        throw new UnsupportedOperationException(); // default behavior for leaf
    }

    void removeChild(IComponent child) {
        throw new UnsupportedOperationException();
    }

    List<IComponent> getChilds() {
        throw new UnsupportedOperationException();
    }

    abstract void print();
}

class Menu extends IComponent {

    List<IComponent> children = new ArrayList<>();

    public Menu(String description) {
        this.description = description;
    }

    @Override
    void addChild(IComponent child) {
        this.children.add(child);
    }

    @Override
    void removeChild(IComponent child) {
        this.children.remove(child);
    }

    @Override
    List<IComponent> getChilds() {
        return this.children;
    }

    @Override
    void print() {
        for (IComponent child : this.children) {
            child.print(); // recursive printing until meet leaf
        }
    }
}

class MenuItem extends IComponent {

    public MenuItem(String description) {
        this.description = description;
    }

    @Override
    void print() {
        System.out.println("Describe myself...");
    }
}
