package systemController;
import librarySystems.*;
import java.util.*;
import dataaccess.*;
import java.io.*;

public class CheckOutController {
	DataAccessFacade daf=new DataAccessFacade();
public Book searchBook(String isbn){
	
	return new DataAccessFacade().searchBook(isbn);
}
public  Member searchMember(String id){

	return new DataAccessFacade().searchMember(id);
}
public BookCopy searchAvailableCopy(String s){
	return new DataAccessFacade().searchBookCopy( s);
	
}
public void updateBookCopyRecord(BookCopy copy){
	new DataAccessFacade().saveBookCopy(copy);
}
public CheckedOutEntry getEntryByMemberID(String memberId){
	List<CheckedOutEntry>list;
	CheckedOutEntry entry=null;
	Map<String,CheckedOutEntry>entryRecord=daf.readCheckedOutEntry();
	entry=entryRecord.get(memberId);
	/*list=(ArrayList<CheckedOutEntry>) entryRecord.values();
	for(CheckedOutEntry entry: list){
		if(entry.getMemberId().equals(memberId))
			return entry;
	}*/
	return entry;
}
}
