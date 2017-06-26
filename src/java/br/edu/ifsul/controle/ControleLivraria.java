package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CatalogoDAO;
import br.edu.ifsul.dao.LivrariaDAO;
import br.edu.ifsul.modelo.Catalogo;
import br.edu.ifsul.modelo.Livraria;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "controleLivraria")
@ViewScoped
//@RequestScoped
public class ControleLivraria implements Serializable {
    
    private LivrariaDAO<Livraria> dao;
    private Livraria objeto;
    private CatalogoDAO<Catalogo> daoCatalogo;
    private Catalogo catalogo;
    private Boolean editando;
    private Boolean editandoCatalogo;
    private Boolean novoCatalogo;
    
    public ControleLivraria() {
        dao = new LivrariaDAO<>();
        daoCatalogo = new CatalogoDAO<>();
        editando = false;
    }
    
    public String listar() {
        editando = false;
        return "/privado/livraria/listar?faces-redirect=true";
    }

    
    public void relatorioLivraria(Integer id){
        List<Livraria> lista = new ArrayList<>();
        objeto = dao.localizar(id);
        lista.add(objeto);
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioLivrariasJavaBeans", parametros,lista);
    }
    
    public void relatorio(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioLivrariasJavaBeans", parametros,dao.getListaTodos());
    }
    
    public void novo() {
        objeto = new Livraria();
        editando = true;
        editandoCatalogo = false;
    }
    
    public void alterar(int id){
        try{
            objeto = dao.localizar(id);
        }catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));
        }
    }
    
    public void excluir(int id){
        objeto = dao.localizar(id);
        if (dao.remover(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        }else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            editando = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: "
                    + Util.getMensagemErro(e));
        }
    }
    
    public void novoCatalogo(){
        catalogo = new Catalogo();
        novoCatalogo = true;
    }
    
    public void alterarCatalogo(int index){
       catalogo = objeto.getCatalogos().get(index);
       novoCatalogo = false;
       editandoCatalogo = true;
    }
    
    public void salvarCatalogo(){
        if (novoCatalogo){
            objeto.adicionarCatalogo(catalogo);
        }
        Util.mensagemInformacao("Catalogo adicionado com sucesso");
    }
    
    public void removerCatalogo(Catalogo obj) {
        objeto.getCatalogos().remove(obj);
        Util.mensagemInformacao("Catalogo removido com sucesso!");
    }
    
    public LivrariaDAO<Livraria> getDao() {
        return dao;
    }
    
    public void setDao(LivrariaDAO<Livraria> dao) {
        this.dao = dao;
    }
    
    public Livraria getObjeto() {
        return objeto;
    }
    
    public void setObjeto(Livraria objeto) {
        this.objeto = objeto;
    }
    
    public CatalogoDAO<Catalogo> getDaoCatalogo() {
        return daoCatalogo;
    }
    
    public void setDaoCatalogo(CatalogoDAO<Catalogo> daoCatalogo) {
        this.daoCatalogo = daoCatalogo;
    }
    
    public Boolean getEditando() {
        return editando;
    }
    
    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
    
    public Boolean getEditandoCatalogo() {
        return editandoCatalogo;
    }
    
    public void setEditandoCatalogo(Boolean editandoCatalogo) {
        this.editandoCatalogo = editandoCatalogo;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo cheque) {
        this.catalogo = cheque;
    }

    public Boolean getNovoCatalogo() {
        return novoCatalogo;
    }

    public void setNovoCatalogo(Boolean novoCatalogo) {
        this.novoCatalogo = novoCatalogo;
    }
    
}
