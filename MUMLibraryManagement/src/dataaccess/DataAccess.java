package dataaccess;

import java.util.HashMap;

import librarySystems.*;





public interface DataAccess {
	//searchMethods
	public Member searchMember(String memberId);
	public Book searchBook(String isbn);
	
	
	///////save methods
	public void saveNewMember(Member member);
	public void saveNewBook(Book book);
	
	
	//public void updateMember(Member member);
	//////read methods 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, Member> readMembersMap();
}
