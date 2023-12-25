package com.furelise.reader;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.furelise.product.model.Product;
import com.furelise.product.model.ProductService;

@Controller
public class DBGifReaderController {

    private final ProductService productService;

    // 使用构造函数注入 ProductService
    public DBGifReaderController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/DBGifReader")
    @ResponseBody
    public byte[] getGifImage(@RequestParam("pID") Integer pID, @RequestParam("image") Integer image) {
        try {
            Product product = productService.getProductById(pID);

            // 根据image的值选择相应的图像字段
            if (image == 1) {
                return product.getPImage1();
            } else if (image == 2) {
                return product.getPImage2();
            } else if (image == 3) {
                return product.getPImage3();
            } else {
                // 如果image的值不在1到3的范围内，可能需要处理错误或提供默认图像
                return getDefaultImage();
            }
        } catch (Exception e) {
            // 处理异常，例如记录日志
            return getDefaultImage();
        }
    }

    private byte[] getDefaultImage() {
        // 从默认图像文件中读取并返回图像数据
        // 注意：这里需要根据你的项目结构和配置来确定默认图像的路径
        // 这里简化为直接返回一个默认图像的字节数组
        // 实际项目中可能需要根据实际情况调整
        return new byte[0];
    }
}
