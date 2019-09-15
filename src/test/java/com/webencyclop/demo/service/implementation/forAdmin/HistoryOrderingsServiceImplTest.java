package com.webencyclop.demo.service.implementation.forAdmin;

import com.webencyclop.demo.model.Ordering;
import com.webencyclop.demo.service.interfaces.forAdmin.HistoryOrderingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Rollback
public class HistoryOrderingsServiceImplTest {

    @Autowired
    private HistoryOrderingService historyOrderingService;

    @Test
    public void listHistoryOrderings() {
        List<Ordering> orderingList = historyOrderingService.listHistoryOrderings();
        long sizeList = orderingList.size();
        assertEquals(historyOrderingService.listHistoryOrderings().size(),sizeList);
    }

}