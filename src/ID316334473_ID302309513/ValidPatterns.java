package ID316334473_ID302309513;

public enum ValidPatterns {
	// Customer's name must have at least a first and a last name, and all words must be capitalized
	CUSTOMER_FULL_NAME("^[A-Z][a-z]+(?: [A-Z][a-z]+){1,}$"),
	
	// Customer's phone number consists of 3 dialing prefix digits and 7 digits
	CUSTOMER_PHONE_NUMBER("^05[2-4][0-9]{7}$"),

	// Product's ID must contain 4 digits
	PRODUCT_ID("^[A-Z]{3}[0-9]{3}$"),
	
	// Title capitalization rule - first word must always be capital
	// following words must be capital too, unless they're short words (1-3 letters)
	PRODUCT_NAME("^(?:[A-Z][a-z]+)(?: [A-Z][a-z]+| [a-z]{1,3})*$");

	private String pattern;

	ValidPatterns(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}
}
