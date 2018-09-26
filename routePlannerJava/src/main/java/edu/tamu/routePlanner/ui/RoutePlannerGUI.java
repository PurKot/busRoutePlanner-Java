package edu.tamu.routePlanner.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import org.neo4j.driver.v1.Record;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import edu.tamu.routePlanner.data.Neo4jRoutePlanRepository;
import edu.tamu.routePlanner.domain.BusStopRepository;
import edu.tamu.routePlanner.domain.RoutePlan;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import edu.tamu.routePlanner.data.Neo4jBusStopRepository;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;

public class RoutePlannerGUI {

	private JFrame frmBusRoutePlanner;
	private String fromBusStop;
	private String toBusStop;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JLabel lblFrom;
	private JLabel lblTo;
	private JLabel lblResults;
	private JTextField textField;
	int flag = 0;
	private JSeparator separator_1;
	private JTextField textField_1;
	private JScrollPane scrollPane;
	private final PopulateBusStops populateBusStops;
	private JButton btnShowOncampusMaps;
	private JButton btnShowOffcampusMaps;

	/**
	 * Launch the application.
	 */
	public void DisplayUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoutePlannerGUI window = new RoutePlannerGUI();
					window.frmBusRoutePlanner.setVisible(true);
					window.frmBusRoutePlanner.setTitle("Route Planner Application");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RoutePlannerGUI() {
		populateBusStops = new PopulateBusStops();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		BusStopRepository obj = new Neo4jBusStopRepository();
		String[] BusStopsList = obj.getBusStopNames();
		Arrays.sort(BusStopsList);
		frmBusRoutePlanner = new JFrame();
		frmBusRoutePlanner.setBackground(Color.BLACK);
		frmBusRoutePlanner.setForeground(Color.BLACK);
		frmBusRoutePlanner.setTitle("Bus Route Planner");
		frmBusRoutePlanner.getContentPane().setBackground(new Color(112, 128, 144));
		frmBusRoutePlanner.setBounds(200, 100, 987, 851);
		frmBusRoutePlanner.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmBusRoutePlanner.getContentPane().setLayout(null);
		frmBusRoutePlanner.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		frmBusRoutePlanner.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				JFrame frame = (JFrame) e.getSource();

				int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit the application?",
						"Exit Application", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION)
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});

		lblFrom = new JLabel("From");
		lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFrom.setBounds(46, 76, 100, 30);
		frmBusRoutePlanner.getContentPane().add(lblFrom);

		lblTo = new JLabel("To");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTo.setBounds(46, 290, 100, 30);
		frmBusRoutePlanner.getContentPane().add(lblTo);

		lblResults = new JLabel("Results");
		lblResults.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblResults.setBounds(63, 538, 100, 30);
		frmBusRoutePlanner.getContentPane().add(lblResults);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(239, 461, 661, 197);
		frmBusRoutePlanner.getContentPane().add(scrollPane);
		JTextArea txtResults = new JTextArea();
		scrollPane.setViewportView(txtResults);
		txtResults.setWrapStyleWord(true);
		txtResults.setFont(new Font("Monospaced", Font.PLAIN, 18));

		// Initializing the combo boxes with the BusStops
		comboBox = new JComboBox(BusStopsList);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				fromBusStop = (String) comboBox.getSelectedItem();

			}
		});
		comboBox.setBounds(209, 77, 200, 30);
		frmBusRoutePlanner.getContentPane().add(comboBox);
		comboBox_1 = new JComboBox(BusStopsList);
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toBusStop = (String) comboBox_1.getSelectedItem();
			}
		});
		comboBox_1.setBounds(209, 291, 200, 30);
		frmBusRoutePlanner.getContentPane().add(comboBox_1);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(209, 31, 332, 30);
		frmBusRoutePlanner.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnSearch = new JButton("SEARCH");
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnSearch.setForeground(new Color(34, 139, 34));
		btnSearch.setBackground(UIManager.getColor("Button.background"));
		btnSearch.addActionListener(new ActionListener() {

			/**
			 * Event handler for the 'From' User Entered Address field.
			 */
			public void actionPerformed(ActionEvent arg0) {

				String temp = textField.getText().toLowerCase();
				try {
					comboBox.removeAllItems();

					if ((!temp.contains("college station")) && (!temp.contains("bryan"))) {
						JOptionPane.showMessageDialog(null,
								"Enter the full address, please append your search with College Station, Texas or Bryan, Texas");
					} else

					{
						String q2[] = populateBusStops.getNearestBusStops(textField.getText());

						System.out.println(q2);

						for (int i = 0; i < q2.length; i++) {
							q2[i] = q2[i].replace("\"", "");
							comboBox.addItem(q2[i]);
						}

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		btnSearch.setBounds(583, 33, 110, 30);
		frmBusRoutePlanner.getContentPane().add(btnSearch);

		JLabel lblEnterAddress = new JLabel("Enter Address");
		lblEnterAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnterAddress.setBounds(46, 30, 128, 30);
		frmBusRoutePlanner.getContentPane().add(lblEnterAddress);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(0, 163, 913, 30);
		frmBusRoutePlanner.getContentPane().add(separator);

		separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(0, 384, 900, 23);
		frmBusRoutePlanner.getContentPane().add(separator_1);

		JLabel lblEnterAddress_1 = new JLabel("Enter Address");
		lblEnterAddress_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnterAddress_1.setBounds(42, 225, 152, 27);
		frmBusRoutePlanner.getContentPane().add(lblEnterAddress_1);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_1.setBounds(209, 224, 332, 30);
		frmBusRoutePlanner.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		/**
		 * 'To' User entered address search button
		 */
		JButton btnSearch_1 = new JButton("SEARCH");
		btnSearch_1.setBackground(UIManager.getColor("Button.background"));
		btnSearch_1.addActionListener(new ActionListener() {
			/**
			 * Event handler for the 'To' User Entered Address field.
			 */
			public void actionPerformed(ActionEvent arg0) {
				String temp = textField_1.getText().toLowerCase();
				try {
					comboBox_1.removeAllItems();
					if ((!temp.contains("college station")) && (!temp.contains("bryan"))) {
						JOptionPane.showMessageDialog(null,
								"Enter the full address, please append your search with College Station, Texas or Bryan, Texas");
					} else {
						String q2[] = populateBusStops.getNearestBusStops(textField_1.getText());
						for (int i = 0; i < q2.length; i++) {
							q2[i] = q2[i].replace("\"", "");
							comboBox_1.addItem(q2[i]);
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		btnSearch_1.setForeground(new Color(0, 128, 0));
		btnSearch_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnSearch_1.setBounds(583, 222, 110, 30);
		frmBusRoutePlanner.getContentPane().add(btnSearch_1);
		// Get Route Plan Button initialization and event handlers

		JButton btnSubmit = new JButton("GET ROUTE PLAN");
		btnSubmit.setForeground(new Color(34, 139, 34));
		btnSubmit.addActionListener(new ActionListener() {

			/**
			 * Event handler for the Get Route Plans button. Will get the routes and update
			 * the Results Text area
			 */
			public void actionPerformed(ActionEvent arg0) {
				txtResults.setText(null);
				System.out.println(fromBusStop + " " + toBusStop);
				RoutePlan plan = new Neo4jRoutePlanRepository();
				List<Record> itineraries = plan.getRoutes(fromBusStop, toBusStop);
				flag = 0;
				for (Record s : itineraries) {
					String text = String.format("%s%n", s.get("itinerary"));
					text = text.replace("\"", "");
					text = text.replace("[", "");
					text = text.replace("]", "");
					txtResults.append(stringFormat(text));
				}

				comboBox.removeAllItems();
				comboBox_1.removeAllItems();
				textField.setText("");
				textField_1.setText("");
				comboBox.setModel(new DefaultComboBoxModel<String>(BusStopsList));
				comboBox_1.setModel(new DefaultComboBoxModel<String>(BusStopsList));

			}
		});

		btnSubmit.setBackground(UIManager.getColor("Button.background"));
		btnSubmit.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnSubmit.setBounds(514, 312, 281, 56);
		frmBusRoutePlanner.getContentPane().add(btnSubmit);
		JScrollPane sp = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 128, 128), new Color(128, 128, 0)));
		sp.setSize(661, 197);
		sp.setLocation(239, 461);
		frmBusRoutePlanner.getContentPane().add(sp);

		btnShowOncampusMaps = new JButton("Show OnCampus Maps");
		btnShowOncampusMaps.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnShowOncampusMaps.setForeground(new Color(0, 100, 0));
		btnShowOncampusMaps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Event handler to show On Campus Routes
				 */
				String OnCampusURI = "http://transport.tamu.edu/busroutes/Routes.aspx?r=OnCampus";
				try {

					URI uri = new URI(OnCampusURI);
					java.awt.Desktop.getDesktop().browse(uri);

				} catch (URISyntaxException | IOException e1) {
					// System.out.println("THROW::: make sure we handle browser error");
					e1.printStackTrace();
				}
			}
		});
		btnShowOncampusMaps.setBounds(279, 706, 226, 29);
		frmBusRoutePlanner.getContentPane().add(btnShowOncampusMaps);

		btnShowOffcampusMaps = new JButton("Show OffCampus Maps");
		btnShowOffcampusMaps.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnShowOffcampusMaps.setForeground(new Color(0, 128, 0));
		btnShowOffcampusMaps.addActionListener(new ActionListener() {
			/**
			 * Event handler to show Off Campus maps
			 */
			public void actionPerformed(ActionEvent e) {
				String OffCampusURI = "http://transport.tamu.edu/busroutes/Routes.aspx?r=OffCampus";
				try {

					URI uri = new URI(OffCampusURI);
					java.awt.Desktop.getDesktop().browse(uri);

				} catch (URISyntaxException | IOException e1) {
					// System.out.println("THROW::: make sure we handle browser error");
					e1.printStackTrace();
				}
			}

		});
		btnShowOffcampusMaps.setBounds(627, 706, 226, 29);
		frmBusRoutePlanner.getContentPane().add(btnShowOffcampusMaps);

	}

	/**
	 * Method to format the Route Plan Results to display on the GUI form
	 * 
	 * @param text - results from the Neo4j database query
	 * @return custom formatted results
	 */

	protected String stringFormat(String text) {

		String tempSt = "Bus Stop";
		String orgText = text;
		int temparr[] = new int[100];

		int i = 0;
		int a = orgText.indexOf(tempSt);
		while (a >= 0) {

			temparr[i] = a;
			a = orgText.indexOf(tempSt, (a + 1));
			i++;

		}
		StringBuilder str = new StringBuilder(orgText);
		int b = i - 1;
		for (int e = b; e >= 0; e--) {
			if (e == b)

			{
				str.insert(temparr[e], "To ");
			} else if (e == 0) {
				str.insert(temparr[e], "From ");
			} else {
				str.insert(temparr[e], "Transit ");
			}
		}

		return (str.toString());
	}
}
