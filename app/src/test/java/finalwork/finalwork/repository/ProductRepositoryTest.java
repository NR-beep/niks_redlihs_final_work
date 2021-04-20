package finalwork.finalwork.repository;

import org.junit.Before;
import org.junit.Test;
import finalwork.finalwork.model.Product;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductRepositoryTest  {
    @Mock
    private Map<Long, Product> repoMock;

    @InjectMocks
    private ProductRepository victim;

    @Test
    public void shouldFindAllProducts() {
        Product orange = new Product();
        Product fish = new Product();

        when(repoMock.values()).thenReturn(Arrays.asList(orange, fish));

        List<Product> result = victim.findAll();
        assertNotNull(result);
        assertTrue(result.contains(orange));
        assertTrue(result.contains(fish));


    }


    @Test
    public void shouldFindProductById() {
        Product orange = new Product();

        when(repoMock.get(any())).thenReturn(orange);

        Product result = victim.findById(1L);
        assertEquals(orange, result);

        verify(repoMock).get(1L);
        verifyNoMoreInteractions(repoMock);
    }

    @Test
    public void shouldSaveProduct() {
        Product orange = new Product();

        final Long productId = victim.save(orange);
        assertEquals(productId, orange.getId());
        verify(repoMock).put(productId, orange);
        verifyNoMoreInteractions(repoMock);
//        assertEquals(productId, orange.getId());
//        assertEquals(orange, repoMock.get(productId));
    }

    @Test
    public void shouldRemoveProductById() {
        victim.delete(1L);

        verify(repoMock).remove(1L);
        verifyNoMoreInteractions(repoMock);
    }
}