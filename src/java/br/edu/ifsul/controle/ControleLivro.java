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
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "controleLivro")
@ViewScoped
public class ControleLivro implements Serializable{
    
        
    private LivroDAO<Livro> dao;
    private Livro objeto;
    private Boolean editando;
    private AutorDAO<Autor> daoAutor;
    private Autor autor;
    private FormatoDAO daoFormato;
    private IdiomaDAO daoIdioma;
    private CatalogoDAO daoCatalogo;
    private Boolean editandoAutor;
    private Boolean novoAutor;
    
    public ControleLivro(){
        dao = new LivroDAO<>();
        daoAutor = new AutorDAO<>();
        daoFormato = new FormatoDAO();
        daoIdioma = new IdiomaDAO();
        daoCatalogo = new CatalogoDAO();
        editando = false;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
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
    
    public String listar(){
        return "/privado/livro/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Livro();
        editando = true;
    }
    
    public String salvar(){
        if (getDao().persist(getObjeto())){
            Util.mensagemInformacao(getDao().getMensagem());
            return "listar";
        } else {
            Util.mensagemErro(getDao().getMensagem());
            return "formulario";
        }
    }

    public String cancelar(){
        return "listar";
    }
    
    public void excluir(String isbn){
        objeto = dao.localizarLivro(isbn);
        if (dao.remover(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        }else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public void novoAutor(){
        editandoAutor = true;
    }
    
    public void salvarAutor(){
        if (!objeto.getAutores_do_livro().contains(autor)) {
            objeto.getAutores_do_livro().add(autor);
            Util.mensagemInformacao("Autor adicionado com sucesso!");
        } else {
            Util.mensagemErro("O Autor já está cadastrado nesse livro!");
        }
        editandoAutor = false;
    }
    
    public void removerAutor(Autor obj) {
        objeto.getAutores_do_livro().remove(obj);
        Util.mensagemInformacao("Autor removido com sucesso!");
    }
    
    public void adicionarAutor(){
        if (autor != null){
            if(!objeto.getAutores_do_livro().contains(autor)){
                objeto.getAutores_do_livro().add(autor);
                Util.mensagemInformacao("Autor adicionado com sucesso!");
            } else {
                Util.mensagemErro("Autor já existe na lista de autores!");
            }
        }
    }
    
    public void removerAutor(int index){
        objeto.getAutores_do_livro().remove(index);
        Util.mensagemInformacao("Autor removido com sucesso!");
    }
    
    public String alterar(String isbn){
        try{
            objeto = dao.localizarLivro(isbn);
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
    
}
