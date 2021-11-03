package implementation.structural;

import lombok.Getter;
import lombok.Setter;

/**
 * Proxy provides a representative for another object in order to control client's access to it
 * There're number of proxy type: caching proxies, synchronization proxies, firewall proxies, copy-on-write proxies
 */
public class ProxyPattern {
    void main() {
        ProtectionProxy protectionProxy = new ProtectionProxy();
        protectionProxy.request();
    }
}

// This interface defines how client access/request/use main object
interface ISubject {
    void request();
}

class RealSubject implements ISubject {

    @Override
    public void request() {
        System.out.println("Do complicated things");
    }
}

class ProtectionProxy implements ISubject {

    RealSubject realSubject = new RealSubject();

    @Getter
    @Setter
    private boolean allowedByMe = false;

    @Override
    public void request() {
        if(this.allowedByMe) {
            realSubject.request();
        }
    }

}