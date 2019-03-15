# SpringBoot 整合 ssm + jsp页面

第一次上传代码，有什么说的不对或者不仔细的请指出 慢慢学习进步

这个项目使用eclipse来写的 用Springboot来整合ssm用jsp页面来表示，虽然Springboot官方并不太支持用jsp页面来表示，但我这里提供jsp来给一些有需求的人 比如我自己~.

下面是我的文件目录 一个application类，一个controller，一个映射的mapper和mapper.xml,和一个基本类 和 手动创建的main中的webapp...jsp

![img](file:///C:\Users\HP\AppData\Roaming\Tencent\Users\1033515525\QQ\WinTemp\RichOle\RGZZ5$2SF8PMLCF{}~`{J22.png) 

## 首先创建一个maven项目

### maven的pom.xml配置





```java
 <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>cn.my</groupId>
  <artifactId>EnglishC</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.0.RELEASE</version>
        <relativePath/>
    </parent>
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    <java.version>1.8</java.version>
</properties>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>1.3.2</version>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <!--添加jsp依赖 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
    </dependency>
    <dependency>
        <groupId>org.apache.tomcat.embed</groupId>
        <artifactId>tomcat-embed-jasper</artifactId>
    </dependency>
    <dependency>
		<groupId>com.alibaba</groupId>
		<artifactId>druid</artifactId>
		<version>1.1.6</version>
	</dependency>
</dependencies>
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
</project>
```
## resources的准备

### 创立application.properties文件

```
spring.mvc.view.prefix=/WEB-INF/JSP/ 	对应寻找jsp目录 但是在实际目录中还有一个父目录创建为webapp
spring.mvc.view.suffix=.jsp				将.jsp后缀可以省略	

spring.datasource.driverClassName = com.mysql.jdbc.Driver 
spring.datasource.url=jdbc:mysql://localhost:3306/englishcuseUnicode=true&characterEncoding=utf-8   
spring.datasource.username = root
spring.datasource.password = XUEzhiqian990519
上面四条是对应的数据库mysql连接的基本条件  
spring.datasource.type =com.alibaba.druid.pool.DruidDataSource 这是自定义的druid数据源
mybatis.mapper-locations=classpath:mapper/*.xml 将mapper的定义在mapper目录下的所有xml结尾的文本
mybatis.type-aliases-package=cn.my.application.pojo 这个是基本类的位置
```

### 创立对应的mapper目录

既然有对应的mapper映射的位置那就肯定有mapper目录

![1552638774974](C:\Users\HP\AppData\Local\Temp\1552638774974.png)

我的mapper目录是放在resources下的 所有mapper.xml也放在这里

## 目录结构以及类的内容

![1552638849719](C:\Users\HP\AppData\Local\Temp\1552638849719.png)

然后根据上图创建相同的目录结构，cn.my.application负责启动类，其他的包都要在cn.my.application.*创建方便springboot的调用 首先是application.java的内容

### application



```
package cn.my.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.my.application.mapper")
public class application {
	public static void main(String[] args) {
		SpringApplication.run(application.class, args);
	}
}

```

@SpringBootApplication表示这是一个Springboot应用

@MapperScan（...）表示要扫描的daomapper的位置

再就是main函数中创建一个SpringApplication.run（application.class,args）;

### controller

首先创建controller层的话，因为我们是用jsp来显示，那么对应的我们也要配置相应的jsp页面，目录如下

![1552639051652](C:\Users\HP\AppData\Local\Temp\1552639051652.png)

下面是jsp的页面内容，传进来的参会在controller层代码看见

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${yuedu.yuedu}</h1>
<h3>答案1：${yuedu.answer1}</h1>
<h3>答案2：${yuedu.answer2}</h1>
<h3>答案3：${yuedu.answer3}</h1>
<h3>答案4：${yuedu.answer4}</h1>
</body>
</html>
```

pom.xml也引入了相关的包，application.properties中也写入了jsp的配置，那么目录的就要根据webapp/WEB-INF/JSP/来创建，但在properties配置中只用写/WEB-INF/JSP/的位置，因为pom.xml的依赖会自动来到main中找到webapp来表示这是一个web项目，所以webapp的目录是必不可少的，紧接着就是根据properties中的配置找到对应的jsp。

jsp的位置写好了，在上面写的就是controller的java类内容

```
package cn.my.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.my.application.mapper.yuedulijieMapper;
import cn.my.application.pojo.yuedulijie;

@Controller
public class controller {
	@Autowired
	private yuedulijieMapper yuedumapper;
	@RequestMapping("/index")
		public ModelAndView  itemsList() {
			Integer id = 1;
			yuedulijie yuedu = yuedumapper.SelectById(id);
			ModelAndView mav = new ModelAndView("index");
			mav.addObject("yuedu", yuedu);
			return mav;
	}
}
```

@Controller表示这是controller层，那么springboot就会自动根据注释找到这里将它表示为controller层，

这个yuedulijieMapper是在dao层声明的接口，用@Autowired自动装配 

@RequestMapping("/index")是将下面这个代码块的内容界面地址后缀声明为/index

用的是springmvc返回ModelAndView 在这个代码块中进行对页面逻辑的操作与变量添加

将ModelAndView变量创建时new的字符串跟需要显示的jsp页面的名称一样，对应的就是那个jsp页面，最后返回这个ModelAndView对象，就是将这个jsp页面返回到一开始的“/index”界面

### dao

下面是yuedulijieMapper.java的代码

```
package cn.my.application.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.my.application.pojo.yuedulijie;

@Mapper
public interface yuedulijieMapper {
	
	public yuedulijie SelectById(@Param("id")Integer id);
}

```

dao层中声明了@Mapper表示这是mapper接口，跟最开始的application.java的mapperscan的注解有了对应，在接口中声明的方法会在对应映射的mapper.xml用到如下

下面是yuedulijieMapper.xml

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="cn.my.application.mapper.yuedulijieMapper" >
    <select id="SelectById"  resultType="cn.my.application.pojo.yuedulijie" parameterType="java.lang.Integer">
        SELECT * FROM yuedulijie WHERE id = #{id};
    </select>

</mapper>
```

select表示这个查方法类别，其余的还有update、delete、insert等

mapper namespace对应的就是所映射的mapper的位置， select中id对应的是对应的方法，比如select中对应一个方法函数，那你再来一个insert中也要对应一个方法函数，一个块对应一个，resultType表示返回的结果类型，

parameterType表示传进来的参数类型，像#{id}就是在接口中@Param("id")声明的

mapper这一块是直接对数据库进行操作，相关的数据库信息也已经在properties中声明

### pojo

基本类肯定跟数据库表是对应的，我这里是简单基本类yuedulijie.java与简单表

```
package cn.my.application.pojo;

public class yuedulijie {
	private Integer id;
	private String yuedu;
	private String answer1;
	private String answer2;
	private String answer3;
	public yuedulijie() {
		super();
	}
	public yuedulijie(Integer id, String yuedu, String answer1, String answer2, String answer3, String answer4,
			String answer5) {
		super();
		this.id = id;
		this.yuedu = yuedu;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.answer5 = answer5;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getYuedu() {
		return yuedu;
	}
	public void setYuedu(String yuedu) {
		this.yuedu = yuedu;
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	public String getAnswer4() {
		return answer4;
	}
	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}
	public String getAnswer5() {
		return answer5;
	}
	public void setAnswer5(String answer5) {
		this.answer5 = answer5;
	}
	private String answer4;
	private String answer5;
}

```



### 数据库表

下面是数据库表与信息代码![1552640045224](C:\Users\HP\AppData\Local\Temp\1552640045224.png)

```
CREATE TABLE `NewTable` (
`id`  int(11) NOT NULL ,
`yuedu`  varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '阅读理解的题目' ,
`answer1`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`answer2`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`answer3`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`answer4`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`answer_true`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
ROW_FORMAT=DYNAMIC
;


```

记得注意properties中和mapper中对应的数据库与表的信息是否正确

## 项目结果

配置完成这些就完成了Springboot整合SSM+JSP页面 的基本实现结果结果如下图显示

![1552640150288](C:\Users\HP\AppData\Local\Temp\1552640150288.png)

如果自己想做出更复杂的逻辑以及功能，就可以在这些代码上进行添加。感谢观看 记得点个赞~有不对的评论私聊指出都行 一起进步！