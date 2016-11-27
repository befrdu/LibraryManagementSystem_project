package UI;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import librarySystems.*;
import dataaccess.DataAccessFacade;
import dataservice.*;
import systemController.*;


import java.time.LocalDate;
import java.util.*;

import javax.swing.JOptionPane;
public class Checkout_Form {

private	CheckOutController chkController=new CheckOutController();
private	Member member=null;
private	Book book=null;
private TableView <Book> tbv=creatTableView();
	public GridPane createCheckoutForm(){
		tbv.setVisible(false);
		GridPane chkout=new GridPane();
				chkout.setVgap(5);
				chkout.setHgap(5);
	   Text txtInfo=new Text("Enter the following information");
	   Label lb1Member=new Label("Member ID:");
	   Label lblIsbn=new Label("Book ISBN");
	   TextField txt1=new TextField();
	   TextField txt2=new TextField();
	   Button chkOut=new Button("Check Out");
	   Button chkButton=new Button("Check Book");
	   		
	   		  chkOut.setVisible(false);
	   		  /*--------------------Search Book and memeber--------------------------------*/
	   		  
	          chkButton.setOnAction(new EventHandler<ActionEvent>(){
	        	  
	        	  public void handle(ActionEvent e){

	        		  try{
	        			    member=chkController.searchMember(txt1.getText());
	        		  }
	        		 catch(Exception exception){
	        			 JOptionPane.showMessageDialog(null, "Searchig Member can't proceed!", "Error!", JOptionPane.INFORMATION_MESSAGE);
	        		 }
	        		  if(member!=null){
	        			  try{
	        				book= chkController.searchBook(txt2.getText());
	        			  }
	        			  catch(Exception ex){
	        				  JOptionPane.showMessageDialog(null, "Searchig Book can't proceed!", "Error!", JOptionPane.INFORMATION_MESSAGE);
	        			  }
	        		  }
	        		  else{
	        			  JOptionPane.showMessageDialog(null, "No Member with member id: "+txt1.getText(), "Error!", JOptionPane.INFORMATION_MESSAGE);
	  	        		
	        		  }
	        		  if(book==null){
	        			  JOptionPane.showMessageDialog(null, "No Book having ISBN: "+txt2.getText(), "Error!", JOptionPane.INFORMATION_MESSAGE);
	  	        		
	        		  }
	        		  else{
	        			 ObservableList<Book>bookList=bookList(book);
	        			 setData(bookList);
	        			 tbv.setVisible(true);
	        			 chkOut.setVisible(true);
                         
	            	  }
	        		  }
	        		  
	          });
	          chkOut.setOnAction(new EventHandler<ActionEvent>(){
	        	  public void handle(ActionEvent e){
	        		boolean available=book.isAvailable();
	        	  BookCopy copy=chkController.searchAvailableCopy(book.getIsbn());
	        		if(available){
	        			int chekOutLength=book.getMaxCheckoutLength();
	        			CheckedOutEntry entry=new CheckedOutEntry(member.getMemberId(),copy,LocalDate.now(),LocalDate.now().plusDays(chekOutLength));
	        			//copy.changeAvailability();
	        			chkController.updateBookCopyRecord(copy);
	        			CheckedOutRecord entryRecord=new CheckedOutRecord();
	        			entryRecord.addCheckedOutEntry(entry);
	        			JOptionPane.showMessageDialog(null, "Book is checked Out", "Successfull!",JOptionPane.INFORMATION_MESSAGE);
	        		}
	        		else{
	        			JOptionPane.showMessageDialog(null, "The Book copy is not available", "Failed to checkout!",JOptionPane.INFORMATION_MESSAGE);
	        		
	        		}
	        	  }

	          });
	   chkout.add(txtInfo, 0, 0,3,1);
	   chkout.add(lb1Member, 0, 1);
	   chkout.add(lblIsbn, 0, 2);
	   chkout.add(txt1, 1, 1);
	   chkout.add(txt2, 1, 2);
	   chkout.add(chkButton, 1, 3);
	   chkout.add(tbv, 0, 4,4,1);
	   chkout.add(chkOut, 2, 5);
	   
	   return chkout;
	}
	public TableView<Book> creatTableView(){
		TableView<Book> table=new TableView<Book>();
					
	    TableColumn<Book,String> firstCol = new TableColumn<Book,String>("ISBN");
		firstCol.setCellValueFactory(
	            new PropertyValueFactory<Book, String>("isbn"));
			firstCol.setCellFactory(TextFieldTableCell.forTableColumn());
	    TableColumn<Book,String> secondCol = new TableColumn<Book,String>("Title");
	    secondCol.setCellValueFactory(
	            new PropertyValueFactory<Book, String>("title"));
	    
	    secondCol.setCellFactory(TextFieldTableCell.forTableColumn());
	    TableColumn<Book,Integer> thirdCol = new TableColumn<Book,Integer>("Max-CheckOut");
	    thirdCol.setCellValueFactory(
	            new PropertyValueFactory<Book, Integer>("maxCheckoutLength"));
	    thirdCol.setMinWidth(80);
	    table.setMaxHeight(100);
	
	   // TableColumn <Book,Integer>fourthCol = new TableColumn<Book,Integer>("Copies");
	    table.getColumns().addAll(firstCol, secondCol, thirdCol);
	    
	    
	    return table;
	}
	public void setData(ObservableList<Book> items) {
		ObservableList<Book> current = tbv.getItems();
		if(current != null) {
			current.addAll(items);
		}
		tbv.setItems(current);
	}
    
	public ObservableList bookList(Book b){
		int count=0;
		for(int i=0;i<b.getCopies().length;i++){
			count+=1;
		}
		
		ObservableList<Book> bookData =
				
	               FXCollections.observableArrayList(b);
		return bookData;
		
	}
}
