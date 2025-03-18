package su.rj._3.examples._1;

import com.sun.net.httpserver.HttpExchange;

import su.rj._3.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * My {@link com.sun.net.httpserver.HttpHandler} subclass to handle /test.
 */
public class StaticResourceHandler extends HttpHandler {
    public StaticResourceHandler(String[] interfaces) {
        super(interfaces);
    }

    /**
     * @param httpExchange I am not sure wht it does
     * @throws IOException It is not our problem, but with http server.
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        final String res = "It is a stub. More things will be here. \n";
        httpExchange.sendResponseHeaders(503,res.length());
        OutputStream resbody = httpExchange.getResponseBody();
        resbody.write(res.getBytes(StandardCharsets.UTF_8));
    }
}
