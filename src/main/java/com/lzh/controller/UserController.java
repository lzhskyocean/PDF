package com.lzh.controller;

import com.itextpdf.text.DocumentException;
import com.lzh.pojo.UserInfo;
import com.lzh.util.PDFUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @author lizhenhao
 * @date 2019/07/12 21:12:10
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/info")
    public String toAddUserInfo(){

        return "user_info";
    }



    @PostMapping("/info")
    @ResponseBody
    public String addUserInfo(UserInfo userInfo){

        try {
            PDFUtils.writeSimplePDFPTable(userInfo);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return "PDF导出成功!";
    }


}
