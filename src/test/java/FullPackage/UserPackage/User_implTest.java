package FullPackage.UserPackage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class User_implTest {
    User_impl userImpl;
    @BeforeEach
    void setUp() {
        userImpl= new User_impl();
    }

    @AfterEach
    void tearDown() {
        userImpl = null;
    }

    @Test
    public void testLoginUser() throws SQLException {
        assertTrue(userImpl.loginUser(1234, "Anup@12"));
    }
}