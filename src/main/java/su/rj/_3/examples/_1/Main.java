//TODO:Fix this!
package su.rj._3.examples._1;

import jdk.jfr.Unsigned;
import su.rj._3.FwHttpServer;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * An example how to use this library.
 * It starts a server at localhost:8080.
 */
public class Main {
    static FwHttpServer srv;
    @Unsigned
    static int port = 8080;
    /**
     *
     * @param args You know what it does.<br>
     * Args for this program:<br>
     * <span style="color:red">✡</span>port=[integer]<br>
     *             Port to run on, default 8080.
     * There will be more args here.
     */
    public static void main(String[] args) {
        Logger log = Logger.getLogger("su.rj._3.examples._1.*");
        log.setLevel(Level.ALL);
        for (String arg:args){
            if (arg.startsWith("port=")){
                try{
                    int newport = Integer.parseInt(arg.substring(5));
                    log.fine("Changed port from "+port+" to "+newport+". ");
                } catch (NumberFormatException e) {
                    log.warning("Invalid argument 'port'= format: "+arg);
                }
            }
        }
        try {
            srv = new MyServerFw(new InetSocketAddress(port),10);
            srv.bindHandler(new MyHandler(new String[]{"/test"}));
            srv.bindHandler(new QuitHandler(new String[]{"/quit"}));
            srv.bindHandler(new StaticResourceHandler(new String[]{"/"}));
            log.fine(srv.getAddress().toString());
            srv.go();
            Scanner sc = new Scanner(System.in);
            System.out.println("To exit type q. ");
            while(!sc.next().startsWith("q")){
                System.out.println("To exit type q, then enter. ");
            }
            System.out.println("Stopping. ");
            srv.stop(1);
        } catch (RuntimeException e) {
            System.err.println("Something wrong with server. ");
            throw new RuntimeException(e);
        }
    }
}