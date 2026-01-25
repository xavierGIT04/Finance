package com.ipnet.FinanceApp.Entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.ipnet.FinanceApp.utils.Transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Retrait extends Transaction{
	
	@ManyToOne
	@JoinColumn(name = "compte_id")
	private Compte compte;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	
	public Retrait() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	

}
