package dbms;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class adminlogincontroller implements Initializable
{

    @FXML
    private Button cont;
    @FXML
    private StackPane root1;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public void load()
    {

        Parent setting = null;
        try{

            setting= (StackPane)FXMLLoader.load(getClass().getResource("adminlogin.fxml"));


        }
        catch(IOException e)
        {
        }
        Scene scene =new Scene(setting);
        runner.mainstage.setScene(scene);
        runner.mainstage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        root1.setOpacity(0);
        makeFadeInTransition();
    }
    private void makeFadeInTransition() {
        FadeTransition fd = new FadeTransition();
        fd.setDuration(Duration.millis(700));
        fd.setNode(root1);
        fd.setFromValue(0);
        fd.setToValue(1);
        fd.play();
    }

    private void makeFadeOut(int a) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(700));
        fade.setNode(root1);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setOnFinished((ActionEvent event)->{
            if(a==1)
            {
                adminpagecontroller ob = new adminpagecontroller();
                ob.load();
            }
            else if(a==2)
            {
                main ob = new main();
                ob.load();

            }
            else if(a==3)
            {
                adminlogincontroller ob = new adminlogincontroller();
                ob.load();
            }


        });
        fade.play();
    }

    public void continue_func(ActionEvent actionEvent)
    {
        if(username.getText().compareTo("Niket")==0 && password.getText().compareTo("1234")==0)
            makeFadeOut(1);
        else
            makeFadeOut(3);

    }
    public void back_func(ActionEvent actionEvent)
    {
        makeFadeOut(2);
    }


}
