package model;

public class Presente {
    private String descricao;
    private String categoria;
    private float preco;

    public Presente(String descricao, String categoria, float preco) {
        this.descricao = descricao;
        this.categoria = categoria;
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return descricao + ", R$" + preco ;
    }
}
