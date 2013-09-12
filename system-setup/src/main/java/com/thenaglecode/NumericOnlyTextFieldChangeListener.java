package com.thenaglecode;

import com.sun.istack.internal.NotNull;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 10/09/13
 * Time: 11:35 AM
 * //todo implement
 */


public class NumericOnlyTextFieldChangeListener implements ChangeListener<Number> {

    @NotNull
    private TextField field;

    NumericOnlyTextFieldChangeListener(TextField field){
        this.field = field;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
        if(newValue.intValue() > oldValue.intValue()){
            char ch  = field.getText().charAt(oldValue.intValue());

            //Check if the new character is the number or other's
            if(!(ch >= '0' && ch <= '9')){

                //if it's not number then just setText to previous one
                field.setText(field.getText().substring(0, field.getText().length()-1));
            }
        }
    }
}
