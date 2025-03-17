package su.rj._3;

import com.sun.net.httpserver.*;
//import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

public abstract class NFwHttpServer implements Server<HttpServer> {
    private HttpServer srv;
    Logger logger;
    public NFwHttpServer(/*@NotNull*/ InetSocketAddress addr){
        try {
            this.srv= HttpServer.create(addr,10);
        } catch (IOException e) {
            if(this.handle(e)){
                throw new RuntimeException(e);
            }
        }
    }

    public NFwHttpServer(/*@NotNull*/ InetSocketAddress addr, int backlog){
        try {
            this.srv= HttpServer.create(addr,backlog);
        } catch (IOException e) {
            if(this.handle(e)){
                throw new ServerError(e);
            }
        }
    }

    public void go(){
        this.srv.start();
    }

    public InetSocketAddress getAddress(){
        return this.srv.getAddress();
    }

    //TODO:Replace by a more secure analog.
    //@Deprecated(forRemoval = true)
    public HttpContext createContext(String where, HttpHandler ctx){
        return this.srv.createContext(where, ctx);
    }

    protected abstract boolean handle(Throwable e);

    public void stop(int i) {
        this.srv.stop(i);
    }

    //TODO:Replace by a more secure analog.
    //@Deprecated(forRemoval = true)
    public HttpServer getSrv() {
        return srv;
    }
}
