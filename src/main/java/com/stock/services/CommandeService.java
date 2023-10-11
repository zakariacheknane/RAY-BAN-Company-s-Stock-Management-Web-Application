package com.stock.services;

import java.util.List;

import com.stock.entities.Commande;

public interface CommandeService {
	public List<Commande> findAll();
	public Commande find(Long codeArt);
	public void create(Commande commande);
	public void update(Commande commande);
	public void delete(Commande commande);
}
