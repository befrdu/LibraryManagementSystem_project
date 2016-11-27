package librarySystems;

import java.io.Serializable;
import java.time.LocalDate;
public class CheckedOutEntry implements Serializable {
private static final long serialVersionUID = 6110690276685962829L;
private String memberId;
private BookCopy book;
private LocalDate checkedOutDate;
private LocalDate dueDate;
public CheckedOutEntry(String id,BookCopy b, LocalDate out, LocalDate due){
	memberId=id;
	book=b;
	checkedOutDate=out;
	dueDate=due;
	
}
public String getMemberId(){
	return memberId;
}
public BookCopy getBook(){
	return book;
}

public String toString(){
	System.out.println("Member_Id           ISBN           Book Title          Cheked_Out_Date               Due Date");
	System.out.println("-------------|----------------|-------------------|-------------------------|------------------");
	return memberId+"             "+book.getBook().getIsbn()+"          "+book.getBook().getTitle()+"      "
			+ "          "+checkedOutDate+"          "+dueDate;
}
}
