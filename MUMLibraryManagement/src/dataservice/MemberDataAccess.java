package dataservice;

import java.util.List;
import librarySystems.*;

public class MemberDataAccess extends iDataAccess {
	public void saveUser(Member member){
		List<Member> allMember = getAllItems();
		allMember.add(member);
		save(allMember);
	}
	public Member serachUser(String memberId){
		List<Member> allMember = getAllItems();
		for(Member member:allMember){
			if (member.getMemberId().equals(memberId));
				return member;
		}
		return null;
		
	}
	public void updateBook(Member member) {
		List<Member> allMember = getAllItems();
		for (int i=0;i<allMember.size();i++) {
			if (allMember.get(i).getMemberId().equals(member.getMemberId())) {
				allMember.add(i, member);
				//System.out.println(allBook.get(i).getCopies()[0].getCopyNum());
				break;
			}
		}
		//allMember.add(member);
		save(allMember);
	}
}
