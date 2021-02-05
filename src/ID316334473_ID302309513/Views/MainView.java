package ID316334473_ID302309513.Views;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import ID316334473_ID302309513.FileHandler;
import ID316334473_ID302309513.UIHandler;
import ID316334473_ID302309513.Models.CustomerModel;
import ID316334473_ID302309513.Models.ProductModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends WindowView {
	// Constants

	// Fields
	private HBox hBox, tableViewsHBox;
	private VBox buttonsVBox;
	private Button addProductButton, removeLastProductButton, removeProductByIDButton, removeAllProductsButton,
			notifyCustomersButton;
	private ObservableMap<String, ProductModel> allProducts;
	private ObservableList<CustomerModel> allCustomers;
	private TableView<Map.Entry<String, ProductModel>> productsTableView;
	private TableView<CustomerModel> customersTableView;

	// Properties (Getters and Setters)
	public Button getAddProductButton() {
		return addProductButton;
	}

	public Button getRemoveLastProductButton() {
		return removeLastProductButton;
	}

	public Button getRemoveProductByIDButton() {
		return removeProductByIDButton;
	}

	public Button getRemoveAllProductsButton() {
		return removeAllProductsButton;
	}

	public Button getNotifyCustomersButton() {
		return notifyCustomersButton;
	}

	public ObservableMap<String, ProductModel> getAllProducts() {
		return allProducts;
	}

	public ObservableList<CustomerModel> getAllCustomers() {
		return allCustomers;
	}

	public TableView<Map.Entry<String, ProductModel>> getProductsTableView() {
		return productsTableView;
	}

	public TableView<CustomerModel> getCustomersTableView() {
		return customersTableView;
	}

	// Constructors
	public MainView() {
		this(new Stage());
	}

	public MainView(Stage primaryStage) {
		super(primaryStage);

		buildScene();
		addEffects();

		readAllProducts();
	}

	// Methods
	@Override
	protected void buildScene() {
		double sceneWidth = 1500, sceneHeight = 700, backgroundFontSize = 50;

		hBox = new HBox();
		tableViewsHBox = new HBox();
		buttonsVBox = new VBox();
		addProductButton = new Button("Add Product");
		removeLastProductButton = new Button("Undo");
		removeProductByIDButton = new Button("Remove Product By ID");
		removeAllProductsButton = new Button("Remove All Products");
		notifyCustomersButton = new Button("Notify Customers");
		productsTableView = UIHandler.buildProductsTableView();
		customersTableView = UIHandler.buildCustomersTableView();

		hBox.setAlignment(Pos.CENTER);
		tableViewsHBox.setAlignment(Pos.CENTER);
		buttonsVBox.setAlignment(Pos.CENTER);
		addProductButton.setMinWidth(150);
		removeLastProductButton.setMinWidth(150);
		removeProductByIDButton.setMinWidth(150);
		removeAllProductsButton.setMinWidth(150);
		notifyCustomersButton.setMinWidth(150);
		productsTableView.setOpacity(0.7);
		customersTableView.setOpacity(0.7);

		tableViewsHBox.getChildren().addAll(productsTableView, customersTableView);
		HBox.setMargin(productsTableView, new Insets(0, 10, 0, 0));
		HBox.setMargin(customersTableView, new Insets(0, 0, 0, 10));

		buttonsVBox.getChildren().addAll(addProductButton, removeLastProductButton, removeProductByIDButton,
				removeAllProductsButton, notifyCustomersButton);
		VBox.setMargin(addProductButton, new Insets(0, 0, 10, 0));
		VBox.setMargin(removeLastProductButton, new Insets(10, 0, 10, 0));
		VBox.setMargin(removeProductByIDButton, new Insets(10, 0, 10, 0));
		VBox.setMargin(removeAllProductsButton, new Insets(10, 0, 10, 0));
		VBox.setMargin(notifyCustomersButton, new Insets(10, 0, 0, 0));

		hBox.getChildren().addAll(tableViewsHBox, buttonsVBox);
		HBox.setMargin(tableViewsHBox, new Insets(110, 10, 90, 0));
		HBox.setMargin(buttonsVBox, new Insets(110, 0, 90, 10));

		stage.setScene(
				new Scene(UIHandler.buildBackground("Grocery.jpg", hBox, sceneWidth, sceneHeight, backgroundFontSize),
						sceneWidth, sceneHeight));
		UIHandler.setGeneralFeatures(stage);

		stage.show();
		UIHandler.playAudio("Shufersal.wav");
	}

	@Override
	protected void addEffects() {
		super.addEffects();
	}

	@Override
	protected Node asNode() {
		return (Node) hBox;
	}

	public void readAllProducts() {
		FileHandler fileHandler = new FileHandler();
		Iterator<ProductModel> it = fileHandler.iterator();
		ProductModel currentProduct = null;

		allProducts = FXCollections.observableMap(new TreeMap<String, ProductModel>());
		allCustomers = FXCollections.observableArrayList();

		while (it.hasNext()) {
			currentProduct = it.next();
			if (!allProducts.containsKey(currentProduct.getTextualID()))
				addProduct(currentProduct);
		}
	}

	public void writeAllProducts() {
		for (ProductModel product : allProducts.values())
			FileHandler.writeProductToFile(product);
	}

	public void addProduct(ProductModel product) {
		CustomerModel currentCustomer = product.getCustomer();

		allProducts.put(product.getTextualID(), product);
		if ((currentCustomer != null) && (!allCustomers.contains(currentCustomer)))
			allCustomers.add(currentCustomer);

		productsTableView.setItems(FXCollections.observableArrayList(allProducts.entrySet()));
		customersTableView.setItems(allCustomers);
	}

	public void removeProduct(ProductModel product) {
		allProducts.remove(product.getTextualID());
	}
}