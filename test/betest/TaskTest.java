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
import timetrackingexam.be.Task;

/**
 *
 * @author math2
 */
public class TaskTest {
    
    public TaskTest() {
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
        Task t = new Task(1, 4, "TestTask", "Testing");
        t.setId(1);
        assertEquals(1, t.getId());
    }
    
    @Test
    public void testGetSetName(){
        Task t = new Task(1, 4, "TestTask", "Testing");
        t.setName("TestingTask");
        assertEquals("TestingTask", t.getName());
    }
    
    @Test
    public void testGetSetDescription(){
        Task t = new Task(1, 4, "TestTask", "Testing");
        t.setDescription("Testing Tasks");
        assertEquals("Testing Tasks", t.getDescription());
    }
    
    @Test
    public void testGetSetProjectID(){
        Task t = new Task(1, 4, "TestTask", "Testing");
        t.setProjectId(2);
        assertEquals(2, t.getProjectId());
    }
    
    @Test
    public void testGetSetUserID(){
        Task t = new Task(1, 4, "TestTask", "Testing");
        t.setUserId(5);
        assertEquals(5, t.getUserId());
    }
}
