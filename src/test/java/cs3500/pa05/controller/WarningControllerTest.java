package cs3500.pa05.controller;

import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

class WarningControllerTest {

  @ExtendWith(ApplicationExtension.class)
  class ChangeWeekNameAction {

    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     */
    @Start
    private void start(Stage stage) {}

    @Test
    private void testChangeWeekName(FxRobot robot) {}

  }

  @Test
  void switchScene() {
  }

  @Test
  void run() {
  }
}