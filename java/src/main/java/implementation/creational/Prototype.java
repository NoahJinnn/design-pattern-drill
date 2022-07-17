package implementation.creational;

import java.util.ArrayList;
import java.util.List;

/**
 * Use the Prototype Pattern when creating an instance of a given class is either expensive or complicated
 * Lets you copy existing objects without making your code dependent on their classes
 */
public class Prototype  {
    void main() {
        List<MyCloneable> list = new ArrayList<MyCloneable>();
        List<MyCloneable> copyList = new ArrayList<MyCloneable>();
        RootSample rs = new RootSample("name", "body");
        SubSample ss = new SubSample("subName");
        list.add(rs);
        list.add(ss);
        for (MyCloneable myCloneable : list) {
            copyList.add(myCloneable.clone());
        }
    }
}

class RootSample implements MyCloneable {
    public String name;
    NestObj nestObj;

    public RootSample(String name, String body) {
        this.name = "RootSample";
        this.nestObj = new NestObj(body);
    }

    class NestObj {
        public String body;
        public NestObj(String body) {
            this.body = body;
        }
    }

    public RootSample clone() {
        RootSample clone = new RootSample(this.name, this.nestObj.body);
        return clone;
    }
}

class SubSample implements MyCloneable {
    public String subName;
    public SubSample(String subName) {
        this.subName = subName;
    }

    public SubSample clone() {
        SubSample clone = new SubSample(this.subName);
        return clone;
    }
}

interface MyCloneable {
    public MyCloneable clone();
}