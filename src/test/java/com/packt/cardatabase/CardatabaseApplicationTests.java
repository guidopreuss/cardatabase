package com.packt.cardatabase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.packt.cardatabase.web.CarController;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class CardatabaseApplicationTests {
    @Autowired
    private CarController carController;
    @Test
    @DisplayName("GP First example test case")
    void contextLoads() {
        assertThat(carController).isNotNull();
    }

}
