<h1 style="text-align: center">格子日志后台管理系统</h1>
<div style="text-align: center">
基于eladmin https://github.com/elunez/eladmin.git
前台：https://github.com/Kahen/diary-vue
</div>


#### 部署到docker 
- 修改dockerHost
```shell script
mvn clean package docker:build -DpushImage
docker run -p 8000:8000 --name diary-system \
-d diary/eladmin-system 
```
