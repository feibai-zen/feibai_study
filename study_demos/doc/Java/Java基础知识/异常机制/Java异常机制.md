# 异常机制

## 概述
   异常类型
   自定义异常 

## 本质
   Java采用面向对象的方式来处理异常的。本质：当程序出现错误，程序安全退出的机制。
   
## 异常处理过程
   1.抛出异常
    在执行一个方法时，如果发生异常，则这个方法生成代表该异常的一个对象，停止当前执行路径，并把异常对象提交给JRE。
   
   2.捕获异常 
    JRE得到该异常后，寻找相应的代码来处理该异常。JRE在方法的调用栈中查找，从生成异常的方法开始回溯，直到找到相应的一场处理代码为止。
   
## 异常分类
    所有异常对象都是派生于java.lang.Throwable类
    
### Error
    是程序无法处理的错误，Error表明系统JVM已经处于不可恢复的崩溃状态中。应用程序本身无法克服和恢复的一种严重问题。
    
### Exception

#### 编译器已检查的异常：CheckedException
   例如，网络断线，硬盘空间不够，发生这样的异常后，程序不应该死掉。编译器强制普通异常必须 try..catch 处理或用 throws 声明继续抛给上层调用方
   法处理，所以普通异常也称为checked异常。
    IOException
    SQLException
    
#### 运行时异常：RuntimeException
   任何运行时异常，都是程序员的疏忽大意。系统异常可以处理也可以不处理，所以，编译器不强制用 try..catch 处理或用throws声明，所以系统异常也称
   为unchecked异常。
   
   - 1）java.lang.NullPointerException 
   - 2）java.lang.ClassNotFoundException 
   - 3）java.lang.NumberFormatException 
   - 4）java.lang.IndexOutOfBoundsException 
   - 5）java.lang.IllegalArgumentException 
   - 6）java.lang.ClassCastException


## 使用异常机制注意事项
   - 1.要避免使用异常处理代替错误处理，会降低程序的清晰性，影响性能。
   - 2.处理异常不可以代替简单测试---只有在异常情况下使用异常机制。
   - 3.不要进行小粒度的异常处理---应该将整个任务包装在一个try块中。
   - 4.异常往往在最高层处理


## 自定义异常
   - 1.遇到JDK无法描述清楚想表达的异常
   - 2.自定义异常类只需要从Exception类或者它的子类派生一个子类即可
   - 3.如果继承Exception类，则为受检查异常，必须对其进行处理。如果不想处理，可以让自定义异常继承运行时异常RuntimeException
   - 4.习惯上自定义异常应该包含2个构造器：1）默认构造器；2）带有详细信息的构造器

```java
class IllegalAgeException extends RuntimeException {
   	public IllegalAgeException() {
   
   	}
   
   	public IllegalAgeException(String message) {
   		super(message);
   	}
}
```
