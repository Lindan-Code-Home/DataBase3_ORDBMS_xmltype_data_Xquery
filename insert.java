//package cs585;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class insert {
	private static Connection conn;
	public void connectDB(){
		  conn=null;
		try{
			System.out.print("Connecting to DB...");
			conn=DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:sysdba", "LINDAN", "Tld19901107");
			System.out.println("connected !!");
			//conn.close();
			//System.out.println("Close successfully");
		}
		catch(SQLException e){
			System.out.println( "Error while connecting to DB: "+ e.toString() );
			e.printStackTrace();
			return ;
		}
		
	}//end connectDB
	public void importDataBook() throws ParserConfigurationException, SAXException, IOException, SQLException{
		
		File fXmlFile = new File("book.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("Book");
		System.out.println("----------------------------");
		for (int temp = 0; temp <nList.getLength(); temp++) {//nList.getLength();
			
			String s="INSERT INTO BookTable VALUES('";
			Node nNode = nList.item(temp);
	 
			//System.out.println("\nCurrent Element :" + nNode.getNodeName());
			
	 
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	            
	            String author="";
				Element eElement = (Element) nNode;
				NodeList authorList=eElement.getElementsByTagName("Author");
				for(int i=0;i<authorList.getLength();i++){
					//Node authorNode=authorList.item(i);
					//Element authorElement=(Element) authorNode;
					author+="<Author>"+authorList.item(i).getTextContent() +"</Author>";
				
				}
				//System.out.println(author);
				String titleName=eElement.getElementsByTagName("Title").item(0).getTextContent();
				String title=titleName.replaceAll("'", "''");
	            s+=eElement.getAttribute("ID")+"','<Book ID=\""+eElement.getAttribute("ID")+"\"><Title>"+title+"</Title>"
				+author+"<Price>"+eElement.getElementsByTagName("Price").item(0).getTextContent()+"</Price><ISBN>"
				+eElement.getElementsByTagName("ISBN").item(0).getTextContent()+"</ISBN><Publish_Date>"+eElement.getElementsByTagName("Publish_Date").item(0).getTextContent()+"</Publish_Date></Book>')";
	            
	            System.out.println(s);
	            Statement stmt= conn.createStatement();
	            ResultSet rs= stmt.executeQuery(s);
	            stmt.close();
	            
				//System.out.println("Book id : " + eElement.getAttribute("ID"));
				//System.out.println("Book title : " + eElement.getElementsByTagName("Title").item(0).getTextContent());
				//System.out.println("Book Author : " + eElement.getElementsByTagName("Author").item(0).getTextContent());
				//System.out.println("Book Price : " + eElement.getElementsByTagName("Price").item(0).getTextContent());
				//System.out.println("Book ISBN : " + eElement.getElementsByTagName("ISBN").item(0).getTextContent());
				//System.out.println("Publish_Data : " + eElement.getElementsByTagName("Publish_Date").item(0).getTextContent());
	            
			}//end if
	}//end for
	}//end import dataBook
	
	///////////Import Data Review
public void importDataReview() throws ParserConfigurationException, SAXException, IOException, SQLException{
		
		File fXmlFile = new File("review.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("Review");
		System.out.println("----------------------------");
		for (int temp = 0; temp <nList.getLength(); temp++) {//nList.getLength();
			
			String s="INSERT INTO ReviewTable VALUES('";
			Node nNode = nList.item(temp);
	 
			//System.out.println("\nCurrent Element :" + nNode.getNodeName());
			
	 
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	            
	            
				Element eElement = (Element) nNode;
				String des="";
				
				Node firstChild= eElement.getElementsByTagName("Review_Description").item(0);//.getFirstChild();
				if(firstChild!=null){
					String str=eElement.getElementsByTagName("Review_Description").item(0).getTextContent();
					
					String str2=str.replaceAll("'", "''");
					des= "<Review_Description>"+str2+"</Review_Description>";
				}
					//des= "<Review_Description>"+eElement.getElementsByTagName("Review_Description").item(0).getTextContent()+"</Review_Description>";
				
				
				String titleName=eElement.getElementsByTagName("Book_Title").item(0).getTextContent();
				String title=titleName.replaceAll("'", "''");
				
				
				
	            s+=eElement.getAttribute("Review_ID")+"','<Review Review_ID=\""+eElement.getAttribute("Review_ID")+"\"><Book_Title>"+title+"</Book_Title><Rating>"
				+eElement.getElementsByTagName("Rating").item(0).getTextContent()+"</Rating><Reviewer>"+eElement.getElementsByTagName("Reviewer").item(0).getTextContent()+"</Reviewer>"
				+des+"<Review_Date>"+eElement.getElementsByTagName("Review_Date").item(0).getTextContent()+"</Review_Date></Review>')";
	            
	            System.out.println(s); //Review_Description
	            Statement stmt= conn.createStatement();
	            ResultSet rs= stmt.executeQuery(s);
	            stmt.close();
	            
				//System.out.println("Book id : " + eElement.getAttribute("ID"));
				//System.out.println("Book title : " + eElement.getElementsByTagName("Title").item(0).getTextContent());
				//System.out.println("Book Author : " + eElement.getElementsByTagName("Author").item(0).getTextContent());
				//System.out.println("Book Price : " + eElement.getElementsByTagName("Price").item(0).getTextContent());
				//System.out.println("Book ISBN : " + eElement.getElementsByTagName("ISBN").item(0).getTextContent());
				//System.out.println("Publish_Data : " + eElement.getElementsByTagName("Publish_Date").item(0).getTextContent());
	            
			}//end if
	}//end for
	}//end import dataReview
	
	
	
	
	
	
	public static void main(String[] args) throws SQLException, ParserConfigurationException, SAXException, IOException{
		insert t = new insert();
		t.connectDB();
		t.importDataBook();
		t.importDataReview();
	}
}
