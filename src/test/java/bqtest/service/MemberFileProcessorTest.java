package bqtest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberFileProcessorTest {

	@Autowired
	private MemberFileProcessor memberFileProcessor;
	
	@Autowired
	private MemberImporter memberImporter;
	
	
	@Test
	@DisplayName("Test Instance of MemberImporter")
	void testGetMemberImporter() {
		MemberImporter mi = memberFileProcessor.getMemberImporter();
		
		assertTrue(mi instanceof MemberImporter);
	}

	@Test
	@DisplayName("Test Get Non-Duplicate Members")
	void testGetNonDuplicateMembers() throws Exception {
		
		/**
		 * Can't create new Member by parameterized constructor because of restriction of modifying Member class source code 
		 * So going for long process and getting List of Member from file data 
		 */
		
		File inputMemberFile = new File("MembersTest.txt");
		List<Member> member = memberImporter.importMembers(inputMemberFile);
		
		/**
		 * MembersTest.txt is a new file added in the project
		 * It contains 6 Members out of 3 are duplicates
		 */
		List< Member > nonDuplicateMembers = memberFileProcessor.getNonDuplicateMembers(member);
		assertEquals(3, nonDuplicateMembers.size());
		
	}

	@Test
	@DisplayName("Test Splitted Members By State")
	void testSplitMembersByState() throws Exception {
		
		File inputMemberFile = new File("Members.txt");
		List<Member> member = memberImporter.importMembers(inputMemberFile);
		
		Map<String, List<Member>> map=
				memberFileProcessor.splitMembersByState(member);
		assertEquals(5, map.keySet().size());
		
	}

	@Test
	@DisplayName("Test Process File Method")
	void testProcessFile() throws Exception {
		File inputMemberFile = new File("Members.txt");
		
		Map<String, List<Member>> map=
				memberFileProcessor.processFile(inputMemberFile);
		assertEquals(5, map.keySet().size());
	}
	

}
