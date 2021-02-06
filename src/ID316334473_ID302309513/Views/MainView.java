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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainView extends WindowView {
	// Constants

	// Fields
	private HBox hBox, tableViewsHBox, totalProfitHBox;
	private VBox productsVBox, customersVBox, buttonsVBox;
	private Label productsLabel, customersLabel, totalProfitLabel;
	private TextField totalProfitTextField;
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
		double sceneWidth = 1500, sceneHeight = 700, backgroundFontSize = 50, viewFontSize = 20;

		hBox = new HBox();
		tableViewsHBox = new HBox();
		totalProfitHBox = new HBox();
		productsVBox = new VBox();
		customersVBox = new VBox();
		buttonsVBox = new VBox();
		productsLabel = new Label("Products");
		customersLabel = new Label("Customers");
		totalProfitLabel = new Label("Total Profit:");
		totalProfitTextField = new TextField();
		addProductButton = new Button("Add Product");
		removeLastProductButton = new Button("Undo");
		removeProductByIDButton = new Button("Remove Product By ID");
		removeAllProductsButton = new Button("Remove All Products");
		notifyCustomersButton = new Button("Notify Customers");
		productsTableView = UIHandler.buildProductsTableView();
		customersTableView = UIHandler.buildCustomersTableView();

		hBox.setAlignment(Pos.CENTER);
		productsVBox.setAlignment(Pos.CENTER);
		customersVBox.setAlignment(Pos.CENTER);
		tableViewsHBox.setAlignment(Pos.CENTER);
		totalProfitHBox.setAlignment(Pos.CENTER);
		buttonsVBox.setAlignment(Pos.CENTER);
		productsLabel.setFont(new Font(backgroundFontSize));
		productsLabel.setAlignment(Pos.CENTER);
		customersLabel.setFont(new Font(backgroundFontSize));
		customersLabel.setAlignment(Pos.CENTER);
		totalProfitLabel.setFont(new Font(viewFontSize));
		totalProfitTextField.setAlignment(Pos.CENTER);
		totalProfitTextField.setEditable(false);
		addProductButton.setMinWidth(150);
		removeLastProductButton.setMinWidth(150);
		removeProductByIDButton.setMinWidth(150);
		removeAllProductsButton.setMinWidth(150);
		notifyCustomersButton.setMinWidth(150);
		productsTableView.setOpacity(0.7);
		customersTableView.setMinHeight(430);
		customersTableView.setOpacity(0.7);

		totalProfitHBox.getChildren().addAll(totalProfitLabel, totalProfitTextField);
		HBox.setMargin(totalProfitLabel, new Insets(0, 10, 0, 0));
		HBox.setMargin(totalProfitTextField, new Insets(0, 0, 0, 10));

		productsVBox.getChildren().addAll(productsLabel, productsTableView, totalProfitHBox);
		VBox.setMargin(productsLabel, new Insets(0, 0, 0, 0));
		VBox.setMargin(productsTableView, new Insets(10, 0, 10, 0));
		VBox.setMargin(totalProfitHBox, new Insets(10, 0, 0, 0));
		
		customersVBox.getChildren().addAll(customersLabel, customersTableView);
		VBox.setMargin(customersLabel, new Insets(0, 0, 0, 0));
		VBox.setMargin(customersTableView, new Insets(10, 0, 0, 0));

		tableViewsHBox.getChildren().addAll(productsVBox, customersVBox);
		HBox.setMargin(productsVBox, new Insets(0, 10, 0, 0));
		HBox.setMargin(customersVBox, new Insets(0, 0, 0, 10));

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
	}

	@Override
	protected void addEffects() {
		super.addEffects();

		UIHandler.addCursorEffectsToNode(addProductButton);
		UIHandler.addCursorEffectsToNode(removeLastProductButton);
		UIHandler.addCursorEffectsToNode(removeProductByIDButton);
		UIHandler.addCursorEffectsToNode(removeAllProductsButton);
		UIHandler.addCursorEffectsToNode(notifyCustomersButton);
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
		Iterator<ProductModel> iterator = null;
		CustomerModel currentCustomer = product.getCustomer();
		int totalProfit = 0;

		allProducts.put(product.getTextualID(), product);
		if ((currentCustomer != null) && (!allCustomers.contains(currentCustomer)))
			allCustomers.add(currentCustomer);

		iterator = allProducts.values().iterator();
		while (iterator.hasNext())
			totalProfit += iterator.next().getNumericProfit();

		productsTableView.setItems(FXCollections.observableArrayList(allProducts.entrySet()));
		customersTableView.setItems(allCustomers);
		totalProfitTextField.setText("" + totalProfit);
	}

	public void removeProduct(ProductModel product) {
		allProducts.remove(product.getTextualID());
	}
}