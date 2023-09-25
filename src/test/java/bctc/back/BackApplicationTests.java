package bctc.back;

import bctc.back.data.credentials.CredentialsRepository;
import bctc.back.data.users.parent.ParentRepository;
import bctc.back.data.users.student.StudentRepository;
import bctc.back.data.users.turor.TutorRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
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
 */

@SpringBootTest
@Sql({
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


	@SneakyThrows
	@Test
	@Transactional
	public void testLoadDataInCredentials(){
		int expectedSize;
		try (Stream<String> stream = Files.lines(
				Path.of("src/main/resources/sql/credentials.sql"),
				StandardCharsets.UTF_8)
		) {
			expectedSize = (int) stream.count();
		}

		assertSizes(
				credentialsRepository.findAll().size(),
				expectedSize
		);
	}

	@SneakyThrows
	@Test
	@Transactional
	public void testLoadDataInStudent(){
		int expectedSize;
		try (Stream<String> stream = Files.lines(
				Path.of("src/main/resources/sql/student.sql"),
				StandardCharsets.UTF_8)
		) {
			expectedSize = (int) stream.count();
		}

		assertSizes(
				studentRepository.findAll().size(),
				expectedSize
		);
	}

	@SneakyThrows
	@Test
	@Transactional
	public void testLoadDataInParent(){
		int expectedSize;
		try (Stream<String> stream = Files.lines(
				Path.of("src/main/resources/sql/parent.sql"),
				StandardCharsets.UTF_8)
		) {
			expectedSize = (int) stream.count();
		}

		assertSizes(
				parentRepository.findAll().size(),
				expectedSize
		);
	}

	@SneakyThrows
	@Test
	@Transactional
	public void testLoadDataInTutor(){
		int expectedSize;
		try (Stream<String> stream = Files.lines(
				Path.of("src/main/resources/sql/tutor.sql"),
				StandardCharsets.UTF_8)
		) {
			expectedSize = (int) stream.count();
		}

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
}
