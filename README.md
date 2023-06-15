## Message service

### 最後更新：2022.06.15

整合 line-bot 及 chatGPT (使用 text-davinci-003)  
包含聊天及照片生成  
聊天：開頭加上 /c  
照片：開頭加上 /i  
  
**記得去 application-param.properties 修改成你自己的連線參數（說明詳見下方執行步驟）**  
**LINE API 規定要 https，推薦使用本地測試工具：ngrok**

#### 其它功能
1. 簡易的 mail 寄送
2. 發送簡訊驗證碼 

mail 及簡訊功能尚未完整測試，不需要可自行移除

## 執行步驟

#### 1. fork project to your own repository
#### 2. clone to local
#### 3. 修改專案參數設定

參數設定檔位置：src/main/resources/env/dev/application-param.properties

**1. OpenAI Secret Key**

	OpenAI 平台註冊／登入（https://platform.openai.com/overview）
	右上方出現個人頭像，點選 View API keys
	點頁面中間的 Create new secret key > 生成後先複製（視窗關掉後將無法再查看）
	用 secret key 替換設定檔中的 {YOUR_OPENAI_KEY}

**2. Line Channel Secret**

	申請 Line Developer 帳號／登入（https://developers.line.biz/en/）
	點 Create Provider
	點選剛剛創的 Provider > Create a new Channel -> 選擇 Messaging API
	輸入必填資料（注意：Channel name = 官方帳號名稱）
	完成後，選擇 Basic Settings 分頁 > 最下方 Channel Secret -> 點選 Issue
	生成 LINE_CHANNEL_SECRET，替換設定檔中的 {YOUR_LINE_CHANNEL_SECRET}

**3. Line Channel Token**

	接續剛剛 Line Secret 頁面 > 選擇 Messaging API 分頁 > 最下方 Channel access token -> 點選 Issue
	生成 LINE_CHANNEL_ACCESS_TOKEN，替換設定檔中的 {YOUR_LINE_CHANNEL_TOKEN}

####3. 專案啟動

**_以下說明為使用 Terminal 之教學，熟悉 IDE 者也可直接使用 IDE 執行_**

前置條件：
1. 具備 <a href="https://www.oracle.com/tw/java/technologies/javase/jdk11-archive-downloads.html">java 11</a>
2. 具備 <a href=https://maven.apache.org/download.cgi>maven 3 或以上</a>
3. 下載 <a href="https://ngrok.com/">ngrok</a>

確認 java 版本：於 Terminal 輸入 java -version  
確認 maven 版本：於 Terminal 輸入 mvn -v  
  
（建議設定成環境變數，也可以直接使用下載下來的 java 及 maven 資料夾執行）

**1. Maven package**

	開啟 Terminal 並跳至專案根目錄路徑
	輸入指令：mvn clean package -Dmaven.test.skip=true -P dev

**2. 執行 Jar**

	Terminal 跳至專案中的 /target 底下
	輸入指令：java -jar message-service.jar
	出現大大的 Spring Boot 代表成功佈署於 8063 port.

**3. 使用 ngrok 將 localhost 短暫發佈成 https**

	原本的 Terminal 不要關掉，重新開一個 Terminal
	跳到 ngrok 資料夾之根目錄
	輸入指令：ngrok http 8063
	出現 Session Status  online 表示成功
	複製 Forwarding 顯示之 https 網址並加上 /callback，如：{your_forwarding_url}/callback（後續 Line 會用到）

**3. 設定 Line Webhook: 使用剛剛複製的 https 網址**

	回到 Line Developer > 選擇前面創的 Provider
	到 Messaging API 分頁 > 最下方的 Webhook URL > 輸入剛剛複製之網址，如：https://test-test-test.ngrok.co/callback
	點選 Verify，跳出 success 表示設定正確（若沒成功請確認網址輸入無誤）
	將下方 Use webhook 打開
	完成，可掃描上方 QR code 開始測試

## 其它專案說明

1. 開發 IDE：IntelliJ IDEA 2022.1
2. 專案建置：Maven 3.8.2
3. 語言版本：JAVA JDK 11
4. 執行：jar as service
5. 主框架：spring boot 2.7.12
6. Line-Bot：5.0.3 
7. 設定檔位置：src.main.resources 
8. 使用 maven build（多環境配置：dev & prod） 
9. 日誌：logback-test.xml－－會分別產生 INFO 及 ERROR 資訊的日誌檔
