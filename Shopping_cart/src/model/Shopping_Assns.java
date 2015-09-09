package model;

public class Shopping_Assns {
	private Product product;
	private OnlineCustomer customer;
	private ShoppingCart cart;
	private Comment comment;
	private Transaction transaction;
	private Order order;
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public ShoppingCart getCart() {
		return cart;
	}
	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public OnlineCustomer getCustomer() {
		return customer;
	}
	public void setCustomer(OnlineCustomer customer) {
		this.customer = customer;
	}
}
