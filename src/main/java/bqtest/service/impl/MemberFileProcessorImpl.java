package bqtest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import bqtest.service.Member;
import bqtest.service.MemberFileProcessor;
import bqtest.service.MemberImporter;

@Service
public class MemberFileProcessorImpl extends MemberFileProcessor {

    /*
     * Implement methods here
     */

    @Override
    protected MemberImporter getMemberImporter() {
    	
    	/*
    	 * Returning new instance of MemberImporter
    	 */
    	
    	MemberImporter memberImporter =  new MemberImporterImpl();
    	
    	return memberImporter;
    }

    @Override
    protected List<Member> getNonDuplicateMembers(List<Member> membersFromFile) {
    	
    	/** 
    	 * Can't modify the Member class source code as per the instructions. 
    	 * Duplicate objects can be avoided by implementing equals() and hashCode() 
    	 * Due to the restriction going for long process
    	 */
    	
    	Map<String, Member> map = new HashMap<String, Member>();
    	
    	/** 
    	 * Creating a Map with String Id and Member object to avoid duplicate objects based on Id
    	 */
    	
    	for(Member member: membersFromFile) {
			String memberId = member.getId();
			if( !map.containsKey(memberId)) {
				map.put(memberId, member);
			}
		}
    	List<Member> list = new ArrayList<Member>();
    	
    	/** 
    	 * Adding all the filtered unique Members to a list
    	 */
    	
    	list.addAll(map.values());
		return list;
    }

    @Override
    protected Map<String, List<Member>> splitMembersByState(List<Member> validMembers) {
    	
    	Set<String> states = new HashSet<String>();
		
		/* 
		 * Filtering unique states
		 */
		for(Member member : validMembers) {
			
			states.add(member.getState());
		}
		
		/* 
		 * Filtering members based upon each state
		 */
		
		Map< String, List< Member >> splittedMembersByState = new HashMap< String, List< Member >>();
		
		for(String state : states) {
			
			List<Member> memberByState = new ArrayList<Member>();
			
			for(Member member : validMembers) {
				
				if(state.equals(member.getState())) {
					memberByState.add(member);
				}
			}
			
			splittedMembersByState.put(state, memberByState);
		}
    	
    	
    	
        return splittedMembersByState;
    }

}
