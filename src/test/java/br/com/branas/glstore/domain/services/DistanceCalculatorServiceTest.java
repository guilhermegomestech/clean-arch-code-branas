package br.com.branas.glstore.domain.services;

import br.com.branas.glstore.domain.vo.CoordinateVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DistanceCalculatorServiceTest {

    @InjectMocks
    private DistanceCalculatorService service;

    @Test
    void testCalculateCoord() {
        CoordinateVO coordFrom = new CoordinateVO(-27.5945, -48.5477);
        CoordinateVO coordTo = new CoordinateVO(-22.9129, -43.2003);

        assertEquals(service.calculate(coordFrom, coordTo), 748.2217780081631);

    }

}