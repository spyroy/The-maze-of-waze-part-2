package gameClient;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * this class takes the result of a game and saves it to kml 
 * file, the saving will be done automatically after games end
 * @author spyro
 *
 */
public class KML_Logger {

	private int stage;
	private StringBuilder info;
	private int index = 0;

	public KML_Logger(int stage) {
		this.stage = stage;
		info = new StringBuilder();
		kmlStart();
	}

	/**
	 * the start for every kml file
	 * has the logo of purple circle for node
	 * yellow star for banana
	 * and red star for apple
	 */
	public void kmlStart() {
		info.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
				+ "<kml xmlns=\"http://earth.google.com/kml/2.2\">\r\n" + "  <Document>\r\n" + "    <name>"
				+ "Game stage :" + this.stage + "</name>" + "\r\n" + " <Style id=\"node\">\r\n"
				+ "      <IconStyle>\r\n" + "        <Icon>\r\n"
				+ "          <href>http://maps.google.com/mapfiles/kml/paddle/purple-circle.png</href>\r\n"
				+ "        </Icon>\r\n" + "        <hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\r\n"
				+ "      </IconStyle>\r\n" + "    </Style>" + " <Style id=\"fruit-banana\">\r\n"
				+ "      <IconStyle>\r\n" + "        <Icon>\r\n"
				+ "          <href>http://maps.google.com/mapfiles/kml/paddle/ylw-stars.png</href>\r\n"
				+ "        </Icon>\r\n" + "        <hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\r\n"
				+ "      </IconStyle>\r\n" + "    </Style>" + " <Style id=\"fruit-apple\">\r\n"
				+ "      <IconStyle>\r\n" + "        <Icon>\r\n"
				+ "          <href>http://maps.google.com/mapfiles/kml/paddle/red-stars.png</href>\r\n"
				+ "        </Icon>\r\n" + "        <hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\r\n"
				+ "      </IconStyle>\r\n" + "    </Style>" + " <Style id=\"robot\">\r\n" + "      <IconStyle>\r\n"
				+ "        <Icon>\r\n" + "          <href>http://maps.google.com/mapfiles/kml/shapes/man.png</href>\r\n"
				+ "        </Icon>\r\n" + "        <hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\r\n"
				+ "      </IconStyle>\r\n" + "    </Style>");
	}

	/**
	 * place a mark on location
	 * given from the game for every element
	 * @param id
	 * @param location
	 */
	public void addPlaceMark(String id, String location) {

		DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		LocalDateTime localDateTime = LocalDateTime.now();
		String date = FORMATTER.format(localDateTime);
		if (id != null && location != null)
			info.append("    <Placemark>\r\n" + "      <TimeStamp>\r\n" + "        <when>" + date + "</when>\r\n"
					+ "      </TimeStamp>\r\n" + "      <styleUrl>#" + id + "</styleUrl>\r\n" + "      <Point>\r\n"
					+ "        <coordinates>" + location + "</coordinates>\r\n" + "      </Point>\r\n"
					+ "    </Placemark>\r\n");

	}

	/**
	 * should do a path between all nodes
	 */
	public void addLineString(ArrayList<String> locations) {
		if (locations != null)

			info.append("    <LineString>\r\n" + "<tessellate>" + index + "</tessellate>\r\n" + "<coordinates>\r\n");
		for (String s : locations) {
			info.append(s + " ");
		}
		info.append("</coordinates>\r\n" + "</LineString>");

	}

	
/**
 * the of every kml file
 */
	public void kmlEnd() {
		info.append("  \r\n</Document>\r\n" + "</kml>");
		try {
			File f = new File("data/" + this.stage + ".kml");
			PrintWriter pw = new PrintWriter(f);
			pw.write(info.toString());
			pw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * kml to string
	 * @return
	 */
	public String kmlToString() {
		return info.toString();
	}

}