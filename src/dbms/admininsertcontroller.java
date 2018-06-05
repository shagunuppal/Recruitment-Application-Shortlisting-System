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

public class admininsertcontroller implements Initializable
{

    @FXML
    private StackPane root1;

    @FXML
    private Button cont;

    @FXML
    private TextField name;

    @FXML
    private TextField department;

    @FXML
    private TextField cgpa;

    @FXML
    private TextField workex;

    @FXML
    private TextField researchpaper;

    @FXML
    private TextField lor;

    @FXML
    private TextField english;

    @FXML
    private TextField no_of_lang;

    @FXML
    private TextField project_id;

    @FXML
    private Button back;
    public void load()
    {

        Parent setting = null;
        try{

            setting= (StackPane)FXMLLoader.load(getClass().getResource("admininsert.fxml"));


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
        fade.setOnFinished((ActionEvent event)->{
                adminpagecontroller ob = new adminpagecontroller();
                ob.load();

        });
        fade.play();
    }

    public void continue_func(ActionEvent actionEvent) throws SQLException {
        int x1 = 10;
        String x2 = name.getText();
        String x3 = department.getText();
        double x4 = Double.parseDouble(cgpa.getText());
        int x5 = Integer.parseInt(workex.getText());
        int x6 = Integer.parseInt(researchpaper.getText());
        int x7 = Integer.parseInt(lor.getText());
        int x8 = Integer.parseInt(no_of_lang.getText());
        int x9 = Integer.parseInt(english.getText());
        int x10 = Integer.parseInt(project_id.getText());
        String sql1 = "insert into students"
                + " (ID, Name, Department, CGPA, Work_Experience, Research_Papers, LORs, Number_of_Languages, English_Spoken, Project_ID)"
                + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        runner.pre_st = runner.myconn.prepareStatement(sql1);
        runner.pre_st.setInt(1, x1);
        runner.pre_st.setString(2, x2);
        runner.pre_st.setString(3, x3);
        runner.pre_st.setDouble(4, x4);
        runner.pre_st.setInt(5, x5);
        runner.pre_st.setInt(6, x6);
        runner.pre_st.setInt(7, x7);
        runner.pre_st.setInt(8, x8);
        runner.pre_st.setInt(9, x9);
        runner.pre_st.setInt(10, x10);

        runner.pre_st.executeUpdate();

        makeFadeOut();
    }
    public void back_func(ActionEvent actionEvent)
    {
        makeFadeOut();
    }


}
