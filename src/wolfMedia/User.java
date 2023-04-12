package wolfMedia;
import java.sql.*;

public class User {

    private String userID;
    private String phone;
    private String email;
    private Date registrationDate;
    private float monthlySubscriptionFee;
    private String statusOfSubscription;
    private String firstName;
    private String lastName;

    public User(String userID, String phone, String email, Date registrationDate, float monthlySubscriptionFee,
            String statusOfSubscription, String firstName, String lastName) {
        this.userID = userID;
        this.phone = phone;
        this.email = email;
        this.registrationDate = registrationDate;
        this.monthlySubscriptionFee = monthlySubscriptionFee;
        this.statusOfSubscription = statusOfSubscription;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public float getMonthlySubscriptionFee() {
        return monthlySubscriptionFee;
    }

    public void setMonthlySubscriptionFee(float monthlySubscriptionFee) {
        this.monthlySubscriptionFee = monthlySubscriptionFee;
    }

    public String getStatusOfSubscription() {
        return statusOfSubscription;
    }

    public void setStatusOfSubscription(String statusOfSubscription) {
        this.statusOfSubscription = statusOfSubscription;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static int createUser(User user, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO users (userID, phone, email, registrationDate, monthlySubscriptionFee, statusOfSubscription, firstName, lastName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getUserID());
            statement.setString(2, user.getPhone());
            statement.setString(3, user.getEmail());
            statement.setDate(4, user.getRegistrationDate());
            statement.setFloat(5, user.getMonthlySubscriptionFee());
            statement.setString(6, user.getStatusOfSubscription());
            statement.setString(7, user.getFirstName());
            statement.setString(8, user.getLastName());
            isInserted = statement.executeUpdate();
            System.out.println("User created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating user: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    
    public User readUser(String userID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE userID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getString("userID"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getDate("registrationDate"),
                        resultSet.getFloat("monthlySubscriptionFee"),
                        resultSet.getString("statusOfSubscription"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return user;
    }
    
    public static int updateUser(User user, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE users SET phone = ?, email = ?, registrationDate = ?, monthlySubscriptionFee = ?, statusOfSubscription = ?, firstName = ?, lastName = ? WHERE userID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getPhone());
            statement.setString(2, user.getEmail());
            statement.setDate(3, user.getRegistrationDate());
            statement.setFloat(4, user.getMonthlySubscriptionFee());
            statement.setString(5, user.getStatusOfSubscription());
            statement.setString(6, user.getFirstName());
            statement.setString(7, user.getLastName());
            statement.setString(8, user.getUserID());
            isUpdated = statement.executeUpdate();
            return isUpdated;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    
    public static int deleteUser(String userID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM users WHERE userID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            isDeleted = statement.executeUpdate();
            return isDeleted;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }    
}

// package  wolfMedia;

// import java.sql.*;


// public class User {
//     private int userId;
//     private String first_name;
//     private String last_name;
//     private String email;
//     private String subscriptionStatus;
//     private String phone;
//     private java.sql.Date registrationDate;

//     public User(String email, String first_name, String last_name, String subscriptionStatus, String phone, java.sql.Date registrationDate){

//         this.email=email;
//         this.first_name=first_name;
//         this.last_name=last_name;
//         this.subscriptionStatus=subscriptionStatus;
//         this.phone=phone;
//         this.registrationDate=registrationDate;
//     }
//     public User(int userId){
//         this.userId=userId;
//     }
//     public boolean Insertion(User user, Connection conn){
//         try{
//             PreparedStatement statement=conn.prepareStatement("INSERT INTO kgopala3.User (Email, Phone, SubscriptionStatus, LastName, RegistrationDate, FirstName) VALUES (?,?,?,?,?,?,?)");
//             statement.setString(1, user.getEMail());
//             statement.setString(2, user.getPhone());
//             statement.setString(3, user.getSubscriptionStatus());
//             statement.setString(4, user.getLastName());
//             statement.setDate(5, user.getRegistrationDate());
//             statement.setString(6, user.getFirstName());
//             int isinserted=statement.executeUpdate();
//             if(isinserted==1){
//                 return true;
//             }
//             else{
//                 return false;
//             }
//         } catch(SQLException e){
//             e.printStackTrace();
//             return false;
//         }
//     }

//     public boolean Deletion(int userId, Connection conn){
//         try{
//            PreparedStatement statement=conn.prepareStatement("DELETE FROM kgopala3.User WHERE UserID=?");
//            statement.setInt(1, userId);
//            int numrowsDeleted=statement.executeUpdate();
//             if(numrowsDeleted>0){
//                 return true;
//             }
//             else{
//                 return false;
//             }
//         } catch(SQLException e){
//             e.printStackTrace();
//             return false;
//         }
//     }

//     public User ReadEntry(int userId, Connection conn){
//         try{
//             PreparedStatement statement=conn.prepareStatement("SELECT * FROM kgopala3.User WHERE UserID=?");
//             statement.setInt(1, userId);
//             ResultSet result = statement.executeQuery();
//             if(result.next()){
//                 User user = new User(result.getString("Email"), result.getString("FirstName"), result.getString("LastName"), result.getString("SubscriptionStatus"), result.getString("Phone"), new java.sql.Date(result.getDate("RegistrationDate").getTime()));
//                 user.setUserId(userId);
//                 return user;
//             }
//             else{
//                 return null;
//             }
//         } catch(SQLException e){
//             e.printStackTrace();
//             return null;
//         }
//     }    
    
//     public static int getUserId(){
//         return userId;
//     }
//     public void setUserId(int userId){
//         this.userId=userId;
//     }
//     public String getFirstName(){
//         return first_name;
//     }
//     public void setFirstName(String first_name){
//         this.first_name=first_name;
//     }
//     public String getLastName(){
//         return last_name;
//     }
//     public void setLastName(String last_name){
//         this.last_name=last_name;
//     }
//     public String getEMail(){
//         return email;
//     }
//     public void setEMail(String email){
//         this.email=email;
//     }
//     public String getSubscriptionStatus(){
//         return subscriptionStatus;
//     }
//     public void setSubscriptionStatus(String subscriptionStatus){
//         this.subscriptionStatus=subscriptionStatus;
//     }
//     public String getPhone(){
//         return phone;
//     }
//     public void setPhone(String phone){
//         this.phone=phone;
//     }
//     public java.sql.Date getRegistrationDate(){
//         return registrationDate;
//     }
//     public void setRegistrationDate(java.sql.Date registrationDate){
//         this.registrationDate=registrationDate;
//     }

// }