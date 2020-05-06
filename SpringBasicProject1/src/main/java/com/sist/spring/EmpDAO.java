package com.sist.spring;
import java.util.*;
import java.sql.*;
/*
 	class A
 	{
 		private String name;
 		public void setName(String name)
 		{
 			this.name=name;
 		}
 	}
 	
 	A a; A a=new A();
 	a.setName("홍길동");
 */
public class EmpDAO {
	private Connection conn;
	private PreparedStatement ps;
	private String driverName;
	private String url;
	private String userName;
	private String password;
	
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public PreparedStatement getPs() {
		return ps;
	}
	public void setPs(PreparedStatement ps) {
		this.ps = ps;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void init(){
		try{
			Class.forName(driverName);
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	public void getConnection(){
		try{
			conn=DriverManager.getConnection(url,userName,password);
		}catch (Exception ex) {}
	}
	
	public void disConnection(){
		try{
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		}catch (Exception ex) {}
	}
	
	public List<EmpVO> empAllData()
	{
		List<EmpVO> list=new ArrayList<EmpVO>();
		try{
			getConnection();
			String sql="SELECT empno,ename,job,hiredate,sal "
					+ "FROM emp";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				EmpVO vo=new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				vo.setSal(rs.getInt(5));
				
				list.add(vo);
			}
			rs.close();
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally
		{
			disConnection();
		}
		return list;
	}

	
}
