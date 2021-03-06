package br.com.gdaniel.cursomc.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.gdaniel.cursomc.Dao.CategoriaDao;
import br.com.gdaniel.cursomc.Model.Categoria;
import br.com.gdaniel.cursomc.Services.Exceptions.ExceptionsObjectNotFound;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaDao em;
	
	
	public Categoria find(Integer id) {  
		Optional<Categoria> obj = em.findById(id);  
		
		return obj.orElseThrow(()-> new ExceptionsObjectNotFound("Objeto não encontrado! Id: " + id + ", Tipo: " + 
		Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {	
		obj.setId(null);
		return em.save(obj);
	}
	
	public Categoria update(Categoria obj) {	
		find(obj.getId());
		return em.save(obj);
	}
	
	public void delete(Integer id) {
		
		find(id);
		
		try {
			
			em.deleteById(id);
		
		}catch (DataIntegrityViolationException e) {
			throw new br.com.gdaniel.cursomc.Services.Exceptions.
			DataIntegrityViolationException("Não é possivel excluir uma categoria que possui produtos");
		}
	}
	
	
	public List<Categoria> findAll() {  
		return em.findAll();  
	}
	
}
