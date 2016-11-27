package dataaccess;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import librarySystems.*;

public class DataAccessFacade implements DataAccess {
	
	enum StorageType {
		BOOKS, MEMBERS, USERS,BOOKCOPY,CHEKEDOUT_ENTRY;
	}
	
	public static final String OUTPUT_DIR = System.getProperty("user.dir") 
			+ "\\data";
	public static final String DATE_PATTERN = "MM/dd/yyyy";
	
//--------------------------SEARCH METHODS-----------------------------------------------------
	
	public Member searchMember(String memberId) {
		HashMap<String,Member> memberMap =  readMembersMap();
		Member b = memberMap.get(memberId);
		return b;
	}
	
	public Book searchBook(String isbn) {
		HashMap<String,Book> booksMap =  readBooksMap();
		Book b = booksMap.get(isbn);
		return b;
	}
	public BookCopy searchBookCopy(String isbn) {
		HashMap<String,BookCopy> booksMap =  readBookCopyMap();
		BookCopy b = booksMap.get(isbn);
		return b;
	}

	public BookCopy searchBookCopy2(String isbn) {
		BookCopy b;
		HashMap<Integer,BookCopy> booksMap =  readBookCopyMap2();
		List<BookCopy>bookCopies=(List<BookCopy>) booksMap.values();
		for(BookCopy copy: bookCopies){
			String isNo=copy.getBook().getIsbn();
			if(isNo.equals(isbn)&&copy.isAvailable())
				return copy;
		}
			
		return null;
	}
//--------------------------READ METHODS----------------------------------------------------------
	@SuppressWarnings("unchecked")
	public  HashMap<String,Book> readBooksMap() {	
		return (HashMap<String,Book>) readFromStorage(StorageType.BOOKS);
	}
	@SuppressWarnings("unchecked")
	public  HashMap<String,Member> readMembersMap() {	
		return (HashMap<String,Member>) readFromStorage(StorageType.MEMBERS);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, User> readUserMap() {
		return (HashMap<String, User>)readFromStorage(StorageType.USERS);
	}
	@SuppressWarnings("unchecked")
	public HashMap<String, BookCopy> readBookCopyMap() {
		return (HashMap<String, BookCopy>)readFromStorage(StorageType.BOOKCOPY);
	}
	@SuppressWarnings("unchecked")
	public HashMap<Integer, BookCopy> readBookCopyMap2() {
		return (HashMap<Integer, BookCopy>)readFromStorage(StorageType.BOOKCOPY);
	}
	@SuppressWarnings("unchecked")
	public HashMap<String,CheckedOutEntry> readCheckedOutEntry() {
		
        HashMap<String,CheckedOutEntry> entryRecoredMap = (HashMap<String,CheckedOutEntry>)readFromStorage(StorageType.CHEKEDOUT_ENTRY);
		
		//List<CheckedOutEntry>entryRecordList=((List<CheckedOutEntry>) entryRecoredMap.values());
		
		return entryRecoredMap ;
	}
//--------------------------SAVE METHODS-----------------------------------------------------------
	public void saveNewMember(Member member) {
		HashMap<String,Member>memberMap=readMembersMap();
		String id=member.getMemberId();
		memberMap.put(id, member);
		saveToStorage(StorageType.MEMBERS,memberMap);
	}
		
	public void saveNewBook(Book book) {
		HashMap<String, Book> bookMap = readBooksMap();
		String isbn = book.getIsbn();
		bookMap.put(isbn, book);
		saveToStorage(StorageType.BOOKS, bookMap);	
	}
	public void saveBookCopy(BookCopy bookCopy) {
		HashMap<String, BookCopy> bookMap = readBookCopyMap();
		String isbn = bookCopy.getBook().getIsbn();
		bookMap.put(isbn, bookCopy);
		saveToStorage(StorageType.BOOKCOPY, bookMap);	
	}
	public void saveBookCopy2(BookCopy bookCopy) {
		HashMap<Integer, BookCopy> bookMap = readBookCopyMap2();
		bookCopy.getBook().addCopy();
		int copyNum = bookCopy.getCopyNum();
		bookMap.put(copyNum, bookCopy);
		saveToStorage(StorageType.BOOKCOPY, bookMap);	
	}
	public void saveCheckedOutRecord(CheckedOutEntry entry) {
		HashMap<String,CheckedOutEntry>entryRecordMap=readCheckedOutEntry();
		String memberId=entry.getMemberId();
		entryRecordMap.put(memberId, entry);
		saveToStorage(StorageType.CHEKEDOUT_ENTRY, entryRecordMap);	
	}
//----------------------UPDATE METHODS-----------------------------------------------------------
	
	public void updateMember(Member member){
		
	}
//--------------------LOG IN---------------------------------------------------------------------	
	public Auth login(String id, String pwd) {
		HashMap<String, User> userMap = readUserMap();
		if(!userMap.containsKey(id)) return null;
		User user = userMap.get(id);
		if(!pwd.equals(user.getPassword())) {
			return null;
		}
		return user.getAuthorization();
	}
//-----------------------------------------------------------------------------------------------

//=================================LOAD DATA MAP==================================================		
	static void loadBookMap(List<Book> bookList) {
		HashMap<String, Book> map = new HashMap<String, Book>();
		//extract each book in the bookList, and add (ISBN, each bookItem) as an entry in the HashMap
		bookList.forEach(book -> map.put(book.getIsbn(), book));
		saveToStorage(StorageType.BOOKS, map);
	}
	static void loadUserMap(List<User> userList) {
		HashMap<String, User> map = new HashMap<String, User>();
		userList.forEach(user -> map.put(user.getId(), user));
		saveToStorage(StorageType.USERS, map);
	}
	static void loadMemberMap(List<Member> userList) {
		HashMap<String, Member> map = new HashMap<String, Member>();
		userList.forEach(user -> map.put(user.getMemberId(), user));
		saveToStorage(StorageType.MEMBERS, map);
	}
	static void loadBookCopy(List<BookCopy> bookCopies) {
		HashMap<String, BookCopy> map = new HashMap<String, BookCopy>();
		bookCopies.forEach(user -> map.put(user.getBook().getIsbn(), user));
		saveToStorage(StorageType.BOOKCOPY, map);
	}
	static void loadBookCopy2(List<BookCopy> bookCopies) {
		HashMap<Integer, BookCopy> map = new HashMap<Integer, BookCopy>();
		bookCopies.forEach(user -> map.put(user.getCopyNum(), user));
		saveToStorage(StorageType.BOOKCOPY, map);
	}
	static void loadCheckedEntry(List<CheckedOutEntry> entry) {
		HashMap<String, CheckedOutEntry> map = new HashMap<String, CheckedOutEntry>();
		entry.forEach(user -> map.put(user.getMemberId(), user));
		saveToStorage(StorageType.CHEKEDOUT_ENTRY, map);
	}
	static void saveToStorage(StorageType type, Object ob) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(Exception e) {}
			}
		}
	}
	
	static Object readFromStorage(StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return retVal;
	}
//=================================//============================================================		
}
