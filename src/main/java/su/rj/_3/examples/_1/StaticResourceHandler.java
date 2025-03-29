package su.rj._3.examples._1;

import com.sun.net.httpserver.HttpExchange;

import org.jetbrains.annotations.NotNull;
import su.rj._3.FwHttpServer;
import su.rj._3.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.charset.Charset;
import java.util.SortedMap;

/**
 * My {@link com.sun.net.httpserver.HttpHandler} subclass to handle /test.
 */
public class StaticResourceHandler extends HttpHandler {
    protected InputStream loadRes(String name) throws IOException{
        URL res = this.getClass().getResource(name);
        if(res!=null) return res.openStream();
        return null;
    }
    public StaticResourceHandler(String[] interfaces) {
        super(interfaces);
    }

    /**
     * @param httpExchange I am not sure wht it does
     * @throws IOException It is not our problem, but with http server.
     */
    @Override
    public void handle(@NotNull HttpExchange httpExchange) throws IOException {
        String res;
        int answ=200;
        try {
            InputStream is=this.loadRes(httpExchange.getRequestURI().toString());
            SortedMap<String, Charset> charsets = Charset.availableCharsets();
            if (charsets.containsKey("KOI8_R")) {
                //res = new String(is.readAllBytes(), charsets.get("KOI8_R"));
                FwHttpServer.getLogger().fine("KOI8_R encoding supported. ");
            } else {
                FwHttpServer.getLogger().warning("KOI8_R encoding not supported. ");
            }
            res = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e){
            res = "ERROR 404";
            answ=404;
        }
        httpExchange.sendResponseHeaders(answ, res.length());
        OutputStream resbody = httpExchange.getResponseBody();
        resbody.write(res.getBytes(StandardCharsets.UTF_8));
    }
}
