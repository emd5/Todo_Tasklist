package view;/*
 *Liz Mahoney
 *11/22/17
 *TodoView.java
 */

/**
 * @author Liz Mahoney
 * @version 1.0
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class TodoView extends Application {
    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_HEIGHT = 400;


    private static  String[] titles =
            new String[]{"Welcome0", "TasksList", "Add New ", "Task2"};
    private static  String[] buttonLabels;


    private Stage stage;

    @Override
    public void start(Stage stage) {

        this.stage = stage;
        stage.setTitle("Task List");


            stage.setScene(getScene(new String[]{"View", "+",
                    "Finish"}, 0));



        stage.show();

    }

    //layout of main scene
    private Scene getScene(String[] buttonLabels, int page){

        VBox contentFrame = new VBox();
        Button[] buttons = new Button[buttonLabels.length];


        if(page +2 >= buttonLabels.length){
            buttons[page+2] = new Button(buttonLabels[page+2]);
            contentFrame.getChildren().addAll(buttons[page+2]);
            contentFrame.getStylesheets().addAll("css/todo.css");
            return new Scene(getAddTaskFrame(contentFrame,page+2));
        }
        else if(page+1 >= buttonLabels.length){
            buttons[page+1] = new Button(buttonLabels[page+1]);
            contentFrame.getChildren().addAll(buttons[page+1]);
            contentFrame.getStylesheets().addAll("css/todo.css");
            return new Scene(getTaskListFrame(contentFrame, page+1));
        }
        else {
            buttons[page] = new Button(buttonLabels[page]);
            contentFrame.getChildren().addAll(buttons[page]);
            contentFrame.getStylesheets().addAll("css/todo.css");
            return new Scene(getWelcomeFrame(contentFrame, page));


        }

    }



    private VBox getWelcomeFrame(VBox contentFame, int page){

        Text title = getTitle(page);
        ImageView image = getNoteBookImage();
        contentFame.getChildren().addAll(title, image);
        return contentFame;
    }

    private VBox getAddTaskFrame(VBox contentFame, int page){

        contentFame.getChildren().addAll();
        return contentFame;
    }

    private VBox getTaskListFrame(VBox contentFame, int page){

        contentFame.getChildren().addAll();
        return contentFame;
    }

    private ImageView getNoteBookImage(){
        return new ImageView("image/tasks.png");
    }


    private Text getTitle(int page){
        return new Text(titles[page]);
    }


}
