package su.rj._3;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

public abstract class FwHttpServer implements Server<HttpServer> {
    private HttpServer srv;
    Logger logger;
    private ServerStates state;
    public FwHttpServer(@NotNull InetSocketAddress addr){
        try {
            state=ServerStates.VALID;
            this.srv = HttpServer.create(addr,10);
        } catch (IOException e) {
            if(this.handle(e)){
                throw new RuntimeException(e);
            }
        }
    }

    public FwHttpServer(@NotNull InetSocketAddress addr, int backlog){
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

    @NotNull
    public InetSocketAddress getAddress(){
        return this.srv.getAddress();
    }

    /**
     * Refer to createContext method in {@link com.sun.net.httpserver.HttpServer}.
     * Deprecated:replace with bind method.
     * @param where The path to associate with context.
     * @param ctx The {@link com.sun.net.httpserver.HttpHandler} to associate with context
     * @return The created HttpContext
     */
    @Deprecated
    public HttpContext createContext(String where, HttpHandler ctx){
        try {
            return this.srv.createContext(where, ctx);
        } catch (NullPointerException e) {
            this.state=ServerStates.INVALID;
            if(handle(e))
                throw new RuntimeException(e);
            return null;
        }
    }

    public void bindHandler(@NotNull su.rj._3.HttpHandler handler){
        for(String s:handler.getRelAddressList()){
            try {
                try {
                    this.srv.removeContext(s);
                } catch (IllegalArgumentException ignored){}
                this.srv.createContext(s, handler);
            } catch (NullPointerException e) {
                this.state=ServerStates.INVALID;
                if(handle(e))
                    throw new RuntimeException(e);
            }
        }
    }

    protected abstract boolean handle(@NotNull Throwable e);

    public void stop(int i) {
        this.srv.stop(i);
    }

    public HttpServer getSrv() {
        this.state = ServerStates.DESTRUCTIBLE;
        return srv;
    }

    public ServerStates getState() {
        return state;
    }
}
