/*
 * @Author: Julio Sergio Quadros dos Santos.
 * @Email: julioquadros@hotmail.com
 * @2017 - Todos os Direitos Reservados
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Idioma;
import java.io.Serializable;

public class IdiomaDAO<T> extends DAOGenerico<Idioma> implements Serializable{
    
    public IdiomaDAO(){
        super();
        super.setClassePersistente(Idioma.class);  
    }
}
