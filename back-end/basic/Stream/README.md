## Stream流

#### 一、遍历/匹配 foreach/find/match

```java
package com.sw.demo.stream;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 1.遍历/匹配（foreach/find/match）
 */
public class foreach01 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 77, 5, 4, 33, 677, 86, 43);

        //遍历输出符合条件的元素
        list.stream().filter(x -> x > 6).forEach(System.out::println);
        //匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        //匹配任意一个元素（适用于并行流）
        Optional<Integer> findAny = list.stream().filter(x -> x > 6).findAny();
        //是否包含符合指定条件的元素
        boolean flag = list.stream().anyMatch(x -> x > 6);
        System.out.println("匹配第一个元素" + findFirst.get());
        System.out.println("匹配任意一个元素" + findAny.get());
        System.out.println("是否存在大于6的值" + flag);
    }
}

```



#### 二、筛选 filter

按照一定的规则校验流中的元素，将符合条件的元素提取到新的流中

```java
package com.sw.demo.stream;


import com.sw.demo.pojo.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 2.筛选员工中工资高于8000的人，并形成新的集合
 */
public class filter02 {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 18, "male", "昆明"));
        personList.add(new Person("Jack", 7000, 19, "male", "楚雄"));
        personList.add(new Person("Lily", 7800, 20, "female", "楚雄"));
        personList.add(new Person("Anni", 8200, 21, "female", "大理"));
        personList.add(new Person("Owen", 9500, 22, "male", "昆明"));
        personList.add(new Person("Alisa", 7900, 22, "female", "保山"));

        List<String> result = personList.stream().filter(x -> x.getSalary()>8000).map(Person::getName).collect(Collectors.toList());
        System.out.println("工资大于8000的员工："+result);
    }
}

```



#### 三、聚合 max/min/count

与MySQL中聚合函数的使用类似

```java
package com.sw.demo.stream;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * 3.聚合（max/min/count)
 */
public class polymerization03 {
    public static void main(String[] args) {

        //例1：获取String集合中最长的元素
        List<String> list = Arrays.asList("abc", "dcdf", "nihao", "xiexieni", "hello");

        Optional<String> max = list.stream().max(Comparator.comparing(String::length));
        //System.out.println("最长的元素为：" + max);


        //例2：获取最大值，并按自定义格式排序
        List<Integer> list2 = Arrays.asList(1, 55, 22, 8, 90, 777, 45);

        //自然排序
        Optional<Integer> max01 = list2.stream().max(Integer::compareTo);
        //自定义排序
        Optional<Integer> max02 = list2.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        //System.out.println("自然排序" + max01);
        //System.out.println("自定义排序" + max02);


        //例3：获取员工工资最高的人（同理获取String集合中最长的元素）


        //例4：计算集合中有几个大于6的数
        List<Integer> list4 = Arrays.asList(5, 6, 8, 1, 2, 55, 22);

        long count = list4.stream().filter(x -> x > 6).count();
        System.out.println(count);
    }
}

```



#### 四、映射 map/flatMap

map：接收一个函数作为参数该函数会被应用到每个元素上并将其映射成一个新的元素

flatMap：接收一个函数作为参数，并将流中的每个值都换成另一个流，然后把所有流连接成一个新的流

```java
package com.sw.demo.stream;


import com.sw.demo.pojo.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 4.映射(map/flatMap)
 * 映射可以将一个流的元素按照一定的规则映射到另一个流中
 * map：接受一个函数作为参数，该函数会被应用到每个函数上，并将其映射成一个新的函数
 * flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
 */
public class mapping04 {
    public static void main(String[] args) {
        //例1：将数组元素转换为大写/每个元素+3
        String[] str = {"abc", "bde", "cfg"};
        List<String> list01 = Arrays.stream(str).map(String::toUpperCase).collect(Collectors.toList());

        List<Integer> list02 = Arrays.asList(1, 2, 3, 6, 7);
        List<Integer> plus = list02.stream().map(x -> x + 3).collect(Collectors.toList());

        //System.out.println("字符转换为大写：" + list01);
        //System.out.println("每个值+3：" + plus);

        //例2：每个员工的薪资上涨1000
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 18, "male", "昆明"));
        personList.add(new Person("Jack", 7000, 19, "male", "楚雄"));
        personList.add(new Person("Lily", 7800, 20, "female", "楚雄"));
        personList.add(new Person("Anni", 8200, 21, "female", "大理"));
        personList.add(new Person("Owen", 9500, 22, "male", "昆明"));
        personList.add(new Person("Alisa", 7900, 22, "female", "保山"));

        //不改变原来员工集合的方式
        List<Person> newPersonList = personList.stream().map(person -> {
            Person newPerson = new Person(person.getName(), 0, 0, null, null);
            newPerson.setSalary(person.getSalary() + 1000);
            return newPerson;
        }).collect(Collectors.toList());
        //System.out.println("改动前：" + personList.get(0).getName() + "----->" + personList.get(0).getSalary());
        //System.out.println("改动后：" + newPersonList.get(0).getName() + "----->" + newPersonList.get(0).getSalary());

        //改变了原来员工集合的方式
        List<Person> newPersonList1 = personList.stream().map(person -> {
            person.setSalary(person.getSalary() + 1000);
            return person;
        }).collect(Collectors.toList());
        //System.out.println("二次改动前：" + personList.get(0).getName() + "----->" + personList.get(0).getSalary());
        //System.out.println("二次改动后：" + newPersonList1.get(0).getName() + "----->" + newPersonList1.get(0).getSalary());
        /*
        二次改动前：Tom----->9900（直接在personList的基础上操作，没有new对象）
        二次改动后：Tom----->9900
         */


        //例3：将两个字符串合并为1个新的字符串
        List<String> list03 = Arrays.asList("a,s,a,s", "a,s,1,2,d");
        List<String> newList03 = list03.stream().flatMap(s -> {
            //将每个元素转换为一个stream
            String[] split = s.split(",");
            Stream<String> s01 = Arrays.stream(split);
            return s01;
        }).collect(Collectors.toList());

        System.out.println("处理集合前：" + list03);
        System.out.println("处理集合后：" + newList03);
        /*
        处理集合前：[a,s,a,s, a,s,1,2,d]
        处理集合后：[a, s, a, s, a, s, 1, 2, d]
         */
    }
}

```



#### 五、归约 reduce

把一个流缩减成一个值，实现对集合的求和、乘积、最值等操作

```java
package com.sw.demo.stream;


import com.sw.demo.pojo.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 5.规约
 * 规约也称缩减，即把一个流缩减为一个值，实现对集合求和、乘积、最值
 */
public class reduce05 {
    public static void main(String[] args) {

        //例1：
        List<Integer> list = Arrays.asList(5, 8, 88, 9, 555);

        //求和方式一：
        Optional<Integer> sum1 = list.stream().reduce((x, y) -> x + y);
        //求和方式二：
        Optional<Integer> sum2 = list.stream().reduce(Integer::sum);
        //求和方式三：
        Integer sum3 = list.stream().reduce(0, Integer::sum);

        //乘积
        Optional<Integer> product = list.stream().reduce((x, y) -> x * y);

        //求最大值方式一
        Optional<Integer> max1 = list.stream().reduce((x, y) -> x > y ? x : y);
        //求最大值方式er
        Integer max2 = list.stream().reduce(1, Integer::max);

        //System.out.println("求和：" + sum1.get() + "," + sum2.get() + "," + sum3);
        //System.out.println("乘积：" + product.get());
        //System.out.println("最值：" + max1 + "," + max2);


        //例2：求所有员工的工资之和，最高工资
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 18, "male", "昆明"));
        personList.add(new Person("Jack", 7000, 19, "male", "楚雄"));
        personList.add(new Person("Lily", 7800, 20, "female", "楚雄"));
        personList.add(new Person("Anni", 8200, 21, "female", "大理"));
        personList.add(new Person("Owen", 9500, 22, "male", "昆明"));
        personList.add(new Person("Alisa", 7900, 22, "female", "保山"));

        //求和1：
        Optional<Integer> s1 = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        //方式2：
        Integer s2 = personList.stream().reduce(0, (s, p) -> s += p.getSalary(), (sa1, sa2) -> sa1 + sa2);
        //方式3:
        Integer s3 = personList.stream().reduce(0, (s, p) -> s += p.getSalary(), Integer::sum);

        //求工资最高的人方式一
        Integer smax1 = personList.stream().reduce(0, (m, p) -> m > p.getSalary() ? m : p.getSalary(), Integer::max);
        //求工资最高的人方式二
        Integer smax2 = personList.stream().reduce(0, (m, p) -> m > p.getSalary() ? m : p.getSalary(), (x, y) -> x > y ? x : y);

        System.out.println("工资之和："+s1+","+s2+","+s3);
        System.out.println("工资最值："+smax1+","+smax2);
    }
}

```



#### 六、收集 collect

##### 1.归集 toList/toSet/toMap

因为流不存储数据，在流中的数据处理完之后，要将流中的数据重新归到新的集合里

```java
package com.sw.demo.stream;


import com.sw.demo.pojo.Person;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 6.收集（collect）
 * 将流收集为一个值或一个新的集合
 * collect主要依赖java.util.stream.Collectors类内置的静态方法
 */
public class collect01 {
    public static void main(String[] args) {

        /*
        6.1 归集：因为流不存储数据，在流中的数据处理完之后，需要将流中的数据重新归集到新的集合里
        toList(),toSet(),toMap()
         */
        //toList/toSet
        List<Integer> list = Arrays.asList(1, 6, 3, 4, 6, 7, 9, 6, 20);
        List<Integer> newList = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        //去重
        Set<Integer> newSet = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toSet());

        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 18, "male", "昆明"));
        personList.add(new Person("Jack", 7000, 19, "male", "楚雄"));
        personList.add(new Person("Lily", 7800, 20, "female", "楚雄"));
        personList.add(new Person("Anni", 8200, 21, "female", "大理"));
        personList.add(new Person("Owen", 9500, 22, "male", "昆明"));
        personList.add(new Person("Alisa", 7900, 22, "female", "保山"));

        Map<?, Person> map = personList.stream().filter(p -> p.getSalary() > 8000).collect(Collectors.toMap(Person::getName, p -> p));

        System.out.println("toList:" + newList); //toList:[6, 4, 6, 6, 20]
        System.out.println("toSet" + newSet); //toSet[4, 20, 6]
        System.out.println("toMap:" + map); //key:name - value:Person
    }
}

```



##### 2.统计 count/averaging

Collectors提供了一系列用于数据统计的静态方法

+ 计数 `count`
+ 平均值 `averagingInt`，`averagingLong`，`averagingDouble`
+ 最值 `maxBy`，`minBy`
+ 求和 `summingInt`，`summingLong`，`summingDouble`
+ 统计以上所有 `summarizingInt`，`summarizingLong`，`summarizingDouble`

```java
package com.sw.demo.stream;


import com.sw.demo.pojo.Person;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 6.2 统计（count/averaging）
 * 计数 count
 * 平均值 averagingInt averagingLong averagingDouble
 * 最值 maxBy minBy
 * 求和 summingInt summingLong summingDouble
 * 统计以上所有 summarizingInt summarizingLong summarizingDouble
 */
public class collect02 {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 18, "male", "昆明"));
        personList.add(new Person("Jack", 7000, 19, "male", "楚雄"));
        personList.add(new Person("Lily", 7800, 20, "female", "楚雄"));
        personList.add(new Person("Anni", 8200, 21, "female", "大理"));
        personList.add(new Person("Owen", 9500, 22, "male", "昆明"));
        personList.add(new Person("Alisa", 7900, 22, "female", "保山"));

        //求总数
        Long count = personList.stream().collect(Collectors.counting());
        //平均工资
        Double salary = personList.stream().collect(Collectors.averagingDouble(Person::getSalary));
        //最高工资
        Optional<Integer> max = personList.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compareTo));
        //工资之和
        Integer sum = personList.stream().collect(Collectors.summingInt(Person::getSalary));
        //统计所有信息
        DoubleSummaryStatistics collect = personList.stream().collect(Collectors.summarizingDouble(Person::getSalary));

        System.out.println("员工总数：" + count);
        System.out.println("平均工资：" + salary);
        System.out.println("最高工资：" + max);
        System.out.println("工资之和：" + sum);
        System.out.println("所有信息：" + collect);
    }

}

```



##### 3.分组 partitioningBy/groupingBy

分区：将stream按条件分为两个map，例：按性别将员工分组

分组：将集合分为多个map，例：按地区分组

```java
package com.sw.demo.stream;

import com.sw.demo.pojo.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 6.3 分组(partitioningBy/groupingBy)
 * 分区：将stream按条件分为两个Map
 * 分组：将集合分为多个Map，如按部门，地区分组
 */
public class collect03 {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 18, "male", "昆明"));
        personList.add(new Person("Jack", 7000, 19, "male", "楚雄"));
        personList.add(new Person("Lily", 7800, 20, "female", "楚雄"));
        personList.add(new Person("Anni", 8200, 21, "female", "大理"));
        personList.add(new Person("Owen", 9500, 22, "male", "昆明"));
        personList.add(new Person("Alisa", 7900, 22, "female", "保山"));

        //按员工薪资高于8000分组 key:boolean - value:List
        Map<Boolean, List<Person>> part = personList.stream().collect(Collectors.groupingBy(x -> x.getSalary() > 8000));
        //按性别分组 key:String - value:List
        Map<String, List<Person>> sex = personList.stream().collect(Collectors.groupingBy(Person::getSex));
        //先按性别分组，再按地区分组 key:String - value:Map
        Map<String, Map<String, List<Person>>> area = personList.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getArea)));
        System.out.println("薪资高于8000的员工："+part);
        System.out.println("按性别分组："+sex);
        System.out.println("按地区分组："+area);
    }
}

```



##### 4.接合 joining

将stream中的元素用特定的连接符连接成一个字符串（如果没有连接符，则直接连接）

```java
package com.sw.demo.stream;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 6.4 接合(joining)
 * joining可以将stream中的元素用特定的连接符连接成一个字符串（如果没有连接符，则直接连接）
 */
public class collect04 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "1", "b", "2", "c", "3", "d", "4");
        String joining = list.stream().collect(Collectors.joining("-->"));
        System.out.println(joining);

    }
}

```



##### 5.归约 reducing

Collectors类提供的reducing方法，相比于stream本身的reduce方法，增加了对自定义归约的支持

```java
package com.sw.demo.stream;

import com.sw.demo.pojo.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 6.5 规约(reducing)
 * Collectors类提供的reducing方法相较于stream本身的reduce方法，增加了对自定义规约的支持
 */
public class collect05 {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 18, "male", "昆明"));
        personList.add(new Person("Jack", 7000, 19, "male", "楚雄"));
        personList.add(new Person("Lily", 7800, 20, "female", "楚雄"));
        personList.add(new Person("Anni", 8200, 21, "female", "大理"));
        personList.add(new Person("Owen", 9500, 22, "male", "昆明"));
        personList.add(new Person("Alisa", 7900, 22, "female", "保山"));

        //每位员工增加50块奖金，求发奖金之后所有员工的薪资之和
        Integer sum = personList.stream().collect(Collectors.reducing(0, Person::getSalary, (x, y) -> (x + y + 50)));
        System.out.println("发奖金之后的薪资之和：" + sum);

        //stream的reduce方法，求员工薪资之和
        Optional<Integer> reduceSum = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        System.out.println("员工薪资之和(reduce方法)："+reduceSum);
    }
}

```



#### 七、排序 sorted

sorted()：自然排序，流中元素需实现Comparable接口

sorted(Comparator com)：自定义排序

```java
package com.sw.demo.stream;


import com.sw.demo.pojo.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 7 排序（sort）
 * sorted():自然排序，流中元素实现Comparable接口
 * sorted(Comparator com):Comparator排序器实现自定义排序
 */
public class sorted07 {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 18, "male", "昆明"));
        personList.add(new Person("Jack", 7000, 19, "male", "楚雄"));
        personList.add(new Person("Lily", 7800, 20, "female", "楚雄"));
        personList.add(new Person("Anni", 8900, 21, "female", "大理"));
        personList.add(new Person("Owen", 9500, 22, "male", "昆明"));
        personList.add(new Person("Alisa", 7900, 22, "female", "保山"));

        //例：按薪资排序（工资相同的按年龄比较）

        //升序排列（自然排序）
        List<String> listASC = personList.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName).collect(Collectors.toList());
        //降序排列（升序反转即为降序）
        List<String> listDESC = personList.stream().sorted(Comparator.comparing(Person::getSalary).reversed()).map(Person::getName).collect(Collectors.toList());

        //先按工资，再按年龄升序排列(升序)
        List<String> listASC01 = personList.stream().sorted(Comparator.comparing(Person::getSalary).thenComparing(Person::getAge)).map(Person::getName).collect(Collectors.toList());
        //先按工资再按年龄自定义排序（降序）
        /*
        debug流程（降序）：两两比较，a>b，a存起来,b继续跟c比较，如果b<c，则继续比较a、c两个数，否则反之
         */
        List<String> diy = personList.stream().sorted((p1, p2) -> {
            if (p1.getSalary() == p2.getSalary()) {
                //System.out.println(p2.getAge() - p1.getAge());
                return p2.getAge() - p1.getAge();
            } else {
                //System.out.println(p2.getSalary() - p1.getSalary());
                return p2.getSalary() - p1.getSalary();
            }
        }).map(Person::getName).collect(Collectors.toList());

        System.out.println("自然排序："+listASC);
        System.out.println("工资降序："+listDESC);
        System.out.println("先按工资再按年龄自然排序："+listASC01);
        System.out.println("自定义排序："+diy);
    }
}

```



#### 八、提取/组合

concat合并，distinct去重，limit限制，skip跳过

```java
package com.sw.demo.stream;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 8.提取/组合
 * 对流进行合并、去重（distinct）、限制（limit）、跳过（skip）等操作
 */
public class extract08 {
    public static void main(String[] args) {
        String[] arr1 = {"a", "b", "d", "r", "a"};
        String[] arr2 = {"g", "r", "d", "b", "c"};

        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);

        //合并两个流，并去重
        List<String> concatDistinct = Stream.concat(stream1, stream2).distinct().collect(Collectors.toList());

        //limit 限制只能从流中获取前n个数
        List<Integer> limit = Stream.iterate(1, x -> x + 1).limit(10).collect(Collectors.toList());

        //skip 跳过前n个数据，注：需加上limit限制，否则iterate构造器会一直创建数据
        List<Integer> skip = Stream.iterate(1, x -> x + 2).skip(1).limit(5).collect(Collectors.toList());

        System.out.println("合并流：" + concatDistinct);
        System.out.println("限制只能从流中获取前10个数：" + limit);
        System.out.println("跳过第一个数据：" + skip);
    }
}

```

注：全文参考https://blog.csdn.net/mu_wind/article/details/109516995