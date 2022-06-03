package implementation.behavioral;

import java.util.Arrays;

/**
 * Iterator encapsulates traversing operator of aggregate object ( aggregator ) into iterator object
 */
public class Iterator {

}

interface IIterator<T> {
    boolean hasNext();
    T next();
    void remove();
}

class DinerMenuIterator implements IIterator<String> {

    String[] menu;
    int idx = 0;

    public DinerMenuIterator(String[] menu) {
        this.menu = menu;
    }

    @Override
    public boolean hasNext() {
        if(idx < menu.length || menu[idx] != null) return true;
        return false;
    }

    @Override
    public String next() {
        String val = menu[idx];
        idx++;
        return val;
    }

    @Override
    public void remove() {
        if(this.menu.length > 0) {
            this.menu = Arrays.copyOf(this.menu, this.menu.length - 1);
        }
    }
}

interface IAggregator<T> {
    IIterator<T> getIterator();
}

class DinerMenu implements IAggregator<String> {

    String[] menus = {"A", "B", "C"};
    IIterator<String> menuIterator = new DinerMenuIterator(menus);

    @Override
    public IIterator<String> getIterator() {
        return this.menuIterator;
    }
}
