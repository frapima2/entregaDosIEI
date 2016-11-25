package sample;

        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;
        import java.util.ArrayList;
        import java.util.List;
        import javafx.scene.layout.Pane;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Pane menu = (Pane)FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Buscar Telefonos");
        primaryStage.setScene(new Scene(menu, 500, 240));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
