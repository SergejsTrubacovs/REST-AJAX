package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public enum EmployeeDao {
	instance;
	
	private Map<Integer, Employee> EmployeesMap = new HashMap<Integer, Employee>();
	
	private EmployeeDao() {
		
		Employee Employee1 = new Employee();
		Employee1.setId(1);
		Employee1.setName("Joe");
		Employee1.setSurname("Mullins");
		Employee1.setDepartment("Engineering");
		Employee1.setPosition("Lecturer");
		Employee1.setRate(63.08);
		Employee1.setHours(12.0);
		
		EmployeesMap.put(1, Employee1);
		
		Employee Employee2 = new Employee();
		Employee2.setId(2);
		Employee2.setName("Joan");
		Employee2.setSurname("Macgill");
		Employee2.setDepartment("Science");
		Employee2.setPosition("Researcher");
		Employee2.setRate(38.00);
		Employee2.setHours(35.0);
		
		EmployeesMap.put(2, Employee2);
		
		Employee Employee3 = new Employee();
		Employee3.setId(3);
		Employee3.setName("Jim");
		Employee3.setSurname("Mitchell");
		Employee3.setDepartment("Business");
		Employee3.setPosition("Researcher");
		Employee3.setRate(38.00);
		Employee3.setHours(25.0);
		
		EmployeesMap.put(3, Employee3);
		
		Employee Employee4 = new Employee();
		Employee4.setId(4);
		Employee4.setName("John");
		Employee4.setSurname("Magner");
		Employee4.setDepartment("Humanities");
		Employee4.setPosition("Lecturer");
		Employee4.setRate(63.08);
		Employee4.setHours(16.0);
		
		EmployeesMap.put(4, Employee4);
		
		Employee Employee5 = new Employee();
		Employee5.setId(5);
		Employee5.setName("Jean");
		Employee5.setSurname("Madden");
		Employee5.setDepartment("Design");
		Employee5.setPosition("Professor");
		Employee5.setRate(76.45);
		Employee5.setHours(14.0);
		
		EmployeesMap.put(5, Employee5);
	}
	
	public List<Employee> getEmployees() {
		List<Employee> Employees = new ArrayList<Employee>();
		Employees.addAll(EmployeesMap.values());
		return Employees;
	}
	
	public Employee getEmployee (int id) {
		return EmployeesMap.get(id);
	}

	public void create(Employee Employee) {
		EmployeesMap.put(Employee.getId(), Employee);
	}

	/**
	 * DB
	 */
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			 con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB", "sa", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public boolean executeUpdate(String updateStmt) throws SQLException  {
		
		boolean result = false;
		Connection con = getConnection();
		if(con == null)
			throw new SQLException("Database connection error...");
		try {
			System.out.println(updateStmt);
			Statement stmt = con.createStatement();
			stmt.executeUpdate(updateStmt);
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean delete(int id)
	{
		String stmt = "DELETE FROM employee WHERE id = " + id + "";
		boolean result = false;
		try {
			result = executeUpdate(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public List<Employee> getEmployee() {
		String queryStmt = "SELECT * FROM employee";
		Connection con = getConnection();
		List<Employee> empList = new ArrayList<Employee>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(queryStmt);
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String surname = rs.getString(3);
				String department = rs.getString(4);
				String position = rs.getString(5);
				Double rate = rs.getDouble(6);
				Double hours = rs.getDouble(7);

				
				Employee employee = new Employee();
				employee.setId(id);
				employee.setName(name);
				employee.setSurname(surname);
				employee.setDepartment(department);
				employee.setPosition(position);
				employee.setRate(rate);
				employee.setHours(hours);
				empList.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return empList;	
	}
	
	public Employee getId(int empId) {
		
		String queryStmt = "SELECT * FROM employee WHERE id = " + empId;
		Connection con = getConnection();
		Employee employee = null;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(queryStmt);
			if(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String surname = rs.getString(3);
				String department = rs.getString(4);
				String position = rs.getString(5);
				Double rate = rs.getDouble(6);
				Double hours = rs.getDouble(7);
				
				employee = new Employee();
				employee.setId(id);
				employee.setName(name);
				employee.setSurname(surname);
				employee.setDepartment(department);
				employee.setPosition(position);
				employee.setRate(rate);
				employee.setHours(hours);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return employee;
	}
	
	public boolean saveEmployee(Employee emp)
	{
		Connection con = getConnection();
		try {
			PreparedStatement prepStmt = con.prepareStatement(
					"INSERT INTO employee VALUES(NULL,?,?,?,?,?,?)");
			
			prepStmt.setString(1, emp.getName());
			prepStmt.setString(2, emp.getSurname());
			prepStmt.setString(3, emp.getDepartment());
			prepStmt.setString(4, emp.getPosition());
			prepStmt.setDouble(5, emp.getRate());
			prepStmt.setDouble(6, emp.getHours());
			
			prepStmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} return false;
	}

}
