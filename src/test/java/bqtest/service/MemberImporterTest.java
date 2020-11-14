 package bqtest.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberImporterTest {

	@Autowired
	private MemberImporter memberImporter;
	
	@Test
	@DisplayName("Test Import Members from file")
	void testImportMembers() throws Exception {
		File inputMemberFile = new File("Members.txt");
		List<Member> member = memberImporter.importMembers(inputMemberFile);
		Assertions.assertEquals( 108 , member.size());
	}
	
	@Test
	@DisplayName("Test FileNotFoundException")
	void testExpectedException() {
	 
	  assertThrows(IOException.class, () -> {
		  File inputMemberFile = new File("MemberNotExist.txt");
		  memberImporter.importMembers(inputMemberFile);
	  });
	 
	}

}
