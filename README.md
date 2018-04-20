# servermanager
Server Manager 基于 JavaFX 开发的 一款桌面服务器管理软件。
> 目前版本还可靠，仅供参考学习。
### 目前实现功能
- 备份项目应用数据、项目数据库数据，仅支持 MySQL 数据库

### 计划实现功能
- 更可靠的运行
- 支持 Oracle 数据库
- 定时备份项目数据

#### 构建 jar
1. ```src/main/java/META-INF``` 下创建 ```MAINFEST.MF```
    > 文件内容（最后一行为空行）：
    - Manifest-Version: 1.0
    - Main-Class: com.gioov.MainWindow
    - 
    
2. ```Build``` -> ```Build Artifacts``` -> ```Build Or Rebuild```
3. cmd 运行 ```java -jar servermanager.jar```，若报错，请将 ```data``` 文件夹及文件置于运行目录。

#### 构建 exe（不推荐，测试报错）
1. ```src/main/java/META-INF``` 下创建 ```MAINFEST.MF```
    > 文件内容（最后一行为空行）：
    - Manifest-Version: 1.0
    - Main-Class: com.gioov.MainWindow
    - 
    
2. ```Build``` -> ```Build Artifacts``` -> ```Build Or Rebuild```
3. 运行 ```scripts``` 文件夹下的 ```build_exe.cmd```

## 开发环境（jar 已测试正常，exe 测试报错）
- Windows 10 x64
- MySQL 5.7
- Java 8
- Maven 3.5
- SQLite 3

## Snapshots
![servermanager_1](/screenshots/servermanager_1.png)
![servermanager_2](/screenshots/servermanager_2.png)
![servermanager_3](/screenshots/servermanager_3.png)
![servermanager_4](/screenshots/servermanager_4.png)
![servermanager_5](/screenshots/servermanager_5.png)

