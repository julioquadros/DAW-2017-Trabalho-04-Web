/*
 * @Author: Julio Sergio Quadros dos Santos.
 * @Email: julioquadros@hotmail.com
 * @2017 - Todos os Direitos Reservados
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AutorDAO;
import br.edu.ifsul.dao.LivroDAO;
import br.edu.ifsul.dao.FormatoDAO;
import br.edu.ifsul.dao.IdiomaDAO;
import br.edu.ifsul.dao.CatalogoDAO;
import br.edu.ifsul.modelo.Livro;
import br.edu.ifsul.modelo.Autor;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "controleLivro")
@SessionScoped
public class ControleLivro implements Serializable{
        
    private LivroDAO dao;
    private Livro objeto;
    private Autor autor;
    private FormatoDAO daoFormato;
    private IdiomaDAO daoIdioma;
    private CatalogoDAO daoCatalogo;
    private AutorDAO daoAutor;
    
    public ControleLivro(){
        dao = new LivroDAO();
        daoFormato = new FormatoDAO();
        daoIdioma = new IdiomaDAO();
        daoCatalogo = new CatalogoDAO();
        autor = new Autor();
        daoAutor = new AutorDAO();
    }
    
    public String listar(){
        return "/privado/livro/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Livro();
        return "formulario";
    }
    
    public String salvar(){
        if (dao.salvar(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario";
        }
    }

    public String cancelar(){
        return "listar";
    }
    
    public void remover(String isbn){
        objeto = dao.localizar(isbn);
        if (dao.remover(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        }else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public String editar(String isbn){
        try{
            objeto = dao.localizar(isbn);
            return "formulario";
        }catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));
            return "listar";
        }
    }
    
    public LivroDAO getDao() {
        return dao;
    }

    public void setDao(LivroDAO dao) {
        this.dao = dao;
    }

    public Livro getObjeto() {
        return objeto;
    }

    public void setObjeto(Livro objeto) {
        this.objeto = objeto;
    }

    public FormatoDAO getDaoFormato() {
        return daoFormato;
    }

    public void setDaoFormato(FormatoDAO daoFormato) {
        this.daoFormato = daoFormato;
    }

    public IdiomaDAO getDaoIdioma() {
        return daoIdioma;
    }

    public void setDaoIdioma(IdiomaDAO daoIdioma) {
        this.daoIdioma = daoIdioma;
    }

    public CatalogoDAO getDaoCatalogo() {
        return daoCatalogo;
    }

    public void setDaoCatalogo(CatalogoDAO daoCatalogo) {
        this.daoCatalogo = daoCatalogo;
    }

    public AutorDAO getDaoAutor() {
        return daoAutor;
    }

    public void setDaoAutor(AutorDAO daoAutor) {
        this.daoAutor = daoAutor;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
}
