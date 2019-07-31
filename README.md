# 新闻后台管理系统

### 项目说明

平台用户分为两三种：普通用户、普通管理员、超级管理员

1）普通用户：没有账户，只能阅读新闻

2）普通管理员：新闻的增删改查

3）超级管理员：用户和新闻的增删改查，超级管理员多了管理用户的功能、菜单权限

### 小组分工

| 姓名   | 学号       | GitHub账号        | 具体功能分工                                                 |
| :----- | ---------- | ----------------- | :----------------------------------------------------------- |
| 付淅   | 2016012063 | 2016012063fuxi    | 数据增删改查CRUD、排序、数据字典、多表关联、分页             |
| 刘哲   | 2016012083 | LiuZhe2016012083  | RBAC权限模型，不同用户角色类型的资源访问权限及管理员对用户的访问权限的管理 |
| 柯招坤 | 2016012007 | ClausKE           | 文件上传下载、树形结构                                       |
| 黄鹤   | 2016012080 | HuangHe2016012080 | 负责系统界面设计，设计系统人机交互的界面效果和整体布局。     |
| 唐杰   | 2016012058 | tangjie2016012058 | 使用Div+css并结合Javascript负责产品的前端开发和页面制作，完成浏览器端的制作 |

### 项目结构

![QQ图片20190627181354](/src/main/webapp/include/img/readmepic/QQ图片20190627181354.png)

![QQ图片20190627183345](/src/main/webapp/include/img/readmepic/QQ图片20190627183345.png)

![QQ图片20190627183845](/src/main/webapp/include/img/readmepic/QQ图片20190627183845.png)

### 类说明

**action**

MenuAct:菜单权限控制类，根据用户的角色来展示相对应的菜单

NewsAct：新闻控制层类，调用服务层进行增删改查

NtypeAct:栏目控制层类，调用服务层进行增删改查

RoleAct:角色控制层类，调用服务层进行增删改查

UploadAct:上传文件类

UserAct:用户控制层类

**bean**   

实体类：对应数据库字段

**Dao**

数据访问，通用的增删改查方法

**service**

服务层，调用dao层

**Utils**

GsonUtils:json格式转换工具包

LoginFilter:登陆过滤器

MyStrutsFilter:struts过滤器

MyUtils:自定义工具包，比较常用的方法

**配置文件**

applicationContext.xml: spring配置文件

Config.json:富文本的配置文件

Log4j.properties:日志配置文件

Struts.xml：struts配置文件

### 默认的用户名和密码

|            | 用户名 | 密码   |
| ---------- | ------ | ------ |
| 超级管理员 | admin  | admin  |
| 普通管理员 | ddd    | 123456 |

### 项目截图

![QQ图片20190627185606](/src/main/webapp/include/img/readmepic/QQ图片20190627185606.png)

点开新闻标题

![QQ图片20190627185657](/src/main/webapp/include/img/readmepic/QQ图片20190627185657.png)

点击登陆按钮

![QQ图片20190627185730](/src/main/webapp/include/img/readmepic/QQ图片20190627185730.png)

输入超级管理员的用户名和密码

![QQ图片20190627185135](/src/main/webapp/include/img/readmepic/QQ图片20190627185135.png)

查询功能：输入关键字

![QQ图片20190627185508](/src/main/webapp/include/img/readmepic/QQ图片20190627185508.png)

点击修改按钮

![QQ图片20190627185350](/src/main/webapp/include/img/readmepic/QQ图片20190627185350.png)

点击删除按钮

![QQ图片20190627190221](/src/main/webapp/include/img/readmepic/QQ图片20190627190221.png)

![QQ图片20190627190238](/src/main/webapp/include/img/readmepic/QQ图片20190627190238.png)

添加新闻：手动输入，也可以通过上传附件的形式

![QQ图片20190627185545](/src/main/webapp/include/img/readmepic/QQ图片20190627185545.png)

![QQ图片20190627190423](/src/main/webapp/include/img/readmepic/QQ图片20190627190423.png)

查看用户列表，可以进行修改、删除操作

![QQ图片20190627190540](/src/main/webapp/include/img/readmepic/QQ图片20190627190540.png)

![QQ图片20190627190604](/src/main/webapp/include/img/readmepic/QQ图片20190627190604.png)

添加用户，输入用户信息

![QQ图片20190627190627](/src/main/webapp/include/img/readmepic/QQ图片20190627190627.png)

查询、修改、删除新闻分类信息

![QQ图片20190627191249](/src/main/webapp/include/img/readmepic/QQ图片20190627191249.png)

![QQ图片20190627191315](/src/main/webapp/include/img/readmepic/QQ图片20190627191315.png)

添加栏目分类

![QQ图片20190627191343](/src/main/webapp/include/img/readmepic/QQ图片20190627191343.png)

查询、删除管理员角色信息

![QQ图片20190627191556](/src/main/webapp/include/img/readmepic/QQ图片20190627191556.png)

添加管理员可访问的栏目资源权限

![QQ图片20190627191626](/src/main/webapp/include/img/readmepic/QQ图片20190627191626.png)

用普通管理员的账号登陆，普通管理员没有用户管理的权限，只能进行新闻内容的修改、删除、添加

![QQ图片20190627191742](/src/main/webapp/include/img/readmepic/QQ图片20190627191742.png)

### 注意事项

项目clone下来后应修改applicationContext.xml文件中的字段：

```
<property name="username" value=""></property>
<property name="password" value=""></property>
```

### 





