package com.techouts;

import com.structure.Student;
import com.structure.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/students/export")
public class ExportExcelServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final StudentDAO dao = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Student> students = dao.findAll();

        // Create an in-memory Excel workbook (.xlsx)
        try (Workbook wb = new XSSFWorkbook()) {
            Sheet sheet = wb.createSheet("Students");

            // Header style (bold)
            Font headerFont = wb.createFont();
            headerFont.setBold(true);
            CellStyle headerStyle = wb.createCellStyle();
            headerStyle.setFont(headerFont);

            // Optional: numeric format for CGPA
            CellStyle cgpaNumeric = wb.createCellStyle();
            DataFormat fmt = wb.createDataFormat();
            cgpaNumeric.setDataFormat(fmt.getFormat("0.00")); // 2 decimal places

            // Header row
            String[] cols = { "Roll", "Name", "CGPA", "Gender" };
            Row header = sheet.createRow(0);
            for (int i = 0; i < cols.length; i++) {
                Cell c = header.createCell(i);
                c.setCellValue(cols[i]);
                c.setCellStyle(headerStyle);
            }

            // Data rows
            int r = 1;
            for (Student s : students) {
                Row row = sheet.createRow(r++);

                // Roll (assuming int/long)
                row.createCell(0).setCellValue(s.getRollNumber());

                // Name
                row.createCell(1).setCellValue(safe(s.getName()));

                // CGPA: try to write as number; fallback to string
                Cell cgpaCell = row.createCell(2);
                Object cgpa = s.getCgpa(); // adjust if your getter returns primitive/double
                if (cgpa instanceof Number) {
                    cgpaCell.setCellValue(((Number) cgpa).doubleValue());
                    cgpaCell.setCellStyle(cgpaNumeric);
                } else {
                    // If stored as string in DB
                    String cgpaStr = String.valueOf(cgpa);
                    try {
                        double val = Double.parseDouble(cgpaStr);
                        cgpaCell.setCellValue(val);
                        cgpaCell.setCellStyle(cgpaNumeric);
                    } catch (Exception e) {
                        cgpaCell.setCellValue(cgpaStr);
                    }
                }

                // Gender
                row.createCell(3).setCellValue(safe(s.getGender()));
            }

            // Auto-size columns
            for (int i = 0; i < cols.length; i++) sheet.autoSizeColumn(i);

            // Prepare response headers for download
            String filename = "students.xlsx";
            resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            resp.setHeader("Content-Disposition", contentDisposition(filename));

            // Write workbook to response stream
            wb.write(resp.getOutputStream());
            // Stream closed by container
        }
    }

    private String safe(String s) {
        return s == null ? "" : s;
    }

    // Proper Content-Disposition for UTF-8 filenames
    private String contentDisposition(String filename) {
        String encoded = URLEncoder.encode(filename, StandardCharsets.UTF_8).replace("+", "%20");
        return "attachment; filename=\"" + filename + "\"; filename*=UTF-8''" + encoded;
    }
}
