package systemController;
import dataaccess.*;
import librarySystems.*;
public class AddBookCopyCtrl {
DataAccessFacade daf=new DataAccessFacade();
public Book searchBook(String isbn){

	return daf.searchBook(isbn);
}
public boolean addBookCopy(Book b, int copy){
	BookCopy bookCopy;
	int a=b.getNumCopies();
	for(int i=a;i<=copy+a;i++){
		bookCopy=new BookCopy(b,i*1000+i,true);
		daf.saveBookCopy2(bookCopy);
		
	}
	return true;
	
}
}
