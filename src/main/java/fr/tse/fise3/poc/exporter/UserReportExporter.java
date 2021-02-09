package fr.tse.fise3.poc.exporter;

import java.awt.Color;
import java.util.List;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletResponse;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import fr.tse.fise3.poc.domain.Time;
import fr.tse.fise3.poc.domain.User;

public class UserReportExporter {
	
	 List<Time> timesOfUser;
	 
	 User user ;
	 
	 String date;
	 
	 public UserReportExporter(List<Time> timesOfUser, User user, String date) {
	        this.timesOfUser = timesOfUser;
	        this.user = user;
	        this.date = date;
	    }
	 
	    private void writeTableHeader(PdfPTable table) {
	        PdfPCell cell = new PdfPCell();
	        cell.setBackgroundColor(Color.GRAY);
	        cell.setPadding(5);
	         
	        Font font = FontFactory.getFont(FontFactory.HELVETICA);
	        font.setColor(Color.WHITE);
	         
	        cell.setPhrase(new Phrase("Date start", font));
	         
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Date end", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Project", font));
	        table.addCell(cell);
	              
	    }
	     
	    private void writeTableData(PdfPTable table) {
	        for (Time time : timesOfUser) {
	        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");
	            table.addCell(String.valueOf(time.getDateStart().format(formatter)));
	            table.addCell(String.valueOf(time.getDateEnd().format(formatter)));
	            table.addCell(time.getProject().getTitle());
	        }
	    }
	    
	     
	    public void export(HttpServletResponse response) throws DocumentException, IOException {
	        Document document = new Document(PageSize.A4);
	        PdfWriter.getInstance(document, response.getOutputStream());
	         
	        document.open();
	        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font.setSize(18);
	        font.setColor(Color.GRAY);
	         
	        Paragraph p = new Paragraph("MONTHLY REPORT: "+ this.date, font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	         
	        document.add(p);
	        
	        Paragraph p_name = new Paragraph("- NAME  : "+ user.getFirstname()+" "+ user.getLastname(),FontFactory.getFont(FontFactory.COURIER, Font.DEFAULTSIZE, Font.NORMAL));
	        
	        p_name.setSpacingBefore(40);
	        
	        document.add(p_name);
	        
	        Paragraph p_email = new Paragraph("- EMAIL : "+ user.getEmail(), FontFactory.getFont(FontFactory.COURIER, Font.DEFAULTSIZE, Font.NORMAL));
	        
	        p_email.setSpacingBefore(10);
	        p_email.setSpacingAfter(40);
	        document.add(p_email);
	       
	         
	        PdfPTable table = new PdfPTable(3);
	        table.setWidthPercentage(100f);
	        table.setWidths(new float[] {3.5f, 3.5f, 3.5f});
	        table.setSpacingBefore(10);
	         
	        writeTableHeader(table);
	        writeTableData(table);
	         
	        document.add(table);
	         
	        document.close();
	         
	    }

}
