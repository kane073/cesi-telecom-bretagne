package eu.telecom_bretagne.CESI.data.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the authentification database table.
 * 
 */
@Entity
@NamedQuery(name="Authentification.findAll", query="SELECT a FROM Authentification a")
public class Authentification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AUTHENTIFICATION_IDAUTHENTIFICATION_GENERATOR", sequenceName="AUTHENTIFICATION_ID_AUTHENTIFICATION_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUTHENTIFICATION_IDAUTHENTIFICATION_GENERATOR")
	@Column(name="id_authentification")
	private Integer idAuthentification;

	private String login;

	private String password;

	private String role;

	public Authentification() {
	}

	public Integer getIdAuthentification() {
		return this.idAuthentification;
	}

	public void setIdAuthentification(Integer idAuthentification) {
		this.idAuthentification = idAuthentification;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}