package UI;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import librarySystems.*;
import systemController.AddMemberController;

public class AddMember_Form extends GridPane {
	public  GridPane createAddMemberForm(){
		GridPane grid=new GridPane();
		        grid.setVgap(5);
				grid.setHgap(5);
		Label lbl_1=new Label("First Name:");
		Label lbl_2=new Label("Last Name:");
		Label lbl_3=new Label("Member Id:");
		Label lbl_4=new Label("Phone Number:");
		Label lbl_5=new Label("Street:");
		Label lbl_6=new Label("city:");
		Label lbl_7=new Label("State:");
		Label lbl_8=new Label("Zip Code");
		
		TextField txt1=new TextField();
		TextField txt2=new TextField();
		TextField txt3=new TextField();
		TextField txt4=new TextField();
		TextField txt5=new TextField();
		TextField txt6=new TextField();
		TextField txt7=new TextField();
		TextField txt8=new TextField();
		
		Button addMember=new Button(" Add Member");
		addMember.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				AddMemberController ctr=new AddMemberController();
				Address address=new Address(txt5.getText(),txt6.getText(),txt7.getText(),txt8.getText());
				Member member=new Member(txt3.getText(),txt1.getText(),txt2.getText(),txt4.getText(),address);
				ctr.addMember(member);
				JOptionPane.showMessageDialog(null, "You add libraryMember successfully ","Adding sucessfull!",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		grid.add(lbl_1, 0, 1);
		grid.add(lbl_2, 0, 2);
		grid.add(lbl_3, 0, 3);
		grid.add(lbl_4, 0, 4);
		grid.add(lbl_5, 0, 5);
		grid.add(lbl_6, 0, 6);
		grid.add(lbl_7, 0, 7);
		grid.add(lbl_8, 0, 8);
		
		grid.add(txt1, 1, 1);
		grid.add(txt2, 1, 2);
		grid.add(txt3, 1, 3);
		grid.add(txt4, 1, 4);
		grid.add(txt5, 1, 5);
		grid.add(txt6, 1, 6);
		grid.add(txt7, 1, 7);
		grid.add(txt8, 1, 8);
		
		grid.add(addMember, 1, 9);
		
		return grid;
		
	}
	public void clear(){

	}
}
