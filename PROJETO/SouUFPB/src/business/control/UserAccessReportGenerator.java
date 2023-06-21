package business.control;

import java.io.IOException;

import infra.InfraException;

public class UserAccessReportGenerator {

    public void generateHTMLReport(String fileName) throws IOException, InfraException {
        UserAccessReport report = new HTMLUserAccessReport();
        report.generateReport(fileName);
    }

    public void generatePDFReport(String fileName) throws IOException, InfraException {
        UserAccessReport report = new TXTUserAccessReport();
        report.generateReport(fileName);
    }
}
