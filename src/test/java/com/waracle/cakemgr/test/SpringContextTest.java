package com.waracle.cakemgr.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.*;

import com.waracle.cakemgr.CakeManagerApplication;

/**
 * @author gfeng
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CakeManagerApplication.class)
public class SpringContextTest {

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }
}