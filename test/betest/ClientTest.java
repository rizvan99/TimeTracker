
package betest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import timetrackingexam.be.Client;

public class ClientTest {
    
    public ClientTest() {
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
    public void testGetId(){
        Client c = new Client(1, "hans", 150);
        assertEquals(1, c.getId());
    }
    
    @Test
    public void testSetId(){
        Client c = new Client(1, "Hans", 150);
        c.setId(15);
        assertEquals(15, c.getId());
    }
    
    @Test
    public void testGetName(){
        Client c = new Client(1, "Hans", 150);
        
        assertEquals("Hans", c.getName());
    }
    
    @Test
    public void testSetName(){
        Client c = new Client(1, "Hans", 150);
        
        c.setName("Britta");
        assertEquals("Britta", c.getName());
    }
    
    public void testGetRate(){
        Client c = new Client(1, "Hans", 150);
        
        assertEquals(150, c.getDefaultrate());
    }
    
    @Test
    public void testSetRate(){
        Client c = new Client(1, "Hans", 150);
        
        c.setDefaultrate(250);
        assertEquals(250, c.getDefaultrate());
    }
    
}
