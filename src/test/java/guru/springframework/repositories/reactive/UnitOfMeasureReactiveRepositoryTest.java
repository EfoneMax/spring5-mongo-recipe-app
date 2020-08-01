package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest {
    public static final String UOM_DESCRIPTION = "uom description";

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave() {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription(UOM_DESCRIPTION);

        unitOfMeasureReactiveRepository.save(uom).block();

        Long uomCount = unitOfMeasureReactiveRepository.count().block();

        assertEquals(Long.valueOf(1l), uomCount);
    }

    @Test
    public void testFindByDescription() {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription(UOM_DESCRIPTION);

        unitOfMeasureReactiveRepository.save(uom).block();

        UnitOfMeasure foundUom = unitOfMeasureReactiveRepository.findByDescription(UOM_DESCRIPTION).block();

        assertNotNull(foundUom);
        assertEquals(UOM_DESCRIPTION, foundUom.getDescription());
    }
}