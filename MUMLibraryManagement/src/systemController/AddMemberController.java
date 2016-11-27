package systemController;
import dataaccess.*;
import librarySystems.*;
public class AddMemberController {
DataAccessFacade daf=new DataAccessFacade();
public void addMember(Member member){
	daf.saveNewMember(member);
}
}
