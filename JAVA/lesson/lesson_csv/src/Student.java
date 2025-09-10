public class Student {
    private String firstName;
    private String lastName;
    private String group;
    private String age;

    public Student(String firstName, String lastName, String group, String age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
        this.age = age;
    }

    @Override
    public String toString() {
        return firstName + ", " + lastName + ", " + group + ", " + age + ";";
    }

    public String toCsv(){
        return firstName + "," + lastName + "," + group + "," + age;
    }

    public static Student fromCsv(String line){
        String[] csvArr = line.split(",");
        return new Student(csvArr[0], csvArr[1], csvArr[2], csvArr[3]);
    }


}
