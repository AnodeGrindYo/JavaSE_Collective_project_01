package Partie;
//package Modele;

/*
 * @author Adrien Godoy
 */

import java.sql.Date;


	public class Joueur {
			private int id_joueur;
			private String nom;
			private String prenom;
			private Date dateDeNaissance;
			private String pseudo;
			private String sexe;
			private String email;
			private String mdp;
			private int numero;
			
			public Joueur(){
				
			}
			public Joueur(String nom, String prenom, Date dateDeNaissance, String pseudo, String email,String mdp)
			{
				super();
				this.nom = nom;
				this.prenom = prenom;
				this.dateDeNaissance=dateDeNaissance;
				this.pseudo=pseudo;
				this.email=email;
				this.mdp=mdp;
			}
			
			public int getId(){
				return id_joueur;
			}
			
			public void setId(int id){
				this.id_joueur = id;
			}
			public String getNom() {
				return nom;
			}
			
			public void setNom(String nom) {
				this.nom = nom;
			}
			
			public String getPrenom() {
				return prenom;
			}
			
			public void setPrenom(String prenom) {
				this.prenom = prenom;
			}
			
			public Date getdateDeNaissance() {
				return dateDeNaissance;
			}
			
			public void setdateDeNaissance(Date dateDeNaissance) {
				this.dateDeNaissance = dateDeNaissance;
			}
			
			public String getpseudo() {
				return pseudo;
			}
			
			public void setpseudo(String pseudo) {
				this.pseudo = pseudo;
			}
			
			public String getemail() {
				return email;
			}
			
			public void setemail(String email) {
				this.email = email;
			}
			
			public String getmdp() {
				return this.mdp;
			}
			
			public String getSexe(){
				return sexe;
			}
			
			public void setSexe(String sexe){
				this.sexe=sexe;
			}
			
			public void setmdp(String mdp) {
				this.mdp = mdp;
			}
			
			
			public String toString() {
				return "Personne [nom = " + nom +
						       ", prenom = " + prenom + ",dateDeNaissance=" +dateDeNaissance + "pseudo=" +pseudo +",email=" +email + "mdp=" +mdp + "]";
			}
			
			public int getNumero()
			{
				return numero;
			}
			public void setNumero(int numero)
			{
				this.numero = numero;
			}
			
			
		}