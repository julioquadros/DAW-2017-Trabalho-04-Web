package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Livraria;
import java.io.Serializable;

public class LivrariaDAO<T> extends DAOGenerico<Livraria> implements Serializable {
    
    public LivrariaDAO(){
        super();
        super.setClassePersistente(Livraria.class); 
        ordem = "nome";
    }
    
//    public Livraria getObjectById(Integer id) throws Exception {
//        Livraria obj = (Livraria) em.find(classePersistente, id);
//        /// inicializando a lista para não lazy inicialization exception
//        obj.getCatalogos().size();
//        return obj;
//    }
   
}