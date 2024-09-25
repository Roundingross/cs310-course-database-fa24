package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class RegistrationDAO {
    
    private final DAOFactory daoFactory;
    
    RegistrationDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public boolean create(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // Query for inserting new record
		String query = "INSERT INTO jsu_fa24_v1.registration (studentid, termid, crn) VALUES (?, ?, ?)";
		ps = conn.prepareStatement(query);
		// Placeholder values
		ps.setInt(1, studentid);
		ps.setInt(2, termid);
		ps.setInt(3, crn);
		
		// Execute count update and return result
		int updateCount = ps.executeUpdate();
		result = updateCount > 0;
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public boolean delete(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // Query to delete from record (studentid, termid, crn)
                String query = "DELETE FROM registration WHERE studentid=? AND termid=? AND crn=?";
		ps = conn.prepareStatement(query);
		// Placeholder values
		ps.setInt(1, studentid);
		ps.setInt(2, termid);
		ps.setInt(3, crn);
		
		// Execute count update and return result
		int updateCount = ps.executeUpdate();
		result = updateCount > 0;
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
    public boolean delete(int studentid, int termid) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // Query to delete from record (studentid, termid)
                String query = "DELETE FROM registration WHERE studentid=? AND termid=?";
		ps = conn.prepareStatement(query);
		// Placeholder values
		ps.setInt(1, studentid);
		ps.setInt(2, termid);
		
		// Execute count update and return result
		int updateCount = ps.executeUpdate();
		result = updateCount > 0;
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public String list(int studentid, int termid) {
        
        String result = null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // Query to return list of records
                String query = "SELECT * FROM registration WHERE studentid=? AND termid=? ORDER BY crn";
		ps = conn.prepareStatement(query);
		// Placeholder values
		ps.setInt(1, studentid);
		ps.setInt(2, termid);
		
		// Execute query and check for results
		boolean hasResults = ps.execute();
		if (hasResults) {
			// Converting result and returning as JSON
			rs = ps.getResultSet();
			result = DAOUtility.getResultSetAsJson(rs);
		}
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
}
