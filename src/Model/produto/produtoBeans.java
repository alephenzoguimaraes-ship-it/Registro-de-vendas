package Model.produto;

public class produtoBeans {
    private Long idProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private Long qtdeProduto;
    private double valorProduto;
    public produtoBeans(Long idProduto, String nomeProduto, String descricaoProduto, Long qtdeProduto, double valorProduto) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.qtdeProduto = qtdeProduto;
        this.valorProduto = valorProduto;
    }
    public produtoBeans() {
    }
    public Long getIdProduto() {
        return idProduto;
    }
    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }
    public String getNomeProduto() {
        return nomeProduto;
    }
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    public String getDescricaoProduto() {
        return descricaoProduto;
    }
    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }
    public Long getQtdeProduto() {
        return qtdeProduto;
    }
    public void setQtdeProduto(Long qtdeProduto) {
        this.qtdeProduto = qtdeProduto;
    }
    public double getValorProduto() {
        return valorProduto;
    }
    public void setValorProduto(double valorProduto) {
        this.valorProduto = valorProduto;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idProduto == null) ? 0 : idProduto.hashCode());
        result = prime * result + ((nomeProduto == null) ? 0 : nomeProduto.hashCode());
        result = prime * result + ((descricaoProduto == null) ? 0 : descricaoProduto.hashCode());
        result = prime * result + ((qtdeProduto == null) ? 0 : qtdeProduto.hashCode());
        long temp;
        temp = Double.doubleToLongBits(valorProduto);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        produtoBeans other = (produtoBeans) obj;
        if (idProduto == null) {
            if (other.idProduto != null)
                return false;
        } else if (!idProduto.equals(other.idProduto))
            return false;
        if (nomeProduto == null) {
            if (other.nomeProduto != null)
                return false;
        } else if (!nomeProduto.equals(other.nomeProduto))
            return false;
        if (descricaoProduto == null) {
            if (other.descricaoProduto != null)
                return false;
        } else if (!descricaoProduto.equals(other.descricaoProduto))
            return false;
        if (qtdeProduto == null) {
            if (other.qtdeProduto != null)
                return false;
        } else if (!qtdeProduto.equals(other.qtdeProduto))
            return false;
        if (Double.doubleToLongBits(valorProduto) != Double.doubleToLongBits(other.valorProduto))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "produtoBeans [idProduto=" + idProduto + ", nomeProduto=" + nomeProduto + ", descricaoProduto="
                + descricaoProduto + ", qtdeProduto=" + qtdeProduto + ", valorProduto=" + valorProduto + "]";
    }
}
