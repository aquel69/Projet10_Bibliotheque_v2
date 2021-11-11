package fr.lardon.bibliocataloguelivres;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class BiblioCatalogueLivresApplicationTests {



	@Test
	void contextLoads() {
		assertThat("Hello JUnit 5").isEqualTo("Hello JUnit 5");
	}

}
