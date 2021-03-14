package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mediatek2021.*;

// classe mono-instance : l'unique instance est connue de la bibliotheque
// via une injection de dépendance dans son bloc static

public class MediatekData implements PersistentMediatek, PersitantData {


	// Jean-François Brette 01/01/2018
	private static final String USER = "sa"; 
	private static final String PASS = "root"; 
	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "org.h2.Driver";   
	private static final String DB_URL = "jdbc:h2:~/test"; 
	private String req;
	ResultSet rs=null;
	static Connection conn =null;
	static Statement stmt=null;
	
	static {
		// injection dynamique de la dépendance dans le package stable mediatek2021
		Mediatek.getInstance().setData(new MediatekData());
		try {
			// STEP 1: Register JDBC driver 
	        Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt  = conn.createStatement();
			
			String sql = "DROP TABLE IF EXISTS USER;" +
					"DROP TABLE IF EXISTS DOCUMENT;" +
					"DROP SEQUENCE IF EXISTS SeqIdDoc;";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE USER " + 
		            "(login VARCHAR(255) PRIMARY KEY, " +  
		            " password VARCHAR(255) not NULL,"
		            + "type VARCHAR(255) not NULL)"; 
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE DOCUMENT " +
				"(idDoc INTEGER PRIMARY KEY, " +
				" titre VARCHAR(255) not NULL," +
				" auteur VARCHAR(255) not NULL," +
				" type INTEGER not NULL," + 		//0 pour livre, 1 pour CD, 2 pour DVD
				" disponible INTEGER not NULL)";
			stmt.executeUpdate(sql);
			
			sql ="CREATE SEQUENCE IF NOT EXISTS SeqIdDoc START WITH 1 INCREMENT BY 1 MINVALUE 1";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO USER " + "VALUES ('root', 'root','bibli')"; 
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO DOCUMENT " + "VALUES (SeqIdDoc.NEXTVAL, 'la chasse du bison tome 1', 'Grand chef RedBull', 0, 1)"; 
			stmt.executeUpdate(sql);
			
			stmt.close();
			conn.close(); 
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// renvoie la liste de tous les documents de la bibliothèque
	@Override
	public List<Document> catalogue(int type) {
		ArrayList<Document> cat=new ArrayList<Document>();
		req="SELECT * FROM Document where type="+type;
		try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt  = conn.createStatement();
			rs = stmt.executeQuery(req);
			while(rs.next()){ 
				cat.add(new Doc(Integer.parseInt(rs.getString("idDoc")),rs.getString("titre"), rs.getString("auteur"), Integer.parseInt(rs.getString("type")), 1));
			} 
			stmt.close();
			conn.close();
			rs.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cat;
	}
	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override 
	public Utilisateur getUser(String login, String password) {
		req="SELECT * FROM USER where login=\'"+login+"' and password='"+password+"';";
		User u = null;
		try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt  = conn.createStatement();
			rs=stmt.executeQuery(req);
			
			while(rs.next()) {
				u = new User(rs.getString("login"),rs.getString("password"),rs.getString("type"));
			}
			
			stmt.close();
			conn.close();
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		req="SELECT * FROM Document where idDoc ="+numDocument;
		Doc d = null;
		
		try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt  = conn.createStatement();
			
			rs= stmt.executeQuery(req);
			
			while(rs.next()) {
				d = new Doc(Integer.parseInt(rs.getString("idDoc")),rs.getString("titre"), rs.getString("auteur"), Integer.parseInt(rs.getString("type")), 1);
			}
			
			stmt.close();
			conn.close();
			rs.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}

	// ajoute un nouveau document - exception à définir
	@Override
	public void newDocument(int type, Object... args) throws NewDocException {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc en fonction du type et des infos optionnelles
		
		 req = "INSERT INTO DOCUMENT VALUES (SeqIdDoc.NEXTVAL,'"+args[0]+"','"+args[1]+"',"+type+",1);";
         try {
        	conn = DriverManager.getConnection(DB_URL,USER,PASS);
 			stmt  = conn.createStatement();
 			
 			stmt.executeUpdate(req);
			
			stmt.close();
			conn.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
         
	}

	// supprime un document - exception à définir
	@Override
	public void suppressDoc(int numDoc) throws SuppressException {
		 String sql = "DELETE FROM DOCUMENT  " + "WHERE idDoc = "+numDoc; 
         try {
        	conn = DriverManager.getConnection(DB_URL,USER,PASS);
 			stmt  = conn.createStatement();
 			
			stmt.executeUpdate(sql);
			
			stmt.close();
			conn.close();						
         } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}