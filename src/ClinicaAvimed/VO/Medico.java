package ClinicaAvimed.VO;
public class Medico {
   
    private int medicoCod;
    private String medNome;
    private String crm;
    private String celMensagem;
    private String cel;
    private String email;
    private String cpf;
    private String cnpj;
    private String logradouro;
    private String numLog;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public Medico(int medicoCod, String medNome, String crm, String celMensagem, String cel, String email, String cpf, String cnpj, String logradouro, String numLog, String complemento, String bairro, String cidade, String uf, String cep) {
        this.medicoCod = medicoCod;
        this.medNome = medNome;
        this.crm = crm;
        this.celMensagem = celMensagem;
        this.cel = cel;
        this.email = email;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.logradouro = logradouro;
        this.numLog = numLog;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }

    public Medico() {
    }
    
      public int getMedicoCod() {
        return medicoCod;
    }

    public void setMedicoCod(int medicoCod) {
        this.medicoCod = medicoCod;
    }

    public String getMedNome() {
        return medNome;
    }

    public void setMedNome(String medNome) {
        this.medNome = medNome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getCelMensagem() {
        return celMensagem;
    }

    public void setCelMensagem(String celMensagem) {
        this.celMensagem = celMensagem;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumLog() {
        return numLog;
    }

    public void setNumLog(String numLog) {
        this.numLog = numLog;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "\n\nMedico " + ",\nMedNome= " + medNome + "\nCrm= " + crm + "\nCelMensagem= " + celMensagem + "\nCel= " + cel + "\nEmail= " + email + "\nCpf= " + cpf;
    }

   

}
