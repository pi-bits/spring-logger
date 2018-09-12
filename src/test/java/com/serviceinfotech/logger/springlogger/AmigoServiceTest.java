package com.serviceinfotech.logger.springlogger;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AmigoServiceTest {
    @Autowired
    AmigoService amigoService;


    @Test
    public void shouldInvokeCallAmigo() throws Exception {

        assertThat(amigoService.callAmigo("Prashant"), Is.is("Hello Prashant!!!"));
    }
}