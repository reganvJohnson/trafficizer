package com.lorisoft.trafficizer.endpoints.rest;

import org.springframework.web.bind.annotation.RestController;

import com.lorisoft.trafficizer.command.CarForm;
import com.lorisoft.trafficizer.converters.CarToCarForm;
import com.lorisoft.trafficizer.entity.Car;
import com.lorisoft.trafficizer.services.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class CarController {
	private CarService carService;
	private CarToCarForm carToCarForm;
	// private int werdCount = 0;
	// static Connection conn = null;
	// static Statement s = null;

	@Autowired
	public void setCarToCarForm(CarToCarForm carToCarForm) {
		this.carToCarForm = carToCarForm;
	}

	@Autowired
	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	@RequestMapping("/")
	public String redirToCarList() {
		System.out.println("redirToCarList");
		return "redirect:/car/list";
	}

	@RequestMapping({ "/car/list", "/car" })
	public String listCars(Model model) {
		System.out.println("listCars");
		model.addAttribute("cars", carService.listAll());
		System.out.println("after addAttrib");
		return "car/list";
	}

	@RequestMapping("/car/show/{id}")
	public String getCar(@PathVariable String id, Model model) {
		model.addAttribute("car", carService.getById(Long.valueOf(id)));
		return "car/show";
	}

	@RequestMapping("car/edit/{id}")
	public String edit(@PathVariable String id, Model model) {
		Car car = carService.getById(Long.valueOf(id));
		CarForm carForm = carToCarForm.convert(car);

		model.addAttribute("carForm", carForm);
		return "car/carform";
	}

	@RequestMapping("/car/new")
	public String newCar(Model model) {
		model.addAttribute("carForm", new CarForm());
		return "car/carform";
	}

	@RequestMapping(value = "/car", method = RequestMethod.POST)
	public String saveOrUpdateCar(@Valid CarForm carForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "car/carform";
		}

		Car savedCar = carService.saveOrUpdateCarForm(carForm);

		return "redirect:/car/show/" + savedCar.getId();
	}

	@RequestMapping("/car/delete/{id}")
	public String delete(@PathVariable String id) {
		carService.delete(Long.valueOf(id));
		return "redirect:/car/list";
	}

	/*
	 * String bob() { // ## DEFINE VARIABLES SECTION ## // define the driver to use
	 * String driver = "org.apache.derby.jdbc.EmbeddedDriver"; // the database name
	 * String dbName = "jdbcDemoDB"; // define the Derby connection URL to use
	 * String connectionURL = "jdbc:derby:" + dbName + ";create=true";
	 * PreparedStatement psInsert = null; String result = ""; ResultSet myWishes;
	 * String printLine = "  __________________________________________________";
	 * String createString = "CREATE TABLE WISHES  " +
	 * "(WISH_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY " +
	 * "   CONSTRAINT WISH_PK PRIMARY KEY, " +
	 * " ENTRY_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
	 * " WISH_ITEM VARCHAR(32) NOT NULL) "; String answer;
	 * 
	 * // JDBC code sections // Beginning of Primary DB access section // ## BOOT
	 * DATABASE SECTION ## if (conn == null) { try { // Create (if needed) and
	 * connect to the database. // The driver is loaded automatically. conn =
	 * DriverManager.getConnection(connectionURL);
	 * System.out.println("Connected to database " + dbName);
	 * 
	 * // ## INITIAL SQL SECTION ## // Create a statement to issue simple commands.
	 * s = conn.createStatement(); // Call utility method to check if table exists.
	 * // Create the table if needed if (!wwdChk4Table(conn)) {
	 * System.out.println(" . . . . creating table WISHES");
	 * s.execute(createString); } } catch (Throwable e) { // Catch all exceptions
	 * and pass them to the Throwable.printStackTrace method
	 * System.out.println(" . . . exception thrown:");
	 * e.printStackTrace(System.out); return result; }
	 * System.out.println("Getting Started With Derby JDBC program ending."); } try
	 * { // Prepare the insert statement to use psInsert =
	 * conn.prepareStatement("insert into WISHES(WISH_ITEM) values (?)");
	 * 
	 * // ## ADD / DISPLAY RECORD SECTION ## // The Add-Record Loop - continues
	 * until 'exit' is entered do { // Call utility method to ask user for input
	 * answer = getWishItem(); // Check if it is time to EXIT, if not insert the
	 * data if (!answer.equals("exit")) { // Insert the text entered into the
	 * WISH_ITEM table psInsert.setString(1, answer); psInsert.executeUpdate();
	 * 
	 * // Select all records in the WISHES table myWishes =
	 * s.executeQuery("select ENTRY_DATE, WISH_ITEM from WISHES order by ENTRY_DATE"
	 * );
	 * 
	 * // Loop through the ResultSet and print the data
	 * System.out.println(printLine); result += printLine + "<br/>"; while
	 * (myWishes.next()) { String outstr = "On " + myWishes.getTimestamp(1) +
	 * " I wished for " + myWishes.getString(2); System.out.println(outstr); result
	 * += outstr + "<br/>"; } System.out.println(printLine); result += printLine +
	 * "<br/>"; // Close the resultSet myWishes.close(); } // END of IF block //
	 * Check if it is time to EXIT, if so end the loop } while
	 * (!answer.equals("exit")); // End of do-while loop
	 * 
	 * // Release the resources (clean up ) psInsert.close(); // s.close(); //
	 * conn.close(); // s = null; // conn = null;
	 * System.out.println("Closed connection");
	 * 
	 * // ## DATABASE SHUTDOWN SECTION ## // // In embedded mode, an application
	 * should shut down Derby. Shutdown throws the // XJ015 exception to confirm
	 * success. //
	 * 
	 * if (driver.equals("org.apache.derby.jdbc.EmbeddedDriver")) { boolean
	 * gotSQLExc = false; try {
	 * DriverManager.getConnection("jdbc:derby:;shutdown=true"); } catch
	 * (SQLException se) { if (se.getSQLState().equals("XJ015")) { gotSQLExc = true;
	 * } } if (!gotSQLExc) {
	 * System.out.println("Database did not shut down normally"); } else {
	 * System.out.println("Database shut down normally"); } }
	 * 
	 * // Beginning of the primary catch block: prints stack trace } catch
	 * (Throwable e) { // // Catch all exceptions and pass them to the
	 * Throwable.printStackTrace method //
	 * System.out.println(" . . . exception thrown:");
	 * e.printStackTrace(System.out); }
	 * System.out.println("Getting Started With Derby JDBC program ending."); return
	 * result; }
	 * 
	 * public String getWishItem() { String[] werdz = { "one", "two", "three",
	 * "four", "five", "six", "exit" }; String result = werdz[werdCount++]; if
	 * (werdCount >= werdz.length) { werdCount = 0; } return werdz[werdCount++]; }
	 * 
	 * public static boolean wwdChk4Table(Connection conTst) throws SQLException {
	 * boolean chk = true; boolean doCreate = false; try { Statement s =
	 * conTst.createStatement(); s.
	 * execute("update WISHES set ENTRY_DATE = CURRENT_TIMESTAMP, WISH_ITEM = 'TEST ENTRY' where 1=3"
	 * ); } catch (SQLException sqle) { String theError = (sqle).getSQLState(); //
	 * System.out.println(" Utils GOT: " + theError); // If table exists will get -
	 * WARNING 02000: No row was found if (theError.equals("42X05")) // Table does
	 * not exist { return false; } else if (theError.equals("42X14") ||
	 * theError.equals("42821")) { System.out.println(
	 * "WwdChk4Table: Incorrect table definition. Drop table WISHES and rerun this program"
	 * ); throw sqle; } else {
	 * System.out.println("WwdChk4Table: Unhandled SQLException"); throw sqle; } }
	 * // System.out.println("Just got the warning - table exists OK "); return
	 * true; } // END wwdInitTable
	 */
}