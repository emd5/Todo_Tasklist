/*
 *Liz Mahoney
 *11/22/17
 *TodoView.java
 */
package view;

import controller.TodoController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Todo;

import java.util.*;

/**
 * This class is the UI of the application
 *
 * @author Liz Mahoney
 * @version 1.0
 */
public class TodoView extends Application implements Observer {

    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_HEIGHT = 400;

    private static  String[] titles = new String[]{"Welcome","Tasks","Add New Task"};
    private static  String[] buttonLabels = new String[] {"View", "+", "Finish"};

    private static TodoController todoController = new TodoController();
    private static List<Todo> todoList;

    private Stage stage;

    @Override
    public void start(Stage stage) {

        todoList = todoController.getTaskList();

        this.stage = stage;
        stage.setTitle("Task List");

        stage.setScene(getWelcomeScene( 0));
        stage.show();
        todoController.addObserver(this);
    }

    private Scene getWelcomeScene(int page){

        VBox childFrame = new VBox();
        childFrame.setId("child-frame");

        Text title = getTitle(page);
        title.setId("title");
        Text taskText = new Text("You have " + todoList.size() + " unfinished" +
                " tasks");
        taskText.setId("contentMessage");
        Button button = createButton(page);
        button.setId("button");

        Image imageNotebook = getNoteBookImage();
        ImageView imageView = new ImageView(imageNotebook);

        childFrame.getChildren().addAll(title,taskText, button, imageView);
        childFrame.getStylesheets().addAll("css/todo.css");

        return new Scene(childFrame, WINDOW_WIDTH,WINDOW_HEIGHT);
    }

    private Image getNoteBookImage(){

        return new Image("image/tasks.png", 150,150, true, true);
    }

    private Scene getAddTaskScene( int page){

        VBox childFrame = new VBox();
        childFrame.setId("child-frame");

        Text title = getTitle(page);
        title.setId("title");

        TextArea textArea = new TextArea();
        textArea.setId("textarea");

        Button taskButton = createButton(page);
        taskButton.setId("button");

        taskButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = textArea.getText();
                if(!(text.isEmpty())){
                    todoController.addTask(text);
                    stage.setScene(getTaskListScene(page-1));
                }
            }
        });

        childFrame.getChildren().addAll(title, textArea, taskButton);
        childFrame.getStylesheets().addAll("css/todo.css");

        return new Scene(childFrame, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    private Scene getTaskListScene(int page){

        VBox childFrame = new VBox();
        childFrame.setId("child-frame");

        HBox hBox = new HBox();
        hBox.setId("taskListHbox");

        Text title = getTitle(page);
        title.setId("title");

        Button addTaskButton = createButton(page);
        addTaskButton.setId("addTaskButton");

        hBox.getChildren().addAll(title, addTaskButton);

        VBox verticalCheckbox;

        if(todoList.isEmpty()){
            verticalCheckbox = new VBox();
            verticalCheckbox.setId("vertical-checkbox");
            Text text = new Text("There are no tasks currently. Add a task by" +
                    " clicking the + button above");
            text.setId("contentMessage");

            verticalCheckbox.getChildren().addAll(text);
            childFrame.getChildren().addAll(hBox,verticalCheckbox);
        }
        else {
            VBox verticalCheckBox = getCheckBoxes(page);
            verticalCheckBox.setId("vertical-checkbox");

            childFrame.getChildren().addAll(hBox, verticalCheckBox);
        }

        childFrame.getStylesheets().addAll("css/todo.css");

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



    private VBox getCheckBoxes(int page){

        VBox verticalCheckBox = new VBox();

        Text text = null;

        CheckBox[] boxes = new CheckBox[todoList.size()];

        for(int i=0;i < todoList.size();i++){
            CheckBox checkBox = new CheckBox(todoList.get(i).getTask());
            boxes[i] = checkBox;

            checkBox.setId("padding10");

        }

        //event handling on checkboxes
        for(int i=0; i<boxes.length; i++){
            final CheckBox box = boxes[i];
            final Todo todo = todoList.get(i);
            box.selectedProperty()
                    .addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean>
                    observable, Boolean oldValue, Boolean newValue) {
                   todoController.removeTask(todo.getId());
                    stage.setScene(getTaskListScene(page));
                }
            });
            verticalCheckBox.getChildren().addAll(boxes);
        }

        return verticalCheckBox;
    }

    private Text getTitle(int page){

        return new Text(titles[page]);
    }

    /**
     * This method overrides any changes to the todolist.
     * @param todoModel
     * @param todoList the list that is being observed
     */
    @Override
    public void update(Observable todoModel, Object todoList) {
        this.todoList = (List<Todo>) todoList;
    }
}
