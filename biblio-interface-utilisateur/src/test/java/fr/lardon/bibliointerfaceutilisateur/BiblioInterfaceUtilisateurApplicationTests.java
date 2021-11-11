package fr.lardon.bibliointerfaceutilisateur;

import fr.lardon.bibliointerfaceutilisateur.controller.CatalogueController;
import fr.lardon.bibliointerfaceutilisateur.controller.EmployeController;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Abonne;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.AbonnePretOuvrage;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.Livre;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.Ouvrage;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.Pret;
import fr.lardon.bibliointerfaceutilisateur.proxies.MicroserviceGestionUtilisateur;
import fr.lardon.bibliointerfaceutilisateur.proxies.MicroserviceLivresProxy;

import fr.lardon.bibliointerfaceutilisateur.technical.FunctionalException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class BiblioInterfaceUtilisateurApplicationTests {

	@InjectMocks
	@Spy
	EmployeController employeController;

	@InjectMocks
	@Spy
	CatalogueController catalogueController;

	@Mock
	MicroserviceLivresProxy livreProxy;

	@Mock
	MicroserviceGestionUtilisateur gestionUtilisateur;

	private List<Pret> pretList;
	private Ouvrage ouvrage;
	private AbonnePretOuvrage abonnePretOuvrage;
	private Livre livre;
	private List<Ouvrage> ouvrageList;
	private Pret pret;

	@Test
	@MockitoSettings(strictness = Strictness.LENIENT)
	@Tag("verificationDEmpruntIdentique")
	@DisplayName("vérification si l'emprunt de l'abonné est identique et retourne true")
	void verificationDEmpruntIdentiqueTestReturnTrue() {
		//Given
		instanciationVariableEmpruntIdentique();

		abonnePretOuvrage.setListeOuvrage(ouvrageList);
		abonnePretOuvrage.setListePret(pretList);

		abonnePretOuvrage.setListePret(pretList);

		//When
		doReturn(abonnePretOuvrage).when(employeController).getAbonnePretOuvrage();

		boolean reponse = employeController.verificationDEmpruntIdentique(ouvrage);

		assertThat(reponse).isEqualTo(true);
	}

	@Test
	@MockitoSettings(strictness = Strictness.LENIENT)
	@Tag("verificationDEmpruntIdentique")
	@DisplayName("vérification si l'ouvrage est déjà rendu et retourne true")
	void verificationDEmpruntIdentiqueSiLOuvrageEstDejaRenduTestReturnFalse() {
		//Given
		instanciationVariableEmpruntIdentique();

		pret.setRendu(true);
		pretList.add(pret);

		abonnePretOuvrage.setListeOuvrage(ouvrageList);
		abonnePretOuvrage.setListePret(pretList);

		abonnePretOuvrage.setListePret(pretList);

		//When
		doReturn(abonnePretOuvrage).when(employeController).getAbonnePretOuvrage();
		boolean reponse = employeController.verificationDEmpruntIdentique(ouvrage);

		//Then
		assertThat(reponse).isEqualTo(false);
	}

	@Test
	@MockitoSettings(strictness = Strictness.LENIENT)
	@Tag("verificationDEmpruntIdentique")
	@DisplayName("vérification si l'emprunt de l'abonné n'est pas identique et retourne false")
	void verificationDEmpruntIdentiqueTestReturnFalse() {
		//Given
		instanciationVariableEmpruntIdentique();
		Ouvrage ouvrage1 = new Ouvrage();
		Livre livre1 = new Livre();
		List<Pret> pretList1 = new ArrayList<>();

		livre1.setIdLivre(2);
		ouvrage1.setLivre(livre1);
		pretList1.add(pret);

		abonnePretOuvrage.setListeOuvrage(ouvrageList);
		abonnePretOuvrage.setListePret(pretList1);

		abonnePretOuvrage.setListePret(pretList1);

		//When
		doReturn(abonnePretOuvrage).when(employeController).getAbonnePretOuvrage();
		boolean reponse = employeController.verificationDEmpruntIdentique(ouvrage1);

		//Then
		assertThat(reponse).isEqualTo(false);
	}

	@Test
	@MockitoSettings(strictness = Strictness.LENIENT)
	@Tag("verificationOuvrageExistant")
	@DisplayName("vérification que l'ouvrage en paramètre est bien existant et que la méthode retourne true")
	void verificationOuvrageExistantTestReturnTrue() {
		//Given
		Ouvrage ouvrage1 = new Ouvrage();
		List<Ouvrage> ouvrageList = new ArrayList<>();

		ouvrage1.setCodeBibliotheque("18004625200568");
		ouvrageList.add(ouvrage1);

		//When
		doReturn(ouvrageList).when(livreProxy).listeDesOuvrages();
		boolean reponse = employeController.verificationOuvrageExistant(ouvrage1);

		//Then
		assertThat(reponse).isEqualTo(true);
	}

	@Test
	@MockitoSettings(strictness = Strictness.LENIENT)
	@Tag("verificationOuvrageExistant")
	@DisplayName("vérification que l'ouvrage en paramètre n'est pas existant et que la méthode retourne false")
	void verificationOuvrageExistantTestReturnFalse() {
		//Given
		Ouvrage ouvrage1 = new Ouvrage();
		Ouvrage ouvrage2 = new Ouvrage();
		List<Ouvrage> ouvrageList = new ArrayList<>();

		ouvrage1.setCodeBibliotheque("18004625200568");
		ouvrage2.setCodeBibliotheque("18004625200569");
		ouvrageList.add(ouvrage1);

		//When
		doReturn(ouvrageList).when(livreProxy).listeDesOuvrages();
		boolean reponse = employeController.verificationOuvrageExistant(ouvrage2);

		//Then
		assertThat(reponse).isEqualTo(false);
	}

	@Test
	@MockitoSettings(strictness = Strictness.LENIENT)
	@Tag("verificationAbonneExistant")
	@DisplayName("vérification que l'abonné est existant et que la méthode retourne true")
	void verificationAbonneExistantTestReturnTrue() {
		//Given
		Abonne abonne = new Abonne();
		List<Abonne> abonneList = new ArrayList<>();

		abonne.setNumeroAbonne("456132246");
		abonneList.add(abonne);

		//When
		doReturn(abonneList).when(gestionUtilisateur).listeAbonnes();
		boolean reponse = employeController.verificationAbonneExistant(abonne);

		//Then
		assertThat(reponse).isEqualTo(true);
	}

	@Test
	@MockitoSettings(strictness = Strictness.LENIENT)
	@Tag("verificationAbonneExistant")
	@DisplayName("vérification que l'abonné n'existe pas et que la méthode retourne false")
	void verificationAbonneExistantTestReturnFalse() {
		//Given
		Abonne abonne = new Abonne();
		Abonne abonneFalse = new Abonne();
		List<Abonne> abonneList = new ArrayList<>();

		abonne.setNumeroAbonne("456132246");
		abonneList.add(abonne);

		abonneFalse.setNumeroAbonne("456132245");

		//When
		doReturn(abonneList).when(gestionUtilisateur).listeAbonnes();
		boolean reponse = employeController.verificationAbonneExistant(abonneFalse);

		//Then
		assertThat(reponse).isEqualTo(false);
	}

	@Test
	@MockitoSettings(strictness = Strictness.LENIENT)
	@Tag("calculNombreDePage")
	@DisplayName("vérification que l'exception se lève si le nombre de page est négatif")
	void calculNombreDePageTestSiException() {
		//Given
		int nbTotalPages = -1;
		List<Livre> listeLivresRecherche = new ArrayList<>();
		listeLivresRecherche.add(new Livre());

		//When
		//Then
		assertThrows( FunctionalException.class, () ->
						catalogueController.calculNombreDePage(nbTotalPages, listeLivresRecherche),
				"Le nombre de page est négatif.");
	}

	@Test
	@MockitoSettings(strictness = Strictness.LENIENT)
	@Tag("calculNombreDePage")
	@DisplayName("vérification normal de la méthode")
	void calculNombreDePageTest() throws FunctionalException {
		//Given
		int nbTotalPages = 1;
		List<Livre> listeLivresRecherche = new ArrayList<>();
		listeLivresRecherche.add(new Livre());

		//When
		//Then
		assertThat(catalogueController.calculNombreDePage(nbTotalPages, listeLivresRecherche)).isEqualTo(1);
	}

	public void instanciationVariableEmpruntIdentique(){
		ouvrageList = new ArrayList<>();
		livre = new Livre();
		pret = new Pret();
		ouvrage = new Ouvrage();
		abonnePretOuvrage = new AbonnePretOuvrage();
		pretList = new ArrayList<>();

		livre.setIdLivre(1);
		ouvrage.setLivre(livre);
		ouvrageList.add(ouvrage);
		pret.setOuvragePret(ouvrage);
		pret.setRendu(false);
		pretList.add(pret);
	}
}



