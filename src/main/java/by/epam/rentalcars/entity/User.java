package by.epam.rentalcars.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @Column
    public String email;

    @Column(name = "firstName")
    public String firstName;

    @Column(name = "lastName")
    public String lastName;

    @Column
    private String password;

    @Column(name = "passportId")
    public String passportId;

    @Column
    public boolean admin;

    @Column
    public String phone;

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
