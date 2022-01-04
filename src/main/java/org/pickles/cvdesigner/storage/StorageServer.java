package org.pickles.cvdesigner.storage;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class StorageServer {

    private static boolean alreadyStarted = false;

    public static void startServer() throws Exception {
        if (!alreadyStarted) {
            HttpServer server = HttpServer.create(new InetSocketAddress(5000), 0);
            server.createContext("/", new MyHandler());
            ExecutorService executors = Executors.newFixedThreadPool(20);
            server.setExecutor(executors); // creates a default executor
            server.start();
            alreadyStarted = true;
        }
    }

    private static String readFileAsString(String file) throws IOException {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String file = JsonStorageTemplate.getPathToStorage();
            String response = readFileAsString(file);
            t.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
