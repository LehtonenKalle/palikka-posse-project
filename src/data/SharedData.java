package data;

import java.util.concurrent.*;

public class SharedData {
	private volatile double colorTresHold;

    public synchronized void setColorTresHold(double data) {
        this.colorTresHold = data;
    }

    public synchronized double getColorTresHold() {
        return this.colorTresHold;
    }
}
