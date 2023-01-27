import java.util.Objects;
import java.util.OptionalInt;

// Задание по паттерну Builder

public class Person {
    private final String name;
    private final String surname;
    private int age;
    private String adress;

    private Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    private Person(String name, String surname, int age) {
        this(name, surname);
        this.age = age;
    }

    private Person(String name, String surname, int age, String adresss) {
        this(name, surname, age);
        this.adress = adresss;
    }

    public boolean hasAge() {
        return getAge().isPresent();
    }

    public boolean hasAddress() {
        return adress != null;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public OptionalInt getAge() {
        return OptionalInt.of(age);
    }

    public String getAddress() {
        return adress;
    }

    public void setAddress(String address) {
        this.adress = address;
    }

    public void happyBirthday() {
        if (hasAge()) age += 1;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append(" ").append(surname).append(" из города ").append(adress);
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age, adress);
    }

    public PersonBuilder newChildBuilder() {
        return new PersonBuilder()
                .setSurname(surname)
                .setAge(0)
                .setAddress(adress);
    }

    public static class PersonBuilder {

        private String name;
        private String surname;
        private int age;
        private String adress = "";

        public PersonBuilder() {
        }

        public PersonBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public PersonBuilder setAge(int age) {
            if (0 <= age && age < 120) {
                this.age = age;
            } else {
                throw (new IllegalArgumentException("Недопустимый возраст"));
            }
            return this;
        }

        public PersonBuilder setAddress(String adress) {
            this.adress = adress;
            return this;
        }

        private OptionalInt getAge() {
            return OptionalInt.of(age);
        }

        private boolean hasAge() {
            return getAge().isPresent();
        }

        public Person build() {
            if (name == null) {
                throw (new IllegalStateException("Не задано имя"));
            } else if (surname == null) {
                throw (new IllegalArgumentException("Не задана Фамилия"));
            } else {
                if (!hasAge()) {
                    return new Person(name, surname);
                } else {
                    if (adress.equals("")) {
                        return new Person(name, surname, age);
                    } else {
                        return new Person(name, surname, age, adress);
                    }
                }
            }
        }
    }
}