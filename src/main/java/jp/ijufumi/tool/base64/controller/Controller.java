package jp.ijufumi.tool.base64.controller;

import javafx.scene.control.Button;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import jp.ijufumi.tool.base64.util.Encoder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Map;

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
//    @FXML
//    Button inputPasteBtn;
//    @FXML
//    Button inputCopyBtn;
//    @FXML
//    Button outputPasteBtn;
//    @FXML
//    Button outputCopyBtn;

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

    @FXML
    public void doCopy(ActionEvent e) {
        Clipboard board = Clipboard.getSystemClipboard();
        Button clickedBtn = (Button) e.getSource();
        switch (clickedBtn.getId()) {
            case "inputPasteBtn":
                if (board.hasString()) {
                    inputText.setText(board.getString());
                } else {
                    //TODO:アラート画面表示
                }
                break;
            case "inputCopyBtn":
                String inputValue = inputText.getText();
                if (StringUtils.isNotEmpty(inputValue)) {
                    Map<DataFormat, Object> content = Collections.singletonMap(DataFormat.PLAIN_TEXT, inputValue);
                    board.setContent(content);
                }
                break;
            case "outputPasteBtn":
                if (board.hasString()) {
                    outputText.setText(board.getString());
                } else {
                    //TODO:アラート画面表示
                }
                break;
            case "outputCopyBtn":
                String outputValue = outputText.getText();
                if (StringUtils.isNotEmpty(outputValue)) {
                    Map<DataFormat, Object> content = Collections.singletonMap(DataFormat.PLAIN_TEXT, outputValue);
                    board.setContent(content);
                }
                break;
        }
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
