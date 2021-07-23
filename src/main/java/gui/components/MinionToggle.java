package gui.components;

import javafx.scene.control.ToggleButton;
import model.MinionBase;

public class MinionToggle extends ToggleButton {
    private MinionBase minionBase;

    public MinionToggle(String text) {
        super(text);
    }

    public void setMinionBase(MinionBase minionBase) {
        this.minionBase = minionBase;
        setId(minionBase.toString() + "Toggle");
    }

    public MinionBase getMinionBase() {
        return minionBase;
    }
}
