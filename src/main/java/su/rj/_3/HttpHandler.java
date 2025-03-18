package su.rj._3;

import com.sun.net.httpserver.HttpExchange;

public abstract class HttpHandler implements com.sun.net.httpserver.HttpHandler {
    private final String[] relAdressList;
    public HttpHandler(String[] interfaces) {
        this.relAdressList = interfaces;
    }
    public abstract void handle(HttpExchange httpExchange);
    public String[] getRelAddressList() {
        return relAdressList;
    }
}
