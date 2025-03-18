package su.rj._3.examples._1;

import com.sun.net.httpserver.HttpExchange;

import su.rj._3.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * My {@link com.sun.net.httpserver.HttpHandler} subclass to handle /test.
 */
public class QuitHandler extends HttpHandler {
    public QuitHandler(String[] interfaces) {
        super(interfaces);
    }

    /**
     * @param httpExchange The {@link com.sun.net.httpserver.HttpHandler} to process.
     * @throws IOException It is not our problem, but with http server.
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        final String res = "Pay please to the creator of this website.\n";
        httpExchange.sendResponseHeaders(200,res.length());
        OutputStream responseBody = httpExchange.getResponseBody();
        responseBody.write(res.getBytes(StandardCharsets.UTF_8));
        Main.srv.stop(1);
    }
}
