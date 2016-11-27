package UI;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import javax.swing.JOptionPane;

import dataaccess.*;
import librarySystems.*;
import systemController.*;

public class SearchCheckoutRecord_Form {
	DataAccessFacade daf=new DataAccessFacade();
	CheckOutController ctrl=new CheckOutController();
public GridPane creatCheckOutRecordForm(){
	GridPane grid=new GridPane();
	Label lbl_1=new Label();
	TextField txt1=new TextField();
	Button btn=new Button("Search");
		   btn.setOnAction(new EventHandler<ActionEvent>(){
			   public void handle(ActionEvent e){
				   String memeberId=txt1.getText();
				  CheckedOutEntry record= ctrl.getEntryByMemberID(memeberId);
				  if(record!=null)
				  System.out.println(record.toString());
				  else
					  JOptionPane.showMessageDialog(null, "No recor registed for "+memeberId,"Record Not Found!",JOptionPane.INFORMATION_MESSAGE);
			   }
		   });
	grid.add(lbl_1, 0, 1);
	grid.add(txt1, 1, 1);
	grid.add(btn, 1, 2);
	
	return grid;
	
}
}
