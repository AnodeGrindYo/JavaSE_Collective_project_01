package BDD;
/**
 *
 */


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import Partie.Joueur;

//import Modele.Joueur;

/**
 * @author Daphne Cluseau
 *
 */
public class BDD{

	static Statement stmt;
	static Connection con;
	static String userid = "javap3";
	static String password = "ldnr";
	static ResultSet rs = null;

	static String url = "jdbc:mysql://mbret.net:3306/javap3";

	public BDD(){

	}

	public static void close(){
		try {
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}    
	}

	public static Connection getConnection()
	{
		try {
			con = DriverManager.getConnection(url, userid, password);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static ResultSet selectFrom(String queryString){

		Connection con = getConnection();
		try{
			stmt = con.createStatement();
			rs = (ResultSet) stmt.executeQuery(queryString);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}    

	public static void InsertTable(String queryString) throws SQLException{

		Connection con = getConnection();
		try{
			stmt = con.createStatement();
			stmt.executeUpdate(queryString);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}    

	//Permet d'inscrire un joueur
	public void AjouterJoueur(Joueur j){
		String nom=j.getNom();
		String prenom=j.getPrenom();
		String pseudo=j.getpseudo();
		String sexe=j.getSexe();
		Date date=j.getdateDeNaissance();
		String mail=j.getemail();
		String mdp=j.getmdp();
		try
		{
			InsertTable("INSERT INTO joueur(nom, prenom, pseudo, naissance, sexe, mail, mdp)VALUES('"+ nom +"','"+prenom+"','"+pseudo+"','"+date+"','"+sexe+"','"+mail+"','"+mdp+"');");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Permet de récupérer les infos du joueur
	public Joueur InfosJoueur(int id) throws SQLException{
		ResultSet rs = selectFrom("SELECT nom, prenom, pseudo, naissance, sexe, mail FROM joueur WHERE id_joueur="+id+";");
		Joueur j=new Joueur();
		if (rs.first())
		{
	//		System.out.println(rs.getString(1)); // TEST
			j.setNom(rs.getString(1));
			j.setPrenom(rs.getString(2));
			j.setpseudo(rs.getString(3));
			j.setdateDeNaissance(rs.getDate(4));
			j.setSexe(rs.getString(5));
			j.setemail(rs.getString(6));
			j.setId(id);
		}
		return j;
	}

	//Permet de récupérer l'id du joueur une fois qu'il a renseigné son mail et son mot de passe
	public int ConnecterJoueur(String mail, String mdp) throws SQLException{
		
		ResultSet rs = selectFrom("SELECT id_joueur FROM joueur WHERE mail='"+mail+"' AND mdp='"+mdp+"';");
		if(rs.first()){
			System.out.println(rs.getInt(1));
			return rs.getInt(1);
		}else{
			return -1;//-1 signale qu'il y a une erreur
		}
	}

	//Renvoie le nombre de parties auxquelles le joueur a participé
	public int Participation(Joueur j) throws SQLException{
		ResultSet rs = selectFrom("SELECT COUNT (*) FROM liaison WHERE liaison.id_joueur="+j.getId()+";");
		int p = rs.getInt(1);
		return p;
	}

	//Renvoie le nombre de parties que le joueur a gagnées
	public int PartiesGagnees(Joueur j) throws SQLException{
		ResultSet rs = selectFrom("SELECT COUNT (*) FROM liaison, partie WHERE liaison.id_partie=partie.id_partie AND liaison.id_joueur="+j.getId()+" AND liaison.noJoueur=partie.resultat;");
		int g = rs.getInt(1);
		return g;
	}

	//Renvoie le nombre de parties que le joueur a perdues
	public int PartiesPerdues(Joueur j) throws SQLException{
		ResultSet rs = selectFrom("SELECT COUNT (*) FROM liaison, partie WHERE liaison.id_partie=partie.id_partie AND liaison.id_joueur="+j.getId()+" AND liaison.noJoueur!=partie.resultat AND partie.resultat>0;");
		int p = rs.getInt(1);
		return p;
	}

	//Permet de modifier le joueur suivant le champ renseigné
	public void ModifierJoueur(String champ, String val, int id){
		try
		{
			InsertTable("UPDATE joueur SET "+champ+"='"+val+"' WHERE id_joueur="+id+";");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Permet de supprimer un joueur de la BDD
	public void SupprimerJoueur(int id){
		try
		{
			InsertTable("DELETE FROM joueur WHERE id_joueur="+id+"");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Permet de créer une partie sur la BDD
	public int CreerPartie(int id_joueur, int noJoueur) throws SQLException{
		LocalDateTime today = LocalDateTime.now();
		InsertTable("INSERT INTO partie(id_partie, date_debut) VALUES (NULL,'"+today+"');");
		System.out.println("CreerPartie : InsertTable 1 OK");
		ResultSet rs = selectFrom("SELECT id_partie FROM partie WHERE date_debut='"+today+"';");
		System.out.println("CreerPartie : ResultSet OK");
		if(rs.first()){	
			int p = rs.getInt(1);
			System.out.println("CreerPartie : p = "+p);
			System.out.println("CreerPartie : id_joueur = "+id_joueur+" ; noJoueur = "+noJoueur);
			InsertTable("INSERT INTO liaison(id_partie, id_joueur,noJoueur) VALUES ('"+p+"','"+id_joueur+"','"+noJoueur+"');");
			System.out.println("CreerPartie : InsertTable 2 OK");
			return p;
		}else{
			return -1;
		}
		
	}

	//Permet de rejoindre une partie existente sur la BDD
	public void RejoindrePartie(int id_partie, int id_joueur, int noJoueur){
		try
		{
			InsertTable("INSERT INTO liaison(id_partie, id_joueur,noJoueur) VALUES ('"+id_partie+"','"+id_joueur+"','"+noJoueur+"');");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Permet d'enregistrer les coups sur la BDD
  	public void JouerCoup(int id_joueur, int id_partie, int coord)
  	{
  		//récupérer l'id du joueur et l'id de la partie, ainsi que les coordonnées
  		LocalDateTime today = LocalDateTime.now();
  		try
  		{
  			InsertTable("INSERT INTO coup (id_partie, id_joueur, coord,datecoup) VALUES ('"+id_partie+"','"+id_joueur+"','"+coord+"','"+today+"');");
  		} catch (SQLException e)
  		{
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	}

	//Permet d'enregistrer les résulats de la partie sur la BDD (notamment le joueur qui a gagné)
	public void FinPartie(int res, int id_partie){
		try
		{
			InsertTable("UPDATE partie SET resultat="+res+" WHERE id_partie=id_partie;");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *
	 */
	// renvoie 1 si le joueur existe déjà, sinon, renvoie -1
	public int VerifierJoueur(Joueur j) throws SQLException{
		ResultSet rs = selectFrom("SELECT id_joueur FROM joueur WHERE mail='"+j.getemail()+"';");
		if(rs.first()){
			//Boîte du message d'erreur
			JOptionPane jop3 = new JOptionPane();
			jop3.showMessageDialog(null, "Le joueur existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
			return 1;
		}else{
			return -1;
		}
	}
}