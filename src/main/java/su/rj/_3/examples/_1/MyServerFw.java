package su.rj._3.examples._1;

import su.rj._3.FwHttpServer;

import java.net.InetSocketAddress;

public class MyServerFw extends FwHttpServer {
    public MyServerFw(InetSocketAddress addr) {
        super(addr);
    }

    public MyServerFw(InetSocketAddress addr, int i) {
        super(addr, i);
    }

    @Override
    public void stop(int i) {
        super.stop(i);
        System.out.println("Stopping server!");
    }

    @Override
    protected boolean handle(Throwable e) {
        return false;
    }
}
