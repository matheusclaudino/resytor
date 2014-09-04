package model;

/**
 *
 * @author LuizVenturote https://github.com/luizventurote
 */
public class Documento {
    
    private String conteudo;
    private double similaridade;

    public Documento(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public double getSimilaridade() {
        return similaridade;
    }

    public void setSimilaridade(double similaridade) {
        this.similaridade = similaridade;
    }
    
}
