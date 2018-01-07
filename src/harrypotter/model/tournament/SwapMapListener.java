package harrypotter.model.tournament;

import java.awt.FontFormatException;
import java.io.IOException;

public interface SwapMapListener {
public void onFinishingFirstTask() throws IOException, FontFormatException;
public void onFinishingSecondTask() throws IOException, FontFormatException;
public void onFinishingThirdTask() throws IOException, FontFormatException;

}
