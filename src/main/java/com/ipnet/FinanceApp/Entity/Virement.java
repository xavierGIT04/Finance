package com.ipnet.FinanceApp.Entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.ipnet.FinanceApp.utils.Transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Virement extends Transaction {

	@ManyToOne
	@JoinColumn(name = "compte_source_id")
	private Compte compteSource;
	
	@ManyToOne
	@JoinColumn(name = "compte_destination_id")
	private Compte compteDestination;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	
	public Virement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Compte getCompteDestination() {
		return compteDestination;
	}

	public void setCompteDestination(Compte compteDestination) {
		this.compteDestination = compteDestination;
	}

	public Compte getCompteSource() {
		return compteSource;
	}

	public void setCompteSource(Compte compteSource) {
		this.compteSource = compteSource;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
	

}
