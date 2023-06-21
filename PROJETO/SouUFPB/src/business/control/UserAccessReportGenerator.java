package business.control;

public class UserAccessReportGenerator {

    public void generateHTMLReport(String fileName) {
        UserAccessReport report = new HTMLUserAccessReport();
        report.generateReport(fileName);
    }

    public void generatePDFReport(String fileName) {
        UserAccessReport report = new TXTUserAccessReport();
        report.generateReport(fileName);
    }
}
