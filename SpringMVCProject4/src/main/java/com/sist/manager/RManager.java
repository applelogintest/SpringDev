package com.sist.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Component;
import java.io.*;
/*
 	Spring => 수집 (Hadoop) ==> mapreduce,spark ==> R ===> React
 							  ================   		 ====== Android
 							  	 mongodb					코틀린,React-Native
 */
@Component
public class RManager {
	/*public static void main(String[] args)
	{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			Connection conn=DriverManager.getConnection(url,"hr","happy");
			String sql="SELECT empno,ename,job,sal FROM emp";
			
			PreparedStatement ps=conn.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			
			String temp="empno,ename,job,sal\n";
			while(rs.next())
			{
				temp+=rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getInt(4)+"\n";
			}
			rs.close();
			ps.close();
			conn.close();
			temp=temp.substring(0,temp.lastIndexOf("\n"));
			
			FileWriter fw=new FileWriter("c:\\upload\\emp.csv");
			fw.write(temp);
			fw.close();
			
			RConnection rc=new RConnection();
			rc.voidEval("emp<-read.csv(\"c:/upload/emp.csv\",header=T,seq=\",\")"); // send Rserve를 통해 R한태 값을 보냄
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}*/
	// C:\springDev\springStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SpringMVCProject4\board
	public void rGraph(int no)
	{
		try{
			// R연결 => Rserve동작 
			RConnection rc=new RConnection(); // default : localhost
			rc.voidEval("library(rJava)"); // import
			rc.voidEval("library(KoNLP)");
			rc.voidEval("library(Rserve)");
			rc.voidEval("Rserve(FALSE,port=6311,args='--RS-encoding utf8 --no-save --slave --encoding utf8')");
			rc.voidEval("png(\"C:/springDev/springStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringMVCProject4/board/"+no+".png\",width=700,height=450)");
			rc.voidEval("data<-readLines(\"c:/data/board.txt\")"); // Eval은 send (Rserve를 통해 R한태 값을 보냄) 
			rc.voidEval("data2<-sapply(data,extractNoun,USE.NAMES = F)");
			rc.voidEval("data3<-unlist(data2)");
			rc.voidEval("data3<-gsub(\"[A-Za-z]\",\"\",data3)"); // replace == gsub
			rc.voidEval("data3<-gsub(\"[0-9]\",\"\",data3)");
			rc.voidEval("data3<-Filter(function(x){nchar(x)>=2},data3)");
			
			rc.voidEval("data4<-table(data3)");
			rc.voidEval("data5<-head(sort(data4,decreasing = T),10)");
			rc.voidEval("barplot(data5,col=rainbow(15))");
			rc.voidEval("dev.off()");
			rc.close();
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}




















