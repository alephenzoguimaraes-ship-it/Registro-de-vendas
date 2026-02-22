package Model.vendas;

import java.time.LocalDate;

public class vendasBeans {
    private Long idPedidoVendas;
    private Long idClienteVendas;
    private String nomeClienteVendas;
    private Long idProdutoVendas;
    private String nomeProdutoVendas;
    private double valorProdutoVendas;
    private Long qtdeCompradaVendas;
    private Long idFuncionarioVendas;
    private String nomeFuncionarioVendas;
    private boolean statusPagamentoVendas;
    private LocalDate dataCompraVendas;
    public vendasBeans(Long idPedidoVendas, Long idClienteVendas, String nomeClienteVendas, Long idProdutoVendas,
            String nomeProdutoVendas, double valorProdutoVendas, Long qtdeCompradaVendas, Long idFuncionarioVendas,
            String nomeFuncionarioVendas, boolean statusPagamentoVendas, LocalDate dataCompraVendas) {
        this.idPedidoVendas = idPedidoVendas;
        this.idClienteVendas = idClienteVendas;
        this.nomeClienteVendas = nomeClienteVendas;
        this.idProdutoVendas = idProdutoVendas;
        this.nomeProdutoVendas = nomeProdutoVendas;
        this.valorProdutoVendas = valorProdutoVendas;
        this.qtdeCompradaVendas = qtdeCompradaVendas;
        this.idFuncionarioVendas = idFuncionarioVendas;
        this.nomeFuncionarioVendas = nomeFuncionarioVendas;
        this.statusPagamentoVendas = statusPagamentoVendas;
        this.dataCompraVendas = dataCompraVendas;
    }
    public vendasBeans(){
        super();
    }
    public Long getIdPedidoVendas() {
        return idPedidoVendas;
    }
    public void setIdPedidoVendas(Long idPedidoVendas) {
        this.idPedidoVendas = idPedidoVendas;
    }
    public Long getIdClienteVendas() {
        return idClienteVendas;
    }
    public void setIdClienteVendas(Long idClienteVendas) {
        this.idClienteVendas = idClienteVendas;
    }
    public String getNomeClienteVendas() {
        return nomeClienteVendas;
    }
    public void setNomeClienteVendas(String nomeClienteVendas) {
        this.nomeClienteVendas = nomeClienteVendas;
    }
    public Long getIdProdutoVendas() {
        return idProdutoVendas;
    }
    public void setIdProdutoVendas(Long idProdutoVendas) {
        this.idProdutoVendas = idProdutoVendas;
    }
    public String getNomeProdutoVendas() {
        return nomeProdutoVendas;
    }
    public void setNomeProdutoVendas(String nomeProdutoVendas) {
        this.nomeProdutoVendas = nomeProdutoVendas;
    }
    public double getValorProdutoVendas() {
        return valorProdutoVendas;
    }
    public void setValorProdutoVendas(double valorProdutoVendas) {
        this.valorProdutoVendas = valorProdutoVendas;
    }
    public Long getQtdeCompradaVendas() {
        return qtdeCompradaVendas;
    }
    public void setQtdeCompradaVendas(Long qtdeCompradaVendas) {
        this.qtdeCompradaVendas = qtdeCompradaVendas;
    }
    public Long getIdFuncionarioVendas() {
        return idFuncionarioVendas;
    }
    public void setIdFuncionarioVendas(Long idFuncionarioVendas) {
        this.idFuncionarioVendas = idFuncionarioVendas;
    }
    public String getNomeFuncionarioVendas() {
        return nomeFuncionarioVendas;
    }
    public void setNomeFuncionarioVendas(String nomeFuncionarioVendas) {
        this.nomeFuncionarioVendas = nomeFuncionarioVendas;
    }
    public boolean isStatusPagamentoVendas() {
        return statusPagamentoVendas;
    }
    public void setStatusPagamentoVendas(boolean statusPagamentoVendas) {
        this.statusPagamentoVendas = statusPagamentoVendas;
    }
    public LocalDate getDataCompraVendas() {
        return dataCompraVendas;
    }
    public void setDataCompraVendas(LocalDate dataCompraVendas) {
        this.dataCompraVendas = dataCompraVendas;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPedidoVendas == null) ? 0 : idPedidoVendas.hashCode());
        result = prime * result + ((idClienteVendas == null) ? 0 : idClienteVendas.hashCode());
        result = prime * result + ((nomeClienteVendas == null) ? 0 : nomeClienteVendas.hashCode());
        result = prime * result + ((idProdutoVendas == null) ? 0 : idProdutoVendas.hashCode());
        result = prime * result + ((nomeProdutoVendas == null) ? 0 : nomeProdutoVendas.hashCode());
        long temp;
        temp = Double.doubleToLongBits(valorProdutoVendas);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((qtdeCompradaVendas == null) ? 0 : qtdeCompradaVendas.hashCode());
        result = prime * result + ((idFuncionarioVendas == null) ? 0 : idFuncionarioVendas.hashCode());
        result = prime * result + ((nomeFuncionarioVendas == null) ? 0 : nomeFuncionarioVendas.hashCode());
        result = prime * result + (statusPagamentoVendas ? 1231 : 1237);
        result = prime * result + ((dataCompraVendas == null) ? 0 : dataCompraVendas.hashCode());
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
        vendasBeans other = (vendasBeans) obj;
        if (idPedidoVendas == null) {
            if (other.idPedidoVendas != null)
                return false;
        } else if (!idPedidoVendas.equals(other.idPedidoVendas))
            return false;
        if (idClienteVendas == null) {
            if (other.idClienteVendas != null)
                return false;
        } else if (!idClienteVendas.equals(other.idClienteVendas))
            return false;
        if (nomeClienteVendas == null) {
            if (other.nomeClienteVendas != null)
                return false;
        } else if (!nomeClienteVendas.equals(other.nomeClienteVendas))
            return false;
        if (idProdutoVendas == null) {
            if (other.idProdutoVendas != null)
                return false;
        } else if (!idProdutoVendas.equals(other.idProdutoVendas))
            return false;
        if (nomeProdutoVendas == null) {
            if (other.nomeProdutoVendas != null)
                return false;
        } else if (!nomeProdutoVendas.equals(other.nomeProdutoVendas))
            return false;
        if (Double.doubleToLongBits(valorProdutoVendas) != Double.doubleToLongBits(other.valorProdutoVendas))
            return false;
        if (qtdeCompradaVendas == null) {
            if (other.qtdeCompradaVendas != null)
                return false;
        } else if (!qtdeCompradaVendas.equals(other.qtdeCompradaVendas))
            return false;
        if (idFuncionarioVendas == null) {
            if (other.idFuncionarioVendas != null)
                return false;
        } else if (!idFuncionarioVendas.equals(other.idFuncionarioVendas))
            return false;
        if (nomeFuncionarioVendas == null) {
            if (other.nomeFuncionarioVendas != null)
                return false;
        } else if (!nomeFuncionarioVendas.equals(other.nomeFuncionarioVendas))
            return false;
        if (statusPagamentoVendas != other.statusPagamentoVendas)
            return false;
        if (dataCompraVendas == null) {
            if (other.dataCompraVendas != null)
                return false;
        } else if (!dataCompraVendas.equals(other.dataCompraVendas))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "vendasBeans [idPedidoVendas=" + idPedidoVendas + ", idClienteVendas=" + idClienteVendas
                + ", nomeClienteVendas=" + nomeClienteVendas + ", idProdutoVendas=" + idProdutoVendas
                + ", nomeProdutoVendas=" + nomeProdutoVendas + ", valorProdutoVendas=" + valorProdutoVendas
                + ", qtdeCompradaVendas=" + qtdeCompradaVendas + ", idFuncionarioVendas=" + idFuncionarioVendas
                + ", nomeFuncionarioVendas=" + nomeFuncionarioVendas + ", statusPagamentoVendas="
                + statusPagamentoVendas + ", dataCompraVendas=" + dataCompraVendas + "]";
    }  
}
