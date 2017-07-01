package client;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import server.Employee;

public class ParseEmployees {

	boolean inEmployees = false;
	boolean inEmployee = false;
	boolean inId = false;
	boolean inName = false;
	boolean inSurname = false;
	boolean inDepartment = false;
	boolean inPosition = false;
	boolean inRate = false;
	boolean inHours = false;
	
	Employee currentEmployee = new Employee();
	List<Employee> currentEmployeeList = new ArrayList<Employee>();
	
	public List<Employee> doParseEmployees(String s) {
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser pullParser = factory.newPullParser();
			pullParser.setInput(new StringReader(s));
			processDocument(pullParser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentEmployeeList; 
	}
	
	public void processDocument(XmlPullParser pullParser) throws XmlPullParserException, IOException {
		int eventType = pullParser.getEventType();
		do {
			if (eventType == XmlPullParser.START_DOCUMENT) {
				System.out.println("Start document");
			} else if (eventType == XmlPullParser.END_DOCUMENT) {
				System.out.println("End document");
			} else if (eventType == XmlPullParser.START_TAG) {
				processStartElement(pullParser);
			} else if (eventType == XmlPullParser.END_TAG) {
				processEndElement(pullParser);
			} else if (eventType == XmlPullParser.TEXT) {
				processText(pullParser);
			}
			eventType = pullParser.next();
		} while (eventType != XmlPullParser.END_DOCUMENT);
	}
	
	public void processStartElement(XmlPullParser event) {
		String name = event.getName();
		if (name.equals("employees")) {
			inEmployees = true;
			currentEmployeeList = new ArrayList<Employee>();
		}
		else if (name.equals("employee")) {
			inEmployee = true;
			currentEmployee = new Employee();
		} else if (name.equals("id")) {
			inId = true;
		} else if (name.equals("name")) {
			inName = true;
		} else if (name.equals("surname")) {
			inSurname = true;
		} else if (name.equals("department")) {
			inDepartment = true;
		} else if (name.equals("position")) {
			inPosition = true;
		} else if (name.equals("rate")) {
			inRate = true;
		} else if (name.equals("hours")) {
			inHours = true;
		}
	}
	
	public void processText(XmlPullParser event) throws XmlPullParserException {
		if (inId) {
			String s = event.getText();
			currentEmployee.setId(Integer.parseInt(s));
		}
		if (inName) {
			String s = event.getText();
			currentEmployee.setName(s);
		}
		if (inSurname) {
			String s = event.getText();
			currentEmployee.setSurname(s);
		}
		if (inDepartment) {
			String s = event.getText();
			currentEmployee.setDepartment(s);
		}
		if (inPosition) {
			String s = event.getText();
			currentEmployee.setPosition(s);
		}
		if (inRate) {
			String s = event.getText();
			currentEmployee.setRate(Double.parseDouble(s));
		}
		if (inHours) {
			String s = event.getText();
			currentEmployee.setHours(Double.parseDouble(s));
		}
	}	
	
	public void processEndElement(XmlPullParser event) {
		String name = event.getName();
		
		if (name.equals("employees")) {
			inEmployees = false;
		}
		else if (name.equals("employee")) {
			inEmployee = false;
			currentEmployeeList.add(currentEmployee);
		} else if (name.equals("id")) {
			inId = false;
		} else if (name.equals("name")) {
			inName = false;
		} else if (name.equals("surname")) {
			inSurname = false;
		} else if (name.equals("department")) {
			inDepartment = false;
		} else if (name.equals("position")) {
			inPosition = false;
		} else if (name.equals("rate")) {
			inRate = false;
		} else if (name.equals("hours")) {
			inHours = false;
		}
	}

}
