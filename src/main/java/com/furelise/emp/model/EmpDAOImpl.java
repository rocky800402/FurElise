package com.furelise.emp.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAOImpl implements EmpDAO  {
	private static final String INSERT_STMT = "insert into emp values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE emp SET empName = ?,empMail = ?,empTel = ?,empBirth = ?,empPass = ?,IDNumber = ?,empLicense = ?, empArea1 = ?, empArea2 = ?, empArea3 = ?, timeID = ?, IDF = ?, IDB = ?, licenseF = ?, bankCode = ?, accountNo = ?, bankPic = ?,workSum = ?, empRegiDate = ?, empStatus = ?, empIsSuspended = ? WHERE empID = ?";
	private static final String DELETE_STMT = "DELETE FROM emp WHERE empID = ?";
	private static final String FIND_BY_PK = "select * from emp where empID = ?";
	private static final String GET_ALL = "select * from emp ";
	
	static {
		try {
			Class.forName(Util.DRIVER);
		}catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	public void add(Emp emp) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, emp.getEmpID());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getEmpMail());
			pstmt.setString(4, emp.getEmpTel());
			pstmt.setDate(5, emp.getEmpBirth());
			pstmt.setString(6, emp.getEmpPass());
			pstmt.setString(7, emp.getIDNumber());
			pstmt.setBoolean(8, emp.isEmpLicense());
			pstmt.setString(9, emp.getEmpArea1());
			pstmt.setString(10, emp.getEmpArea2());
			pstmt.setString(11, emp.getEmpArea3());
			pstmt.setInt(12, emp.getTimeID());
//			pstmt.setBlob(13, emp.getIDF());
//			pstmt.setBlob(14, emp.getIDB());
//			pstmt.setBlob(15, emp.getLicenseF());
			pstmt.setString(16, emp.getBankCode());
			pstmt.setString(17, emp.getAccountNo());
//			pstmt.setBlob(18, emp.getBankPic());
			pstmt.setInt(19, emp.getWorkSum());
			pstmt.setTimestamp(20, emp.getEmpRegiDate());
			pstmt.setInt(21, emp.getEmpStatus());
			pstmt.setBoolean(22, emp.isEmpIsSuspended());
			
			byte[] pic1 = emp.getIDF();
			pstmt.setBytes(13,pic1);
			byte[] pic2 = emp.getIDB();
			pstmt.setBytes(14,pic2);
			byte[] pic3 = emp.getLicenseF();
			pstmt.setBytes(15,pic3);
			byte[] pic4 = emp.getBankPic();
			pstmt.setBytes(18,pic4);
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
		} finally {
			closeResources(con, pstmt, null);
		}
	}
	
	public void update(Emp emp) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, emp.getEmpID());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getEmpMail());
			pstmt.setString(4, emp.getEmpTel());
			pstmt.setDate(5, emp.getEmpBirth());
			pstmt.setString(6, emp.getEmpPass());
			pstmt.setString(7, emp.getIDNumber());
			pstmt.setBoolean(8, emp.isEmpLicense());
			pstmt.setString(9, emp.getEmpArea1());
			pstmt.setString(10, emp.getEmpArea2());
			pstmt.setString(11, emp.getEmpArea3());
			pstmt.setInt(12, emp.getTimeID());
//			pstmt.setBlob(13, emp.getIDF());
//			pstmt.setBlob(14, emp.getIDB());
//			pstmt.setBlob(15, emp.getLicenseF());
			pstmt.setString(16, emp.getBankCode());
			pstmt.setString(17, emp.getAccountNo());
//			pstmt.setBlob(18, emp.getBankPic());
			pstmt.setInt(19, emp.getWorkSum());
			pstmt.setTimestamp(20, emp.getEmpRegiDate());
			pstmt.setInt(21, emp.getEmpStatus());
			pstmt.setBoolean(22, emp.isEmpIsSuspended());
			
			byte[] pic5 = emp.getIDF();
			pstmt.setBytes(13,pic5);
			byte[] pic6 = emp.getIDB();
			pstmt.setBytes(14,pic6);
			byte[] pic7 = emp.getLicenseF();
			pstmt.setBytes(15,pic7);
			byte[] pic8 = emp.getBankPic();
			pstmt.setBytes(18,pic8);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
		} finally {
			closeResources(con, pstmt, null);
		}
	}
	
	public void delete(Integer empID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, empID);
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
		} finally {
			closeResources(con, pstmt, null);
		}
	}
	
	public Emp findByPK(Integer empID) {
		Emp emp = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
		try {
			
			con = DriverManager.getConnection(Util.URL,Util.USER,Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1,empID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				emp = new Emp();
				emp.setEmpID(rs.getInt("empID")); ;
				emp.setEmpName(rs.getString("empName"));
				emp.setEmpMail(rs.getString("empMail"));
				emp.setEmpTel(rs.getString("empTel"));
				emp.setEmpBirth(rs.getDate("empBirth"));
				emp.setEmpPass(rs.getString("empPass"));
				emp.setIDNumber(rs.getString("iDNumber"));
				emp.setEmpLicense(rs.getBoolean("empLicense"));
				emp.setEmpArea1(rs.getString("empArea1"));
				emp.setEmpArea2(rs.getString("empArea2")); ;
				emp.setEmpArea3(rs.getString("empArea3"));
				emp.setTimeID(rs.getInt("timeID"));
				emp.setIDF(rs.getBytes("IDF"));
				emp.setIDB(rs.getBytes("IDB"));
				emp.setLicenseF(rs.getBytes("licenseF"));
				emp.setBankCode(rs.getString("bankCode"));
				emp.setAccountNo(rs.getString("accountNo"));
				emp.setBankPic(rs.getBytes("bankPic"));
				emp.setWorkSum(rs.getInt("workSum")); ;
				emp.setEmpRegiDate(rs.getTimestamp("empRegiDate"));
				emp.setEmpStatus(rs.getInt("empStatus"));
				emp.setEmpIsSuspended(rs.getBoolean("empIsSuspended"));				
		
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			Util.closeResources(con, pstmt, rs);
		}
		return emp;
	}
	
	public List<Emp> getAll() {
		List<Emp> empList = new ArrayList<>();
		Emp emp = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				emp = new Emp();
				emp.setEmpID(rs.getInt("empID")); ;
				emp.setEmpName(rs.getString("empName"));
				emp.setEmpMail(rs.getString("empMail"));
				emp.setEmpTel(rs.getString("empTel"));
				emp.setEmpBirth(rs.getDate("empBirth"));
				emp.setEmpPass(rs.getString("empPass"));
				emp.setIDNumber(rs.getString("iDNumber"));
				emp.setEmpLicense(rs.getBoolean("empLicense"));
				emp.setEmpArea1(rs.getString("empArea1"));
				emp.setEmpArea2(rs.getString("empArea2")); ;
				emp.setEmpArea3(rs.getString("empArea3"));
				emp.setTimeID(rs.getInt("timeID"));
				emp.setIDF(rs.getBytes("IDF"));
				emp.setIDB(rs.getBytes("IDB"));
				emp.setLicenseF(rs.getBytes("licenseF"));
				emp.setBankCode(rs.getString("bankCode"));
				emp.setAccountNo(rs.getString("accountNo"));
				emp.setBankPic(rs.getBytes("bankPic"));
				emp.setWorkSum(rs.getInt("workSum")); ;
				emp.setEmpRegiDate(rs.getTimestamp("empRegiDate"));
				emp.setEmpStatus(rs.getInt("empStatus"));
				emp.setEmpIsSuspended(rs.getBoolean("empIsSuspended"));
				empList.add(emp);
			}
		
	}catch (SQLException se) {
		se.printStackTrace();
		// Clean up JDBC resources
	} finally {
		closeResources(con, pstmt, rs);
	}
		return empList;
}
	private void closeResources(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException{
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = fis.readAllBytes();
		fis.close();
		return buffer;
	}
	
}
