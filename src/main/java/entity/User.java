package entity;

/**
 * This is the object user, which is used to store information on the users id,
 * username, password and weather or not it's an admins account that is logged
 * on.
 *
 * @author Temporalis
 */
public class User {

    private int id;
    private String username;
    private String password;
    private boolean admin;

    /**
     * This is a constructor for the object
     *
     * @param name the username of the user. This is the same as the username
     * field in the database
     * @param password password for the user object. Currently saved as plain
     * text this is a horrible practice which we are aware is a deadly sin in
     * coding.
     * @param admin a boolean to know if the user object is considered an
     * administrator by the system. True means it's an admin false mean it's a
     * base user.
     */
    public User(String name, String password, boolean admin) {
        this.username = name;
        this.password = password;
        this.admin = admin;
    }

    /**
     * This is an overloaded constructor for the object, this constructor also
     * takes an int which saves the id
     *
     * @param id the user_id number from the database
     * @param name the username of the user. This is the same as the username
     * field in the database
     * @param password password for the user object. Currently saved as plain
     * text this is a horrible practice which we are aware is a deadly sin in
     * coding.
     * @param admin a boolean to know if the user object is considered an
     * administrator by the system. True means it's an admin false mean it's a
     * base user.
     */
    public User(int id, String name, String password, boolean admin) {
        this.id = id;
        this.username = name;
        this.password = password;
        this.admin = admin;
    }

    /**
     * Used to get the id. This is useful to check if the stored user object
     * matches with the user object from the database.
     *
     * @return id. This returns the id int from the user object
     */
    public int getId() {
        return id;
    }

    /**
     * This method is primarily to be used for objects that are made with the
     * constructor that has no id. After the object has been created we can get
     * the id from the database and set the id in the object to match the id in
     * the object
     *
     * @param id this is the int value which is to overwrite the current
     * information in the id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Used to get the username for whatever nefarious purpose.
     *
     * @return username. This returns the string username from the object.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Updates the username of the user object to match the input. Useful if the
     * user can somehow change username. (Why the fuck would this be
     * possible???)
     *
     * @param username the input username to overwrite the stored username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the users stored password
     *
     * @return password. The password stored in the password String
     */
    public String getPassword() {
        return password;
    }

    /**
     * If we ever add the feature of changing passwords we would have to update
     * both the password in the object and in the database, this is the
     * groundwork for updating the object version of the password.
     *
     * @param password this is the new string which is to overwrite the current
     * string for password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Checks if the object is considered an admin
     *
     * @return admin. True means the user is an admin, false means it's a normal
     * user. Tiers of admins don't exist, you either are or aren't, no
     * superadmins or anything of the sort.
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Used to update the admin field. Can both remove or add the powers
     *
     * @param admin this is the new boolean value to overwrite the current admin
     * boolean.
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * Returns a string with all the information from the user object in a set
     * fashion so that it's easily comparable to other user objects. It lists
     * the id, username, password and weather or not the user is considered an
     * administrator
     *
     * @return a string with parameters added to them
     */
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + username + ", password=" + password + ", admin=" + admin + '}';
    }
}
