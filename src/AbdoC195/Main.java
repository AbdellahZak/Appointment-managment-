package AbdoC195;

import AbdoC195.DB.DbHelper;
import AbdoC195.DB.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Views/loginView.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {
        JDBC.makeConnection();
        int rowsAffected= DbHelper.insert("skay", "shad");
        if(rowsAffected>0){
            System.out.println("insert failed");
        }
        else{
            System.out.println("insert failed");
        }
        launch(args);
        JDBC.closeConnection();
    }
}
