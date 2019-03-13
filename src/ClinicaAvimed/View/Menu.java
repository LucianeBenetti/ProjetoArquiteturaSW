package ClinicaAvimed.View;

import javax.swing.JOptionPane;

public class Menu {
    
    private static final int MENU_MEDICO = 1;
    private static final int MENU_ESPECIALIDADE = 2;
    private static final int MENU_ESPECIALIZACAO = 3;
    private static final int SAIR = 4;
    
    public void apresentarMenu() {
        int opcao = -1;
        
        while (opcao != SAIR){
            try{
                String valorDigitado = (JOptionPane.showInputDialog(criarOpcoesMenu()));
                if(valorDigitado != null){
                    opcao = Integer.parseInt(valorDigitado);
                }else{
                    break;
                }
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Por favor, digitar a opção 1, 2, 3 ou 4!");
            }
            
            
            switch (opcao){
                case MENU_MEDICO:
                    MenuMedico menuMedico = new MenuMedico();
                    menuMedico.chamaMenuMedico();
                    break;
                
                case MENU_ESPECIALIDADE:
                    MenuEspecialidade menuEspecialidade = new MenuEspecialidade();
                    menuEspecialidade.chamaMenuEspecialidade();
                    break;
                    
                case MENU_ESPECIALIZACAO:
                    MenuEspecializacao menuEspecializacao = new MenuEspecializacao();
                    menuEspecializacao.chamaMenuEspecializacao();
                    break;
                
                case SAIR:
                    int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?");
                    if (resposta == 0){
                        JOptionPane.showMessageDialog(null, "Você foi desconectado do sistema!");
                    }
                    break;
                 
                default:
                    JOptionPane.showMessageDialog(null, "Opção Inválida. Digite a opção!");
            }
       }
    }

    private String criarOpcoesMenu() {
        String mensagem = "Sistema AVISENA - Controle de Especialidades Médicas";
        mensagem += "\nOpções";
        mensagem += "\n 1 - Menu Médico.";
        mensagem += "\n 2 - Menu Especialidade.";
        mensagem += "\n 3 - Menu Especialização";
        mensagem += "\n 4 - Sair.";
        return mensagem;
    }
    
}
