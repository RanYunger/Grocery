package ID316334473_ID302309513.Models;

public class MementoModel {
	// Fields
	private ProductModel lastAddedProduct;

	// Constructors
	public MementoModel(ProductModel lastAddedProduct) {
		this.lastAddedProduct = lastAddedProduct;
	}

	// Methods
	public ProductModel getLastAddedProduct() {
		return lastAddedProduct;
	}
}