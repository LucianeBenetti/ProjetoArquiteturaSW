package ClinicaAvimed.View;

import ClinicaAvimed.DAO.MedicoDAO;
import ClinicaAvimed.VO.Medico;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MenuMedico {
    
    private static final int CADASTRAR_MEDICO = 1;
    private static final int EXCLUIR_MEDICO = 2;
    private static final int ATUALIZAR_MEDICO = 3;
    private static final int EXIBIR_TODOS_MEDICOS = 4;
    private static final int EXIBIR_MEDICO_POR_CRM = 5;
    private static final int SAIR = 6;

    public void chamaMenuMedico() {
        
        int opcao = -1;
        
        while(opcao != SAIR){
            
            try{
                String valorDigitado = (JOptionPane.showInputDialog(chamaOpcoesMenuMedico()));
               if(valorDigitado != null){
                 opcao = Integer.parseInt(valorDigitado); 
               }else{
                   break;
               }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Por favor, digite um número inteiro entre 1 e 6.");
            }
            switch (opcao){
                case CADASTRAR_MEDICO:
                    this.cadastrarMedico();
                    break;
                case EXCLUIR_MEDICO:
                    this.excluirMedico();
                    break;
                case ATUALIZAR_MEDICO:
                    this.atualizarMedico();
                    break;
                case EXIBIR_TODOS_MEDICOS:
                    this.exibirTodosMedicos();
                    break;
                case EXIBIR_MEDICO_POR_CRM:
            {
                try {
                    this.exibirMedicoCRM();
                } catch (SQLException ex) {
                    Logger.getLogger(MenuMedico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                case SAIR:
                    int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?");
                    if(resposta == 0){
                        JOptionPane.showMessageDialog(null, "Você foi desconectado do Menu Médico!!");
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Por favor, digite a opção");
            }
        }
    }

    private String chamaOpcoesMenuMedico() {
        String mensagem = "Menu Médico.";
        mensagem += "\n1 - Cadastrar Médico.";
        mensagem += "\n2 - Excluir Médico.";
        mensagem += "\n3 - Atualizar Médico.";   
        mensagem += "\n4 - Exibir todos os médicos.";
        mensagem += "\n5 - Exibir médico por CRM.";
        mensagem += "\n6 - Sair.";
                       
        return mensagem;
    }

    private boolean cadastrarMedico() {
        Medico medico = new Medico();
        MedicoDAO inserirMedico = new MedicoDAO();
        
        medico.setCpf(JOptionPane.showInputDialog(null, "Digite o CPF do médico para cadastrar."));
        if (inserirMedico.existeRegistroPorCpf(medico.getCpf())){
            JOptionPane.showMessageDialog(null, "Médico já cadastrado. Tente novamente!");
        }      
        String nome= JOptionPane.showInputDialog(null, "Digite o nome do médico.");
        if(nome==null)return false;
            medico.setMedNome(nome);
        
        String crm = JOptionPane.showInputDialog(null, "Digite o CRM do médico.");
        if (crm == null) return false;
        medico.setCrm(crm);
        
        String celMensagem = JOptionPane.showInputDialog(null, "Digite o Celular para mensagem do médico.");
        if (celMensagem ==null) return false;
        medico.setCelMensagem(celMensagem);
        
        String cel = JOptionPane.showInputDialog(null, "Digite o Celular do médico.");
        if (cel ==null) return false;
        medico.setCel(cel);
        
        String email = JOptionPane.showInputDialog(null, "Digite o e-mail do médico.");
        if (email ==null) return false;
        medico.setEmail(email);
        
        String cpf = JOptionPane.showInputDialog(null, "Digite o CPF do médico para cadastrar.");
        if (cpf==null) return false;     
            medico.setCpf(cpf);
            
        String cnpj = JOptionPane.showInputDialog(null, "Digite o CNPJ do médico para cadastrar.");
        if (cnpj==null) return false;     
            medico.setCnpj(cnpj);
               
        String logradouro= JOptionPane.showInputDialog(null, "Digite o Logradouro da residência.");
        if (logradouro==null) return false;     
            medico.setLogradouro(logradouro);
        
        String numLog = JOptionPane.showInputDialog(null, "Digite o número da residência.");
        if (numLog==null) return false;     
            medico.setNumLog(numLog);
        
        String complemento = JOptionPane.showInputDialog(null, "Digite o complemento do endereço do médico.");
        if (complemento==null) return false;     
            medico.setComplemento(complemento);
        
        String bairro = JOptionPane.showInputDialog(null, "Digite o Bairro.");
        if (bairro==null) return false;     
            medico.setBairro(bairro);
        
        String cidade = JOptionPane.showInputDialog(null, "Digite o nome do cidade.");
        if (cidade==null) return false;     
            medico.setCidade(cidade);
        
        String uf = JOptionPane.showInputDialog(null, "Digite o Estado.");
        if (uf==null) return false;     
            medico.setUf(uf);
        
        String cep = JOptionPane.showInputDialog(null, "Digite CEP da residência.");
        if (cep==null) return false;     
            medico.setCep (cep);
        
        inserirMedico.inserir(medico);
         JOptionPane.showMessageDialog(null,"Médico cadastrado com sucesso!");
         
     return true;
}
    private void excluirMedico() {
        Medico medico = new Medico();
        MedicoDAO excluirMedico = new MedicoDAO();
        
        medico.setCpf(JOptionPane.showInputDialog(null, "Digite o CPF do médico para excluir"));
        if (excluirMedico.existeRegistroPorCpf(medico.getCpf())){
          excluirMedico.delete(medico.getCpf());
          JOptionPane.showMessageDialog(null,"Medico deleteado com sucesso!");

        }else{
          JOptionPane.showMessageDialog(null, "CPF não existe. Não foi possível excluir o Médico.");  
        }
    }

    private boolean atualizarMedico() {
        Medico medico = new Medico();
        MedicoDAO inserirMedico = new MedicoDAO();
        
        medico.setCpf(JOptionPane.showInputDialog(null, "Digite o CPF do médico para atualizar."));
        if (inserirMedico.existeRegistroPorCpf(medico.getCpf())){
                 
        String nome= JOptionPane.showInputDialog(null, "Digite o nome do médico.");
        if(nome==null)return false;
            medico.setMedNome(nome);
        
        String crm = JOptionPane.showInputDialog(null, "Digite o CRM do médico.");
        if (crm == null) return false;
        medico.setCrm(crm);
        
        String celMensagem = JOptionPane.showInputDialog(null, "Digite o Celular para mensagem do médico.");
        if (celMensagem ==null) return false;
        medico.setCelMensagem(celMensagem);
        
        String cel = JOptionPane.showInputDialog(null, "Digite o Celular do médico.");
        if (cel ==null) return false;
        medico.setCel(cel);
        
        String email = JOptionPane.showInputDialog(null, "Digite o e-mail do médico.");
        if (email ==null) return false;
        medico.setEmail(email);
        
        String cpf = JOptionPane.showInputDialog(null, "Digite o CPF do médico para cadastrar.");
        if (cpf==null) return false;     
            medico.setCpf(cpf);
            
        String cnpj = JOptionPane.showInputDialog(null, "Digite o CNPJ do médico para cadastrar.");
        if (cnpj==null) return false;     
            medico.setCnpj(cnpj);
               
        String logradouro= JOptionPane.showInputDialog(null, "Digite o Logradouro da residência.");
        if (logradouro==null) return false;     
            medico.setLogradouro(logradouro);
        
        String numLog = JOptionPane.showInputDialog(null, "Digite o número da residência.");
        if (numLog==null) return false;     
            medico.setNumLog(numLog);
        
        String complemento = JOptionPane.showInputDialog(null, "Digite o complemento do endereço do médico.");
        if (complemento==null) return false;     
            medico.setComplemento(complemento);
        
        String bairro = JOptionPane.showInputDialog(null, "Digite o Bairro.");
        if (bairro==null) return false;     
            medico.setBairro(bairro);
        
        String cidade = JOptionPane.showInputDialog(null, "Digite o nome do cidade.");
        if (cidade==null) return false;     
            medico.setCidade(cidade);
        
        String uf = JOptionPane.showInputDialog(null, "Digite o Estado.");
        if (uf==null) return false;     
            medico.setUf(uf);
        
        String cep = JOptionPane.showInputDialog(null, "Digite CEP da residência.");
        if (cep==null) return false;     
            medico.setCep (cep);
        }
        if(inserirMedico.atualizar(medico)){
            JOptionPane.showMessageDialog(null, "Médico atualizado com sucesso!");	
        }else{
            JOptionPane.showMessageDialog(null,"Médico não cadastrado. Tente novamente!");
        }
        return true;
    }

    private void exibirTodosMedicos() {
        MedicoDAO consultarMedicos = new MedicoDAO();
        
        List<Medico> medicos = consultarMedicos.listarTodosMedicos();
        JOptionPane.showMessageDialog(null, medicos.toString());
    }

    private void exibirMedicoCRM() throws SQLException {
        MedicoDAO consultarMedicos = new MedicoDAO();
        String crm = JOptionPane.showInputDialog(null, "Digite o CRM para consultar");
        
        Medico medicoConsultado = consultarMedicos.consultarMedicoPorCRM(crm);
        try{
        if (medicoConsultado != null){
            JOptionPane.showMessageDialog(null, medicoConsultado);
        }else {
            JOptionPane.showMessageDialog(null,"Médico não encontrado. Tente novamente!");
        }
        }catch (NumberFormatException  e){
                System.out.println(e.getMessage());
        } 
          
    }
    
}
