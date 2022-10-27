package tests.values;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DoublePointTest.class,
        LinesCrossPointTests.class,
        LineTests.class,
        LineTranslationTests.class,
        NextPointOnLineTests.class,
        PointLocationTest.class,
        IntersectTest.class,
        ReflectTest.class,
        PolygonTest.class,
})
public class TestPackage{
}

