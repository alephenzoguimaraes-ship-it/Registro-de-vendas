package Model.cliente;

public class clienteBeans {
    private Long idCliente;
    private String nomeCliente;
    private int idadeCliente;
    private String cpfCnpjCliente;
    private Character fisicaJuridicaCliente;
    private String telefoneCliente;
    
    public clienteBeans(Long idCliente, String nomeCliente, int idadeCliente, String cpfCnpjCliente, Character fisicaJuridicaCliente, String telefoneCliente) {
            this.idCliente = idCliente;
            this.nomeCliente = nomeCliente;
            this.idadeCliente = idadeCliente;
            this.cpfCnpjCliente = cpfCnpjCliente;
            this.fisicaJuridicaCliente = fisicaJuridicaCliente;
            this.telefoneCliente = telefoneCliente;
    }
    public clienteBeans() {
        super();
    }
    public Long getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
    public String getNomeCliente() {
        return nomeCliente;
    }
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    public int getIdadeCliente() {
        return idadeCliente;
    }
    public void setIdadeCliente(int idadeCliente) {
        this.idadeCliente = idadeCliente;
    }
    public String getCpfCnpjCliente() {
        return cpfCnpjCliente;
    }
    public void setCpfCnpjCliente(String cpfCnpjCliente) {
        this.cpfCnpjCliente = cpfCnpjCliente;
    }
    public Character getFisicaJuridicaCliente() {
        return fisicaJuridicaCliente;
    }
    public void setFisicaJuridicaCliente(Character fisicaJuridicaCliente) {
        this.fisicaJuridicaCliente = fisicaJuridicaCliente;
    }
    public String getTelefoneCliente() {
        return telefoneCliente;
    }
    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
        result = prime * result + ((nomeCliente == null) ? 0 : nomeCliente.hashCode());
        result = prime * result + idadeCliente;
        result = prime * result + ((cpfCnpjCliente == null) ? 0 : cpfCnpjCliente.hashCode());
        result = prime * result + ((fisicaJuridicaCliente == null) ? 0 : fisicaJuridicaCliente.hashCode());
        result = prime * result + ((telefoneCliente == null) ? 0 : telefoneCliente.hashCode());
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
        clienteBeans other = (clienteBeans) obj;
        if (idCliente == null) {
            if (other.idCliente != null)
                return false;
        } else if (!idCliente.equals(other.idCliente))
            return false;
        if (nomeCliente == null) {
            if (other.nomeCliente != null)
                return false;
        } else if (!nomeCliente.equals(other.nomeCliente))
            return false;
        if (idadeCliente != other.idadeCliente)
            return false;
        if (cpfCnpjCliente == null) {
            if (other.cpfCnpjCliente != null)
                return false;
        } else if (!cpfCnpjCliente.equals(other.cpfCnpjCliente))
            return false;
        if (fisicaJuridicaCliente == null) {
            if (other.fisicaJuridicaCliente != null)
                return false;
        } else if (!fisicaJuridicaCliente.equals(other.fisicaJuridicaCliente))
            return false;
        if (telefoneCliente == null) {
            if (other.telefoneCliente != null)
                return false;
        } else if (!telefoneCliente.equals(other.telefoneCliente))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "clienteBeans [idCliente=" + idCliente + ", nomeCliente=" + nomeCliente + ", idadeCliente="
                + idadeCliente + ", cpfCnpjCliente=" + cpfCnpjCliente + ", fisicaJuridicaCliente="
                + fisicaJuridicaCliente + ", telefoneCliente=" + telefoneCliente + "]";
    }
}
