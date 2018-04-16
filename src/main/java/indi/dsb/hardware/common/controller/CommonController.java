package indi.dsb.hardware.common.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import indi.dsb.hardware.common.ApiContant;
import indi.dsb.hardware.common.ResponseCode;
import indi.dsb.hardware.common.utils.DateUtil;
import indi.dsb.hardware.common.utils.Response;

/**
 * @Title: CommonController.java
 * @Package com.honeylovely.center.common.controller
 * @Description: 公共控制器
 * @author dsb
 * @date 2018年3月21日 上午9:32:41
 */
@Controller
@RequestMapping(value = "common")
public class CommonController {

    /**
     * @Title uploadFiles
     * @Description 批量上传文件
     * @param files
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequestMapping("/uploadFiles")
    @ResponseBody
    public Response uploadFiles(MultipartFile[] files) throws IllegalStateException, IOException {

        if (null != files && files.length > 0) {

            StringBuffer urls = new StringBuffer();
            String nowDateStr = DateUtil.formatDate(new Date());
            // 文件存储路径 需要先创建好 F://files//center 文件夹
            String saveFilePath = ApiContant.SAVE_FILE_PATH + "//" + nowDateStr;
            File saveFolder = new File(saveFilePath);
            if (!saveFolder.exists() && !saveFolder.isDirectory()) {
                saveFolder.mkdir();
            }

            for (MultipartFile file : files) {
                // 原始名称
                String oldFileName = file.getOriginalFilename(); // 获取上传文件的原名

                // 上传图片
                if (file != null && oldFileName != null && oldFileName.length() > 0) {
                    // 新的图片名称
                    String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
                    // 新图片
                    File newFile = new File(saveFilePath + "//" + newFileName);
                    // 将内存中的数据写入磁盘
                    file.transferTo(newFile);
                    urls.append(ApiContant.READ_FILE_PATH + "/" + nowDateStr + "/" + newFileName + "|");
                }
            }
            
            urls.deleteCharAt(urls.length() - 1);
            return new Response(ResponseCode.SUCCESS, urls.toString());
        }

        return new Response(ResponseCode.MISS_REQUIRED);
    }
    
}