/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import timetrackingexam.be.User;

/**
 *
 * @author math2
 */
public class UserTest {
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetSetID(){
        User u = new User("Karl", "Børge", "kb@home.dk", "11223344", User.Role.Admin, 1);
        u.setId(4);
        assertEquals(4, u.getId());
    }
    @Test
    public void testGetSetFirstName(){
        User u = new User("Karl", "Børge", "kb@home.dk", "11223344", User.Role.Admin, 1);
        u.setFirstName("Henrik");
        assertEquals("Henrik", u.getFirstName());
    }
    @Test
    public void testGetSetLastName(){
        User u = new User("Karl", "Børge", "kb@home.dk", "11223344", User.Role.Admin, 1);
        u.setLastName("Knudsen");
        assertEquals("Knudsen", u.getLastName());
    }
    @Test
    public void testGetEmail(){
        User u = new User("Karl", "Børge", "kb@home.dk", "11223344", User.Role.Admin, 1);
        assertEquals("kb@home.dk", u.getEmail());
    }
    @Test
    public void testGetSetPassword(){
        User u = new User("Karl", "Børge", "kb@home.dk", "11223344", User.Role.Admin, 1);
        u.setPassword("1337");
        assertEquals("1337", u.getPassword());
    }
    @Test
    public void testGetSetRole(){
        User u = new User("Karl", "Børge", "kb@home.dk", "11223344", User.Role.Admin, 1);
        u.setRole(User.Role.Default);
        assertEquals(User.Role.Default, u.getRole());
    }
}
