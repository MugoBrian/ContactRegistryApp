package com.contactregistry.ContactRegistryApp.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;
import java.util.stream.Stream;

import com.contactregistry.ContactRegistryApp.dao.ContactDAO;
import com.contactregistry.ContactRegistryApp.model.Contact;
import com.contactregistry.ContactRegistryApp.util.DBUtil;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String format = req.getParameter("format"); // "pdf", "csv", "xls"
        String county = req.getParameter("county");
        
        try (Connection conn = DBUtil.getConnection()) {
            ContactDAO contactDao = new ContactDAO(conn);
            List<Contact> contacts = (county != null && !county.isEmpty())
                    ? contactDao.getContactsByCounty(county)
                    : contactDao.getAllContacts();
            if (format == null || format.isEmpty()) {
                            List<String> counties = contactDao.getAllCounties();
                            req.setAttribute("contacts", contacts);
                            req.setAttribute("counties", counties);
                            req.setAttribute("selectedCounty", county);
                            req.getRequestDispatcher("report.jsp").forward(req, resp);
                        } else {
                switch (format.toLowerCase()) {
                    case "pdf" -> generatePDF(contacts, resp);
                    case "csv" -> generateCSV(contacts, resp);
                    case "xls" -> generateExcel(contacts, resp);
                    default -> resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unsupported format: " + format);
                }
            }
        } catch (Exception e) {
            throw new ServletException("Error generating report", e);
        }
    }

    private void generatePDF(List<Contact> contacts, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/pdf");
        resp.setHeader("Content-Disposition", "attachment; filename=contacts.pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, resp.getOutputStream());
        document.open();

        Font headerFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        document.add(new Paragraph("Contact Report", headerFont));
        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{2, 2, 2, 2, 2, 1.5f, 2});

        addTableHeader(table);
        for (Contact c : contacts) {
            table.addCell(c.getFirstName() + " " + c.getLastName());
            table.addCell(Integer.toString(c.getIdNumber()));
            table.addCell(c.getPhoneNumber());
            table.addCell(c.getEmailAddress());
            table.addCell(c.getDateOfBirth().toString());
            table.addCell(c.getGender());
            table.addCell(c.getCounty());
        }

        document.add(table);
        document.close();
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Full Name", "ID Number", "Phone", "Email", "Date of Birth", "Gender", "County")
                .forEach(column -> {
                    PdfPCell header = new PdfPCell();
                    header.setPhrase(new Phrase(column));
                    table.addCell(header);
                });
    }

    private void generateCSV(List<Contact> contacts, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/csv");
        resp.setHeader("Content-Disposition", "attachment; filename=contacts.csv");

        try (OutputStream out = resp.getOutputStream()) {
            String header = "Full Name,ID Number,Phone,Email,Date of Birth,Gender,County\n";
            out.write(header.getBytes());
            
            for (Contact c : contacts) {
                String line = String.format("\"%s %s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"\n",
                        c.getFirstName(), c.getLastName(), c.getIdNumber(), c.getPhoneNumber(),
                        c.getEmailAddress(), c.getDateOfBirth(), c.getGender(), c.getCounty());
                out.write(line.getBytes());
            }
            
            out.flush();
        }
    }

    private void generateExcel(List<Contact> contacts, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/vnd.ms-excel");
        resp.setHeader("Content-Disposition", "attachment; filename=contacts.xls");

        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Contacts");
            
            Row header = sheet.createRow(0);
            String[] columns = {"Full Name", "ID Number", "Phone", "Email", "Date of Birth", "Gender", "County"};
            for (int i = 0; i < columns.length; i++) {
                header.createCell(i).setCellValue(columns[i]);
            }
            
            int rowIdx = 1;
            for (Contact c : contacts) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(c.getFirstName() + " " + c.getLastName());
                row.createCell(1).setCellValue(c.getIdNumber());
                row.createCell(2).setCellValue(c.getPhoneNumber());
                row.createCell(3).setCellValue(c.getEmailAddress());
                row.createCell(4).setCellValue(c.getDateOfBirth().toString());
                row.createCell(5).setCellValue(c.getGender());
                row.createCell(6).setCellValue(c.getCounty());
            }
            
            workbook.write(resp.getOutputStream());
        }
    }
}
