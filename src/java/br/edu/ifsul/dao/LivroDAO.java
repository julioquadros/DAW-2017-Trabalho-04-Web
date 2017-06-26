/*
 * @Author: Julio Sergio Quadros dos Santos.
 * @Email: julioquadros@hotmail.com
 * @2017 - Todos os Direitos Reservados
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Livro;
import java.io.Serializable;

public class LivroDAO<T> extends DAOGenerico<Livro> implements Serializable {
    
    public LivroDAO(){
        super();
        super.setClassePersistente(Livro.class);       
    }
   
}