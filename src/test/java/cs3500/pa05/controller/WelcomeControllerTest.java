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
class WelcomeControllerTest {
  private Button button;
  private WelcomeController controller = new WelcomeController();

  /**
   * Will be called with {@code @Before} semantics, i. e. before each test method.
   *
   * @param stage - Will be injected by the test runner.
   */
  @Start
  void start(Stage stage) {
    button = new Button("open existing file");
    button.setId("openExistingFile");
    Week week = new Week(1, 1, "filename", "password");
    button.setOnAction(actionEvent -> controller.switchScene(button,
        new OpenExistingFileController(), "openExistingFile.fxml"));
    stage.setScene(new Scene(new StackPane(button), 100, 100));
    stage.show();
  }

  @Test
  void testChangeWeekName(FxRobot robot) {
    controller.run();
    Assertions.assertThat(button).hasText("create new file");
    Assertions.assertThat(robot.lookup("#openExistingFile").queryAs(Button.class))
        .hasText("open existing file");
    Assertions.assertThat(robot.lookup("create new file").queryAs(Button.class))
        .hasText("open existing file");
    Assertions.assertThat(robot.lookup(".button").queryButton()).hasText("click me!");
    Assertions.assertThat(button).hasText("create new file");
    controller.switchScene(button, new OpenExistingFileController(), "openExistingFile.fxml");
  }
}