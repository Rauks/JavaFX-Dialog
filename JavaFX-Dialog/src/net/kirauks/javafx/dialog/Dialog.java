/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.kirauks.javafx.dialog;

import javafx.stage.Stage;

/**
 *
 * @author Karl
 */
public class Dialog extends Stage{
    public enum DialogType{
        ERROR,
        WARNING,
        QUESTION,
        INFORMATION;
    }
    public enum DialogOptions{
        OK,
        OK_CANCEL,
        YES_NO,
        YES_NO_CANCEL;
    }
}
