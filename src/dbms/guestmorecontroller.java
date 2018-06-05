package dbms;

import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class guestmorecontroller implements Initializable {

    @FXML
    private StackPane root1;

    @FXML
    private Button cont;

    @FXML
    private Button back;

    @FXML
    private Button cont1;

    @FXML
    private TextField cgpa;

    @FXML
    private TextField depa;

    @FXML
    private TextField fiel;
    @FXML
    private TextField fiel1;

    @FXML
    private Button cont11;

    @FXML
    private Button cont2;
    private ObservableList<ObservableList> data;
    private TableView tableview;


    public void load()
    {

        Parent setting = null;
        try{

            setting= (StackPane)FXMLLoader.load(getClass().getResource("guestmore.fxml"));


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
                guestpagecontroller ob = new guestpagecontroller();
                ob.load();
            }
            else if(a==2)
            {
                adminpagecontroller ob = new adminpagecontroller();
                ob.load();
            }
            else if(a==3)
            {
                main ob = new main();
                ob.load();

            }


        });
        fade.play();
    }

    public void simple2(ActionEvent actionEvent) throws SQLException {
        String field = fiel.getText();
        String field_value = fiel1.getText();
        String dep = "Department";
        String dep1 = depa.getText();
        String order_by_field = "CGPA";
        //PreparedStatement pre_st = myconn.prepareStatement("select * from students where CGPA > 1 and Department = 'ECE' order by CGPA");
        runner.pre_st = runner.myconn.prepareStatement("select * from students where "+field+" > ? and "+dep+" = ?"+"order by "+order_by_field);
        runner.pre_st.setString(1, field_value);
        runner.pre_st.setString(2, dep1);
        ResultSet rs = runner.pre_st.executeQuery();
        buildData(rs);
    }

    public  void show_all (ActionEvent actionEvent) throws SQLException
    {
        PreparedStatement mystmt1 = runner.myconn.prepareStatement("select * from students");
        ResultSet rs = mystmt1.executeQuery();
        buildData(rs);
    }
    public void simple1(ActionEvent actionEvent) throws SQLException
    {
        double a = Double.parseDouble(cgpa.getText());
        String b = depa.getText();
        PreparedStatement mystmt1 = runner.myconn.prepareStatement("select * from students where CGPA > ? and Department = ?");

        mystmt1.setDouble(1, a);
        mystmt1.setString(2, b);
        ResultSet rs = mystmt1.executeQuery();
        buildData(rs);
    }
    public void check_average(ActionEvent actionEvent) throws SQLException {
        runner.mystmt = runner.myconn.createStatement();
        ResultSet rs = runner.mystmt.executeQuery("select avg(CGPA) from students");
        buildData(rs);


    }


    public void back_func(ActionEvent actionEvent)
    {
        makeFadeOut(3);
    }


    public void buildData(ResultSet rs){
        tableview = new TableView();
        data = FXCollections.observableArrayList();

        try{
            //c = myconn;
            //ResultSet
            //ResultSet rs = c.createStatement().executeQuery(SQL);

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
                System.out.println("Column ["+i+"] ");
            }
            Scene sc = new Scene(tableview);
            Stage temp = new Stage();
            temp.setScene(sc);
            temp.show();

            /********************************
             * Data added to ObservableList *
             ********************************/
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }


    public void get_average(MouseEvent mouseEvent) {
    }
}
