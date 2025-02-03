
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AssignmentTest {

    private Assignment assignment;
    private Order mockOrder;
    private Administrator mockAdmin;
    private DeliveryDriver mockDeliveryDriver;

    @BeforeEach
    public void setUp() {
        assignment = new Assignment();
        mockOrder = mock(Order.class);
        mockAdmin = mock(Administrator.class);
        mockDeliveryDriver = mock(DeliveryDriver.class);
    }

    @Test
    public void testSetAndGetId() {
        Long expectedId = 1L;
        assignment.setId(expectedId);
        assertEquals(expectedId, assignment.getId());
    }

    @Test
    public void testSetAndGetOrder() {
        assignment.setOrder(mockOrder);
        assertEquals(mockOrder, assignment.getOrder());
    }

    @Test
    public void testSetAndGetAssignedBy() {
        assignment.setAssignedBy(mockAdmin);
        assertEquals(mockAdmin, assignment.getAssignedBy());
    }

    @Test
    public void testSetAndGetDeliveryDriver() {
        assignment.setDeliveryDriver(mockDeliveryDriver);
        assertEquals(mockDeliveryDriver, assignment.getDeliveryDriver());
    }

    @Test
    public void testToString() {
        assignment.setId(1L);
        String expectedString = "Assignment(id=1, order=null, assignedBy=null, deliveryDriver=null)";
        assertEquals(expectedString, assignment.toString());
    }
}