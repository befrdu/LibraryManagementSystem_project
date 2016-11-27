package librarySystems;


public class Member extends Person {
	private String memberId;
	public Member(String mid, String f, String l, String t, Address a){
		super(f,l,t,a);
		memberId=mid;
	}
	public String getMemberId(){
		return memberId;
	}
}
