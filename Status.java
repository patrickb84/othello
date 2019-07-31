/****************************************
 * OTHELLO
 * A Board Game
 * 
 * Gerald Brady & Patrick Bradshaw
 * January 27, 2018
 * CSIS 2410
 * 
 ****************************************/

package othello;

import javax.swing.ImageIcon;

/**
 * The Status Enumaration represents the 4 states that a square can have.
 * @author Patrick Bradshaw & Gerald Brady
 *
 */
public enum Status {
	OPEN(new ImageIcon(Status.class.getResource("/othello/lib/blank.png"))),
	BLACK(new ImageIcon(Status.class.getResource("/othello/lib/blackdisc.png"))),
	WHITE(new ImageIcon(Status.class.getResource("/othello/lib/whitedisc.png"))),
	LEGAL(new ImageIcon(Status.class.getResource("/othello/lib/legalmarker.png")));
	
	ImageIcon statusImage;
	
	private Status(ImageIcon statusImage) {
		this.statusImage = statusImage;
	}
	
	/**
	 * The important method for getting the image of a particular state.
	 * @return
	 */
	public ImageIcon getStatusImage() {
		return statusImage;
	}
}
