package ClinicaAvimed.View;

import ClinicaAvimed.DAO.EspecialidadeDAO;
import ClinicaAvimed.VO.Especialidade;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MenuEspecialidade {
    
    private static final int CADASTRAR_ESPECIALIDADE = 1;
    private static final int EXCLUIR_ESPECIALIDADE = 2;
    private static final int ATUALIZAR_ESPECIALIDADE = 3;
    private static final int EXIBIR_TODAS_ESPECIALIDADES = 4;
    private static final int EXIBIR_ESPECIALIDADE_POR_ID = 5;
    private static final int SAIR = 6;
    
    public void chamaMenuEspecialidade() {
        int opcao = -1;
        
        while (opcao !=6){
            try{
                String valorDigitado = JOptionPane.showInputDialog(criarMenuEspeciadidade());
                if(valorDigitado != null){
                    opcao = Integer.parseInt(valorDigitado);
                }else{
                    break;
                }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "O número digitado deve ser um inteiro entre 1 e 6");
            }
        switch(opcao){
            case 1:
                this.cadastrarEspecialidade();
                break;
            case 2:
		this.excluirEspecialidade();
		break;
            case 3:
		this.atualizarEspecialidade();
		break;
            case 4:
		this.consultarTodasEspecialidades();
		break;
            case 5:
            {
                try {
                    this.consultarEspecialidadePorID();
                } catch (SQLException ex) {
                    Logger.getLogger(MenuEspecialidade.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
		break;
            case 6: 
                int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza?");
                    if(resposta == 0){
                        JOptionPane.showMessageDialog(null, "Você foi desconectado");
                        break;
                    }
            default:
                JOptionPane.showMessageDialog(null, "Opção Inválida!");
        }  
        }
    }
    private String criarMenuEspeciadidade() {
        String mensagem = "Menu Especialidade.";
        mensagem += "\n1 - Cadastrar Especialidade.";
        mensagem += "\n2 - Excluir Especialidade.";
        mensagem += "\n3 - Atualizar Especialidade.";   
        mensagem += "\n4 - Exibir todas as Especialidades.";
        mensagem += "\n5 - Exibir Especialidade por ID.";
        mensagem += "\n6 - Sair.";
                
        return mensagem;
    }

    private boolean cadastrarEspecialidade() {
        Especialidade especialidade = new Especialidade();
        EspecialidadeDAO inserirEspecialidade = new EspecialidadeDAO();
        
        String nome = JOptionPane.showInputDialog(null, "Digite o nome da especialidade.");
        if (nome==null) return false;     
            especialidade.setEspNome(nome);
        
        String espInstituicao = JOptionPane.showInputDialog(null, "Digite a instituição.");
        if (espInstituicao==null) return false;     
            especialidade.setEspInstituicao(espInstituicao);
              
        inserirEspecialidade.inserir(especialidade);
         JOptionPane.showMessageDialog(null,"Especialidade cadastrado com sucesso!");
         
         return true;
        }
   
    private void excluirEspecialidade() {
        Especialidade especialidade = new Especialidade();
        EspecialidadeDAO excluirEspecialidade = new EspecialidadeDAO();
        
        especialidade.setEspecialidadeCod(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID da especialidade para excluir")));
        if(excluirEspecialidade.delete(especialidade.getEspecialidadeCod())){
        JOptionPane.showMessageDialog(null,"Especialidade deleteada com sucesso!");

        }else{
          JOptionPane.showMessageDialog(null, "ID não existe. Não foi possível excluir a especialidade.");  
        }
    }

    private boolean atualizarEspecialidade() {
        Especialidade especialidade = new Especialidade();
        EspecialidadeDAO inserirEspecialidade = new EspecialidadeDAO();
        
        especialidade.setEspecialidadeCod(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID da Especialidade para atualizar.")));
        if (inserirEspecialidade.existeRegistroPorId(especialidade.getEspecialidadeCod())){
        
        String nome = JOptionPane.showInputDialog(null, "Digite o nome da especialidade.");
        if (nome==null) return false;     
            especialidade.setEspNome(nome);
        
        String espInstituicao = JOptionPane.showInputDialog(null, "Digite a instituição.");
        if (espInstituicao==null) return false;     
            especialidade.setEspInstituicao(espInstituicao);
        }
        if(inserirEspecialidade.atualizar(especialidade)){
            JOptionPane.showMessageDialog(null, "Especialidade atualizada com sucesso!");	
        }else{
            JOptionPane.showMessageDialog(null,"Especialidade não cadastrado. Tente novamente!");
        }
            return true;
        }
    

    private void consultarTodasEspecialidades() {
         EspecialidadeDAO consultarEspecialidades = new EspecialidadeDAO();
        
        List<Especialidade> especialidades = consultarEspecialidades.listarTodosEspecialidades();
        JOptionPane.showMessageDialog(null, especialidades.toString());
    }

    private void consultarEspecialidadePorID() throws SQLException {
         EspecialidadeDAO consultarEspecialidades = new EspecialidadeDAO();
         int espCod = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite Id da especialidade para consultar"));
         
        Especialidade especialidadeConsultado = consultarEspecialidades.consultarEspecialidadePorID(espCod);
            
        try{
        if (especialidadeConsultado != null){
            JOptionPane.showMessageDialog(null, especialidadeConsultado);
        }else {
            JOptionPane.showMessageDialog(null,"Especialidade não encontrada. Tente novamente!");
        }
        }catch (NumberFormatException  e){
                System.out.println(e.getMessage());
        } 
    }
}
