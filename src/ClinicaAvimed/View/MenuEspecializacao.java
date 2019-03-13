package ClinicaAvimed.View;

import ClinicaAvimed.DAO.EspecializacaoDAO;
import ClinicaAvimed.VO.Especializacao;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MenuEspecializacao {

    private static final int CADASTRAR_ESPECIALIZACAO = 1;
    private static final int EXCLUIR_ESPECIALIZACAO = 2;
    private static final int ATUALIZAR_ESPECIALIZACAO = 3;
    private static final int EXIBIR_TODAS_ESPECIALIZACAO = 4;
    private static final int EXIBIR_ESPECIALIZACAO_POR_ID = 5;
    private static final int SAIR = 6;
    
    public void chamaMenuEspecializacao() {
        int opcao = -1;
        
        while (opcao !=6){
            try{
                String valorDigitado = JOptionPane.showInputDialog(criarMenuEspecializacao());
                if(valorDigitado != null){
                    opcao = Integer.parseInt(valorDigitado);
                }else{
                    break;
                }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "O número digitado deve ser um inteiro entre 1 e 6.");
            }
            switch(opcao){
                case 1:
                this.cadastrarEspecializacao();
                break;
            case 2:
		this.excluirEspecializacao();
		break;
            case 3:
		this.atualizarEspecializacao();
		break;
            case 4:
		this.consultarTodasEspecializacoes();
		break;
            case 5:
                try {
                    this.consultarEspecializacaoPorID();
                } catch (SQLException ex) {
                    Logger.getLogger(MenuEspecializacao.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private String criarMenuEspecializacao() {
       String mensagem = "Menu Especialização";
       mensagem += "\n1 - Cadastrar Especialização.";
       mensagem += "\n2 - Excluir Especialização.";
       mensagem += "\n3 - Atualizar Especialização.";   
       mensagem += "\n4 - Exibir todas as Especializações.";
       mensagem += "\n5 - Exibir Especialização por ID.";
       mensagem += "\n6 - Sair.";
       return mensagem;
    }
    //int especializacaoCod, int especialidadeCod, int medicoCod, String espAnoEspecializacao
    private boolean cadastrarEspecializacao() {
        Especializacao especializacao = new Especializacao();
        EspecializacaoDAO inserirEspecializacao = new EspecializacaoDAO();
        
        String especialidadeCod = JOptionPane.showInputDialog(null, "Digite o ID da especialidade.");
        if(especialidadeCod == null) return false;
        especializacao.setEspecializacaoCod(Integer.parseInt(especialidadeCod));
        
        String medicoCod = JOptionPane.showInputDialog(null, "Digite o ID do médico.");
        if(medicoCod == null) return false;
        especializacao.setMedicoCod(Integer.parseInt(medicoCod));
        
        String espAnoEspecializacao = JOptionPane.showInputDialog(null, "Digiteo ano da especialização.");
        if(espAnoEspecializacao == null) return false;
        especializacao.setEspAnoEspecializacao(espAnoEspecializacao);
        
        inserirEspecializacao.inserir(especializacao);
         JOptionPane.showMessageDialog(null,"Especializacao cadastrada com sucesso!");
         return true;
    }

    private void excluirEspecializacao() {
        Especializacao especializacao = new Especializacao();
        EspecializacaoDAO excluirEspecializacao = new EspecializacaoDAO();
        
        especializacao.setEspecializacaoCod(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID da especializacao para excluir")));
        if(excluirEspecializacao.delete(especializacao.getEspecializacaoCod())){
        JOptionPane.showMessageDialog(null,"Especializacao deleteada com sucesso!");

        }else{
          JOptionPane.showMessageDialog(null, "ID não existe. Não foi possível excluir a especializacao.");  
        }
    }

    private boolean atualizarEspecializacao() {
        Especializacao especializacao = new Especializacao();
        EspecializacaoDAO inserirEsp = new EspecializacaoDAO();
        
        especializacao.setEspecializacaoCod(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID da Especializa para atualizar.")));
        if (inserirEsp.existeRegistroPorId(especializacao.getEspecializacaoCod())){
   
            String especialidadeCod = JOptionPane.showInputDialog(null, "Digite o ID da especialidade.");
            if(especialidadeCod == null) return false;
            especializacao.setEspecializacaoCod(Integer.parseInt(especialidadeCod));
            
            String medicoCod = JOptionPane.showInputDialog(null, "Digite o ID do médico.");
            if(medicoCod == null) return false;
            especializacao.setMedicoCod(Integer.parseInt(medicoCod));
            
            String espAnoEspecializacao = JOptionPane.showInputDialog(null, "Digiteo ano da especialização.");
            if(espAnoEspecializacao == null) return false;
            especializacao.setEspAnoEspecializacao(espAnoEspecializacao);
        }
            if(inserirEsp.atualizar(especializacao)){
                JOptionPane.showMessageDialog(null, "Especializacao atualizada com sucesso!");
            }else{
                JOptionPane.showMessageDialog(null, "Especializacao não existe para atualizar!");
            }
            return true;
        }

    private void consultarTodasEspecializacoes() {
        EspecializacaoDAO consultarEspecializacaos = new EspecializacaoDAO();
        
        List<Especializacao> especializacaos = consultarEspecializacaos.listarTodosEspecializacoes();
        JOptionPane.showMessageDialog(null, especializacaos.toString());
    }

    private void consultarEspecializacaoPorID() throws SQLException {
        EspecializacaoDAO consultarEspecializacaos = new EspecializacaoDAO();
        int espCod = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite Id da especializacao para consultar"));
        
        Especializacao especializacaoConsultado = consultarEspecializacaos.consultarEspecializacaoPorID(espCod);
            
        try{
        if (especializacaoConsultado != null){
            JOptionPane.showMessageDialog(null, especializacaoConsultado);
        }else {
            JOptionPane.showMessageDialog(null,"Especializacao não encontrada. Tente novamente!");
        }
        }catch (NumberFormatException  e){
                System.out.println(e.getMessage());
        } 
    }
}
