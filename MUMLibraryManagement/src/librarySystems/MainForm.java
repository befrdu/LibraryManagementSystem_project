package librarySystems;
import dataservice.*;
import systemController.*;

import javax.swing.JOptionPane;

import UI.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainForm extends Application{
	

private Button log_in=new Button("Log In");
private GridPane login=createLogInForm();
private GridPane mainPane=new GridPane();
private GridPane chkGrid=new Checkout_Form().createCheckoutForm();
private GridPane addMemberFrom=new AddMember_Form().createAddMemberForm();
private GridPane addBookPane=new AddBook_Form().creatAddBookForm();
private GridPane addBookCopyPane=new  AddBookCopy_Form().createAddBookCopyForm();
private GridPane updateMemberInfoPane=new UpdateMemberInfo_Form().creatUpdateMemberInfoForm();
private GridPane updateBookInfoPane=new UpdateBookInfo_Form().creatUpdateMemberInfoForm();
private GridPane createUserPane=new CreateUser().creatUserForm();
private GridPane searchRecord =new SearchCheckoutRecord_Form().creatCheckOutRecordForm();
private Menu chkOut=new Menu("Checkout Book");
private Auth authorization=null;
private Menu addBook=new Menu("Add Book");
private Menu addMember=new Menu("Add Member");
private Menu addBookCopy=new Menu("Add Book Copy");
private Menu update=new Menu("Search");
private Menu creatUser=new Menu("Create User");
private MenuBar menuBar=createMainMenuBar();
public static void main(String []args){
	launch(args);
}
public void start(Stage primaryStage){
	
	primaryStage.setTitle("Library Information System");
	//MenuBar to contain menus
	 menuBar.setVisible(false);
	 login.setVisible(false);
	
	//root Node
	StackPane root =new StackPane();
	
	//main GridePane
	        mainPane=new GridPane();
		    mainPane.setAlignment(Pos.TOP_LEFT);
			mainPane.setVgap(10);
			mainPane.setHgap(10);
			mainPane.setPadding(new Insets(25,25,25,25));
			
//LogIn Button
	HBox hb1=new HBox();
	hb1.setAlignment(Pos.TOP_LEFT);
	       log_in.setOnAction(new EventHandler<ActionEvent>(){
	    	   
	    	   public void handle(ActionEvent e){
	    		  //
	    		   String str=log_in.getText();
	    		   if(str.equals("Log In")){
	    			   login.setVisible(true);
	    		   }
	    		   else{
	    			   updateBookInfoPane.setVisible(false);
	    			    updateMemberInfoPane.setVisible(false);
	    			    chkGrid.setVisible(false);
						login.setVisible(false);
						addMemberFrom.setVisible(false);
						addBookPane.setVisible(false);
						addBookCopyPane.setVisible(false);
						menuBar.setVisible(false);
						searchRecord.setVisible(false);
						log_in.setText("Log In");
						addMemberFrom.setVisible(false);
						createUserPane.setVisible(false);
	    		   }
	    		   
	    	   }
	       });
	hb1.getChildren().add(log_in);
	mainPane.add(hb1, 0, 0);;
	mainPane.add(menuBar,1, 0);
	
	mainPane.add(login, 1, 1);
	//display(login);
	root.getChildren().add(mainPane);
	primaryStage.setScene(new Scene(root,650,400));
	primaryStage.show();
	
}
public GridPane createLogInForm()
{
	GridPane login=new GridPane();
	login.setAlignment(Pos.TOP_LEFT);
	login.setVgap(5);
	login.setHgap(5);
	
	Label info=new Label("Please Enter your User Name and Password!");
	Label userName=new Label("User_Name");
	Label userType=new Label("User_Type");
	TextField txtUser=new TextField();
	PasswordField txtPassword =new PasswordField();
	//TextField txtPassword=new TextField();
	Button login2=new Button("Sign In");
	
		   login2.setOnAction(new EventHandler<ActionEvent>(){
			   public void handle(ActionEvent e){
				   createMenus();
				   String userN=txtUser.getText().trim();
				   String pass=txtPassword.getText().trim();
				   
				 authorization=new LogInController().validateUser(userN, pass);
				 
				   if(authorization!=null){
					   menuBar.setVisible(true);
					   login.setVisible(false);
					   log_in.setText("Log out");
					   txtUser.setText("");
					   txtPassword.setText("");
					   if(authorization==Auth.LIBRARIAN){
						  chkOut.setDisable(false);
						  addBook.setDisable(true);
						  addMember.setDisable(true);
						  update.setDisable(true);
						  creatUser.setDisable(true);
						  addBookCopy.setDisable(true);
					   }
					   if(authorization==Auth.ADMIN){
						  addBook.setDisable(false);
						  addMember.setDisable(false);
						  update.setDisable(false);
						  creatUser.setDisable(false);
						  addBookCopy.setDisable(false);
						  chkOut.setDisable(true);
					   }
					   if(authorization==Auth.BOTH){
							  addBook.setDisable(false);
							  addMember.setDisable(false);
							  update.setDisable(false);
							  creatUser.setDisable(false);
							  addBookCopy.setDisable(false);
							  chkOut.setDisable(false);
					   }
				   }
				   else{   
					   JOptionPane.showMessageDialog(null, "Error, try again!", "Incorrect Password or UserName!", JOptionPane.INFORMATION_MESSAGE);
					   txtUser.setText("");
					   txtPassword.setText("");
					   txtUser.setFocusTraversable(true);
				   }
			   }
		   });
	login.add(info, 0, 0,3,1);
	login.add(userName, 0, 1);
	login.add(userType, 0, 2);
	login.add(txtUser, 1, 1);
	login.add(txtPassword, 1, 2);
	login.add(login2, 1, 3);
	
	return login;
}
public void createMenus(){
	/*//chkOut=new Menu("Checkout Book");
	//chkOut.setDisable(true);
	addBook=new Menu("Add Book");
	addMember=new Menu("Add Member");
	addBookCopy=new Menu("Add Book Copy");
	update=new Menu("Update");
	creatUser=new Menu("Create User");
	//item1=new MenuItem("Check Out Book");
*/}
public MenuBar createMainMenuBar(){
	MenuBar menuBar=new MenuBar();
	MenuItem item2=new MenuItem("Add Book");
			addBook.getItems().add(item2);
	MenuItem item1=new MenuItem("Check Out Book");
			chkOut.getItems().add(item1);
	MenuItem item3=new MenuItem("Add Member");
			addMember.getItems().add(item3);
	MenuItem item4=new MenuItem("ChekedOutBook");
	MenuItem item5=new MenuItem("Member");
			update.getItems().addAll(item4,item5);
	MenuItem item6=new MenuItem("Create Librerian user");
	MenuItem item7=new MenuItem("Create Admin useer");
	MenuItem item8=new MenuItem("Create both Librerian and Admin user");
			creatUser.getItems().addAll(item6, item7, item8);
	MenuItem item9=new MenuItem("Add Book Copy");
			 addBookCopy.getItems().add(item9);
	item8.setOnAction(new EventHandler<ActionEvent>(){
					public void handle(ActionEvent e){
						createUser();

					}
				});	
		item7.setOnAction(new EventHandler<ActionEvent>(){
					public void handle(ActionEvent e){
						createUser();

					}
				});		 
		item6.setOnAction(new EventHandler<ActionEvent>(){
					public void handle(ActionEvent e){
						createUser();

					}
				});	
			 
		item5.setOnAction(new EventHandler<ActionEvent>(){
					public void handle(ActionEvent e){
						updateBookInfoPane=new UpdateBookInfo_Form().creatUpdateMemberInfoForm();
				
						chkGrid.setVisible(false);
						login.setVisible(false);
						addMemberFrom.setVisible(false);
						addBookPane.setVisible(false);
						addBookCopyPane.setVisible(false);
						updateMemberInfoPane.setVisible(false);						
						createUserPane.setVisible(false);
						searchRecord.setVisible(false);
						//updateBookInfoPane.setVisible(true);
						mainPane.add(updateBookInfoPane, 1, 1);

					}
				});			 
	item4.setOnAction(new EventHandler<ActionEvent>(){
					public void handle(ActionEvent e){
						updateMemberInfoPane=new UpdateMemberInfo_Form().creatUpdateMemberInfoForm();
				
						chkGrid.setVisible(false);
						login.setVisible(false);
						addMemberFrom.setVisible(false);
						addBookPane.setVisible(false);
						addBookCopyPane.setVisible(false);
						updateBookInfoPane.setVisible(false);
						createUserPane.setVisible(false);
						updateMemberInfoPane.setVisible(false);
						searchRecord.setVisible(true);
						mainPane.add(searchRecord, 1, 1);

					}
				});			 
	item9.setOnAction(new EventHandler<ActionEvent>(){
					public void handle(ActionEvent e){
						addBookCopyPane=new  AddBookCopy_Form().createAddBookCopyForm();
				
						chkGrid.setVisible(false);
						login.setVisible(false);
						addMemberFrom.setVisible(false);
						addBookPane.setVisible(false);
						updateMemberInfoPane.setVisible(false);
						updateBookInfoPane.setVisible(false);
						createUserPane.setVisible(false);
						searchRecord.setVisible(false);
						addBookCopyPane.setVisible(true);
						mainPane.add(addBookCopyPane, 1, 1);

					}
				});		 
	item1.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent e){
			chkGrid=new Checkout_Form().createCheckoutForm();
			login.setVisible(false);
			addMemberFrom.setVisible(false);
			addBookPane.setVisible(false);
			addBookCopyPane.setVisible(false);
			updateMemberInfoPane.setVisible(false);
			updateBookInfoPane.setVisible(false);
			createUserPane.setVisible(false);
			searchRecord.setVisible(false);
			chkGrid.setVisible(true);
			mainPane.add(chkGrid, 1, 1);
			
		}
	});
	item3.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent e){
			addMemberFrom=new AddMember_Form().createAddMemberForm();
			chkGrid.setVisible(false);
			login.setVisible(false);
			addBookCopyPane.setVisible(false);
			updateMemberInfoPane.setVisible(false);
			updateBookInfoPane.setVisible(false);
            addBookPane.setVisible(false);
            createUserPane.setVisible(false);
            searchRecord.setVisible(false);
            addMemberFrom.setVisible(true);
			mainPane.add(addMemberFrom, 1, 1);

		}
	});
	item2.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent e){
			addBookPane=new AddBook_Form().creatAddBookForm();
			chkGrid.setVisible(false);
			login.setVisible(false);
			addMemberFrom.setVisible(false);
			addBookCopyPane.setVisible(false);
			updateMemberInfoPane.setVisible(false);
			updateBookInfoPane.setVisible(false);
			createUserPane.setVisible(false);
			searchRecord.setVisible(false);
			addBookPane.setVisible(true);
			mainPane.add(addBookPane, 1, 1);
			

		}
	});
	menuBar.getMenus().addAll(chkOut,addBook,addMember,addBookCopy,update,creatUser);
	
	return menuBar;
}
public void createUser(){
	createUserPane=new CreateUser().creatUserForm();
	
	chkGrid.setVisible(false);
	login.setVisible(false);
	addMemberFrom.setVisible(false);
	addBookPane.setVisible(false);
	addBookCopyPane.setVisible(false);
	updateMemberInfoPane.setVisible(false);
	updateBookInfoPane.setVisible(false);
	searchRecord.setVisible(false);
	createUserPane.setVisible(true);
	mainPane.add(createUserPane, 1, 1);
}
}
