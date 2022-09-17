import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class ProjectLogic {

    // Create a  list for objects
    // This is used inside the program to perform tasks without accessing sql
    List<ProjectInfo> objectsOfProject = new ArrayList<>();
    List<ProjectInfo> completed = new ArrayList<>();
    List<ProjectInfo> incomplete = new ArrayList<>();
    List<ProjectInfo> PastDue = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    public void getLogic() throws ParseException, SQLException {

        String menuStringLower = "a";
        sqlIn();

        label:
        while(!menuStringLower.equals("e")){
            // Create menu for selecting
            // This calls functions below to implement the program
            System.out.print("\nL\t\tTo Log a New Project\nC\t\tTo Change info about Person / Project & Payment\nF\t\tTo finalize and print Invoice\nW\t\tTo print Incomplete Projects\nD\t\tTo see Projects that are Overdue\nU\t\tTo print Completed projects\nP\t\tTo Print All\nO\t\tFind A Project\nE\t\tTo Exit\nEnter Here: ");
            String menuString = scan.nextLine();
            menuStringLower = menuString.toLowerCase();

            switch (menuStringLower) {
                case "l":
                    logProject();
                    break;
                case "c":
                    logEdit();
                    break;
                case "f":
                    logFinal();
                    break;
                case "w":
                    logIncomplete();
                    break;
                case "d":
                    logOverdue();
                    break;
                case "u":
                    logComplete();
                    break;
                case "e":
                    break label;
                case "p":
                    logPrintAll();
                    break;
                case "o":
                    findProject();
                    break;
            }
        }
    }

    public void logProject() throws SQLException {
        // Logging Project Information
        // Connects directly to SQL and updates the relevant tables
        System.out.print("\n\nWhat is the project number: ");
        String projectNumString = scan.nextLine();

        System.out.print("\nWhat is the name of the project: ");
        String projectNameString = scan.nextLine();

        System.out.print("\nWhat type of building are you working on: ");
        String buildingTypeString = scan.nextLine();

        System.out.print("\nWhat is the address of the building being worked on: ");
        String clientAddressString = scan.nextLine();

        System.out.print("\nWhat is the ERF number: ");
        String erfNumberString = scan.nextLine();

        float netProfitString = (float) 0;
        try {

            System.out.print("\nHow much are you charging the client for this project: R");
            netProfitString = Float.parseFloat(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please only enter Numbers!");
        }

        float payToDateString = (float) 0;
        try {
            System.out.print("\nHow much have your clients paid towards the final amount of the bill: R");
            payToDateString = Float.parseFloat(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please only enter Numbers!");
        }

        System.out.print("\nWhen is the deadline for this project, Please enter date in format Year-MM-DD: ");
        String deadlineString = scan.nextLine();

        System.out.print("\nPlease Enter Clients details below: ");

        System.out.print("\n\nWhat is this clients name, Please enter name and surname: ");
        String nameString = scan.nextLine();

        System.out.print("\nWhat is this clients telephone number: ");
        String cellNumString = scan.nextLine();

        System.out.print("\nWhat is this clients email Address: ");
        String emailAddressString = scan.nextLine();

        System.out.print("\nWhat is this clients physical address: ");
        String physicalAddressString = scan.nextLine();

        System.out.print("\nPlease enter Contractors details below: ");

        System.out.print("\n\nWhat is this Contractors name, Please enter name and surname: ");
        String contractorNameString = scan.nextLine();

        System.out.print("\nWhat is this Contractors telephone number: ");
        String contractorCellNumString = scan.nextLine();

        System.out.print("\nWhat is this Contractors email Address: ");
        String contractorEmailAddressString = scan.nextLine();

        System.out.print("\nWhat is this Contractors physical address: ");
        String contractorPhysicalAddressString = scan.nextLine();

        System.out.print("\nPlease enter Architects details below: ");

        System.out.print("\n\nWhat is this Architects name, Please enter name and surname: ");
        String archNameString = scan.nextLine();

        System.out.print("\nWhat is this Architects telephone number: ");
        String archCellNumString = scan.nextLine();

        System.out.print("\nWhat is this Architects email Address: ");
        String archEmailAddressString = scan.nextLine();

        System.out.print("\nWhat is this Architects physical address: ");
        String archPhysicalAddressString = scan.nextLine();

        System.out.print("\nPlease enter Engineers details below: ");

        System.out.print("\n\nWhat is this Engineers name, Please enter name and surname: ");
        String engineerString = scan.nextLine();

        System.out.print("\nWhat is this Engineers telephone number: ");
        String cellNumEngineerString = scan.nextLine();

        System.out.print("\nWhat is this Engineers email Address: ");
        String emailAddressEngineerString = scan.nextLine();

        System.out.print("\nWhat is this Engineers physical address: ");
        String physicalAddressEngineerString = scan.nextLine();

        System.out.println("\nProject is Unfinalised and completion date is unknown,\nIn order to finalise project or update payment please see option C on main menu.");
        String finalised = "Unfinalised";
        String completionDate = "Unknown";


        // If Name of project is empty make the default House + surname of the client
        if (projectNameString.isEmpty()) {
            String[] nameSplit = nameString.split("\s");
            projectNameString = buildingTypeString + " " + nameSplit[1];
        }
        // Make an object of all the info logged
        ProjectInfo projectNew = new ProjectInfo(finalised, completionDate, projectNumString, projectNameString, buildingTypeString, clientAddressString, erfNumberString, netProfitString, payToDateString, deadlineString, nameString, cellNumString, emailAddressString, physicalAddressString, contractorNameString, contractorCellNumString, contractorEmailAddressString, contractorPhysicalAddressString, archNameString, archCellNumString, archEmailAddressString, archPhysicalAddressString, engineerString, cellNumEngineerString, emailAddressEngineerString, physicalAddressEngineerString);
        System.out.println(projectNew);
        objectsOfProject.add(projectNew);

        sqlAddClient("Client", nameString, cellNumString, emailAddressString, physicalAddressString);

        sqlAddContractor("Contractor", contractorNameString, contractorCellNumString, contractorEmailAddressString, contractorPhysicalAddressString);

        sqlAddArch("Architect", archNameString, archCellNumString, archEmailAddressString, archPhysicalAddressString);

        sqlAddEngineer("Engineer", engineerString, cellNumEngineerString, emailAddressEngineerString, physicalAddressEngineerString);

        sqlAddProject(finalised, completionDate, projectNumString, projectNameString, buildingTypeString, clientAddressString, erfNumberString, netProfitString, payToDateString, deadlineString, nameString, contractorNameString, archNameString, engineerString);
    }
    public void logEdit() throws SQLException {
        // editing the info logged
        // This enables editing of people and projects
        // Updates directly to SQL
        System.out.print("\nPE\t\tTo edit a Person\nPRO\t\tTo edit a Project\nEnter here: ");
        String editMenuInput = scan.nextLine();
        String editMenuLower = editMenuInput.toLowerCase();

        if (editMenuLower.equals("pe")) {

            System.out.print("Please enter the name of the person you would like to edit: ");
            String personEdit = scan.nextLine();

            for(int i = 0; i < objectsOfProject.toArray().length; i++){
                // Only edit if person is Architect, Contractor or Engineer
                // Ask for name and edit name given.

                if (personEdit.equals(objectsOfProject.get(i).getContractorName())){
                    System.out.print(objectsOfProject.get(i).toConString());

                    System.out.print("\nPlease enter Contractors details below: ");

                    System.out.print("\n\nWhat is this Contractors name, Please enter name and surname: ");
                    String contractorNameString = scan.nextLine();

                    System.out.print("\nWhat is this Contractors telephone number: ");
                    String contractorCellNumString = scan.nextLine();

                    System.out.print("\nWhat is this Contractors email Address: ");
                    String contractorEmailAddressString = scan.nextLine();

                    System.out.print("\nWhat is this Contractors physical address: ");
                    String contractorPhysicalAddressString = scan.nextLine();

                    // Update SQL and object of projects.
                    objectsOfProject.get(i).setContractor(contractorNameString, contractorCellNumString, contractorEmailAddressString, contractorPhysicalAddressString);
                    sqlPersonUpdateContractor(contractorNameString,contractorCellNumString,contractorEmailAddressString,contractorPhysicalAddressString,personEdit);
                }

                else if(personEdit.equals(objectsOfProject.get(i).getArchName())){
                    System.out.print(objectsOfProject.get(i).toArchString());

                    System.out.print("\nPlease enter Architects details below: ");

                    System.out.print("\n\nWhat is this Architects name, Please enter name and surname: ");
                    String archNameString = scan.nextLine();

                    System.out.print("\nWhat is this Architects telephone number: ");
                    String archCellNumString = scan.nextLine();

                    System.out.print("\nWhat is this Architects email Address: ");
                    String archEmailAddressString = scan.nextLine();

                    System.out.print("\nWhat is this Architects physical address: ");
                    String archPhysicalAddressString = scan.nextLine();

                    // Update SQL and object of projects.
                    objectsOfProject.get(i).setArch(archNameString, archCellNumString, archEmailAddressString, archPhysicalAddressString);
                    sqlPersonUpdateArch(archNameString,archEmailAddressString,archEmailAddressString,archPhysicalAddressString,personEdit);
                }

                else if(personEdit.equals(objectsOfProject.get(i).getEngName())){
                    System.out.print(objectsOfProject.get(i).toEngString());

                    System.out.print("\nPlease enter Engineers details below: ");

                    System.out.print("\n\nWhat is this Engineers name, Please enter name and surname: ");
                    String engineerString = scan.nextLine();

                    System.out.print("\nWhat is this Engineers telephone number: ");
                    String cellNumEngineerString = scan.nextLine();

                    System.out.print("\nWhat is this Engineers email Address: ");
                    String emailAddressEngineerString = scan.nextLine();

                    System.out.print("\nWhat is this Engineers physical address: ");
                    String physicalAddressEngineerString = scan.nextLine();

                    // Update SQL and object of projects.
                    objectsOfProject.get(i).setEngineer(engineerString, cellNumEngineerString, emailAddressEngineerString, physicalAddressEngineerString);
                    sqlPersonUpdateEngineer(engineerString,cellNumEngineerString,emailAddressEngineerString,physicalAddressEngineerString,personEdit);
                }
            }
        }
        else if (editMenuLower.equals("pro")) {
            // Iterate over the list of objects and look for the name entered by the user
            for(int iterator = 0; iterator < objectsOfProject.toArray().length;iterator++)
            {
                System.out.println(objectsOfProject.get(iterator));
            }
            System.out.print("\nPlease enter the number of the project you would like to edit: ");
            String numPersonEdit = scan.nextLine();

            // Log the edited info in the project object
            for(int projIterator = 0; projIterator < objectsOfProject.toArray().length; projIterator++) {
                if(Objects.equals(objectsOfProject.get(projIterator).getProjectNum(), numPersonEdit)) {
                    System.out.print("\nWhat is the due date of the Project, Please Enter in the format of Year-MM-dd: ");
                    String dueDateString = scan.nextLine();
                    objectsOfProject.get(projIterator).setDeadline(dueDateString);

                    System.out.print("\nHow much has the client paid to date: R");
                    float paidToDateString = Float.parseFloat(scan.nextLine());

                    objectsOfProject.get(projIterator).setPayToDate(paidToDateString);

                    // Sql connection
                    //Change the table in sql.
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS", "adamkraftk", "johnadam1@RSA");
                    PreparedStatement sqlUpdate = connection.prepareStatement("UPDATE Project "+"SET deadline = '" + dueDateString + "',clientPaid = '" + paidToDateString + "'WHERE projectNum = '" + numPersonEdit + "';");
                    sqlUpdate.executeUpdate();
                    connection.close();
                }
                }
            }
        }

    public void logFinal() throws SQLException {
        for(int iterator = 0; iterator < objectsOfProject.toArray().length;iterator++){
            System.out.println(objectsOfProject.get(iterator));
        }
        System.out.print("\nEnter the number of the project you would like to finalise: ");
        String finalEditString = scan.nextLine();

        for(int finalIterator = 0; finalIterator < objectsOfProject.toArray().length; finalIterator++){

            // If number entered by user is equal to number of project and client has not paid in full
            //Finalise project and give completion date
            if(Objects.equals(objectsOfProject.get(finalIterator).getProjectNum(), finalEditString) && objectsOfProject.get(finalIterator).getTotalProfit() != objectsOfProject.get(finalIterator).getPayToDate()) {
                System.out.print("\nProject is Finalised.");
                String finalisedString = "Finalised";

                System.out.print("\nWhat is the expected completion date: ");
                String completionString = scan.nextLine();

                objectsOfProject.get(finalIterator).setFinalised(finalisedString, completionString);
                System.out.print(objectsOfProject.get(finalIterator));

                // Sql connection
                //Change the table in sql.
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS", "adamkraftk", "johnadam1@RSA");
                PreparedStatement sqlUpdate = connection.prepareStatement("UPDATE Project SET deadline = '" + completionString + "',progress = '" + finalisedString + "'WHERE projectNum = '" + finalEditString + "';");
                sqlUpdate.executeUpdate();
                connection.close();

            }
            // If the project does not match the description given by the user
            else if(!Objects.equals(objectsOfProject.get(finalIterator).getProjectNum(), finalEditString)){
                System.out.print("Searching");
            }
            // If the user has paid in full
            //Finalise project and give completion date
            else{
                System.out.print("\nCustomer has paid in full! No invoice can be generated.");

                System.out.print("\n\nProject is Finalised.");
                String finalisedString = "Finalised";

                System.out.print("\nWhat is the expected completion date, Please enter the date in the format of yyyy-MM-dd: ");
                String completionString = scan.nextLine();

                objectsOfProject.get(finalIterator).setCompletionDate(finalisedString,completionString);

                // Sql connection
                //Change the table in sql.
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS", "adamkraftk", "johnadam1@RSA");
                PreparedStatement sqlUpdate = connection.prepareStatement("UPDATE Project SET deadline = '" + completionString + "',progress = '" + finalisedString + "'WHERE projectNum = '" + finalEditString + "';");
                sqlUpdate.executeUpdate();
                connection.close();
            }
        }
    }

    public void logPrintAll() {
        // Print all objects of project
        System.out.println("Projects: ");
        for(int iterator = 0; iterator < objectsOfProject.toArray().length;iterator++){
            System.out.println(objectsOfProject.get(iterator));}
    }

    public void logComplete() {
        // This looks for incomplete projects and adds them to a list and then prints that list
        for(int iteratorComplete = 0; iteratorComplete < objectsOfProject.toArray().length; iteratorComplete++) {
            if(objectsOfProject.get(iteratorComplete).getFinalised().equals("Finalised")) {
                completed.add(objectsOfProject.get(iteratorComplete));
            }
        }

        for(int iteratorCom = 0; iteratorCom < completed.toArray().length; iteratorCom++) {
            System.out.print(completed.get(iteratorCom));
        }

        if(completed.isEmpty()) {
            System.out.print("There are no completed projects");
        }
    }

    public void logIncomplete() {
        // This looks for complete projects and adds them to a list and then prints that list
        for(int iteratorIncomplete = 0; iteratorIncomplete < objectsOfProject.toArray().length; iteratorIncomplete++) {
            if(objectsOfProject.get(iteratorIncomplete).getFinalised().equals("Unfinalised"))
            {incomplete.add(objectsOfProject.get(iteratorIncomplete));}
        }
        for(int iteratorIncom = 0; iteratorIncom < incomplete.toArray().length; iteratorIncom++) {
            System.out.print(incomplete.get(iteratorIncom));
        }
        if(incomplete.isEmpty()) {
            System.out.print("There are no incomplete projects");
        }
    }

    public void logOverdue() throws ParseException {
        //This finds the current date and measures the date on the project and compares them
        Date date = new Date(System.currentTimeMillis());
        for (int i = 0; i < objectsOfProject.toArray().length; i++) {
            String dateFormat = "yyyy-MM-dd";
            Date setDate = new SimpleDateFormat(dateFormat).parse(objectsOfProject.get(i).getDeadline());
            if (setDate.before(date)) {
                PastDue.add(objectsOfProject.get(i));
            }
        }
        for(int iterator = 0; iterator < PastDue.toArray().length; iterator++) {
            System.out.print(PastDue.get(iterator));
        }
        if(PastDue.isEmpty()) {
            System.out.println("There are no Overdue Projects");
        }
    }

    public void sqlIn() throws SQLException {
        // Sql connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS", "adamkraftk", "johnadam1@RSA");
        Statement statement = connection.createStatement();
        ResultSet results;

        // Calls the data from SQL and saves them as a project
        results = statement.executeQuery("""
                Select * from Project
                    inner join Client on Project.clientName = Client.clientName
                    inner join Contractor on Project.contractorName = Contractor.contractorName
                    inner join Arch on Project.architectName = Arch.archName
                    inner join Engineer on Project.engineerName = Engineer.engineerName;""");
        while(results.next()) {
            ProjectInfo sqlProjectIn = new ProjectInfo(results.getString("progress"),results.getString("completionDate"),results.getString("projectNum"),results.getString("projectName"),results.getString("buildingType"),results.getString("addressOfBuilding"),results.getString("erf"),results.getFloat("totalProfit"),results.getFloat("clientPaid"),results.getString("deadline"),results.getString("clientName"),results.getString("clientCellNum"),results.getString("clientEmailAddress"),results.getString("clientPhysicalAddress"),results.getString("contractorName"),results.getString("contractorCellNum"),results.getString("contractorEmailAddress"),results.getString("contractorPhysicalAddress"),results.getString("archName"),results.getString("archCellNum"),results.getString("archEmailAddress"),results.getString("archPhysicalAddress"),results.getString("engineerName"),results.getString("engineerCellNum"),results.getString("engineerEmailAddress"),results.getString("engineerPhysicalAddress"));
            objectsOfProject.add(sqlProjectIn);
        }

        results.close();
        statement.close();
        connection.close();
    }


    public void sqlPersonUpdateContractor(String personName,String cellNum,String emailAddress,String Address,String personEdit) throws SQLException {
        // Sql connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS", "adamkraftk", "johnadam1@RSA");
        PreparedStatement killKeyCheck = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0 ;");

        //Change the table in sql.
        PreparedStatement statement = connection.prepareStatement("""
                            UPDATE Contactor\040
                            SET contractorName = ?,
                            contractorCellNum = ?,
                            contractorEmailAddress = ?,
                            contractorPhysicalAddress = ?
                            WHERE clientName = ? ;""");
        statement.setString(1,personName);
        statement.setString(2,cellNum);
        statement.setString(3,emailAddress);
        statement.setString(4,Address);
        statement.setString(5,personEdit);
        killKeyCheck.execute();
        statement.executeUpdate();

        PreparedStatement sqlUpdateProject = connection.prepareStatement("UPDATE Project SET contractorName = ? WHERE contactorName = ? ;");
        sqlUpdateProject.setString(1,personName);
        sqlUpdateProject.setString(2,personEdit);

        sqlUpdateProject.executeUpdate();
        connection.close();
    }

    public void sqlPersonUpdateArch(String personName,String cellNum,String emailAddress,String Address,String personEdit) throws SQLException {
        // Sql connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS", "adamkraftk", "johnadam1@RSA");
        PreparedStatement killKeyCheck = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0 ;");

        //Change the table in sql.
        PreparedStatement statement = connection.prepareStatement("""
                            UPDATE Arch\040
                            SET archName = ?,
                            archCellNum = ?,
                            archEmailAddress = ?,
                            archPhysicalAddress = ?
                            WHERE archName = ? ;""");
        statement.setString(1,personName);
        statement.setString(2,cellNum);
        statement.setString(3,emailAddress);
        statement.setString(4,Address);
        statement.setString(5,personEdit);
        killKeyCheck.execute();
        statement.executeUpdate();

        PreparedStatement sqlUpdateProject = connection.prepareStatement("UPDATE Project SET architectName = ? WHERE architectName = ? ;");
        sqlUpdateProject.setString(1,personName);
        sqlUpdateProject.setString(2,personEdit);

        sqlUpdateProject.executeUpdate();
        connection.close();
    }

    public void sqlPersonUpdateEngineer(String personName,String cellNum,String emailAddress,String Address,String personEdit) throws SQLException {
        // Sql connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS", "adamkraftk", "johnadam1@RSA");
        PreparedStatement killKeyCheck = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0 ;");

        //Change the table in sql.
        PreparedStatement statement = connection.prepareStatement("""
                            UPDATE Engineer\040
                            SET engineerName = ?,
                            engineerCellNum = ?,
                            engineerEmailAddress = ?,
                            engineerPhysicalAddress = ?
                            WHERE engineerName = ? ;""");
        statement.setString(1,personName);
        statement.setString(2,cellNum);
        statement.setString(3,emailAddress);
        statement.setString(4,Address);
        statement.setString(5,personEdit);
        killKeyCheck.execute();
        statement.executeUpdate();

        PreparedStatement sqlUpdateProject = connection.prepareStatement("UPDATE Project SET engineerName = ? WHERE engineerName = ? ;");
        sqlUpdateProject.setString(1,personName);
        sqlUpdateProject.setString(2,personEdit);

        sqlUpdateProject.executeUpdate();
        connection.close();
    }

    public void sqlAddClient(String personToCompany, String personName, String personCellNum, String personEmailAddress, String personPhysicalAddress) throws SQLException {
        // Sql connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS", "adamkraftk", "johnadam1@RSA");
        PreparedStatement killKeyCheck = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0 ;");
        PreparedStatement statement = connection.prepareStatement("""
            INSERT INTO Client VALUES(?, ?, ?, ?, ?);""");
        statement.setString(1,personToCompany);
        statement.setString(2,personName);
        statement.setString(3,personCellNum);
        statement.setString(4,personEmailAddress);
        statement.setString(5,personPhysicalAddress);
        killKeyCheck.execute();
        statement.execute();
    }

    public void sqlAddContractor(String personToCompany, String personName, String personCellNum, String personEmailAddress, String personPhysicalAddress) throws SQLException {
        // Sql connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS", "adamkraftk", "johnadam1@RSA");
        PreparedStatement killKeyCheck = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0 ;");
        PreparedStatement statement = connection.prepareStatement("""
            INSERT INTO Contractor VALUES(?, ?, ?, ?, ?);""");
        statement.setString(1,personToCompany);
        statement.setString(2,personName);
        statement.setString(3,personCellNum);
        statement.setString(4,personEmailAddress);
        statement.setString(5,personPhysicalAddress);
        killKeyCheck.execute();
        statement.execute();
    }

    public void sqlAddArch(String personToCompany, String personName, String personCellNum, String personEmailAddress, String personPhysicalAddress) throws SQLException {
        // Sql connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS", "adamkraftk", "johnadam1@RSA");
        PreparedStatement killKeyCheck = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0 ;");
        PreparedStatement statement = connection.prepareStatement("""
            INSERT INTO Arch VALUES(?, ?, ?, ?, ?);""");
        statement.setString(1,personToCompany);
        statement.setString(2,personName);
        statement.setString(3,personCellNum);
        statement.setString(4,personEmailAddress);
        statement.setString(5,personPhysicalAddress);
        killKeyCheck.execute();
        statement.execute();
    }

    public void sqlAddEngineer(String personToCompany, String personName, String personCellNum, String personEmailAddress, String personPhysicalAddress) throws SQLException {
        // Sql connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS", "adamkraftk", "johnadam1@RSA");
        PreparedStatement killKeyCheck = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0 ;");
        PreparedStatement statement = connection.prepareStatement("""
            INSERT INTO Engineer VALUES(?, ?, ?, ?, ?);""");
        statement.setString(1,personToCompany);
        statement.setString(2,personName);
        statement.setString(3,personCellNum);
        statement.setString(4,personEmailAddress);
        statement.setString(5,personPhysicalAddress);
        killKeyCheck.execute();
        statement.execute();
    }
    public void sqlAddProject(String finalised , String completionDate ,String projectNumString ,String projectNameString ,String addressOfBuilding, String buildingTypeString ,String erfNumberString ,Float netProfitString ,Float payToDateString ,String deadlineString ,String nameString ,String contractorNameString ,String archNameString ,String engineerString) throws SQLException {
        // Sql connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS", "adamkraftk", "johnadam1@RSA");
        PreparedStatement killKeyCheck = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0 ;");
        PreparedStatement statement = connection.prepareStatement("Insert into Project values(? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? );" );
        statement.setString(1,finalised);
        statement.setString(2,completionDate);
        statement.setString(3,projectNumString);
        statement.setString(4,projectNameString);
        statement.setString(5,buildingTypeString);
        statement.setString(6,addressOfBuilding);
        statement.setString(7,erfNumberString);
        statement.setFloat(8,netProfitString);
        statement.setFloat(9,payToDateString);
        statement.setString(10,deadlineString);
        statement.setString(11, nameString);
        statement.setString(12, contractorNameString);
        statement.setString(13, archNameString);
        statement.setString(14, engineerString);

        killKeyCheck.execute();
        statement.execute();
    }

    public void findProject(){
        // Print all
        System.out.print("Please Enter A Project Number to Find that Project: ");
        String projectNum = scan.nextLine();

        for(int iterator = 0; iterator < objectsOfProject.toArray().length;iterator++){
            if (Objects.equals(objectsOfProject.get(iterator).getProjectNum(), projectNum)){
                System.out.print(objectsOfProject.get(iterator));
            }
        }
    }
}
// I tried to make use of modularised programming on the SQL, but unfortunately it kept throwing errors