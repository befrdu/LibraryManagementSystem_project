package systemController;
import librarySystems.*;
import java.util.*;

import dataaccess.*;

public class LogInController {
List<SystemUser>users;
public LogInController(){
	users = new ArrayList();
	users.add(new SystemUser("Both", "both1", Role.Both));
	users.add(new SystemUser("Admin", "admin1", Role.Administrator));
	users.add(new SystemUser("Librerian", "librerian1", Role.Librarian));
}
public Auth validateUser(String username, String password){
	Auth authorization=new DataAccessFacade().login(password, username);
	/*for (SystemUser user : users) {
		if(user.getUsername().equals(username) && user.getPassword().equals(password)){
			return user;
		}
	}*/
	return authorization;
}
}
