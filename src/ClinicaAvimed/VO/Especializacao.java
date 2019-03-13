package ClinicaAvimed.VO;
public class Especializacao {
    
    private int especializacaoCod;
    private int especialidadeCod;
    private int medicoCod;
    private String espAnoEspecializacao;

    public Especializacao(int especializacaoCod, int especialidadeCod, int medicoCod, String espAnoEspecializacao) {
        this.especializacaoCod = especializacaoCod;
        this.especialidadeCod = especialidadeCod;
        this.medicoCod = medicoCod;
        this.espAnoEspecializacao = espAnoEspecializacao;
    }

    public Especializacao() {
    }

    public int getEspecializacaoCod() {
        return especializacaoCod;
    }

    public void setEspecializacaoCod(int especializacaoCod) {
        this.especializacaoCod = especializacaoCod;
    }

    public int getEspecialidadeCod() {
        return especialidadeCod;
    }

    public void setEspecialidadeCod(int especialidadeCod) {
        this.especialidadeCod = especialidadeCod;
    }

    public int getMedicoCod() {
        return medicoCod;
    }

    public void setMedicoCod(int medicoCod) {
        this.medicoCod = medicoCod;
    }

    public String getEspAnoEspecializacao() {
        return espAnoEspecializacao;
    }

    public void setEspAnoEspecializacao(String espAnoEspecializacao) {
        this.espAnoEspecializacao = espAnoEspecializacao;
    }

    @Override
    public String toString() {
        return "\n\nEspecializacao " + "\nEspecializacaoCod= " + especializacaoCod + "\nEspecialidadeCod= " + especialidadeCod + "\nMedicoCod= " + medicoCod + "\nEspAnoEspecializacao= " + espAnoEspecializacao;
    }

   
     
    
}
