package br.com.gdaniel.cursomc.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gdaniel.cursomc.Dao.CategoriaDao;
import br.com.gdaniel.cursomc.Model.Categoria;
import br.com.gdaniel.cursomc.Services.Exceptions.ExceptionsObjectNotFound;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaDao em;
	
	
	public Categoria buscar(Integer id) {  
		Optional<Categoria> obj = em.findById(id);  
		
		return obj.orElseThrow(()-> new ExceptionsObjectNotFound("Objeto não encontrado! Id: " + id + ", Tipo: " + 
		Categoria.class.getName()));
	}
	
}
