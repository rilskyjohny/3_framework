package su.rj._3;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public abstract class HttpHandler implements com.sun.net.httpserver.HttpHandler {
    private final String[] relAdressList;
    public HttpHandler(String[] interfaces) {
        this.relAdressList = interfaces;
    }
    public abstract void handle(HttpExchange httpExchange) throws IOException;
    public String[] getRelAddressList() {
        return relAdressList;
    }
}
