package com.decorator.service;

import com.decorator.model.CoffeeOrderRequest;
import com.decorator.model.CoffeeOrderResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeBuilderServiceTest {

    private final CoffeeBuilderService service = new CoffeeBuilderService();

    @Test
    void recordsEachDecoratorInOrderWithCorrectTotal() {
        CoffeeOrderResponse result = service.buildOrder(request("espresso",
                List.of("Milk", "vanilla", "sugar")));

        assertEquals("Espresso, Milk, Vanilla, Sugar", result.getDescription());
        assertEquals(2.20, result.getTotalCost(), 0.001);
        assertEquals(4, result.getDecoratorChain().size());
        assertEquals("Espresso, Milk", result.getDecoratorChain().get(1).getDescriptionSoFar());
        assertEquals(1.75, result.getDecoratorChain().get(1).getCostSoFar(), 0.001);
        assertEquals(List.of("milk", "vanilla", "sugar"), result.getAddons());
    }

    @Test
    void allowsOmittedAddonsButNotInvalidBase() {
        CoffeeOrderResponse result = service.buildOrder(request("simple", null));
        assertEquals(1.00, result.getTotalCost(), 0.001);
        assertTrue(result.getAddons().isEmpty());
        assertEquals(1, result.getDecoratorChain().size());

        assertThrows(IllegalArgumentException.class, () -> service.buildOrder(request("latte", List.of())));
        assertThrows(IllegalArgumentException.class, () -> service.buildOrder(request(null, List.of())));
    }

    @Test
    void rejectsUnknownBlankOrNullAddonsInsteadOfSilentlyIgnoringThem() {
        assertThrows(IllegalArgumentException.class,
                () -> service.buildOrder(request("espresso", List.of("caramel"))));
        assertThrows(IllegalArgumentException.class,
                () -> service.buildOrder(request("espresso", Arrays.asList("milk", null))));
        assertThrows(IllegalArgumentException.class,
                () -> service.buildOrder(request("espresso", List.of(" "))));
    }

    private CoffeeOrderRequest request(String base, List<String> addons) {
        CoffeeOrderRequest request = new CoffeeOrderRequest();
        request.setBaseType(base);
        request.setAddons(addons);
        return request;
    }
}
