package dbms;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;


class Student
{
    int id;
    SimpleStringProperty name;
    SimpleStringProperty dept;
    float cgpa;
    int workex;
    int res_paper;
    int lor;
    int no_of_lang;
    boolean eng_speaker;
    int project_id;
    public Student(int i,String a, String b, float cg,int wor,int res,int lor,int nol, boolean eng, int proj)
    {
        id =i;
        name = new SimpleStringProperty(a);
        dept = new SimpleStringProperty(b);
        cgpa = cg;
        workex = wor;
        res_paper = res;
        this.lor = lor;
        eng_speaker = eng;
        project_id = proj;
    }
}

public class runner extends Application
{
    static Connection myconn = null;
    static Statement mystmt = null;
    static PreparedStatement pre_st = null;

    static Stage mainstage;
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        mainstage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("mainpage.fxml"));
        mainstage.setTitle("Main Page");
        mainstage.setScene(new Scene(root));
        mainstage.show();
    }
    public static void main(String[] args) throws SQLException {
        myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","niks");
        mystmt = myconn.createStatement();
        launch(args);
    }
}
