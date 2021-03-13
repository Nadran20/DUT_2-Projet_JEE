package persistantdata;

import java.util.List;
import mediatek2021.Document;
import mediatek2021.NewDocException;
import mediatek2021.SuppressException;
import mediatek2021.Utilisateur;

public interface PersitantData {

	// renvoie la liste de tous les documents de la bibliothèque
	List<Document> catalogue(int type);

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	Utilisateur getUser(String login, String password);

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	Document getDocument(int numDocument);

	// ajoute un nouveau document - exception à définir
	void newDocument(int type, Object... args) throws NewDocException;

	// supprime un document - exception à définir
	void suppressDoc(int numDoc) throws SuppressException;

}