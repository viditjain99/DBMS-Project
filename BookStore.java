import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Stack;

public class BookStore extends Application
{
    static Stack<Scene> sceneStack=new Stack<>();
    static Connection connection;
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/BookStore","root","password123");
        System.out.println(connection);
        Group root=new Group();
        Rectangle2D screenDimens= Screen.getPrimary().getBounds();
        Scene homePage=new Scene(root,screenDimens.getWidth(),screenDimens.getHeight());
        Text titleTextView=new Text("Book Store");
        titleTextView.setFont(Font.font("verdana",50));
        titleTextView.setLayoutX(screenDimens.getWidth()/2-50);
        titleTextView.setLayoutY(50);
        Button backButton=new Button("Back");
        backButton.setAlignment(Pos.TOP_LEFT);
        backButton.setPadding(new Insets(10,10,10,10));

        backButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                sceneStack.pop();
                primaryStage.setScene(sceneStack.peek());
            }
        });
        Button booksDatabaseButton=new Button("Books Database");
        booksDatabaseButton.setLayoutX(screenDimens.getWidth()/2);
        booksDatabaseButton.setLayoutY(screenDimens.getHeight()/2-100);
        booksDatabaseButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                AnchorPane root=new AnchorPane();
                Scene booksDatabaseScene=new Scene(root,screenDimens.getWidth(),screenDimens.getHeight());
                TabPane tabPane=new TabPane();
                tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
                for(int i=0;i<5;i++)
                {
                    Tab tab=new Tab("tab "+(i+1));
                    HBox hBox=new HBox();
                    hBox.getChildren().add(new Label("Tab "+(i+1)));
                    hBox.setAlignment(Pos.TOP_LEFT);
                    tab.setContent(hBox);
                    tabPane.getTabs().add(tab);
                }
                AnchorPane.setTopAnchor(tabPane,5.0);
                AnchorPane.setLeftAnchor(tabPane,5.0);
                AnchorPane.setRightAnchor(tabPane,5.0);
                AnchorPane.setTopAnchor(backButton,5.0);
                AnchorPane.setRightAnchor(backButton,5.0);
                root.getChildren().addAll(tabPane,backButton);
                primaryStage.setScene(booksDatabaseScene);
                sceneStack.push(booksDatabaseScene);
            }
        });
        Button customerDatabaseButton=new Button("Customer Database");
        customerDatabaseButton.setLayoutX(screenDimens.getWidth()/2);
        customerDatabaseButton.setLayoutY(screenDimens.getHeight()/2);
        customerDatabaseButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                AnchorPane root=new AnchorPane();
                Scene customerDatabaseScene=new Scene(root,screenDimens.getWidth(),screenDimens.getHeight());
                TabPane tabPane=new TabPane();
                tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
                for(int i=0;i<5;i++)
                {
                    Tab tab=new Tab("tab "+(i+1));
                    HBox hBox=new HBox();
                    hBox.getChildren().add(new Label("Tab "+(i+1)));
                    hBox.setAlignment(Pos.TOP_LEFT);
                    tab.setContent(hBox);
                    tabPane.getTabs().add(tab);
                }
                AnchorPane.setTopAnchor(tabPane,5.0);
                AnchorPane.setLeftAnchor(tabPane,5.0);
                AnchorPane.setRightAnchor(tabPane,5.0);
                AnchorPane.setTopAnchor(backButton,5.0);
                AnchorPane.setRightAnchor(backButton,5.0);
                root.getChildren().addAll(tabPane,backButton);
                primaryStage.setScene(customerDatabaseScene);
                sceneStack.push(customerDatabaseScene);
            }
        });
        Button employeesDatabaseButton=new Button("Employees Database");
        employeesDatabaseButton.setLayoutX(screenDimens.getWidth()/2);
        employeesDatabaseButton.setLayoutY(screenDimens.getHeight()/2+100);
        employeesDatabaseButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                AnchorPane root=new AnchorPane();
                Scene employeesDatabaseScene=new Scene(root,screenDimens.getWidth(),screenDimens.getHeight());
                TabPane tabPane=new TabPane();
                tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
                for(int i=0;i<5;i++)
                {
                    Tab tab=new Tab("tab "+(i+1));
                    HBox hBox=new HBox();
                    hBox.getChildren().add(new Label("Tab "+(i+1)));
                    hBox.setAlignment(Pos.TOP_LEFT);
                    tab.setContent(hBox);
                    tabPane.getTabs().add(tab);
                }
                AnchorPane.setTopAnchor(tabPane,5.0);
                AnchorPane.setLeftAnchor(tabPane,5.0);
                AnchorPane.setRightAnchor(tabPane,5.0);
                AnchorPane.setTopAnchor(backButton,5.0);
                AnchorPane.setRightAnchor(backButton,5.0);
                root.getChildren().addAll(tabPane,backButton);
                primaryStage.setScene(employeesDatabaseScene);
                sceneStack.push(employeesDatabaseScene);
            }
        });
        root.getChildren().addAll(customerDatabaseButton,booksDatabaseButton,employeesDatabaseButton,titleTextView);
        primaryStage.setScene(homePage);
        sceneStack.push(homePage);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
