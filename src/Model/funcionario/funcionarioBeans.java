package Model.funcionario;

public class funcionarioBeans {
    private Long idFuncionario;
    private String nomeFuncionario;
    private int idadeFuncionario;
    private String cpfFuncionario;
    private String telefoneFuncionario;
    private double comissaoFuncionario;
    private String profissaoFuncionario;
    private double salarioFuncionario;
    public funcionarioBeans(Long idFuncionario, String nomeFuncionario, int idadeFuncionario, String cpfFuncionario, String telefoneFuncionario, double comissaoFuncionario, String profissaoFuncionario, double salarioFuncionario) {
        this.idFuncionario = idFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.idadeFuncionario = idadeFuncionario;
        this.cpfFuncionario = cpfFuncionario;
        this.telefoneFuncionario = telefoneFuncionario;
        this.comissaoFuncionario = comissaoFuncionario;
        this.profissaoFuncionario = profissaoFuncionario;
        this.salarioFuncionario = salarioFuncionario;
    }
    public funcionarioBeans(){
        super();
    }
    public Long getIdFuncionario() {
        return idFuncionario;
    }
    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
    public String getNomeFuncionario() {
        return nomeFuncionario;
    }
    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }
    public int getIdadeFuncionario() {
        return idadeFuncionario;
    }
    public void setIdadeFuncionario(int idadeFuncionario) {
        this.idadeFuncionario = idadeFuncionario;
    }
    public String getCpfFuncionario() {
        return cpfFuncionario;
    }
    public void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }
    public String getTelefoneFuncionario() {
        return telefoneFuncionario;
    }
    public void setTelefoneFuncionario(String telefoneFuncionario) {
        this.telefoneFuncionario = telefoneFuncionario;
    }
    public double getComissaoFuncionario() {
        return comissaoFuncionario;
    }
    public void setComissaoFuncionario(double comissaoFuncionario) {
        this.comissaoFuncionario = comissaoFuncionario;
    }
    public String getProfissaoFuncionario() {
        return profissaoFuncionario;
    }
    public void setProfissaoFuncionario(String profissaoFuncionario) {
        this.profissaoFuncionario = profissaoFuncionario;
    }
    public double getSalarioFuncionario() {
        return salarioFuncionario;
    }
    public void setSalarioFuncionario(double salarioFuncionario) {
        this.salarioFuncionario = salarioFuncionario;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idFuncionario == null) ? 0 : idFuncionario.hashCode());
        result = prime * result + ((nomeFuncionario == null) ? 0 : nomeFuncionario.hashCode());
        result = prime * result + idadeFuncionario;
        result = prime * result + ((cpfFuncionario == null) ? 0 : cpfFuncionario.hashCode());
        result = prime * result + ((telefoneFuncionario == null) ? 0 : telefoneFuncionario.hashCode());
        long temp;
        temp = Double.doubleToLongBits(comissaoFuncionario);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((profissaoFuncionario == null) ? 0 : profissaoFuncionario.hashCode());
        temp = Double.doubleToLongBits(salarioFuncionario);
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
        funcionarioBeans other = (funcionarioBeans) obj;
        if (idFuncionario == null) {
            if (other.idFuncionario != null)
                return false;
        } else if (!idFuncionario.equals(other.idFuncionario))
            return false;
        if (nomeFuncionario == null) {
            if (other.nomeFuncionario != null)
                return false;
        } else if (!nomeFuncionario.equals(other.nomeFuncionario))
            return false;
        if (idadeFuncionario != other.idadeFuncionario)
            return false;
        if (cpfFuncionario == null) {
            if (other.cpfFuncionario != null)
                return false;
        } else if (!cpfFuncionario.equals(other.cpfFuncionario))
            return false;
        if (telefoneFuncionario == null) {
            if (other.telefoneFuncionario != null)
                return false;
        } else if (!telefoneFuncionario.equals(other.telefoneFuncionario))
            return false;
        if (Double.doubleToLongBits(comissaoFuncionario) != Double.doubleToLongBits(other.comissaoFuncionario))
            return false;
        if (profissaoFuncionario == null) {
            if (other.profissaoFuncionario != null)
                return false;
        } else if (!profissaoFuncionario.equals(other.profissaoFuncionario))
            return false;
        if (Double.doubleToLongBits(salarioFuncionario) != Double.doubleToLongBits(other.salarioFuncionario))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "funcionarioBeans [idFuncionario=" + idFuncionario + ", nomeFuncionario=" + nomeFuncionario
                + ", idadeFuncionario=" + idadeFuncionario + ", cpfFuncionario=" + cpfFuncionario
                + ", telefoneFuncionario=" + telefoneFuncionario + ", comissaoFuncionario=" + comissaoFuncionario
                + ", profissaoFuncionario=" + profissaoFuncionario + ", salarioFuncionario=" + salarioFuncionario + "]";
    }
}
