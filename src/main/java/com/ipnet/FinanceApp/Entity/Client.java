package com.ipnet.FinanceApp.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.ipnet.FinanceApp.utils.BaseEntity;
import com.ipnet.FinanceApp.utils.Transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Client extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private UUID publicId = UUID.randomUUID();
	
	@Column(length = 56, nullable = false)
	private String nom;
	
	@Column(length = 56, nullable = false)
	private String prenom;
	
	@Column
	private String adresse;
	
	@Column(nullable = false, unique = true)
	private String telephone;
	
	@OneToMany(mappedBy = "proprietaire")
	private List<Compte> comptes = new ArrayList<>();
	
	@OneToMany(mappedBy = "client")
	private List<Virement> virements = new ArrayList<>();
	
	@OneToMany(mappedBy = "client")
	private List<Retrait> retraits = new ArrayList<>();
	
	
	public Long getId() {
		return id;
	}

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public UUID getPublicId() {
		return publicId;
	}

	public void setPublicId(UUID publicId) {
		this.publicId = publicId;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	public List<Virement> getVirements() {
		return virements;
	}

	public void setVirements(List<Virement> virements) {
		this.virements = virements;
	}

	public List<Retrait> getRetraits() {
		return retraits;
	}

	public void setRetraits(List<Retrait> retraits) {
		this.retraits = retraits;
	}

	

}
