package wolfMedia;

public class UserPayment {
    private String paymentID;
    private int paymentAmount;

    public UserPayment(String paymentID, int paymentAmount) {
        this.paymentID = paymentID;
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public int createUserPayment(UserPayment userPayment, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isInserted = 0;
        try {
            String query = "INSERT INTO userPayments (paymentID, paymentAmount) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, userPayment.getPaymentID());
            statement.setInt(2, userPayment.getPaymentAmount());
            isInserted = statement.executeUpdate();
            System.out.println("User payment created.");
            return isInserted;
        } catch (SQLException ex) {
            System.out.println("Error creating user payment: " + ex.getMessage());
            return 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    
    public UserPayment readUserPayment(String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        UserPayment userPayment = null;
        try {
            String query = "SELECT * FROM userPayments WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, paymentID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userPayment = new UserPayment(resultSet.getString("paymentID"),
                        resultSet.getInt("paymentAmount"));
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
        return userPayment;
    }
    
    public int updateUserPayment(UserPayment userPayment, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isUpdated = 0;
        try {
            String query = "UPDATE userPayments SET paymentAmount = ? WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, userPayment.getPaymentAmount());
            statement.setString(2, userPayment.getPaymentID());
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
    
    public int deleteUserPayment(String paymentID, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        int isDeleted = 0;
        try {
            String query = "DELETE FROM userPayments WHERE paymentID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, paymentID);
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
