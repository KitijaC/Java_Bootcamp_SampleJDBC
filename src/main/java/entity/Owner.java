package entity;

import java.sql.Timestamp;

public class Owner {
    private int id;
    private String ownerName;
    private int age;
    private String email;
    private Timestamp createdAt;
    private Timestamp lastUpdated;


    public Owner() {
    }

    public Owner(String ownerName, int age, String email) {
        this.ownerName = ownerName;
        this.age = age;
        this.email = email;
    }

    public Owner(int id, String ownerName, int age, String email, Timestamp createdAt, Timestamp lastUpdated) {
        this.id = id;
        this.ownerName = ownerName;
        this.age = age;
        this.email = email;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", ownerName='" + ownerName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
