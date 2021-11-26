/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betest;

import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import timetrackingexam.be.TaskTime;

/**
 *
 * @author math2
 */
public class TaskTimeTest {
    
    public TaskTimeTest() {
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
    public void testGetSetID() {
        TaskTime tt = new TaskTime(1, 4, 12, 59, 5, LocalDate.now());
        tt.setID(2);
        assertEquals(2, tt.getID());
    }
    @Test
    public void testGetSetUserID() {
        TaskTime tt = new TaskTime(1, 4, 12, 59, 5, LocalDate.now());
        tt.setUserId(7);
        assertEquals(7, tt.getUserId());
    }
    @Test
    public void testGetSetTaskID() {
        TaskTime tt = new TaskTime(1, 4, 12, 59, 5, LocalDate.now());
        tt.setTaskid(5);
        assertEquals(5, tt.getTaskid());
    }
    @Test
    public void testGetSetDate() {
        TaskTime tt = new TaskTime(1, 4, 12, 59, 5, LocalDate.now());
        tt.setDate(LocalDate.parse("2020-05-22"));
        assertEquals(LocalDate.parse("2020-05-22"), tt.getDate());
    }
    @Test
    public void testGetSetSeconds() {
        TaskTime tt = new TaskTime(1, 4, 12, 59, 5, LocalDate.now());
        tt.setSec(2);
        assertEquals(2, tt.getSec());
    }
    @Test
    public void testGetSetMinuttes() {
        TaskTime tt = new TaskTime(1, 4, 12, 59, 5, LocalDate.now());
        tt.setMin(10);
        assertEquals(10, tt.getMin());
    }
    @Test
    public void testGetSetHours() {
        TaskTime tt = new TaskTime(1, 4, 12, 59, 5, LocalDate.now());
        tt.setHours(12);
        assertEquals(12, tt.getHours());
    }
}
