package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import librarySystems.*;
import dataaccess.*;
import java.util.*;

import javax.swing.JOptionPane;
public class AddBook_Form {
	List<Author>authors=new ArrayList();
	DataAccessFacade daf=new DataAccessFacade();
public GridPane creatAddBookForm(){
	GridPane grid=new GridPane();
    grid.setVgap(5);
	grid.setHgap(5);
Label lbl_1=new Label("ISBN:");
Label lbl_2=new Label("Book Title");
Label lbl_3=new Label("Checkout Length:");
Label lbl_4=new Label("No. of Copies:");
Label lbl_5=new Label("First Name:");
Label lbl_6=new Label("Last Name:");
Label lbl_7=new Label("Phone:");
Label lbl_8=new Label("Biography:");
Label lbl_9=new Label("Street:");
Label lbl_10=new Label("city:");
Label lbl_11=new Label("State:");
Label lbl_12=new Label("Zip Code");

TextField txt1=new TextField();
TextField txt2=new TextField();
TextField txt3=new TextField();
TextField txt4=new TextField();
TextField txt5=new TextField();
TextField txt6=new TextField();
TextField txt7=new TextField();
TextArea txt8=new TextArea();
		txt8.setMaxWidth(150);
TextField txt9=new TextField();
TextField txt10=new TextField();
TextField txt11=new TextField();
TextField txt12=new TextField();
//TextField txt13=new TextField();
Button addBook=new Button(" Add Book");
	   addBook.setOnAction(new EventHandler<ActionEvent>(){
	   public void handle(ActionEvent e){
		   String isbn=txt1.getText();
		   String title=txt2.getText();
		   int maxCheckout=Integer.parseInt(txt3.getText());
		   String fn=txt5.getText();
		   String ln=txt6.getText();
		   String phone=txt7.getText();
		   String bio=txt8.getText();
		   String street=txt9.getText();
		   String city=txt10.getText();
		   String state=txt11.getText();
		   String zip=txt12.getText();

		   addAuthor(fn,ln,phone,street,city,state,zip,bio);
		   addBook(isbn,title,maxCheckout,authors);
		   txt1.setText("");
		   txt2.setText("");
		   txt3.setText("");
		   txt4.setText("");
		   txt5.setText("");
		   txt6.setText("");
		   txt7.setText("");
		   txt8.setText("");
		   txt9.setText("");
		   txt10.setText("");
		   txt11.setText("");
		   txt12.setText("");
		   
	   }
	   });
Button addAuthor=new Button(" Add Author");
Button addMoreAuthor=new Button("Another Author>>");
       addMoreAuthor.setOnAction(new EventHandler<ActionEvent>(){
    	   public void handle(ActionEvent e){
    		   String fn=txt5.getText();
    		   String ln=txt6.getText();
    		   String phone=txt7.getText();
    		   String bio=txt8.getText();
    		   String street=txt9.getText();
    		   String city=txt10.getText();
    		   String state=txt11.getText();
    		   String zip=txt12.getText();
    		   addAuthor(fn,ln,phone,street,city,state,zip,bio);
    		   txt5.setText("");
    		   txt6.setText("");
    		   txt7.setText("");
    		   txt8.setText("");
    		   txt9.setText("");
    		   txt10.setText("");
    		   txt11.setText("");
    		   txt12.setText("");
    	   }
       });
Text txt=new Text("Book Information:");
grid.add(txt,0,0);
grid.add(lbl_1, 0, 1);
grid.add(lbl_2, 0, 2);
grid.add(lbl_3, 0, 3);
grid.add(lbl_4, 0, 4);
grid.add(txt1, 1, 1);
grid.add(txt2, 1, 2);
grid.add(txt3, 1, 3);
grid.add(txt4, 1, 4);

Text txtAuD=new Text("Authors Detail");
grid.add(txtAuD, 2, 1);
grid.add(lbl_5, 2, 2);
grid.add(lbl_6, 2, 3);
grid.add(lbl_7, 2, 4);
grid.add(lbl_8, 2, 5);
grid.add(lbl_9, 2, 6);
grid.add(lbl_10, 2, 7);
grid.add(lbl_11, 2, 8);
grid.add(lbl_12,2, 9);
grid.add(txt5, 3, 2);
grid.add(txt6, 3, 3);
grid.add(txt7, 3, 4);
grid.add(txt8, 3, 5);
grid.add(txt9, 3, 6);
grid.add(txt10, 3, 7);
grid.add(txt11, 3, 8);
grid.add(txt12, 3, 9);


grid.add(addBook, 1, 10);
//grid.add(addAuthor, 2, 10);
grid.add(addMoreAuthor, 3, 10);

return grid;

}
public void addAuthor(String fn, String ln, String telephone, String street, String city,String state, String zip, String bio){
	Address add=new Address(street, city,state,zip);
	Author author=new Author(fn,ln,telephone,add,bio);
	authors.add(author);
	
}
public void addBook(String isbn, String title, int maxchek, List<Author>authors){
	Book book =new Book(isbn,title,maxchek,authors);
	daf.saveNewBook(book);
	JOptionPane.showMessageDialog(null, "The Book is added successfully!","Adding Successfull",JOptionPane.INFORMATION_MESSAGE);
}
public void clear(){
	
}
}
