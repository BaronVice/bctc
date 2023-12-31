package bctc.back;

import bctc.back.data.credentials.CredentialsRepository;
import bctc.back.data.users.parent.ParentRepository;
import bctc.back.data.users.student.StudentRepository;
import bctc.back.data.users.turor.TutorRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * <strong>Ensure that your properties contains:</strong><br>
 * spring.jpa.hibernate.ddl-auto: create-drop<br>
 * spring.jpa.database-platform: org.hibernate.dialect.PostgreSQLDialect<br>
 * spring.jpa.defer-datasource-initialization: true<br>
 *
 * <strong>If database contains any values</strong>
 */

@SpringBootTest
@Sql({
		"/sql/clear.sql",
		"/sql/credentials.sql",
		"/sql/parent.sql",
		"/sql/student.sql",
		"/sql/tutor.sql"
})
@AllArgsConstructor(onConstructor = @__(@Autowired))
class BackApplicationTests {
	private final CredentialsRepository credentialsRepository;
	private final ParentRepository parentRepository;
	private final StudentRepository studentRepository;
	private final TutorRepository tutorRepository;

	@Test
	@Transactional
	public void testLoadDataInCredentials(){
		int expectedSize = getFileLines("src/main/resources/sql/credentials.sql");

		assertSizes(
				credentialsRepository.findAll().size(),
				expectedSize
		);
	}

	@Test
	@Transactional
	public void testLoadDataInStudent(){
		int expectedSize = getFileLines("src/main/resources/sql/student.sql");

		assertSizes(
				studentRepository.findAll().size(),
				expectedSize
		);
	}

	@Test
	@Transactional
	public void testLoadDataInParent(){
		int expectedSize = getFileLines("src/main/resources/sql/parent.sql");

		assertSizes(
				parentRepository.findAll().size(),
				expectedSize
		);
	}

	@Test
	@Transactional
	public void testLoadDataInTutor(){
		int expectedSize = getFileLines("src/main/resources/sql/tutor.sql");

		assertSizes(
				tutorRepository.findAll().size(),
				expectedSize
		);
	}

	private void assertSizes(int givenSize, int expectedSize){
		Assert.isTrue(
				givenSize == expectedSize,
				String.format(
						"Size error: given size is %d but expected %d", givenSize, expectedSize
				)
		);
	}

	@SneakyThrows
	private int getFileLines(String path){
		int lines;
		try (Stream<String> stream = Files.lines(
				Path.of(path),
				StandardCharsets.UTF_8)
		) {
			lines = (int) stream.count();
		}

		return lines;
	}
}
