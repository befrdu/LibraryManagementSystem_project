package UI;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
public class CreateUser {
public GridPane creatUserForm(){
	GridPane grid=new GridPane();
	Label lbl_1=new Label("Create User Name:");
	Label lbl_2=new Label("Password:");
	Label lbl_3=new Label("Confirm Password:");
	
	TextField txt1=new TextField();
	PasswordField pas1=new PasswordField();
	PasswordField pas2=new PasswordField();
	Button btn=new Button("Create User");
	
	grid.add(lbl_1, 0, 1);
	grid.add(lbl_2, 0, 2);
	grid.add(lbl_3, 0, 3);
	grid.add(txt1, 1, 1);
	grid.add(pas1, 1, 2);
	grid.add(pas2, 1, 3);
	grid.add(btn, 1, 4);
	
	return grid;
}
}
