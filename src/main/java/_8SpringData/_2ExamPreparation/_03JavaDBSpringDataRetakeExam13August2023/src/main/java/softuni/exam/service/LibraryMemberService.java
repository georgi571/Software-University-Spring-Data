package softuni.exam.service;

import java.io.IOException;

// TODO: Implement all methods
public interface LibraryMemberService {

    boolean areImported();

    String readLibraryMembersFileContent() throws IOException;
	
	String importLibraryMembers() throws IOException;
}
