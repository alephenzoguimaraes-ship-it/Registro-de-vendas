package Model.vendas;

import java.time.LocalDate;

public class pedidosBeans {
    private long idPedido;
    private long idCliente;
    private long idProduto;
    private long idFuncionario;
    private long qtdeComprada;
    private boolean statusPagamentos;
    private LocalDate dataCompra;
    public pedidosBeans(long idPedido, long idCliente, long idProduto, long idFuncionario, long qtdeComprada,
        boolean statusPagamentos, LocalDate dataCompra) {
            this.idPedido = idPedido;
            this.idCliente = idCliente;
            this.idProduto = idProduto;
            this.idFuncionario = idFuncionario;
            this.qtdeComprada = qtdeComprada;
            this.statusPagamentos = statusPagamentos;
            this.dataCompra = dataCompra;
    }
    public pedidosBeans() {
    }
    public long getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }
    public long getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }
    public long getIdProduto() {
        return idProduto;
    }
    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }
    public long getIdFuncionario() {
        return idFuncionario;
    }
    public void setIdFuncionario(long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
    public long getQtdeComprada() {
        return qtdeComprada;
    }
    public void setQtdeComprada(long qtdeComprada) {
        this.qtdeComprada = qtdeComprada;
    }
    public boolean isStatusPagamentos() {
        return statusPagamentos;
    }
    public void setStatusPagamentos(boolean statusPagamentos) {
        this.statusPagamentos = statusPagamentos;
    }
    public LocalDate getDataCompra() {
        return dataCompra;
    }
    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (idPedido ^ (idPedido >>> 32));
        result = prime * result + (int) (idCliente ^ (idCliente >>> 32));
        result = prime * result + (int) (idProduto ^ (idProduto >>> 32));
        result = prime * result + (int) (idFuncionario ^ (idFuncionario >>> 32));
        result = prime * result + (int) (qtdeComprada ^ (qtdeComprada >>> 32));
        result = prime * result + (statusPagamentos ? 1231 : 1237);
        result = prime * result + ((dataCompra == null) ? 0 : dataCompra.hashCode());
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
        pedidosBeans other = (pedidosBeans) obj;
        if (idPedido != other.idPedido)
            return false;
        if (idCliente != other.idCliente)
            return false;
        if (idProduto != other.idProduto)
            return false;
        if (idFuncionario != other.idFuncionario)
            return false;
        if (qtdeComprada != other.qtdeComprada)
            return false;
        if (statusPagamentos != other.statusPagamentos)
            return false;
        if (dataCompra == null) {
            if (other.dataCompra != null)
                return false;
        } else if (!dataCompra.equals(other.dataCompra))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "pedidosBeans [idPedido=" + idPedido + ", idCliente=" + idCliente + ", idProduto=" + idProduto
                + ", idFuncionario=" + idFuncionario + ", qtdeComprada=" + qtdeComprada + ", statusPagamentos="
                + statusPagamentos + ", dataCompra=" + dataCompra + "]";
    }
}
