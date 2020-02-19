package com.zjj.aisearch;

/**
 * @program: AISearch
 * @description: Demo
 * @author: zjj
 * @create: 2019-10-11 19:24:32
 **/
public class Demo {
    /**
     * spring:ioc,aop,事务控制
     *
     * mybatis:
     *
     * springmvc:拦截器
     *
     *
     * java基础
     * 次要:
     * mysql
     * vue,js
     *
     * thymeleaf:
     *
     * springboot
     *
     *
     *
     *
     *
     */


    /**
     *
     * 一个".java"源文件中是否可以包括多个类（不是内部类）？有什么限制？
     *
     * 有些jdk源码都不止一个类,比如ClassLoader,就有两个类,ClassLoader和SystemClassLoaderAction
     *
     * Java有没有goto?
     *
     * goto是java语言中的保留字，目前还没有在java中使用。
     *
     * 说说&和&&的区别。
     *
     * &&有短路的功能
     *
     * 在JAVA中如何跳出当前的多重嵌套循环？
     *
     * 定义一个标号,break,从没用过
     *
     * switch语句能否作用在byte上，能否作用在long上，能否作用在String上?
     *
     *  可作用于char byte short int及其包装类,不可long duuble float boolean及包装类,此外,1.5枚举,1.7字符串
     *
     *  jdk新特性
     *  jdk5:装箱拆箱,枚举,静态导入,可变参,泛型,增强for,注解
     *  jdk7:switch可用string,try-with-resources 自动关闭资源
     *  jdk8:lambda表达式,接口可以有默认方法,函数式接口,只包含一个方法的接口,配合lambda使用,::关键字传递方法或构造器引用,多重注解,扩展注解,stream函数式操作流
     *
     *
     * short s1= 1; s1 = sl + 1（s1+1是int类型，而等号左边的是short类型，所以需要强转）;有什么错? short s1 = 1; s1 += 1;有什么错?(,编译器自动转换,没有错)
     *
     *  char型变量中能不能存贮一个中文汉字?为什么?
     * 可以,都是两字节
     *
     * 用最有效率的方法算出2乘以8等於几?
     *
     * 2<<3
     *
     * 使用final关键字修饰一个变量时，是引用不能变，还是引用的对象不能变？
     *
     * 是指引用变量不能变，引用变量所指向的对象中的内容还是可以改变的。
     *
     * 静态变量和实例变量的区别
     * 静态变量前面要加static，实例变量不用,静态属于类,可直接用,
     *
     *
     * 是否可以从一个static方法内部发出对非static方法的调用？
     * 不可
     *
     * Integer与int的区别
     *
     * 1、Integer是int的包装类，int则是java的一种基本数据类型

     2、Integer变量必须实例化后才能使用，而int变量不需要

     3、Integer实际是对象的引用，当new一个Integer时，实际上是生成一个指针指向此对象；而int则是直接存储数据值 。

     4、Integer的默认值是null，int的默认值是0
     *
     *
     * Math.round(11.5)等於多少?Math.round(-11.5)等於多少?
     *
     * ceil的英文意义是天花板，该方法就表示向上取整,Math.ceil(11.3)的结果为12,,Math.ceil(-11.3)的结果是-11
     * floor的英文意义是地板，该方法就表示向下取整，所以，Math.floor(11.6)的结果为11,Math.floor(-11.6)的结果是-12；
     * 最难掌握的是round方法，它表示“四舍五入”，算法为Math.floor(x+0.5)，即将原来的数字加上0.5后再向下取整，
     * Math.round(11.5)的结果为12，Math.round(-11.5)的结果为-11。
     *
     * Overload和Override的区别？Overloaded的方法是否可以改变返回值的类型?
     * Overload是重载的意思，
     * Override是是重写。
     * 重载的方法是可以改变返回值的类型。
     *
     * 接口是否可继承接口,?可以
     * 抽象类是否可实现(implements)接口?  可以
     * 抽象类是否可继承具体类(concreteclass)?  可
     * 抽象类中是否可以有静态的main方法？可以,ok
     *
     * 1、编译时多态（又称静态多态）
     *  方法重载都是编译时多态。

     2、运行时多态（又称动态多态）

     重载表现出两种多态性，当引用指向本类实例时，为编译时多态，指向子类,为运行时多态。
     *
     * abstractclass和interface语法上有什么区别?
     *
     * 接口更纯粹,
     *
     * 抽象类可以实现部分或全部方法
     *
     * abstract的method是否可同时是static,是否可同时是native，是否可同时是synchronized?
     *
     * 都不可以，因为abstract申明的方法是要求子类去实现的，abstract只是告诉你有这样一个接口，你要去实现，至于你的具体实现可以是native和synchronized，也可以不是，抽象方法是不关心这些事的，所以写这两个是没有意义的。然后，static方法是不会被覆盖的，而abstract方法正是要子类去覆盖它，所以也是没有意义的。所以，总的来说，就是java语法不允许你这样做，事实上，也没有意义这样做。
     ————————————————
     版权声明：本文为CSDN博主「绝地反击T」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     原文链接：https://blog.csdn.net/u012110719/article/details/48803749
     *
     *
     * java 接口的修饰符：public abstract（interface 本身就是抽象的，加不加 abstract 都一样）
     *
     *
     * 接口中字段的修饰符：public static final（默认不写）
     *
     *
     * 接口中方法的修饰符:public abstract（默认不写）
     *
     *
     * 内部类重未用过不了解
     *
     * String s = "Hello";s = s + "world!";这两行代码执行后，原始的String对象中的内容到底变了没有？
     *
     *  没有，因为String类是不可变类（immutable class）。不可变类，顾名思义就是说类的实例是不可被修改的。实例的信息是在创建的时候提供，并且在整个生命周期中都不可改变。在这段代码中，s原来指向一个String对象，内容是“hello”，然后我们对s进行了+操作，那么s所指向的那个对象是否发生了改变呢？答案是没有。这时，s不指向原来那个对象了，而指向了另一个String对象，内容为”helloworld！",原来那个对象还存在内存中，只是s这个引用变量不再指向他了。
     *  通过上面的说明，我们很容易得出一个结论，如果经常对字符串进行各种各样的修改，或者说，不可预见的修改，那么使用String来代表字符串的话会引起很大的内存开销。因为，String对象建立后不能改变，所以对于每一个不同的字符串，都需要一个String对象来表示。这时，应该考虑使用StringBuffer类，他允许修改，而不是每个不同的字符串都要生成一个新的对象。并且，这两种类的对象转换十分容易。
     *
     *  ArrayList和Vector的区别
     *  Vector重未用过
     *
     *  HashMap和Hashtable的区别
     *  Hashtable从未用过,
     *
     *
     *   1、继承的父类不同


     Hashtable继承自Dictionary类，而HashMap继承自AbstractMap类。但二者都实现了Map接口。

     2、线程安全性不同
     javadoc中关于hashmap的一段描述如下：此实现不是同步的。线程不安全

     hashtable,线程安全

     4、key和value是否允许null值

     Hashtable中，key和value都不允许出现null值。
     HashMap，key和value都可出现null值。

     HashMap中，null可以作为键，这样的键只有一个；可以有一个或多个键所对应的值为null。当get()方法返回null值时，可能是 HashMap中没有该键，也可能使该键所对应的值为null。因此，在HashMap中不能由get()方法来判断HashMap中是否存在某个键， 而应该用containsKey()方法来判断。
     *
     * List和 Map区别?
     *
     * List：是存储单列数据的集合，存储的数据是有序并且是可以重复的
     Map：存储双列数据的集合，通过键值对存储数据，存储 的数据是无序的，Key值不能重复，value值可以重复

     *
     * List,Set, Map是否继承自Collection接口?
     *
     * List，Set是，Map不是。
     *
     *说出ArrayList,Vector,LinkedList的存储性能和特性
     * ArrayList,插入慢,查找快,LinkedList,正相反
     *
     *
     *
     * 、Collection和Collections的区别。
     *
     * 服务于Java的Collection框架。的工具类
     *
     *
     * 你所知道的集合类都有哪些？主要方法？
     *
     * String s = new String("xyz");创建了几个StringObject？是否可以继承String类?
     *
     * 不可
     * 两个。第一个对象是字符串常量"xyz" 第二个对象是new String()的时候产生的，在堆中分配内存给这个对象，只不过这个对象的内容是指向字符串常量"xyz" 另外还有一个引用s，指向第二个对象。这是一个变量，在栈中分配内存。
     *
     * String和StringBuffer的区别
     *
     * 1、运算速度比较（通常情况下）：StringBuilder > StringBuffer > String
     *
     * StringBuilder（非线程安全）StringBuffer（线程安全的）
     *
     * String：适用于少量的字符串操作。

     StringBuilder：适用于单线程下在字符串缓冲区进行大量操作。

     StringBuffer：适用于多线程下在字符串缓冲区进行大量操作。

     *
     *
     * String s = “a” + “b” + “c” + “d” + “e”; 问此语句共创建了几个对象？
     答：就创建了一个
     *
     *
     *try {}里有一个return语句，那么紧跟在这个try后的finally{}里的code会不会被执行，什么时候被执行，在return前还是后?
     *
     * 会执行,在return前执行
     *
     *
     * final

     　　在java中，final可以用来修饰类，方法和变量（成员变量或局部变量）。下面将对其详细介绍。

     1.1 修饰类

     　　当用final修饰类的时，表明该类不能被其他类所继承。当我们需要让一个类永远不被继承，此时就可以用final修饰，但要注意：

     final类中所有的成员方法都会隐式的定义为final方法。

     1.2 修饰方法

     使用final方法的原因主要有两个：

     　　(1) 把方法锁定，以防止继承类对其进行更改。
     *
     *
     * final成员变量表示常量，只能被赋值一次，赋值后其值不再改变。类似于C++中的const。
     *
     * final修饰一个成员变量（属性），必须要显示初始化。这里有两种初始化方式，一种是在变量声明的时候初始化；第二种方法是在声明变量的时候不赋初值，但是要在这个变量所在的类的所有的构造函数中对这个变量赋初值。
     *
     * finalize()是在java.lang.Object里定义的，也就是说每一个对象都有这么个方法。这个方法在gc启动，该对象被回收的时候被调用。其实gc可以回收大部分的对象（凡是new出来的对象，gc都能搞定，一般情况下我们又不会用new以外的方式去创建对象），所以一般是不需要程序员去实现finalize的。
     特殊情况下，需要程序员实现finalize，当对象被回收的时候释放一些资源，比如：一个socket链接，在对象初始化时创建，整个生命周期内有效，那么就需要实现finalize，关闭这个链接。
     　　使用finalize还需要注意一个事，调用super.finalize();
     *
     *
     * 运行时异常与一般异常有何异同？
     *
     * 1.定义不同，运行时异常都是RuntimeException类及其子类异常，如NullPointerException、IndexOutOfBoundsException等。一般异常是RuntimeException以外的异常，类型上都属于Exception类及其子类。

     2.处理方法不同，运行时异常是不检查异常，程序中可以选择捕获处理，也可以不处理。对于一般异常，JAVA编译器强制要求用户必需对出现的这些异常进行catch并处理，否则程序就不能编译通过。
     *
     * 3.发生原因不同，运行时异常一般是由程序逻辑错误引起的，程序应该从逻辑角度尽可能避免这类异常的发生。面对这种异常不管我们是否愿意，只能自己去写一大堆catch块去处理可能的异常。
     *
     * Java提供了两类主要的异常:runtime exception和checked exception。checked 异常也就是我们经常遇到的IO异常，以及SQL异常都是这种异常。对于这种异常，JAVA编译器强制要求我们必需对出现的这些异常进行catch。所以，面对这种异常不管我们是否愿意，只能自己去写一大堆catch块去处理可能的异常。这类异常一般是外部错误,例如试图从文件尾后读取数据等,这并不是程序本身的错误,而是在应用环境中出现的外部错误.



     但是另外一种异常：runtime exception，也称运行时异常，我们可以不处理。当出现这样的异常时，总是由虚拟机接管。比如：我们从来没有人去处理过NullPointerException异常，它就是运行时异常，并且这种异常还是最常见的异常之一。RuntimeException体系包括错误的类型转换、数组越界访问和试图访问空指针等等.处理RuntimeException的原则是:假如出现RuntimeException,那么一定是程序员的错误.例如,可以通过检查数组下标和数组边界来避免数组越界访问异常.



     出现运行时异常后，系统会把异常一直往上层抛，一直遇到处理代码。如果没有处理块，到最上层，如果是多线程就由Thread.run()抛出，如果是单线程就被main()抛出。抛出之后，如果是线程，这个线程也就退出了。如果是主程序抛出的异常，那么这整个程序也就退出了。运行时异常是Exception的子类，也有一般异常的特点，是可以被Catch块处理的。只不过往往我们不对他处理罢了。也就是说，你如果不对运行时异常进行处理，那么出现运行时异常之后，要么是线程中止，要么是主程序终止。

     如果不想终止，则必须扑捉所有的运行时异常，决不让这个处理线程退出。队列里面出现异常数据了，正常的处理应该是把异常数据舍弃，然后记录日志。不应该由于异常数据而影响下面对正常数据的处理。在这个场景这样处理可能是一个比较好的应用，但并不代表在所有的场景你都应该如此。如果在其它场景，遇到了一些错误，如果退出程序比较好，这时你就可以不太理会运行时异常，或者是通过对异常的处理显式的控制程序退出。异常处理的目标之一就是为了把程序从异常中恢复出来。


     *
     *
     * error和exception有什么区别?
     *
     * Exception 和 Error 都是继承了 Throwable 类，在 Java 中只有 Throwable 类型的实例才可以被抛出（throw）或者捕获（catch），它是异常处理机制的基本组成类型。
     Exception 和 Error 体现了 Java 平台设计者对不同异常情况的分类。Exception 是程序正常运行中，可以预料的意外情况，可能并且应该被捕获，进行相应处理。
     Error 是指在正常情况下，不大可能出现的情况，绝大部分的 Error 都会导致程序（比如 JVM 自身）处于非正常的、不可恢复状态。既然是非正常情况，所以不便于也不需要捕获，常见的比如 OutOfMemoryError 之类，都是 Error 的子类。
     Exception 又分为可检查（checked）异常和不检查（unchecked）异常，可检查异常在源代码里必须显式地进行捕获处理，这是编译期检查的一部分。前面我介绍的不可查的 Error，是 Throwable 不是 Exception。
     不检查异常就是所谓的运行时异常，类似 NullPointerException、ArrayIndexOutOfBoundsException 之类，通常是可以编码避免的逻辑错误，具体根据需要来判断是否需要捕获，并不会在编译期强制要求。
     ————————————————
     版权声明：本文为CSDN博主「迷途的小狼」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     原文链接：https://blog.csdn.net/LouisQMei/article/details/80764150
     *
     *
     * 请写出你最常见到的5个runtime exception。 常见异常见：http://www.runoob.com/java/java-exceptions.html

     5个RuntimeException:

     NullPionterException

     ArrayIndexOutOfBoundsException

     StringIndexOutOfBoundsException

     ClassCastException

     NumberFormatException
     *
     *
     *
     * Java 中堆和栈有什么区别？
     *
     *
     *
     * 最主要的区别就是栈内存用来存储局部变量和方法调用。
     而堆内存用来存储Java中的对象。无论是成员变量，局部变量，还是类变量，它们指向的对象都存储在堆内存中
     *
     * 栈内存归属于单个线程，每个线程都会有一个栈内存，其存储的变量只能在其所属线程中可见，即栈内存可以理解成线程的私有内存。
     而堆内存中的对象对所有线程可见。堆内存中的对象可以被所有线程访问。
     异常错误
     如果栈内存没有可用的空间存储方法调用和局部变量，JVM会抛出java.lang.StackOverFlowError。
     而如果是堆内存没有可用的空间存储生成的对象，JVM会抛出java.lang.OutOfMemoryError。
     *
     *
     *
     * 空间大小
     栈的内存要远远小于堆内存，如果你使用递归的话，那么你的栈很快就会充满。如果递归没有及时跳出，很可能发生
     *
     *
     *
     * 能将 int 强制转换为 byte 类型的变量吗？如果该值大于 byte 类型的范围，将会出现什么现象？
     *
     * 可以做强制转换，但是 Java 中 int 是 32 位的，而 byte 是 8 位的，所以，如果强制转化，int 类型的高 24 位将会被丢弃，因为byte 类型的范围是从 -128 到 127。
     *
     * a.hashCode() 有什么用？与 a.equals(b) 有什么关系？
     *
     * hashCode（）方法提供了对象的hashCode值，是一个native方法，返回的默认值与System.identityHashCode(obj)一致。

     hashCode() 的作用是获取哈希码，也称为散列码；它实际上是返回一个int整数。这个哈希码的作用是确定该对象在哈希表中的索引位置。

     equals与hashCode的关系?

     equals()相等的两个对象，hashCode()一定相等；

     hashCode()不相等，一定能推出equals()也不相等；

     hashCode()相等，equals()可能相等，也可能不等。
     ————————————————
     版权声明：本文为CSDN博主「鵬程萬裡」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     原文链接：https://blog.csdn.net/qq_19309473/article/details/88830871
     *
     *
     * 字节流与字符流的区别?
     *
     * 要把一段二进制数据数据逐一输出到某个设备中，或者从某个设备中逐一读取一段二进制数据，不管输入输出设备是什么，我们要用统一的方式来完成这些操作，用一种抽象的方式进行描述，这个抽象描述方式起名为IO流，对应的抽象类为OutputStream和InputStream，不同的实现类就代表不同的输入和输出设备，它们都是针对字节进行操作的。

     底层设备永远只接受字节数据，有时候要写字符串到底层设备，需要将字符串转成字节再进行写入。字符流是字节流的包装，字符流则是直接接受字符串，它内部将串转成字节，再写入底层设备，这为我们向IO设备写入或读取字符串提供了一点点方便。
     *
     *
     * 什么是java序列化，如何实现java序列化？Serializable接口的作用?
     *
     * 将一个java对象变成字节流的形式传出去称为序列化，从字节流中恢复一个对象称为反序列化。 实现serializable接口，这样，javac编译时就会进行特殊处理，编译的类才可以被writeObject方法操作，这就是所谓的序列化。需要被序列化的类必须实现Serializable接口，该接口是一个mini接口，其中没有需要实现方法，implements Serializable只是为了标注该对象是可被序列化的。
     *
     *描述一下JVM加载class文件的原理机制?
     *
     * 类加载不太了解
     *
     *
     * GC是什么?为什么要有GC?
     *
     * 垃圾回收,内存管理
     *
     * Java 中，throw 和 throws 有什么区别
     *
     * 抛出和声明
     *
     * throw代表的是动作，throws是状态
     throw用在方法中，而throws用在方法声明中
     throw只能抛出一种异常，而throws可以抛出多个
     *
     *
     *
     * volatile关键字是否能保证线程安全？
     * 否,没用过
     *
     * Java能不能不通过构造函数创建对象（）
     * Java创建对象的几种方式： 1. 用new语句创建对象，这是最常见的创建对象的方法。 2. 运用反射手段,调用java.lang.Class或者java.lang.reflect.Constructor类的newInstance()实例方法。 3. 调用对象的clone()方法。 4. 运用反序列化手段
     *
     *
     *
     * throws和throw
     throws：用来声明一个方法可能产生的所有异常，不做任何处理而是将异常往上传，谁调用我我就抛给谁。
       用在方法声明后面，跟的是异常类名
       可以跟多个异常类名，用逗号隔开
       表示抛出异常，由该方法的调用者来处理
       throws表示出现异常的一种可能性，并不一定会发生这些异常
     throw：则是用来抛出一个具体的异常类型。
       用在方法体内，跟的是异常对象名
       只能抛出一个异常对象名
       表示抛出异常，由方法体内的语句处理
       throw则是抛出了异常，执行throw则一定抛出了某种异常  
     ————————————————
     版权声明：本文为CSDN博主「kobe-fly」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     原文链接：https://blog.csdn.net/weixin_38011265/article/details/79149313
     *
     *
     *synchronized和lock的区别？
     *
     *主要相同点：Lock能完成Synchronized所实现的所有功能。
     > 主要不同点：Lock有比Synchronized更精确的线程予以和更好的性能。 > 1. Synchronized会自动释放锁，但是Lock一定要求程序员手工释放，并且必须在finally从句中释放。 > 2. synchronized修饰方法时 表示同一个对象在不同的线程中 表现为同步队列 如果实例化不同的对象 那么synchronized就不会出现同步效果了。
     *
     *
     *什么是值传递和引用传递？java中是值传递还是引用传递，还是都有?
     对象被值传递，意味着传递了对象的一个副本。因此，就算是改变了对象副本，也不会影响源对象的值。
     对象被引用传递，意味着传递的并不是实际的对象，而是对象的引用。因此，外部对引用对象所做的改变会反映到所有的对象上。 >>java中方法参数传递方式是按值传递。 如果参数是基本类型，传递的是基本类型的字面量值的拷贝。 如果参数是引用类型，传递的是该参量所引用的对象在堆中地址值的拷贝。
     *
     *
     * .构造器（constructor）是否可被重写（override）?
     构造方法是不能被子类重写的，但是构造方法可以重载，也就是说一个类可以有多个构造方法。
     *
     *建线程有几种不同的方式？你喜欢哪一种？为什么？
     有三种方式可以用来创建线程： 1. 继承Thread类 2. 实现Runnable接口 3. 应用程序可以使用Executor框架来创建线程池
     *
     *finalize()方法什么时候被调用？
     在释放对象占用的内存之前，且对象的finalize()还没被垃圾收集器调用过，则垃圾收集器会调用对象的finalize()方法。
     *
     *如果对象的引用被置为null，垃圾收集器是否会立即释放对象占用的内存？
     不会，在下一个垃圾回收周期中，这个对象将是可被回收的。
     *
     *
     *什么是JDBC？
     JDBC（Java Data Base Connectivity,java数据库连接）是一种用于执行SQL语句的Java API，可以为多种关系数据库提供统一访问，它由一组用Java语言编写的类和接口组成。JDBC提供了一种基准，据此可以构建更高级的工具和接口，使数据库开发人员能够编写数据库应用程序。
     *
     *
     * 面向对象的五大基本原则
     单一职责原则SRP(Single Responsibility Principle) >>一个类应该仅有一个引起它变化的原因,例如：比如在职员类里，将工程师、销售人员、销售经理这些情况都放在职员类里考虑，其结果将会非常混乱，在这个假设下，职员类里的每个方法都要if else判断是哪种情况，从类结构上来说将会十分臃肿，并且上述三种的职员类型，不论哪一种发生需求变化，都会改变职员类！这个是大家所不愿意看到的！
     开放封闭原则OCP(Open－Close Principle)
     既开放又封闭，对扩展是开放的，对更改是封闭的！
     扩展即扩展现行的模块，当我们软件的实际应用发生改变时，出现新的需求，就需要我们对模块进行扩展，使其能够满足新的需求！

     替换原则(the Liskov Substitution Principle LSP) >* 子类可以替换父类并且出现在父类能够出现的任何地方 >>在这个原则中父类应尽可能使用接口或者抽象类来实现！,子类通过实现了父类接口，能够替父类的使用地方！ 通过这个原则，我们客户端在使用父类接口的时候，通过子类实现！ 意思就是说我们依赖父类接口，在客户端声明一个父类接口，通过其子类来实现 这个时候就要求子类必须能够替换父类所出现的任何地方，这样做的好处就是，在根据新要求扩展父类接口的新子类的时候而不影响当前客户端的使用！

     依赖倒置原则(the Dependency Inversion Principle DIP) 具体依赖抽象，上层依赖下层。 > 传统的结构化编程中，最上层的模块通常都要依赖下面的子模块来实现，也称为高层依赖低层！ 所以DIP原则就是要逆转这种依赖关系，让高层模块不要依赖低层模块，所以称之为依赖倒置原则！

     接口分离原则(the Interface Segregation Principle ISP) 模块间要通过抽象接口隔离开，而不是通过具体的类强耦合起来
     *
     *用最有效率的方法计算2乘以8？
     *
     *
     *2 << 3（左移3位相当于乘以2的3次方，右移3位相当于除以2的3次方）。
     *
     *在Java中，如何跳出当前的多重嵌套循环？
     在最外层循环前加一个标记如A，然后用break A;可以跳出多重循环。
     *
     *两个对象值相同(x.equals(y) == true)，但却可有不同的hash code，这句话对不对？
     答：不对，如果两个对象x和y满足x.equals(y) == true，它们的哈希码（hash code）应当相同。Java对于eqauls方法和hashCode方法是这样规定的： 1. 如果两个对象相同（equals方法返回true），那么它们的hashCode值一定要相同； 2. 如果两个对象的hashCode相同，它们并不一定相同。

     如果违背了上述原则就会发现在使用容器时，相同的对象可以出现在Set集合中，同时增加新元素的效率会大大下降（对于使用哈希存储的系统，如果哈希码频繁的冲突将会造成存取性能急剧下降）。
     *
     *是否可以继承String类？
     答：String 类是final类，不可以被继承。
     *
     *char 型变量中能不能存贮一个中文汉字，为什么？
     答：char类型可以存储一个中文汉字，因为Java中使用的编码是Unicode（不选择任何特定的编码，直接使用字符在字符集中的编号，这是统一的唯一方法），一个char类型占2个字节（16比特），所以放一个中文是没问题的。
     *
     * Collection和Collections的区别？
     答：Collection是一个接口，它是Set、List等容器的父接口；Collections是个一个工具类，提供了一系列的静态方法来辅助容器操作，这些方法包括对容器的搜索、排序、线程安全化等等。
     *
     * 事务的ACID是指什么？
     原子性(Atomic)：事务中各项操作，要么全做要么全不做，任何一项操作的失败都会导致整个事务的失败；
     一致性(Consistent)：事务结束后系统状态是一致的；
     隔离性(Isolated)：并发执行的事务彼此无法看到对方的中间状态；
     持久性(Durable)：事务完成后所做的改动都会被持久化，即使发生灾难性的失败。通过日志和同步备份可以在故障发生后重建数据。

     脏读（Dirty Read）：A事务读取B事务尚未提交的数据并在此基础上操作，而B事务执行回滚，那么A读取到的数据就是脏数据。

     不可重复读（Unrepeatable Read）：事务A重新读取前面读取过的数据，发现该数据已经被另一个已提交的事务B修改过了。
     幻读（Phantom Read）：事务A重新执行一个查询，返回一系列符合查询条件的行，发现其中插入了被事务B提交的行。
     *
     *hread 类中的start() 和 run() 方法有什么区别？
     这个问题经常被问到，但还是能从此区分出面试者对Java线程模型的理解程度。start()方法被用来启动新创建的线程，而且start()内部调用了run()方法，这和直接调用run()方法的效果不一样。当你调用run()方法的时候，只会是在原来的线程中调用，没有新的线程启动，start()方法才会启动新线程。
     *
     *ava中Runnable和Callable有什么不同？
     Runnable和Callable都代表那些要在不同的线程中执行的任务。Runnable从JDK1.0开始就有了，Callable是在JDK1.5增加的。它们的主要区别是Callable的 call() 方法可以返回值和抛出异常，而Runnable的run()方法没有这些功能。Callable可以返回装载有计算结果的Future对象.
     *
     *JDBC的DriverManager是用来做什么的？
     JDBC的DriverManager是一个工厂类，我们通过它来创建数据库连接。
     当JDBC的Driver类被加载进来时，它会自己注册到DriverManager类里面然后我们会把数据库配置信息传成DriverManager.getConnection()方法，DriverManager会使用注册到它里面的驱动来获取数据库连接，并返回给调用的程序。
     *
     *JDBC的ResultSet是什么？
     *
     *
     * 在Java中定义一个不做事且没有参数的构造方法的作用
     *Java程序在执行子类的构造方法之前，如果没有用super()来调用父类特定的构造方法，则会调用父类中“没有参数的构造方法”。因此，如果父类中只定义了有参数的构造方法，而在子类的构造方法中又没有用super()来调用父类中特定的构造方法，则编译时将发生错误，因为Java程序在父类中找不到没有参数的构造方法可供执行。解决办法是在父类里加上一个不做事且没有参数的构造方法。
     *
     *实现一个线程有哪几种方式,各有什么优缺点,比较常用的是那种?
     *
     *通过继承Thread类，优点：可以直接调用start方法启动。缺点：继承一个类后，不能再继承别的类。需要重写run方法。无返回值。
     实现Runnable接口，优点：可以实现多个接口或继承一个类；缺点：不能直接启动，要通过构造一个Thread把自己传进去。需要重写run方法，无返回值。
     实现Callable接口，优点：可以抛出异常，有返回值；缺点：只有jkd1.5以后才支持。需要重写call方法。结合FutureTask和Thread类一起使用，最后调用start启动。
     一般最常用的是第二种，实现Runnable接口。比较方便，可扩展性高。
     *
     *java中会存在内存泄漏吗，请简单描述。
     *
     *1、长生命周期的对象持有短生命周期对象的引用就很可能发生内存泄露。
     *
     *，缓存系统，我们加载了一个对象放在缓存中(例如放在一个全局map对象中)，然后一直不再使用它，这个对象一直被缓存引用，但却不再被使用。
     *
     *说一说Servlet的生命周期?
     *servlet有良好的生存期的定义，包括加载和实例化、初始化、处理请求以及服务结束。这个生存期由javax.servlet.Servlet接口的init,service和destroy方法表达。

     Servlet被服务器实例化后，容器运行其init方法，请求到达时运行其service方法，service方法自动派遣运行与请求对应的doXXX方法（doGet，doPost）等，当服务器决定将实例销毁的时候调用其destroy方法。

     web容器加载servlet，生命周期开始。通过调用servlet的init()方法进行servlet的初始化。通过调用service()方法实现，根据请求的不同调用不同的do*()方法。结束服务，web容器调用servlet的destroy()方法。
     *
     *转发：forward()是使用RequestDispatcher接口中的forward()方法来实现，需要request 和 response作为参数，就是将用户的请求，连同请求信息等内容，一起转发到服务器的另外一个servlet去处理，它不会丢失request信息。这一过程是服务器内部完成的，作为访问者，是感觉不到了，或者说是透明的，因此访客浏览器的url 是不会发生变化的。
     ————————————————
     版权声明：本文为CSDN博主「六月星海」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     原文链接：https://blog.csdn.net/qq_38039015/article/details/79715105
     *
     *
     * 重定向：重定向redirect()是通过HttpServletResponse对象的sendRedirect()来实现，该方法相当于浏览器重新发送一个请求 response.sendRedirect(path); 会丢失request的所有信息，  它属于页面级的重定向，仅仅让你的浏览器重新访问一个新的url，作为浏览者，能很明显的看到浏览器url地址的变化，这和点击了一个普通的超链接的后果是一样的。
     ————————————————
     版权声明：本文为CSDN博主「六月星海」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     原文链接：https://blog.csdn.net/qq_38039015/article/details/79715105
     *
     *
     *两者的选择：
     forward()方法更加高效， 在forward()方法可以满足需要时， 尽量使用 forward()方法， 并且， 这样也有助于隐藏实际的链接。 在有些情况下， 比如， 需要跳转到一个其它服务器上的资源， 则必须使用。
     *
     *、request.getAttribute()和 request.getParameter()有何区别?
     *
     *getParameter 得到的都是 String 类型的。或者是 http://a.jsp?id=123 中的 123，或者是某个表
     单提交过去的数据。
     getAttribute 则可以是对象。
     getParameter()是获取 POST/GET 传递的参数值；
     getAttribute()是获取对象容器中的数据值；
     *
     *jsp静态包含和动态包含的区别
     *
     *
     *JSP中有两种包含：静态包含：<%@include file="被包含页面"%>和动态包含：

     <jsp:include page="被包含页面"flush="true">。
     *
     *1_ <%@ include file=” ”%>是指令元素。<jsp:include page=” ”/>是行为元素

     2_最终编译成java文件的数目不同。（从上面的例子可以看出）

     *_静态包含在转换成为java文件的时候将包含文件的内容“复制”到主体文件，

     然后作为一个整体编译。最终编译为一个java文件。

     *_动态包含是各个jsp文件分别转换，分别编译。最终编程成多个java文件。

     3_执行时间不同

     *_静态包含发生在：JSP---->java文件阶段。

     *_动态包含发生在：执行class文件阶段。动态加入。

     4_静态包含在两个文件中不能有相同的变量，动态包含允许。

     由于静态包含相当于将包含文件内容直接复制到主体文件中，如果出现相同的

     变量，就会出现覆盖等问题，导致文件出错。而动态包含相当于调用不同的jsp，

     变量所在的空间不同，自然不会出现覆盖等现象。

     5_无论是动态包含还是静态包含，其request对象都是相同的。也就是同一个request对象。

     静态包含最终编译成一个java文件，有一个request对象很好理解。而动态包含

     最终编译成多个jsp文件，为何会使用一个request对象呢？其实这些jsp组合的过程

     是一个请求转发的过程，自然也使用同一个request对象了。
     *
     *MVC的各个部分都有那些技术来实现?如何实现?
     *MVC 是 Model－View－Controller 的简写。 Model 代表的是应用的业务逻辑（ 通过
     JavaBean， EJB 组件实现）， View 是应用的表示面（ 由 JSP 页面产生）， Controller 是提供
     应用的处理过程控制（ 一般是一个 Servlet）， 通过这种设计模型把应用逻辑， 处理过程和显
     示逻辑分成不同的组件实现。 这些组件可以进行交互和重用。
     *
     *JSP共有以下9个内置的对象：

     request 用户端请求，此请求会包含来自GET/POST请求的参数

     response 网页传回用户端的回应

     pageContext 网页的属性是在这里管理

     session 与请求有关的会话期

     application servlet 正在执行的内容

     out 用来传送回应的输出

     config servlet的构架部件

     page JSP网页本身

     exception 针对错误网页，未捕捉的例外


     request表示HttpServletRequest对象。它包含了有关浏览器请求的信息，并且提供了几个用于获取cookie, header,和session数据的有用的方法。

     response表示HttpServletResponse对象，并提供了几个用于设置送回浏览器的响应的方法（如cookies,头信息等）

     out对象是javax.jsp.JspWriter的一个实例，并提供了几个方法使你能用于向浏览器回送输出结果。

     pageContext表示一个javax.servlet.jsp.PageContext对象。它是用于方便存取各种范围的名字空间、servlet相关的对象的API，并且包装了通用的

     servlet相关功能的方法。

     session表示一个请求的javax.servlet.http.HttpSession对象。Session可以存贮用户的状态信息

     applicaton 表示一个javax.servle.ServletContext对象。这有助于查找有关servlet引擎和servlet环境的信息

     config表示一个javax.servlet.ServletConfig对象。该对象用于存取servlet实例的初始化参数。

     page表示从该页面产生的一个servlet实例



     JSP共有以下6种基本动作


     jsp:include：在页面被请求的时候引入一个文件。

     jsp:useBean：寻找或者实例化一个JavaBean。

     jsp:setProperty：设置JavaBean的属性。

     jsp:getProperty：输出某个JavaBean的属性。

     jsp:forward：把请求转到一个新的页面。

     jsp:plugin：根据浏览器类型为Java插件生成OBJECT或EMBED标记
     *
     *
     *1）采用GET方法向服务器上传数据时，一般将数据添加到URL后面，并且二者用 “？” 连接，各个变量之间用 “&” 连接。由于对URL的长度存在限制，因此采用GET方法能上传的数据量非常小，通常在1024Byte左右。而POST方法传递数据是通过HTTP请求的附件进行的，传送的数据量更大一些，一般默认认为不受限制的。

     2）由于GET方法上传的数据是添加在URL中的，因此上传的数据被彻底“暴露”出来，本身存在安全隐患，尤其是一些敏感信息。而POST方法向服务器提交的内容在URL中没有明文显示，对用户都是不可见的，所以，安全性更好。
     ————————————————
     版权声明：本文为CSDN博主「AndyAtcsdn」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     原文链接：https://blog.csdn.net/u012005549/article/details/82817082
     *
     *cookie是Web服务器发送给浏览器的一块信息。浏览器会在本地文件中给每一个Web服务器存储cookie。以后浏览器在给特定的Web服务器发请求的时候，同时会发送所有为该服务器存储的cookie。
     （cookie实际上是一小段的文本信息。客户端请求服务器，如果服务器需要记录该用户的状态，就使用response向客户端浏览器颁发一个cookie。客户端浏览器会把cookie保存起来。当浏览器再次请求该网站时，浏览器就会把请求地址和cookie一同给服务器。服务器检查该cookie，从而判断用户的状态。服务器还可以根据需要修改cookie的内容。）

     cookie有两种

     一种是基于窗口的，浏览器窗口关闭后，cookie就没有了；
     另一种是将信息存储在一个临时文件中，并设置存在的时间。
     在使用cookie时要注意几点：首先不要在cookie中存放敏感信息；（不安全）
     其次cookie存储的数据量有限（4k），不能将过多的内容存储cookie中；
     再者浏览器通常只允许一个站点最多存放20个cookie。
     ————————————————
     版权声明：本文为CSDN博主「Star__1024」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     原文链接：https://blog.csdn.net/qq_33858191/article/details/88295553
     *
     *session(存服务器、占内存、)
     session是另一种记录客户状态的机制。不同的是cookie保存在客户端浏览器中，而session保存在服务器上。客户端浏览器访问服务器的时候，服务器把客户端信息以某种形式记录在服务器上，这就是session。客户端浏览器再次访问时只需要从该session中查找该客户的状态就可以了。

     （当用户通过浏览器和服务器建立一次会话后，sessionID就会随响应信息返回存储在基于窗口的cookie中，那就意味着只要浏览器没有关闭，会话没有超时，下一次请求时这个sessionID又会提交给服务器让服务器识别用户身份。）

     session话中可以为用户保存信息。当然，和用户会话相关的其他信息（除了会话ID）也可以存在cookie方便进行会话跟踪。

     不同点
     session对象是在服务器内存中的，而基于窗口的cookie是在客户端内存中的。
     cookie不是很安全，别人可以分析存放在本地的cookie，进行cookie欺骗；session会在一定时间内保存在服务器上，当访问增多，会占用服务器性能
     cookie保存数据不能超过4k，很多浏览器都限制一个站点最多存放20个cookie
     ————————————————
     版权声明：本文为CSDN博主「Star__1024」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     原文链接：https://blog.csdn.net/qq_33858191/article/details/88295553
     *
     *
     *JSP在本质上就是SERVLET,但是两者的创建方式不一样.

     Servlet完全是JAVA程序代码构成，擅长于流程控制和事务处理，通过Servlet来生成动态网页很不直观.

     JSP由HTML代码和JSP标签构成，可以方便地编写动态网页.
     因此在实际应用中采用Servlet来控制业务流程，而采用JSP来生成动态网页.

     在struts框架中，JSP位于MVC设计模式的视图层，而Servlet位于控制层.



     JSP是Servlet技术的扩展，本质上就是Servlet的简易方式。

     JSP编译后是“类servlet”。

     Servlet和JSP最主要的不同点在于，Servlet的应用逻辑是在Java文件中，并且完全从表示层中的HTML里分离开来。而JSP是Java和HTML组合成一个扩展名为.jsp的文件。

     JSP侧重于视图，Servlet主要用于控制逻辑。
     *
     *当容器启动时，会读取在webapps目录下所有的web应用中的web.xml文件，然后对 xml文件进行解析，并读取servlet注册信息。然后，将每个应用中注册的servlet类都进行加载，并通过 反射的方式实例化。（有时候也是在第一次请求时实例化）

     在servlet注册时加上1如果为正数，则在一开始就实例化，如果不写或为负数，则第一次请求实例化。
     *
     *
     *
     * A.载入JDBC驱动程序

     String driver = "oracle.jdbc.driver.OracleDriver";

     B.定义连接URL

     String url = "jdbc:oracle:thin:@127.0.0.1:1521:DB_NAME";

     C.建立连接

     String username= "zhouchuandong";

     String password = "xiaodong728";

     Connection conn = DriverManager.getConnection(url,username,password);

     D.创建Statement对象

     Statement stmt = conn.createStatement();

      E.执行查询或更新SQL

     String sql = "insert into student1 (id , name,sex ) values (1002,'黄国国','男')";

     stmt.addBatch(sql);

     F.结果处理

     int[] count =stmt.executeBatch();
     if(count.length>0){
     System.out.println("加入成功！");
     }

       G.关闭连接
     ————————————————
     版权声明：本文为CSDN博主「上士闻道～勤而行之」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     原文链接：https://blog.csdn.net/huangli1466384630/article/details/79832256
     *
     *先来说说，什么是java中的Statement：Statement是java执行数据库操作的一个重要方法，用于在已经建立数据库连接的基础上，向数据库发送要执行的SQL语句。具体步骤：
     *
     *Statement 对象用于将 SQL 语句发送到数据库中。实际上有三种 Statement 对象，它们都作为在给定连接上执行 SQL语句的包容器：Statement、PreparedStatement（它从 Statement 继承而来）和CallableStatement（它从 PreparedStatement 继承而来）。它们都专用于发送特定类型的 SQL 语句：Statement 对象用于执行不带参数的简单 SQL 语句；PreparedStatement 对象用于执行带或不带参数的预编译 SQL 语句；CallableStatement 对象用于执行对数据库已存储过程的调用。
     *
     *综上所述，总结如下：Statement每次执行sql语句，数据库都要执行sql语句的编译，最好用于仅执行一次查询并返回结果的情形，效率高于PreparedStatement.但存在sql注入风险。PreparedStatement是预编译执行的。在执行可变参数的一条SQL时，PreparedStatement要比Statement的效率高，因为DBMS预编译一条SQL当然会比多次编译一条SQL的效率高。安全性更好，有效防止SQL注入的问题。对于多次重复执行的语句，使用Prepared

     Statement效率会更高一点。执行SQL语句是可以带参数的，并支持批量执行SQL。由于采用了Cache机制，则预编译的语句，就会放在Cache中，下次执行相同的SQL语句时，则可以直接从Cache中取出来。
     *
     *那么CallableStatement扩展了PreparedStatement的接口，用来调用存储过程，它提供了对于输入和输出参数的支持，CallableStatement 接口还有对 PreparedStatement 接口提供的输入参数的sql查询的支持。
     *
     * 从安全性上来看，PreparedStatement是通过?来传递参数的，避免了拼sql而出现sql注入的问题，所以安全性较好。
     *
     *说说事务的概念，在JDBC编程中处理事务的步骤。
     *
     *1，事务是作为单个逻辑工作单元执行的一系列操作。
     2，一个逻辑工作单元必须有四个属性，称为原子性、一致性、隔离性和持久性 (ACID) 属性，只有这样才能成为一个事务
     事务处理步骤：
     3，conn.setAutoComit(false);设置提交方式为手工提交
     4，conn.commit()提交事务
     5，出现异常，回滚 conn.rollback();
     ————————————————
     版权声明：本文为CSDN博主「dengit_w」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     原文链接：https://blog.csdn.net/qq_36969411/article/details/78118004
     *
     *
     *什么是连接池
     数据库连接池负责分配、管理和释放数据库连接，它允许应用程序重复使用一个现有的数据库连接，而不是再重新建立一个。
     *
     *
     *为什么要使用连接池
      数据库连接是一种关键的有限的昂贵的资源，这一点在多用户的网页应用程序中体现得尤为突出。  一个数据库连接对象均对应一个物理数据库连接，每次操作都打开一个物理连接，使用完都关闭连接，这样造成系统的 性能低下。 数据库连接池的解决方案是在应用程序启动时建立足够的数据库连接，并讲这些连接组成一个连接池(简单说：在一个“池”里放了好多半成品的数据库联接对象)，由应用程序动态地对池中的连接进行申请、使用和释放。对于多于连接池中连接数的并发请求，应该在请求队列中排队等待。并且应用程序可以根据池中连接的使用率，动态增加或减少池中的连接数。 连接池技术尽可能多地重用了消耗内存地资源，大大节省了内存，提高了服务器地服务效率，能够支持更多的客户服务。通过使用连接池，将大大提高程序运行效率，同时，我们可以通过其自身的管理机制来监视数据库连接的数量、使用情况等。
     ————————————————
     版权声明：本文为CSDN博主「CrankZ」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     原文链接：https://blog.csdn.net/crankz/article/details/82874158
     *
     *连接池主要参数
     使用连接池时，要配置一下参数

     最小连接数：是连接池一直保持的数据库连接,所以如果应用程序对数据库连接的使用量不大,将会有大量的数据库连接资源被浪费.
     最大连接数：是连接池能申请的最大连接数,如果数据库连接请求超过次数,后面的数据库连接请求将被加入到等待队列中,这会影响以后的数据库操作
     最大空闲时间
     获取连接超时时间
     超时重试连接次数
     ————————————————
     版权声明：本文为CSDN博主「CrankZ」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     原文链接：https://blog.csdn.net/crankz/article/details/82874158
     *
     *
     *JDBC的脏读是什么？哪种数据库隔离级别能防止脏读？
     *
     * JDBC的DriverManager是用来做什么的?
     *管理JDBC驱动的类得到数据库的连接吧。。只用过getConnection（）
     *
     *1. ResultSet  executeQuery(String sql); 执行SQL查询，并返回ResultSet 对象。
     2.int              executeUpdate(String sql); 可执行增，删，改，返回执行受到影响的行数。
     3.boolean     execute(String sql); 可执行任何SQL语句，返回一个布尔值，表示是否返回ResultSet 。
     *
     *SQL查询出来的结果分页展示一般怎么做？
     * limit
     *
     *JDBC的ResultSet是什么？
     *
     *使用Spring框架的好处是什么？
     *
     *1.方便解耦，简化开发
     通过Spring提供的IoC容器，我们可以将对象之间的依赖关系交由Spring进行控制，避免硬编码所造成的过度程序耦合。有了Spring，用户不必再为单实例模式类、属性文件解析等这些很底层的需求编写代码，可以更专注于上层的应用。
     2.AOP编程的支持
     通过Spring提供的AOP功能，方便进行面向切面的编程，许多不容易用传统OOP实现的功能可以通过AOP轻松应付。
     3.声明事物的支持
     在Spring中，我们可以从单调烦闷的事务管理代码中解脱出来，通过声明式方式灵活地进行事务的管理，提高开发效率和质量。
     4.方便程序的测试
     可以用非容器依赖的编程方式进行几乎所有的测试工作，在Spring里，测试不再是昂贵的操作，而是随手可做的事情。例如：Spring对Junit4支持，可以通过注解方便的测试Spring程序。
     5.方便集成各种优秀框架
     Spring不排斥各种优秀的开源框架，相反，Spring可以降低各种框架的使用难度，Spring提供了对各种优秀框架（如Struts,Hibernate、Hessian、Quartz）等的直接支持。
     6.降低Java EE API的使用难度
     Spring对很多难用的Java EE API（如JDBC，JavaMail，远程调用等）提供了一个薄薄的封装层，通过Spring的简易封装，这些Java EE API的使用难度大为降低。
     7.Java 源码是经典学习范例
     Spring的源码设计精妙、结构清晰、匠心独用，处处体现着大师对Java设计模式灵活运用以及对Java技术的高深造诣。Spring框架源码无疑是Java技术的最佳实践范例。如果想在短时间内迅速提高自己的Java技术水平和应用开发水平，学习和研究Spring源码将会使你收到意想不到的效果。
     ————————————————
     版权声明：本文为CSDN博主「八零末愤青」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     原文链接：https://blog.csdn.net/hht006158/article/details/80181207
     *
     *
     *
     *
     *
     *什么是spring框架？spring特点与好处,使用spring框架的好处是什么.
     2018-05-03 16:35:49 八零末愤青 阅读数 18855更多
     分类专栏： Java
     版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。
     本文链接：https://blog.csdn.net/hht006158/article/details/80181207
     Spring是一个开源框架，Spring是于2003 年兴起的一个轻量级的Java 开发框架，由Rod Johnson 在其著作Expert One-On-One J2EE Development and Design中阐述的部分理念和原型衍生而来。它是为了解决企业应用开发的复杂性而创建的。框架的主要优势之一就是其分层架构，分层架构允许使用者选择使用哪一个组件，同时为 J2EE 应用程序开发提供集成的框架。Spring使用基本的JavaBean来完成以前只可能由EJB完成的事情。然而，Spring的用途不仅限于服务器端的开发。从简单性、可测试性和松耦合的角度而言，任何Java应用都可以从Spring中受益。Spring的核心是控制反转（IoC）和面向切面（AOP）。简单来说，Spring是一个分层的JavaSE/EEfull-stack(一站式) 轻量级开源框架。

     特点：

     1.方便解耦，简化开发
     通过Spring提供的IoC容器，我们可以将对象之间的依赖关系交由Spring进行控制，避免硬编码所造成的过度程序耦合。有了Spring，用户不必再为单实例模式类、属性文件解析等这些很底层的需求编写代码，可以更专注于上层的应用。
     2.AOP编程的支持
     通过Spring提供的AOP功能，方便进行面向切面的编程，许多不容易用传统OOP实现的功能可以通过AOP轻松应付。
     3.声明事物的支持
     在Spring中，我们可以从单调烦闷的事务管理代码中解脱出来，通过声明式方式灵活地进行事务的管理，提高开发效率和质量。
     4.方便程序的测试
     可以用非容器依赖的编程方式进行几乎所有的测试工作，在Spring里，测试不再是昂贵的操作，而是随手可做的事情。例如：Spring对Junit4支持，可以通过注解方便的测试Spring程序。
     5.方便集成各种优秀框架
     Spring不排斥各种优秀的开源框架，相反，Spring可以降低各种框架的使用难度，Spring提供了对各种优秀框架（如Struts,Hibernate、Hessian、Quartz）等的直接支持。
     6.降低Java EE API的使用难度
     Spring对很多难用的Java EE API（如JDBC，JavaMail，远程调用等）提供了一个薄薄的封装层，通过Spring的简易封装，这些Java EE API的使用难度大为降低。
     7.Java 源码是经典学习范例
     Spring的源码设计精妙、结构清晰、匠心独用，处处体现着大师对Java设计模式灵活运用以及对Java技术的高深造诣。Spring框架源码无疑是Java技术的最佳实践范例。如果想在短时间内迅速提高自己的Java技术水平和应用开发水平，学习和研究Spring源码将会使你收到意想不到的效果。
     好处：
     在我们进入细节以前，让我们看一下Spring可以给一个工程带来的一些好处：
     Spring能有效地组织你的中间层对象，无论你是否选择使用了EJB。如果你仅仅使用了Struts或其他的包含了J2EE特有APIs的framework，你会发现Spring关注了遗留下的问题。Spring能消除在许多工程上对Singleton的过多使用。根据我的经验，这是一个主要的问题，它减少了系统的可测试性和面向对象特性。
     Spring能消除使用各种各样格式的属性定制文件的需要，在整个应用和工程中，可通过一种一致的方法来进行配置。曾经感到迷惑，一个特定类要查找迷幻般的属性关键字或系统属性，为此不得不读Javadoc乃至源编码吗？有了Spring，你可很简单地看到类的JavaBean属性。倒置控制的使用（在下面讨论）帮助完成这种简化。
     Spring能通过接口而不是类促进好的编程习惯，减少编程代价到几乎为零。
     Spring被设计为让使用它创建的应用尽可能少的依赖于他的APIs。在Spring应用中的大多数业务对象没有依赖于Spring。
     使用Spring构建的应用程序易于单元测试。
     Spring能使EJB的使用成为一个实现选择，而不是应用架构的必然选择。你能选择用POJOs或local EJBs来实现业务接口，却不会影响调用代码。
     Spring帮助你解决许多问题而无需使用EJB。Spring能提供一种EJB的替换物，它们适于许多web应用。例如，Spring能使用AOP提供声明性事务而不通过使用EJB容器，如果你仅仅需要与单个的数据库打交道，甚至不需要JTA实现。
     Spring为数据存取提供了一致的框架，不论是使用JDBC或O/R mapping产品（如Hibernate）。
     Spring确实使你能通过最简单可行的解决办法解决你的问题。这些特性是有很大价值的。
     总结起来，Spring有如下优点：
     1.低侵入式设计，代码污染极低
     2.独立于各种应用服务器，基于Spring框架的应用，可以真正实现Write Once,Run Anywhere的承诺
     3.Spring的DI机制降低了业务对象替换的复杂性，提高了组件之间的解耦
     4.Spring的AOP支持允许将一些通用任务如安全、事务、日志等进行集中式管理，从而提供了更好的复用
     5.Spring的ORM和DAO提供了与第三方持久层框架的良好整合，并简化了底层的数据库访问
     6.Spring并不强制应用完全依赖于Spring，开发者可自由选用Spring框架的部分或全部
     简单理解
     一.概念：
     1. spring是开源的轻量级框架

     2 spring核心主要两部分：

     （1）aop：面向切面编程，扩展功能不修改源代码实现,详解【http://www.cnblogs.com/landeanfen/p/4782370.html】

     （2）ioc：控制反转

     比如有一个类，在类里面有方法（不是静态的方法），调用类里面的方法，创建类的对象，使用对象调用方法，创建类对象的过程，需要new出来对象， 把对象的创建不是通过new方式实现，而是交给spring配置创建类对象。

     我用通俗的话给你解释把。首先你不用框架不是每次创建对象都要用关键字“new”呢？对吧。有了spring配置就不用new了，直接拿。举个例子：假如你吃饭，每次你要吃饭时都要自己准备碗和筷子，每次都要自己准备，用了框架后，再 吃饭你只要吃就行了，就不用准备碗和筷子了因为spring已经给你准备好了。这样是不是很方便。
     spring主要就是不用你自己创建对象，都配置在配置文件中。如果你写好一个项目，你再a类中创建了b类的方法，c类也创建了b类的方法，如果那天要改b类的类名，你就要在a和c中都改，如果有100个类都用了b类呢？那你不是要改死哦！！
     如果用了spring，你只要修改配置文件一个位置就好了，是不是很方便维护呢。所以，小项目用不着spring框架。手打好累。。。
     ioc：控制反转
     ioc的思想最核心的地方在于，资源不由使用资源的双方管理，而由不使用资源的第三方管理，这可以带来很多好处。第一，资源集中管理，实现资源的可配置和易管理。第二，降低了使用资源双方的依赖程度，也就是我们说的耦合度。

     也就是说，甲方要达成某种目的不需要直接依赖乙方，它只需要达到的目的告诉第三方机构就可以了，比如甲方需要一双袜子，而乙方它卖一双袜子，它要把袜子卖出去，并不需要自己去直接找到一个卖家来完成袜子的卖出。它也只需要找第三方，告诉别人我要卖一双袜子。这下好了，甲乙双方进行交易活动，都不需要自己直接去找卖家，相当于程序内部开放接口，卖家由第三方作为参数传入。甲乙互相不依赖，而且只有在进行交易活动的时候，甲才和乙产生联系。反之亦然。这样做什么好处么呢，甲乙可以在对方不真实存在的情况下独立存在，而且保证不交易时候无联系，想交易的时候可以很容易的产生联系。甲乙交易活动不需要双方见面，避免了双方的互不信任造成交易失败的问题。因为交易由第三方来负责联系，而且甲乙都认为第三方可靠。那么交易就能很可靠很灵活的产生和进行了。

     这就是ioc的核心思想。生活中这种例子比比皆是，支付宝在整个淘宝体系里就是庞大的ioc容器，交易双方之外的第三方，提供可靠性可依赖可灵活变更交易方的资源管理中心。另外人事代理也是，雇佣机构和个人之外的第三方。

     控制反转( Inversion of Control ), 我觉得有必要先了解软件设计的一个重要思想：依赖倒置原则（Dependency Inversion Principle ）。

     什么是依赖倒置原则？假设我们设计一辆汽车：先设计轮子，然后根据轮子大小设计底盘，接着根据底盘设计车身，最后根据车身设计好整个汽车。这里就出现了一个“依赖”关系：汽车依赖车身，车身依赖底盘，底盘依赖轮子。

     &amp;lt;img src=&quot;https://pic4.zhimg.com/50/v2-c68248bb5d9b4d64d22600571e996446_hd.jpg&quot; data-caption=&quot;&quot; data-rawwidth=&quot;1562&quot; data-rawheight=&quot;186&quot; class=&quot;origin_image zh-lightbox-thumb&quot; width=&quot;1562&quot; data-original=&quot;https://pic4.zhimg.com/v2-c68248bb5d9b4d64d22600571e996446_r.jpg&quot;&amp;gt;
     这样的设计看起来没问题，但是可维护性却很低。假设设计完工之后，上司却突然说根据市场需求的变动，要我们把车子的轮子设计都改大一码。这下我们就蛋疼了：因为我们是根据轮子的尺寸设计的底盘，轮子的尺寸一改，底盘的设计就得修改；同样因为我们是根据底盘设计的车身，那么车身也得改，同理汽车设计也得改——整个设计几乎都得改！

     我们现在换一种思路。我们先设计汽车的大概样子，然后根据汽车的样子来设计车身，根据车身来设计底盘，最后根据底盘来设计轮子。这时候，依赖关系就倒置过来了：轮子依赖底盘， 底盘依赖车身， 车身依赖汽车。

     &amp;lt;img src=&quot;https://pic1.zhimg.com/50/v2-e64bf72c5c04412f626b21753aa9e1a1_hd.jpg&quot; data-caption=&quot;&quot; data-rawwidth=&quot;1504&quot; data-rawheight=&quot;190&quot; class=&quot;origin_image zh-lightbox-thumb&quot; width=&quot;1504&quot; data-original=&quot;https://pic1.zhimg.com/v2-e64bf72c5c04412f626b21753aa9e1a1_r.jpg&quot;&amp;gt;
     这时候，上司再说要改动轮子的设计，我们就只需要改动轮子的设计，而不需要动底盘，车身，汽车的设计了。

     这就是依赖倒置原则——把原本的高层建筑依赖底层建筑“倒置”过来，变成底层建筑依赖高层建筑。高层建筑决定需要什么，底层去实现这样的需求，但是高层并不用管底层是怎么实现的。这样就不会出现前面的“牵一发动全身”的情况。

     控制反转（Inversion of Control） 就是依赖倒置原则的一种代码设计的思路。具体采用的方法就是所谓的依赖注入（Dependency Injection）。其实这些概念初次接触都会感到云里雾里的。说穿了，这几种概念的关系大概如下：

     &amp;lt;img src=&quot;https://pic1.zhimg.com/50/v2-ee924f8693cff51785ad6637ac5b21c1_hd.jpg&quot; data-caption=&quot;&quot; data-rawwidth=&quot;1398&quot; data-rawheight=&quot;630&quot; class=&quot;origin_image zh-lightbox-thumb&quot; width=&quot;1398&quot; data-original=&quot;https://pic1.zhimg.com/v2-ee924f8693cff51785ad6637ac5b21c1_r.jpg&quot;&amp;gt;
     为了理解这几个概念，我们还是用上面汽车的例子。只不过这次换成代码。我们先定义四个Class，车，车身，底盘，轮胎。然后初始化这辆车，最后跑这辆车。代码结构如下：

     &amp;lt;img src=&quot;https://pic3.zhimg.com/50/v2-8ec294de7d0f9013788e3fb5c76069ef_hd.jpg&quot; data-caption=&quot;&quot; data-rawwidth=&quot;512&quot; data-rawheight=&quot;717&quot; class=&quot;origin_image zh-lightbox-thumb&quot; width=&quot;512&quot; data-original=&quot;https://pic3.zhimg.com/v2-8ec294de7d0f9013788e3fb5c76069ef_r.jpg&quot;&amp;gt;
     这样，就相当于上面第一个例子，上层建筑依赖下层建筑——每一个类的构造函数都直接调用了底层代码的构造函数。假设我们需要改动一下轮胎（Tire）类，把它的尺寸变成动态的，而不是一直都是30。我们需要这样改：

     &amp;lt;img src=&quot;https://pic4.zhimg.com/50/v2-64e8b19eeb70d9cf87c27fe4c5c0fc81_hd.jpg&quot; data-caption=&quot;&quot; data-rawwidth=&quot;534&quot; data-rawheight=&quot;154&quot; class=&quot;origin_image zh-lightbox-thumb&quot; width=&quot;534&quot; data-original=&quot;https://pic4.zhimg.com/v2-64e8b19eeb70d9cf87c27fe4c5c0fc81_r.jpg&quot;&amp;gt;
     由于我们修改了轮胎的定义，为了让整个程序正常运行，我们需要做以下改动：

     &amp;lt;img src=&quot;https://pic1.zhimg.com/50/v2-82e0c12a1b26f7979ed9241e169affda_hd.jpg&quot; data-caption=&quot;&quot; data-rawwidth=&quot;1186&quot; data-rawheight=&quot;1452&quot; class=&quot;origin_image zh-lightbox-thumb&quot; width=&quot;1186&quot; data-original=&quot;https://pic1.zhimg.com/v2-82e0c12a1b26f7979ed9241e169affda_r.jpg&quot;&amp;gt;
     由此我们可以看到，仅仅是为了修改轮胎的构造函数，这种设计却需要修改整个上层所有类的构造函数！在软件工程中，这样的设计几乎是不可维护的——在实际工程项目中，有的类可能会是几千个类的底层，如果每次修改这个类，我们都要修改所有以它作为依赖的类，那软件的维护成本就太高了。

     所以我们需要进行控制反转（IoC），及上层控制下层，而不是下层控制着上层。我们用依赖注入（Dependency Injection）这种方式来实现控制反转。所谓依赖注入，就是把底层类作为参数传入上层类，实现上层类对下层类的“控制”。这里我们用构造方法传递的依赖注入方式重新写车类的定义：

     &amp;lt;img src=&quot;https://pic1.zhimg.com/50/v2-c920a0540ce0651003a5326f6ef9891d_hd.jpg&quot; data-caption=&quot;&quot; data-rawwidth=&quot;1338&quot; data-rawheight=&quot;1424&quot; class=&quot;origin_image zh-lightbox-thumb&quot; width=&quot;1338&quot; data-original=&quot;https://pic1.zhimg.com/v2-c920a0540ce0651003a5326f6ef9891d_r.jpg&quot;&amp;gt;
     这里我们再把轮胎尺寸变成动态的，同样为了让整个系统顺利运行，我们需要做如下修改：

     &amp;lt;img src=&quot;https://pic4.zhimg.com/50/v2-99ad2cd809fcb86dd791ff7f65fb1779_hd.jpg&quot; data-caption=&quot;&quot; data-rawwidth=&quot;1344&quot; data-rawheight=&quot;1424&quot; class=&quot;origin_image zh-lightbox-thumb&quot; width=&quot;1344&quot; data-original=&quot;https://pic4.zhimg.com/v2-99ad2cd809fcb86dd791ff7f65fb1779_r.jpg&quot;&amp;gt;
     看到没？这里我只需要修改轮胎类就行了，不用修改其他任何上层类。这显然是更容易维护的代码。不仅如此，在实际的工程中，这种设计模式还有利于不同组的协同合作和单元测试：比如开发这四个类的分别是四个不同的组，那么只要定义好了接口，四个不同的组可以同时进行开发而不相互受限制；而对于单元测试，如果我们要写Car类的单元测试，就只需要Mock一下Framework类传入Car就行了，而不用把Framework, Bottom, Tire全部new一遍再来构造Car。

     这里我们是采用的构造函数传入的方式进行的依赖注入。其实还有另外两种方法：Setter传递和接口传递。这里就不多讲了，核心思路都是一样的，都是为了实现控制反转。

     &amp;lt;img src=&quot;https://pic1.zhimg.com/50/v2-861683acac47577c81f2b7493dd05649_hd.jpg&quot; data-caption=&quot;&quot; data-rawwidth=&quot;924&quot; data-rawheight=&quot;298&quot; class=&quot;origin_image zh-lightbox-thumb&quot; width=&quot;924&quot; data-original=&quot;https://pic1.zhimg.com/v2-861683acac47577c81f2b7493dd05649_r.jpg&quot;&amp;gt;
     看到这里你应该能理解什么控制反转和依赖注入了。那什么是控制反转容器(IoC Container)呢？其实上面的例子中，对车类进行初始化的那段代码发生的地方，就是控制反转容器。

     &amp;lt;img src=&quot;https://pic4.zhimg.com/50/v2-c845802f9187953ed576e0555f76da42_hd.jpg&quot; data-caption=&quot;&quot; data-rawwidth=&quot;1422&quot; data-rawheight=&quot;628&quot; class=&quot;origin_image zh-lightbox-thumb&quot; width=&quot;1422&quot; data-original=&quot;https://pic4.zhimg.com/v2-c845802f9187953ed576e0555f76da42_r.jpg&quot;&amp;gt;
     显然你也应该观察到了，因为采用了依赖注入，在初始化的过程中就不可避免的会写大量的new。这里IoC容器就解决了这个问题。这个容器可以自动对你的代码进行初始化，你只需要维护一个Configuration（可以是xml可以是一段代码），而不用每次初始化一辆车都要亲手去写那一大段初始化的代码。这是引入IoC Container的第一个好处。

     IoC Container的第二个好处是：我们在创建实例的时候不需要了解其中的细节。在上面的例子中，我们自己手动创建一个车instance时候，是从底层往上层new的：

     &amp;lt;img src=&quot;https://pic2.zhimg.com/50/v2-555b2be7d76e78511a6d6fed3304927f_hd.jpg&quot; data-caption=&quot;&quot; data-rawwidth=&quot;2430&quot; data-rawheight=&quot;168&quot; class=&quot;origin_image zh-lightbox-thumb&quot; width=&quot;2430&quot; data-original=&quot;https://pic2.zhimg.com/v2-555b2be7d76e78511a6d6fed3304927f_r.jpg&quot;&amp;gt;
     这个过程中，我们需要了解整个Car/Framework/Bottom/Tire类构造函数是怎么定义的，才能一步一步new/注入。

     而IoC Container在进行这个工作的时候是反过来的，它先从最上层开始往下找依赖关系，到达最底层之后再往上一步一步new（有点像深度优先遍历）：

     &amp;lt;img src=&quot;https://pic3.zhimg.com/50/v2-24a96669241e81439c636e83976ba152_hd.jpg&quot; data-caption=&quot;&quot; data-rawwidth=&quot;2522&quot; data-rawheight=&quot;354&quot; class=&quot;origin_image zh-lightbox-thumb&quot; width=&quot;2522&quot; data-original=&quot;https://pic3.zhimg.com/v2-24a96669241e81439c636e83976ba152_r.jpg&quot;&amp;gt;
     这里IoC Container可以直接隐藏具体的创建实例的细节，在我们来看它就像一个工厂：

     &amp;lt;img src=&quot;https://pic2.zhimg.com/50/v2-5ca61395f37cef73c7bbe7808f9ea219_hd.jpg&quot; data-caption=&quot;&quot; data-rawwidth=&quot;2448&quot; data-rawheight=&quot;524&quot; class=&quot;origin_image zh-lightbox-thumb&quot; width=&quot;2448&quot; data-original=&quot;https://pic2.zhimg.com/v2-5ca61395f37cef73c7bbe7808f9ea219_r.jpg&quot;&amp;gt;
     我们就像是工厂的客户。我们只需要向工厂请求一个Car实例，然后它就给我们按照Config创建了一个Car实例。我们完全不用管这个Car实例是怎么一步一步被创建出来。

     实际项目中，有的Service Class可能是十年前写的，有几百个类作为它的底层。假设我们新写的一个API需要实例化这个Service，我们总不可能回头去搞清楚这几百个类的构造函数吧？IoC Container的这个特性就很完美的解决了这类问题——因为这个架构要求你在写class的时候需要写相应的Config文件，所以你要初始化很久以前的Service类的时候，前人都已经写好了Config文件，你直接在需要用的地方注入这个Service就可以了。这大大增加了项目的可维护性且降低了开发难度。
     *
     *
     *1>FileSystemXmlApplicationContext:此容器从一个XML文件中加载bean的定义，XML Bean配置文件的全路径名必须提供给它的构造函数

     2>ClassPathXmlApplicationContext:此容器也从一个XML文件中加载bean的定义，这里需要正确设置classpath因为这个容器将在classpath里找bean配置

     3>WebXmlApplicationContext:此容器加载一个XML文件，此文件定义了一个WEB应用的所有bean
     *
     *
     *Spring beans

     Spring bean 表示受到Spring管理的对象。具体说来，它是被Spring框架容器初始化、配置和管理的对象。Spring bean是在Spring的配置文件中定义（现在也可以通过annotation注解来定义），在Spring容器中初始化，然后注入到应用程序中的。

     因为在最早的版本中，Spring是被设计用来管理JavaBean的，所以Spring管理的对象会被称为“bean”。当然，现在Spring已经可以管理任何对象，即使它不具备默认构造器和设置方法（getter和setter）这些JavaBean的特性。然而，”Spring bean“这个术语仍然被保存了下来。

     Spring bean可以是POJO吗？当然可以，并且它通常就是。（即使它并不一定得是POJO，例如Spring可以用来处理重量级Java对象，比如EJB对象）。
     *
     *
     *泛型,反射,集合,网络,javaweb,框架,模板引擎
     *
     *数据库,事务,
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */
}
