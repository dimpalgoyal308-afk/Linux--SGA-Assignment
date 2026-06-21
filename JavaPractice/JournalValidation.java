import java.util.*;


class CheckNameException extends Exception {
	public CheckNameException(String m) {
		super(m);
	}
}

class CheckJournalException extends Exception {
	public CheckJournalException(String m) {
		super(m);
	}
}

class CheckIssueNumberException extends Exception {
	public CheckIssueNumberException(String m) {
		super(m);
	}
}

class CheckIssnException extends Exception {
	public CheckIssnException(String m) {
		super(m);
	}
}

class Journal {
	private String name;
	private String journalId;
	private String issueNumber;
	private String issn;

	public Journal(String name, String journalId, String issueNumber, String issn) {
		this.name = name;
		this.journalId = journalId;
		this.issueNumber = issueNumber;
		this.issn = issn;
	}

	public String getName() {
		return name;
	}

	public String getJournalId() {
		return journalId;
	}

	public String getIssueNumber() {
		return issueNumber;
	}

	public String getIssn() {
		return issn;
	}

	public String toString() {
		return "Journal{name='" + name + "', journalId='" + journalId + "', issueNumber='" + issueNumber + "', issn='"
				+ issn + "'}";
	}

	public void validateJournal()
			throws CheckNameException, CheckJournalException, CheckIssueNumberException, CheckIssnException {
		// validate name, should not be longer than 30.
		String name = this.getName();
		if (name.length() > 30) {
			throw new CheckNameException("Name must be less than 30 characters");
		}
		// validate Id, should only be alphanumeric
		String journalId = this.getJournalId();
		for (int i = 0; i < journalId.length(); i++) {
			if (!Character.isLetterOrDigit(journalId.charAt(i))) {
				throw new CheckJournalException("Journal ID must only contain alphanumeric characters");
			}
		}
		// validate issue number, should not be longer than 20.
		String issueNumber = this.getIssueNumber();
		if (issueNumber.length() > 20) {
			throw new CheckIssueNumberException("Issue number must be less less than or equal to 20 characters");
		}

		// validate issn , length should be equal to 9
		// format should be NNNN-NNNN
		String issn = this.getIssn();
		if (issn.length() != 9) {
			throw new CheckIssnException("ISSN must be equal to 9 characters");
		}
		if (issn.charAt(4) != '-') {
			throw new CheckIssnException("ISSN must have a hyphen at the 5th position");
		}
		for (int i = 0; i < issn.length(); i++) {
			if (i == 4) {
				continue;
			}
			if (!Character.isDigit(issn.charAt(i))) {
				throw new CheckIssnException("ISSN must only contain digits and hyphens");
			}
		}
	}

	public static void main(String[] args) {
		List<Journal> journals = new ArrayList<>();
		List<Journal> validations = new ArrayList<>();

		// valid journals
		journals.add(new Journal("Activities", "Activ20", "Act256", "1234-5678"));
		journals.add(new Journal("Fun", "Fun9", "Fun3", "1294-5278"));

		// invalid name
		journals.add(
				new Journal("ldfkhkrjhwfhekjhrjkerhkjrhgkjerhgkjrehgkjrehgkjerhg", "Sport3", "Spor56", "234478345"));
		// invalid JournalId
		journals.add(new Journal("Dance", "Dan@", "Dan34", "5678-2345"));
		// Invalid issue number
		journals.add(new Journal("Play", "Play7", "thenameistoolongcantsotre", "3434-5674"));
		// invalid ISSN
		journals.add(new Journal("Sprts", "Sport3", "Spor56", "234478345"));

		for (int i = 0; i < journals.size(); i++) {
			Journal currentjournal = journals.get(i);
			try {
				currentjournal.validateJournal();
				validations.add(currentjournal);
			} catch (CheckNameException | CheckJournalException | CheckIssueNumberException | CheckIssnException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Valid Journals : ");
		for (int i = 0; i < validations.size(); i++) {
			System.out.println(validations.get(i));
		}
	}
}
