package com.furelise.mem.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.furelise.mem.model.entity.Mem;
import com.furelise.mem.service.MemService;

@Controller
@RequestMapping
public class RegisterController {

    @Autowired
    private MemService memSvc;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostMapping("/register")
    public String doRegister(HttpServletRequest req, Model model, HttpSession session)
            throws ServletException, IOException {

        // ===獲得請求參數===
        String email = req.getParameter("rEmail").trim();
        String name = req.getParameter("rName");
        String birth = req.getParameter("rBirth");
        String tel = req.getParameter("rTel").trim();
        String password = req.getParameter("rPassword");
        String confirmPassword = req.getParameter("rConfirmPassword");

        Mem newMem = new Mem();

        // ===輸入格式驗證錯誤處理===
        List<String> errMsgs = new LinkedList<String>();

        // email錯誤處理
        /*
         * ^[a-zA-Z0-9._%+-]+：以字母、數字、點（.）、下劃線（_）、百分比（%）、加號（+）、減號（-）開頭。
         * @[a-zA-Z0-9.-]+：然後是@符號，後面可以包含字母、數字、點（.）或減號（-）。
         * \\.：然後是一個點（.），需要使用雙斜槓轉義。
         * [a-zA-Z]{2,}$：最後是至少兩個字母結尾。
         */
        String memMailReg = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (email == null || (email.trim().length()) == 0) {
            // 未輸入
            errMsgs.add(" 請輸入電子信箱！");
        } else if (!email.trim().matches(memMailReg)) {
            errMsgs.add(" 電子信箱格式有誤，請修正！");
        } else if (memSvc.findByMemMail(email) != null) {
            // 該電子信箱(帳號)已有註冊紀錄
            errMsgs.add(" 己有帳號使用此電子信箱，請選擇其他電子信箱。");
        }

        // name錯誤處理
        /*
         * 使用捕獲組 () 來表示整個名字的模式。
         * 模式包含大小寫字母、底線 [a-zA-Z_] 和漢字的 Unicode 範圍 \u4e00-\u9fa5。
         * {2,15} 指定捕獲組內容的長度應在 2 到 15 個字符之間。
         */
        String nameReg = "^([a-zA-Z_\u4e00-\u9fa5]{2,20})$";
        if (name == null || (name.trim().length()) == 0) {
            // 未輸入
            errMsgs.add(" 請輸入姓名！");
        } else if (!name.trim().matches(nameReg)) {
            errMsgs.add(" 姓名格式有誤：僅能輸入中、英文字母與底線，且長度必需在2到20之間。");
        }
        String birthStr = birth.replaceAll("/", "-");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(birthStr, formatter);

        // birth錯誤處理
        LocalDate nowDate = LocalDate.now();
        if (birth == null || (birth.trim().length()) == 0) {
            errMsgs.add(" 請選擇生日日期！");
        } else {
            // yyyy/mm/dd轉成yyyy-mm-dd，再從String轉成Date
            if (birthDate.isAfter(nowDate)) {
                // 生日不可以選比今天晚的日期
                errMsgs.add(" 生日輸入有誤：不可選擇晚於今日的日期。");
            }
        }


        // tel錯誤處理
        // [0-9]+：匹配一個或多個（+）數字（0到9）
        String telReg = "^[0-9]+$";
        if (tel == null || (tel.trim().length()) == 0) {
            // 未輸入
            errMsgs.add(" 請輸入電話！");
        } else if (!tel.trim().matches(telReg)) {
            errMsgs.add(" 電話格式有誤：僅能輸入數字。");
        }

        // password錯誤處理
        if (password == null || password.trim().length() == 0) {
            errMsgs.add(" 請輸入密碼！");
        }

        // confirmPassword錯誤處理
        if (confirmPassword == null || confirmPassword.trim().length() == 0) {
            errMsgs.add(" 請再次輸入確認密碼！");
        } else if (!confirmPassword.equals(password)) {
            // 密碼與確認密碼不相同
            errMsgs.add(" 密碼與確認密碼輸入不相同，請修正！");
        }


        // ===資料放入newMem物件===
        newMem.setMemMail(email);
        newMem.setMemName(name);
        newMem.setMemBirth(birthDate); // 已處理String轉Date
        newMem.setMemTel(tel);
        newMem.setMemPass(password);


        // ===資料有誤，forward回註冊頁面===
        if (!errMsgs.isEmpty()) {
//			req.setAttribute("newMem", newMem); 
            model.addAttribute("newMem", newMem);// 含有輸入格式錯誤的mem物件,也存入req以便forward回註冊頁面時顯示
            model.addAttribute("errMsgs", errMsgs);
//			RequestDispatcher failureView = req.getRequestDispatcher("forward:/templates/login.html");
//			failureView.forward(req, res);
            return "login";
        } else {

            // ===資料正確，寄發驗證碼信件給電子信箱===
            String code = memSvc.returnAuthCode(); // 產生驗證碼
            System.out.println("Auth code is: " + code);

            // 存入Redis
            redisTemplate.opsForValue().set("MemMail:" + email, code, Duration.ofMinutes(10));

            memSvc.sendVerificationCode(email, name, code);


            // ===forward至驗證頁完成驗證===
//			model.addAttribute("email", email);
//			model.addAttribute("name", name);
//			model.addAttribute("newMem", newMem);
            session.setAttribute("email", email);
            session.setAttribute("name", name);
            session.setAttribute("newMem", newMem);
//			RequestDispatcher verificationView = req.getRequestDispatcher("forward:/templates/verificationCode_page.html");
//			verificationView.forward(req, res);
            return "verificationCode_page";

        }

    }

}
