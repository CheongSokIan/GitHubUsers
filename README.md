# GitHub Users
Android exercise for test

## App 內容
- <b>User 列表</b>︰顯示 User 清單，每次會跟據 User Id 再取 20 個資料進行顯示
- <b>User 資訊頁面</b>︰顯示一位 User 的詳細資訊

<a href="https://ibb.co/XFLVQt3"><img src="https://i.ibb.co/XFLVQt3/photo1638728742.jpg" alt="photo1638728742" border="0"></a>
<a href="https://ibb.co/mzJN1FD"><img src="https://i.ibb.co/mzJN1FD/photo1638728742-1.jpg" alt="photo1638728742-1" border="0"></a> 


## MVP 架構
跟據要求，本專案使用 Model - View - Presenter 架構實作︰
- <b>Model</b>︰負責 Remote 和 Local 資料的抓取和更新，最後交由 Repository 返回 in Memory 的 Data
- <b>View</b>︰透過 Fragment 實作 Presenter 的 View Interface 去 trigger UI 更新 
- <b>Presenter</b>︰透過實作 Presenter Interface 來定義行為，主要驅動 Model 和 View 更新，或是把從 Model 層返回的 Data 傳給 View 更新 

<img src="https://github.com/googlesamples/android-architecture/wiki/images/mvp.png">
(圖片來源︰https://github.com/android/architecture-samples/blob/todo-mvp/README.md)


## 使用套件
Android Jetpack(Room, Navigation Component), OkHttp, Retrofit, Moshi, Glide

## 專案設定
- minSdkVersion 21
- targetSdkVersion 31
- compileSdkVersion 31


