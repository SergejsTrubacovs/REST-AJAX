package client;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import server.Employee;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;

public class Client {

	private JFrame frame;
	private JTextField textFieldId;
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldDepartment;
	private JTextField textFieldPosition;
	private JTextField textFieldRate;
	private JTextField textFieldHours;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("College Employees");
		frame.setBounds(0, -14, 477, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("Employee Id");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("Georgia", Font.PLAIN, 13));
		lblId.setBounds(10, 58, 92, 22);
		frame.getContentPane().add(lblId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Georgia", Font.PLAIN, 13));
		lblName.setBounds(10, 91, 92, 22);
		frame.getContentPane().add(lblName);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSurname.setFont(new Font("Georgia", Font.PLAIN, 13));
		lblSurname.setBounds(10, 124, 92, 22);
		frame.getContentPane().add(lblSurname);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDepartment.setFont(new Font("Georgia", Font.PLAIN, 13));
		lblDepartment.setBounds(10, 157, 92, 22);
		frame.getContentPane().add(lblDepartment);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPosition.setFont(new Font("Georgia", Font.PLAIN, 13));
		lblPosition.setBounds(10, 190, 92, 22);
		frame.getContentPane().add(lblPosition);
		
		JLabel lblRate = new JLabel("Rate");
		lblRate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRate.setFont(new Font("Georgia", Font.PLAIN, 13));
		lblRate.setBounds(10, 223, 92, 22);
		frame.getContentPane().add(lblRate);
		
		JLabel lblHours = new JLabel("Hours");
		lblHours.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHours.setFont(new Font("Georgia", Font.PLAIN, 13));
		lblHours.setBounds(10, 256, 92, 22);
		frame.getContentPane().add(lblHours);
		
		textFieldId = new JTextField();
		textFieldId.setToolTipText("");
		textFieldId.setFont(new Font("Georgia", Font.PLAIN, 13));
		textFieldId.setColumns(10);
		textFieldId.setBounds(112, 60, 125, 20);
		frame.getContentPane().add(textFieldId);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Georgia", Font.PLAIN, 13));
		textFieldName.setColumns(10);
		textFieldName.setBounds(112, 93, 125, 20);
		frame.getContentPane().add(textFieldName);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setFont(new Font("Georgia", Font.PLAIN, 13));
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(112, 126, 125, 20);
		frame.getContentPane().add(textFieldSurname);
		
		textFieldDepartment = new JTextField();
		textFieldDepartment.setFont(new Font("Georgia", Font.PLAIN, 13));
		textFieldDepartment.setColumns(10);
		textFieldDepartment.setBounds(112, 159, 125, 20);
		frame.getContentPane().add(textFieldDepartment);
		
		textFieldPosition = new JTextField();
		textFieldPosition.setFont(new Font("Georgia", Font.PLAIN, 13));
		textFieldPosition.setColumns(10);
		textFieldPosition.setBounds(112, 192, 125, 20);
		frame.getContentPane().add(textFieldPosition);
		
		textFieldRate = new JTextField();
		textFieldRate.setFont(new Font("Georgia", Font.PLAIN, 13));
		textFieldRate.setColumns(10);
		textFieldRate.setBounds(112, 225, 125, 20);
		frame.getContentPane().add(textFieldRate);
		
		textFieldHours = new JTextField();
		textFieldHours.setFont(new Font("Georgia", Font.PLAIN, 13));
		textFieldHours.setColumns(10);
		textFieldHours.setBounds(112, 258, 125, 20);
		frame.getContentPane().add(textFieldHours);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(247, 60, 197, 218);
		frame.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		
		JLabel lblCollegeEmployees = new JLabel("College Employees");
		lblCollegeEmployees.setFont(new Font("Georgia", Font.BOLD, 16));
		lblCollegeEmployees.setBounds(112, 11, 164, 22);
		frame.getContentPane().add(lblCollegeEmployees);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					URI uri = new URIBuilder().setScheme("http")
							.setHost("localhost")
							.setPort(8080)
							.setPath("/A00212878_S_Trubacovs_REST/rest/employees")
							.build();
					
					System.out.println(uri.toString());
					HttpPost httpPost = new HttpPost(uri);
					httpPost.setHeader("Accept", "text/html");
					
					CloseableHttpClient httpClient = HttpClients.createDefault();
					
					//POST
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
					nameValuePairs.add(new BasicNameValuePair("id", textFieldId.getText()));
					nameValuePairs.add(new BasicNameValuePair("name", textFieldName.getText()));
					nameValuePairs.add(new BasicNameValuePair("surname", textFieldSurname.getText()));
					nameValuePairs.add(new BasicNameValuePair("department", textFieldDepartment.getText()));
					nameValuePairs.add(new BasicNameValuePair("position", textFieldPosition.getText()));
					nameValuePairs.add(new BasicNameValuePair("rate", textFieldRate.getText()));
					nameValuePairs.add(new BasicNameValuePair("hours", textFieldHours.getText()));
					
					httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					System.out.println("Sending Request");
					CloseableHttpResponse response = httpClient.execute(httpPost);
					
					textArea.setText("Data on "+textFieldName.getText()+" "+textFieldSurname.getText()+"\nsaved in DB");
					
					String text;
					try {
						HttpEntity entity=response.getEntity();
						text=getASCIIContentFromEntity(entity);
					} finally {
						response.close();
					}
					System.out.println(text);
					
				} catch (Exception e) {
					e.printStackTrace();
					}
			}
		});
		btnAdd.setFont(new Font("Georgia", Font.PLAIN, 13));
		btnAdd.setBounds(112, 300, 115, 25);
		frame.getContentPane().add(btnAdd);
		
		JButton btnList = new JButton("List All");
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CloseableHttpResponse response = null;
					try {
						URI uri = new URIBuilder().setScheme("http")
								.setHost("localhost").setPort(8080)
								.setPath("/A00212878_S_Trubacovs_REST/rest/employees").build();
						
						System.out.println(uri.toString());
						HttpGet httpGet = new HttpGet(uri);
						httpGet.setHeader("Accept", "application/xml");
						
						CloseableHttpClient httpClient = HttpClients.createDefault();
						response = httpClient.execute(httpGet);
						
						HttpEntity entity = response.getEntity();
						String text = getASCIIContentFromEntity(entity);
						System.out.println(text);
						List<Employee> employeeList = new ParseEmployees().doParseEmployees(text);
						for (Employee employee : employeeList) {
							System.out.println("Id: " + employee.getId());
							System.out.println("Name: " + employee.getName());
							System.out.println("Surname: "+ employee.getSurname());
							System.out.println("Department: " + employee.getDepartment());
							System.out.println("Position: " + employee.getPosition());
							System.out.println("Rate: " + employee.getRate());
							System.out.println("Hours: " + employee.getHours());
						}
						for (Employee employee : employeeList) {
							textArea.append(
									"\nId: "+ employee.getId()
									+"\nName: "+ employee.getName()
									+"\nSurname: "+ employee.getSurname()
									+"\nDepartment: "+ employee.getDepartment()
									+"\nPosition: "+ employee.getPosition()
									+"\nRate: "+ employee.getRate()
									+"\nHours: "+ employee.getHours());
						}
						
					} finally {
						response.close();
					}
				} catch (Exception el) {
					el.printStackTrace();
				}			
			}
		});
		btnList.setFont(new Font("Georgia", Font.PLAIN, 13));
		btnList.setBounds(112, 336, 115, 25);
		frame.getContentPane().add(btnList);
		
		JButton btnSearchById = new JButton("Search by Id");
		btnSearchById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CloseableHttpResponse response = null;
					
						URI uri = new URIBuilder().setScheme("http")
								.setHost("localhost").setPort(8080)
								.setPath("/A00212878_S_Trubacovs_REST/rest/employees/"
								+textFieldId.getText()).build();
						
						System.out.println(uri.toString());
						HttpGet httpGet = new HttpGet(uri);
						httpGet.setHeader("Accept", "application/xml");
						
						CloseableHttpClient httpClient = HttpClients.createDefault();
						response = httpClient.execute(httpGet);
						
						HttpEntity entity = response.getEntity();
						String text = getASCIIContentFromEntity(entity);
						System.out.println(text);
						List<Employee> empList = new ParseEmployees().doParseEmployees(text);
						for (int index = 0; index < empList.size(); index++) {
							Employee emp = empList.get(index);
							System.out.println("Id: " + emp.getId());
							System.out.println("Name: " + emp.getName());
							System.out.println("Surname: "+ emp.getSurname());
							System.out.println("Department: " + emp.getDepartment());
							System.out.println("Position: " + emp.getPosition());
							System.out.println("Rate: " + emp.getRate());
							System.out.println("Hours: " + emp.getHours());
							
							textFieldId.setText(""+ emp.getId());
							textFieldName.setText(""+ emp.getName());
							textFieldSurname.setText(""+ emp.getSurname());
							textFieldDepartment.setText(""+ emp.getDepartment());
							textFieldPosition.setText(""+ emp.getPosition());
							textFieldRate.setText(""+ emp.getRate());
							textFieldHours.setText(""+ emp.getHours());
							
							/*textArea.append(
									"\nId: "+ emp.getId()
									+"\nName: "+ emp.getName()
									+"\nSurname: "+ emp.getSurname()
									+"\nDepartment: "+ emp.getDepartment()
									+"\nPosition: "+ emp.getPosition()
									+"\nRate: "+ emp.getRate()
									+"\nHours: "+ emp.getHours());*/
							
						} response.close();	
				} catch (Exception el) {
					el.printStackTrace();
				}	
			}
		});
		btnSearchById.setFont(new Font("Georgia", Font.PLAIN, 13));
		btnSearchById.setBounds(247, 300, 115, 25);
		frame.getContentPane().add(btnSearchById);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldId.setText("");
				textFieldName.setText("");
				textFieldSurname.setText("");
				textFieldDepartment.setText("");
				textFieldPosition.setText("");
				textFieldRate.setText("");
				textFieldHours.setText("");
				textArea.setText("");
			}
		});
		btnClear.setFont(new Font("Georgia", Font.PLAIN, 13));
		btnClear.setBounds(247, 336, 115, 25);
		frame.getContentPane().add(btnClear);	
	}
	
	static String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
		InputStream in = entity.getContent();
		StringBuffer out = new StringBuffer();
		int n = 1;
		while (n > 0) {
			byte[] b = new byte[4096];
			n = in.read(b);
			if (n > 0)
				out.append(new String(b, 0, n));
		}
		return out.toString();		
	}
}
