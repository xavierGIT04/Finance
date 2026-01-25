package com.ipnet.FinanceApp.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.ipnet.FinanceApp.utils.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Compte extends BaseEntity{
	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private UUID publicId = UUID.randomUUID();
	
	@Column(length = 16, nullable = false, unique = true, updatable = false)
	private String numero;
	
	@Column(nullable = false)
	private float solde;
	
	@ManyToOne
	@JoinColumn(name = "type_compte_id")
	private TypeCompte typeCompte;
	
	@OneToMany(mappedBy = "compte")
	private List<Depot> depots = new ArrayList<>();
	
	@OneToMany(mappedBy = "compte")
	private List<Retrait> retraits = new ArrayList<>();
	
	@OneToMany(mappedBy = "compteDestination")
	private List<Virement> virementsAdestinationDeCeCompte = new ArrayList<>();
	
	@OneToMany(mappedBy = "compteSource")
	private List<Virement> virementsApartirDeCeCompte = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "proprietaire_id")
	private Client proprietaire;
	

	public Long getId() {
		return id;
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public float getSolde() {
		return solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}

	public TypeCompte getTypeCompte() {
		return typeCompte;
	}

	public void setTypeCompte(TypeCompte typeCompte) {
		this.typeCompte = typeCompte;
	}

	public Client getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Client proprietaire) {
		this.proprietaire = proprietaire;
	}

	public List<Depot> getDepots() {
		return depots;
	}

	public void setDepots(List<Depot> depots) {
		this.depots = depots;
	}

	public List<Retrait> getRetraits() {
		return retraits;
	}

	public void setRetraits(List<Retrait> retraits) {
		this.retraits = retraits;
	}

	public List<Virement> getVirementsAdestinationDeCeCompte() {
		return virementsAdestinationDeCeCompte;
	}

	public void setVirementsAdestinationDeCeCompte(List<Virement> virementsAdestinationDeCeCompte) {
		this.virementsAdestinationDeCeCompte = virementsAdestinationDeCeCompte;
	}

	public List<Virement> getVirementsApartirDeCeCompte() {
		return virementsApartirDeCeCompte;
	}

	public void setVirementsApartirDeCeCompte(List<Virement> virementsApartirDeCeCompte) {
		this.virementsApartirDeCeCompte = virementsApartirDeCeCompte;
	}
	
	
	
	
}
