package corp.ny.com.tuttifrutti;

import corp.ny.com.rufus.database.Model;
import corp.ny.com.rufus.database.annotation.Column;
import corp.ny.com.rufus.database.annotation.Table;

/**
 * Created by Yann Yvan CEO of N.Y. Corp. on 20/04/18.
 */
@Table
public class User extends Model<User> {
    @Column(increment = true, primary = true)
    private int id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String deviceToken;
    @Column
    private String profilePicture;
    @Column(nullable = true)
    private String phone;

    public static User getInstance() {
        User user = new User();
        user.setId(1);
        user.setName("Yann");
        user.setEmail("email");
        user.setPassword("Password");
        user.setDeviceToken("Token");
        user.setProfilePicture("Profile");
        user.setPhone("Phone");
        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

}
