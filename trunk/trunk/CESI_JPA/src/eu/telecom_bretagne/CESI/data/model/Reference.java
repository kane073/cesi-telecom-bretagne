package eu.telecom_bretagne.CESI.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the reference database table.
 * 
 */
@Entity
@NamedQuery(name="Reference.findAll", query="SELECT r FROM Reference r")
public class Reference implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REFERENCE_IDREFERENCE_GENERATOR", sequenceName="REFERENCE_IDREFERENCE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REFERENCE_IDREFERENCE_GENERATOR")
	private Integer idreference;

	private String codereference;

	@Temporal(TemporalType.DATE)
	private Date datedebutreference;

	@Temporal(TemporalType.DATE)
	private Date datefinreference;

	private String institutionrattachement;

	//bi-directional many-to-one association to Publication
	@ManyToOne
	@JoinColumn(name="id")
	private Publication publication;

	public Reference() {
	}

	public Integer getIdreference() {
		return this.idreference;
	}

	public void setIdreference(Integer idreference) {
		this.idreference = idreference;
	}

	public String getCodereference() {
		return this.codereference;
	}

	public void setCodereference(String codereference) {
		this.codereference = codereference;
	}

	public Date getDatedebutreference() {
		return this.datedebutreference;
	}

	public void setDatedebutreference(Date datedebutreference) {
		this.datedebutreference = datedebutreference;
	}

	public Date getDatefinreference() {
		return this.datefinreference;
	}

	public void setDatefinreference(Date datefinreference) {
		this.datefinreference = datefinreference;
	}

	public String getInstitutionrattachement() {
		return this.institutionrattachement;
	}

	public void setInstitutionrattachement(String institutionrattachement) {
		this.institutionrattachement = institutionrattachement;
	}

	public Publication getPublication() {
		return this.publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

}