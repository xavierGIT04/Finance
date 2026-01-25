package com.ipnet.FinanceApp.Entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.ipnet.FinanceApp.utils.Transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Depot extends Transaction{

	@Column(nullable = false)
	private String nomDeposant;
	
	@Column(nullable = false)
	private String telephoneDeposant;
	
	@Column(nullable = false)
	private String adresseDeposant;
	
	@ManyToOne
	@JoinColumn(name = "compte_id")
	private Compte compte;
	
	
	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public String getNomDeposant() {
		return nomDeposant;
	}

	public void setNomDeposant(String nomDeposant) {
		this.nomDeposant = nomDeposant;
	}

	public String getTelephoneDeposant() {
		return telephoneDeposant;
	}

	public void setTelephoneDeposant(String telephoneDeposant) {
		this.telephoneDeposant = telephoneDeposant;
	}

	public String getAdresseDeposant() {
		return adresseDeposant;
	}

	public void setAdresseDeposant(String adresseDeposant) {
		this.adresseDeposant = adresseDeposant;
	}
	
	
}
