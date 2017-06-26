/*
 * @Author: Julio Sergio Quadros dos Santos.
 * @Email: julioquadros@hotmail.com
 * @2017 - Todos os Direitos Reservados
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Catalogo;
import java.io.Serializable;

public class CatalogoDAO<T> extends DAOGenerico<Catalogo> implements Serializable {

    public CatalogoDAO(){
        super();
        super.classePersistente = Catalogo.class;
    }
}