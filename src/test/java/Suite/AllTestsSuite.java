package Suite;

import com.weare.api.tests.*;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;


@Suite
@SelectClasses({
        RegistrationTest.class,
        InvalidRegistrationTest.class,
        LoginTest.class,
        CreatePostTest.class,
        PostApiTests.class
})
public class AllTestsSuite {

}


