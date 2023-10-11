package com.stock.services;

import java.util.List;


import com.stock.entities.Commande;
import com.stock.respositories.CommandeRepository;


public class CommandeServiceImpl implements CommandeService{
	private CommandeRepository commandeRepository;
	
	public CommandeServiceImpl(CommandeRepository commandeRepository) {
		super();
		this.commandeRepository = commandeRepository;
	}
	
	@Override
	public List<Commande> findAll() {
		// TODO Auto-generated method stub
		return commandeRepository.findAll();
	}

	@Override
	public Commande find(Long codeArt) {
		// TODO Auto-generated method stub
		return commandeRepository.find(codeArt);
	}

	@Override
	public void create(Commande commande) {
		commandeRepository.create(commande);
		
	}

	@Override
	public void update(Commande commande) {
		commandeRepository.update(commande);
		
	}

	@Override
	public void delete(Commande commande) {
		// TODO Auto-generated method stub
		commandeRepository.delete(commande);
		
	}

}
