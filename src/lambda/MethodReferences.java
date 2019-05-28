package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

//java8方法引用（Method reference）暂时来说主要用途为简写某些语句的写法和在Lambda表达式中
//可以用于以下种方法上：
//Integer::parseInt //静态方法引用
//实例方法引用，以下例子是用来减少Lambda语句的复杂性
//stream.forEach(System.out::print); = stream.forEach(x -> System.out.print(x)); 
//构造器引用，以下例子是减少了Lambda语句的复杂性
//strList.stream().map(Integer::new); = strList.stream().map(s -> new Integer(s));       

public class MethodReferences {

	public static void main(String[] args) {
		// 方法引用::引用构造函数
        PersonFactory factory = new PersonFactory(Person::new);

        List<Person> personList = new ArrayList<Person>();

        Person p1 = factory.getPerson();
        p1.setName("Kobe");
        personList.add(p1);
        Person p2 = factory.getPerson();
        p2.setName("James");
        personList.add(p2);
        Person p3 = factory.getPerson();
        p3.setName("Paul");
        personList.add(p3);

        Person[] persons1 = personList.toArray(new Person[personList.size()]);
        System.out.print("排序前: ");
        printArray(persons1);

        // 方法引用::引用静态方法
        Arrays.sort(persons1, lambda.MethodReferences::myCompare);
        System.out.print("排序后: ");
        printArray(persons1);
        System.out.println();

        Person[] persons2 = personList.toArray(new Person[personList.size()]);
        System.out.print("排序前: ");
        printArray(persons2);

        // 方法引用::用特定对象的实例方法
        Arrays.sort(persons2, p1::compare);
        System.out.print("排序后: ");
        printArray(persons2);
        System.out.println();

        Person[] persons3 = personList.toArray(new Person[personList.size()]);
        System.out.print("排序前: ");
        printArray(persons3);

        // 方法引用::引用特定类型的任意对象的实例方法
        Arrays.sort(persons3, Person::compareTo);
        System.out.print("排序后: ");
        printArray(persons3);
    }

    public static void printArray(Person[] persons) {
        for (Person p : persons) {
            System.out.print(p.name + "  ");
        }
        System.out.println();
    }

    public static int myCompare(Person p1, Person p2) {
        return p1.getName().compareTo(p2.getName());
    }

    static class Person {
        private String name;

        public Person() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int compare(Person p1, Person p2) {
            return p1.getName().compareTo(p2.getName());
        }

        public int compareTo(Person p) {
            return this.getName().compareTo(p.getName());
        }
    }

    static class PersonFactory {
        private Supplier<Person> supplier;

        public PersonFactory(Supplier<Person> supplier) {
            this.supplier = supplier;
        }

        public Person getPerson() {
            return supplier.get();
        }

	}

}
