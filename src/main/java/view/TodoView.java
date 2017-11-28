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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TodoView extends Application {
    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_HEIGHT = 400;

    private static  String[] titles = new String[]{"Welcome","Tasks","Add New Task"};
    private static  String[] buttonLabels = new String[] {"View", "+", "Finish"};

    private Stage stage;


    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("Task List");
        stage.setScene(getWelcomeScene(0));
        stage.show();
    }

    private Scene getWelcomeScene(int page){

        VBox childFrame = new VBox();
        childFrame.setAlignment(Pos.CENTER);
        Text title = getTitle(page);
        Text taskText = new Text("You have " + " unfinished tasks");
        Button button = createButton(page);

        ImageView imageView = getNoteBookImage();

        childFrame.getChildren().addAll(title,taskText, button, imageView);

        return new Scene(childFrame, WINDOW_WIDTH,WINDOW_HEIGHT);
    }

    private Scene getAddTaskScene( int page){

        VBox childFrame = new VBox();

        Text title = getTitle(page);
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setPrefSize(20,50);

        Button taskButton = createButton(page);
        taskButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = textArea.getText();
                if(!(text.isEmpty())){
                    //submit new task
                }
                else{
                    //pop up message
                    textArea.setText("");
                }
            }
        });

        childFrame.getChildren().addAll(title, textArea, taskButton);

        return new Scene(childFrame, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    private Scene getTaskListScene( int page){
        VBox childFrame = new VBox();
        childFrame.setAlignment(Pos.CENTER);
        HBox hBox = new HBox();
        Text title = getTitle(page);
        Button addTaskButton = createButton(page);

        hBox.getChildren().addAll(title, addTaskButton);

        VBox verticalCheckBox = getCheckBoxes();

        childFrame.getChildren().addAll(hBox, verticalCheckBox);

        return new Scene(childFrame, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    private Button createButton(int page) {

        Button button = new Button(buttonLabels[page]);

        button.setOnAction(new EventHandler<ActionEvent>() {
      
            @Override
            public void handle(ActionEvent event) {
                switch(page) {
                    case 1:
                        System.out.println("1");
                        stage.setScene(getAddTaskScene(page+1));
                        break;
                    case 0:
                        System.out.println("0");
                        stage.setScene(getTaskListScene(page+1));
                        break;
                    default:
                        System.out.println("def");
                        stage.setScene(getWelcomeScene(page));
                }
            }
        });

        return button;
    }

    private ImageView getNoteBookImage(){

        return new ImageView("image/tasks.png");
    }

    private VBox getCheckBoxes(){

        String [] todoTaskList ={"get groceries", "eat food", "play with " +
                "stuff"};
        CheckBox[] boxes = new CheckBox[todoTaskList.length];

        VBox verticalCheckBox = new VBox();

        for(int i=0;i < todoTaskList.length;i++){
            CheckBox checkBox = new CheckBox(todoTaskList[i]);
            boxes[i] = checkBox;

        }
        //event handling on checkboxes
        for(int i=0; i<boxes.length; i++){
            final CheckBox box = boxes[i];
            box.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(newValue == true){
                        //delete todotask
                        //display delete
                    }
                }
            });

        }
        verticalCheckBox.getChildren().addAll(boxes);

        return verticalCheckBox;
    }

    private Text getTitle(int page){

        return new Text(titles[page]);
    }





}
