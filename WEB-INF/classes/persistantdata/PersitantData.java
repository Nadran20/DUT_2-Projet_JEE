package persistantdata;

import java.util.List;
import mediatek2021.Document;
import mediatek2021.NewDocException;
import mediatek2021.SuppressException;
import mediatek2021.Utilisateur;

public interface PersitantData {

	// renvoie la liste de tous les documents de la biblioth�que
	List<Document> catalogue(int type);

	// va r�cup�rer le User dans la BD et le renvoie
	// si pas trouv�, renvoie null
	Utilisateur getUser(String login, String password);

	// va r�cup�rer le document de num�ro numDocument dans la BD
	// et le renvoie
	// si pas trouv�, renvoie null
	Document getDocument(int numDocument);

	// ajoute un nouveau document - exception � d�finir
	void newDocument(int type, Object... args) throws NewDocException;

	// supprime un document - exception � d�finir
	void suppressDoc(int numDoc) throws SuppressException;

}