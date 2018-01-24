package ua.nure.levushevskiy.SummaryTask4.servlet;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import ua.nure.levushevskiy.SummaryTask4.dto.PaymentDTO;
import ua.nure.levushevskiy.SummaryTask4.exception.InitializationException;
import ua.nure.levushevskiy.SummaryTask4.service.impl.PaymentServiceImpl;
import ua.nure.levushevskiy.SummaryTask4.util.EntityConstants;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/savePdfReport")
public class SavePdfReportServlet extends HttpServlet {

    /**
     * An object that contains payment business logic.
     */
    private PaymentServiceImpl paymentService;

    @Override
    public final void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        initPaymentService(context);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PaymentDTO paymentDTO =  paymentService.getById(Integer.parseInt(req.getParameter(EntityConstants.PAYMENT_PARAM)));
        resp.setHeader("Content-Disposition",
                "attachment;filename=payment_ID"+paymentDTO.getIdPayment()+".pdf");
        try {
            Document document = new Document();
            OutputStream os = resp.getOutputStream();
            PdfWriter.getInstance(document, os);
            document.open();

            createReport(paymentDTO, document);

            os.flush();
            document.close();

            os.close();

        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that initializes payment service.
     *
     * @param context - servlet context.
     */
    private void initPaymentService(final ServletContext context) {
        paymentService = (PaymentServiceImpl) context.getAttribute(EntityConstants.PAYMENT_SERVICE);
        if (paymentService == null) {
            throw new InitializationException("Account service is not initialized!");
        }
    }

    private void createReport(PaymentDTO paymentDTO,  Document document) throws Exception {
        Font font = new Font(Font.FontFamily.HELVETICA,
                16, Font.NORMAL);
        font.setColor(BaseColor.BLUE);
        Font font1 = new Font(Font.FontFamily.HELVETICA,
                32, Font.BOLD);
        Font font2 = new Font(Font.FontFamily.HELVETICA,
                16, Font.ITALIC | Font.UNDERLINE);

        // отцентрированный параграф
        Paragraph title = new Paragraph("Report of payment", font1);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(32);
        document.add(title);

        // параграф с добавленным чанком текста
        Paragraph chunkParagraph = new Paragraph();
        chunkParagraph.setFont(font2);
        chunkParagraph.setSpacingAfter(8);
        chunkParagraph.add(new Chunk("Payment ID :"));
        chunkParagraph.setFont(font);
        chunkParagraph.add(new Chunk("\t"+paymentDTO.getIdPayment()));
        document.add(chunkParagraph);

        chunkParagraph = new Paragraph();
        chunkParagraph.setFont(font2);
        chunkParagraph.setSpacingAfter(8);
        chunkParagraph.add(new Chunk("Payment name:"));
        chunkParagraph.setFont(font);
        chunkParagraph.add(new Chunk("\t"+paymentDTO.getPaymentNameDTO().getPaymentName()));
        document.add(chunkParagraph);

        // параграф с текстом
        Paragraph description = new Paragraph("Description: ", font2);
        description.setSpacingAfter(8);
        document.add(description);

        //Description of payment
        Paragraph payment = new Paragraph(paymentDTO.getDescription(), font);
        payment.setSpacingAfter(8);
        document.add(payment);

        // параграф с добавленным чанком текста
        chunkParagraph = new Paragraph();
        chunkParagraph.setFont(font2);
        chunkParagraph.setSpacingAfter(8);
        chunkParagraph.add(new Chunk("Date of payment:"));
        chunkParagraph.setFont(font);
        chunkParagraph.add(new Chunk("\t"+paymentDTO.getDatePayment()));
        document.add(chunkParagraph);

        // параграф с добавленным чанком текста
        chunkParagraph = new Paragraph();
        chunkParagraph.setFont(font2);
        chunkParagraph.setSpacingAfter(8);
        chunkParagraph.add(new Chunk("Total of payment:"));
        chunkParagraph.setFont(font);
        chunkParagraph.add(new Chunk("\t"+paymentDTO.getTotal()));
        document.add(chunkParagraph);

        // картинка, загруженная по URL
        String imageUrl = "C:\\Users\\Serg\\Google Диск\\Epam\\Проект\\Payments\\web\\assets\\images\\stamp.png";
        // Image.getInstance("sample.png")
        Image stamp = Image.getInstance(imageUrl);
        stamp.setAlignment(Element.ALIGN_RIGHT);
        document.add(stamp);

    }

}
