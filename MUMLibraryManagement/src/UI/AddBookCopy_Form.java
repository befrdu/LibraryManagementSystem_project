package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import systemController.*;
import librarySystems.*;
import java.util.*;

import javax.swing.JOptionPane;

public class AddBookCopy_Form {
	Book book;
public GridPane createAddBookCopyForm(){
	GridPane addBook=new GridPane();
	
	AddBookCopyCtrl ctrl=new AddBookCopyCtrl();
	Label lbl_1=new Label("ISBN");
	Label lbl_2=new Label("Number of book copy:");
		  lbl_2.setVisible(false);
	Label lbl_3=new Label("Search result:");
		  lbl_3.setVisible(false);
	TextField txt1=new TextField();
	TextField txt2=new TextField();
			  txt2.setVisible(false);
	TextArea txt3=new TextArea();
			 txt3.setPrefRowCount(4);
			 txt3.setPrefColumnCount(30);
			 txt3.setEditable(false);;
			 txt3.setVisible(false);
			 
	
	Button search=new Button("Search Book");
	Button add=new Button("Add copies");
		   add.setVisible(false);
			search.setOnAction(new EventHandler<ActionEvent>(){
				
				public void handle(ActionEvent e){
					book=ctrl.searchBook(txt1.getText());
					if(book!=null){
						String title=book.getTitle();
						int copies=book.getNumCopies();
						List<Author>authors=book.getAuthors();
						String s="Book Title; "+title+"\nNumber of Copies: "+copies+"\nAuthors: "+authors;
						txt3.setText(s);
						add.setVisible(true);
						txt3.setVisible(true);
						txt2.setVisible(true);
						
					}
				}
			});
	
		add.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				int copyNum=Integer.parseInt(txt2.getText());
				boolean sumbitted=ctrl.addBookCopy(book, copyNum);
				if(sumbitted){
					JOptionPane.showMessageDialog(null, "Book Copy addess Sucessfully!", "Successfull",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	addBook.add(lbl_1, 1, 1);
	addBook.add(txt1, 1, 2);
				txt1.setMaxWidth(120);
	addBook.add(search, 1, 3);
	
	addBook.add(lbl_3, 1, 4);
	addBook.add(txt3, 1, 5);
	addBook.add(lbl_2, 1, 6);
	addBook.add(txt2, 1, 7);
				txt2.setMaxWidth(120);;
	addBook.add(add, 1, 8);
	return addBook;
}

}
