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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class admindeletecontroller implements Initializable
{

    @FXML
    private Button cont;
    @FXML
    private StackPane root1;
    @FXML
    private TextField del_id;

    public void load()
    {

        Parent setting = null;
        try{

            setting= (StackPane)FXMLLoader.load(getClass().getResource("admindelete.fxml"));


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

    private void makeFadeOut() {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(700));
        fade.setNode(root1);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setOnFinished((ActionEvent event)->
        {
            adminpagecontroller ob = new adminpagecontroller();
            ob.load();
        });
        fade.play();
    }

    public void continue_func(ActionEvent actionEvent) throws SQLException {
        String x2 = del_id.getText();
        String sql3 = "delete from students where ID=?";
        runner.pre_st = runner.myconn.prepareStatement(sql3);
        runner.pre_st.setString(1, x2);
        int rows_affected = runner.pre_st.executeUpdate();
        makeFadeOut();

    }
    public void back_func(ActionEvent actionEvent)
    {

        makeFadeOut();
    }


}
