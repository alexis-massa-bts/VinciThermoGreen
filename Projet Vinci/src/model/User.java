/**
 * @author Alexis Massa
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import dataAccess.DataMySQL;

public class User {
	private String login;
	private String password;
	private String name;
	private String surname;
	private String email;
	private boolean role;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getRole() {
		return role;
	}
	public void setRole(boolean role) {
		this.role = role;
	}
}