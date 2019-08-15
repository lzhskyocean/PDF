package com.lzh.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lzh.pojo.UserInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author lizhenhao
 * @date 2019/07/11 19:47:36
 */
public class PDFUtils {

    private static BaseFont bfChinese;  // BaseFont对象

    static {
        try {
            // 创建BaseFont对象，指明字体，编码方式,是否嵌入
            //bfChinese=BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            bfChinese = BaseFont.createFont("C:\\Windows\\Fonts\\simkai.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 导出一个文本PDF
     *
     * @param author
     * @param title
     * @param content
     * @param createTime
     */
    public static void writeSimplePDFPText(String author, String title, String content, String createTime) throws FileNotFoundException, DocumentException {

        // 1.创建Document对象
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);

        // 2.创建一个书写器PdfWriter
        String filePath = "D:\\FileDemo";
        StringBuffer fileName = new StringBuffer(UUID.randomUUID().toString().replace("-", ""));
        fileName.append(".pdf");
        File file = new File(filePath, fileName.toString());

        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));

        // 3.打开文档
        document.open();

        // 4.向文档中添加数据[段落/章节]
        // 创建章节
        Font headFont = new Font(bfChinese);  // 文档标题
        Font keyFont = new Font(bfChinese);  // 文档关键字
        Font textFont = new Font(bfChinese);  // 文档内容

        // 文档的章节
        headFont.setColor(BaseColor.BLACK);
        Paragraph chapterTitle = new Paragraph(title, headFont);
        chapterTitle.setAlignment(1); // 0-靠左(默认)  1-居中  2-靠右
        Chapter chapter = new Chapter(chapterTitle, 1);
        chapter.setNumberDepth(0);  // 0则不进行编号 1则进行标号(默认) 如果numberdepth为更高（例如x> 1），则将显示x-1个父项的数量。

        //keyFont.setSize(14);
        //keyFont.setColor(BaseColor.GRAY);
        Font authorFont = new Font(bfChinese);
        authorFont.setSize(14);
        authorFont.setColor(BaseColor.GRAY);
        Paragraph chapterAuthor = new Paragraph("---" + author, authorFont);
        chapterAuthor.setAlignment(2);
        chapter.add(chapterAuthor);

        // 设置部分小标题[可以通过数组或者集合遍历添加]
        keyFont.setColor(BaseColor.BLUE);
        keyFont.setSize(16);
        Paragraph sectionTitle1 = new Paragraph("小标题1", keyFont);
        Paragraph sectionTitle2 = new Paragraph("小标题2", keyFont);
        Paragraph sectionTitle3 = new Paragraph("小标题3", keyFont);

        // 设置部分内容
        textFont.setSize(12);
        //textFont.setColor(BaseColor.PINK);
        Paragraph chapterContent1 = new Paragraph(content, textFont);
        sectionTitle1.add(chapterContent1);
        chapter.addSection(sectionTitle1, 0);  // 0不编号


        textFont.setSize(12);
        //textFont.setColor(BaseColor.RED);
        Paragraph chapterContent2 = new Paragraph(content, textFont);
        sectionTitle2.add(chapterContent2);
        chapter.addSection(sectionTitle2);


        // 设置部分内容
        textFont.setSize(12);
        textFont.setColor(BaseColor.RED);
        Paragraph chapterContent3 = new Paragraph(content, textFont);
        chapterContent2.setAlignment(1);  // 居中
        sectionTitle3.add(chapterContent3);
        chapter.addSection(sectionTitle3);

        // 设置时间
        Font createTimeFont = new Font(bfChinese);
        createTimeFont.setSize(10);
        createTimeFont.setColor(BaseColor.BLACK);
        Paragraph chapterCreateTime = new Paragraph(createTime);
        chapterCreateTime.setAlignment(2);
        chapter.add(chapterCreateTime);

        // 将内容添加到文件
        document.add(chapter);

        System.out.println(fileName + " 文件已生成!");

        // 5.关闭资源
        document.close();
        pdfWriter.close();


    }


    /**
     * 导出一个表格PDF
     */
    public static void writeSimplePDFPTable() throws IOException, DocumentException {

        // 1.创建Document对象
        Document document = new Document(PageSize.A4, 0, 0, 50, 0);

        // 2.创建一个书写器PdfWriter
        String filePath = "D:\\FileDemo";
        StringBuffer fileName = new StringBuffer(UUID.randomUUID().toString().replace("-", ""));
        fileName.append(".pdf");
        File file = new File(filePath, fileName.toString());

        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));

        // 设置PDF的属性
        document.addTitle("信息统计表");
        // 作者
        document.addAuthor("lzh");
        // 主题
        document.addSubject("信息");
        // 关键字
        document.addKeywords("信息统计");
        // 创建时间
        document.addCreationDate();

        // 设置用户密码
        String userPassword = "123456";
        // 设置拥有者密码
        String ownerPassword = "654321";

        //byte[] userPassword, byte[] ownerPassword, int permissions(权限), int encryptionType(加密类型)
        pdfWriter.setEncryption(userPassword.getBytes(),ownerPassword.getBytes(),pdfWriter.ALLOW_PRINTING,pdfWriter.STANDARD_ENCRYPTION_128);

        // 3.打开文档
        document.open();

        Font titleFont = new Font(bfChinese);
        titleFont.setSize(20F);
        titleFont.setColor(BaseColor.BLUE);
        Paragraph title = new Paragraph("信息统计表\n\n\n", titleFont);
        title.setAlignment(1);  // 居中
        document.add(title);

        // 创建PdfPTable对象
        PdfPTable pdfPTable = new PdfPTable(7);  // 设置表格列数:7

        pdfPTable.setWidths(new float[]{100F, 100F, 100F, 100F, 100F, 100F, 80F});  // 设置每列的宽度

        // 添加表格内容
        Font keyFont = new Font(bfChinese, 15F, Font.NORMAL);
        Font contentFont = new Font(bfChinese, 12F, Font.BOLD);
        //contentFont.setSize(8F);


        pdfPTable.addCell(addPdfPCell("姓名", keyFont));
        pdfPTable.addCell(addPdfPCell("", contentFont));
        pdfPTable.addCell(addPdfPCell("性别", keyFont));
        pdfPTable.addCell("男");  // 无法设置内容,只是用来添加单元格
        pdfPTable.addCell(addPdfPCell("出生年月", keyFont));
        pdfPTable.addCell("");

        // 添加图片
        Image image = Image.getInstance("D:\\FileDemo\\table.png");
        image.scaleAbsolute(50, 70);  // 设置图片的宽高
        PdfPCell cell = new PdfPCell(image);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);  // 设置单元格内容水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置单元格内容垂直居中
        cell.setRowspan(3);
        pdfPTable.addCell(cell);

        pdfPTable.addCell(addPdfPCell("学历", keyFont));
        pdfPTable.addCell("");
        pdfPTable.addCell(addPdfPCell("婚否", keyFont));
        pdfPTable.addCell("");
        pdfPTable.addCell(addPdfPCell("民族", keyFont));
        pdfPTable.addCell("");


        pdfPTable.addCell(addPdfPCell("专业", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 2));
        pdfPTable.addCell(addPdfPCell("毕业学校", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 2));


        pdfPTable.addCell(addPdfPCell("健康状况", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 2));
        pdfPTable.addCell(addPdfPCell("户籍所在地", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 3));


        pdfPTable.addCell(addPdfPCell("政治面貌", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 2));
        pdfPTable.addCell(addPdfPCell("身份证号", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 3));

        pdfPTable.addCell(addPdfPCell("参加工作时间", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 2));
        pdfPTable.addCell(addPdfPCell("待遇要求", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 3));

        pdfPTable.addCell(addPdfPCell("联系电话", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 2));
        pdfPTable.addCell(addPdfPCell("电子邮件", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 3));

        pdfPTable.addCell(addPdfPCell("联系地址", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 6));

        pdfPTable.addCell(addPdfPCell("现工作所在地", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 6));

        pdfPTable.addCell(addPdfPCell("离职原因", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 6));


        // 简历栏
        pdfPTable.addCell(mergeRow("简历", keyFont, 6));
        pdfPTable.addCell(addPdfPCell("起止时间", keyFont));
        pdfPTable.addCell(mergeCol("学习/工作单位", keyFont, 3));
        pdfPTable.addCell(mergeCol("专业/职位", keyFont, 2));
        // 遍历添加空单元格
        for (int i = 0; i < 5; i++) {
            pdfPTable.addCell("");
            pdfPTable.addCell(mergeCol("", contentFont, 3));
            pdfPTable.addCell(mergeCol("", contentFont, 2));
        }

        // 家庭栏
        pdfPTable.addCell(mergeRow("家庭状况", keyFont, 6));
        pdfPTable.addCell(addPdfPCell("姓名", keyFont));
        pdfPTable.addCell(addPdfPCell("关系", keyFont));
        pdfPTable.addCell(addPdfPCell("年龄", keyFont));
        pdfPTable.addCell(addPdfPCell("文化程度", keyFont));
        pdfPTable.addCell(mergeCol("现工作单位", keyFont, 2));
        // 遍历添加空的单元格
        for (int i = 0; i < 5; i++) {
            pdfPTable.addCell("");
            pdfPTable.addCell("");
            pdfPTable.addCell("");
            pdfPTable.addCell("");
            pdfPTable.addCell(mergeCol("", contentFont, 2));
        }

        // 特别提示栏
        pdfPTable.addCell(addPdfPCell("特别提示", keyFont));
        PdfPCell pCell = mergeCol("1. 本人承诺保证所填写资料真实。 \n2. 保证遵守公司招聘有关规程和国家有关法规。  \n3. 请填写好招聘登记表，带齐照片、学历、职称证书的有效证件及相关复印件。\n", contentFont, 6);
        pCell.setHorizontalAlignment(Element.ALIGN_LEFT);  // 靠左
        pdfPTable.addCell(pCell);


        // 将表添加到文档
        document.add(pdfPTable);

        // 4.关闭资源
        document.close();
        pdfWriter.close();


        System.out.println(fileName + "的文件已经生成!");


    }


    /*
    * 数据提交
    * */
    public static void writeSimplePDFPTable(UserInfo userInfo) throws IOException, DocumentException {

        // 1.创建Document对象
        Document document = new Document(PageSize.A4, 0, 0, 50, 0);

        // 2.创建一个书写器PdfWriter
        String filePath = "D:\\FileDemo";
        StringBuffer fileName = new StringBuffer(UUID.randomUUID().toString().replace("-", ""));
        fileName.append(".pdf");
        File file = new File(filePath, fileName.toString());

        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));

        // 设置PDF的属性
        document.addTitle("信息统计表");
        // 作者
        document.addAuthor("lzh");
        // 主题
        document.addSubject("信息");
        // 关键字
        document.addKeywords("信息统计");
        // 创建时间
        document.addCreationDate();

        // 设置用户密码
        String userPassword = "123456";
        // 设置拥有者密码
        String ownerPassword = "654321";

        //byte[] userPassword, byte[] ownerPassword, int permissions(权限), int encryptionType(加密类型)
        pdfWriter.setEncryption(userPassword.getBytes(),ownerPassword.getBytes(),pdfWriter.ALLOW_PRINTING,pdfWriter.STANDARD_ENCRYPTION_128);

        // 3.打开文档
        document.open();

        Font titleFont = new Font(bfChinese);
        titleFont.setSize(20F);
        titleFont.setColor(BaseColor.BLUE);
        Paragraph title = new Paragraph("信息统计表\n\n\n", titleFont);
        title.setAlignment(1);  // 居中
        document.add(title);

        // 创建PdfPTable对象
        PdfPTable pdfPTable = new PdfPTable(7);  // 设置表格列数:4

        pdfPTable.setWidths(new float[]{100F, 100F, 100F, 100F, 100F, 100F, 80F});  // 设置每列的宽度

        // 添加表格内容
        Font keyFont = new Font(bfChinese, 15F, Font.NORMAL);
        Font contentFont = new Font(bfChinese, 12F, Font.BOLD);
        //contentFont.setSize(8F);


        pdfPTable.addCell(addPdfPCell("姓名", keyFont));
        pdfPTable.addCell(addPdfPCell(userInfo.getName(),contentFont));
        pdfPTable.addCell(addPdfPCell("性别", keyFont));
        pdfPTable.addCell(addPdfPCell(userInfo.getGender(),contentFont));  // 无法设置内容,只是用来添加单元格
        pdfPTable.addCell(addPdfPCell("出生年月", keyFont));
        pdfPTable.addCell(addPdfPCell(userInfo.getDate(),contentFont));

        // 添加图片
        Image image = Image.getInstance("D:\\FileDemo\\table.png", true);
        image.scaleAbsolute(50, 70);  // 设置图片的宽高
        PdfPCell cell = new PdfPCell(image);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);  // 设置单元格内容水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置单元格内容垂直居中
        cell.setRowspan(3);
        pdfPTable.addCell(cell);

        pdfPTable.addCell(addPdfPCell("学历", keyFont));
        pdfPTable.addCell(addPdfPCell(userInfo.getStudyLevel(),contentFont));
        pdfPTable.addCell(addPdfPCell("婚否", keyFont));
        pdfPTable.addCell(addPdfPCell(userInfo.getMarry(),contentFont));
        pdfPTable.addCell(addPdfPCell("民族", keyFont));
        pdfPTable.addCell(addPdfPCell(userInfo.getNationality(),contentFont));


        pdfPTable.addCell(addPdfPCell("专业", keyFont));
        pdfPTable.addCell(mergeCol(userInfo.getProfession(), contentFont, 2));
        pdfPTable.addCell(addPdfPCell("毕业学校", keyFont));
        pdfPTable.addCell(mergeCol(userInfo.getSchool(), contentFont, 2));


        pdfPTable.addCell(addPdfPCell("健康状况", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 2));
        pdfPTable.addCell(addPdfPCell("户籍所在地", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 3));


        pdfPTable.addCell(addPdfPCell("政治面貌", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 2));
        pdfPTable.addCell(addPdfPCell("身份证号", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 3));

        pdfPTable.addCell(addPdfPCell("参加工作时间", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 2));
        pdfPTable.addCell(addPdfPCell("待遇要求", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 3));

        pdfPTable.addCell(addPdfPCell("联系电话", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 2));
        pdfPTable.addCell(addPdfPCell("电子邮件", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 3));

        pdfPTable.addCell(addPdfPCell("联系地址", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 6));

        pdfPTable.addCell(addPdfPCell("现工作所在地", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 6));

        pdfPTable.addCell(addPdfPCell("离职原因", keyFont));
        pdfPTable.addCell(mergeCol("", contentFont, 6));


        // 简历栏
        pdfPTable.addCell(mergeRow("简历", keyFont, 6));
        pdfPTable.addCell(addPdfPCell("起止时间", keyFont));
        pdfPTable.addCell(mergeCol("学习/工作单位", keyFont, 3));
        pdfPTable.addCell(mergeCol("专业/职位", keyFont, 2));
        // 遍历添加空单元格
        for (int i = 0; i < 5; i++) {
            pdfPTable.addCell("");
            pdfPTable.addCell(mergeCol("", contentFont, 3));
            pdfPTable.addCell(mergeCol("", contentFont, 2));
        }

        // 家庭栏
        pdfPTable.addCell(mergeRow("家庭状况", keyFont, 6));
        pdfPTable.addCell(addPdfPCell("姓名", keyFont));
        pdfPTable.addCell(addPdfPCell("关系", keyFont));
        pdfPTable.addCell(addPdfPCell("年龄", keyFont));
        pdfPTable.addCell(addPdfPCell("文化程度", keyFont));
        pdfPTable.addCell(mergeCol("现工作单位", keyFont, 2));
        // 遍历添加空的单元格
        for (int i = 0; i < 5; i++) {
            pdfPTable.addCell("");
            pdfPTable.addCell("");
            pdfPTable.addCell("");
            pdfPTable.addCell("");
            pdfPTable.addCell(mergeCol("", contentFont, 2));
        }

        // 特别提示栏
        pdfPTable.addCell(addPdfPCell("特别提示", keyFont));
        PdfPCell pCell = mergeCol("1. 本人承诺保证所填写资料真实。 \n2. 保证遵守公司招聘有关规程和国家有关法规。  \n3. 请填写好招聘登记表，带齐照片、学历、职称证书的有效证件及相关复印件。\n", contentFont, 6);
        pCell.setHorizontalAlignment(Element.ALIGN_LEFT);  // 靠左
        pdfPTable.addCell(pCell);


        // 将表添加到文档
        document.add(pdfPTable);

        // 4.关闭资源
        document.close();
        pdfWriter.close();


        System.out.println(fileName + "的文件已经生成!");


    }





    /**
     * 合并行单元格,并填充内容
     *
     * @param content
     * @param font
     * @param row
     * @return
     */
    public static PdfPCell mergeRow(String content, Font font, int row) {

        // 创建单元格对象,并将内容和字体传入
        PdfPCell pdfPCell = new PdfPCell(new Paragraph(content, font));

        // 设置单元格内容居中
        pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);  // 设置单元格水平对齐方式
        pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置单元格的垂直对齐方式

        // 合并单元格:将当前列的该单元格的row行合并成一个单元格
        pdfPCell.setRowspan(row);

        return pdfPCell;
    }


    /**
     * 合并列单元格,并填充内容
     *
     * @param content
     * @param font
     * @param col
     * @return
     */
    public static PdfPCell mergeCol(String content, Font font, int col) {

        // 创建单元格对象,并将内容和字体传入
        PdfPCell pdfPCell = new PdfPCell(new Paragraph(content, font));

        // 设置单元格的高度
        pdfPCell.setMinimumHeight(25F);

        // 设置单元格内容居中
        pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);  // 水平对齐
        pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置单元格的垂直对齐方式

        // 合并单元格:将当前行的该单元格的col列合并成一个单元格
        pdfPCell.setColspan(col);

        return pdfPCell;
    }


    /**
     * 给单元格填充内容
     *
     * @param content
     * @param font
     * @return
     */
    public static PdfPCell addPdfPCell(String content, Font font) {

        PdfPCell pdfPCell = new PdfPCell(new Paragraph(content, font));

        pdfPCell.setMinimumHeight(25F);

        pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);

        pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        return pdfPCell;
    }

}
