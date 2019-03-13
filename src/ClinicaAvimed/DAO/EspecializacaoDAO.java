package ClinicaAvimed.DAO;

import ClinicaAvimed.VO.Especializacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EspecializacaoDAO {
    
    public boolean existeRegistroPorId(int espeCod) {
        Connection conn = Banco.getConnection();
	Statement stmt = Banco.getStatement(conn);
	ResultSet resultado = null;
	String query = "SELECT * FROM especializacao WHERE espCod like '" + espeCod + "'";
	try {
            resultado = stmt.executeQuery(query);
            if (resultado.next()) {
               return true;
            }
	}catch (SQLException e) {
		System.out.println("Erro ao executar Query que verifica a existência de Especialização pelo ID. Cause: \n" + e.getMessage());
		return false;
	}finally {
            Banco.closeResultSet(resultado);
            Banco.closeStatement(stmt);
            Banco.closeConnection(conn);
	}
	return false;
}
    
 public int inserir(Especializacao especializacao) {
    int novoId = -1;
          
    String query = "INSERT INTO especializacao (espCod, medCod, espAnoEspecializacao)" + " VALUES (?, ?, ?)";
    
    Connection conn = Banco.getConnection();
    PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query, Statement.RETURN_GENERATED_KEYS);
    
    try{
        prepStmt.setInt(1, especializacao.getEspecialidadeCod());
        prepStmt.setInt(2, especializacao.getMedicoCod());
        prepStmt.setString(3, especializacao.getEspAnoEspecializacao());
        
        prepStmt.executeUpdate();
        
        ResultSet generatedKeys = prepStmt.getGeneratedKeys();
        if(generatedKeys.next()){
            novoId = generatedKeys.getInt(1);
        }
    }catch(SQLException e){
        System.out.println("Erro ao executar Query de Cadastro da Especializacao! Causa: \n: " + e.getMessage());
    }finally {
       Banco.closePreparedStatement(prepStmt);
       Banco.closeConnection(conn);
    }
    return novoId;
}

    public boolean delete(int EspecializacaoCod) {
        boolean sucessoDelete = false;
        
        String query = "DELETE from especializacao where EspeCod = ? ";
        
        Connection conn = Banco.getConnection();
        PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
        
        try{
            prepStmt.setInt(1, EspecializacaoCod);
            int codigoRetorno = prepStmt.executeUpdate();
                if (codigoRetorno == 1){
                    sucessoDelete = true;
                }
        }catch(SQLException e){
           System.out.println("Erro ao executar Query de Exclusão da Especializacao! Causa: \n: " + e.getMessage()); 
        }finally{
            Banco.closePreparedStatement(prepStmt);
            Banco.closeConnection(conn);
        }
        return sucessoDelete;
    }

    public boolean atualizar(Especializacao especializacao) {
        boolean sucessoAtualizar = false;
         
        String query = "UPDATE especializacao SET espCod=?, medCod=?, espAnoEspecializacao=?  " + " where EspeCod = ?"; 

         Connection conn = Banco.getConnection();
         PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
         
        try {
            prepStmt.setInt(1, especializacao.getEspecialidadeCod());
            prepStmt.setInt(2, especializacao.getMedicoCod());
            prepStmt.setString(3, especializacao.getEspAnoEspecializacao());
            prepStmt.setInt(4, especializacao.getEspecializacaoCod());
          
            int codigoRetorno = prepStmt.executeUpdate();
            
            if(codigoRetorno == 1){
                sucessoAtualizar = true;
            }
         } catch (SQLException ex) {
            System.out.println("Erro ao executar Query de Atualização da Especializacao!Causa: \n: " + ex.getMessage());
         }finally {
            Banco.closePreparedStatement(prepStmt);
            Banco.closeConnection(conn);
	}
        return sucessoAtualizar;
    }

    public ArrayList<Especializacao> listarTodosEspecializacoes() {
        
        String query = "SELECT *From especializacao";
        
        Connection conn = Banco.getConnection();
        PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
        
        ArrayList<Especializacao> especializacaos = new ArrayList<>();
        
        try {
            ResultSet result = prepStmt.executeQuery(query);
            while(result.next()){
                Especializacao especializacao = new Especializacao();
                
                especializacao.setEspecializacaoCod(result.getInt(1));
                especializacao.setEspecialidadeCod(result.getInt(2));
                especializacao.setMedicoCod(result.getInt(3));
                especializacao.setEspAnoEspecializacao(result.getString(4));
               
                especializacaos.add(especializacao);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
              
        return especializacaos;
    }

    public Especializacao consultarEspecializacaoPorID(int espeCod) throws SQLException {
    
        String query = "SELECT *from especializacao " + " where espeCod = ?";
        
        Connection conn = Banco.getConnection();
        PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
        ArrayList <Especializacao> especializacoes = new ArrayList<>();
        Especializacao especializacao = null;
         try { 
            prepStmt.setInt(1, espeCod);
            ResultSet result = prepStmt.executeQuery();
          
            while(result.next()){
                especializacao = new Especializacao();
                
                especializacao.setEspecializacaoCod(result.getInt(1));
                especializacao.setEspecialidadeCod(result.getInt(2));
                especializacao.setMedicoCod(result.getInt(3));
                especializacao.setEspAnoEspecializacao(result.getString(4));
                
                especializacoes.add(especializacao);
            }
        } catch (SQLException ex) {
               System.out.println(ex.getMessage());
        }finally {
          Banco.closeStatement(prepStmt);
          Banco.closeConnection(conn);
	}
        return especializacao;
    }
    
}
