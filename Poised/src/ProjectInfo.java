public class ProjectInfo {

    private String finalised;
    private String completionDate;
    private String projectNum;
    private String projectName;
    private String buildingType;
    private String clientAddress;
    private String erfNum;
    private float TotalProfit;
    private float payToDate;
    private String deadline;
    private String clientToCompany;
    private String clientName;
    private String clientCellNum;
    private String clientEmailAddress;
    private String clientPhysicalAddress;
    private String contractorToCompany;
    private String contractorName;
    private String contractorCellNum;
    private String contractorEmailAddress;
    private String contractorPhysicalAddress;
    private String archToCompany;
    private String archName;
    private String archCellNum;
    private String archEmailAddress;
    private String archPhysicalAddress;

    private String engineerToCompany;
    private String nameEngineer;
    private String cellNumEngineer;
    private String emailAddressEngineer;
    
    private String physicalAddressEngineer;

    public ProjectInfo(String finalised, String completionDate, String projectNum, String projectName, String buildingType, String clientAddress, String erfNum, float TotalProfit, float payToDate, String deadline, String clientName, String clientCellNum, String clientEmailAddress, String clientPhysicalAddress, String contractorName, String contractorCellNum, String contractorEmailAddress, String contractorPhysicalAddress, String archName, String archCellNum, String archEmailAddress, String archPhysicalAddress, String nameEngineer, String cellNumEngineer, String emailAddressEngineer, String physicalAddressEngineer) {
        this.finalised = finalised;
        this.completionDate = completionDate;
        this.projectNum = projectNum;
        this.projectName = projectName;
        this.buildingType = buildingType;
        this.clientAddress = clientAddress;
        this.erfNum = erfNum;
        this.TotalProfit = TotalProfit;
        this.payToDate = payToDate;
        this.deadline = deadline;
        this.clientToCompany = "Client";
        this.clientName = clientName;
        this.clientCellNum = clientCellNum;
        this.clientEmailAddress = clientEmailAddress;
        this.clientPhysicalAddress = clientPhysicalAddress;
        this.contractorToCompany = "Contractor";
        this.contractorName = contractorName;
        this.contractorCellNum = contractorCellNum;
        this.contractorEmailAddress = contractorEmailAddress;
        this.contractorPhysicalAddress = contractorPhysicalAddress;
        this.archToCompany = "Architect";
        this.archName = archName;
        this.archCellNum = archCellNum;
        this.archEmailAddress = archEmailAddress;
        this.archPhysicalAddress = archPhysicalAddress;
        this.engineerToCompany = "Engineer";
        this.nameEngineer = nameEngineer;
        this.cellNumEngineer = cellNumEngineer;
        this.emailAddressEngineer = emailAddressEngineer;
        this.physicalAddressEngineer = physicalAddressEngineer;
    }

    public String getEngName(){return nameEngineer;}

    public String getContractorName() {return contractorName;}

    public String getDeadline() {return deadline;}

    public String getArchName() {return archName;}

    public String getProjectNum(){return this.projectNum;}

    public float getTotalProfit(){return this.TotalProfit;}

    public float getPayToDate(){return this.payToDate;}

    public void setDeadline(String deadline) {this.deadline = deadline;}

    public String getFinalised() {return finalised;}

    public void setPayToDate(float payToDate) {
        this.payToDate = payToDate;
    }

    public void setContractor(String contractorName, String contractorCellNum, String contractorEmailAddress, String contractorPhysicalAddress) {
        this.contractorName = contractorName;
        this.contractorCellNum = contractorCellNum;
        this.contractorEmailAddress = contractorEmailAddress;
        this.contractorPhysicalAddress = contractorPhysicalAddress;
    }

    public void setArch(String archName, String archCellNum,String archEmailAddress, String archPhysicalAddress) {
        this.archName = archName;
        this.archCellNum = archCellNum;
        this.archEmailAddress = archEmailAddress;
        this.archPhysicalAddress = archPhysicalAddress;
    }

    public void setEngineer( String nameEngineer, String cellNumEngineer, String emailAddressEngineer, String physicalAddressEngineer){
        this.nameEngineer = nameEngineer;
        this.cellNumEngineer = cellNumEngineer;
        this.emailAddressEngineer = emailAddressEngineer;
        this.physicalAddressEngineer = physicalAddressEngineer;
    }

    public String setFinalised(String finalised, String completionDate) {
        this.finalised = finalised;
        this.completionDate = completionDate;
        // To find amount owed
        float amountOwed = TotalProfit - payToDate;

        System.out.print("\nInvoice for: " + clientName + "\nFor Project: " + projectName + "\nProject number: " + projectNum + "\nAddress Of Client: " + clientPhysicalAddress + "\nFor the amount of R" + amountOwed);
        return "\nThe amount payable is: " + amountOwed;
    }

    public String setCompletionDate(String finalised, String completionDate) {
        this.finalised = finalised;
        this.completionDate = completionDate;
        return "Project has been Finalised";
    }

    public String toString(){
        String projectInformation = "\n\nStatus: " + finalised + "\nWhen will the project be finished: " + completionDate + "\nProject Number: " + projectNum + "\nProject Name: " + projectName + "\nType of Building: " +
                buildingType + "\nAddress of Client: " + clientAddress + "\nERF Number: " + erfNum + "\nAmount Charged: R" + TotalProfit + "\nAmount that the Client has paid: R" + payToDate +
                "\nProject Deadline: " + deadline + "\n\n";

        String perInformation = "\nPerson's Relation to Company: " + clientToCompany + "\nClient's Name: " + clientName
                + "\nClient's Telephone Number: " + clientCellNum + "\nClient's email address: " + clientEmailAddress + "\nClient's Physical Address: " + clientPhysicalAddress + "\n\n";

        String contractorInformation = "\nPerson's Relation to Company: " + contractorToCompany + "\nContractor's Name: " + contractorName
                + "\nContractor's Telephone Number: " + contractorCellNum + "\nContractor's email address: " + contractorEmailAddress + "\nContractor's Physical Address: " + contractorPhysicalAddress + "\n\n";

        String archInformation = "\nPerson's Relation to Company: " + archToCompany + "\nArchitect's Name: " + archName
                + "\nArchitect's Telephone Number: " + archCellNum + "\nArchitect's email address: " + archEmailAddress + "\nArchitect's Physical Address: " + archPhysicalAddress + "\n";

        String engineerInformation = "\nPerson's Relation to Company: " + engineerToCompany + "\nEngineer's Name: " + nameEngineer
                + "\nEngineer's Telephone Number: " + cellNumEngineer + "\nEngineer's email address: " + emailAddressEngineer + "\nEngineer's Physical Address: " + physicalAddressEngineer + "\n";

        return projectInformation + perInformation + contractorInformation + archInformation + engineerInformation;
    }

    public String toConString(){
        return "\nPerson's Relation to Company: " + contractorToCompany + "\nContractor's Name: " + contractorName
                + "\nContractor's Telephone Number: " + contractorCellNum + "\nContractor's email address: " + contractorEmailAddress + "\nContractor's Physical Address: " + contractorPhysicalAddress + "\n\n";
    }

    public String toArchString(){
        return "\nPerson's Relation to Company: " + archToCompany + "\nArchitect's Name: " + archName
                + "\nArchitect's Telephone Number: " + archCellNum + "\nArchitect's email address: " + archEmailAddress + "\nArchitect's Physical Address: " + archPhysicalAddress + "\n";
    }

    public String toEngString(){
        return "\nPerson's Relation to Company: " + engineerToCompany + "\nEngineer's Name: " + nameEngineer
                + "\nEngineer's Telephone Number: " + cellNumEngineer + "\nEngineer's email address: " + emailAddressEngineer + "\nEngineer's Physical Address: " + emailAddressEngineer + "\n";
    }
}
