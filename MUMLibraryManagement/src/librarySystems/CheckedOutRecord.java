package librarySystems;
import dataaccess.*;

import java.io.Serializable;
import java.util.*;
import java.util.zip.CheckedOutputStream;
public class CheckedOutRecord implements Serializable {
	private static final long serialVersionUID = 6110690276685962829L;
DataAccessFacade daf=new DataAccessFacade();
private static List<CheckedOutEntry> entryList=new ArrayList();

public void addCheckedOutEntry(CheckedOutEntry e){

	daf.saveCheckedOutRecord(e);
	
}

public List<CheckedOutEntry> retrieveEntry(){
   
	//entryList=daf.readCheckedOutEntry();
	return entryList;
}
public void addEntryRecord(CheckedOutEntry e){
	entryList=retrieveEntry();
	entryList.add(e);

}
public CheckedOutEntry getEntryByMemberID(String memberId){
	List<CheckedOutEntry>list=new ArrayList<CheckedOutEntry>();
	Map<String,CheckedOutEntry>entryRecord=daf.readCheckedOutEntry();
	list=(List<CheckedOutEntry>) entryRecord.values();
	for(CheckedOutEntry entry: list){
		if(entry.getMemberId().equals(memberId))
			return entry;
	}
	return null;
}
public static List<CheckedOutEntry> getEntryList(){
	return entryList;
}
}
