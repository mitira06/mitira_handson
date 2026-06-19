package com.example.app.mockito.basic;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class MyServiceTest {

    // Exercise 1: Mocking and Stubbing
    @Test
    void testExternalApi() {
        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getData("default")).thenReturn("Mock Data");

        MyService service = new MyService(mockApi);
        String result = service.fetchData();

        assertEquals("Mock Data", result);
    }

    // Exercise 2: Verifying Interactions
    @Test
    void testVerifyInteraction() {
        ExternalApi mockApi = mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.fetchData();

        verify(mockApi).getData("default");
    }

    // Exercise 3: Argument Matching
    @Test
    void testArgumentMatching() {
        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getData(anyString())).thenReturn("Matched Data");

        MyService service = new MyService(mockApi);
        String result = service.fetchData("anyValueHere");

        assertEquals("Matched Data", result);
        verify(mockApi).getData(eq("anyValueHere"));
        verify(mockApi).getData(any(String.class));
    }

    // Exercise 4: Handling Void Methods
    @Test
    void testVoidMethod() {
        Notifier mockNotifier = mock(Notifier.class);
        AlertService alertService = new AlertService(mockNotifier);

        // stub void method to do nothing explicitly (default behavior, shown for clarity)
        doNothing().when(mockNotifier).notify(anyString());

        alertService.sendAlert("System down");

        verify(mockNotifier).notify("System down");
    }

    // Exercise 5: Mocking and Stubbing with Multiple Returns
    @Test
    void testMultipleReturnValues() {
        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getData("default"))
                .thenReturn("First Data")
                .thenReturn("Second Data");

        MyService service = new MyService(mockApi);

        assertEquals("First Data", service.fetchData());
        assertEquals("Second Data", service.fetchData());
        // Mockito repeats the last stub for further calls
        assertEquals("Second Data", service.fetchData());
    }

    // Exercise 6: Verifying Interaction Order
    @Test
    void testVerifyInteractionOrder() {
        ExternalApi mockApi = mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.fetchData(); // calls getData(...) then log(...)

        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).getData("default");
        inOrder.verify(mockApi).log(anyString());
    }

    // Exercise 7: Handling Void Methods with Exceptions
    @Test
    void testVoidMethodThrowsException() {
        Notifier mockNotifier = mock(Notifier.class);
        doThrow(new RuntimeException("Notification failed"))
                .when(mockNotifier).notify(anyString());

        AlertService alertService = new AlertService(mockNotifier);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> alertService.sendAlert("Critical failure")
        );

        assertEquals("Notification failed", exception.getMessage());
        verify(mockNotifier).notify("Critical failure");
    }
}
