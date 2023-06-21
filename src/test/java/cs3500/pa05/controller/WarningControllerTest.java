package cs3500.pa05.controller;

import cs3500.pa05.model.Week;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;


@ExtendWith(ApplicationExtension.class)
class WarningController {

  private Button button;
  private WeekViewController controller;
  private Week week;

  /**
   * Will be called with {@code @Before} semantics, i. e. before each test method.
   *
   * @param stage - Will be injected by the test runner.
   */
  @Start
  private void start(Stage stage) {
    button = new Button("got it");
    button.setId("doneOnWarningScreen");
    week = new Week(1, 1, "filename", "password");
    controller = new WeekViewController(week);
    button.setOnAction(actionEvent -> controller.switchScene(button,
        controller, "warningScreen.fxml"));
    stage.setScene(new Scene(new StackPane(button), 100, 100));
    stage.show();
  }

  @Test
  private void testWarningScreen(FxRobot robot) {
    controller.run();
    Assertions.assertThat(button).hasText("got it");
    Assertions.assertThat(robot.lookup("#doneOnWarningScreen").queryAs(Button.class))
        .hasText("got it");
    Assertions.assertThat(robot.lookup("doneOnWarningScreen").queryAs(Button.class))
        .hasText("got it");
    Assertions.assertThat(robot.lookup(".button").queryButton()).hasText("got it");
    controller.switchScene(button, controller, "createNewFile");
  }
}