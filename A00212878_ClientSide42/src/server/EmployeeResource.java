package server;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/employees")
public class EmployeeResource {
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public List<Employee> getEmployees() {
		return EmployeeDao.instance.getEmployee();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Path("{employeeId}")
	public Employee getEmployee(@PathParam("employeeId") String id ) {
		return EmployeeDao.instance.getId(Integer.parseInt(id));
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void postEmployee(
			@FormParam("name") String name,
			@FormParam("surname") String surname,
			@FormParam("department") String department,
			@FormParam("position") String position,
			@FormParam("rate") String rate,
			@FormParam("hours") String hours,
			@Context HttpServletResponse servletResponse) throws IOException {
		System.out.println("Inside POST:");
		System.out.println("Name = " + name);
		System.out.println("Surname = " + surname);
		System.out.println("Department = " + department);
		System.out.println("Position = " + position);
		System.out.println("Rate = " + rate);
		System.out.println("Hours = " + hours);
		
		Employee employee = new Employee();
		employee.setName(name);
		employee.setSurname(surname);
		employee.setDepartment(department);
		employee.setPosition(position);
		employee.setRate(Double.parseDouble(rate));
		employee.setHours(Double.parseDouble(hours));
		EmployeeDao.instance.saveEmployee(employee);
		System.out.println("Data on employee "+name+" "+surname+" saved in DB");
		//return "Data on "+name+" "+surname+" saved in DB";
	}
	
	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("{employeeId}")
	public void deleteEmployee(
			@PathParam("employeeId") int id,
			@Context HttpServletResponse servletResponse)throws IOException {
		EmployeeDao.instance.delete(id);
		System.out.println("Employee with Id "+id+" deleted from DB");
	}
	
}
