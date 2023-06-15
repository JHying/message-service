## 說明

### 2022.06.15

整合 line-bot 及 chatgpt (使用 text-davinci-003)  
包含聊天及照片生成  
聊天：開頭加上 /c  
照片：開頭加上 /i  
  
**記得去 application-param.properties 修改成你自己的連線參數**  
**LINE API 規定要 https，推薦使用本地測試工具：ngrok**

#### 其它功能
1. 簡易的 mail 寄送
2. 發送簡訊驗證碼 
3. 以上尚未測試，不需要自行移除

## 其它專案說明

1. 開發 IDE：IntelliJ IDEA 2022.1
2. 專案建置：Maven 3.8.2
3. 語言版本：JAVA JDK 11
4. 執行：jar as service
5. 主框架：spring boot 2.7.12
6. Line-Bot：5.0.3 
7. 設定檔位置：src.main.resources 
8. 使用 maven build（多環境配置：dev & prod） 
9. 日誌：logback-test.xml－－分別產生 INFO 及 ERROR 資訊的日誌檔
