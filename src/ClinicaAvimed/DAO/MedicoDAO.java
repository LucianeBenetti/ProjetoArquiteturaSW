package ClinicaAvimed.DAO;

import ClinicaAvimed.VO.Medico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicoDAO {
    
    public boolean existeRegistroPorCpf(String cpf) {
		
	Connection conn = Banco.getConnection();
	Statement stmt = Banco.getStatement(conn);
	ResultSet resultado = null;
	String query = "SELECT * FROM medico WHERE cpf like '" + cpf + "'";
            try {
		resultado = stmt.executeQuery(query);
                    if (resultado.next()) {
			return true;
			}
            }catch (SQLException e) {
		System.out.println("Erro ao executar Query que verifica a existência de Médico por CPF. Causa: \n" + e.getMessage());
                    return false;
            }finally {
		Banco.closeResultSet(resultado);
		Banco.closeStatement(stmt);
		Banco.closeConnection(conn);
            }
	return false;
    }
    

public int inserir(Medico medico) {
    int novoId = -1;
        
    String query = "INSERT INTO medico (medNome, crm, celMen, cel, email, cpf, cnpj, logradouro, numLog, complemento, bairro, cidade, uf, cep)" 
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    Connection conn = Banco.getConnection();
    PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query, Statement.RETURN_GENERATED_KEYS);
    
    try{
        prepStmt.setString(1, medico.getMedNome());
        prepStmt.setString(2, medico.getCrm());
        prepStmt.setString(3, medico.getCelMensagem());
        prepStmt.setString(4, medico.getCel());
        prepStmt.setString(5, medico.getEmail());
        prepStmt.setString(6, medico.getCpf());
        prepStmt.setString(7, medico.getCnpj());
        prepStmt.setString(8, medico.getLogradouro());
        prepStmt.setString(9, medico.getNumLog());
        prepStmt.setString(10, medico.getComplemento());
        prepStmt.setString(11, medico.getBairro());
        prepStmt.setString(12, medico.getCidade());
        prepStmt.setString(13, medico.getUf());
        prepStmt.setString(14, medico.getCep());
        
        prepStmt.executeUpdate();
        
        ResultSet generatedKeys = prepStmt.getGeneratedKeys();
        if(generatedKeys.next()){
            novoId = generatedKeys.getInt(1);
        }
    }catch(SQLException e){
        System.out.println("Erro ao executar Query de Cadastro de Médico! Causa: \n: " + e.getMessage());
    }finally {
       Banco.closePreparedStatement(prepStmt);
       Banco.closeConnection(conn);
    }
    return novoId;
}

    public boolean delete(String cpf) {
        boolean sucessoDelete = false;
        
        String query = "DELETE from medico where cpf like ? ";
        
        Connection conn = Banco.getConnection();
        PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
        
        try{
            prepStmt.setString(1, cpf);
            int codigoRetorno = prepStmt.executeUpdate();
                if (codigoRetorno == 1){
                    sucessoDelete = true;
                }
        }catch(SQLException e){
           System.out.println("Erro ao executar Query de Exclusão do Médico! Causa: \n: " + e.getMessage()); 
        }finally{
            Banco.closePreparedStatement(prepStmt);
            Banco.closeConnection(conn);
        }
        return sucessoDelete;
    }

    public boolean atualizar(Medico medico) {
        boolean sucessoAtualizar = false;
           
        String query = "UPDATE medico SET medNome=?, crm=?, celMen=?, cel=?, email=?, cpf=?, cnpj=?, logradouro=?, numLog=?, complemento=?, bairro=?, cidade=?, uf=?, cep=?" + " where cpf like ?"; 

         Connection conn = Banco.getConnection();
         PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
         
        try {
            prepStmt.setString(1, medico.getMedNome());
            prepStmt.setString(2, medico.getCrm());
            prepStmt.setString(3, medico.getCelMensagem());
            prepStmt.setString(4, medico.getCel());
            prepStmt.setString(5, medico.getEmail());
            prepStmt.setString(6, medico.getCpf());
            prepStmt.setString(7, medico.getCnpj());
            prepStmt.setString(8, medico.getLogradouro());
            prepStmt.setString(9, medico.getNumLog());
            prepStmt.setString(10, medico.getComplemento());
            prepStmt.setString(11, medico.getBairro());
            prepStmt.setString(12, medico.getCidade());
            prepStmt.setString(13, medico.getUf());
            prepStmt.setString(14, medico.getCep());
            prepStmt.setString(15, medico.getCpf());
            
            int codigoRetorno = prepStmt.executeUpdate();
            
            if(codigoRetorno == 1){
                sucessoAtualizar = true;
            }
         } catch (SQLException ex) {
            System.out.println("Erro ao executar Query de Atualização do Médico!Causa: \n: " + ex.getMessage());
         }finally {
            Banco.closePreparedStatement(prepStmt);
            Banco.closeConnection(conn);
	}
        return sucessoAtualizar;
    }

    public ArrayList<Medico> listarTodosMedicos() {
        
        String query = "SELECT *From medico";
        
        Connection conn = Banco.getConnection();
        PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
        
        ArrayList<Medico> medicos = new ArrayList<>();
        
        try {
            ResultSet result = prepStmt.executeQuery(query);
            while(result.next()){
                Medico medico = new Medico();
                medico.setMedNome(result.getString(2));
                medico.setCpf(result.getString(7));
                medico.setCrm(result.getString(3));
                medico.setCnpj(result.getString(8));
                medico.setCelMensagem(result.getString(4));
                
                medicos.add(medico);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        return medicos;
    }

    public Medico consultarMedicoPorCRM(String crm) throws SQLException {
    
        String query = "SELECT *from medico " + " where crm = ?";
        
        Connection conn = Banco.getConnection();
        PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
        ArrayList <Medico> medicos = new ArrayList<>();
        Medico medico = null;
         try { 
            prepStmt.setString(1, crm);
            ResultSet result = prepStmt.executeQuery();
          
            while(result.next()){
                medico = new Medico();
                medico.setMedicoCod(result.getInt("medCod"));
                medico.setMedNome(result.getString("medNome"));
                medico.setCrm(result.getString("crm"));
                medico.setCelMensagem(result.getString("celMen"));
                medico.setCel(result.getString("cel"));
                medico.setEmail(result.getString("email"));
                medico.setCpf(result.getString("cpf"));
                medico.setCnpj(result.getString("cnpj"));
                medico.setLogradouro(result.getString("logradouro"));
                medico.setNumLog(result.getString("numLog"));
                medico.setComplemento(result.getString("complemento"));
                medico.setBairro(result.getString("bairro"));
                medico.setCidade(result.getString("cidade"));
                medico.setUf(result.getString("uf"));
                medico.setCep(result.getString("cep"));
                medico.setCrm(result.getString("crm"));
                  
                medicos.add(medico);
            }
        } catch (SQLException ex) {
               System.out.println(ex.getMessage());
        }finally {
          Banco.closeStatement(prepStmt);
          Banco.closeConnection(conn);
	}
        return medico;
    }
    
}
