package io.github.mrspock182.rest.application;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public abstract class MockitoInitTests {
    @Before
    public void setUp() {
        FixtureFactoryLoader.loadTemplates("io.github.mrspock182.rest.application");
    }

    @Before
    public abstract void init();
}
