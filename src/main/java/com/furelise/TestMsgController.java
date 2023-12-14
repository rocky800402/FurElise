package com.furelise;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


//FIXME: 記得砍掉我，我只是一個範例, http://localhost:8080/msg
@Controller
public class TestMsgController {

    //TODO:
    @GetMapping(value = "/msg")
    // 將Model 作為Controller 的引數，由Spring 框架自動創建並作為參數傳入
    public String model(Model model) {
        // 設定傳遞資料
        model.addAttribute("message", "Hello thymeleaf. (using Model)");
        // 返回值指定頁面路徑
        return "test-msg";
    }


}
