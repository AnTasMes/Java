class HelloWorld {
    public static void main(String[] args) {
        Person p1 = new Person();

        p1.SetFirstName("Andrej");
        p1.SetLastName("Tasevski");

        p1.PrintPerson();

        Student s1 = new Student("Marko", "Markovic", "112");

        s1.PrintPerson();
    }
}

class Person {
    private String _first_name;
    private String _last_name;

    public Person(String first_name, String last_name) {
        this._first_name = first_name;
        this._last_name = last_name;
    }

    public Person() {
        this._first_name = "";
        this._last_name = "";
    }

    public void SetFirstName(String first) {
        this._first_name = first;
    }

    public void SetLastName(String last) {
        this._last_name = last;
    }

    public void PrintPerson() {
        System.out.println("Person: " + this._first_name + " " + this._last_name);
    }
}

class Student extends Person {
    public String S_Number;

    public Student(String first_name, String last_name, String S_Number) {
        super(first_name, last_name);

        this.S_Number = S_Number;
    }
}