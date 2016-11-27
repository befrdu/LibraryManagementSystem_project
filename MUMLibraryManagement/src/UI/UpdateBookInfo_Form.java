package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class UpdateBookInfo_Form {
	public GridPane creatUpdateMemberInfoForm(){
		int id=1234;
		GridPane grid=new GridPane();
		GridPane grid2=new AddBook_Form().creatAddBookForm();
		Label lbl_1=new Label("Enter ISBN");
		Label lbl_2=new Label();
		TextField txt1=new TextField();
		Button search=new Button("Search");
		search.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				int str=Integer.parseInt(txt1.getText());
				if(str==id){
					lbl_2.setText("Insert Book New Values:");
					grid.add(grid2, 2, 5);
				}
				else{
					lbl_2.setText("No Book having such ISBN:");
				}
			}
		});
		
		grid.add(lbl_1, 0, 1);
		grid.add(txt1, 1, 1);
		grid.add(lbl_2, 1, 3);
		grid.add(search, 2, 2);
		
		
		return grid;
	}
}
