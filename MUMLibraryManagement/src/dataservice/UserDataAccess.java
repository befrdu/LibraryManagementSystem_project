package dataservice;

import java.util.List;

import java.util.Map.Entry;

import librarySystems.*;

public class UserDataAccess extends iDataAccess {
	public void saveUser(SystemUser user){
		List<SystemUser> allUser = getAllItems();
		allUser.add(user);
		save(allUser);
	}
	public SystemUser serachUser(SystemUser temp){
		List<SystemUser> allUser = getAllItems();
		for(SystemUser user:allUser){
			if (user.getUsername().equals(temp.getUsername())
					&& user.getPassword().equals(temp.getPassword()))
				return user;
		}
		return null;
		
	}

	
}
