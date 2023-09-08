package pet;

import java.sql.Timestamp;

public class Pet {
    private int id;
    private String petName;
    private int age;
    private float weight;
    private int ownerId;
    private int petTypeId;
    private Timestamp createdAt;
    private Timestamp lastUpdated;

    // 3 constructors, getter and setter, toString method


    public Pet() {
    }

    public Pet(String petName, int age, float weight, int ownerId, int petTypeId) {
        this.petName = petName;
        this.age = age;
        this.weight = weight;
        this.ownerId = ownerId;
        this.petTypeId = petTypeId;
    }

    public Pet(int id, String petName, int age, float weight, int ownerId, int petTypeId, Timestamp createdAt, Timestamp lastUpdated) {
        this.id = id;
        this.petName = petName;
        this.age = age;
        this.weight = weight;
        this.ownerId = ownerId;
        this.petTypeId = petTypeId;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getPetTypeId() {
        return petTypeId;
    }

    public void setPetTypeId(int petTypeId) {
        this.petTypeId = petTypeId;
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
        return "Pet{" +
                "id=" + id +
                ", petName='" + petName + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", ownerId=" + ownerId +
                ", petTypeId=" + petTypeId +
                ", createdAt=" + createdAt +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
