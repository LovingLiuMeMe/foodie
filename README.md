### 事务的传播行为
```
事务传播 - Propagation
     REQUIRED: 使用当前的事务，如果当前没有事务，则自己新建一个事务，子方法是必须运行在一个事务中的；
               如果当前存在事务，则加入这个事务，成为一个整体。
               举例：领导没饭吃，我有钱，我会自己买了自己吃；领导有的吃，会分给你一起吃。
     SUPPORTS: 如果当前有事务，则使用事务；如果当前没有事务，则不使用事务。
               举例：领导没饭吃，我也没饭吃；领导有饭吃，我也有饭吃。
     MANDATORY: 该传播属性强制必须存在一个事务，如果不存在，则抛出异常
                举例：领导必须管饭，不管饭没饭吃，我就不乐意了，就不干了（抛出异常）
     REQUIRES_NEW: 如果当前有事务，则挂起该事务，并且自己创建一个新的事务给自己使用；
                   如果当前没有事务，则同 REQUIRED
                   举例：领导有饭吃，我偏不要，我自己买了自己吃
     NOT_SUPPORTED: 如果当前有事务，则把事务挂起，自己不适用事务去运行数据库操作
                    举例：领导有饭吃，分一点给你，我太忙了，放一边，我不吃
     NEVER: 如果当前有事务存在，则抛出异常
            举例：领导有饭给你吃，我不想吃，我热爱工作，我抛出异常
     NESTED: 如果当前有事务，则开启子事务（嵌套事务），嵌套事务是独立提交或者回滚；
             如果当前没有事务，则同 REQUIRED。
             但是如果父事务提交，则会携带子事务一起提交。
             如果父事务回滚，则子事务会一起回滚。相反，子事务异常，则父事务可以回滚或不回滚（try-catch 捕获）。
             举例：领导决策不对，老板怪罪，领导带着小弟一同受罪。小弟出了差错，领导可以推卸责任。
```
### 事务的隔离级别
Spring  事务的隔离级别:
1. `ISOLATION_DEFAULT`：这是一个 PlatfromTransactionManager 默认的隔离级别，
使用数据库默认的事务隔离级别.
另外四个与 JDBC 的隔离级别相对应
2. `ISOLATION_READ_UNCOMMITTED`： 这是事务最低的隔离级别， 它充许令外一个
事务可以看到这个事务未提交的数据。
这种隔离级别会产生脏读，不可重复读和幻像读。
3. `ISOLATION_READ_COMMITTED`：保证一个事务修改的数据提交后才能被另外一
个事务读取。另外一个事务不能读取该事务未提交的数。
4. `ISOLATION_REPEATABLE_READ`： 这种事务隔离级别可以防止脏读， 不可重复读。
但是可能出现幻像读。
它除了保证一个事务不能读取另一个事务未提交的数据外， 还保证了避免下面的
情况产生(不可重复读)。
5. `ISOLATION_SERIALIZABLE` 这是花费最高代价但是最可靠的事务隔离级别。事务
被处理为顺序执行,除了防止脏读，不可重复读外，还避免了幻像读。


**1. 脏读** ：脏读就是指当一个事务正在访问数据，并且对数据进行了修改，而这种修改还没有提交到数据库中，这时，另外一个事务也访问这个数据，然后使用了这个数据。
**2. 不可重复读** ：是指在一个事务内，多次读同一数据。在这个事务还没有结束时，另外一个事务也访问该同一数据。那么，在第一个事务中的两次读数据之间，由于第二个事务的修改，那么第一个事务两次读到的的数据可能是不一样的。这样就发生了在一个事务内两次读到的数据是不一样的，因此称为是不可重复读。例如，一个编辑人员两次读取同一文档，但在两次读取之间，作者重写了该文档。当编辑人员第二次读取文档时，文档已更改。原始读取不可重复。如果只有在作者全部完成编写后编辑人员才可以读取文档，则可以避免该问题。
**3. 幻读** : 是指当事务不是独立执行时发生的一种现象，例如第一个事务对一个表中的数据进行了修改，这种修改涉及到表中的全部数据行。同时，第二个事务也修改这个表中的数据，这种修改是向表中插入一行新数据。那么，以后就会发生操作第一个事务的用户发现表中还有没有修改的数据行，就好象发生了幻觉一样。例如，一个编辑人员更改作者提交的文档，但当生产部门将其更改内容合并到该文档的主复本时，发现作者已将未编辑的新材料添加到该文档中。如果在编辑人员和生产部门完成对原始文档的处理之前，任何人都不能将新材料添加到文档中，则可以避免该问题。

## springBoot 打包为War包
1.更改聚合工程中的web工程打包方式由`jar` -> `war`
```xml
<packaging>war</packaging>
```
2.聚合工程的父工程排除`springboot`自带的`tomcat`
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <!-- 打包war [2] 移除自带内置tomcat -->
    <exclusions>
        <exclusion>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <groupId>org.springframework.boot</groupId>
        </exclusion>
     </exclusions>
</dependency>
```
3.在聚合工程中的父工程添加依赖
```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>servlet-api</artifactId>
    <scope>provided</scope>
</dependency>
```
4.在子web工程中添加war项目类型的启动类
```java
package cn.lovingliu;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Author：LovingLiu
 * @Description: SpringBoot web项目的War启动类启动类
 * @Date：Created in 2019-12-29
 */
public class StartWarApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 指向Application这个springBoot启动类
        return builder.sources(Application.class);
    }
}
```
5.在聚合工程的父工程中的pom.xml文件中添加配置
```xml
<build>
    <plugins>
        <plugin>
            <artifactId>maven-war-plugin</artifactId>
            <version>2.6</version>
            <configuration>
                <!--如果想在没有web.xml文件的情况下构建WAR，请设置为false。-->
                <failOnMissingWebXml>false</failOnMissingWebXml>
            </configuration>
        </plugin>
    </plugins>
</build>
```
6.将打包好的war包,扔到webapps下即可
访问 http://49.235.110.134:8080/foodie-dev-api-1.0-SNAPSHOT/swagger-ui.html  (foodie-dev-api-1.0-SNAPSHOT 是打包的项目名称)
但是，强烈不建议使用SpringBoot发布war包，原因有三：

1、默认的SpringBoot支持静态资源以jar包的方式发布部署

2、前后端分离，后端使用SpringBoot开发，前段就无所谓了，完全可以不依赖SpringBoot

3、在服务端加入Swagger插件，直接通过接口做测试，无需web界面
### 注意
使用外部Tomcat部署访问的时候，`application.properties`(或者`application.yml`)中配置的
`server.port=`
`server.servlet.context-path=`
将失效，请使用Tomcat的端口。

### 后端设置cookie报错
我的代码正在使用tomcat 8.5版本之后上`cookie`报错：
为此cookie指定了无效的域[.mydomain]。

我发现Rfc6265CookieProcessor是在tomcat 8最新版本中引入的。



修改 tomcat 的配置文件 `context.xml` ，指定 `CookieProcessor` 为 `org.apache.tomcat.util.http.LegacyCookieProcessor`，具体配置如下：
```xml
<Context>
    <CookieProcessor className="org.apache.tomcat.util.http.LegacyCookieProcessor" />
</Context>
``` 
SpringBoot 的解决方式
在 springboot 启动类中增加内嵌 Tomcat 的配置 Bean，如下代码：
```java
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    // Tomcat Cookie 处理配置 Bean
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieProcessorCustomizer() {
        return (factory) -> factory.addContextCustomizers(
            (context) -> context.setCookieProcessor(new LegacyCookieProcessor()));
    }
}
```