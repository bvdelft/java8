import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class RosterTest {

    public static void printPersonsOlderThan(List<Person> roster, int age) {        
        for (Person p : roster) {
            if (p.getAge() >= age) {
                p.printPerson();
            }
        }
    }
    
    public static void printPersons(List<Person> roster, CheckPerson tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }
    
    public static void printPersonsPredicate(List<Person> roster, Predicate<Person> tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }
    
    public static void processPersons(
            List<Person> roster,
            Predicate<Person> tester,
            Consumer<Person> block) {
        for (Person p : roster) {
            if (tester.test(p)) {
                block.accept(p);
            }
        }
    }
    
    
    public static void main(String[] arg) {
        List<Person> roster = Person.createRoster();        
        RosterTest.printPersonsOlderThan(roster, 20);
        System.out.println("-----");
        RosterTest.printPersons(roster, new CheckPersonForService());
        System.out.println("-----");
         RosterTest.printPersons(roster, new CheckPerson() {
           public boolean test(Person p) {
             System.out.print(roster.size());
             return true;
           }
         });
        System.out.println("-----");
        RosterTest.printPersons(
            roster,
            (Person p) -> p.getGender() == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 25
        );
        System.out.println("-----");
        RosterTest.printPersonsPredicate(
            roster,
            p -> p.getGender() == Person.Sex.FEMALE
        );
        System.out.println("-----");
        RosterTest.processPersons(
            roster,
            p -> p.getGender() == Person.Sex.FEMALE,
            p -> { System.out.print(p.getName() + " :) ");
                   System.out.println(roster.size()); }
        );
    }

}
