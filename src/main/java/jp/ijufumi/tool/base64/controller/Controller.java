package jp.ijufumi.tool.base64.controller;

import jp.ijufumi.tool.base64.util.Encoder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;

public class Controller {
    @FXML
    RadioButton encode;
    @FXML
    RadioButton decode;
    @FXML
    CheckBox insertLF;
    @FXML
    TextArea inputText;
    @FXML
    TextArea outputText;

    @FXML
    @SuppressWarnings("unused")
    public void execute(ActionEvent e) {
        outputText.setText(encode(inputText.getText(), Type.Order));
    }

    @FXML
    @SuppressWarnings("unused")
    public void executeRev(ActionEvent e) {
        inputText.setText(encode(outputText.getText(), Type.Rev));
    }

    private String encode(String value, Type type) {
        String result;
        if (type.checkEncode(encode.isSelected())) {
            if (insertLF.isSelected()) {
                result = Encoder.doEncodeChunked(value);
            } else {
                result = Encoder.doEncode(value);
            }
        }
        else {
            result = Encoder.doDecode(value);
        }

        return result;
    }

    enum Type {
        Order,
        Rev {
            public boolean checkEncode(boolean isEncode) {
                return !isEncode;
            }
        }
        ;

        public boolean checkEncode(boolean isEncode) {
            return isEncode;
        }
    }
}
