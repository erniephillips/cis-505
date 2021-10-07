import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

class EditingCell extends TableCell<Student, String> {

  private TextField textField;

  public EditingCell() {
  }

  @Override
  public void startEdit() {
    if (!isEmpty()) {
      super.startEdit();
      if (textField == null) {
        createTextField();
      }

      setGraphic(textField);
      setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
      // textField.selectAll();
      Platform.runLater(new Runnable() {
        @Override
        public void run() {
          textField.requestFocus();
          textField.selectAll();
        }
      });
    }
  }

  @Override
  public void cancelEdit() {
    super.cancelEdit();

    setText((String) getItem());
    setGraphic(null);
  }

  @Override
  public void updateItem(String item, boolean empty) {
    super.updateItem(item, empty);

    if (empty) {
      setText(null);
      setGraphic(null);
    } else {
      if (isEditing()) {
        if (textField != null) {
          textField.setText(getString());
        }
        setGraphic(textField);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
      } else {
        setText(getString());
        setContentDisplay(ContentDisplay.TEXT_ONLY);
      }
    }
  }

  private void createTextField() {
    textField = new TextField(getString());
    textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);

    // textField.focusedProperty().addListener((ChangeListener<? super Boolean>) new
    // ChangeListener<Boolean>() {
    // @Override
    // public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1,
    // Boolean arg2) {
    // if (!arg2) {
    // commitEdit(textField.getText());
    // }
    // }

    // @Override
    // public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1,
    // Boolean arg2) {
    // // TODO Auto-generated method stub

    // }
    // });

    textField.setOnKeyPressed((EventHandler<? super KeyEvent>) new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent t) {
        if (t.getCode() == KeyCode.ENTER) {
          commitEdit(textField.getText());
        } else if (t.getCode() == KeyCode.ESCAPE) {
          cancelEdit();
        } else if (t.getCode() == KeyCode.TAB) {
          commitEdit(textField.getText());
          TableColumn nextColumn = getNextColumn(!t.isShiftDown());
          if (nextColumn != null) {
            getTableView().edit(getTableRow().getIndex(), nextColumn);
          }

        }
      }

    });
  }

  private String getString() {
    return getItem() == null ? "" : getItem().toString();
  }

  private TableColumn<Student, ?> getNextColumn(boolean forward) {
    List<TableColumn<Student, ?>> columns = new ArrayList<>();
    for (TableColumn<Student, ?> column : getTableView().getColumns()) {
      columns.addAll(getLeaves(column));
    }
    // There is no other column that supports editing.
    if (columns.size() < 2) {
      return null;
    }
    int currentIndex = columns.indexOf(getTableColumn());
    int nextIndex = currentIndex;
    if (forward) {
      nextIndex++;
      if (nextIndex > columns.size() - 1) {
        nextIndex = 0;
      }
    } else {
      nextIndex--;
      if (nextIndex < 0) {
        nextIndex = columns.size() - 1;
      }
    }
    return columns.get(nextIndex);
  }

  private List<TableColumn<Student, ?>> getLeaves(TableColumn<Student, ?> root) {
    List<TableColumn<Student, ?>> columns = new ArrayList<>();
    if (root.getColumns().isEmpty()) {
      // We only want the leaves that are editable.
      if (root.isEditable()) {
        columns.add(root);
      }
      return columns;
    } else {
      for (TableColumn<Student, ?> column : root.getColumns()) {
        columns.addAll(getLeaves(column));
      }
      return columns;
    }
  }
}