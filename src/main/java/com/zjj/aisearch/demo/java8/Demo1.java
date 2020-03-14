package com.zjj.aisearch.demo.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @program: AISearch
 * @description:
 * @author: zjj
 * @create: 2020-03-08 10:32:55
 **/
public class Demo1 {
    /**
     * 允许在接口中有默认方法实现
     * Java 8 允许我们使用default关键字，为接口声明添加非抽象的方法实现。这个特性又被称为扩展方法。下面是我们的第一个例子：
     */
    @Test
    public void Test1() {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        System.out.println(formula.calculate(100));     // 100.0
        System.out.println(formula.sqrt(16));           // 4.0
    }

    @Test
    public void test2() throws Exception {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        /**
         * 除了创建匿名对象以外，Java 8 还提供了一种更简洁的方式，Lambda表达式。
         */
       /* Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });*/
        /**
         *你可以看到，这段代码就比之前的更加简短和易读。但是，它还可以更加简短：
         */
        /*Collections.sort(names, (String a, String b) -> b.compareTo(a));*/
/**
 * Java编译器能够自动识别参数的类型，所以你就可以省略掉类型不写。让我们再深入地研究一下lambda表达式的威力吧。
 */
        Collections.sort(names, (a, b) -> b.compareTo(a));
        System.out.println(names);
    }

    /**
     * 函数式接口
     *
     * Lambda表达式如何匹配Java的类型系统？每一个lambda都能够通过一个特定的接口，与一个给定的类型进行匹配。一个所谓的函数式接口必须要有且仅有一个抽象方法声明。每个与之对应的lambda表达式必须要与抽象方法的声明相匹配。由于默认方法不是抽象的，因此你可以在你的函数式接口里任意添加默认方法。
     */
    /**
     * java Comparator为何是函数式接口？
     * https://segmentfault.com/q/1010000018112927
     * <p>
     * public @interface FunctionalInterface 官方文档：
     * <p>
     * If an interface declares an abstract method overriding one of the public methods of java.lang.Object, that also does not count toward the interface's abstract method count since any implementation of the interface will have an implementation from java.lang.Object or elsewhere.
     * 如果接口声明了一个覆盖java.lang.Object的全局方法之一的抽象方法，那么它不会计入接口的抽象方法数量中，因为接口的任何实现都将具有java.lang.Object或其他地方的实现。
     * <p>
     * 从中我们可以得知函数式接口的几点特征：
     * <p>
     * 函数式接口只有一个抽象方法
     * default方法某默认实现，不属于抽象方法
     * 接口重写了Object的公共方法也不算入内
     * 所以，Comparator虽然有两个抽象方法：
     * <p>
     * int compare(T o1, T o2);
     * boolean equals(Object obj);
     * 其中 equals为Object的方法，不算入内，所以Comparator可以作为函数式接口。
     */

    @Test
    public void test3() throws Exception {
       /* Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted);    // 123*/
        /**
         * 注意，如果你不写@FunctionalInterface 标注，程序也是正确的。
         */

        /**
         * 方法和构造函数引用
         上面的代码实例可以通过静态方法引用，使之更加简洁：
         */

        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        System.out.println(converted);   // 123

        /**
         * Java 8 允许你通过::关键字获取方法或者构造函数的的引用。上面的例子就演示了如何引用一个静态方法。而且，我们还可以对一个对象的方法进行引用：
         */


    }

    @Test
    public void test4() throws Exception {
        Something something = new Something();
        Converter<String, String> converter = something::startsWith;
        String converted = converter.convert("Java");
        System.out.println(converted);    // "J"
    }

    /**
     * 让我们看看如何使用::关键字引用构造函数。首先我们定义一个示例bean，包含不同的构造方法：
     */


    @Test
    public void test5() throws Exception {
        Something something = new Something();
        Converter<String, String> converter = something::startsWith;
        String converted = converter.convert("Java");
        System.out.println(converted);    // "J"
    }

    /**
     * 然后我们通过构造函数引用来把所有东西拼到一起，而不是像以前一样，通过手动实现一个工厂来这么做。
     */

    /**
     * 我们通过Person::new来创建一个Person类构造函数的引用。Java编译器会自动地选择合适的构造函数来匹配PersonFactory.create函数的签名，并选择正确的构造函数形式。
     *
     * @throws Exception
     */
    @Test
    public void test6() throws Exception {
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.println(person);
    }

    /**
     * Lambda的范围
     * 对于lambdab表达式外部的变量，其访问权限的粒度与匿名对象的方式非常类似。你能够访问局部对应的外部区域的局部final变量，以及成员变量和静态变量。
     * 访问局部变量
     * 我们可以访问lambda表达式外部的final局部变量：
     */

    @Test
    public void test7() throws Exception {
        final int num = 1;
        Converter<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num);

        System.out.println(stringConverter.convert(2));     // 3
    }

    /**
     * 但是与匿名对象不同的是，变量num并不需要一定是final。下面的代码依然是合法的：
     */

    @Test
    public void test8() throws Exception {
        int num = 1;
        Converter<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num);

        System.out.println(stringConverter.convert(2));     // 3
    }

    /**
     * 然而，num在编译的时候被隐式地当做final变量来处理。下面的代码就不合法：
     */

    @Test
    public void test9() throws Exception {
        int num = 1;
        Converter<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num);
        /*num = 23;*/
        System.out.println(stringConverter.convert(2));     // 3
        //在lambda表达式内部企图改变num的值也是不允许的。
    }
    /**
     * 与局部变量不同，我们在lambda表达式的内部能获取到对成员变量或静态变量的读写权。这种访问行为在匿名对象里是非常典型的。
     */
}
