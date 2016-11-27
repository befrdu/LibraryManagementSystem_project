package UI;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
public class UpdateMemberInfo_Form {
	
public GridPane creatUpdateMemberInfoForm(){
	int id=1234;
	GridPane grid=new GridPane();
	GridPane grid2=new AddMember_Form().createAddMemberForm();
	Label lbl_1=new Label("Enter Member ID");
	Label lbl_2=new Label();
	TextField txt1=new TextField();
	Button search=new Button("Search");
	search.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent e){
			int str=Integer.parseInt(txt1.getText());
			if(str==id){
				lbl_2.setText("Insert member New Value:");
				grid.add(grid2, 2, 5);
			}
			else{
				lbl_2.setText("No member having such IdNo:");
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
