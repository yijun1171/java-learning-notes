package org.yj.concurrent.io;

import java.io.*;

/**
 * Created by yj on 15-5-1.
 */
public class SerializeTest {

    static class User implements Serializable{
        String name;
        int age;
        Address address;

        User(String name, int age, Address address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", address=" + address +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }
    }

    static class Address implements Serializable{
        String provience;
        String city;

        Address(String provience, String city) {
            this.provience = provience;
            this.city = city;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "provience='" + provience + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }

        public String getProvience() {
            return provience;
        }

        public void setProvience(String provience) {
            this.provience = provience;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Address add = new Address("sichuan", "dazhou");
        User user = new User("yj", 22, add);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream(1024);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(buffer);
            oos.writeObject(user);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                oos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(buffer.toByteArray());
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(bais);
            User yj = (User) ois.readObject();
            System.out.println(yj);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                ois.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
