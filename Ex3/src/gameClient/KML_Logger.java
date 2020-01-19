package gameClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represent a KML_Logger object that creates a KML file for each game.
 * KML_Logger attributes :
 * 1.stage
 * 2.StringBuilder - to concat all the game info.
 */
public class KML_Logger
{

	private int stage;
	private StringBuilder info;

	/**
	 * Constructor, initialize the object and concat the standard start of a KML file.
	 * @param stage
	 */
	public KML_Logger(int stage) 
	{
		this.stage = stage;
		info = new StringBuilder();
		kmlStart();
	}

	/**
	 * Concat the opening string for the KML file.
	 * Sets the elements of the game such as: node, fruit and robot that will be added as a placemark to the KML file.
	 */
	public void kmlStart()
	{
		info.append(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
						"<kml xmlns=\"http://earth.google.com/kml/2.2\">\r\n" +
						"  <Document>\r\n" +
						"    <name>" + "Game stage :"+this.stage + "</name>" +"\r\n"+
						" <Style id=\"node\">\r\n" +
						"      <IconStyle>\r\n" +
						"        <Icon>\r\n" +
						"          <href>http://maps.google.com/mapfiles/kml/pal3/A.png</href>\r\n" +
						"        </Icon>\r\n" +
						"        <hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\r\n" +
						"      </IconStyle>\r\n" +
						"    </Style>" +
						" <Style id=\"fruit-banana\">\r\n" +
						"      <IconStyle>\r\n" +
						"        <Icon>\r\n" +
						"          <href>http://maps.google.com/mapfiles/kml/paddle/ylw-stars.png</href>\r\n" +
						"        </Icon>\r\n" +
						"        <hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\r\n" +
						"      </IconStyle>\r\n" +
						"    </Style>" +
						" <Style id=\"fruit-apple\">\r\n" +
						"      <IconStyle>\r\n" +
						"        <Icon>\r\n" +
						"          <href>http://maps.google.com/mapfiles/kml/paddle/red-stars.png</href>\r\n" +
						"        </Icon>\r\n" +
						"        <hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\r\n" +
						"      </IconStyle>\r\n" +
						"    </Style>" +
						" <Style id=\"robot\">\r\n" +
						"      <IconStyle>\r\n" +
						"        <Icon>\r\n" +
						"          <href>http://maps.google.com/mapfiles/kml/shapes/man.png</href>\r\n" +
						"        </Icon>\r\n" +
						"        <hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\r\n" +
						"      </IconStyle>\r\n" +
						"    </Style>"
				);
	}

	/**
	 * Add placemark to the KML file.
	 * Each game element has a placemark id.
	 * @param id
	 * @param location
	 */
	public void addPlaceMark(String id, String location)
	{
		//Create formatter
		DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		//Local date time instance
		LocalDateTime localDateTime = LocalDateTime.now();
		//Get formatted String
		String ldtString = FORMATTER.format(localDateTime);
		
		System.out.println(ldtString);

		info.append(
				"    <Placemark>\r\n" +
						"      <TimeStamp>\r\n" +
						"        <when>" + ldtString+ "</when>\r\n" +
						"      </TimeStamp>\r\n" +
						"      <styleUrl>#" + id + "</styleUrl>\r\n" +
						"      <Point>\r\n" +
						"        <coordinates>" + location + "</coordinates>\r\n" +
						"      </Point>\r\n" +
						"    </Placemark>\r\n"
				);

	}

	/**
	 * Concat the closing string for the KML file.
	 * Creates a kml file name=stage.kml,
	 * and save it to the data folder in this project.
	 */
	public void kmlEnd()
	{
		info.append("  \r\n</Document>\r\n" +
				"</kml>"
				);
		try
		{
			File f=new File("data/"+this.stage+".kml");
			PrintWriter pw=new PrintWriter(f);
			pw.write(info.toString());
			pw.close();

		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
//	public static void main()
//	{
//		KML_Logger km=new KML_Logger(4);
//		km.kmlStart();
//	}
}