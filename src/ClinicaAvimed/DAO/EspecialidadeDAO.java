package ClinicaAvimed.DAO;

import ClinicaAvimed.VO.Especialidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EspecialidadeDAO {
    
   public boolean existeRegistroPorId(int espCod) {
        Connection conn = Banco.getConnection();
	Statement stmt = Banco.getStatement(conn);
	ResultSet resultado = null;
	String query = "SELECT * FROM especialidade WHERE espCod like '" + espCod + "'";
	try {
            resultado = stmt.executeQuery(query);
            if (resultado.next()) {
               return true;
            }
	}catch (SQLException e) {
		System.out.println("Erro ao executar Query que verifica a existência de Especialidade pela ID. Cause: \n" + e.getMessage());
		return false;
	}finally {
            Banco.closeResultSet(resultado);
            Banco.closeStatement(stmt);
            Banco.closeConnection(conn);
	}
	return false;
}
    
 public int inserir(Especialidade especialidade) {
    int novoId = -1;
          
    String query = "INSERT INTO especialidade (espNome, espInstituicao)" + " VALUES (?, ?)";
    
    Connection conn = Banco.getConnection();
    PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query, Statement.RETURN_GENERATED_KEYS);
    
    try{
        prepStmt.setString(1, especialidade.getEspNome());
        prepStmt.setString(2, especialidade.getEspInstituicao());
        
        prepStmt.executeUpdate();
        
        ResultSet generatedKeys = prepStmt.getGeneratedKeys();
        if(generatedKeys.next()){
            novoId = generatedKeys.getInt(1);
        }
    }catch(SQLException e){
        System.out.println("Erro ao executar Query de Cadastro da Especialidade! Causa: \n: " + e.getMessage());
    }finally {
       Banco.closePreparedStatement(prepStmt);
       Banco.closeConnection(conn);
    }
    return novoId;
}

    public boolean delete(int EspecialidadeCod) {
        boolean sucessoDelete = false;
        
        String query = "DELETE from especialidade where EspCod = ? ";
        
        Connection conn = Banco.getConnection();
        PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
        
        try{
            prepStmt.setInt(1, EspecialidadeCod);
            int codigoRetorno = prepStmt.executeUpdate();
                if (codigoRetorno == 1){
                    sucessoDelete = true;
                }
        }catch(SQLException e){
           System.out.println("Erro ao executar Query de Exclusão da Especialidade! Causa: \n: " + e.getMessage()); 
        }finally{
            Banco.closePreparedStatement(prepStmt);
            Banco.closeConnection(conn);
        }
        return sucessoDelete;
    }

    public boolean atualizar(Especialidade especialidade) {
        boolean sucessoAtualizar = false;
           
        String query = "UPDATE especialidade SET espNome=?, espInstituicao=? " + "where EspCod = ?"; 

         Connection conn = Banco.getConnection();
         PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
         
        try {
            prepStmt.setString(1, especialidade.getEspNome());
            prepStmt.setString(2, especialidade.getEspInstituicao());
            prepStmt.setInt(3, especialidade.getEspecialidadeCod());
                        
            int codigoRetorno = prepStmt.executeUpdate();
            
            if(codigoRetorno == 1){
                sucessoAtualizar = true;
            }
         } catch (SQLException ex) {
            System.out.println("Erro ao executar Query de Atualização do Especialidade!Causa: \n: " + ex.getMessage());
         }finally {
            Banco.closePreparedStatement(prepStmt);
            Banco.closeConnection(conn);
	}
        return sucessoAtualizar;
    }

    public ArrayList<Especialidade> listarTodosEspecialidades() {
        
        String query = "SELECT *From especialidade";
        
        Connection conn = Banco.getConnection();
        PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
        
        ArrayList<Especialidade> especialidades = new ArrayList<>();
        
        try {
            ResultSet result = prepStmt.executeQuery(query);
            while(result.next()){
                Especialidade especialidade = new Especialidade();
                especialidade.setEspecialidadeCod(result.getInt(1));
                especialidade.setEspNome(result.getString(2));
                especialidade.setEspInstituicao(result.getString(3));
               
                especialidades.add(especialidade);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
              
        return especialidades;
    }

    public Especialidade consultarEspecialidadePorID(int espCod) throws SQLException {
    
        String query = "SELECT *from especialidade " + " where espCod = ?";
        
        Connection conn = Banco.getConnection();
        PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
        ArrayList <Especialidade> especialidades = new ArrayList<>();
        Especialidade especialidade = null;
         try { 
            prepStmt.setInt(1, espCod);
            ResultSet result = prepStmt.executeQuery();
          
            while(result.next()){
                especialidade = new Especialidade();
                especialidade.setEspecialidadeCod(result.getInt("espCod"));
                especialidade.setEspNome(result.getString("espNome"));
                especialidade.setEspInstituicao(result.getString("espInstituicao"));
                
                especialidades.add(especialidade);
            }
        } catch (SQLException ex) {
               System.out.println(ex.getMessage());
        }finally {
          Banco.closeStatement(prepStmt);
          Banco.closeConnection(conn);
	}
        return especialidade;
    }
    
}
