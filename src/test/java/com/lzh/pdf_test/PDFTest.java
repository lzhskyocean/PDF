package com.lzh.pdf_test;

import com.itextpdf.text.DocumentException;
import com.lzh.PdfApplicationTests;
import com.lzh.util.PDFUtils;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author lizhenhao
 * @date 2019/07/12 21:06:10
 */
public class PDFTest extends PdfApplicationTests {


    /*
    * 生成文本PDF
    * */
    @Test
    public void writeSimplePDFPText(){

        // String author, String title, String content, String createTime
        String author = "李振豪";
        String title = "第一个PDF";
        String content = "iText是著名的开放源码的站点sourceforge一个项目，是用于生成PDF文档的一个java类库。通过iText不仅可以生成PDF或rtf的文档。";
        String createTime = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());

        try {
            PDFUtils.writeSimplePDFPText(author,title,content,createTime);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }


    }


    /*
    * 生成表格PDF
    * */
    @Test
    public void writeSimplePDFPTable(){

        try {
            PDFUtils.writeSimplePDFPTable();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }


}
