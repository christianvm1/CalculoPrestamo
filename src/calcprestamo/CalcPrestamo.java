/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcprestamo;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author chris
 */
public class CalcPrestamo extends Application {

    Label air = new Label("Annual Interest Rate:"); //i
    TextField air_text = new TextField();

    Label years = new Label("Number of years:"); //n
    TextField years_text = new TextField();

    Label loan = new Label("Loan Amount:"); //h
    TextField loan_text = new TextField();

    Label monthly = new Label("Monthly Payment:"); //m
    TextField monthly_text = new TextField();

    Label total = new Label("Total Payment:");
    TextField total_text = new TextField();
    
    @Override
    public void start(Stage primaryStage) {
        Button calcular = new Button();
        calcular.setText("Calcular");
        calcular.setOnAction(e -> {
                monthly_text.setText("$"+calculaM());
                total_text.setText("$"+calculaTotal());
        });
       
        VBox labels = new VBox(air,years,loan,monthly,total);
        labels.setSpacing(14.0);
        labels.setPadding(new Insets(10.0));
        
        VBox textos = new VBox(air_text,years_text,loan_text,monthly_text,total_text,calcular);
        textos.setSpacing(5.0);
        textos.setPadding(new Insets(5.0));
        textos.setAlignment(Pos.CENTER_RIGHT);
        
        HBox root = new HBox(labels,textos);
        root.setSpacing(5.0);
        root.setPadding(new Insets(5.0));
        
        Scene scene = new Scene(root, 320, 200);
        
        primaryStage.setTitle("Calcular Prestamo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public String calculaM()
    {
        double h = parseDouble(loan_text.getText());
        double i = parseDouble(air_text.getText());
        double n = parseDouble(years_text.getText());
        double r;
        double m;
        double elevado = -12 * n;
        double x;
        String resultado;
        
        r = i/(100*12);
        x = Math.pow((1 + r), elevado);
        m = (h * r)/(1 - x);
        
        m = (double)Math.round(m * 100d) / 100d;
        resultado = ""+m;
        
        return resultado; 
    }
    
    public String calculaTotal()
    {
        double m = parseDouble(calculaM());
        double n = parseDouble(years_text.getText());
        double resultado = m * n * 12;
        resultado = (double)Math.round(resultado * 100d) / 100d;
        String total = "" + resultado; 
        
        return total;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
