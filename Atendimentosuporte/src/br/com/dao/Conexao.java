package br.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {
	
	protected Connection con = null; // classse para estabelecer conexoes com o servidor de banco de dados
	protected PreparedStatement pst = null; // classe para as execuções de sql
	protected ResultSet rs = null; // guarda o resultado da consulta select
	
	public void abrirConexao() {
		try {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				
				con = DriverManager.getConnection("jdbc:mysql://10.26.44.224:6020/suportedb?useTimeZone=true", "root", "123@senac");
		}
		catch(SQLException se) {
			se.printStackTrace(); // mostrar erro do SQL
		}
		catch(Exception e) {
			e.printStackTrace(); // mostrar erro generico
		}
	}
	public void fecharConexao() {
		try {
				con.close();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}