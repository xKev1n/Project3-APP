package feec.vutbr.cz;

import ch.qos.logback.core.net.SocketConnector;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import feec.vutbr.cz.exceptions.ExceptionHandler;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;


public class App extends Application {

        private FXMLLoader loader;
        private VBox mainStage;
        private HttpServer server;

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            //Dockerization
            server = null;
            try {
                server = HttpServer.create(new InetSocketAddress(8080), 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            server.createContext("/", new MyHandler());
            server.setExecutor(null); // creates a default executor
            server.start();

            try {
                loader = new FXMLLoader(getClass().getResource("App.fxml"));
                mainStage = loader.load();


                primaryStage.setTitle("BDS JavaFX Demo");
                Scene scene = new Scene(mainStage);
                //setUserAgentStylesheet(STYLESHEET_MODENA);
                //String myStyle = getClass().getResource("css/myStyle.css").toExternalForm();
                //scene.getStylesheets().add(myStyle);

                primaryStage.getIcons().add(new Image(App.class.getResourceAsStream("logos/vut.jpg")));
                primaryStage.setScene(scene);
                primaryStage.show();

                primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent windowEvent) {
                        server.stop(1000);
                    }
                });
            } catch (Exception ex) {
                ExceptionHandler.handleException(ex);
            }
        }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "<h1> Hello World!!!! I just Dockerized a Maven Project </h1>";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}
