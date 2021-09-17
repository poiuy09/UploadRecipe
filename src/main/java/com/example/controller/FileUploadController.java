package com.example.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.form.UploadForm;

//コメント変更

/***
 * ファイルアップロードのコントローラークラス
 */
@Controller
public class FileUploadController {
	/**
     * 初期処理なくした
     * @return
     */

    /**
     * ファイルアップロード処理
     * @param uploadForm
     * @return
     */
    @PostMapping("/upload")
    String upload(Model model, UploadForm uploadForm) {
    	model.addAttribute("uploadForm", new UploadForm());
        //フォームで渡されてきたアップロードファイルを取得
        MultipartFile multipartFile = uploadForm.getMultipartFile();

        //アップロード実行処理メソッド呼び出し
        uploadAction(multipartFile);

        //リダイレクト
        return "redirect:/";
    }

    /**
     * アップロード実行処理
     * @param multipartFile
     */
    private void uploadAction(MultipartFile multipartFile) {
        //ファイル名取得
    	String fileName = multipartFile.getOriginalFilename();

        //格納先のフルパス ※事前に格納先フォルダ「UploadTest」をCドライブ直下に作成しておく 変更するのはここでしょ!!
        //Path filePath = Paths.get("C:/UploadTest/" + fileName);
        Path filePath = Paths.get("C:\\pleiades\\workspace\\UploadRecipe\\src\\main\\resources\\static\\img\\spot" + fileName);

        try {
            //アップロードファイルをバイト値に変換
            byte[] bytes  = multipartFile.getBytes();

            //バイト値を書き込む為のファイルを作成して指定したパスに格納
            OutputStream stream = Files.newOutputStream(filePath);

            //ファイルに書き込み
            stream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
