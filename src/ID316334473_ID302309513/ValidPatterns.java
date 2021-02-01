package ID316334473_ID302309513;

public enum ValidPatterns {
	// Customer's name must have at least a first and a last name, and all words must be capitalized
	CUSTOMER_FULL_NAME("^[A-Z][a-z]+(?: [A-Z][a-z]+){1,}$"),
	
	// Customer's phone number consists of 3 dialing prefix digits, comma and 7 digits
	CUSTOMER_PHONE("^05[2-4]-[0-9]{7}$"),
	
	// Title capitalization rule - first word must always be capital
	// following words must be capital too, unless they're short words (1-3 letters)
	PRODUCT_NAME("^(?:[A-Z][a-z]+)(?: [A-Z][a-z]+| [a-z]{1,3})*$");
	
	// Ballot address can start with building number;
	// all words follow the title capitalization rule mentioned above
//	BALLOT_ADDRESS(
//			"^(?:[0-9]{1,3} )?(?:[A-Z][a-z]+)(?: [A-Z][a-z]+| [a-z]{1,3})*, (?:[A-Z][a-z]+)(?: [A-Z][a-z]+| [a-z]{1,3})*$");

	private String pattern;

	ValidPatterns(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}
}
