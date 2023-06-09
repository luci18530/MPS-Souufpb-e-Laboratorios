package business.control;

public class UserAccessReportGenerator {

    public void generateHTMLReport() {
        UserAccessReport report = new HTMLUserAccessReport();
        report.generateReport();
    }

    public void generatePDFReport() {
        UserAccessReport report = new PDFUserAccessReport();
        report.generateReport();
    }
}
