package ar.edu.udecy.web.inventory.service.impl;

    import ar.edu.udecy.web.inventory.dto.CurrentStockDTO;
    import ar.edu.udecy.web.inventory.entity.CurrentStockEntity;
    import ar.edu.udecy.web.inventory.entity.ProductEntity;
    import ar.edu.udecy.web.inventory.handler.exception.ResourceNotFoundException;
    import ar.edu.udecy.web.inventory.repository.CurrentStockRepository;
    import ar.edu.udecy.web.inventory.repository.ProductRepository;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.junit.jupiter.MockitoExtension;

    import java.time.LocalDateTime;
    import java.util.List;
    import java.util.Optional;

    import static org.junit.jupiter.api.Assertions.*;
    import static org.mockito.Mockito.*;

    @ExtendWith(MockitoExtension.class)
    class CurrentStockServiceImplTests {

        @Mock
        private CurrentStockRepository currentStockRepository;

        @Mock
        private ProductRepository productRepository;

        @InjectMocks
        private CurrentStockServiceImpl currentStockService;

        @Test
        void shouldReturnAllCurrentStockDTOs() {
            List<CurrentStockEntity> entities = List.of(
                    new CurrentStockEntity(1L, "101", 50, LocalDateTime.now(), 500.0),
                    new CurrentStockEntity(2L, "102", 30, LocalDateTime.now(), 300.0)
            );
            when(currentStockRepository.findAll()).thenReturn(entities);

            List<CurrentStockDTO> result = currentStockService.findAll();

            assertEquals(2, result.size());
            assertEquals("101", result.get(0).getProductId());
            assertEquals("102", result.get(1).getProductId());
        }

        @Test
        void shouldReturnCurrentStockDTOById() {
            CurrentStockEntity entity = new CurrentStockEntity(1L, "101", 50, LocalDateTime.now(), 500.0);
            when(currentStockRepository.findById(101L)).thenReturn(Optional.of(entity));

            CurrentStockDTO result = currentStockService.findById(101L);

            assertNotNull(result);
            assertEquals("101", result.getProductId());
            assertEquals(50, result.getQuantity());
        }

        @Test
        void shouldThrowExceptionWhenCurrentStockNotFoundById() {
            when(currentStockRepository.findById(101L)).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> currentStockService.findById(101L));
        }

        @Test
        void shouldSaveNewCurrentStockDTO() {
            ProductEntity product = ProductEntity.builder().productName("Product A").productId("101").build();
            CurrentStockEntity entity = new CurrentStockEntity(null, "101", 50, LocalDateTime.now(), 500.0);
            CurrentStockEntity savedEntity = new CurrentStockEntity(1L, "101", 50, LocalDateTime.now(), 500.0);

            when(productRepository.findById("101")).thenReturn(Optional.of(product));
            when(currentStockRepository.save(any(CurrentStockEntity.class))).thenReturn(savedEntity);

            CurrentStockDTO dto = new CurrentStockDTO(null, "101", 50, null, 500.0);
            CurrentStockDTO result = currentStockService.save(dto);

            assertNotNull(result);
            assertEquals(1L, result.getId());
            assertEquals("101", result.getProductId());
        }

        @Test
        void shouldThrowExceptionWhenSavingExistingCurrentStock() {
            when(currentStockRepository.existsById(1L)).thenReturn(true);

            CurrentStockDTO dto = new CurrentStockDTO(1L, "101", 50, null, 500.0);

            assertThrows(ResourceNotFoundException.class, () -> currentStockService.save(dto));
        }


        @Test
        void shouldThrowExceptionWhenUpdatingNonExistentCurrentStock() {
            when(currentStockRepository.findById(101L)).thenReturn(Optional.empty());

            CurrentStockDTO dto = new CurrentStockDTO(null, "101", 60, null, 600.0);

            assertThrows(ResourceNotFoundException.class, () -> currentStockService.update(101L, dto));
        }

        @Test
        void shouldDeleteCurrentStockById() {
            CurrentStockEntity entity = new CurrentStockEntity(1L, "101L", 50, LocalDateTime.now(), 500.0);
            when(currentStockRepository.findById(101L)).thenReturn(Optional.of(entity));

            assertDoesNotThrow(() -> currentStockService.deleteById(101L));
            verify(currentStockRepository, times(1)).delete(entity);
        }

        @Test
        void shouldThrowExceptionWhenDeletingNonExistentCurrentStock() {
            when(currentStockRepository.findById(101L)).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> currentStockService.deleteById(101L));
        }
    }